package io.github.raduorleanu.sep4.databaseHandlers;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
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
    }

    private class ChangeEvent implements ValueEventListener {

        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if(dataSnapshot.exists() && dataSnapshot.getValue() != null) {
                List<T> l = new ArrayList<>();

                for(DataSnapshot s : dataSnapshot.getChildren()) {
                    T value = s.getValue(type);

                    if(value != null) {
                        //repository.insertData(value);
                        l.add(value);
                    } else {
                        Log.w("data-snap", "value was null");
                    }
                }
                repository.updateData(l);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    }
}
