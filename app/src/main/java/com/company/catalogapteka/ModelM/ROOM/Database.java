package com.company.catalogapteka.ModelM.ROOM;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.company.catalogapteka.DAO.ProductDAO;
import com.company.catalogapteka.ModelM.Model.Product;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@androidx.room.Database(entities = {Product.class},version = 3)
public abstract class Database extends RoomDatabase {

    public abstract ProductDAO productDAO();

    private static  Database INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
     static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public  static  Database getInstance(Context context){

        if(INSTANCE==null){
            INSTANCE= Room
                    .databaseBuilder(context.getApplicationContext(),Database.class,"UserDB")
                    .fallbackToDestructiveMigration().build();

        }

        return INSTANCE;
    }
}
