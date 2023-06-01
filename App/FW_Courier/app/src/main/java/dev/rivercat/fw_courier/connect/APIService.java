package dev.rivercat.fw_courier.connect;

import java.util.ArrayList;

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
}
