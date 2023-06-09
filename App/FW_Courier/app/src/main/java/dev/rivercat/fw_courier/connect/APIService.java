package dev.rivercat.fw_courier.connect;

import java.util.ArrayList;

import dev.rivercat.fw_courier.module.FoodInformation;
import dev.rivercat.fw_courier.module.HistoryInformation;
import dev.rivercat.fw_courier.module.LoginInformation;
import dev.rivercat.fw_courier.module.RegisterInformation;
import dev.rivercat.fw_courier.module.RestaurantInformation;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIService {
    @POST("/account/login")
    Call<Void> login(@Body LoginInformation loginInformation);

    @POST("/account/register")
    Call<Void> register(@Body RegisterInformation registerInformation);

    @GET("/restaurant/all")
    Call<ArrayList<RestaurantInformation>> restaurant();

    @POST("/restaurant/register")
    Call<Void> restaurantRegister(@Body RestaurantInformation restaurantInformation);

    @POST("/restaurant/login")
    Call<Void> restaurantLogin(@Body RestaurantInformation restaurantInformation);

    @GET("/history/{username}")
    Call<ArrayList<String>> getHistory(@Path("username") String username);

    @GET("/restaurant_c2/{name}/get_order")
    Call<ArrayList<HistoryInformation>> getOrder(@Path("name") String restaurant_name);

    @POST("/restaurant_c2/done")
    Call<Void> sendDoneMessage(@Body HistoryInformation historyInformation);

    @POST("/restaurant_food/add")
    Call<Void> sendFoodInformationToServer(@Body FoodInformation foodInformation);

    @POST("/restaurant_food/delete")
    Call<Void> sendFoodInformationToServerDelete(@Body FoodInformation foodInformation);

    @GET("/restaurant_food/{restaurant_name}")
    Call<ArrayList<FoodInformation>> getFoodInformationFromServer(@Path("restaurant_name") String restaurantName);

    @POST("/history/send")
    Call<Void> sendOrderToServer(@Body HistoryInformation historyInformation);
}
