package dev.rivercat.fw_courier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

import dev.rivercat.fw_courier.module.FoodInformation;
import dev.rivercat.fw_courier.view.UserOrderView;

public class PayActivity extends AppCompatActivity {

    private Button btnHome;

    private ListView lv_order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        lv_order = findViewById(R.id.pay_lv_choose);
        btnHome = findViewById(R.id.pay_btn_home);

        ArrayList<FoodInformation> foodInformations = new ArrayList<>();
        Bundle extras = getIntent().getExtras();
        if (extras != null)
            foodInformations = extras.getParcelableArrayList("info");

        UserOrderView userOrderView = new UserOrderView(this, foodInformations);
        lv_order.setAdapter(userOrderView);

        View.OnClickListener onClickListener = v -> {
            if (v.getId() == R.id.pay_btn_home) {
                Intent intent = new Intent(PayActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        };

        btnHome.setOnClickListener(onClickListener);
    }
}