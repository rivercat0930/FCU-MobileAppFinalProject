package dev.rivercat.fw_courier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class RestaurantActivity extends AppCompatActivity {

    private Button btnSetting;
    private ListView lv_order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);


        btnSetting = findViewById(R.id.restaurant_btn_setting);
        lv_order = findViewById(R.id.restaurant_lv_order);
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
}