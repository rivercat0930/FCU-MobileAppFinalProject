package dev.rivercat.fw_courier;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import dev.rivercat.fw_courier.connect.APIService;
import dev.rivercat.fw_courier.connect.RetrofitManager;
import dev.rivercat.fw_courier.module.LoginInformation;
import dev.rivercat.fw_courier.view.Database;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText etAccount;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnRegister;
    private Button btnRestaurant;
    private ImageView main_image_logo;
    private APIService apiService;

    private Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etAccount = findViewById(R.id.main_et_account);
        etPassword = findViewById(R.id.main_et_password);

        btnLogin = findViewById(R.id.main_btn_login);
        btnRegister = findViewById(R.id.main_btn_register);
        btnRestaurant = findViewById(R.id.main_btn_restaurant);

        main_image_logo = findViewById(R.id.main_image_logo);
        main_image_logo.setImageResource(R.drawable.everyone_must_eat_rice);

        apiService = RetrofitManager.getInstance().getAPI();

        database = new Database(this);
        database.open();
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // login button
                if (v.getId() == R.id.main_btn_login) {
                    String account = etAccount.getText().toString();
                    String password = etPassword.getText().toString();
                    /*if (etAccount.getText().toString().isEmpty() || etPassword.getText().toString().isEmpty())
                        Toast.makeText(MainActivity.this, "請輸入帳號密碼", Toast.LENGTH_SHORT).show();
                    else
                        dealWithLoginEvent(etAccount.getText().toString(), etPassword.getText().toString());*/
                    if (account.isEmpty() || password.isEmpty()) {
                        Toast.makeText(MainActivity.this, "請輸入帳號密碼", Toast.LENGTH_SHORT).show();
                    } else {
                        boolean isAccountExist = database.checkAccountExist(account, password);
                        if (!isAccountExist) {
                            Toast.makeText(MainActivity.this, "帳號不存在", Toast.LENGTH_SHORT).show();
                        } else {
                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                            startActivity(intent);
                        }
                    }
                }
                else if (v.getId() == R.id.main_btn_restaurant) {
                    Intent intent = new Intent(MainActivity.this, RestaurantSigninActivity.class);
                    startActivity(intent);
                }
                else if (v.getId() == R.id.main_btn_register) {
                    Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                    startActivity(intent);
                }
            }
        };

        btnRegister.setOnClickListener(onClickListener);
        btnRestaurant.setOnClickListener(onClickListener);
        btnLogin.setOnClickListener(onClickListener);
    }

    /*private void dealWithLoginEvent(String username, String password) {
        LoginInformation loginInformation = new LoginInformation(username, password);
        Call<Void> call = apiService.login(loginInformation);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                System.out.println(response.code());
                if (response.code() == 200) {
                    Toast.makeText(MainActivity.this, "登入成功", Toast.LENGTH_SHORT).show();
                    File prjDir = getFilesDir();
                    File outputFile = new File(prjDir,"username.txt");
                    try {
                        FileOutputStream fos = new FileOutputStream(outputFile);
                        fos.write(username.getBytes());
                        fos.close();
                    }catch (IOException e){
                        throw new RuntimeException(e);
                    }
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                }

                if (response.code() == 404)
                    Toast.makeText(MainActivity.this, "使用者名稱或密碼錯誤，請確認是否註冊", Toast.LENGTH_SHORT).show();

                if (response.code() == 403)
                    Toast.makeText(MainActivity.this, "使用者名稱或密碼錯誤，請確認是否註冊", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println("ERROR: " + t);
            }
        });
    }*/
}
