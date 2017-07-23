package com.example.zenek.weatherzen.models.answer;

import com.example.zenek.weatherzen.models.Event;
import com.example.zenek.weatherzen.rest.BaseAnswer;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by zenek on 03.06.2017.
 */

public class EventAnswer extends BaseAnswer{
    @SerializedName("data")
    List<Event> events;

    public List<Event> getEvents() {
        return events;
    }
}
