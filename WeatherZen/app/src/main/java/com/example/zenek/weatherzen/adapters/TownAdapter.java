package com.example.zenek.weatherzen.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.zenek.weatherzen.R;
import com.example.zenek.weatherzen.models.Galery;
import com.example.zenek.weatherzen.models.town.Town;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zenek on 16.06.2017.
 */

public class TownAdapter extends RecyclerView.Adapter<TownViewHolder> {

    private List<Town> data = new ArrayList<>();

    public void updateData(List<Town> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public TownViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new TownViewHolder(inflater.inflate(R.layout.list_town_item, parent, false));

    }


    @Override
    public void onBindViewHolder(TownViewHolder holder, int position) {
        holder.bindData(data.get(position),position);
    }


    @Override
    public int getItemCount() {
        return data.size();
    }
}