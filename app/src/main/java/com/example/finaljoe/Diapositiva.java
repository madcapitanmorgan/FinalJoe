package com.example.finaljoe;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity

public class Diapositiva {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name="diapositiva_id")
    public int diapositivaId;

    @ColumnInfo(name = "diapositiva_script")
    public String diapositivaScript;

    @ColumnInfo(name = "diapositiva_image")
    public String diapositivaImage;

    @ColumnInfo(name = "diapositiva_time")
    public int diapositivaTime;


    public Diapositiva(String diapositivaScript, String diapositivaImage, int diapositivaTime) {
        this.diapositivaScript = diapositivaScript;
        this.diapositivaImage = diapositivaImage;
        this.diapositivaTime = diapositivaTime;
    }

    public void setDiapositivaId(int diapositivaId) {

        this.diapositivaId = diapositivaId;
    }
    public int getId() {

        return diapositivaId;
    }

    public String getDiapositivaScript() {

        return diapositivaScript;
    }
    public String getDiapositivaImage(){

        return diapositivaImage;
    }
    public int getDiapositivaTime(){
        return diapositivaTime;
    }
}
