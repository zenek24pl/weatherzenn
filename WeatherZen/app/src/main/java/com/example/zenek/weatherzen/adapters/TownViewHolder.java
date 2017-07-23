package com.example.zenek.weatherzen.adapters;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zenek.weatherzen.ActivityActions;
import com.example.zenek.weatherzen.MainActivity;
import com.example.zenek.weatherzen.R;
import com.example.zenek.weatherzen.fragments.TownDetailsFragment;
import com.example.zenek.weatherzen.models.Galery;
import com.example.zenek.weatherzen.models.town.Town;
import com.inverce.mod.core.IM;

/**
 * Created by zenek on 16.06.2017.
 */

public class TownViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private TextView name;
    private ImageView image;
    private TextView icon;
    private TextView likes;
    private ImageView color;
    private RelativeLayout root;
    private int townID;



    TownViewHolder(View view) {
        super(view);
        findViews(view);

    }

    private void findViews(View view) {
        root=(RelativeLayout)view.findViewById(R.id.town_root);
        color=(ImageView)view.findViewById(R.id.item_town_color);
        name = (TextView) view.findViewById(R.id.item_town_name);
        image = (ImageView) view.findViewById(R.id.item_town_image);
        likes=(TextView)view.findViewById(R.id.item_town_likes);
        icon=(TextView)view.findViewById(R.id.item_town_icon);
        root.setOnClickListener(this);

    }


    public void bindData(Town town,int position) {
        townID=town.getId();
        name.setText(town.getName());
        likes.setText(String.valueOf(town.getLikes()));
        Glide.with(IM.activity())
                .load(town.getUrl())
                .dontTransform()
                .into(image);
        if (position == 0) {
            color.setBackgroundColor(ContextCompat.getColor(IM.context(), R.color.green_alpha));
        } else if(position == 1){
            color.setBackgroundColor(ContextCompat.getColor(IM.context(), R.color.yellow_aplha));
        }else if(position == 2){
            color.setBackgroundColor(ContextCompat.getColor(IM.context(), R.color.orange_alpha));
        }else if(position == 3){
            color.setBackgroundColor(ContextCompat.getColor(IM.context(), R.color.red_alpha));
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.town_root:
                MainActivity.actions().post().setFragment(TownDetailsFragment.newInstance(townID),true);
                break;
        }
    }
}