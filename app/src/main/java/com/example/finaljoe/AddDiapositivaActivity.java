package com.example.finaljoe;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import static com.example.finaljoe.yolActivity.path;

public class AddDiapositivaActivity extends AppCompatActivity {

    //public static final String EXTRA_SCRIPT = "com.example.FinalJoe.EXTRA_SCRIPT";
    public static int contadorDiapositivas = 1;
    private EditText editTextScript;
    private EditText editTextImage;
    private EditText editTextTime;
    private static DiapositivaRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_diapositiva);

        repository = new DiapositivaRepository(this.getApplication());

        editTextScript = findViewById(R.id.edit_text_script);
        editTextImage= findViewById(R.id.edit_text_imageName);
        editTextTime = findViewById(R.id.edit_text_time);

        Diapositiva diapositiva = new Diapositiva("","",0);
        diapositiva.setDiapositivaId(contadorDiapositivas++);
        diapositiva = repository.search(diapositiva);
        editTextImage.setText(path);

        Button buttonAdd2 = findViewById(R.id.b_add2);

        buttonAdd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDiapositiva();
                Intent intent = new Intent(AddDiapositivaActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void saveDiapositiva(){
        String script = editTextScript.getText().toString();
        String image =editTextImage.getText().toString();
        int time = Integer.parseInt(editTextTime.getText().toString());
       // Log.d("",script+" "+image+" "+time);

        if(script.trim().isEmpty()||image.trim().isEmpty()||time == 0){
            Toast.makeText(this, "Uncomplete", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            Diapositiva diapositiva = new Diapositiva(script,image,time);
            //Log.d("", diapositiva.toString());
            repository.insert(diapositiva);
        }
        /*
        Intent data = new Intent();
        data.putExtra(EXTRA_SCRIPT, script);
        data.putExtra(EXTRA_IMAGE, image);
        data.putExtra(EXTRA_TIME, time);
        //data.putExtra(EXTRA_IMAGEURL, imageUrl);
        setResult(RESULT_OK,data);
        */

    }


}
