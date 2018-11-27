package io.github.raduorleanu.sep4.util;

import com.google.firebase.database.FirebaseDatabase;

public class FirebaseProvider {

    private static FirebaseDatabase db;

    private FirebaseProvider() {}

    public static FirebaseDatabase getDb() {
        if(db == null) {
            db = FirebaseDatabase.getInstance();
            return db;
        }
        return db;
    }

}
