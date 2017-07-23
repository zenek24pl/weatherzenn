package com.example.zenek.weatherzen.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.zenek.weatherzen.ActivityActions;
import com.example.zenek.weatherzen.R;
import com.example.zenek.weatherzen.core.BaseFragment;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

/**
 * Created by zenek on 03.06.2017.
 */

public class LoginFragment extends BaseFragment {
    private LoginButton loginButton;
    private TextView info;
    CallbackManager callbackManager;

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);

        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        loginButton = (LoginButton) view.findViewById(R.id.login_button);
        info=(TextView)view.findViewById(R.id.info);
        callbackManager = CallbackManager.Factory.create();

      //   loginButton.setReadPermissions("email");
        loginButton.setPublishPermissions("user_posts");
        loginButton.setPublishPermissions("publish_pages");
        loginButton.setPublishPermissions("publish_actions");
        // If using in a fragment
        loginButton.setFragment(this);
        // Other app specific specialization

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                info.setText(
                        "User ID: "
                                + loginResult.getAccessToken().getUserId()
                                + "\n" +
                                "Auth Token: "
                                + loginResult.getAccessToken().getToken() );
            setFragment(TownFragment.newInstance(),true);
            }


            @Override
            public void onCancel() {
                info.setText("Login attempt canceled.");
            }

            @Override
            public void onError(FacebookException exception) {
                info.setText("Login attempt failed.");
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }
}
