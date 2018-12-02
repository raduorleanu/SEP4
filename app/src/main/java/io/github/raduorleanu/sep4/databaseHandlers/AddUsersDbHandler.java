package io.github.raduorleanu.sep4.databaseHandlers;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;

import io.github.raduorleanu.sep4.models.User;
import io.github.raduorleanu.sep4.util.FirebaseProvider;

public class AddUsersDbHandler {

    public static void writeUsers(List<User> going, String eventId) {
        FirebaseDatabase db = FirebaseProvider.getDb();
        DatabaseReference dbReference = db.getReference("attendees");
        HashMap<String, Object> map = new HashMap<>();
        map.put(eventId, going);
        dbReference.updateChildren(map);
    }

}
