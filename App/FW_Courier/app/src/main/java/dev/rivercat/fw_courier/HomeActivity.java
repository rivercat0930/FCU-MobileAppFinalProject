package dev.rivercat.fw_courier;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

import dev.rivercat.fw_courier.view.Database;

public class HomeActivity extends AppCompatActivity {
    private Button btnHistory;
    private ListView lvRestaurants;

    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnHistory = findViewById(R.id.home_btn_history);
        lvRestaurants = findViewById(R.id.home_list_lv);

        database = new Database(this);
        database.open();
        database.addRestaurant2("天天韓式料理", "好吃的韓式料理");
        database.addRestaurant2("微積分日式料理", "讓你變聰明的日式料理");
        database.addRestaurant2("物件導向餐廳", "高品質的西餐廳");
        database.addRestaurant2("資料庫結構喵喵", "可愛的貓貓咖啡廳");
        database.addRestaurant2("早安壓總部", "只賣白飯的店");

        showAllRestaurants();
        /*apiService = RetrofitManager.getInstance().getAPI();
        Call<ArrayList<RestaurantInformation>> call = apiService.restaurant();

        call.enqueue(new Callback<ArrayList<RestaurantInformation>>(){
            @Override
            public void onResponse(Call<ArrayList<RestaurantInformation>> call, Response<ArrayList<RestaurantInformation>>response) {
                System.out.println("restaurants list status:"+response.code());
                handleShow(response.body());
            }
            @Override
            public void onFailure(Call<ArrayList<RestaurantInformation>>call,Throwable t){

            }});*/
        lvRestaurants.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) parent.getItemAtPosition(position);
                if (cursor != null) {
                    String restaurantName = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                    String restaurantDescription = cursor.getString(cursor.getColumnIndexOrThrow("description"));

                    Intent intent = new Intent(HomeActivity.this, OrderActivity.class);
                    intent.putExtra("restaurantName", restaurantName);
                    intent.putExtra("restaurantDescription", restaurantDescription);
                    startActivity(intent);
                }
            }
        });

        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, UserActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showAllRestaurants() {
        Cursor cursor = database.getAllRestaurant2();
        if (cursor != null) {
            SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                    HomeActivity.this,
                    android.R.layout.simple_list_item_2,
                    cursor,
                    new String[]{"name", "description"},
                    new int[]{android.R.id.text1, android.R.id.text2},
                    0
            );
            lvRestaurants.setAdapter(adapter);
        }
    }
}

    /*private void handleShow(ArrayList<RestaurantInformation>restaurantInformations){
        RestaurantView restaurantView = new  RestaurantView(this,restaurantInformations);
        GridView home_list_gv = findViewById(R.id.home_list_gv);
        home_list_gv.setAdapter(restaurantView);
    }*/
