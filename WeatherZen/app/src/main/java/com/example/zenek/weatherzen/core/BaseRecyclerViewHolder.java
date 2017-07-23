package com.example.zenek.weatherzen.core;


import android.support.v7.widget.RecyclerView;
import android.view.View;

public class BaseRecyclerViewHolder<T> extends RecyclerView.ViewHolder {

    public View root;

    public BaseRecyclerViewHolder(View root) {
        super(root);
        this.root = root;
    }

    public void bindData(T data) {
    }

    public void bindData(T data, int position) {
    }
}