package dev.rivercat.fw_courier.connect;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    private static RetrofitManager retrofitManager = new RetrofitManager();
    private APIService apiService;

    private RetrofitManager() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fcu-mobileapp.rivercat.dev/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(APIService.class);
    }

    public static RetrofitManager getInstance() {
        return retrofitManager;
    }

    public APIService getAPI() {
        return apiService;
    }
}
