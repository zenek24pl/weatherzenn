package com.example.zenek.weatherzen.adapters;

import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zenek.weatherzen.App;
import com.example.zenek.weatherzen.MainActivity;
import com.example.zenek.weatherzen.R;
import com.example.zenek.weatherzen.fragments.EventDetailsFragment;
import com.example.zenek.weatherzen.models.Event;
import com.inverce.mod.core.IM;

/**
 * Created by zenek on 03.06.2017.
 */

class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView name;

    private TextView place;
    private TextView start_date;
    private TextView details;
    private TextView eventPosition;
    private RelativeLayout root;
    String Name;

    long eventId;

    EventViewHolder(View view,String townName) {
        super(view);
        findViews(view);
        setListeners();
        setFonts();
        Name=townName;
    }

    private void setFonts() {
        Typeface font = Typeface.createFromAsset(App.getAppContext().getAssets(), "fontawesome-webfont.ttf");
        details.setTypeface(font);
    }

    private void findViews(View view) {
        root = (RelativeLayout) view.findViewById(R.id.item_event_root);
        name = (TextView) view.findViewById(R.id.item_event_name);
        place = (TextView) view.findViewById(R.id.item_event_place);
        start_date = (TextView) view.findViewById(R.id.item_event_start);
        details = (TextView) view.findViewById(R.id.item_event_details);
        eventPosition = (TextView) view.findViewById(R.id.item_event_position);
    }


    public void bindData(Event product, int position) {
        if (product != null) {
            this.eventId = Long.parseLong(product.getId());
            name.setText(product.getName());
            eventPosition.setText(String.valueOf(position));
            if (product.getPlace() != null) {
                place.setText(product.getPlace().getName());
            } else {
                place.setText("Brak danych");
            }
            if (product.getStartTime() != null)
                start_date.setText(product.getStartTime());
        }

    }

    private void setListeners() {
        details.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_event_details:
                com.inverce.mod.core.IM.onUi().execute(() -> MainActivity.actions().post().setFragment(EventDetailsFragment.newInstance(eventId,Name), true));
                break;
        }
    }
}
