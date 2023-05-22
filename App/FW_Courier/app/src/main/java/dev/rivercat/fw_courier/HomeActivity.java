package dev.rivercat.fw_courier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    private Button btnHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnHistory = findViewById(R.id.home_btn_history);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.home_btn_history){
                    Intent intent = new Intent(HomeActivity.this, UserActivity.class);
                    startActivity(intent);
                }
            }
        };

        btnHistory.setOnClickListener(onClickListener);

    }
}