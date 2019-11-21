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

    public void insert(Diapositiva diapositiva){
        Log.e("repository", diapositiva.diapositivaImage+" " + diapositiva.diapositivaTime+ " "+ diapositiva.diapositivaScript);
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
    public Diapositiva search(Diapositiva diapositiva)
    {
        try {
            return new SearchDiapositivaAsynkTask(diapositivaDAO).execute(diapositiva).get();
        }
       catch (Exception e){
            Log.d("Exception pinche richi", "ex> "+e);
            return null;
       }
    }




    private static class SearchDiapositivaAsynkTask extends AsyncTask<Diapositiva, Void , Diapositiva> {
        private DiapositivaDAO diapositivaDAO;

        private SearchDiapositivaAsynkTask(DiapositivaDAO diapositivaDAO)
        {
            this.diapositivaDAO = diapositivaDAO;
        }

        @Override
        protected Diapositiva doInBackground(Diapositiva... diapositivas) {

            //Diapositiva diapositiva= new Diapositiva("","", 0);
            //diapositiva =  diapositivaDAO.search(diapositiva.diapositivaId).get(0);
            Diapositiva diapositiva =  diapositivaDAO.search(diapositivas[0].diapositivaId);
            return diapositiva;
        }

        @Override
        protected void onPostExecute(Diapositiva diapositiva) {
            super.onPostExecute(diapositiva);

        }
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
