package com.company.catalogapteka;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.company.catalogapteka.ViewModel.AddViewModel;

import java.io.FileNotFoundException;
import java.io.InputStream;

import static android.content.ContentValues.TAG;

public class ItemProductActivity extends AppCompatActivity {

    private ImageView img_product;
    private EditText name,desc,price;
    private Button update;
    private AddViewModel addViewModel;
    private int id;
    private Uri uri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_product);
        Log.d(TAG,"onCreate: called");

        img_product=findViewById(R.id.image_product);
        name=findViewById(R.id.item_name);
        desc=findViewById(R.id.item_description);
        price=findViewById(R.id.item_price);
        update=findViewById(R.id.update);

        Intent intent=getIntent();

        try {
            uri=Uri.parse(intent.getStringExtra("image"));
        }
        catch (NullPointerException e){

        }

        Log.d("MyTag","Image after intent "+intent.getStringExtra("image"));
        img_product.setImageURI(uri);
        id=intent.getIntExtra("id",-1);
        name.setText(intent.getStringExtra("name"));
        desc.setText(intent.getStringExtra("desc"));
        price.setText(intent.getStringExtra("price"));



        addViewModel=new ViewModelProvider(this).get(AddViewModel.class);

        updateClick();
        chooseImage();

    }
    public void updateClick(){
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addViewModel.updateProductThroughViewModel(id,name.getText().toString(),desc.getText().toString(),price.getText().toString(),uri.toString());

                finish();
            }
        });
    }
    public void chooseImage(){
        img_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent,"pickImage"),1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK && requestCode==1){

            uri=data.getData();
            img_product.setImageURI(data.getData());
        }
    }
}