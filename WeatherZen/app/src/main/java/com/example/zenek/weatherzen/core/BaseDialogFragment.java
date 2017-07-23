package com.example.zenek.weatherzen.core;


import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.zenek.weatherzen.ActivityActions;
import com.inverce.mod.core.IM;


public class BaseDialogFragment extends DialogFragment {
    ActivityActions actions;

    @Nullable
    public ActivityActions getActions() {
        return actions;
    }

    public void showOnCurrentActivity() {
        if (IM.activity() instanceof AppCompatActivity) {
            AppCompatActivity activity = (AppCompatActivity) IM.activity();
            show(activity.getSupportFragmentManager(), getTag());
        } else {
           // Log.w("Cant show dialog, no activity");
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        android.util.Log.w("BaseFragments", "Attaching " + this.getClass().getSimpleName() + " to " + context.getClass().getSimpleName());
        try {
            //noinspection unchecked // user needs to check
            actions = (ActivityActions) context;
        } catch (Exception ex) {
            throw new IllegalStateException("Activity not implementing interface", ex);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        actions = null;
    }
}
