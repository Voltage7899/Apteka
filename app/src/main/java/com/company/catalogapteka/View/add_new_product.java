package com.company.catalogapteka.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.company.catalogapteka.ViewModel.AddViewModel;
import com.company.catalogapteka.databinding.ActivityAddNewProductBinding;

public class add_new_product extends AppCompatActivity {

    private AddViewModel addViewModel;
    private ActivityAddNewProductBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_new_user);

        binding = ActivityAddNewProductBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);
        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNewUser(binding.name.getText().toString(),binding.description.getText().toString(),binding.price.getText().toString());
            }
        });
        addViewModel=new ViewModelProvider(this).get(AddViewModel.class);

    }

    private void saveNewUser(String name, String description,String price){


        addViewModel.AddProductThroughViewModel(name,description,price);

        finish();

    }
}