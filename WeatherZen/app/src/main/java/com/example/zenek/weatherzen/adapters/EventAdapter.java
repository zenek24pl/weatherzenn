package com.example.zenek.weatherzen.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.zenek.weatherzen.R;
import com.example.zenek.weatherzen.models.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zenek on 03.06.2017.
 */

public class EventAdapter extends RecyclerView.Adapter<EventViewHolder> {

    private List<Event> data = new ArrayList<>();
    String townName;

    public void updateData(List<Event> data,String townName){
        this.data = data;
        this.townName=townName;
        notifyDataSetChanged();
    }
    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new EventViewHolder(inflater.inflate(R.layout.list_event_item, parent, false ),townName);

    }


    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        holder.bindData(data.get(position),position);
    }


    @Override
    public int getItemCount() {
       return data.size();
    }
}
