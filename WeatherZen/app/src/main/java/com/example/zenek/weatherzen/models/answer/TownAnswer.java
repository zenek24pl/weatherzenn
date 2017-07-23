package com.example.zenek.weatherzen.models.answer;

import com.example.zenek.weatherzen.models.Event;
import com.example.zenek.weatherzen.models.town.Town;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by zenek on 16.06.2017.
 */

public class TownAnswer {
    @SerializedName("towns")
    List<Town> towns;

    public List<Town> getTwons() {
        return towns;
    }
}
