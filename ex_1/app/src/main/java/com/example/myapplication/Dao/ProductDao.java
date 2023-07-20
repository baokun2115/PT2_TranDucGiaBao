package com.example.myapplication.Dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.Entity.Product;

import java.util.List;

@androidx.room.Dao
public interface ProductDao {
    @Insert
    long insert(Product product);
    @Update
    void update(Product product);
    @Delete
    void delete(Product product);
    @Query("delete from product")
    void deleteAllProducts();
    @Query("SELECT * from product order by productName asc")
    List<Product> getAllProducts();
}
