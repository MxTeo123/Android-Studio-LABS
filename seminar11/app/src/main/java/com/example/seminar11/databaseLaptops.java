package com.example.seminar11;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {laptop.class}, version = 1)
public abstract class databaseLaptops extends RoomDatabase {
    public abstract LaptopDao laptopDao();
}
