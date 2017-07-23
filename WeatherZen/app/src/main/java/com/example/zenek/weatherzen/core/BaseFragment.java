package com.example.zenek.weatherzen.core;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.zenek.weatherzen.ActivityActions;
import com.raizlabs.android.dbflow.sql.language.SQLite;

public class BaseFragment extends com.inverce.mod.navigation.BaseFragment<ActivityActions> {


    ActivityActions actions;

    protected boolean wasScrolled;

    @Nullable
    @Override
    public ActivityActions getActions() {
        return actions;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.w("BaseFragments", "Attaching " + this.getClass().getSimpleName() + " to " + context.getClass().getSimpleName());
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

    @Override
    public void onStop() {
        super.onStop();
        if (getActions() != null) {
            hideProgressView();
        }
    }

    public void showProgressView(boolean hasData) {
        if (getActions() != null) {
            getActions().startProgress(hasData);
        }
    }

    public void hideProgressView() {
        if (getActions() != null) {
            getActions().setProgressViewVisibility(false);
            getActions().finishProgress();
        }
    }

    public void setFragment(BaseFragment fragment, boolean addToBackstack) {
        if (getActions() != null) {
            getActions().setFragment(fragment, addToBackstack);
        }
    }

    public void popFragmentFromBackstack() {
        if (getActions() != null) {
            getActions().popFragmentFromBackstack();
        }
    }


    public void setProgressType(Class clazz) {
        if (SQLite.selectCountOf().from(clazz).count() > 0) {
            showProgressView(true);
        } else {
            showProgressView(false);
        }
    }
}
