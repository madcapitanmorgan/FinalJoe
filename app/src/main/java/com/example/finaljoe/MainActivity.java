package com.example.finaljoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("Helloworld","Holi");

    }

    public void Start(View view) {
        Intent intent = new Intent(MainActivity.this, StartPresentationActivity.class);
        startActivity(intent);
    }

    public void Add(View view) {
        Intent intent = new Intent(MainActivity.this,yolActivity.class);
        startActivity(intent);
        /*Intent intent = new Intent(this,yolActivity.class);
        startActivity(intent);*/
    }
}
