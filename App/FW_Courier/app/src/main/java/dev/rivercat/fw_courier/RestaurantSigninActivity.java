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

public class RestaurantSigninActivity extends AppCompatActivity {

    private EditText etAccount;
    private EditText etPassword;
    private Button btnSignIn;
    private Button btnRegister;
    //private APIService apiService;
    private Database database;

    public static String RestaurantAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurantsignin);

        etAccount = findViewById(R.id.restaurantsignin_et_account);
        etPassword = findViewById(R.id.restaurantsignin_et_password);

        btnSignIn = findViewById(R.id.restaurantsignin_btn_signin);
        btnRegister = findViewById(R.id.restaurantsignin_btn_register);

        //apiService = RetrofitManager.getInstance().getAPI();

        database = new Database(this);
        database.open();
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.restaurantsignin_btn_signin) {
                    String account = etAccount.getText().toString();
                    String password = etPassword.getText().toString();

                    if (account.isEmpty() || password.isEmpty()) {
                        Toast.makeText(RestaurantSigninActivity.this, "請輸入帳號密碼", Toast.LENGTH_SHORT).show();
                    } else {
                        boolean isAccountExist = database.checkAccountExist(account, password);
                        if (!isAccountExist) {
                            Toast.makeText(RestaurantSigninActivity.this, "帳號不存在", Toast.LENGTH_SHORT).show();
                        } else {
                            RestaurantAccount = account;

                            Intent intent = new Intent(RestaurantSigninActivity.this, RestaurantActivity.class);
                            startActivity(intent);
                        }
                    }

                } else if (v.getId() == R.id.restaurantsignin_btn_register) {
                    Intent intent = new Intent(RestaurantSigninActivity.this, RestaurantRegisterActivity.class);
                    startActivity(intent);
                }
            }

            ;

        /*View.OnClickListener onClickListener = v -> {
            if(v.getId()==R.id.restaurantsignin_btn_signin){
                boolean isNull = etAccount.getText().toString().isEmpty() ||
                        etPassword.getText().toString().isEmpty();

                if (isNull) {
                    Toast.makeText(RestaurantSigninActivity.this, "請輸入完整資訊", Toast.LENGTH_SHORT).show();
                    return;
                }

                RestaurantInformation restaurantInformation =
                        new RestaurantInformation(null, etAccount.getText().toString(), etPassword.getText().toString());
                Call<Void> call = apiService.restaurantLogin(restaurantInformation);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 200) {
                            Toast.makeText(RestaurantSigninActivity.this, "登入成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RestaurantSigninActivity.this, RestaurantActivity.class);
                            startActivity(intent);
                        }
                        else
                            Toast.makeText(RestaurantSigninActivity.this, "Error code: " + response.code(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        System.out.println(t);
                    }
                });
            }else if(v.getId()==R.id.restaurantsignin_btn_register){
                Intent intent = new Intent(RestaurantSigninActivity.this, RestaurantRegisterActivity.class);
                startActivity(intent);
            }*/


        };
            btnSignIn.setOnClickListener(onClickListener);
            btnRegister.setOnClickListener(onClickListener);
        }

}