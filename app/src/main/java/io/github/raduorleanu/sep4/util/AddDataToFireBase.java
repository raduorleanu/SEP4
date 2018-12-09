package io.github.raduorleanu.sep4.util;

import android.content.Context;
import android.util.Log;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import io.github.raduorleanu.sep4.models.Event;
import io.github.raduorleanu.sep4.models.User;

public class AddDataToFireBase {

    private FirebaseDatabase database = FirebaseProvider.getDb();
    private int n;
    private int c;
    private FakeUser f;

    public AddDataToFireBase(int numberOfEvents, int ceilNumberOfUsersAndCommentsPerEvent, Context context) {
        n = numberOfEvents;
        c = ceilNumberOfUsersAndCommentsPerEvent;
        f = new FakeUser(context);
        addEvents();
    }

    private void addEvents() {
        Map<String, Event> eventMap = new HashMap<>();

        List<Event> events = f.getEvents(n);

        for (Event e : events) {
            eventMap.put(e.get_id(), e);
        }
        database.getReference("events").setValue(eventMap);
        Log.w("db-write", "maybe");

        addRandomAttendees(events);
    }

    private void addRandomComments(Map<String, List<User>> events) {

        Map<String, HashMap<String, String>> m = new HashMap<>();

//        Random r = new Random();

        for (Map.Entry<String, List<User>> entry : events.entrySet()) {
            HashMap<String, String> hm = new HashMap<>();
            for(User user : entry.getValue()) {
//                HashMap<String, List<String>> comments = new HashMap<>();
                hm.put(user.get_id(), f.getSomeComments(1).get(0));
            }
            m.put(entry.getKey(), hm);
//            m.put(e.get_id(), f.getSomeComments(r.nextInt(c)));
        }
        database.getReference("comments").setValue(m);
    }

    private void addRandomAttendees(List<Event> events) {
        Map<String, List<User>> m = new HashMap<>();
        Random r = new Random();
        for (Event e : events) {
            m.put(e.get_id(), f.getUsers(r.nextInt(c)));
        }
        database.getReference("attendees").setValue(m);
        addRandomComments(m);
        addMyFriends(m);
    }

    private void addMyFriends(Map<String, List<User>> all) {
        Map<String, List<User>> friends = new HashMap<>();
        List<User> u = new ArrayList<>();
        for (Map.Entry<String, List<User>> entry : all.entrySet()) {
            for(User user: entry.getValue()) {
                if(!u.contains(user)) {
                    u.add(user);
                }
            }
        }
        friends.put(Constants.currentUser.get_id(), u);
        database.getReference("friends").setValue(friends);
        addAllUsers(u);
    }

    private void addAllUsers(List<User> list) {
        list.addAll(f.getUsers(40));
        database.getReference("users").setValue(list);
    }

}
