package io.github.raduorleanu.sep4.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.github.raduorleanu.sep4.R;
import io.github.raduorleanu.sep4.MainActivity;
import io.github.raduorleanu.sep4.util.AuthHandler;


public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private static final String TAG = "LoginActivity";

    private EditText username, password;
    private Button signIn, signUp, signOut;
    private AuthHandler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_view);
        mAuth = FirebaseAuth.getInstance();

        username = findViewById(R.id.uNameView);
        password = findViewById(R.id.passView);
        signIn = findViewById(R.id.signInBtn);
        signUp = findViewById(R.id.signUpBtn);
        signOut = findViewById(R.id.signOutBtn);

        handler = new AuthHandler();

//        if (isLoggedIn())
        goToMain();

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uName = username.getText().toString().trim();
                String cpass = password.getText().toString().trim();

//                if (isLoggedIn()) goToMain();

               if (isAUser(uName,cpass)) {
                   goToMain();
               }else {
                   toastMessage("It's not working!!!");
               }
//               else toastMessage("You didn't fill in all the fields.");

//               handler.openMyProfile(mAuth.getCurrentUser());
//                if (!uName.equals("") && !cpass.equals("")) {
//                    signIn(uName, cpass);
//                } else {
//                    toastMessage("You didn't fill in all the fields.");
//                }
            }

        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toSignUp = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(toSignUp);
            }
        });

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOut();
                toastMessage("Signed out");
            }
        });
    }


    public boolean isAUser(String username, String pass){
        return handler.checkUser(username, pass);
    }

    public void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            assert user != null;
                            toastMessage(user.getDisplayName() + " is signed in");
                            clearFields();
                            goToMain();
                            finish();
                        } else {
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            toastMessage("Authentication failed.");
                        }


                    }
                });
    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void clearFields() {
        username.setText("");
        password.setText("");
    }

    private boolean isLoggedIn() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        return currentUser != null;
    }

    private void goToMain() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void logOut() {
        mAuth.signOut();

    }
}

