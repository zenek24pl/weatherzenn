package com.example.zenek.weatherzen.rest;

import com.example.zenek.weatherzen.Cfg;
import com.example.zenek.weatherzen.models.answer.EventAnswer;
import com.example.zenek.weatherzen.models.answer.WeatherAnswer;
import com.google.android.gms.awareness.state.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by zenek on 05.06.2017.
 */

public interface WeatherInterface {

    @GET(Cfg.WeatherApi.WEATHER)
    Call<WeatherAnswer> getWeather(@Query("q")String city);
}
