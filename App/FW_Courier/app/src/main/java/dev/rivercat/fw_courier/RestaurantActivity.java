package dev.rivercat.fw_courier;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import dev.rivercat.fw_courier.connect.APIService;
import dev.rivercat.fw_courier.connect.RetrofitManager;
import dev.rivercat.fw_courier.module.HistoryInformation;
import dev.rivercat.fw_courier.view.RestaurantOrderView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantActivity extends AppCompatActivity {

    private Button btnSetting;
    private ListView lv_order;
    private APIService apiService;
    private String shopName;
    public static Context restaurantActivityContext;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        apiService = RetrofitManager.getInstance().getAPI();
        btnSetting = findViewById(R.id.restaurant_btn_setting);
        lv_order = findViewById(R.id.restaurant_lv_order);
        restaurantActivityContext = getApplicationContext();
        handler = new Handler();

        File prjDir = this.getFilesDir();
        File outputFile = new File(prjDir,"username_shop.txt");
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

            shopName = sb.toString();
        }catch (IOException e){
            System.out.println(e);
        }

        doTheAutoRefresh();

        getOrderFromRemote(shopName);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.restaurant_btn_setting){
                    Intent intent = new Intent(RestaurantActivity.this, RestaurantSettingActivity.class);
                    startActivity(intent);
                }
            }
        };

        AdapterView.OnItemLongClickListener onItemLongClickListener = (adapterView, view, i, l) -> {
            AlertDialog.Builder alert1 = new AlertDialog.Builder(RestaurantActivity.this);
            AlertDialog SCN;

            TextView usernameField = view.findViewById(R.id.restaurant_show_tv_name);
            String username = usernameField.getText().toString();

            TextView buyField = view.findViewById(R.id.restaurant_show_tv_description);
            ArrayList<String> buy = new ArrayList<>();
            buy.addAll(Arrays.asList(buyField.getText().toString().split(" ")));

            alert1.setTitle("是否完成 " + username + " 訂單?");
            alert1.setMessage("Message內容");
            alert1.setPositiveButton("對，我做完了",null);
            alert1.setNegativeButton("沒，我按爽的", null);

            SCN = alert1.show();

            SCN.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v -> {
                // send done info to server
                HistoryInformation historyInformation = new HistoryInformation(shopName, username, buy);
                sendDoneMessage(historyInformation);

                // close window
                SCN.dismiss();
            });

            SCN.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(v -> {
                // close window
                SCN.dismiss();
            });

            return true;
        };

        btnSetting.setOnClickListener(onClickListener);
        lv_order.setOnItemLongClickListener(onItemLongClickListener);
    }

    private void getOrderFromRemote(String username){
        Call<ArrayList<HistoryInformation>> call = apiService.getOrder(username);

        call.enqueue(new Callback<ArrayList<HistoryInformation>>() {
            @Override
            public void onResponse(Call<ArrayList<HistoryInformation>> call, Response<ArrayList<HistoryInformation>> response) {
                if (response.code() == 200) {
                    ArrayList<HistoryInformation> historyInformation = response.body();

                    RestaurantOrderView restaurantOrderView = new RestaurantOrderView(RestaurantActivity.restaurantActivityContext, historyInformation);
                    lv_order.setAdapter(restaurantOrderView);
                }
                else
                    Toast.makeText(RestaurantActivity.this, "網路錯誤代碼:" + response.code(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ArrayList<HistoryInformation>> call, Throwable t) {

            }
        });
    }

    private void sendDoneMessage(HistoryInformation historyInformation) {
        Call<Void> call = apiService.sendDoneMessage(historyInformation);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 202) {
                    Toast.makeText(RestaurantActivity.this, "已完成訂單", Toast.LENGTH_SHORT).show();

                    finish();
                    startActivity(getIntent());
                }
                else
                    Toast.makeText(RestaurantActivity.this, "網路錯誤，請重新傳送", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println(t);
            }
        });
    }

    private void doTheAutoRefresh() {
        handler.postDelayed(new Runnable() {
            public void run() {
                Intent intent = getIntent();
                finish();
                startActivity(getIntent());
            }
        }, 10000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacksAndMessages(null);
    }
}