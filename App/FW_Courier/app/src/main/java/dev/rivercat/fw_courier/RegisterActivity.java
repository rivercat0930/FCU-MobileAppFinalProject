package dev.rivercat.fw_courier;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import dev.rivercat.fw_courier.connect.APIService;
import dev.rivercat.fw_courier.connect.RetrofitManager;
import dev.rivercat.fw_courier.module.RegisterInformation;
import dev.rivercat.fw_courier.view.Database;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText etAccount;
    private EditText etPassword;
    private EditText etPassword2;
    private Button btnRegister;

    private Database database;
    //private APIService apiService;



    @Override
   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etAccount = findViewById(R.id.register_et_account);
        etPassword = findViewById(R.id.register_et_password);
        etPassword2 = findViewById(R.id.register_et_password2);
        btnRegister = findViewById(R.id.register_btn_register);
        database = new Database(this);
        database.open();
       // apiService = RetrofitManager.getInstance().getAPI();
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    //check empty
                   /* boolean isEmpty = etAccount.getText().toString().isEmpty() ||
                            etPassword.getText().toString().isEmpty() ||
                            etPassword2.getText().toString().isEmpty();
                    if(isEmpty){
                        Toast.makeText(RegisterActivity.this,"請輸入完整資訊",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    //check password are the same
                    boolean isPasswordSame = etPassword.getText().toString().equals(etPassword2.getText().toString());
                    if (!isPasswordSame){
                        Toast.makeText(RegisterActivity.this,"密碼必須相同",Toast.LENGTH_SHORT).show();
                        return;
                    }*/

                    if (v.getId()==R.id.register_btn_register){
                            String account = etAccount.getText().toString();
                            String password = etPassword.getText().toString();
                            String password2 = etPassword2.getText().toString();

                            if(account.isEmpty()||password.isEmpty()||password2.isEmpty()) {
                                Toast.makeText(RegisterActivity.this,"請輸入完整資訊",Toast.LENGTH_SHORT).show();
                                return;
                            }
                        boolean isPasswordSame = etPassword.getText().toString().equals(etPassword2.getText().toString());

                        if(!isPasswordSame){
                                Toast.makeText(RegisterActivity.this,"密碼必須相同",Toast.LENGTH_SHORT).show();
                                return;
                            }

                            database.addAccount(account,password);
                            /*SharedPreferences sharedPreferences = getSharedPreferences("account",MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("account",account);
                            editor.putString("password",password);
                            editor.commit();*/


                            Toast.makeText(RegisterActivity.this,"註冊成功",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(intent);

                    }


                    /*//send to server
                    //successfull then turn back main activity
                    RegisterInformation registerInformation =
                            new RegisterInformation(etAccount.getText().toString(),etPassword.getText().toString());

                    //handleRegisterConnectEvent(registerInformation);*/

                }
        };
        btnRegister.setOnClickListener(onClickListener);
    }
    /*private void handleRegisterConnectEvent(RegisterInformation registerInformation){
        Call<Void> call = apiService.register(registerInformation);
        call.enqueue(new Callback<Void>() {

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 201) {
                    Toast.makeText(RegisterActivity.this, "註冊成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                } else if (response.code() == 403) {
                    Toast.makeText(RegisterActivity.this, "使用者已註冊", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(RegisterActivity.this, "錯誤代碼：" + response.code(), Toast.LENGTH_SHORT).show();
            }

            public void onFialure(Call<Void> call, Throwable t) {
                System.out.println(t);
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            }
        });
    }*/
}