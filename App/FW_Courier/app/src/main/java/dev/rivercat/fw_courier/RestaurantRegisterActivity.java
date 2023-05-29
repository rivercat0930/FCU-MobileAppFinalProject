package dev.rivercat.fw_courier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RestaurantRegisterActivity extends AppCompatActivity {

    private EditText etRestaurantName;
    private EditText etAccount;
    private EditText etPassword;
    private EditText etPassword2;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurantregister);

        etRestaurantName = findViewById(R.id.restaurantregister_et_name);
        etAccount = findViewById(R.id.restaurantregister_et_account);
        etPassword = findViewById(R.id.restaurantregister_et_password);
        etPassword2 = findViewById(R.id.restaurantregister_et_password2);
        btnRegister = findViewById(R.id.restaurantregister_btn_rigister);

        View.OnClickListener onClickListener = v -> {
            if (v.getId() == R.id.restaurantregister_btn_rigister) {
                // check null field
                boolean isNull = etRestaurantName.getText().toString().isEmpty() ||
                        etAccount.getText().toString().isEmpty() ||
                        etPassword.getText().toString().isEmpty() ||
                        etPassword2.getText().toString().isEmpty();

                if (isNull) {
                    Toast.makeText(RestaurantRegisterActivity.this, "請將所有欄位填入", Toast.LENGTH_SHORT).show();
                    return;
                }

                // check password are same
                boolean isPasswordSame = etPassword.getText().toString().equals(etPassword2.getText().toString());
                if (!isPasswordSame) {
                    Toast.makeText(RestaurantRegisterActivity.this, "密碼不一致", Toast.LENGTH_SHORT).show();
                    return;
                }

                // connect server and get response


                Intent intent = new Intent(RestaurantRegisterActivity.this, RestaurantSigninActivity.class);
                startActivity(intent);
            }
        };

        btnRegister.setOnClickListener(onClickListener);

    }
}