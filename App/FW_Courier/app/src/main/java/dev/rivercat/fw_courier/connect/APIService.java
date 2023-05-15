package dev.rivercat.fw_courier.connect;

import dev.rivercat.fw_courier.module.LoginInformation;
import dev.rivercat.fw_courier.module.RegisterInformation;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIService {
    @POST("/account/login")
    Call<Void> login(@Body LoginInformation loginInformation);

    @POST("/account/register")
    Call<Void> register(@Body RegisterInformation registerInformation);
}
