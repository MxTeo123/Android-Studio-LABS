package com.example.seminar9;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Parc.class}, version = 1)
public abstract class DatabaseParcs extends RoomDatabase {
    public abstract ParcDAO getDao();
}
