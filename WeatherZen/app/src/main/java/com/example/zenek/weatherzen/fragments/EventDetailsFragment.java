package com.example.zenek.weatherzen.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.zenek.weatherzen.R;
import com.example.zenek.weatherzen.core.BaseFragment;
import com.example.zenek.weatherzen.models.Event;
import com.example.zenek.weatherzen.models.Event_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

/**
 * Created by zenek on 04.06.2017.
 */

public class EventDetailsFragment extends BaseFragment implements View.OnClickListener {
    private Event event;
    private long eventId;
    String name;
    private TextView description;
    private TextView eventName;
    private Button showMap;

    private static final String ID = "id";
    private static final String NAME="name";
    public static EventDetailsFragment newInstance(long eventId,String Name) {
        EventDetailsFragment fragment = new EventDetailsFragment();
        Bundle args = new Bundle();
        args.putLong(ID, eventId);
        args.putString(NAME,Name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eventId = getArgumentLong(ID, -1);
        name=getArgumentString(NAME,"-1");
        event = SQLite.select().from(Event.class).where(Event_Table.id.eq(String.valueOf(eventId))).querySingle();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.event_details_fragment, container, false);
        findViews(view);
        setListeners();
        fillData();

        return view;
    }

    private void fillData() {
        description.setText(event.getDescription());
        eventName.setText(event.getName());
    }

    private void setListeners() {
        showMap.setOnClickListener(this);
    }

    private void findViews(View view) {
        description=(TextView)view.findViewById(R.id.event_description);
        showMap=(Button)view.findViewById(R.id.bt_map);
        eventName=(TextView)view.findViewById(R.id.event_name);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_map:
                setFragment(MapFragment.newInstance(name,event.getIdTemp()),true);
                break;

        }

    }
}
