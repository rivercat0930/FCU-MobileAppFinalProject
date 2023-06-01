package dev.rivercat.fw_courier.view;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.appcompat.app.AppCompatActivity;

public class Database {
    private AppCompatActivity activity;

    private SQLiteDatabase database;

    private static final String DATABASE_NAME = "fw_database";

    private static final String CRATE_ACCOUNT_TABLE = "CREATE TABLE IF NOT EXISTS Accounts ("+
        "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
        "account TEXT NOT NULL, " +
        "password TEXT NOT NULL )";

    public Database(AppCompatActivity activity){
        this.activity = activity;
    }
    public void open(){
        database = activity.openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE,null);
        database.execSQL(CRATE_ACCOUNT_TABLE);
    }

    public void addAccount(String account, String password) {
        ContentValues values = new ContentValues();
        values.put("account", account);
        values.put("password", password);
        database.insert("Accounts", null, values);
    }

    public boolean checkAccountExist(String account, String password) {
        String query = "SELECT * FROM Accounts WHERE account = ? AND password = ?";
        Cursor cursor = database.rawQuery(query, new String[]{account, password});
        boolean isExist = cursor.getCount() > 0;
        cursor.close();
        return isExist;
    }
}

