package dev.rivercat.fw_courier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class OrderActivity extends AppCompatActivity {

    private Button btnSend;
    private ListView lv_order;
    private ListView lv_food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        lv_food=findViewById(R.id.order_lv_item);
        lv_order=findViewById(R.id.order_lv_choosed);
        btnSend = findViewById(R.id.order_btn_send);
    View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.order_btn_send){
                    Intent intent = new Intent(OrderActivity.this, PayActivity.class);
                    startActivity(intent);
                }
            }
        };

        btnSend.setOnClickListener(onClickListener);

    }
}