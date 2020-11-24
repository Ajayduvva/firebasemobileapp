package com.example.firestore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    Context context;
    List<Productsmodel> recycleModels;
    private LayoutInflater layoutInflater;

    public ProductsAdapter(Context context, List<Productsmodel> model) {
        this.context = context;
        this.recycleModels = model;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsAdapter.ViewHolder holder, int position) {

        holder.Name.setText(recycleModels.get(position).getName());
        holder.Price.setText(recycleModels.get(position).getPrice());

    }


    @Override
    public int getItemCount() {
        return recycleModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Name, Price;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.name);
            Price = itemView.findViewById(R.id.price);

        }

    }
}
