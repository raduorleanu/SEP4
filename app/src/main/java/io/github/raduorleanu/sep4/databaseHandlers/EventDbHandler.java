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

import io.github.raduorleanu.sep4.models.Event;
import io.github.raduorleanu.sep4.repositories.EventRepository;
import io.github.raduorleanu.sep4.util.FirebaseProvider;

public class EventDbHandler {

    private EventRepository repository;
    private FirebaseDatabase db;
    private static DatabaseReference dbReference;

    public EventDbHandler(EventRepository repository, String databasePath) {
        this.repository = repository;
        db = FirebaseProvider.getDb();
        dbReference = db.getReference(databasePath);
        addListeners();
    }

    public void addListeners() {

        dbReference.addListenerForSingleValueEvent(new ReadOnceEvent());
        dbReference.addChildEventListener(new ChangeEvent());
    }

    private class ChangeEvent implements ChildEventListener {

        // toDo; no easy way to test this yet
        @Override
        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            if(dataSnapshot.exists()) {
                Event event = dataSnapshot.getValue(Event.class);
                repository.insertData(event);
            }
        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            if(dataSnapshot.exists()) {
                Event event = dataSnapshot.getValue(Event.class);
                repository.removeData(event);
            }
        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    }

    private class ReadOnceEvent implements ValueEventListener {

        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if(dataSnapshot.exists() && dataSnapshot.getValue() != null) {

                Log.w("ReadOnce", "called");

                List<Event> arr = new ArrayList<>();

                for(DataSnapshot s : dataSnapshot.getChildren()) {
                    Event value = s.getValue(Event.class);

                    if(value != null) {
                        arr.add(value);
                    } else {
                        Log.w("data-snap", "value was null");
                    }
                }
                repository.updateData(arr);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    }
}
