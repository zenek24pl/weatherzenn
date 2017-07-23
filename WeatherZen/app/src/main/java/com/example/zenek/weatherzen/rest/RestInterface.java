package com.example.zenek.weatherzen.rest;


import com.example.zenek.weatherzen.Cfg;
import com.example.zenek.weatherzen.models.Event;
import com.example.zenek.weatherzen.models.answer.EventAnswer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestInterface {

    @GET(Cfg.RestApi.EVENTS)
    Call<EventAnswer> getEvents(@Query("q")String townName);

}
