package com.dev4u.camerapickandselectdemo.Maps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev4u.camerapickandselectdemo.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

/**
 * IDE: Android Studio
 * Created by Nguyen Trong Cong  - 2DEV4U.COM
 * Name packge: com.dev4u.camerapickandselectdemo.Maps
 * Name project: CameraPickAndSelectDemo
 * Date: 10/5/2016
 * Time: 10:49 PM
 */

public class MapsAdapter implements GoogleMap.InfoWindowAdapter {
    Context context;

    public MapsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        // Getting view from the layout file custom_info_window4
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.custom_info_window, null);
        // Getting the position from the marker
        LatLng latLng = marker.getPosition();
        // Getting reference to the TextView to set latitude
        TextView tvLat = (TextView) v.findViewById(R.id.tvLat);
        // Getting reference to the TextView to set longitude
        TextView tvLng = (TextView) v.findViewById(R.id.tvLng);

        ImageView imgMaker=(ImageView) v.findViewById(R.id.imgMaker) ;
//        imgMaker.setImageBitmap();
        // Setting the latitude
        tvLat.setText("Latitude:" + latLng.latitude);
        // Setting the longitude
        tvLng.setText("Longitude:" + latLng.longitude);
        return v;
    }
}
