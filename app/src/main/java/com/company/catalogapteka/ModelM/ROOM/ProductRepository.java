package com.company.catalogapteka.ModelM.ROOM;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.company.catalogapteka.DAO.ProductDAO;
import com.company.catalogapteka.ModelM.Model.Product;
import com.company.catalogapteka.ModelM.ROOM.Database;

import java.util.List;

public class ProductRepository {

    private ProductDAO productDAO;

    public ProductRepository(Application application){
        Database database=Database.getInstance(application);
        productDAO=database.productDAO();
    }
    public LiveData<List<Product>> getListProduct(){
       return productDAO.getAllProduct();
    }

    public void addProduct(Product product){
        Database.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                productDAO.insertProduct(product);
            }
        });
    }
    public void deleteProduct(Product product){
        Database.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                productDAO.DeleteProduct(product);
            }
        });
    }
    public void updateProduct(Product product){
        Database.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                productDAO.updateProduct(product);
            }
        });
    }

}
