package com.example.zenek.weatherzen.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.example.zenek.weatherzen.R;
import com.example.zenek.weatherzen.core.BaseFragment;
import com.inverce.mod.core.IM;



public class MenuFragment extends BaseFragment implements View.OnClickListener {

    private GridLayout mainGrid;
    private ImageView share;
    private ImageView actualEvent;
    private ImageView history;
    private ImageView showWeather;
    public static MenuFragment newInstance() {
        MenuFragment fragment = new MenuFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);

        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu_fragment, container, false);
        findViews(view);
        loadlod();
        setListeners();
        return view;
    }

    private void findViews(View view) {
        mainGrid=(GridLayout)view.findViewById(R.id.mainGrid);
        share=(ImageView)view.findViewById(R.id.share);
        actualEvent=(ImageView)view.findViewById(R.id.events);
        history=(ImageView)view.findViewById(R.id.history);
        showWeather=(ImageView)view.findViewById(R.id.show_map);
    }

    private void setListeners() {
        share.setOnClickListener(this);
        actualEvent.setOnClickListener(this);
        history.setOnClickListener(this);
        showWeather.setOnClickListener(this);
        mainGrid.setRowCount(4);
    }
    public void loadlod(){

        Glide.with(IM.activity())
                .load("http://www.indievox.com/public/anniversary/six/image/fb-share-button.png")
                .dontTransform()
                .into(share);
        Glide.with(IM.activity())
                .load("http://www.teatrarlekin.pl/images/galeria-a/logo-galeriA.jpg")
                .dontTransform()
                .into(history);
        Glide.with(IM.activity())
                .load("http://tenndha.com/wp-content/uploads/2013/03/Upcoming-Events.jpg")
                .dontTransform()
                .into(actualEvent);
        Glide.with(IM.activity())
                .load("https://goldentroutwilderness.files.wordpress.com/2012/01/various-weather.jpg")
                .dontTransform()
                .into(showWeather);
        }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.share:
                setFragment(ShareFragment.newInstance(),true);
                break;
            case R.id.history:
              //  setFragment(GaleryFragment.newInstance(),true);
                break;
            case R.id.events:
               // setFragment(ActualEvents.newInstance(),true);
                break;
            case R.id.show_map:
              //  setFragment(WeatherFragment.newInstance(),true);
        }
    }
}
