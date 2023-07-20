package com.example.sqliteapplication.Database;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.sqliteapplication.Entity.Product;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {
    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "goods.db";
    // below int is our database version
    private static final int DB_VERSION = 1;
    // below variable is for our table name.
    private static final String TABLE_NAME = "product";
    // below variable is for our id column.
    private static final String ID_COL = "id";
    // below variable is for our Product name column
    private static final String NAME_COL = "name";
    // below variable for our Product description column.
    private static final String DESCRIPTION_COL = "description";
    // below variable is for our Product tracks column.
    private static final String PRICE_COL = "price";

    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE "
                + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + DESCRIPTION_COL + " TEXT,"
                + PRICE_COL + " TEXT)";
        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new product to our sqlite database.
    public void addNewProduct(Product product) {
        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();
        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NAME_COL, product.getProductName());
        values.put(DESCRIPTION_COL, product.getProductDescription());
        values.put(PRICE_COL, product.getProductPrice());

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    // we have created a new method to get all the products.
    public ArrayList<Product> getAllProducts() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorProducts = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        // on below line we are creating a new array list.
        ArrayList<Product> productArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorProducts.moveToFirst()) {
            do {
                Product product = new Product("", "", 0.0);

                // must do this to also pass id of product
                product.setId(cursorProducts.getInt(0));
                product.setProductName(cursorProducts.getString(1));
                product.setProductDescription(cursorProducts.getString(2));
                product.setProductPrice(cursorProducts.getDouble(3));

                // on below line we are adding the data from cursor to our array list.
                productArrayList.add(product);
            } while (cursorProducts.moveToNext());
            // moving our cursor to next.
        }

        // at last closing our cursor
        // and returning our array list.
        cursorProducts.close();
        return productArrayList;
    }

    // below is the method for updating our product
    public void updateProduct(Product product) {
        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NAME_COL, product.getProductName());
        values.put(DESCRIPTION_COL, product.getProductDescription());
        values.put(PRICE_COL, product.getProductPrice());

        // update product, compare id in sqlite db
        db.update(TABLE_NAME, values, "id = " + product.getId(), null);
        db.close();
    }

    // below is the method for deleting our Product.
    public void deleteProduct(Product product) {
        // on below line we are creating
        // a variable to write our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are calling a method to delete our
        // Product and we are comparing it with our Product name.
        db.delete(TABLE_NAME, "id = " + product.getId(), null);
        db.close();
    }

    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();

        // delete all records
        db.delete(TABLE_NAME, null, null);
        db.close();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}