package com.company.catalogapteka.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.company.catalogapteka.ModelM.Model.Product;
import com.company.catalogapteka.ModelM.Repository.Repository;

import java.util.List;

public class ListViewModel extends ViewModel {



    public LiveData<List<Product>> getListFromViewModel(){

        //Log.d(TAG,"Переменная живойдаты in ListViewModel "+Repository.getDataBase().userDAO().getAllUser().getValue());


        return Repository.getDataBase().getListProduct();

    }
    public void deleteParty(Product product) {

        Repository.getDataBase().deleteProduct(product);
    }
}
