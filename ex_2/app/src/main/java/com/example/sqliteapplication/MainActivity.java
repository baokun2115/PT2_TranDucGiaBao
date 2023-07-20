package com.example.sqliteapplication;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqliteapplication.Database.DBHandler;
import com.example.sqliteapplication.Entity.Product;

public class MainActivity extends AppCompatActivity {
    // this is application using SQLite

    private EditText editName, editDescription, editPrice;
    private Button btnAddProduct, btnReadProducts;
    private DBHandler dbHandler;
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.editName);
        editDescription = findViewById(R.id.editDescription);
        editPrice = findViewById(R.id.editPrice);

        btnAddProduct = findViewById(R.id.btnAddProduct);
        btnReadProducts = findViewById(R.id.btnReadProducts);

        dbHandler = new DBHandler(MainActivity.this);
        product = new Product("", "", 0.0);

        btnAddProduct.setOnClickListener(v -> {
            addProduct();
        });

        btnReadProducts.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, ViewActivity.class);
            startActivity(i);
        });
    }

    private void addProduct() {
        if (editName.getText().toString().isEmpty() ||
                editDescription.getText().toString().isEmpty() ||
                editPrice.getText().toString().isEmpty()) {
            Toast.makeText(MainActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
        } else {
            product.setProductName(editName.getText().toString());
            product.setProductDescription(editDescription.getText().toString());
            product.setProductPrice(Double.parseDouble(editPrice.getText().toString()));

            //Adding a new product to sqlite data and pass all our values to it.
            dbHandler.addNewProduct(product);

            //After adding the data we are displaying a toast message
            Toast.makeText(MainActivity.this,
                    "Your product name " + editName.getText().toString() + " has been added",
                    Toast.LENGTH_SHORT).show();

            editName.setText("");
            editDescription.setText("");
            editPrice.setText("");
        }
    }
}