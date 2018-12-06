package io.github.raduorleanu.sep4.databaseHandlers;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

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

import static android.content.ContentValues.TAG;

public class ParticipantDbHandler {

    private ParticipantRepository repository;
    private FirebaseDatabase db;
    private static DatabaseReference dbReference;

    public ParticipantDbHandler(ParticipantRepository repository, String databasePath, String eventId) {
        this.repository = repository;
        this.db = FirebaseProvider.getDb();
        dbReference = db.getReference(databasePath + eventId);
        addListeners();
    }

    public void addListeners() {

        dbReference.addListenerForSingleValueEvent(new ReadOnceParticipant());
        dbReference.addValueEventListener(new ChangeParticipant());
    }

    private class ChangeParticipant implements ValueEventListener {


        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            List<User> tempUsers = new ArrayList<>();
            for (DataSnapshot data : dataSnapshot.getChildren()) {
                User user = data.getValue(User.class);

                tempUsers.add(user);
            }
            repository.updateData(tempUsers);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    }

    private class ReadOnceParticipant implements ValueEventListener {

        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists() && dataSnapshot != null) {

                List<User> temp = new ArrayList<>();

                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    User user = data.getValue(User.class);

                    if (user != null) {
                        Log.e(TAG, "User were added");
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
