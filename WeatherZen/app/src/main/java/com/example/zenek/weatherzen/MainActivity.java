package com.example.zenek.weatherzen;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.example.zenek.weatherzen.core.BaseActivity;
import com.example.zenek.weatherzen.core.BaseFragment;
import com.example.zenek.weatherzen.fragments.LoginFragment;
import com.example.zenek.weatherzen.fragments.MenuFragment;
import com.example.zenek.weatherzen.fragments.TownFragment;
import com.inverce.mod.core.IM;
import com.inverce.mod.core.Log;
import com.inverce.mod.events.Event;
import com.facebook.FacebookSdk;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends BaseActivity implements ActivityActions {

    public static Event<ActivityActions> actions() {
        return Event.Bus.event(ActivityActions.class);
    }


    private RelativeLayout smallProgressView, bigProgressView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setFragment(TownFragment.newInstance(), false);
        smallProgressView = (RelativeLayout) findViewById(R.id.activity_main_small_progress_view);
        bigProgressView = (RelativeLayout) findViewById(R.id.activity_main_big_progress_view);
    }

    @Override
    public void setFragment(BaseFragment fragment, boolean addToBackstack) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.root, fragment);

        if (addToBackstack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    @Override
    public void popFragmentFromBackstack() {
        FragmentManager manager = getSupportFragmentManager();
        manager.popBackStack();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    v.clearFocus();
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }

    @Deprecated
    @Override
    public void setProgressViewVisibility(boolean visible) {
        if (visible) {
            bigProgressView.setVisibility(View.VISIBLE);
        } else {
            bigProgressView.setVisibility(View.GONE);
            bigProgressView.setVisibility(View.GONE);
        }
    }

    @Override
    public void startProgress(boolean hasData) {
        if (hasData) {
            smallProgressView.setVisibility(View.VISIBLE);
        } else {
            bigProgressView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void finishProgress() {
        smallProgressView.setVisibility(View.GONE);
        bigProgressView.setVisibility(View.GONE);
    }


    @Override
    protected void onStart() {
        super.onStart();
        Event.Bus.register(ActivityActions.class, this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Event.Bus.unregister(ActivityActions.class, this);
    }


    @Override
    public void removeBackstack() {
        FragmentManager fm = this.getSupportFragmentManager();
        for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    @Override
    public void backPress() {
        super.onBackPressed();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
