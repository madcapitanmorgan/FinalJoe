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


    public Diapositiva(String diapositivaScript, String diapositivaImage) {
        this.diapositivaScript = diapositivaScript;
        this.diapositivaImage = diapositivaImage;
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
}
