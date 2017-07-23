package com.example.zenek.weatherzen.core;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.example.zenek.weatherzen.R;

/**
 * Created by zenek on 19.06.2017.
 */

public class ProgressView extends RelativeLayout {
    public ProgressView(Context context) {
        super(context);
        init();
    }

    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ProgressView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }
    private void init() {
        inflate(getContext(), R.layout.progress_bar, this);
        hide();
    }
    public void show() {
        setVisibility(VISIBLE);

    }


    public void hide() {
        setVisibility(GONE);

    }
}