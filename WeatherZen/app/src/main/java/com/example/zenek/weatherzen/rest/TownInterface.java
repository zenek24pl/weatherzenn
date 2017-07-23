package com.example.zenek.weatherzen.rest;

import com.example.zenek.weatherzen.Cfg;
import com.example.zenek.weatherzen.models.answer.TownAnswer;
import com.example.zenek.weatherzen.models.answer.WeatherAnswer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by zenek on 16.06.2017.
 */

public interface TownInterface {

    @GET(Cfg.TownApi.TOWNS)
    Call<TownAnswer> getTowns();
}
