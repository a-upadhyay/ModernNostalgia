package com.mad.modernnostalgiav2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.PopupMenu;

/**
 * User can access their albums from this window
 */
public class AlbumActivity extends DrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.album_layout);
    }

    /**
     * method to open the album's gallery
     * @param view of type View
     */
    public void startGallery(View view) {
        Intent intent = new Intent(AlbumActivity.this, PhotoActivity.class);
        startActivity(intent);

    }

    /**
     * method to open popup menu
     * @param view of type View
     */
    public void addPopup(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.album_menu, popup.getMenu());
        popup.show();
    }
}
