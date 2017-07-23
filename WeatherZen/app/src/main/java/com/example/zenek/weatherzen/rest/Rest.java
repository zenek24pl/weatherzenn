package com.example.zenek.weatherzen.rest;


import com.example.zenek.weatherzen.Cfg;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Rest {
    private static RestInterface serviceRest;
    private static Gson gson;
    private static OkHttpClient okHttpClient;

    public static Gson getGson() {
        return gson;
    }


    public static RestInterface getRest() {
        return serviceRest;
    }

    public static void init(Cfg.Server server) {
        gson = new GsonBuilder()
                .create();

        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(server.getUrl())
                .client(okHttpClient) // this allows us to have single client for all requests
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        serviceRest = retrofit.create(RestInterface.class);
    }



    public static void cancel(Call<?> call) {
        try {
            if (!call.isCanceled() && call.isExecuted()) {
                call.cancel();
            }
        } catch (Exception ignored) {
        }
    }
}
