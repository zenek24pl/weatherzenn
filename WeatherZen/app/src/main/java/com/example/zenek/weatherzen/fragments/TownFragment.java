package com.example.zenek.weatherzen.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.zenek.weatherzen.R;
import com.example.zenek.weatherzen.adapters.GaleryAdapte;
import com.example.zenek.weatherzen.adapters.TownAdapter;
import com.example.zenek.weatherzen.core.BaseFragment;
import com.example.zenek.weatherzen.models.Galery;
import com.example.zenek.weatherzen.models.answer.TownAnswer;
import com.example.zenek.weatherzen.models.town.Town;
import com.example.zenek.weatherzen.rest.TownRest;
import com.inverce.mod.core.IM;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zenek on 16.06.2017.
 */

public class TownFragment extends BaseFragment {

    RecyclerView recyclerView;
    TownAdapter adapter;

    Call<TownAnswer> townAnswerCall;
    private List<Town> townList=new ArrayList<>();
    public static TownFragment newInstance() {
        TownFragment fragment = new TownFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.town_menu_fragment, container, false);
        findViews(view);

        prepareRecyclerView();
        testData();
        return view;
    }
    public void testData(){
//showProgressView(true);
        townAnswerCall= TownRest.getRest().getTowns();
        townAnswerCall.enqueue(new Callback<TownAnswer>() {
            @Override
            public void onResponse(Call<TownAnswer> call, Response<TownAnswer> response) {
                if (response.isSuccessful()) {
                    if(response.body().getTwons()!=null)
                     townList=response.body().getTwons();
                    for(Town t:townList){
                        t.save();
                    }
                        adapter.updateData(townList);
                }
            }

            @Override
            public void onFailure(Call<TownAnswer> call, Throwable t) {
                Toast.makeText(getContext(),t.toString(),Toast.LENGTH_LONG).show();
            }
        });


    }
    private void prepareRecyclerView() {
        if (adapter == null) {
            adapter = new TownAdapter();

        }
        recyclerView.setLayoutManager(new LinearLayoutManager(IM.context(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }

    private void findViews(View view) {
        recyclerView=(RecyclerView)view.findViewById(R.id.town_recycler);
     //   townList= SQLite.select().from(Town.class).queryList();
    }
}
