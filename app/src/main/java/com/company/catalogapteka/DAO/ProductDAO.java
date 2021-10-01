package com.company.catalogapteka.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.company.catalogapteka.ModelM.Model.Product;

import java.util.List;

@Dao
public interface ProductDAO {

    @Query("SELECT * FROM product")
    LiveData<List<Product>> getAllProduct();

    @Insert
    void insertProduct(Product product);

    @Delete
    void DeleteProduct(Product product);

}
