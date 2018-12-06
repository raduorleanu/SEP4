package io.github.raduorleanu.sep4.databaseHandlers;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;

import io.github.raduorleanu.sep4.models.User;
import io.github.raduorleanu.sep4.repositories.AddFriendsRepository;
import io.github.raduorleanu.sep4.util.Constants;
import io.github.raduorleanu.sep4.util.FirebaseProvider;

public class AddFriendsDbHandler {

    private AddFriendsRepository repository;

    public AddFriendsDbHandler(AddFriendsRepository repository) {
        this.repository = repository;
    }

    public void searchData(String data) {
        FirebaseDatabase db = FirebaseProvider.getDb();
        // get all users?
        final DatabaseReference ref = db.getReference("users");
        ref.orderByChild("name").startAt(data).limitToFirst(5).addChildEventListener(new OrderedByName());
    }

    public void addFriends(List<User> users) {
        FirebaseDatabase db = FirebaseProvider.getDb();
        DatabaseReference dbReference = db.getReference("friends/" + Constants.currentUser.get_id());
        for(User u: users) {
            // push with custom key
            dbReference.child(u.get_id()).setValue(u);
        }
    }

    class OrderedByName implements ChildEventListener {

        @Override
        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            User user = dataSnapshot.getValue(User.class);
            if(user != null ) {
                repository.insertSearchResultSingleUser(user);
            } else {
                Log.w("Found user", "was null");
            }
        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    }

}
