package com.company.catalogapteka;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductViewHolder> {

    private Context context;
    List<Product> productList;

    public ProductListAdapter(Context context){
        this.context=context;
    }

    public void setProductList(List<Product> productList){
        this.productList = productList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_list_element,parent,false);

        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.name.setText("Name: " +this.productList.get(position).name);
        holder.description.setText("Description: " +this.productList.get(position).description);
        holder.price.setText("Price: "+this.productList.get(position).price);

    }

    @Override
    public int getItemCount() {
        if(productList ==null){
            return 0;
        }
        else{return productList.size();}

    }
    public class ProductViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView description;
        TextView price;
        public ProductViewHolder(View view){
            super(view);
            name = view.findViewById(R.id.Name);
            description=view.findViewById(R.id.description);
            price=view.findViewById(R.id.price);
        }
    }
}
