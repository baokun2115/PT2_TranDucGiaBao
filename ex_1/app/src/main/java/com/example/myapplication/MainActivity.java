package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Entity.Product;
import com.example.myapplication.Repository.ProductRepository;

public class MainActivity extends AppCompatActivity {
    // this is application using room database

    private EditText editName, editDescription, editPrice;
    private Button btnAddProduct, btnReadProducts;
    private ProductRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.editName);
        editDescription = findViewById(R.id.editDescription);
        editPrice = findViewById(R.id.editPrice);

        btnAddProduct = findViewById(R.id.btnAddProduct);
        btnReadProducts = findViewById(R.id.btnReadProducts);
        repository = new ProductRepository(getApplication());

        btnAddProduct.setOnClickListener(v -> addProduct());

        btnReadProducts.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, ViewActivity.class);
            startActivity(i);
        });
    }

    private void addProduct() {
        Product product = new Product(editName.getText().toString(),
                editDescription.getText().toString(),
                Double.parseDouble(editPrice.getText().toString()));
        repository.insert(product);

        Toast.makeText(MainActivity.this,
                "Your product name " + product.getProductName() + " has been added",
                Toast.LENGTH_SHORT).show();

        editName.setText("");
        editDescription.setText("");
        editPrice.setText("");
    }
}