package io.github.raduorleanu.sep4.databaseHandlers;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import io.github.raduorleanu.sep4.models.Event;
import io.github.raduorleanu.sep4.util.Constants;
import io.github.raduorleanu.sep4.util.FirebaseProvider;

public class AddCommentDbHandler {
    private FirebaseDatabase db;

    public AddCommentDbHandler() {
        db = FirebaseProvider.getDb();
    }

    public void addComment(String comment) {
        DatabaseReference dbReference = db.getReference("comments");
        HashMap<String, Object> map = new HashMap<>();
        // need to find event_id and then insert the comment
        map.put(Constants.currentUser.get_id(), comment);
        dbReference.updateChildren(map);
    }
}
