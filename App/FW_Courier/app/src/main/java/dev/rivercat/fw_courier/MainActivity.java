package dev.rivercat.fw_courier;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import dev.rivercat.fw_courier.connect.APIService;
import dev.rivercat.fw_courier.connect.RetrofitManager;
import dev.rivercat.fw_courier.module.LoginMessage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText username;
    private EditText password;
    private APIService apiService;
    private LoginMessage loginMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.btnLogin = findViewById(R.id.main_signin_btn);
        this.username = findViewById(R.id.main_account_tv);
        this.password = findViewById(R.id.main_password_tv);

        apiService = RetrofitManager.getInstance().getAPI();

        View.OnClickListener onClickListener = view -> {
            loginMessage = new LoginMessage(username.getText().toString(), password.getText().toString());
            Call<Void> call = apiService.startLogin(loginMessage);

            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.code() == 200)
                        Toast.makeText(MainActivity.this, "login successful", Toast.LENGTH_SHORT).show();

                    if (response.code() == 404)
                        Toast.makeText(MainActivity.this, "no user exist", Toast.LENGTH_SHORT).show();

                    if (response.code() == 403)
                        Toast.makeText(MainActivity.this, "username or password error", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    System.out.println("ERROR: " + t);
                }
            });
        };

        btnLogin.setOnClickListener(onClickListener);
    }
}