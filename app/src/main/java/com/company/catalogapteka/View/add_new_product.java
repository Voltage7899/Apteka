package com.company.catalogapteka.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.company.catalogapteka.APILogic;
import com.company.catalogapteka.ItemProductActivity;
import com.company.catalogapteka.ModelM.Repository.Repository;
import com.company.catalogapteka.ViewModel.AddViewModel;
import com.company.catalogapteka.databinding.ActivityAddNewProductBinding;

import java.util.List;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

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
        binding.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            APILogic apiLogic =new APILogic();
            apiLogic.api.listTovars(new APILogic.adressRequest(binding.adress.getText().toString()),"Token 1e8dbca03b87f394ac3b099901ae5ca3f0455298").enqueue(new Callback<APILogic.geoDataResponse>() {
                @Override
                public void onResponse(Call<APILogic.geoDataResponse> call, Response<APILogic.geoDataResponse> response) {

                       Log.d(TAG,"GEO FROM SERVER"+response.body().getSuggestions());


                    
                }

                @Override
                public void onFailure(Call<APILogic.geoDataResponse> call, Throwable t) {
                    Toast.makeText(add_new_product.this, "PIZDEc", Toast.LENGTH_SHORT).show();
                }
            });
//                addViewModel.getGeoDataThroughViewModel(binding.adress.getText().toString()).observe(add_new_product.this, new Observer<APILogic.geoData>() {
//                    @Override
//                    public void onChanged(APILogic.geoData geoData) {
//                        Log.d(TAG,"GEO DATA in observer"+geoData.getGeo_lat());
//                        binding.geo.setText("Долгота"+geoData.getGeo_lon()+"Широта"+geoData.getGeo_lat());
//                    }
//                });


            }
        });

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