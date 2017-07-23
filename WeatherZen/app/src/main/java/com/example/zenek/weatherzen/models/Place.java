package com.example.zenek.weatherzen.models;

import com.example.zenek.weatherzen.db.AppDatabase;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by zenek on 03.06.2017.
 */
@Table(database = AppDatabase.class)
public class Place extends BaseModel {

    @SerializedName("name")
    @Column
    private String name;
    @SerializedName("location")
    private Location location;
    @SerializedName("id")
    @Column
    private String id;

    @Column
    @PrimaryKey(autoincrement = true)
    int idTemp;

    public int getIdTemp() {
        return idTemp;
    }

    public void setIdTemp(int idTemp) {
        this.idTemp = idTemp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
