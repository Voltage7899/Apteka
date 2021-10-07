package com.company.catalogapteka.View;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.company.catalogapteka.ModelM.Model.Product;
import com.company.catalogapteka.R;

import java.util.List;

import static android.net.Uri.parse;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductViewHolder> {

    private Context context;
    List<Product> productList;
    private OnItemClickListener listener;

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
        try {
            Uri uri=parse(this.productList.get(position).image);
            holder.imageView.setImageURI(uri);
            Log.d("MyTag","image in bind "+uri);
        }
        catch (NullPointerException e){

        }



    }

    @Override
    public int getItemCount() {
        if(productList ==null){
            return 0;
        }
        else{return productList.size();}

    }
    public class ProductViewHolder extends RecyclerView.ViewHolder  {

        TextView name;
        TextView description;
        TextView price;
        ImageView imageView;
        OnItemClickListener onItemClickListener;
        public ProductViewHolder(View view){
            super(view);
            imageView=view.findViewById(R.id.imageRec);
            name = view.findViewById(R.id.Name);
            description=view.findViewById(R.id.description);
            price=view.findViewById(R.id.price);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();
                    if(listener!=null && position!=RecyclerView.NO_POSITION){
                        listener.onItemClick(productList.get(position));
                    }

                }
            });
        }


    }
    public interface OnItemClickListener{
        void onItemClick(Product product);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;

    }

}
