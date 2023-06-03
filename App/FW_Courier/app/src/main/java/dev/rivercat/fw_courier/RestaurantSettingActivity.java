package dev.rivercat.fw_courier;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class RestaurantSettingActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etDescription;
    private EditText etPrice;
    private Button btnAdd;
    private Button btnDel;
    private Button btnFin;

    private ListView lvFood;
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
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.restaurantsetting_btn_add) {

                }
                else if (v.getId() == R.id.restaurantsetting_btn_del) {

                }
                else if (v.getId() == R.id.restaurantsetting_btn_fin) {
                    Intent intent = new Intent(RestaurantSettingActivity.this, RestaurantActivity.class);
                    startActivity(intent);
                }
            }
        };

        btnAdd.setOnClickListener(onClickListener);
        btnDel.setOnClickListener(onClickListener);
        btnFin.setOnClickListener(onClickListener);
    }
}