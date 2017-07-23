package com.example.zenek.weatherzen.models;

import com.example.zenek.weatherzen.db.AppDatabase;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.List;

/**
 * Created by zenek on 04.06.2017.
 */

@Table(database = AppDatabase.class)
public class Galery extends BaseModel {

    @Column
    private String description;

    @PrimaryKey(autoincrement = true)
    @Column

    private int id;

    @Column
    private String name;

    @Column
    private String townName;

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    private String url;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static void saveGalery(Galery galery) {
        galery.save();
    }
}
