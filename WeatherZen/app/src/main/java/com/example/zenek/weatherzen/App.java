package com.example.zenek.weatherzen;

import android.app.Application;
import android.content.Context;
import android.os.SystemClock;

import com.example.zenek.weatherzen.rest.Rest;
import com.example.zenek.weatherzen.rest.TownRest;
import com.example.zenek.weatherzen.rest.WeatherRest;
import com.facebook.FacebookSdk;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import java.util.concurrent.TimeUnit;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


public class App extends Application {

    private static Context mContext;


    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Mogra-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());

        // DBFlow
        FlowManager.init(new FlowConfig.Builder(this)
                .openDatabasesOnInit(true)
                .build());

        // Retrofit / OkHttp / Gson
        Rest.init(Cfg.SERVER);
        WeatherRest.init(Cfg.WEATHER);
        TownRest.init(Cfg.TOWNS);


        SystemClock.sleep(TimeUnit.SECONDS.toMillis(3));
        //facebook
        FacebookSdk.sdkInitialize(getApplicationContext());
        mContext = this;
    }

    public static Context getAppContext() {
        return mContext;
    }
}
