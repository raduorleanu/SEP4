package io.github.raduorleanu.sep4.databaseHandlers;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import io.github.raduorleanu.sep4.models.User;
import io.github.raduorleanu.sep4.repositories.ParticipantRepository;
import io.github.raduorleanu.sep4.util.FirebaseProvider;

public class ParticipantDbHandler {

    private ParticipantRepository repository;
    private FirebaseDatabase db;
    private static DatabaseReference dbReference;

    public ParticipantDbHandler(ParticipantRepository repository, String databasePath) {
        this.repository = repository;
        this.db = FirebaseProvider.getDb();
        dbReference = db.getReference(databasePath);
        addListeners();
    }

    public void addListeners() {

        dbReference.addListenerForSingleValueEvent(new ReadOnceParticipant());
        dbReference.addChildEventListener(new ChangeParticipant());
    }

    private class ChangeParticipant implements ChildEventListener {

        @Override
        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            if (dataSnapshot.exists()) {
                for (DataSnapshot temp : dataSnapshot.getChildren()) {
                    User user = temp.getValue(User.class);
                    repository.insertData(user);
                }

            }
        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()) {
                User user = dataSnapshot.getValue(User.class);
                repository.removeData(user);
            }
        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    }

    private class ReadOnceParticipant implements ValueEventListener {

        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if(dataSnapshot.exists() && dataSnapshot != null){

                List<User> temp = new ArrayList<>();

                for (DataSnapshot data: dataSnapshot.getChildren()) {
                    User user = data.getValue(User.class);

                    if (user != null) {
                        temp.add(user);
                    }
                }
                repository.updateData(temp);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    }

}
