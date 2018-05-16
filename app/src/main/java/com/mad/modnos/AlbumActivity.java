package com.mad.modnos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class AlbumActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.album_home);
    }


    public void openPhoto(View view) {
        Intent intent = new Intent(AlbumActivity.this, PhotoActivity.class);
        startActivity(intent);
    }
}
