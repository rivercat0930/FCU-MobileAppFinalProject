package dev.rivercat.fw_courier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import dev.rivercat.fw_courier.view.Database;

public class RestaurantActivity extends AppCompatActivity {

    private Button btnSetting;
    private Database database;
    private ListView lv_order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);


        btnSetting = findViewById(R.id.restaurant_btn_setting);
        lv_order = findViewById(R.id.restaurant_lv_order);
        database=new Database(this);
        database.open();
        database.addRorder("rivercat", "待處理");
        database.addRorder("supersheep", "已完成");
        database.addRorder("chloeyun", "已完成");
        showOrder();
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.restaurant_btn_setting){
                    Intent intent = new Intent(RestaurantActivity.this, RestaurantSettingActivity.class);
                    startActivity(intent);
                }
            }
        };

        btnSetting.setOnClickListener(onClickListener);
    }
    private void showOrder(){
        Cursor cursor = database.getRorder();
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                RestaurantActivity.this,
                android.R.layout.simple_list_item_2,
                cursor,
                new String[]{"name", "mode"},
                new int[]{android.R.id.text1,android.R.id.text2},
                0
        );
        lv_order.setAdapter(adapter);

    }
}