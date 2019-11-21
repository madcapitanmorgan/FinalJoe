package com.example.finaljoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import static com.example.finaljoe.yolActivity.path;

public class StartPresentationActivity extends AppCompatActivity
{


    private static DiapositivaRepository repository;
    public static int currentDiapositiva = 1;
    static final Integer READ_EXST = 0x4;
    //TextView scriptview;
    ImageView image_prest;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_presentation);

        askForPermission(Manifest.permission.READ_EXTERNAL_STORAGE,READ_EXST);

        setDiapositiva();

    }

    private void askForPermission(String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {

                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);

            } else {

                ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
            }
        } else {
            Toast.makeText(this, "" + permission + " is already granted.", Toast.LENGTH_SHORT).show();
        }
    }

    public void setDiapositiva()
    {

        image_prest = (ImageView) findViewById(R.id.image_prest);

        Log.d("image path",path);

        repository = new DiapositivaRepository(this.getApplication());
        Diapositiva diapositiva = new Diapositiva("","",0);
        diapositiva.setDiapositivaId(currentDiapositiva);
        diapositiva = repository.search(diapositiva);

        Log.d("image DB",diapositiva.diapositivaImage);
        //startRoot.setBackground(Drawable.createFromPath(diapositiva.diapositivaImage));
        Picasso.get()
                .load(Uri.parse(diapositiva.diapositivaImage))
                .into(image_prest);

        //scriptview = findViewById(R.id.tex_script);
        //scriptview.setText(diapositiva.diapositivaScript);
        Toast.makeText(this, "Title: " + diapositiva.diapositivaScript + ".", Toast.LENGTH_SHORT).show();


    }
}
