package com.example.myapplication.Repository;

import static android.content.ContentValues.TAG;

import android.app.Application;
import android.util.Log;

import com.example.myapplication.Dao.ProductDao;
import com.example.myapplication.Database.ProductDatabase;
import com.example.myapplication.Entity.Product;

import java.util.List;
import java.util.concurrent.Executors;

public class ProductRepository {
    public static ProductDao productDao;

    // creating a constructor for variables
    // and passing the variables to it
    public ProductRepository(Application application) {
        ProductDatabase database = ProductDatabase.getInstance(application);
        productDao = database.dao();
    }

    // creating a method to insert the data to our database.
    public void insert(Product product) {
        Executors.newSingleThreadExecutor().execute(() -> productDao.insert(product));
    }

    // creating a method to update data in database.
    public void update(Product product) {
        Executors.newSingleThreadExecutor().execute(() -> productDao.update(product));
    }

    // creating a method to delete the data in our database.
    public void delete(Product product) {
        Executors.newSingleThreadExecutor().execute(() -> productDao.delete(product));
    }

    // below is the method to delete all the Products.
    public void deleteAll() {
        Executors.newSingleThreadExecutor().execute(() -> productDao.deleteAllProducts());
    }

    // below method is to read all the Products.
    public List<Product> getAll() {
        return productDao.getAllProducts();
    }
}
