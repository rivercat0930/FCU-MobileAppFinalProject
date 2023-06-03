package dev.rivercat.fw_courier;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import dev.rivercat.fw_courier.connect.APIService;
import dev.rivercat.fw_courier.connect.RetrofitManager;
import dev.rivercat.fw_courier.module.RestaurantInformation;
import dev.rivercat.fw_courier.view.RestaurantView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class HomeActivity extends AppCompatActivity {

    private Button btnHistory;
    private ImageView home_image_logo;
    private APIService apiService;
    private ListView home_list_lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnHistory = findViewById(R.id.home_btn_history);
        home_image_logo = findViewById(R.id.home_image_logo);
        home_image_logo.setImageResource(R.drawable.everyone_must_eat_rice);
        home_list_lv = findViewById(R.id.home_list_lv);

        apiService = RetrofitManager.getInstance().getAPI();
        Call<ArrayList<RestaurantInformation>> call = apiService.restaurant();

        call.enqueue(new Callback<ArrayList<RestaurantInformation>>(){
            @Override
            public void onResponse(Call<ArrayList<RestaurantInformation>> call, Response<ArrayList<RestaurantInformation>>response) {
                System.out.println("restaurants list status:"+response.code());
                handleShow(response.body());
            }
            @Override
            public void onFailure(Call<ArrayList<RestaurantInformation>>call,Throwable t){

            }});
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.home_btn_history){
                    Intent intent = new Intent(HomeActivity.this, UserActivity.class);
                    startActivity(intent);
                }
            }
        };

        AdapterView.OnItemClickListener onItemClickListener = (adapterView, view, i, l) -> {
            TextView textView = view.findViewById(R.id.restaurant_show_tv_name);
            String name = textView.getText().toString();
            Intent intent = new Intent(HomeActivity.this, OrderActivity.class);
            intent.putExtra("name", name);
            startActivity(intent);
        };

        btnHistory.setOnClickListener(onClickListener);
        home_list_lv.setOnItemClickListener(onItemClickListener);
    }
    private void handleShow(ArrayList<RestaurantInformation>restaurantInformations){
        RestaurantView restaurantView = new RestaurantView(this,restaurantInformations);
        ListView home_list_gv = findViewById(R.id.home_list_lv);
        home_list_gv.setAdapter(restaurantView);
    }
}