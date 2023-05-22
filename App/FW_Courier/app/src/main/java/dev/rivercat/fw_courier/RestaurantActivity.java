package dev.rivercat.fw_courier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RestaurantActivity extends AppCompatActivity {

    private Button btnSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        btnSetting = findViewById(R.id.restaurant_btn_setting);
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