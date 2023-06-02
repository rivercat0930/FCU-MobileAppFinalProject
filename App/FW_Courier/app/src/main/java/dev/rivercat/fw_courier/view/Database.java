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
    private static final String CRATE_RESTAURANT2_TABLE = "CREATE TABLE IF NOT EXISTS Restaurant2 ("+
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "name TEXT NOT NULL, " +
            "description TEXT NOT NULL )";

    private static final String CRATE_FOODS_TABLE = "CREATE TABLE IF NOT EXISTS Foods ("+
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "name TEXT NOT NULL, " +
            "description TEXT NOT NULL,"+
            "price INTEGER NOT NULL )";
    private static final String CRATE_HISTORY_TABLE = "CREATE TABLE IF NOT EXISTS History ("+
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "name TEXT NOT NULL, " +
            "price TEXT NOT NULL )";

    private static final String CRATE_MEAL_TABLE = "CREATE TABLE IF NOT EXISTS Meal ("+
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "name TEXT NOT NULL, " +
            "description TEXT NOT NULL, " +
            "price TEXT NOT NULL )";
    private static final String CRATE_UORDER_TABLE = "CREATE TABLE IF NOT EXISTS Uorder ("+
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "name TEXT NOT NULL, " +
            "price TEXT NOT NULL )";
    private static final String CRATE_RORDER_TABLE = "CREATE TABLE IF NOT EXISTS Rorder ("+
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "name TEXT NOT NULL, " +
            "mode TEXT NOT NULL )";
    public Database(AppCompatActivity activity){
        this.activity = activity;
    }
    public void open(){
        database = activity.openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE,null);
        database.execSQL(CRATE_ACCOUNT_TABLE);
        database.execSQL(CRATE_RESTAURANT_TABLE);
        database.execSQL(CRATE_RESTAURANT2_TABLE);
        database.execSQL(CRATE_FOODS_TABLE);
        database.execSQL(CRATE_HISTORY_TABLE);
        database.execSQL(CRATE_MEAL_TABLE);
        database.execSQL(CRATE_RORDER_TABLE);
        database.execSQL(CRATE_UORDER_TABLE);
    }

    public void addAccount(String account, String password) {
        ContentValues values = new ContentValues();
        values.put("account", account);
        values.put("password", password);
        database.insert("Accounts", null, values);
    }
    public void addMeal(String name,String description, String price) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("description", description);
        values.put("price", price);
        database.insert("Meal", null, values);
    }
    public void addUorder(String name, String price) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("price", price);
        database.insert("Uorder", null, values);
    }
    public void addRorder(String name, String mode) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("mode", mode);
        database.insert("Rorder", null, values);
    }
    public void addHistory(String name, String price) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("price", price);
        database.insert("History", null, values);
    }
    public void addRestaurant(String name,String account, String password) {
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("account", account);
        values.put("password", password);
        database.insert("Restaurant", null, values);
    }
    public void addRestaurant2(String name,String description) {
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("description", description);
        database.insert("Restaurant2", null, values);
    }
    public void addFoods(String name,String description,int price) {
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("description",description);
        values.put("price",price);
        database.insert("Foods", null, values);

    }

    public Cursor getAllFoods() {
        Cursor cursor = database.rawQuery("SELECT * FROM Foods", null);
        return cursor;
    }
    public Cursor getRorder() {
        Cursor cursor = database.rawQuery("SELECT * FROM Rorder", null);
        return cursor;
    }
    public Cursor getAllMeal() {
        Cursor cursor = database.rawQuery("SELECT * FROM Meal", null);
        return cursor;
    }
    public Cursor getAllOrder() {

        Cursor cursor = database.rawQuery("SELECT * FROM Uorder", null);
        return cursor;
    }
    public Cursor getAllRestaurant2() {
        Cursor cursor = database.rawQuery("SELECT * FROM Restaurant2", null);
        return cursor;
    }
    public Cursor getHistory() {
        Cursor cursor = database.rawQuery("SELECT * FROM History", null);
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

