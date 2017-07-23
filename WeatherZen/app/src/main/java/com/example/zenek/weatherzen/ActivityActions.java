package com.example.zenek.weatherzen;


import com.example.zenek.weatherzen.core.BaseFragment;
import com.inverce.mod.events.annotation.Listener;


public interface ActivityActions extends Listener {

    void setFragment(BaseFragment fragment, boolean addToBackstack);
    void popFragmentFromBackstack();
    void removeBackstack();
    void backPress();



    void setProgressViewVisibility(boolean visible);
    void startProgress(boolean hasData);
    void finishProgress();

}
