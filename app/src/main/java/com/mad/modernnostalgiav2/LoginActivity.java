package com.mad.modernnostalgiav2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
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
import com.google.firebase.auth.FirebaseUser;

/**
 * Activity for user authentication before accessing their content
 */
public class LoginActivity extends DrawerActivity{
    private static final String TAG = "MESSAGE" ;
    private EditText mEmail, mPassword;
    private ProgressBar mProgressBar;
    private FirebaseAuth mAuth;


    /**
     * Method called when activity is created
     *  @param savedInstanceState the activity state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        mEmail = findViewById(R.id.et_login_user);
        mPassword = findViewById(R.id.et_login_password);
        mProgressBar = findViewById(R.id.reg_progress);

        mAuth = FirebaseAuth.getInstance();
    }

    /**
     * This method checks if user is signed in
     */
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //TODO updateUI accordingly
    }

    /**
     * Method to login user using Firebase Authentication
     * With several checks for email and password verification.
     * Note: All Strings used once are implemented within respective functions
     */
    private void loginUser() {
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

        mProgressBar.setVisibility(View.VISIBLE); //show progress bar once login verification begins

        /* Check success of verification. */
        mAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                mProgressBar.setVisibility(View.GONE); //hide progress bar once verification complete

                //if successful, the user will be taken to the album screen
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success");
                    Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, AlbumActivity.class);
                    startActivity(intent);

                    FirebaseUser user = mAuth.getCurrentUser();
                }
                //  If there is an error while signing in, the error message will be displayed on screen
                else {
                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    /**
     * method to obtain current user information. To be implemented in future.
     */
    private void getCurrentUser() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            String uid = user.getUid();
        }
    }

    /**
     * onClick method to open album after login successful
     * @param view
     */
    public void login(View view) {
       try { loginUser(); }
       catch(IllegalStateException e) {
           startActivity(new Intent(LoginActivity.this, MainActivity.class));
       }
    }

    /**
     * onClick method to open registration activity if user does not have an account @param view
     */
    public void registration(View view) {
        startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
    }
}
