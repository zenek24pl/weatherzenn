package com.example.zenek.weatherzen.models.weather;

import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.structure.BaseModel;



public class Wind extends BaseModel {

    @SerializedName("speed")
    private double speed;

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
