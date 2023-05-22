package dev.rivercat.fw_courier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserActivity extends AppCompatActivity {

    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        btnBack = findViewById(R.id.user_btn_back);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.user_btn_back){
                    Intent intent = new Intent(UserActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            }
        };

        btnBack.setOnClickListener(onClickListener);
    }
}