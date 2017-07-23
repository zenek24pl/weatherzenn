package com.example.zenek.weatherzen.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DrawableUtils;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.zenek.weatherzen.ActivityActions;
import com.example.zenek.weatherzen.DrawerItemClickListener;
import com.example.zenek.weatherzen.MainActivity;
import com.example.zenek.weatherzen.R;
import com.example.zenek.weatherzen.adapters.EventAdapter;
import com.example.zenek.weatherzen.adapters.TownAdapter;
import com.example.zenek.weatherzen.core.BaseFragment;
import com.example.zenek.weatherzen.core.ProgressView;
import com.example.zenek.weatherzen.models.Event;
import com.example.zenek.weatherzen.models.Event_Table;
import com.example.zenek.weatherzen.models.Location;
import com.example.zenek.weatherzen.models.Shares;
import com.example.zenek.weatherzen.models.answer.EventAnswer;
import com.example.zenek.weatherzen.models.answer.TownAnswer;
import com.example.zenek.weatherzen.models.town.Town;
import com.example.zenek.weatherzen.models.town.Town_Table;
import com.example.zenek.weatherzen.rest.Rest;
import com.example.zenek.weatherzen.rest.TownRest;
import com.inverce.mod.core.IM;
import com.example.zenek.weatherzen.adapters.*;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.queriable.StringQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.zenek.weatherzen.models.Event.saveOrUpdateToDatabaseLocal;

/**
 * Created by zenek on 17.06.2017.
 */

public class TownDetailsFragment extends BaseFragment implements View.OnClickListener {
    private static final String ID = "id";
    private static boolean isLoaded=false;
    RecyclerView recyclerView;
    EventAdapter adapter;
    Call<EventAnswer> eventCall;
    Town town;
    int townId;
    ProgressView pr;
    Call<TownAnswer> townAnswerCall;
    private TextView townName;
    private TextView townEntries;
    private TextView townShares;
    private TextView townLikes;
    private TextView townPins;
    private ImageView imageView;
    private Button moreBt;
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    RelativeLayout progressLayout;
    private ProgressDialog dialog;
    private CustomDrawerListAdapter mAdapter;
    private String[] mPlanetTitles;
    List<Event> eventList,goodEvent;
    private List<Town> townList = new ArrayList<>();
    Location location=new Location();


    public static TownDetailsFragment newInstance(int id) {
        TownDetailsFragment fragment = new TownDetailsFragment();
        Bundle args = new Bundle();
        args.putInt(ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        townId = getArgumentInt(ID, -1);
        town = SQLite.select().from(Town.class).where(Town_Table.id.eq(townId)).querySingle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.town_details, container, false);
        findViews(view);
        setListeners();

        prepareRecyclerView();
        setDrawer();
        fetchData(town.getName());
        return view;
    }

    private void setDrawer() {
        dialog = new ProgressDialog(getActivity());
        mPlanetTitles=getResources().getStringArray(R.array.options_array);
        List<String> myResArrayList = Arrays.asList(mPlanetTitles);
        mAdapter=new CustomDrawerListAdapter(getContext(),myResArrayList);
        drawerList.setAdapter(mAdapter);
        drawerList.setOnItemClickListener(new DrawerItemClickListener(townId));
    }

    private void setData() {

        townName.setText(town.getName());
        townEntries.setText(String.valueOf( town.getEntries()+" odwiedzin"));
        townLikes.setText(String.valueOf( town.getLikes()));
        townPins.setText(String.valueOf(eventList.size()));
        townShares.setText(String.valueOf(town.getShares()));
        Glide.with(IM.activity())
                .load(town.getUrl())
                .dontTransform()
                .into(imageView);
    }

    private void setListeners() {
    moreBt.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();


    }
    @Override
    public void onPause() {
        super.onPause();
        if (eventCall != null) {
            Rest.cancel(eventCall);
        }
        if(dialog!=null){
            dialog.dismiss();
        }
    }
    private void prepareRecyclerView() {
        if (adapter == null) {
            adapter = new EventAdapter();
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(IM.context(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }

    private void findViews(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.town_details_recycler);
       // progressLayout=(RelativeLayout)view.findViewById(R.id.full_progress);
        townName = (TextView) view.findViewById(R.id.town_name);
        townEntries = (TextView) view.findViewById(R.id.entries_count);
        townLikes = (TextView) view.findViewById(R.id.town_likes);
        townPins = (TextView) view.findViewById(R.id.town_pins);
        townShares = (TextView) view.findViewById(R.id.town_share);
        imageView=(ImageView)view.findViewById(R.id.town_detail_image);
        moreBt=(Button)view.findViewById(R.id.more_bt);
        drawerLayout=(DrawerLayout)view.findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawers();
        drawerList=(ListView)view.findViewById(R.id.right_drawer);
        pr=(ProgressView)view.findViewById(R.id.progress_view1);

    }

    public void fetchData(String townName) {
       // progressLayout.setVisibility(View.VISIBLE);
       // pr.show();
      //  IM.onBg().execute(()->{
       //         pr.show();
     //           });
        //    pr.show();
        // setProgressType(Event.class);
        goodEvent=new ArrayList<Event>();

        dialog.show();
        dialog.setMessage("Laduje sie prosze czekac");
        eventCall = Rest.getRest().getEvents(townName);
        eventCall.enqueue(new Callback<EventAnswer>() {
            @Override
            public void onResponse(Call<EventAnswer> call, Response<EventAnswer> response) {
                if (response.isSuccessful()) {
                     eventList=response.body().getEvents();
                    for (Event event:eventList) {
                        if(event.getPlace()!=null) {
                            event.getPlace().save();
                            if (event.getPlace().getLocation() != null)
                            {
                            location= event.getPlace().getLocation();
                            location.save();
                                goodEvent.add(event);
                                event.getPlace().getLocation().save();
                            saveOrUpdateToDatabaseLocal(response.body().getEvents(), event.getPlace(), event.getPlace().getLocation());
                        }}}

                    adapter.updateData(goodEvent,townName);

                        setData();
                    dialog.dismiss();
                   // isLoaded=true;
                } else {
                }

            }

            @Override
            public void onFailure(Call<EventAnswer> call, Throwable t) {


            }
        });
    }

    @Override
    public void onClick(View v) {

    }

}

