package com.mad.modernnostalgiav2;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

/**
 * New Activity: Registration. This is for the user to register an account
 */
public class RegistrationActivity extends DrawerActivity {
    private static final String TAG = "MESSAGE" ;
    private EditText mEmail, mPassword;
    private ProgressBar mProgressBar;
    private FirebaseAuth mAuth;

    /**
     * Method called when activity created
     * @param savedInstanceState the activity state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_layout);

        mEmail = findViewById(R.id.et_email);
        mPassword = findViewById(R.id.et_password);
        mProgressBar = findViewById(R.id.reg_progress);

        mAuth = FirebaseAuth.getInstance();

    }

    /**
     * Method to register new user using Firebase Authentication
     * With several checks for email and password verification.
     * Note: All Strings used once are implemented within respective functions
     */
    private void registerUser() {
        String email = mEmail.getText().toString().trim();
        String pwd = mPassword.getText().toString().trim();

        if (email.isEmpty()) {
            mEmail.setError("Email required");
            mEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("Please enter a valid email address");
            mEmail.requestFocus();
            return;
        }
        if (pwd.isEmpty()) {
            mPassword.setError("Password required");
            mPassword.requestFocus();
            return;
        }
        if (pwd.length()<6) {
            mPassword.setError("Minimum 6 characters required");
            mPassword.requestFocus();
            return;
        }

        mProgressBar.setVisibility(View.VISIBLE); //show progress bar once registration verification begins

        /* Check success of verification. */
        mAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                mProgressBar.setVisibility(View.GONE); //hide progress bar once verification complete

                //if successful, the user will be taken to login with new account
                if (task.isSuccessful()) {
                    Log.d(TAG, "createUserWithEmail:success");
                    Toast.makeText(getApplicationContext(),"User Registered",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                //  If there is an error while registering, the error message will be displayed on screen
                else {
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    /**
     * onClick to register user. It calls the registerUser function above
     * @param view
     */
    public void registerUser(View view) {
       try {
           registerUser();
       }
       catch (IllegalStateException e) {
           startActivity(new Intent(getApplicationContext(), MainActivity.class));
       }
    }

    /**
     * onClick to return back to login screen if user already has an account
     * @param view
     */
    public void loginReturn(View view) {
        Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
