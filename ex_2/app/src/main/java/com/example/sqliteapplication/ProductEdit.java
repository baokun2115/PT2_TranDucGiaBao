package com.example.sqliteapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqliteapplication.Database.DBHandler;
import com.example.sqliteapplication.Entity.Product;

public class ProductEdit extends AppCompatActivity {

    EditText etName, etDescription, etPrice;
    Button btnUpdateProduct, btnDeleteProduct;
    DBHandler dbHandler;
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

        dbHandler = new DBHandler(ProductEdit.this);

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
        if (etName.getText().toString().isEmpty() ||
                etDescription.getText().toString().isEmpty() ||
                etPrice.getText().toString().isEmpty()) {
            Toast.makeText(ProductEdit.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
        } else {
            product.setProductName(etName.getText().toString());
            product.setProductDescription(etDescription.getText().toString());
            product.setProductPrice(Double.parseDouble(etPrice.getText().toString()));
            dbHandler.updateProduct(product);

            Toast.makeText(ProductEdit.this,
                    "Your product id " + product.getId() + " has been updated",
                    Toast.LENGTH_SHORT).show();

            // go to ViewActivity after update product
            Intent i = new Intent(ProductEdit.this, ViewActivity.class);
            startActivity(i);
        }
    }

    private void deleteProduct() {
        dbHandler.deleteProduct(product);

        Toast.makeText(ProductEdit.this,
                "Your product id " + product.getId() + " has been deleted",
                Toast.LENGTH_SHORT).show();

        // go to ViewActivity after delete product
        Intent i = new Intent(ProductEdit.this, ViewActivity.class);
        startActivity(i);
    }
}