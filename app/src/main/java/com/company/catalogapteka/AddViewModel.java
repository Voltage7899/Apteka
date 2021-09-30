package com.company.catalogapteka;

import androidx.lifecycle.ViewModel;

public class AddViewModel extends ViewModel {
    public void AddProductThroughViewModel(String name, String last,String price){

        Product product =new Product();
        product.name=name;
        product.description=last;
        product.price=price;
        Repository.getDataBase().userDAO().insertProduct(product);
    }
}
