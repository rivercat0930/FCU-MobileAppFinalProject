package dev.rivercat.fw_courier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OrderActivity extends AppCompatActivity {

    private Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

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