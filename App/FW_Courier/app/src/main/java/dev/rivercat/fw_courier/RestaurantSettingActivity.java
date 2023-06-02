package dev.rivercat.fw_courier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import dev.rivercat.fw_courier.view.Database;
import android.widget.TextView;

public class                 RestaurantSettingActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etDescription;
    private EditText etPrice;
    private Button btnAdd;
    private Button btnDel;
    private Button btnFin;

    private ListView lvFood;
    private Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_setting);

        etName = findViewById(R.id.restaurantsetting_et_name);
        etDescription = findViewById(R.id.restaurantsetting_et_description);
        etPrice = findViewById(R.id.restaurantsetting_et_price);
        btnAdd = findViewById(R.id.restaurantsetting_btn_add);
        btnDel = findViewById(R.id.restaurantsetting_btn_del);
        btnFin = findViewById(R.id.restaurantsetting_btn_fin);
        lvFood = findViewById(R.id.restaurantsetting_lv_item);
        database = new Database(this);
        database.open();

        database.addFoods("早安咖啡", "早安壓",25);
        database.addFoods("午安咖啡", "就是咖啡",25);
        database.addFoods("晚安咖啡", "含有安眠藥的咖啡",35);
        database.addFoods("早上鬆餅", "裡面有加B群的鬆餅",40);
        database.addFoods("中午便當", "就是普通的便當",75);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=etName.getText().toString();
                String description=etDescription.getText().toString();
                int price=Integer.parseInt(etPrice.getText().toString());
                if(v.getId()==R.id.restaurantsetting_btn_add){
                    if(name.isEmpty()||description.isEmpty()||String.valueOf(price).isEmpty()){
                        Toast.makeText(RestaurantSettingActivity.this, "請輸入所有資訊", Toast.LENGTH_SHORT).show();
                    }else {
                        database.addFoods(name,description,price);
                    }
                }else if(v.getId()==R.id.restaurantsetting_btn_del){

                }else if(v.getId()==R.id.restaurantsetting_btn_fin){
                    Intent intent = new Intent(RestaurantSettingActivity.this, RestaurantActivity.class);
                    startActivity(intent);
                }
                showAllFoods();
            }
        };

        btnAdd.setOnClickListener(onClickListener);
        btnDel.setOnClickListener(onClickListener);
        btnFin.setOnClickListener(onClickListener);
        showAllFoods();
    }

    private void showAllFoods() {
        Cursor cursor = database.getAllFoods();

        String[] from = new String[]{"name", "description", "price"};
        int[] to = new int[]{R.id.text1, R.id.text2, R.id.text3};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                RestaurantSettingActivity.this,
                R.layout.list_item_food,
                cursor,
                from,
                to,
                0
        );

        adapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                if (view.getId() == android.R.id.text2 && columnIndex == cursor.getColumnIndex("price")) {
                    int price = cursor.getInt(columnIndex);
                    ((TextView) view).setText(String.valueOf(price));
                    return true;
                }
                return false;
            }
        });

        lvFood.setAdapter(adapter);
    }
}