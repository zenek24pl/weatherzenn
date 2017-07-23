package com.example.zenek.weatherzen.models.town;

import com.example.zenek.weatherzen.db.AppDatabase;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by zenek on 15.06.2017.
 */
@Table(database = AppDatabase.class)
public class Town extends BaseModel {
    @SerializedName("id")
    @Column
    @PrimaryKey
    private int id;

    @SerializedName("name")
    @Column
    private String name;

    @SerializedName("likes")
    @Column
    private long likes;

    @SerializedName("entries")
    @Column
    private long entries;

    @SerializedName("shares")
    @Column
    private long shares;

    @SerializedName("image")
    @Column
    private String url;

    public long getShares() {
        return shares;
    }

    public void setShares(long shares) {
        this.shares = shares;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getLikes() {
        return likes;
    }

    public void setLikes(long likes) {
        this.likes = likes;
    }

    public long getEntries() {
        return entries;
    }

    public void setEntries(long entries) {
        this.entries = entries;
    }
}
