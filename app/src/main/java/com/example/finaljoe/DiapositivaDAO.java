package com.example.finaljoe;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface DiapositivaDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long[] insert(Diapositiva... diapositivas);

    @Update
    public void update(Diapositiva... diapositivas);

    @Delete
    public void delete(Diapositiva... diapositivas);



    @Query("SELECT * FROM diapositiva")
    public List<Diapositiva> getAll();

    @Query("SELECT * FROM diapositiva WHERE diapositiva_id LIKE :searchId")
    //public List<Diapositiva> search(int searchId);
    public Diapositiva search(int searchId);
}
