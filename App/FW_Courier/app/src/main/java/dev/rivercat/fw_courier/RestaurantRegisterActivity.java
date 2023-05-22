package dev.rivercat.fw_courier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RestaurantRegisterActivity extends AppCompatActivity {

    private EditText etAccount;
    private EditText etPassword;
    private EditText etPassword2;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurantregister);

        etAccount = findViewById(R.id.restaurantregister_et_account);
        etPassword = findViewById(R.id.restaurantregister_et_password);
        etPassword2 = findViewById(R.id.restaurantregister_et_password2);
        btnRegister = findViewById(R.id.restaurantregister_btn_rigister);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.restaurantregister_btn_rigister){
                    Intent intent = new Intent(RestaurantRegisterActivity.this, RestaurantSigninActivity.class);
                    startActivity(intent);
                }
            }
        };

        btnRegister.setOnClickListener(onClickListener);

    }
}