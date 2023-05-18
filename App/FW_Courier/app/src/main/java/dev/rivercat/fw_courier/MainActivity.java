package dev.rivercat.fw_courier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText etAccount;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnRegister;
    private Button btnRestaurant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etAccount = findViewById(R.id.main_et_account);
        etPassword = findViewById(R.id.main_et_password);

        btnLogin = findViewById(R.id.main_btn_login);
        btnRegister = findViewById(R.id.main_btn_register);
        btnRestaurant = findViewById(R.id.main_btn_restaurant);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.main_btn_login){
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                }else if(v.getId()==R.id.main_btn_restaurant){
                    Intent intent = new Intent(MainActivity.this, RestaurantSigninActivity.class);
                    startActivity(intent);
                }else if(v.getId()==R.id.main_btn_register){
                    Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                    startActivity(intent);
                }
            }
        };

        btnRegister.setOnClickListener(onClickListener);
        btnRestaurant.setOnClickListener(onClickListener);
        btnLogin.setOnClickListener(onClickListener);


    }
}