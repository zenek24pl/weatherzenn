package com.example.zenek.weatherzen.models.weather;

import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by zenek on 05.06.2017.
 */

public class Coord extends BaseModel {
    @SerializedName("lon")
    private double lon;

    @SerializedName("lat")
    private double lat;

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

}
