package com.company.catalogapteka;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class ListViewModel extends ViewModel {



    public LiveData<List<Product>> getListFromViewModel(){

        //Log.d(TAG,"Переменная живойдаты in ListViewModel "+Repository.getDataBase().userDAO().getAllUser().getValue());


        return Repository.getDataBase().userDAO().getAllProduct();

    }
    public void deleteParty(Product product) {

        Repository.getDataBase().userDAO().DeleteProduct(product);
    }
}
