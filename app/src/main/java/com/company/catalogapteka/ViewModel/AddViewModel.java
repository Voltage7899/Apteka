package com.company.catalogapteka.ViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.company.catalogapteka.APILogic;
import com.company.catalogapteka.ModelM.Model.Product;
import com.company.catalogapteka.ModelM.Repository.Repository;

import java.util.List;

import static android.content.ContentValues.TAG;

public class AddViewModel extends ViewModel {
    public void AddProductThroughViewModel(String name, String last,String price){

        Product product =new Product();
        product.name=name;
        product.description=last;
        product.price=price;
        Repository.getDataBase().addProduct(product);
    }
    public  void updateProductThroughViewModel(int id,String name, String last,String price,String image){
        Product product =new Product();
        product.image=image;
        product.id=id;
        product.name=name;
        product.description=last;
        product.price=price;
        Repository.getDataBase().updateProduct(product);
    }
//    public LiveData<APILogic.geoDataResponse.geoData> getGeoDataThroughViewModel(String adress) {
//
//        return Repository.getAPILOGIC().getGeoData(adress);
//    }
}
