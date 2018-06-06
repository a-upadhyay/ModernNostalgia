package com.mad.modernnostalgiav2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Activity for user authentication before accessing their content
 */
public class LoginActivity extends DrawerActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
    }

    /**
     * method to open album after login successful
     *  @param view
     */
    public void login(View view) {
        Intent intent = new Intent(LoginActivity.this, AlbumActivity.class);
        startActivity(intent);
    }
}
