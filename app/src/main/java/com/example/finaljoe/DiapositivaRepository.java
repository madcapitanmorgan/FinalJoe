package com.example.finaljoe;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class DiapositivaRepository {
    private DiapositivaDAO diapositivaDAO;
    private LiveData<List<Diapositiva>> allDiapositivas;

    public DiapositivaRepository(Application application){
        DiapositivaDatabase database = DiapositivaDatabase.getInstance(application);
        diapositivaDAO = database.diapositivaDAO();
        allDiapositivas = diapositivaDAO.getAll();
    }

    public void inser(Diapositiva diapositiva){
        new InsertDiapositivaAsynkTask(diapositivaDAO).execute(diapositiva);
    }
    public void update(Diapositiva diapositiva)
    {
        new UpdateDiapositivaAsynkTask(diapositivaDAO).execute(diapositiva);
    }
    public void delete(Diapositiva diapositiva)
    {
        new DeleteDiapositivaAsynkTask(diapositivaDAO).execute();
    }

    public LiveData<List<Diapositiva>> getAllDiapositivas()
    {
        return allDiapositivas;
    }

    private static class InsertDiapositivaAsynkTask extends AsyncTask<Diapositiva, Void , Void> {
        private DiapositivaDAO diapositivaDAO;

        private InsertDiapositivaAsynkTask(DiapositivaDAO diapositivaDAO)
        {
            this.diapositivaDAO = diapositivaDAO;
        }

        @Override
        protected Void doInBackground(Diapositiva... diapositivas) {
            diapositivaDAO.insert(diapositivas[0]);
            return null;
        }
    }
    private static class UpdateDiapositivaAsynkTask extends AsyncTask<Diapositiva, Void , Void>{
        private DiapositivaDAO diapositivaDAO;

        private UpdateDiapositivaAsynkTask(DiapositivaDAO diapositivaDAO)
        {
            this.diapositivaDAO = diapositivaDAO;
        }

        @Override
        protected Void doInBackground(Diapositiva... diapositivas) {
            diapositivaDAO.update(diapositivas[0]);
            return null;
        }
    }
    private static class DeleteDiapositivaAsynkTask extends AsyncTask<Diapositiva, Void , Void>{
        private DiapositivaDAO diapositivaDAO;

        private DeleteDiapositivaAsynkTask(DiapositivaDAO diapositivaDAO)
        {
            this.diapositivaDAO = diapositivaDAO;
        }

        @Override
        protected Void doInBackground(Diapositiva... diapositivas) {
            diapositivaDAO.delete(diapositivas[0]);
            return null;
        }
    }
}
