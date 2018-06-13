package com.example.inha.androidpj;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class LocationActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        long minTime = 1000;
        float minDistance = 1;

        mMap.setMapType(googleMap.MAP_TYPE_NORMAL);

        LocationManager locationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE) ;

        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                updateMap(location);
            }
            public void onStatusChanged(String provider, int stauts, Bundle extras) {
                alertStatus(provider);
            }
            public void onProviderEnabled(String provider) {
                alertProvider(provider);
            }
            public void onProviderDisabled(String provider) {
                checkProvider(provider);
            }
        };

        String strLocationProvider = LocationManager.NETWORK_PROVIDER;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            return;
        }
        locationManager.requestLocationUpdates(strLocationProvider, minTime, minDistance, locationListener);
        // Add a marker in Sydney and move the camera
    }

    public void updateMap(Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        final LatLng objLocation = new LatLng(latitude, longitude);

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(objLocation, 15));
        Marker objMK = mMap.addMarker(new MarkerOptions().position(objLocation).title("Current Position"));
        objMK.showInfoWindow();
    }

    public void checkProvider(String strProvider) {
        Toast.makeText(this, strProvider + "에 의한 turn off position service."
                + "Please Turn on position service....", Toast.LENGTH_SHORT).show();
        Intent objIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(objIntent);
    }

    public void alertProvider(String strProvider){
        Toast.makeText(this, strProvider + "Starting Position Service !", Toast.LENGTH_LONG).show();
    }

    public void alertStatus(String strProvider) {
        Toast.makeText(this, "Changing position service : " + strProvider, Toast.LENGTH_LONG).show();
    }

    public void onBtnFinishClicked(View view){
        finish();
    }
}
