package com.mad.modernnostalgiav2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class CameraActivity extends DrawerActivity {
    private ImageView mImageView;
    public static final String TAG = "MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_capture_layout);
    }

    private static final int REQUEST_CAPTURE_IMAGE = 100;
    public void openCamera(View view) {
        Intent pictureIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        if(pictureIntent.resolveActivity(getPackageManager()) != null) {
            Log.d(TAG, "openCamera: ");
            startActivityForResult(pictureIntent, REQUEST_CAPTURE_IMAGE);
        }
    }

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
