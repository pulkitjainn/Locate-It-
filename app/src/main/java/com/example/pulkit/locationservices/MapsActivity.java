package com.example.pulkit.locationservices;

import android.*;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.identity.intents.Address;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    public static final String TAG = "MA";
    private GoogleMap mMap;

    double a = 0, b=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        Location location = new Location(LocationManager.NETWORK_PROVIDER);

        //locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 10, locationListener);

        //Intent i = getIntent();
        final String str = getIntent().getExtras().getString("add");


        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                if (str != null && !str.isEmpty()) {
                    try {
                        Geocoder coder = new Geocoder(getApplicationContext());
//                        List<Address> addressList;// = new ArrayList<>();
//                                addressList = (<Address> addressList)geoCoder.getFromLocationName(str, 1);


                        List<android.location.Address> addressList = coder.getFromLocationName(str,25);

                        if (addressList != null && addressList.size() > 0) {
                            double lat = addressList.get(0).getLatitude();
                            double lng = addressList.get(0).getLongitude();

                            LatLng loc = new LatLng(lat,lng);
                            mMap.addMarker(new MarkerOptions().position(loc).title(str.toString()));
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc,16));
                        }
                    } catch (Exception e) {
                        Toast.makeText(MapsActivity.this, "hghgbuygbubg", Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION}, 121);
        } else {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 10, locationListener);

        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//        LatLng delhi = new LatLng(,77.3);
//        mMap.addMarker(new MarkerOptions().position(delhi).title("DeLhi"));


//
//        LatLng myLoc = new LatLng(a,b);
//        mMap.addMarker(new MarkerOptions().position(myLoc).title("here I am"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(myLoc));

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==121){
            Toast.makeText(this, "Restaet app", Toast.LENGTH_SHORT).show();
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}






