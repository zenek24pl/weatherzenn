package com.example.zenek.weatherzen.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zenek.weatherzen.R;
import com.example.zenek.weatherzen.models.Event;
import com.example.zenek.weatherzen.models.Galery;
import com.inverce.mod.core.IM;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zenek on 04.06.2017.
 */

public class GaleryAdapte extends PagerAdapter {

    private List<Galery> data = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;

    public GaleryAdapte(Context context,List<Galery> image){
        this.context = context;
        data=image;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return data.size();
    }
    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View myImageLayout = inflater.inflate(R.layout.list_item_galery, view, false);
        TextView title=(TextView)myImageLayout.findViewById(R.id.galery_name);
        TextView description=(TextView)myImageLayout.findViewById(R.id.galery_desc);
        ImageView myImage = (ImageView) myImageLayout
                .findViewById(R.id.galery_image);
        Glide.with(IM.activity())
                .load(data.get(position).getUrl())
                .dontTransform()
                .into(myImage);
        title.setText(data.get(position).getName());
        description.setText(data.get(position).getDescription());

        view.addView(myImageLayout, 0);
        return myImageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}