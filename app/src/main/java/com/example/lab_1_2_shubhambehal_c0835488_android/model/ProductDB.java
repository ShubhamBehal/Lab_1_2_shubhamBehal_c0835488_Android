package com.example.lab_1_2_shubhambehal_c0835488_android.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ProductInfo.class},
        version = 1)
public abstract class ProductDB extends RoomDatabase {
    private static volatile ProductDB instance;

    public static synchronized ProductDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ProductDB.class, "product_db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract ProductDao productDao();
}