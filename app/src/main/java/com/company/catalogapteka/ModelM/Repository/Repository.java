package com.company.catalogapteka.ModelM.Repository;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.company.catalogapteka.ModelM.Model.Product;
import com.company.catalogapteka.ModelM.ROOM.Database;
import com.company.catalogapteka.ModelM.ROOM.ProductRepository;

import static android.content.ContentValues.TAG;

public class Repository {
    static ProductRepository database;
    static Application app;

    static public void initDataBase(Application application){
        app=application;
        if(database==null){
            Log.d(TAG,"Переменная database "+Database.getInstance(application));
            database=new ProductRepository(application);


        }
    }
    static  public ProductRepository getDataBase(){
        if(database==null){
            Toast.makeText(app,"Oh,Shiiiit",Toast.LENGTH_LONG).show();
        }
        return database;
    }
}
