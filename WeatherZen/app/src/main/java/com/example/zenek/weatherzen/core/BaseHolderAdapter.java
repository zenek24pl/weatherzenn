package com.example.zenek.weatherzen.core;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseHolderAdapter<ItemType, ItemHolder extends ViewHolder<?>> extends BaseAdapter {
    protected List<ItemType> elements;

    public BaseHolderAdapter() {
        elements = new ArrayList<>();
    }

    public List<ItemType> getElements() {
        return elements;
    }

    @Override
    public int getCount() {
        return elements.size();
    }

    @Override
    public ItemType getItem(int position) {
        return elements.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).hashCode();
    }

    @Override
    @SuppressWarnings("unchecked")
    public final View getView(int position, View convertView, ViewGroup container) {
        int type = getItemViewType(position);

        ItemHolder holder;
        if (convertView != null) {
            holder = (ItemHolder) convertView.getTag();
        } else {
            LayoutInflater inflater = LayoutInflater.from(container.getContext());
            holder = createViewHolder(inflater, container, type);
            holder.root.setTag(holder);
        }

        onBindViewHolder(holder, position);

        return holder.root;
    }

    public BaseHolderAdapter<ItemType, ItemHolder> addItem(ItemType item) {
        elements.add(item);
        notifyDataSetChanged();
        return this;
    }

    public BaseHolderAdapter<ItemType, ItemHolder> addItems(List<ItemType> items) {
        elements.addAll(items);
        notifyDataSetChanged();
        return this;
    }

    public BaseHolderAdapter<ItemType, ItemHolder> setItems(List<ItemType> items) {
        elements.clear();
        elements.addAll(items);
        notifyDataSetChanged();
        return this;
    }

    public abstract ItemHolder createViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType);
    public abstract void onBindViewHolder(ItemHolder holder, int position);
}
