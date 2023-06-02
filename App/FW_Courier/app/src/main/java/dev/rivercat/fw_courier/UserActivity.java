package dev.rivercat.fw_courier;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import dev.rivercat.fw_courier.connect.APIService;
import dev.rivercat.fw_courier.connect.RetrofitManager;
import dev.rivercat.fw_courier.view.HistoryView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserActivity extends AppCompatActivity {

    private Button btnBack;
    private APIService apiService;
    private ListView listView;
    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        btnBack = findViewById(R.id.user_btn_back);
        apiService = RetrofitManager.getInstance().getAPI();
        listView = findViewById(R.id.user_lv_mealshistory);
        context = getApplicationContext();

        //handle get history event
        String username = "";
        File prjDir = this.getFilesDir();
        File outputFile = new File(prjDir,"username.txt");
        try {
            FileInputStream fis = new FileInputStream(outputFile);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String temp;

            StringBuilder sb = new StringBuilder();
            while((temp = br.readLine()) != null)
                sb.append(temp);

            fis.close();
            isr.close();
            br.close();

            username = sb.toString();
        }catch (IOException e){
            System.out.println(e);
        }

        getHistoryFromRemote(username);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.user_btn_back){
                    Intent intent = new Intent(UserActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            }
        };

        btnBack.setOnClickListener(onClickListener);
    }

    public static Context getUserActivityContext(){
        return UserActivity.context;
    }

    private void getHistoryFromRemote(String username){
        Call<ArrayList<String>> call = apiService.getHistory(username);
        ArrayList<String> ret = new ArrayList<>();
        call.enqueue(new Callback<ArrayList<String>>() {
            @Override
            public void onResponse(Call<ArrayList<String>> call, Response<ArrayList<String>> response) {
                if (response.code() == 200){
                    ret.addAll(response.body());

                    ArrayList<String> ret = response.body();
                    HistoryView historyView = new HistoryView(UserActivity.context, ret);
                    listView.setAdapter(historyView);
                }
                else
                    Toast.makeText(UserActivity.this, "網路錯誤，請重新開啟程式",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ArrayList<String>> call, Throwable t) {
                System.out.println(t);
            }
        });
    }
}