package com.example.finaljoe;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class yolActivity extends AppCompatActivity {

    public static final String MESSAGE_STATUS = "message_status";
    public static Bitmap largeIcon = null;
    private static final int PICK_IMAGE = 100;
    public static ImageView ivIlls;
    public static Uri imageUri;
    private static DiapositivaRepository repository;
    public static String path;

    TextView tvStatus;
    Button btnSend, btnOk;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yol);

        repository = new DiapositivaRepository(this.getApplication());
        largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.worker_done);

        tvStatus = findViewById(R.id.tvStatus);
        btnSend = findViewById(R.id.btnSend);
        btnOk = findViewById(R.id.btnOk);
        ivIlls = findViewById(R.id.ivIlls);

    }

    public void DownloadImage(View view) {
        openGallery();
    }

    private void openGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            path = imageUri.toString();
            System.out.println("Path> "+path);
        }
    }

    public void GotoPrevScreen(View view) {
        Intent intent = new Intent(this,AddDiapositivaActivity.class);
        startActivity(intent);
        this.finish();

    }
}
