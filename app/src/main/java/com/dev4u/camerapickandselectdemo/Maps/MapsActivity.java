package com.dev4u.camerapickandselectdemo.Maps;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.dev4u.camerapickandselectdemo.Camera.CameraActivity;
import com.dev4u.camerapickandselectdemo.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;


public class MapsActivity extends FragmentActivity implements
        OnMapReadyCallback, View.OnClickListener,
        GoogleMap.OnMyLocationButtonClickListener {

    private GoogleMap mMap;
    ArrayList<LocationObj> list = new ArrayList<>();
    private FloatingActionButton fab;
    private ImageView imgGetLocation;

    LocationManager locationManager;
    boolean GpsStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        initView();

        demoData();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        for (LocationObj l : list) {
            setMaket(l);
        }
        mMap.setOnInfoWindowCloseListener(new GoogleMap.OnInfoWindowCloseListener() {
            @Override
            public void onInfoWindowClose(Marker marker) {
                marker.hideInfoWindow();
            }
        });

    }

    void demoData() {
        list.add(new LocationObj(-34, 151));
        list.add(new LocationObj(-30, 160));
        list.add(new LocationObj(-35, 140));
        list.add(new LocationObj(-36, 100));
    }

    void setMaket(LocationObj obj) {
        LatLng sydney = new LatLng(obj.Latitude, obj.Longitude);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.setInfoWindowAdapter(new MapsAdapter(getApplicationContext()));

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            // Show rationale and request permission.
            Toast.makeText(this, "chua bang nhau", Toast.LENGTH_LONG).show();
        }

    }


    private void initView() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
        imgGetLocation = (ImageView) findViewById(R.id.imgGetLocation);
        imgGetLocation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
//                Snackbar.make(v, "abcxyz", Snackbar.LENGTH_LONG).setAction("Action", null)
//                        .setActionTextColor(Color.WHITE).show();
                startActivity(new Intent(this, CameraActivity.class));
                break;
            case R.id.imgGetLocation:
                Toast.makeText(this, "abcxyz", Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    public boolean onMyLocationButtonClick() {
        CheckGpsStatus();
        if (GpsStatus == true) {
            Toast.makeText(this, "Location Services Is Enabled", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Location Services Is Disabled", Toast.LENGTH_LONG).show();
        }
        return GpsStatus;
    }

    public void CheckGpsStatus() {
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        GpsStatus = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }
}
