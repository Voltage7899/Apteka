package com.company.catalogapteka.ViewModel;

import androidx.lifecycle.ViewModel;

import com.company.catalogapteka.ModelM.Model.Product;
import com.company.catalogapteka.ModelM.Repository.Repository;

public class AddViewModel extends ViewModel {
    public void AddProductThroughViewModel(String name, String last,String price){

        Product product =new Product();
        product.name=name;
        product.description=last;
        product.price=price;
        Repository.getDataBase().addProduct(product);
    }
}
