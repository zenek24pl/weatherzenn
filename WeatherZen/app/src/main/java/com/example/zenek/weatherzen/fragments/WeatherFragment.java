package com.example.zenek.weatherzen.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zenek.weatherzen.App;
import com.example.zenek.weatherzen.R;
import com.example.zenek.weatherzen.core.BaseFragment;
import com.example.zenek.weatherzen.core.ProgressView;
import com.example.zenek.weatherzen.models.Event;
import com.example.zenek.weatherzen.models.answer.EventAnswer;
import com.example.zenek.weatherzen.models.answer.WeatherAnswer;
import com.example.zenek.weatherzen.models.town.Town;
import com.example.zenek.weatherzen.models.town.Town_Table;
import com.example.zenek.weatherzen.models.weather.Weather;
import com.example.zenek.weatherzen.rest.Rest;
import com.example.zenek.weatherzen.rest.WeatherRest;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.zenek.weatherzen.models.Event.saveOrUpdateToDatabaseLocal;

/**
 * Created by zenek on 05.06.2017.
 */

public class WeatherFragment extends BaseFragment implements AdapterView.OnItemSelectedListener {
    private static final String ID = "id";
    Town town;
    int townId;
    private Spinner citySpinner;
    private TextView weatherIcon;
    private TextView weatherText;
    private TextView weatherTemp;
    private TextView weatherPlace;
    private TextView weatherPressure;
    private TextView weatherHumidity;
    private Button sendRequest;
    private String cityName;
    Call<WeatherAnswer> weatherAnswerCall;
    ProgressView pr;
    String city;
    public static WeatherFragment newInstance(int id) {
        WeatherFragment fragment = new WeatherFragment();
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
        View view = inflater.inflate(R.layout.weather_fragment, container, false);
        findViews(view);
        setSpinner();
        setListeners();
        setFonts();

        return view;
    }


    private void setListeners() {
        citySpinner.setOnItemSelectedListener(this);

    }

    private void setSpinner() {
        String[] cities = App.getAppContext().getResources().getStringArray(R.array.cities);
        ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, cities);
        citySpinner.setAdapter(cityAdapter);
    }

    private void findViews(View view) {
        citySpinner = (Spinner) view.findViewById(R.id.city_spinner);
        weatherIcon = (TextView) view.findViewById(R.id.wheather_icon);
        weatherText = (TextView) view.findViewById(R.id.weather_text);
        pr=(ProgressView)view.findViewById(R.id.progress_view);
        weatherTemp = (TextView) view.findViewById(R.id.weather_temp);
        weatherPlace = (TextView) view.findViewById(R.id.weather_place);
        weatherPressure = (TextView) view.findViewById(R.id.weather_pressure);
        weatherHumidity = (TextView) view.findViewById(R.id.weather_humidity);
        sendRequest=(Button)view.findViewById(R.id.send_request);
        sendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchWeather(city);
            }
        });
        citySpinner.setSelection(townId-1);
    }

    @Override
    public void onResume() {
        super.onResume();
        fetchWeather(town.getName());
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
         city = parent.getItemAtPosition(position).toString();


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        fetchWeather(town.getName());

    }

    public void setTemp(double kelvin, String cityName, long preassure, long humidity) {
        double celsius = kelvin - 273.15F;
        String cel = String.format("%.2f", celsius);
        weatherTemp.setText(cel + " C");
        weatherPlace.setText("Miejsce : " + cityName);
        weatherPressure.setText("Ciśnienie : " + String.valueOf(preassure) + "HPa");
        weatherHumidity.setText("Wilgotność : " + String.valueOf(humidity) + "%");

    }

    public void setWeather(String desc) {
        switch (desc) {
            case "Clear":
                weatherIcon.setText(R.string.font_weather_sunny);
                weatherText.setText("Slonecznie");
                break;
            case "Snow":
                weatherIcon.setText(R.string.font_weather_snow);
                weatherText.setText("Snieg");
                break;
            case "Cloud":
                weatherIcon.setText(R.string.font_weahter_cloudy);
                weatherText.setText("Pochmurnie");
                break;
            case "Rain":
                weatherIcon.setText(R.string.font_weather_rain);
                weatherText.setText("Deszczowo");
                break;
            default:
                weatherIcon.setText(R.string.font_weather_sunny);
                weatherText.setText("Slonecznie");
                break;


        }
    }

    private void setFonts() {
        Typeface font = Typeface.createFromAsset(App.getAppContext().getAssets(), "weathericons-regular-webfont.ttf");
        weatherIcon.setTypeface(font);
    }

    public void fetchWeather(String cityName) {
        pr.show();
        weatherAnswerCall = WeatherRest.getRest().getWeather(cityName);
        weatherAnswerCall.enqueue(new Callback<WeatherAnswer>() {
            @Override
            public void onResponse(Call<WeatherAnswer> call, Response<WeatherAnswer> response) {
                if (response.isSuccessful()) {
                    //    Toast.makeText(getContext(),"Dziala",Toast.LENGTH_SHORT).show();
                    setWeather(response.body().getWeather().get(0).getMain());
                    setTemp(response.body().getMain().getTemp(), response.body().getName(), response.body().getMain().getPressure(), response.body().getMain().getHumidity());

                } else {
                }
              pr.hide();
            }

            @Override
            public void onFailure(Call<WeatherAnswer> call, Throwable t) {
                //  Toast.makeText(getContext(),t.getMessage().toString(),Toast.LENGTH_LONG).show();

                pr.hide();
            }
        });
    }


}
