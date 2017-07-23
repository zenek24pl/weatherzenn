package com.example.zenek.weatherzen.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zenek.weatherzen.R;
import com.example.zenek.weatherzen.models.Galery;
import com.inverce.mod.core.IM;

/**
 * Created by zenek on 04.06.2017.
 */

public class GaleryViewHolder extends RecyclerView.ViewHolder {
    private TextView name;
    private ImageView image;


    GaleryViewHolder(View view) {
        super(view);
        findViews(view);

    }

    private void findViews(View view) {
        name = (TextView) view.findViewById(R.id.galery_name);
        image = (ImageView) view.findViewById(R.id.galery_image);
    }


    public void bindData(Galery product) {
        name.setText(product.getName());
        Glide.with(IM.activity())
                .load(product.getUrl())
                .dontTransform()
                .into(image);

    }
}
