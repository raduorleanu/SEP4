package io.github.raduorleanu.sep4.util;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.Executor;

import io.github.raduorleanu.sep4.activities.LoginActivity;
import io.github.raduorleanu.sep4.models.User;

public class AuthHandler {

    private static final String TAG = "AuthHandler";
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private FirebaseDatabase database;
    private DatabaseReference userRef;

    public AuthHandler() {
        // Write a message to the database
        database = FirebaseDatabase.getInstance();
        userRef = database.getReference("Users");

    }

    public void createUser(User newUser) {
        userRef.child(newUser.getUsername()).setValue(newUser);
    }

    public boolean checkUser(final String username, String pass) {
        final boolean[] exists = {false};
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dbName: dataSnapshot.getChildren()) {
                    if (dbName.getKey().equals(username)) exists[0] = true;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return exists[0];
    }

    public boolean checkNewUName(final String newUname) {
        final boolean[] exists = {false};
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dbName: dataSnapshot.getChildren()) {
                    if (dbName.getKey().equals(newUname)) exists[0] = true;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return exists[0];
    }


    public void openMyProfile(FirebaseUser currentUser) {
//        toDo - Either implement with Uid or find iteration for children
// boolean[] exists;
//        String userMail = currentUser.getEmail();
//        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                for (DataSnapshot dbName: dataSnapshot.getChildren()) {
//                    if (dbName.getChildren()) exists[0] = true;
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//        Intent intent = new Intent(LoginActivity.class, ProfileActivity.class);
//        intent.putExtra("Deets", )
    }
}
