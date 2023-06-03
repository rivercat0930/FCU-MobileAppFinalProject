package dev.rivercat.fw_courier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import dev.rivercat.fw_courier.connect.APIService;
import dev.rivercat.fw_courier.connect.RetrofitManager;
import dev.rivercat.fw_courier.module.FoodInformation;
import dev.rivercat.fw_courier.module.HistoryInformation;
import dev.rivercat.fw_courier.view.UserOrderView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderActivity extends AppCompatActivity {

    private Button btnSend;
    private ListView lv_order;
    private ListView lv_food;
    private APIService apiService;
    private ArrayList<FoodInformation> currentChoice = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        lv_food=findViewById(R.id.order_lv_item);
        lv_order=findViewById(R.id.order_lv_choosed);
        btnSend = findViewById(R.id.order_btn_send);
        apiService = RetrofitManager.getInstance().getAPI();

        // get clicked restaurant name
        Bundle extras = getIntent().getExtras();
        String restaurantName = "";
        if (extras != null)
            restaurantName = extras.getString("name");

        // get information from server
        Call<ArrayList<FoodInformation>> call = apiService.getFoodInformationFromServer(restaurantName);
        call.enqueue(new Callback<ArrayList<FoodInformation>>() {
            @Override
            public void onResponse(Call<ArrayList<FoodInformation>> call, Response<ArrayList<FoodInformation>> response) {
                UserOrderView userOrderView = new UserOrderView(getApplicationContext(), response.body());
                lv_food.setAdapter(userOrderView);
            }

            @Override
            public void onFailure(Call<ArrayList<FoodInformation>> call, Throwable t) {
                System.out.println(t);
            }
        });

        // set clicked event
        String finalRestaurantName = restaurantName;
        AdapterView.OnItemClickListener onItemClickListener = (adapterView, view, i, l) -> {
            TextView foodNameField = view.findViewById(R.id.restaurant_show_tv_name);
            TextView priceField = view.findViewById(R.id.restaurant_show_tv_description);

            FoodInformation foodInformation = new FoodInformation(
                    finalRestaurantName,
                    foodNameField.getText().toString(),
                    Integer.parseInt(priceField.getText().toString().replace("NT$ ", "")),
                    null
            );

            currentChoice.add(foodInformation);

            UserOrderView userOrderView = new UserOrderView(getApplicationContext(), currentChoice);
            lv_order.setAdapter(userOrderView);
        };

        // cancel order
        AdapterView.OnItemClickListener onItemClickListenerForCancel = (adapterView, view, i, l) -> {
            currentChoice.remove(i);

            UserOrderView userOrderView = new UserOrderView(getApplicationContext(), currentChoice);
            lv_order.setAdapter(userOrderView);
        };

        View.OnClickListener onClickListener = v -> {
            if (v.getId() == R.id.order_btn_send) {
                // send message information to server
                if (currentChoice.size() == 0) {
                    Toast.makeText(OrderActivity.this, "你還沒選喔", Toast.LENGTH_SHORT).show();
                    return;
                }

                File prjDir = this.getFilesDir();
                File outputFile = new File(prjDir,"username.txt");
                String username = "";
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

                // send history format
                ArrayList<String> foods = new ArrayList<>();
                for (FoodInformation food : currentChoice)
                    foods.add(food.getFoodName());

                HistoryInformation historyInformation = new HistoryInformation(
                        finalRestaurantName,
                        username,
                        foods
                );

                Call<Void> sendToServer = apiService.sendOrderToServer(historyInformation);
                sendToServer.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 200) {
                            Toast.makeText(OrderActivity.this, "已送出訂單", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(OrderActivity.this, PayActivity.class);
                            startActivity(intent);
                        }
                        else
                            Toast.makeText(OrderActivity.this, "發生錯誤請再是一次", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        System.out.println(t);
                    }
                });
            }
        };

        btnSend.setOnClickListener(onClickListener);
        lv_food.setOnItemClickListener(onItemClickListener);
        lv_order.setOnItemClickListener(onItemClickListenerForCancel);
    }
}