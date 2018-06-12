package com.mad.modernnostalgiav2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

/**
 * Activity to access camera and display a captured image on screen
 */
public class CameraActivity extends DrawerActivity {
    private ImageView mImageView;
    public static final String TAG = "MESSAGE";

    /**
     * Called when activity is created
     * @param savedInstanceState the activity state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_capture_layout);
    }

    private static final int REQUEST_CAPTURE_IMAGE = 100;

    /**
     * onClick method to access the default camera via an Intent
     * @param view
     */
    public void openCamera(View view) {
        Intent pictureIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        if(pictureIntent.resolveActivity(getPackageManager()) != null) {
            Log.d(TAG, "openCamera: ");
            startActivityForResult(pictureIntent, REQUEST_CAPTURE_IMAGE);
        }
    }

    /**
     * Check captured image and display in the ImageView placeholder on the screen
     * Future implementation: Store the image and add to gallery
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mImageView = findViewById(R.id.imageView);

        if (requestCode == REQUEST_CAPTURE_IMAGE &&
                resultCode == RESULT_OK) {
            if (data != null && data.getExtras() != null) {
                Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
                mImageView.setImageBitmap(imageBitmap);
            }
        }
    }
}
