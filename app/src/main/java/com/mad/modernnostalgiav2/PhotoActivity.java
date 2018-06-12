package com.mad.modernnostalgiav2;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class PhotoActivity extends DrawerActivity {
    private ArrayList<Image> imageList;
    private Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_layout);

        imageList = new ArrayList<>();
        imageList.add(new Image(R.drawable.chico1));
        imageList.add(new Image(R.drawable.chico2));
        imageList.add(new Image(R.drawable.chico3));
        imageList.add(new Image(R.drawable.chico4));
        imageList.add(new Image(R.drawable.chico5));
        imageList.add(new Image(R.drawable.chico6));
        imageList.add(new Image(R.drawable.chico7));
        imageList.add(new Image(R.drawable.chico8));
        imageList.add(new Image(R.drawable.chico9));
        imageList.add(new Image(R.drawable.chico10));
        imageList.add(new Image(R.drawable.chico11));
        imageList.add(new Image(R.drawable.chico12));
        imageList.add(new Image(R.drawable.chico13));
        imageList.add(new Image(R.drawable.chico14));
        imageList.add(new Image(R.drawable.chico15));
        imageList.add(new Image(R.drawable.chico16));
        imageList.add(new Image(R.drawable.chico17));
        imageList.add(new Image(R.drawable.chico18));
        imageList.add(new Image(R.drawable.chico19));
        imageList.add(new Image(R.drawable.chico20));
        imageList.add(new Image(R.drawable.chico21));
        imageList.add(new Image(R.drawable.chico22));
        imageList.add(new Image(R.drawable.chico23));
        imageList.add(new Image(R.drawable.chico24));

        RecyclerView recyclerView = findViewById(R.id.rv_layout);
      //  recyclerView.setHasFixedSize(false);
        GridLayoutManager gridLayout = new GridLayoutManager(getApplicationContext(), 3);
        recyclerView.setLayoutManager(gridLayout);
      //  recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new Adapter(getApplicationContext(), imageList);
        recyclerView.setAdapter(mAdapter);
    }
}
