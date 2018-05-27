package com.mad.modnos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class AlbumActivityOld extends AppCompatActivity {
private RecyclerView mRecyclerView;
private RecyclerView.LayoutManager mLayoutManager;
private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.album_home);

       // mRecyclerView = findViewById(R.id.album_recyler_view);

        //linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //adapter
        //mAdapter = new MyAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);

    }

    /**
     * method to open Photo Activity via button click
     * @param view type of View
     */
    public void openPhoto(View view) {
        Intent intent = new Intent(AlbumActivityOld.this, PhotoActivity.class);
        startActivity(intent);
    }


}
