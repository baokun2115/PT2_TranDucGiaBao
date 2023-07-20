package com.example.myapplication;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Entity.Product;
import com.example.myapplication.Repository.ProductRepository;

public class ProductEdit extends AppCompatActivity {

    EditText etName, etDescription, etPrice;
    Button btnUpdateProduct, btnDeleteProduct;
    ProductRepository repository;
    Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_edit);

        // declare variables
        etName = findViewById(R.id.etName);
        etDescription = findViewById(R.id.etDescription);
        etPrice = findViewById(R.id.etPrice);
        btnUpdateProduct = findViewById(R.id.btnUpdateProduct);
        btnDeleteProduct = findViewById(R.id.btnDeleteProduct);
        repository = new ProductRepository(getApplication());

        // get information from ProductRVAdapter
        if (getIntent()!=null) {
            product = (Product) getIntent().getSerializableExtra("product");

            etName.setText(product.getProductName());
            etDescription.setText(product.getProductDescription());
            etPrice.setText(String.valueOf(product.getProductPrice()));
        }

        btnUpdateProduct.setOnClickListener(v -> updateProduct());
        btnDeleteProduct.setOnClickListener(v -> deleteProduct());
    }

    private void updateProduct() {
        product.setProductName(etName.getText().toString());
        product.setProductDescription(etDescription.getText().toString());
        product.setProductPrice(Double.parseDouble(etPrice.getText().toString()));
        repository.update(product);

        Toast.makeText(ProductEdit.this,
                "Your product id " + product.getId() + " has been updated",
                Toast.LENGTH_SHORT).show();

        // go to ViewActivity after update product
        Intent i = new Intent(ProductEdit.this, ViewActivity.class);
        startActivity(i);
    }

    private void deleteProduct() {
        repository.delete(product);

        Toast.makeText(ProductEdit.this,
                "Your product name " + product.getProductName() + " has been deleted",
                Toast.LENGTH_SHORT).show();

        // go to ViewActivity after delete product
        Intent i = new Intent(ProductEdit.this, ViewActivity.class);
        startActivity(i);
    }
}