package com.example.finaljoe;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Diapositiva.class, version = 1)

public abstract class DiapositivaDatabase extends RoomDatabase {
    private static DiapositivaDatabase instance;

    public abstract DiapositivaDAO diapositivaDAO();

    public static synchronized DiapositivaDatabase getInstance(Context context){
        if (instance==null){
            /*instance = Room.inMemoryDatabaseBuilder(context,
                    DiapositivaDatabase.class, "diapositiva_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();*/
            instance = Room.inMemoryDatabaseBuilder(context,
                    DiapositivaDatabase.class).fallbackToDestructiveMigration().addCallback(roomCallback).build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private DiapositivaDAO diapositivaDAO;

        private PopulateDbAsyncTask(DiapositivaDatabase db){
            diapositivaDAO = db.diapositivaDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //diapositivaDAO.insert(new Diapositiva( "", "",0));
//            diapositivaDAO.insert(new Diapositiva( "mi tema es", "@mipmap/asdf2",4));
//            diapositivaDAO.insert(new Diapositiva( "Jobs, threads y asynkTasks", "@mipmap/asdf3",5));
            return null;
        }
    }
}
