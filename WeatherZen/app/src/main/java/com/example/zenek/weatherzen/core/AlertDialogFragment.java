package com.example.zenek.weatherzen.core;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.zenek.weatherzen.AlertDialogListener;
import com.example.zenek.weatherzen.App;
import com.example.zenek.weatherzen.MainActivity;
import com.example.zenek.weatherzen.R;
import com.example.zenek.weatherzen.fragments.TownDetailsFragment;
import com.example.zenek.weatherzen.models.town.Town;
import com.example.zenek.weatherzen.models.town.Town_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zenek on 18.06.2017.
 */

public class AlertDialogFragment extends BaseDialogFragment implements View.OnClickListener {

    private static final String TAG = "AlertDialogFragment";
    private static final String DIALOG_TITLE = "dialogTitle";
    private static final String DIALOG_INFO = "dialogInfo";
    private static final String TOWN_ID="id";

    private TextView dialogTitleTW, dialogInfoTW;
    private String dialogTitle, dialogInfo;
    private int townId;
    private Button cancelButton, acceptButton;

    private AlertDialogListener listener;

    public static AlertDialogFragment newInstance(String dialogTitle, String dialogInfo,int id) {
        AlertDialogFragment fragment = new AlertDialogFragment();
        Bundle args = new Bundle();
        args.putString(DIALOG_TITLE, dialogTitle);
        args.putString(DIALOG_INFO, dialogInfo);
        args.putInt(TOWN_ID,id);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialogTitle = getArguments().getString(DIALOG_TITLE);
        dialogInfo = getArguments().getString(DIALOG_INFO);
        townId=getArguments().getInt(TOWN_ID);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_alert, container, false);
        findViews(view);
        setListeners();
        setTexts();
        return view;
    }

    private void findViews(View view) {
        dialogTitleTW = (TextView) view.findViewById(R.id.dialog_alert_title);
        dialogInfoTW = (TextView) view.findViewById(R.id.dialog_alert_text);
        cancelButton = (Button) view.findViewById(R.id.dialog_alert_cancel_button);
        acceptButton = (Button) view.findViewById(R.id.dialog_alert_accept_button);
    }

    private void setListeners() {
        cancelButton.setOnClickListener(this);
        acceptButton.setOnClickListener(this);
    }

    private void setTexts() {
        dialogTitleTW.setText(dialogTitle);
        dialogInfoTW.setText(dialogInfo);
    }

    public void setAlertDialogListener(AlertDialogListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.dialog_alert_cancel_button:
                dismiss();
                break;
            case R.id.dialog_alert_accept_button:
                if (listener != null) {
                    listener.onAcceptAction();
                    Intent email = new Intent(Intent.ACTION_SEND);
                    email.putExtra(Intent.EXTRA_EMAIL, new String[]{"youremail@yahoo.com"});
                    email.putExtra(Intent.EXTRA_SUBJECT, "Zażalenie");
                    email.putExtra(Intent.EXTRA_TEXT, "Tutaj podaj treść");
                    email.setType("message/rfc822");
                    startActivity(Intent.createChooser(email, "Wybierz klient'a email :"));
                //    dismiss();
                }
                break;
        }
    }
}