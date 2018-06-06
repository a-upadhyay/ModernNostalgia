package com.mad.modernnostalgiav2;

import android.os.Bundle;
import android.view.View;

/**
 * New Activity: Registration. This is for the user to register an account
 */
public class RegistrationActivity extends DrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_layout);
    }

    public void cancelReg(View view) {
    }

    public void signUp(View view) {
    }
}
