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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_layout);

        mEmail = findViewById(R.id.et_email);
        mPassword = findViewById(R.id.et_password);
        mProgressBar = findViewById(R.id.reg_progress);

        mAuth = FirebaseAuth.getInstance();

        //mSignUp = findViewById(R.id.button_sign_up);
    }

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

        mProgressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                mProgressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    Log.d(TAG, "createUserWithEmail:success");
                    Toast.makeText(getApplicationContext(),"User registered",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                   // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                   /* if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(getApplicationContext(), "Email already registered", Toast.LENGTH_SHORT).show(); } */
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void registerUser(View view) {
       try {
           registerUser();
       }
       catch (IllegalStateException e) {
           startActivity(new Intent(getApplicationContext(), MainActivity.class));
       }
    }

    public void loginReturn(View view) {
        Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
        startActivity(intent);
    }

   /* @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_sign_up:

                break;

            case R.id.button_cancel:
                startActivity(new Intent(this, LoginActivity.class));
                break;

        }
    } */
}
