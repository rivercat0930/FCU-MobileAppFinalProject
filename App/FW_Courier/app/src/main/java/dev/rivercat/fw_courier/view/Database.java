package dev.rivercat.fw_courier.view;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Database {
    private AppCompatActivity activity;

    private SQLiteDatabase database;

    private static final String DATABASE_NAME = "fw_database";

    private static final String CRATE_ACCOUNT_TABLE = "CREATE TABLE IF NOT EXISTS Accounts ("+
        "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
        "account TEXT NOT NULL, " +
        "password TEXT NOT NULL )";

    private static final String CRATE_RESTAURANT_TABLE = "CREATE TABLE IF NOT EXISTS Restaurant ("+
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "account TEXT NOT NULL, " +
            "password TEXT NOT NULL )";

    private static final String CRATE_FOOD_TABLE = "CREATE TABLE IF NOT EXISTS Food ("+
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "restaurant TEXT NOT NULL, " +
            "name TEXT NOT NULL, " +
            "description TEXT NOT NULL,"+
            "price INTEGER NOT NULL )";
    public Database(AppCompatActivity activity){
        this.activity = activity;
    }
    public void open(){
        database = activity.openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE,null);
        database.execSQL(CRATE_ACCOUNT_TABLE);
        database.execSQL(CRATE_RESTAURANT_TABLE);
        database.execSQL(CRATE_FOOD_TABLE);
    }

    public void addAccount(String account, String password) {
        ContentValues values = new ContentValues();
        values.put("account", account);
        values.put("password", password);
        database.insert("Accounts", null, values);
    }

    public void addRestaurant(String name,String account, String password) {
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("account", account);
        values.put("password", password);
        database.insert("Restaurant", null, values);
    }

    public void addFood(String restaurant, String name,String description,int price) {
        ContentValues values = new ContentValues();
        values.put("restaurant",restaurant);
        values.put("name",name);
        values.put("description",description);
        values.put("price",price);
        database.insert("Food", null, values);
    }

    public Cursor getAllFoods() {
        String query = "SELECT restaurant FROM Food WHERE restaurant = ?";
        Cursor cursor = database.rawQuery(query, new String[]{RestaurantAccount});
        if (cursor.getCount() > 0) {
            Toast.makeText(activity, cursor.getCount() + "成功新增", Toast.LENGTH_SHORT).show();
        }
        return cursor;
    }

    public boolean checkAccountExist(String account, String password) {
        String query = "SELECT * FROM Accounts WHERE account = ? AND password = ?";
        Cursor cursor = database.rawQuery(query, new String[]{account, password});
        boolean isExist = cursor.getCount() > 0;
        cursor.close();
        return isExist;
    }

    public boolean checkRestaurantExist(String account, String password) {
        String query = "SELECT * FROM Restaurant WHERE account = ? AND password = ?";
        Cursor cursor = database.rawQuery(query, new String[]{account, password});
        boolean isExist = cursor.getCount() > 0;
        cursor.close();
        return isExist;
    }

}

