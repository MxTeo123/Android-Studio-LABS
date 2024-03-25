package com.example.seminar11;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface LaptopDao {
    @Insert
    void insert(laptop laptop);

    @Query("SELECT * FROM laptop")
    List<laptop> getAllLaptops();
}
