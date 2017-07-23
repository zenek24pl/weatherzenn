package com.example.zenek.weatherzen;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.zenek.weatherzen.core.AlertDialogFragment;
import com.example.zenek.weatherzen.fragments.GaleryFragment;
import com.example.zenek.weatherzen.fragments.LoginFragment;
import com.example.zenek.weatherzen.fragments.MapFragment;
import com.example.zenek.weatherzen.fragments.ShareFragment;
import com.example.zenek.weatherzen.fragments.WeatherFragment;
import com.example.zenek.weatherzen.models.town.Town;
import com.example.zenek.weatherzen.models.town.Town_Table;
import com.example.zenek.weatherzen.models.weather.Main;
import com.facebook.share.ShareApi;
import com.facebook.share.model.ShareContent;
import com.raizlabs.android.dbflow.sql.language.SQLite;

/**
 * Created by zenek on 18.06.2017.
 */

public class DrawerItemClickListener implements ListView.OnItemClickListener,AlertDialogListener {
    CharSequence mTitle;
    AlertDialogFragment alertDialogFragment;
    private long idTown;
    private int idT;
    private Town town;
    public DrawerItemClickListener(long idTown) {
        this.idTown = idTown;
       town= SQLite.select().from(Town.class).where(Town_Table.id.eq((int) idTown)).querySingle();
        idT=town.getId();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        selectItem(position);
        switch (position){
            case 0:
                MainActivity.actions().post().setFragment(ShareFragment.newInstance(),true);
                break;
            case 1:
                MainActivity.actions().post().setFragment(GaleryFragment.newInstance((int)idT),true);
                break;
            case 2:
                MainActivity.actions().post().setFragment(WeatherFragment.newInstance((int)idT),true);
                break;
            case 3:
                AlertDialogFragment alert= AlertDialogFragment.newInstance("LIKE","Aktualna liczba lików dla miast "+town.getName()+" wynosi: "+town.getLikes() +
                        "\nNiestety opcja likowania miasta jest tymczasowo wyłączona"+"\n\n Zgloś zażalenie do autora?" , (int) id);
                alert.showOnCurrentActivity();
                alert.setAlertDialogListener(this);

                break;
            case 4:
                MainActivity.actions().post().setFragment(LoginFragment.newInstance(),true);
                break;
        }
    }


    /** Swaps fragments in the main content view */
    private void selectItem(int position) {
        // Create a new fragment and specify the planet to show based on positio
    }


    @Override
    public void onAcceptAction() {
        Town town= SQLite.select().from(Town.class).where(Town_Table.id.eq((int) idT)).querySingle();
        long currentLikes=town.getLikes();
        town.setLikes((int) (currentLikes+1));
        town.save();
    }
}