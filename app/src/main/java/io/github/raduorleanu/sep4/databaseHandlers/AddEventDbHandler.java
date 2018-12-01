package io.github.raduorleanu.sep4.databaseHandlers;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import io.github.raduorleanu.sep4.models.Event;
import io.github.raduorleanu.sep4.util.FirebaseProvider;

public class AddEventDbHandler {

    private FirebaseDatabase db;

    public AddEventDbHandler() {
        db = FirebaseProvider.getDb();
    }

    public void addEvent(Event event) {
        DatabaseReference dbReference = db.getReference("events");
        HashMap<String, Object> map = new HashMap<>();
        map.put(event.get_id(), event);
        dbReference.updateChildren(map);
    }
}
