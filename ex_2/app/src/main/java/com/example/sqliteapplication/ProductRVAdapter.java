package com.example.sqliteapplication;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqliteapplication.Entity.Product;

import java.util.ArrayList;

public class ProductRVAdapter extends RecyclerView.Adapter<ProductRVAdapter.ViewHolder> {

    private ArrayList<Product> productArrayList;
    private Context context;

    public ProductRVAdapter(ArrayList<Product> products, Context context) {
        productArrayList = products;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductRVAdapter.ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        Product product = productArrayList.get(position);
        Log.i(TAG, "onBindViewHolder id: " + product.getId());
        holder.tvProductName.setText(product.getProductName());
        holder.tvProductDesc.setText(product.getProductDescription());
        holder.tvProductPrice.setText(String.valueOf(product.getProductPrice()));
    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvProductName, tvProductDesc, tvProductPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProductName = itemView.findViewById(R.id.tvName);
            tvProductDesc = itemView.findViewById(R.id.tvDescription);
            tvProductPrice = itemView.findViewById(R.id.tvPrice);

            // below line is to add on click listener for our recycler view item.
            itemView.setOnClickListener(v -> {

                int position = getAdapterPosition();
                Product product = productArrayList.get(position);

                // Update, delete
                Toast.makeText(context, "You click on " + product.getId(), Toast.LENGTH_SHORT).show();

                // pass object information to ProductEdit class
                Intent intent = new Intent(context, ProductEdit.class);
                intent.putExtra("product", product);
                context.startActivity(intent);
            });
        }
    }
}