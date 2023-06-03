package dev.rivercat.fw_courier;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
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
import dev.rivercat.fw_courier.module.FoodInformation;
import dev.rivercat.fw_courier.view.FoodListView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantSettingActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etDescription;
    private EditText etPrice;
    private Button btnAdd;
    private Button btnDel;
    private Button btnFin;
    private ListView lvFood;
    private String shopName;
    private APIService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_setting);

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

        etName = findViewById(R.id.restaurantsetting_et_name);
        etDescription = findViewById(R.id.restaurantsetting_et_description);
        etPrice = findViewById(R.id.restaurantsetting_et_price);
        btnAdd = findViewById(R.id.restaurantsetting_btn_add);
        btnDel = findViewById(R.id.restaurantsetting_btn_del);
        btnFin = findViewById(R.id.restaurantsetting_btn_fin);
        lvFood = findViewById(R.id.restaurantsetting_lv_item);
        apiService = RetrofitManager.getInstance().getAPI();

        Call<ArrayList<FoodInformation>> foodInformationFromServer = apiService.getFoodInformationFromServer(shopName);
        foodInformationFromServer.enqueue(new Callback<ArrayList<FoodInformation>>() {
            @Override
            public void onResponse(Call<ArrayList<FoodInformation>> call, Response<ArrayList<FoodInformation>> response) {
                if (response.code() == 200) {
                    FoodListView foodListView = new FoodListView(getApplicationContext(), response.body());
                    lvFood.setAdapter(foodListView);
                }
                else
                    Toast.makeText(RestaurantSettingActivity.this, "網路錯誤，請重新再進入一次", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ArrayList<FoodInformation>> call, Throwable t) {
                System.out.println(t);
            }
        });

        View.OnClickListener onClickListener = v -> {
            if (v.getId() == R.id.restaurantsetting_btn_add) {
                boolean isLackInformation = etName.getText().toString().isEmpty() ||
                        etDescription.getText().toString().isEmpty() ||
                        etPrice.getText().toString().isEmpty();

                if (isLackInformation) {
                    Toast.makeText(RestaurantSettingActivity.this, "請輸入完整資訊", Toast.LENGTH_SHORT).show();
                    return;
                }

                // send information to server
                FoodInformation foodInformation = new FoodInformation(
                        shopName,
                        etName.getText().toString(),
                        Integer.parseInt(etPrice.getText().toString()),
                        etDescription.getText().toString()
                );

                Call<Void> call = apiService.sendFoodInformationToServer(foodInformation);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 202) {
                            Toast.makeText(RestaurantSettingActivity.this, "新增成功", Toast.LENGTH_SHORT).show();

                            finish();
                            startActivity(getIntent());
                        }
                        else
                            Toast.makeText(RestaurantSettingActivity.this, "錯誤代碼: " + response.code(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        System.out.println(t);
                    }
                });
            }
            else if (v.getId() == R.id.restaurantsetting_btn_del) {
                boolean isLackInformation = etName.getText().toString().isEmpty() ||
                        etDescription.getText().toString().isEmpty() ||
                        etPrice.getText().toString().isEmpty();

                if (isLackInformation) {
                    Toast.makeText(RestaurantSettingActivity.this, "請輸入完整資訊", Toast.LENGTH_SHORT).show();
                    return;
                }

                // send information to server
                FoodInformation foodInformation = new FoodInformation(
                        shopName,
                        etName.getText().toString(),
                        Integer.parseInt(etPrice.getText().toString()),
                        etDescription.getText().toString()
                );

                Call<Void> call = apiService.sendFoodInformationToServerDelete(foodInformation);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 202) {
                            Toast.makeText(RestaurantSettingActivity.this, "刪除成功", Toast.LENGTH_SHORT).show();

                            finish();
                            startActivity(getIntent());
                        }
                        else
                            Toast.makeText(RestaurantSettingActivity.this, "錯誤代碼: " + response.code(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        System.out.println(t);
                    }
                });
            }
            else if (v.getId() == R.id.restaurantsetting_btn_fin) {
                Intent intent = new Intent(RestaurantSettingActivity.this, RestaurantActivity.class);
                startActivity(intent);
            }
        };

        AdapterView.OnItemClickListener onItemClickListener = (adapterView, view, i, l) -> {
            TextView targetString = view.findViewById(R.id.list_restaurant_name);
            etName.setText(targetString.getText().toString());

            targetString = view.findViewById(R.id.order_show_tv_description);
            etDescription.setText(targetString.getText().toString());

            targetString = view.findViewById(R.id.list_restaurant_tv_price);
            etPrice.setText(targetString.getText().toString().replace("NT$ ", ""));
        };

        btnAdd.setOnClickListener(onClickListener);
        btnDel.setOnClickListener(onClickListener);
        btnFin.setOnClickListener(onClickListener);
        lvFood.setOnItemClickListener(onItemClickListener);
    }
}