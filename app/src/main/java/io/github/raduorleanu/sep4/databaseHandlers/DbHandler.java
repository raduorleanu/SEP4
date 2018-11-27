package io.github.raduorleanu.sep4.databaseHandlers;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import io.github.raduorleanu.sep4.interfaces.IDbHandler;
import io.github.raduorleanu.sep4.interfaces.IRepository;
import io.github.raduorleanu.sep4.models.Event;
import io.github.raduorleanu.sep4.util.FirebaseProvider;

/**
 *
 * @param <T> The type of the Mutable Live Data list elements
 */
public class DbHandler<T> implements IDbHandler {

    private IRepository repository;
    private FirebaseDatabase db;
    private static DatabaseReference dbReference;

    private final Class<T> type;

    public DbHandler(Class<T> type, IRepository repository, String databasePath) {
        this.type = type;
        this.repository = repository;
        db = FirebaseProvider.getDb();
        dbReference = db.getReference(databasePath);
        addListeners();
    }

    @Override
    public void addListeners() {
        dbReference.addValueEventListener(new ChangeEvent());
//        dbReference.addListenerForSingleValueEvent(new ChangeEventSingle());
    }

//    private class ChangeEventSingle implements ValueEventListener {
//
//        @Override
//        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//            if(dataSnapshot.exists()) {
//                // toDo: finish this
//                T t = dataSnapshot.getValue(type);
//            }
//        }
//
//        private void extractData(Map<String, Object> data) {
//
//        }
//
//        @Override
//        public void onCancelled(@NonNull DatabaseError databaseError) {
//
//        }
//    }

    private class ChangeEvent implements ValueEventListener {

        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if(dataSnapshot.exists() && dataSnapshot.getValue() != null) {
                logLol((Map<String, Object>) dataSnapshot.getValue());
//                T t = dataSnapshot.getValue(type);
//                Event event = dataSnapshot.getValue(Event.class);
//                if(t != null) {
//                    Log.w("data-snap", t.toString());
//                } else {
//                    Log.w("data-snap", "t was null");
//                }
//
//                if(event != null) {
//                    Log.w("data-snap event", event.toString());
//                } else {
//                    Log.w("data-snap", "event was null");
//                }

                //repository.insertData(t);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }

        private void logLol(Map<String, Object> map) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                Map m = (Map) entry.getValue();
                Log.w("map entry", Objects.requireNonNull(m.get("description")).toString());
            }
        }
    }
}
