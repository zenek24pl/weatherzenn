package com.example.zenek.weatherzen.models;

import com.example.zenek.weatherzen.db.AppDatabase;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.OneToMany;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.List;

@Table(database = AppDatabase.class)
public class Event extends BaseModel {
    @SerializedName("description")
    @Column
    private String description;

    @SerializedName("end_time")
    @Column
    private String endTime;

    @SerializedName("name")
    @Column
    private String name;


    @SerializedName("place")
    private Place place;

    @SerializedName("start_time")
    @Column
    private String startTime;

    @SerializedName("id")
    @Column
    String id;

    @Column
    @PrimaryKey(autoincrement = true)
    int idTemp;

    public int getIdTemp() {
        return idTemp;
    }

    public void setIdTemp(int idTemp) {
        this.idTemp = idTemp;
    }

    @SerializedName("rsvp_status")
    @Column
    private String rsvpStatus;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRsvpStatus() {
        return rsvpStatus;
    }

    public void setRsvpStatus(String rsvpStatus) {
        this.rsvpStatus = rsvpStatus;
    }
    public static void saveOrUpdateToDatabaseLocal(List<Event> eventList,Place place,Location location) { //// TODO: 08/02/2017 kp optymalize this
        for (Event order : eventList) {
            order.save();
            if(order.getPlace()!=null)
            order.getPlace().save();
            place.save();

        }
        if(place!=null)
        place.save();
        if(location!=null)
        location.save();


    }

}
