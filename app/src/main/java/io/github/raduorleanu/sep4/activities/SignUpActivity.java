package io.github.raduorleanu.sep4.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

import io.github.raduorleanu.sep4.MainActivity;
import io.github.raduorleanu.sep4.R;
import io.github.raduorleanu.sep4.models.User;
import io.github.raduorleanu.sep4.util.AuthHandler;


public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = "SignUpActivity";

    private FirebaseAuth mAuth;

    //fields
    private EditText  nameEdit, unameEdit, addrEdit, emailEdit, passEdit;
    private Button signUpButton;
    private AuthHandler handler;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_activity);

        mAuth = FirebaseAuth.getInstance();
        handler = new AuthHandler();

        nameEdit = findViewById(R.id.sign_up_name);
        unameEdit = findViewById(R.id.sign_up_uName);
        addrEdit = findViewById(R.id.sign_up_addr);
        emailEdit = findViewById(R.id.sign_up_email);
        passEdit = findViewById(R.id.sign_up_password);
        signUpButton = findViewById(R.id.sign_up_button);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(!handler.checkNewUName(unameEdit.getText().toString())) {toastMessage("Username is taken"); return;}
                createAccount(emailEdit.getText().toString(), passEdit.getText().toString());
                signIn(emailEdit.getText().toString(), passEdit.getText().toString());
            }
        });
    }

    public void createAccount(final String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithUsername:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithUsername:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            toastMessage("Sorry, couldn't sign ya up");
                        }
                    }
                });
    }

    public void signIn(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            addUserToDb();
                            goToMain();
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void addUserToDb(){
        User newUser = new User(unameEdit.getText().toString(),nameEdit.getText().toString(), addrEdit.getText().toString(),emailEdit.getText().toString(),passEdit.getText().toString() ,"");
        handler.createUser(newUser);
    }

    private void goToMain() {
        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
