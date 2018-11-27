package io.github.raduorleanu.sep4.util;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import io.github.raduorleanu.sep4.models.Event;
import io.github.raduorleanu.sep4.models.User;

public class AddDataToFireBase {

    FirebaseDatabase database = FirebaseProvider.getDb();
    DatabaseReference reference;

    public AddDataToFireBase() {
        reference = database.getReference("events");
        addData();
    }

    private void addData() {
        Map<String, Event> eventMap = new HashMap<>();
        eventMap.put("Event1", new Event(new User("Catalin"), "movie night"));
        eventMap.put("Event2", new Event(new User("Kristian"), "learning german"));
        eventMap.put("Event3", new Event(new User("Yusuf"), "grill"));
        eventMap.put("Event4", new Event(new User("Catalin"), "doing nothing together"));
        reference.setValue(eventMap);
        Log.w("db-write", "maybe");
    }

}
