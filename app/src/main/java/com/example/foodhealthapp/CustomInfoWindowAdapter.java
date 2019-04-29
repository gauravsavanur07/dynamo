package com.example.foodhealthapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    Context context;
    LayoutInflater inflater;

    public CustomInfoWindowAdapter(Context context) {
        this.context = context;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(R.layout.custom_info_window, null);

        TextView title = (TextView) v.findViewById(R.id.title);
        TextView subtitle = (TextView) v.findViewById(R.id.snippet);
        title.setText(marker.getTitle());
        subtitle.setText(marker.getSnippet());
        return v;
    }
}
