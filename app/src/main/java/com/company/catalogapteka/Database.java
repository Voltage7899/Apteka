package com.company.catalogapteka;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {Product.class},version = 1)
public abstract class Database extends RoomDatabase {

    public abstract ProductDAO userDAO();

    private static  Database INSTANCE;

    public  static  Database getInstance(Context context){

        if(INSTANCE==null){
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(),Database.class,"UserDB").allowMainThreadQueries().build();

        }

        return INSTANCE;
    }
}
