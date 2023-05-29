package dev.rivercat.fw_courier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

import dev.rivercat.fw_courier.connect.APIService;
import dev.rivercat.fw_courier.connect.RetrofitManager;
import dev.rivercat.fw_courier.module.RegisterInformation;
import dev.rivercat.fw_courier.module.RestaurantInformation;
import dev.rivercat.fw_courier.view.RestaurantView;
import retrofit2.Response;
import retrofit2.Callback;
import retrofit2.Call;
public class HomeActivity extends AppCompatActivity {

    private Button btnHistory;
    private ImageView home_image_logo;
    private APIService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnHistory = findViewById(R.id.home_btn_history);
        home_image_logo = findViewById(R.id.home_image_logo);
        home_image_logo.setImageResource(R.drawable.everyone_must_eat_rice);


        apiService = RetrofitManager.getInstance().getAPI();
        Call<ArrayList<RestaurantInformation>> call = apiService.restaurant();


        call.enqueue(new Callback<ArrayList<RestaurantInformation>>(){


        @Override
        public void onResponse(Call<ArrayList<RestaurantInformation>>
                                 call, Response<ArrayList<RestaurantInformation>>response) {
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

        btnHistory.setOnClickListener(onClickListener);

    }
    private void handleShow(ArrayList<RestaurantInformation>restaurantInformations){
        RestaurantView restaurantView = new  RestaurantView(this,restaurantInformations);
        GridView home_list_gv = findViewById(R.id.home_list_gv);
        home_list_gv.setAdapter(restaurantView);
}
}