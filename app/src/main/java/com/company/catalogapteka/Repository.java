package com.company.catalogapteka;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

public class Repository {
    static  Database database;
    static Application app;

    static public void initDataBase(Application application){
        app=application;
        if(database==null){
            Log.d(TAG,"Переменная database "+Database.getInstance(application));
            database=Database.getInstance(application);


        }
    }
    static  public Database getDataBase(){
        if(database==null){
            Toast.makeText(app,"Oh,Shiiiit",Toast.LENGTH_LONG).show();
        }
        return database;
    }
}
