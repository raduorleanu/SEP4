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

import io.github.raduorleanu.sep4.models.Comment;
import io.github.raduorleanu.sep4.repositories.CommentRepository;
import io.github.raduorleanu.sep4.util.FirebaseProvider;

public class CommentDbHandler {

    private CommentRepository repository;
    private FirebaseDatabase db;
    private static DatabaseReference dbReference;

    public CommentDbHandler(CommentRepository commentRepository, String databasePath, String eventId) {
        this.repository = commentRepository;
        db = FirebaseProvider.getDb();
        dbReference = db.getReference(databasePath + "/" + eventId);
    addListeners();
}

    public void addListeners() {
        dbReference.addListenerForSingleValueEvent(new ReadOnceEvent());
        dbReference.addValueEventListener(new ChangeEvent());
    }

    private class ReadOnceEvent implements ValueEventListener {

        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            // getValue() returns the data contained in this snapshot as native types
            if (dataSnapshot.exists()) {
//                List<Comment> arr = new ArrayList<>();
                List<String> arr = new ArrayList<>();
                for (DataSnapshot s : dataSnapshot.getChildren()) {
//                    Comment value = s.getValue(Comment.class);
                    String value = s.getValue(String.class);
                    if (value != null) {
                        Log.w("ReadOnce", value.toString());
                        arr.add(value);
                    } else {
                        Log.w("data-snap", "value was null");
                    }
                }
                // calls UpdateData on CommentListRepository to sync with Firebase
                repository.updateData(arr);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Log.w("Db cancelled", databaseError.getMessage());
        }
    }

    private class ChangeEvent implements ValueEventListener {

        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//            List<Comment> tempComments = new ArrayList<>();
            List<String> tempComments = new ArrayList<>();
            for (DataSnapshot data : dataSnapshot.getChildren()) {
//                Comment comment = data.getValue(Comment.class);
                String comment = data.getValue(String.class);
                tempComments.add(comment);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    }
}
