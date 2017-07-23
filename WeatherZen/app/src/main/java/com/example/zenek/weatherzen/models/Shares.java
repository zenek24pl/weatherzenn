package com.example.zenek.weatherzen.models;

import com.example.zenek.weatherzen.db.AppDatabase;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by zenek on 17.06.2017.
 */
@Table(database = AppDatabase.class)
public class Shares extends BaseModel {

    @SerializedName("id")
    @Column
    @PrimaryKey
    private int id;

    @SerializedName("count")
    @Column
    private long count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
