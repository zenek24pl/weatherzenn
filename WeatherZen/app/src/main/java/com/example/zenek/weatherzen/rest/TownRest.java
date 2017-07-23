package com.example.zenek.weatherzen.rest;

import android.support.annotation.Nullable;

import com.example.zenek.weatherzen.Cfg;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zenek on 16.06.2017.
 */

public class TownRest {
    private static TownInterface serviceRest;
    private static Gson gson;
    private static OkHttpClient okHttpClient;

    public static Gson getGson() {
        return gson;
    }


    public static TownInterface getRest() {
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

        serviceRest = retrofit.create(TownInterface.class);
    }

    // rest utils are here

    @Nullable
    public static <T extends BaseAnswer> T getErrorBodyAs(Response<T> response, Class<T> type) {
        try {
            return Rest.getGson().fromJson(response.errorBody().string(), (Type) type);
        } catch (IOException | JsonSyntaxException | NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static MultipartBody.Part part(String param, String filename, File temp) {
        return MultipartBody.Part.createFormData(param, filename,
                RequestBody.create(MediaType.parse("multipart/form-data"), temp));
    }

    public static MultipartBody.Part part(String param, Object value) {
        return MultipartBody.Part.createFormData(param, String.valueOf(value));
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

