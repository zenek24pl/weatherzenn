package com.example.zenek.weatherzen.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zenek.weatherzen.App;
import com.example.zenek.weatherzen.R;
import com.example.zenek.weatherzen.models.town.Town;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zenek on 18.06.2017.
 */

public class CustomDrawerListAdapter extends BaseAdapter {

    Context mContext;
    List<String> mNavItems;
    TextView iconView;

    public CustomDrawerListAdapter(Context context, List<String> navItems) {
        mContext = context;
        mNavItems = navItems;
    }

    private void setFonts() {
        Typeface font = Typeface.createFromAsset(App.getAppContext().getAssets(), "fontawesome-webfont.ttf");
        iconView.setTypeface(font);
    }

    @Override
    public int getCount() {
        return mNavItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mNavItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.drawer_list_item, null);
        } else {
            view = convertView;
        }

        TextView titleView = (TextView) view.findViewById(R.id.menu_item_text);
        iconView = (TextView) view.findViewById(R.id.menu_item_left_icon);

        titleView.setText(mNavItems.get(position));
        setFonts();

        switch (position) {
            case 0:
                iconView.setText(R.string.font_awsome_share);
                break;
            case 1:
                iconView.setText(R.string.font_awsome_galery);
                break;
            case 2:
                iconView.setText(R.string.font_awsome_umbrella);
                break;
            case 3:
                iconView.setText(R.string.font_awsome_like);
                break;
            case 4:
                iconView.setText(R.string.font_weahter_cloudy);

                break;
        }

        return view;
    }
}