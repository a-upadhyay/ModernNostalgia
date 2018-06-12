package com.mad.modernnostalgiav2;


import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.support.design.widget.NavigationView;

import com.mad.modernnostalgiav2.MainActivity;
import com.mad.modernnostalgiav2.R;

/** This is the base class for the navigation drawer menu. All activities extend this class. */
public abstract class DrawerActivity extends AppCompatActivity implements MenuItem.OnMenuItemClickListener{
    Intent intent;

    private FrameLayout view_stub; //This is the framelayout to keep your content view
    private NavigationView navigation_view; // The new navigation view from Android Design Library. Can inflate menu resources. Easy
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private Menu drawerMenu;


    /**
     * This method is called when the activity is created
     * @param savedInstanceState the activity state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.drawer_layout_base);// The base layout that contains your navigation drawer.
        view_stub = findViewById(R.id.view_stub);
        navigation_view = findViewById(R.id.navigation_view);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, 0, 0);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerMenu = navigation_view.getMenu();
        for(int i = 0; i < drawerMenu.size(); i++) {
            drawerMenu.getItem(i).setOnMenuItemClickListener(this);
        }
    }

    /**
     * Called to sync drawer. @param savedInstanceState the activity state
     */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    /**
     * If configuration changes, call @param newConfig
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    /**
     * Override methods for setContentView to make other activity implementations, three below @param layoutResID
     */

    @Override
    public void setContentView(int layoutResID) {
        if (view_stub != null) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            View stubView = inflater.inflate(layoutResID, view_stub, false);
            view_stub.addView(stubView, lp);
        }
    }

    @Override
    public void setContentView(View view) {
        if (view_stub != null) {
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            view_stub.addView(view, lp);
        }
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        if (view_stub != null) {
            view_stub.addView(view, params);
        }
    }

    /**
     * Handle selection of drawer menu items
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Method for dealing with menu navigation
     */
    @Override
    public boolean onMenuItemClick(MenuItem item) {
       switch (item.getItemId()) {
           case R.id.nav_main:
               intent = new Intent(DrawerActivity.this, MainActivity.class);
               startActivity(intent);
               break;
            case R.id.nav_album:
                intent = new Intent(DrawerActivity.this, AlbumActivity.class);
                 startActivity(intent);
                break;
           case R.id.nav_captures:
               intent = new Intent(DrawerActivity.this, CameraActivity.class);
               startActivity(intent);
               break;
           case R.id.nav_gallery:
               intent = new Intent(DrawerActivity.this, PhotoActivity.class);
               startActivity(intent);
               break;
           case R.id.settings:
               intent = new Intent(DrawerActivity.this, SettingsActivity.class);
               startActivity(intent);
               break;
           case R.id.exit:
               finish();
               break;
        }
        return true;
    }

}


