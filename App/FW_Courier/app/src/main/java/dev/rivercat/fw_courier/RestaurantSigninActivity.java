package dev.rivercat.fw_courier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RestaurantSigninActivity extends AppCompatActivity {

    private EditText etAccount;
    private EditText etPassword;
    private Button btnSignIn;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurantsignin);

        etAccount = findViewById(R.id.restaurantsignin_et_account);
        etPassword = findViewById(R.id.restaurantsignin_et_password);
        btnSignIn = findViewById(R.id.restaurantsignin_btn_signin);
        btnRegister = findViewById(R.id.restaurantsignin_btn_register);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.restaurantsignin_btn_signin){
                    Intent intent = new Intent(RestaurantSigninActivity.this, RestaurantActivity.class);
                    startActivity(intent);
                }else if(v.getId()==R.id.restaurantsignin_btn_register){
                    Intent intent = new Intent(RestaurantSigninActivity.this, RestaurantRegisterActivity.class);
                    startActivity(intent);
                }
            }
        };

        btnRegister.setOnClickListener(onClickListener);
        btnSignIn.setOnClickListener(onClickListener);

    }





}