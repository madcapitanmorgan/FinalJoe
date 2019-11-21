package com.example.finaljoe;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

public class DiapositivaRepository {
    private DiapositivaDAO diapositivaDAO;
    private LiveData<List<Diapositiva>> allDiapositivas; //quitar

    public  DiapositivaRepository(Application application){
        DiapositivaDatabase database = DiapositivaDatabase.getInstance(application);
        diapositivaDAO = database.diapositivaDAO();
        //allDiapositivas = diapositivaDAO.getAll();
    }
}
