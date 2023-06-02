package dev.rivercat.fw_courier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import dev.rivercat.fw_courier.connect.APIService;
import dev.rivercat.fw_courier.connect.RetrofitManager;
import dev.rivercat.fw_courier.module.RestaurantInformation;
import dev.rivercat.fw_courier.view.Database;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantRegisterActivity extends AppCompatActivity {

    private EditText etRestaurantName;
    private EditText etAccount;
    private EditText etPassword;
    private EditText etPassword2;
    private Button btnRegister;
    private APIService apiService;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurantregister);

        etRestaurantName = findViewById(R.id.restaurantregister_et_name);
        etAccount = findViewById(R.id.restaurantregister_et_account);
        etPassword = findViewById(R.id.restaurantregister_et_password);
        etPassword2 = findViewById(R.id.restaurantregister_et_password2);
        btnRegister = findViewById(R.id.restaurantregister_btn_rigister);
        apiService = RetrofitManager.getInstance().getAPI();
        database = new Database(this);
        database.open();

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.restaurantregister_btn_rigister) {
                    String account = etAccount.getText().toString();
                    String password = etPassword.getText().toString();
                    String name = etRestaurantName.getText().toString();
                    if (account.isEmpty() || password.isEmpty()|| name.isEmpty()){
                        Toast.makeText(RestaurantRegisterActivity.this, "請輸入完整資訊", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    boolean isPasswordSame = etPassword.getText().toString().equals(etPassword2.getText().toString());

                    if (!isPasswordSame) {
                        Toast.makeText(RestaurantRegisterActivity.this, "密碼必須相同", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    database.addRestaurant(name,account, password);

                    Toast.makeText(RestaurantRegisterActivity.this, "註冊成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RestaurantRegisterActivity.this, RestaurantSigninActivity.class);
                    startActivity(intent);

                }
            }

        };


        /*View.OnClickListener onClickListener = v -> {
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
                RestaurantInformation restaurantInformation =
                        new RestaurantInformation(etRestaurantName.getText().toString(),
                                etAccount.getText().toString(),
                                etPassword.getText().toString());
                Call<Void> call = apiService.restaurantRegister(restaurantInformation);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 201) {
                            Toast.makeText(RestaurantRegisterActivity.this, "註冊成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RestaurantRegisterActivity.this, RestaurantSigninActivity.class);
                            startActivity(intent);
                        }
                        else {
                            System.out.println(response.code());
                            Toast.makeText(RestaurantRegisterActivity.this, "Error code: " + response.code(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            }
        };*/

        btnRegister.setOnClickListener(onClickListener);

    }
}