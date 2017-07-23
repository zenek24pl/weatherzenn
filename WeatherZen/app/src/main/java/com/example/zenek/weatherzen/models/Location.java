package com.example.zenek.weatherzen.models;

import android.text.method.BaseMovementMethod;

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
public class Location extends BaseModel {


    @SerializedName("city")
    @Column
    private String city;
    @SerializedName("country")
    @Column
    private String country;
    @SerializedName("latitude")
    @Column
    private double latitude;
    @SerializedName("longitude")
    @Column
    private double longitude;
    @SerializedName("street")
    @Column
    private String street;
    @SerializedName("zip")
    @Column
    private String zip;
    @Column
    @PrimaryKey(autoincrement = true)
    int idTemp;

    public int getIdTemp() {
        return idTemp;
    }

    public void setIdTemp(int idTemp) {
        this.idTemp = idTemp;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
