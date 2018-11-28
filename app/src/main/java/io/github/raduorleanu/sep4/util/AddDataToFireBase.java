package io.github.raduorleanu.sep4.util;

import android.content.Context;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import io.github.raduorleanu.sep4.models.Event;
import io.github.raduorleanu.sep4.models.User;

public class AddDataToFireBase {

    private FirebaseDatabase database = FirebaseProvider.getDb();
    private DatabaseReference reference;
    private int n;
    private int c;
    private Context context;

    public AddDataToFireBase(int numberOfEvents, int ceilNumberOfUsersPerEvent, Context context) {
        reference = database.getReference("events");
        n = numberOfEvents;
        c = ceilNumberOfUsersPerEvent;
        this.context = context;
        addData();
    }

    private void addData() {
        FakeUser f = new FakeUser(context);
        Map<String, Event> eventMap = new HashMap<>();
//        eventMap.put("Event1", new Event(new User("Catalin"), "movie night"));
//        eventMap.put("Event2", new Event(new User("Kristian"), "learning german"));
//        eventMap.put("Event3", new Event(new User("Yusuf"), "grill"));
//        eventMap.put("Event4", new Event(new User("Catalin"), "doing nothing together"));

        List<Event> events = f.getEvents(n);
        Random r = new Random();

        for(Event e : events) {
            f.addUsersToEvent(e, r.nextInt(c));
            eventMap.put(e.getDescription(), e);
        }

        reference.setValue(eventMap);
        Log.w("db-write", "maybe");
    }

}
