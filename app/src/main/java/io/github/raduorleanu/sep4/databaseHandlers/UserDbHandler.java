package io.github.raduorleanu.sep4.databaseHandlers;

import android.support.annotation.NonNull;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import io.github.raduorleanu.sep4.models.Event;
import io.github.raduorleanu.sep4.models.User;
import io.github.raduorleanu.sep4.repositories.AddUsersToEventUserSwapRepository;
import io.github.raduorleanu.sep4.util.Constants;
import io.github.raduorleanu.sep4.util.FirebaseProvider;

public class UserDbHandler {

    private AddUsersToEventUserSwapRepository repository;

    // the event attached to started the new activity
    private Event event;

    public UserDbHandler(AddUsersToEventUserSwapRepository repository, Event event) {
        this.repository = repository;
        this.event = event;
        //addData();
        getDataFromDb();
    }

    private void addData() {
        List<User> l1 = new ArrayList<>();
        List<User> l2 = new ArrayList<>();

        l1.add(new User("Momo", "", "", "", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRyuL3-fEOFYHRS-CFfzK4kQe-tp9HZtZ-EVL7wjk9rFvXNqnzt"));
        l1.add(new User("Mina", "", "", "", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQYHVyDCUF6wUBYD_nQZUVJb_4kyoicqd22BJO7_-tlkp1kQRLZ"));
        l1.add(new User("Sana", "", "", "", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSR4-0VFcAYf1cGu697c0vbI5uc-cg6uQTPU2Uf9JZJ2R1DI0ws"));
        l1.add(new User("JiHyo", "", "", "", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ3cFoUL_agNdrAtaQCWo_0D8qdQqby32ayHdhbT754V59QEKSnZg"));

        repository.updateNotGoing(l1);
        repository.updateGoing(l2);
    }

    private void getDataFromDb() {
        FirebaseDatabase db = FirebaseProvider.getDb();
        final DatabaseReference ref = db.getReference("friends");
        ref.child(Constants.currentUser.get_id()).addValueEventListener(new FriendsOfUser());
    }

    class AlreadyGoing implements ValueEventListener {

        private List<User> allFriends;

        AlreadyGoing(List<User> allFriends) {
            this.allFriends = allFriends;
        }

        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            //Log.w("asdAASD", "query_ing -> " + event.get_id());
            if(dataSnapshot.exists()) {
                //Log.w("asdAASD", "it exists -> " + event.get_id());
                List<User> alreadyGoing = new ArrayList<>();
                for(DataSnapshot value: dataSnapshot.getChildren()) {
                    User u = value.getValue(User.class);

                    if(u != null) {
                        //Log.w("asdAASD", "one of the already going -> " + u.toString());

                        alreadyGoing.add(u);
                    }
                }
                repository.updateGoing(alreadyGoing);
                repository.updateNotGoing(filterUsers(alreadyGoing));
            } else {
                repository.updateNotGoing(allFriends);
                repository.updateGoing(new ArrayList<User>());
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }

        private List<User> filterUsers(List<User> alreadyGoing) {
            List<User> filtered = new ArrayList<>(allFriends);
            filtered.removeAll(alreadyGoing);
            return filtered;
        }
    }

    class FriendsOfUser implements ValueEventListener {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if(dataSnapshot.exists()) {
                List<User> friends = new ArrayList<>();
                for(DataSnapshot value: dataSnapshot.getChildren()) {
                    User u = value.getValue(User.class);
                    assert u != null;
                    friends.add(u);
                }
                FirebaseDatabase db = FirebaseProvider.getDb();
                final DatabaseReference ref = db.getReference("attendees");
                ref.child(event.get_id()).addValueEventListener(new AlreadyGoing(friends));
            } else {
                repository.checkFriendsNumber();
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    }


}
