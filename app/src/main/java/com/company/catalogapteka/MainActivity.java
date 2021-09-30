package com.company.catalogapteka;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    private ListViewModel listViewModel;
    private Button button;
    private ProductListAdapter productListAdapter;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Repository.initDataBase(getApplication());

        button=findViewById(R.id.show);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, add_new_product.class),100);
            }
        });

        listViewModel=new ViewModelProvider(this).get(ListViewModel.class);

        initRecyclerView();
        loadUserList();


    }
    private void loadUserList(){
        //Не рабочий вариант почему то
        LiveData<List<Product>> liveUser;

        liveUser =listViewModel.getListFromViewModel();
        Log.d(TAG,"Переменная живойдаты для загрузки списка"+liveUser.getValue());
        liveUser.observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                Log.d(TAG,"Переменная живойдаты после обзервера in loadUserList "+liveUser.getValue());

                productListAdapter.setProductList(products);
            }
        });


//        listViewModel.getListFromViewModel().observe(this,(List<Product> user)->{
//            productListAdapter.setProductList(user);
//        });

    }
    private void initRecyclerView(){
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);//декорации
        recyclerView.addItemDecoration(dividerItemDecoration);

        productListAdapter =new ProductListAdapter(getApplicationContext());
        recyclerView.setAdapter(productListAdapter);
        //почему то это говно заработало...(а нет,нихуя)...уже не актуально,но надо спросить у препода,здесь была ошибка нуллпоинтерэкс
        Log.d(TAG,"Переменная живойдаты in initRecycler"+listViewModel.getListFromViewModel().getValue());
        listViewModel.getListFromViewModel().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                Log.d(TAG,"Переменная живойдаты in initRecycler после обзервера "+listViewModel.getListFromViewModel().getValue());

                productListAdapter.setProductList(products);
            }
        });

        //Database database = Database.getInstance(this.getApplicationContext());

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                listViewModel.deleteParty(((ProductListAdapter)recyclerView.getAdapter()).productList.get(position));
                // database.userDAO().DeleteUser(((ProductListAdapter)recyclerView.getAdapter()).productList.get(position));
            }
        }).attachToRecyclerView(recyclerView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode==100){
            loadUserList();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}