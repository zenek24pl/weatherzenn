package com.example.zenek.weatherzen.models.weather;

import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by zenek on 05.06.2017.
 */

public class Sys extends BaseModel
{
    @SerializedName("type")
    private long type;

    @SerializedName("id")
    private long id;

    @SerializedName("message")
    private double message;

    @SerializedName("country")
    private String country;

    @SerializedName("sunrise")
    private long sunrise;

    @SerializedName("sunset")
    private long sunset;

    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getSunrise() {
        return sunrise;
    }

    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }

    public long getSunset() {
        return sunset;
    }

    public void setSunset(long sunset) {
        this.sunset = sunset;
    }

}
