package com.company.catalogapteka.ModelM.Model;

import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Product {




    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name="name")
    public String name;
    @ColumnInfo(name = "description")
    public String description;
    @ColumnInfo(name = "price")
    public String price;
    @ColumnInfo(name="image")
    public String image;
    @ColumnInfo(name="address")
    public String address;



}
