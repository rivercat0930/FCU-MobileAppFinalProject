package dev.rivercat.fw_courier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PayActivity extends AppCompatActivity {

    private Button btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        btnHome = findViewById(R.id.pay_btn_home);
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
}