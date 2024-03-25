package com.example.seminar9;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ParcDAO {

    @Insert
    void insertParc(Parc parc);

    @Update
    void updateParc(Parc parc);

    @Delete
    void deleteParc(Parc parc);

    @Query("select * from parcuri")
    List<Parc> selectAllParks();


    @Query("select * from parcuri where id=:id")
    Parc getParcById(int id);
}
