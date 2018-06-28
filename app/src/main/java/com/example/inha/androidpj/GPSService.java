package com.example.inha.androidpj;


import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class GPSService extends Service implements LocationListener {
    boolean isGPSEnable = false;
    boolean isNetworkEnable = false;
    double latitude, longitude;
    LocationManager locationManager;
    Location location;
    private Handler mHandler = new Handler();
    private Timer mTimer = null;
    long notify_interval = 1000;
    public static String str_receiver = "servicetutorial.service.receiver";
    Intent intent;

    public GPSService() {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mTimer = new Timer();
        mTimer.schedule(new TimerTaskToGetLocation(), 500, 1800000);
        intent = new Intent(str_receiver);

//        fn_getlocation();
    }

    @Override
    public void onLocationChanged(Location location) {

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

    private void fn_getlocation() {
        locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
        isGPSEnable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        isNetworkEnable = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.KOREA);
        String str_date = df.format(new Date());

        final Database db = new Database(getApplicationContext(), "Location3.db",null,1);

        if (!isGPSEnable && !isNetworkEnable) {

        } else {

            if (isNetworkEnable) {
                location = null;
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 500, 0, this);
                if (locationManager!=null){
                    location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    if (location!=null){

                        Log.e("latitude",location.getLatitude()+"");
                        Log.e("longitude",location.getLongitude()+"");

                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                        fn_update(location);
                        //db.insert(str_date+"",latitude+"", longitude+"");
                    }
                }

            }


            if (isGPSEnable){
                location = null;
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,500,0,this);
                if (locationManager!=null){
                    location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    if (location!=null){
                        Log.e("latitude",location.getLatitude()+"");
                        Log.e("longitude",location.getLongitude()+"");
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                        fn_update(location);
                        //db.insert(str_date+"",latitude+"", longitude+"");
/*
                        db.insert("2018-06-26 00:00","37.452018","126.660992","sleep");
                        db.insert("2018-06-26 00:30","37.452018","126.660992","sleep");
                        db.insert("2018-06-26 01:00","37.452018","126.660992","sleep");
                        db.insert("2018-06-26 01:30","37.452018","126.660992","sleep");
                        db.insert("2018-06-26 02:00","37.452018","126.660992","sleep");
                        db.insert("2018-06-26 02:30","37.452018","126.660992","sleep");
                        db.insert("2018-06-26 03:00","37.452018","126.660992","sleep");
                        db.insert("2018-06-26 03:30","37.452018","126.660992","sleep");
                        db.insert("2018-06-26 04:00","37.452018","126.660992","sleep");
                        db.insert("2018-06-26 04:30","37.452018","126.660992","sleep");
                        db.insert("2018-06-26 05:00","37.452018","126.660992","sleep");
                        db.insert("2018-06-26 05:30","37.452018","126.660992","sleep");
                        db.insert("2018-06-26 06:00","37.452018","126.660992","sleep");
                        db.insert("2018-06-26 06:30","37.452018","126.660992","sleep");
                        db.insert("2018-06-26 07:00","37.452018","126.660992","sleep");
                        db.insert("2018-06-26 07:30","37.452018","126.660992","sleep");
                        db.insert("2018-06-26 08:00","37.452018","126.660992","sleep");
                        db.insert("2018-06-26 08:30","37.452018","126.660992","sleep");
                        db.insert("2018-06-26 09:00","37.452018","126.660992","sleep");
                        db.insert("2018-06-26 09:30","37.452018","126.660992","sleep");
                        db.insert("2018-06-26 10:00","37.452018","126.660992","sleep");

                        db.insert("2018-06-26 10:30","37.448069","126.682863","move");
                        db.insert("2018-06-26 11:00","37.446729","126.709533","move");
                        db.insert("2018-06-26 11:30","37.466822","126.679406","move");
                        db.insert("2018-06-26 12:00","37.448722","126.657881","move");

                        db.insert("2018-06-26 12:30","37.448722","126.657881","stay");
                        db.insert("2018-06-26 12:00","37.448722","126.657881","stay");
                        db.insert("2018-06-26 13:30","37.448722","126.657881","stay");
                        db.insert("2018-06-26 13:00","37.448722","126.657881","stay");
                        db.insert("2018-06-26 14:30","37.448722","126.657881","stay");
                        db.insert("2018-06-26 14:00","37.448722","126.657881","stay");
                        db.insert("2018-06-26 15:30","37.448722","126.657881","stay");
                        db.insert("2018-06-26 15:00","37.448722","126.657881","stay");
                        db.insert("2018-06-26 16:30","37.448722","126.657881","stay");
                        db.insert("2018-06-26 16:00","37.448722","126.657881","stay");
                        db.insert("2018-06-26 17:30","37.448722","126.657881","stay");
                        db.insert("2018-06-26 17:00","37.448722","126.657881","stay");
                        db.insert("2018-06-26 18:30","37.448722","126.657881","stay");
                        db.insert("2018-06-26 18:00","37.448722","126.657881","stay");
                        db.insert("2018-06-26 19:30","37.448722","126.657881","stay");
                        db.insert("2018-06-26 19:00","37.448722","126.657881","stay");
                        db.insert("2018-06-26 20:30","37.448722","126.657881","stay");
                        db.insert("2018-06-26 20:00","37.448722","126.657881","stay");

                        db.insert("2018-06-26 20:30","37.448722","126.657881","move");
                        db.insert("2018-06-26 21:00","37.448722","126.657881","move");

                        db.insert("2018-06-26 21:30","37.452018","126.660992","slepp");
                        db.insert("2018-06-26 22:00","37.452018","126.660992","slepp");
                        db.insert("2018-06-26 22:30","37.452018","126.660992","slepp");
                        db.insert("2018-06-26 23:00","37.452018","126.660992","slepp");
                        db.insert("2018-06-26 23:30","37.452018","126.660992","slepp");
                        db.insert("2018-06-26 23:59","37.452018","126.660992","slepp");

                        //

                        db.insert("2018-06-27 00:00","37.452018","126.660992","sleep");
                        db.insert("2018-06-27 00:30","37.452018","126.660992","sleep");
                        db.insert("2018-06-27 01:00","37.452018","126.660992","sleep");
                        db.insert("2018-06-27 01:30","37.452018","126.660992","sleep");
                        db.insert("2018-06-27 02:00","37.452018","126.660992","sleep");
                        db.insert("2018-06-27 02:30","37.452018","126.660992","sleep");
                        db.insert("2018-06-27 03:00","37.452018","126.660992","sleep");
                        db.insert("2018-06-27 03:30","37.452018","126.660992","sleep");
                        db.insert("2018-06-27 04:00","37.452018","126.660992","sleep");
                        db.insert("2018-06-27 04:30","37.452018","126.660992","sleep");
                        db.insert("2018-06-27 05:00","37.452018","126.660992","sleep");
                        db.insert("2018-06-27 05:30","37.452018","126.660992","sleep");
                        db.insert("2018-06-27 06:00","37.452018","126.660992","sleep");
                        db.insert("2018-06-27 06:30","37.452018","126.660992","sleep");
                        db.insert("2018-06-27 07:00","37.452018","126.660992","sleep");
                        db.insert("2018-06-27 07:30","37.452018","126.660992","sleep");
                        db.insert("2018-06-27 08:00","37.452018","126.660992","sleep");
                        db.insert("2018-06-27 08:30","37.452018","126.660992","move");
                        db.insert("2018-06-27 09:00","37.452018","126.660992","move");
                        db.insert("2018-06-27 09:30","37.452018","126.660992","move");
                        db.insert("2018-06-27 10:00","37.452018","126.660992","move");

                        db.insert("2018-06-27 10:30","37.448069","126.682863","move");
                        db.insert("2018-06-27 11:00","37.446729","126.709533","move");
                        db.insert("2018-06-27 11:30","37.466822","126.679406","move");
                        db.insert("2018-06-27 12:00","37.448722","126.657881","move");

                        db.insert("2018-06-27 12:30","37.448722","126.657881","stay");
                        db.insert("2018-06-27 12:00","37.448722","126.657881","stay");
                        db.insert("2018-06-27 13:30","37.448722","126.657881","stay");
                        db.insert("2018-06-27 13:00","37.448722","126.657881","stay");
                        db.insert("2018-06-27 14:30","37.448722","126.657881","stay");
                        db.insert("2018-06-27 14:00","37.448722","126.657881","stay");
                        db.insert("2018-06-27 15:30","37.448722","126.657881","stay");
                        db.insert("2018-06-27 15:00","37.448722","126.657881","stay");
                        db.insert("2018-06-27 16:30","37.448722","126.657881","stay");
                        db.insert("2018-06-27 16:00","37.448722","126.657881","stay");
                        db.insert("2018-06-27 17:30","37.448722","126.657881","stay");
                        db.insert("2018-06-27 17:00","37.448722","126.657881","stay");
                        db.insert("2018-06-27 18:30","37.448722","126.657881","stay");
                        db.insert("2018-06-27 18:00","37.448722","126.657881","stay");
                        db.insert("2018-06-27 19:30","37.448722","126.657881","stay");
                        db.insert("2018-06-27 19:00","37.448722","126.657881","stay");
                        db.insert("2018-06-27 20:30","37.448722","126.657881","stay");
                        db.insert("2018-06-27 20:00","37.448722","126.657881","stay");

                        db.insert("2018-06-27 20:30","37.448722","126.657881","move");
                        db.insert("2018-06-27 21:00","37.448722","126.657881","move");

                        db.insert("2018-06-27 21:30","37.452018","126.660992","move");
                        db.insert("2018-06-27 22:00","37.452018","126.660992","move");
                        db.insert("2018-06-27 22:30","37.452018","126.660992","move");
                        db.insert("2018-06-27 23:00","37.452018","126.660992","move");
                        db.insert("2018-06-27 23:30","37.452018","126.660992","slepp");
                        db.insert("2018-06-27 23:59","37.452018","126.660992","slepp");


*/
                    }
                }
            }
        }

    }

    private class TimerTaskToGetLocation extends TimerTask{
        @Override
        public void run() {

            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    fn_getlocation();
                }
            });

        }
    }

    private void fn_update(Location location){

        intent.putExtra("latutide",location.getLatitude()+"");
        intent.putExtra("longitude",location.getLongitude()+"");
        sendBroadcast(intent);
    }


}