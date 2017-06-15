package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.fileprovider.FileProviderUtils;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private TextView imageUriTextView;
    private ImageView imageView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        imageUriTextView = (TextView) findViewById(R.id.imageUriTextView);
        imageView = (ImageView) findViewById(R.id.imageView);

        findViewById(R.id.cameraButton).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View view) {
                final Intent takePictureIntent = FileProviderUtils.createCameraIntent(getApplicationContext());
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });

        findViewById(R.id.galleryButton).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View view) {
                final Intent takePictureIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            final Uri uri = data != null ? data.getData()
                    : FileProviderUtils.getCameraTempFileProviderUri(getApplicationContext());

            imageUriTextView.setText(String.format(Locale.ENGLISH, "Uri: %s", String.valueOf(uri)));
            Glide.with(this)
                    .load(uri)
                    .fitCenter()
                    .placeholder(R.drawable.ic_android_24dp)
                    .into(imageView);
        }
    }
}
