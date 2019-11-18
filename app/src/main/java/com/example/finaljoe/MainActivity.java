package com.example.finaljoe;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

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

public class MainActivity extends AppCompatActivity {

    public static final String MESSAGE_STATUS = "message_status";
    public static Bitmap largeIcon = null;
    private static final int PICK_IMAGE = 100;
    public static ImageView ivIlls;
    public static Uri imageUri;

    TextView tvStatus;
    Button btnSend;
    WorkManager mWorkManager;
    OneTimeWorkRequest mRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.worker_done);

        tvStatus = findViewById(R.id.tvStatus);
        btnSend = findViewById(R.id.btnSend);
        ivIlls = findViewById(R.id.ivIlls);

        mWorkManager = WorkManager.getInstance();
        mRequest = new OneTimeWorkRequest.Builder(NotificationWorker.class).build();

        mWorkManager.getWorkInfoByIdLiveData(mRequest.getId()).observe(this, new Observer<WorkInfo>() {
            @Override
            public void onChanged(@Nullable WorkInfo workInfo) {
                if (workInfo != null) {
                    WorkInfo.State state = workInfo.getState();
                    tvStatus.append("\n " + state.toString());
                    Picasso.get()
                            .load(imageUri)
                            .into(ivIlls);
                }
            }
        });
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
            mWorkManager.enqueue(mRequest);
        }
    }
}
