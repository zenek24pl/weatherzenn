package com.example.zenek.weatherzen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.zenek.weatherzen.core.BaseActivity;

public class SplashActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        startActivity(intent(MainActivity.class));

        finish();
    }

    private Intent intent(Class<? extends Activity> clazz) {
        Intent intent = new Intent(this, clazz);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }
}
