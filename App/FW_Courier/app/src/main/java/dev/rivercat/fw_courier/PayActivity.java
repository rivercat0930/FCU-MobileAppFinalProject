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

public class PayActivity extends AppCompatActivity {

    private Button btnHome;
    private Database database;

    private ListView lv_order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        lv_order = findViewById(R.id.pay_lv_choose);
        btnHome = findViewById(R.id.pay_btn_home);
        database=new Database(this);
        database.open();

        showOrder();
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.pay_btn_home){
                    Intent intent = new Intent(PayActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            }
        };

        btnHome.setOnClickListener(onClickListener);

    }
    private void showOrder(){
        Cursor cursor = database.getAllOrder();
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                PayActivity.this,
                android.R.layout.simple_list_item_2,
                cursor,
                new String[]{"name", "price"},
                new int[]{android.R.id.text1,android.R.id.text2},
                0
        );
        lv_order.setAdapter(adapter);

    }
}