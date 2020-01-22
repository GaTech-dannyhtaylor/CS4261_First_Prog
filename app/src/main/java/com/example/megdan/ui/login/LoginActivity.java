package com.example.megdan.ui.login;


import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.EditText;
import android.os.Bundle;
import android.widget.TextView;

import com.example.megdan.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.example.megdan.R.id.login;
import static com.example.megdan.R.id.register;
//import com.google.firebase.quickstart.auth.R

public class LoginActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    public FirebaseUser user;
    private EditText email;
    private EditText password;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        mAuth = FirebaseAuth.getInstance();

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = v.getId();
                if (i == R.id.register) {
                    createAccount(email.getText().toString(), password.getText().toString());
                } else if (i == R.id.login) {
                    signIn(email.getText().toString(), password.getText().toString());
                }
            }
        };

        findViewById(R.id.login).setOnClickListener(listener);
        findViewById(R.id.register).setOnClickListener(listener);
    }

    private void createAccount(String email, String password) {
        Log.d("login","Create account");
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("login", "createUserWithEmail:success");
                            user = mAuth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("login", "createUserWithEmail:failure", task.getException());
                            user = null;
                        }
                    }
                });
    }

    private void signIn(String email, String password) {
        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("login", "signInWithEmail:success");
                            user = mAuth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("login", "signInWithEmail:failure", task.getException());
                            user = null;
                        }
                    }
                });
    }

    private void signOut(){
        mAuth.signOut();
        user = null;
    }



}
