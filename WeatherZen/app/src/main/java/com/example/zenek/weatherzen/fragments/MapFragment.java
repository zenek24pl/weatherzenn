package com.example.zenek.weatherzen.fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;

import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.zenek.weatherzen.R;
import com.example.zenek.weatherzen.core.BaseFragment;
import com.example.zenek.weatherzen.models.Event;


import com.example.zenek.weatherzen.models.Event_Table;
import com.example.zenek.weatherzen.models.Location;
import com.example.zenek.weatherzen.models.Location_Table;
import com.example.zenek.weatherzen.models.Place;
import com.example.zenek.weatherzen.models.Place_Table;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zenek on 04.06.2017.
 */

public class MapFragment extends BaseFragment implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private View view;
    Event events;
    List<Event> eventList;
    HashMap<Marker, Location> mapa = new HashMap<Marker, Location>();

    private Marker marker;
    // private GoogleMap mMap;
    private GoogleMap mMap;
    private static final String NAME = "name";
    private static final String ID="id" ;

    private String name;
    private int id;

    public static MapFragment newInstance(String name,int idTemp) {

        Bundle args = new Bundle();

        MapFragment fragment = new MapFragment();
        args.putString(NAME, name);
        args.putInt(ID, idTemp);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        name = getArgumentString(NAME, "-1");
        id=getArgumentInt(ID,-1);
        if (name.equals("Wrocław")) {
            name = "Wroclaw";
        }
        if(name.equals("Kraków")){
            name="Krakow";
         }
        if (name.equals("Łódź")) {
            name.equals("Lodz");
        }
        if (name.equals("Warszawa")) {
            name="Warsaw";
        }
        // event = SQLite.select().from(Event.class).where(Event_Table.id.eq(String.valueOf(eventId))).querySingle();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
        }
        try {
            view = inflater.inflate(R.layout.map, container, false);
        } catch (Exception e) {
        }
        eventList = SQLite.select().from(Event.class).queryList();
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (mMap == null) {
            final SupportMapFragment fragment = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map));
            fragment.getMapAsync(this);

        }
    }

    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;
        if (mMap != null) {
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                return;
            }

            mMap.setMyLocationEnabled(true);

            mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
                @Override
                public void onMapLoaded() {
                    if (mMap != null) {
                        Place places;
                        List<Location> location = new ArrayList<Location>();
                        List<Location> location2 = new ArrayList<Location>();

                        location2=SQLite.select().from(Location.class).queryList();
                        location = SQLite.select().from(Location.class).where(Location_Table.idTemp.eq(id)).queryList();

                        if (location != null) {

                            // Log.v("Newsss",news.info);
                            for (Location station : location) {
                                if (location != null &&!Double.isNaN(station.getLatitude()) &&!Double.isNaN(station.getLongitude())) {
                                    places = SQLite.select().from(Place.class).where(Place_Table.idTemp.eq(station.getIdTemp())).querySingle();
                                    LatLng point = new LatLng(station.getLatitude(), station.getLongitude());
                                    if (marker == null) {
                                        marker = mMap.addMarker(new MarkerOptions()
                                                .position(point)
                                                .title(String.valueOf(places.getName()))
                                                .snippet(String.valueOf(places.getName()))
                                                .icon(getMarkerIcon()));
                                        goToLocationZoom(station.getLatitude(), station.getLongitude(), 12);
                                        mapa.put(marker, station);
                                    }
                                }
                            }
                        }

                    } else {
                        Toast.makeText(getActivity(), "Brak internetu lub danych położenia eventu. Zawróć", Toast.LENGTH_LONG);
                    }

                }

            });
            mMap.setOnInfoWindowClickListener(this);
            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    goToLocationZoom(marker.getPosition().latitude + 0.005, marker.getPosition().longitude, 13);
                    return false;
                }
            });
            if (mMap != null) mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                @Override
                public View getInfoWindow(Marker marker) {
                    return null;
                }

                @Override
                public View getInfoContents(Marker marker) {

                    View v = LayoutInflater.from(getActivity()).inflate(R.layout.windowlayout, null);
                    Location tempEvent = mapa.get(marker);
                    TextView tvLocality = (TextView) v.findViewById(R.id.tvlocality);
                    if(tempEvent.getStreet()!=null) {
                        tvLocality.setText(tempEvent.getStreet());
                    }
                    else {
                        Event event = SQLite.select().from(Event.class).where(Event_Table.idTemp.eq(id)).querySingle();
                        tvLocality.setText(event.getName());


                    }
                  //  Toast.makeText(getContext(), tempEvent.getName(), Toast.LENGTH_SHORT).show();
                    return v;
                }

            });
        }
    }


    @Override
    public void onInfoWindowClick(Marker marker) {

    }

    private void goToLocationZoom(double lat, double lng, float zoom) {


        LatLng ll = new LatLng(lat, lng);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll, zoom);
        mMap.moveCamera(update);

    }

    private BitmapDescriptor getMarkerIcon() {
        return BitmapDescriptorFactory.fromResource(R.drawable.eventt);
    }
}
