package com.example.zenek.weatherzen.models.weather;

import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by zenek on 05.06.2017.
 */

public class Clouds extends BaseModel {

    @SerializedName("all")
    private long all;

    public long getAll() {
        return all;
    }

    public void setAll(long all) {
        this.all = all;
    }
}
