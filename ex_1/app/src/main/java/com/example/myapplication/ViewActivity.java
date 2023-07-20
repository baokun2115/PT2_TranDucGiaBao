package com.example.myapplication;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.myapplication.Entity.Product;
import com.example.myapplication.Repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class ViewActivity extends AppCompatActivity {

    private ArrayList<Product> productArrayList;
    private RecyclerView rvProducts;
    private ProductRVAdapter adapter;
    ProductRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        repository = new ProductRepository(getApplication());
        productArrayList = new ArrayList<>();

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        // get all products
        List<Product> products = repository.getAll();

        for (Product product : products) {
            productArrayList.add(product);
        }

        // Passing arraylist to our adapter class
        adapter = new ProductRVAdapter(productArrayList, ViewActivity.this);

        // Creating recycler view
        rvProducts = findViewById(R.id.rvProducts);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewActivity.this,
                RecyclerView.VERTICAL,
                false);
        rvProducts.setLayoutManager(linearLayoutManager);
        rvProducts.setAdapter(adapter); // Setting our adapter to recycler view
    }

    //Create an Options menu
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.optionmenu, menu);
        return true;
    }

    // Click an item in Options menu
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.clear) {
            // delete all products
            repository.deleteAll();

            // go to MainActivity after delete all products
            Intent i = new Intent(ViewActivity.this, MainActivity.class);
            startActivity(i);

            return true;
        } else if (item.getItemId() == android.R.id.home) {
//            this.finish();

            // go to MainActivity if click on back
            Intent i = new Intent(ViewActivity.this, MainActivity.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}