package com.example.sqliteapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.sqliteapplication.Database.DBHandler;
import com.example.sqliteapplication.Entity.Product;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {

    private ArrayList<Product> productArrayList;
    private RecyclerView rvProducts;
    private ProductRVAdapter adapter;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        productArrayList = new ArrayList<>();
        dbHandler = new DBHandler(ViewActivity.this);

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        // get all products
        productArrayList = dbHandler.getAllProducts();

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
            dbHandler.deleteAll();

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