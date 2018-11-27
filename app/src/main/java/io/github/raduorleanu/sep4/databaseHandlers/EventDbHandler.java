package io.github.raduorleanu.sep4.databaseHandlers;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;
import java.util.Objects;

import io.github.raduorleanu.sep4.interfaces.IRepository;
import io.github.raduorleanu.sep4.models.Event;
import io.github.raduorleanu.sep4.util.FirebaseProvider;

public class EventDbHandler {

    private FirebaseDatabase db;
    private static DatabaseReference dbReference;
    private IRepository repository;

    public EventDbHandler(IRepository repository) {
        this.repository = repository;
        db = FirebaseProvider.getDb();
        dbReference = db.getReference("events");
        addListeners();
    }

    public void addListeners() {
        dbReference.addValueEventListener(new ChangeEvent());
    }

    private class ChangeEvent implements ValueEventListener {

        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if(dataSnapshot.exists() && dataSnapshot.getValue() != null) {
                //logLol((Map<String, Object>) dataSnapshot.getValue());

                for(DataSnapshot s : dataSnapshot.getChildren()) {
                    Event event = s.getValue(Event.class);

                    if(event != null) {
                        //Log.w("data-snap event", event.toString());
                        repository.insertData(event);
                    } else {
                        Log.w("data-snap", "event was null");
                    }
                }
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }

        private void logLol(Map<String, Object> map) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                Map m = (Map) entry.getValue();
                //Log.w("map entry", Objects.requireNonNull(m.get("description")).toString());
            }
        }
    }

}
