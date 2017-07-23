package com.example.zenek.weatherzen.models.answer;

import com.example.zenek.weatherzen.models.weather.Clouds;
import com.example.zenek.weatherzen.models.weather.Coord;
import com.example.zenek.weatherzen.models.weather.Main;
import com.example.zenek.weatherzen.models.weather.Sys;
import com.example.zenek.weatherzen.models.weather.Weather;
import com.example.zenek.weatherzen.models.weather.Wind;
import com.example.zenek.weatherzen.rest.BaseAnswer;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by zenek on 05.06.2017.
 */

public class WeatherAnswer extends BaseAnswer {

    @SerializedName("coord")
    private Coord coord;

    @SerializedName("weather")
    private List<Weather> weather;

    @SerializedName("base")
    private String base;

    @SerializedName("main")
    private Main main;

    @SerializedName("visibility")
    private long visibility;

    @SerializedName("wind")
    private Wind wind;

    @SerializedName("clouds")
    private Clouds clouds;

    @SerializedName("dt")
    private long dt;

    @SerializedName("sys")
    private Sys sys;

    @SerializedName("id")
    private long id;

    @SerializedName("name")
    private String name;

    @SerializedName("cod")
    private long cod;

    public Coord getCoord() {
        return coord;
    }


    public List<Weather> getWeather() {
        return weather;
    }

    public String getBase() {
        return base;
    }

    public Main getMain() {
        return main;
    }


    public long getVisibility() {
        return visibility;
    }


    public Wind getWind() {
        return wind;
    }

    public Clouds getClouds() {
        return clouds;
    }


    public long getDt() {
        return dt;
    }


    public Sys getSys() {
        return sys;
    }


    public long getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public long getCod() {
        return cod;
    }

}
