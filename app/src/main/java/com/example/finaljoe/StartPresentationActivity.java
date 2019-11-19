package com.example.finaljoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class StartPresentationActivity extends AppCompatActivity {
    private static DiapositivaRepository repository;
    TextView scriptview;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_presentation);

        repository = new DiapositivaRepository(this.getApplication());
        Diapositiva diapositiva = new Diapositiva("","",0);
        diapositiva.setDiapositivaId(1);
        diapositiva = repository.search(diapositiva);
        scriptview = findViewById(R.id.tex_script);
        scriptview.setText(diapositiva.diapositivaScript);
    }
}
