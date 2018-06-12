package com.mad.modernnostalgiav2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

/**
 * User can access their albums from this window
 */
public class AlbumActivity extends DrawerActivity {

    /**
     * Method called when activity created
     * @param savedInstanceState the activity state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.album_layout);
    }

    /**
     * onClick method to open the album's gallery
     * @param view of type View
     */
    public void startGallery(View view) {
        Intent intent = new Intent(AlbumActivity.this, PhotoActivity.class);
        startActivity(intent);
        //Toast to show what Album has been opened
        Toast.makeText(getApplicationContext(), "Chico Album", Toast.LENGTH_SHORT).show();
    }

    /**
     * popup menu for album properties. To be implemented in future.
     */
    public void addPopup(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.album_menu, popup.getMenu());
        popup.show();
    }
}
