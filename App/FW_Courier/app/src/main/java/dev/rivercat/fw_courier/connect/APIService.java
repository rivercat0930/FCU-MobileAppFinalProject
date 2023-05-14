package dev.rivercat.fw_courier.connect;

import dev.rivercat.fw_courier.module.LoginMessage;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIService {
    @POST("/account/login")
    Call<Void> startLogin(@Body LoginMessage loginMessage);
}
