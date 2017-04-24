package com.example.locationget.service;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.annotation.IntDef;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.locationget.MainActivity;

public class LocateService extends Service {

    public LocationClient mLocationClient;
    private String context = "";

    public LocateService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new MyLocationListener());
        requestLocation();
        /*Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                editor.putString("location", context);
                editor.apply();
            }
        };
        handler.postDelayed(runnable, 5000);*/
        return super.onStartCommand(intent, flags, startId);
    }

    private void requestLocation(){
        initLocation();
        mLocationClient.start();
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setScanSpan(5000);
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
    }

    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            StringBuilder currentPosition = new StringBuilder();
            currentPosition.append("经度：").append(location.getLongitude()).append("\n");
            currentPosition.append("纬度：").append(location.getLatitude()).append("\n");
            currentPosition.append("国家：").append(location.getCountry()).append("\n");
            currentPosition.append("省：").append(location.getProvince()).append("\n");
            currentPosition.append("市：").append(location.getCity()).append("\n");
            currentPosition.append("区：").append(location.getDistrict()).append("\n");
            currentPosition.append("街道：").append(location.getStreet()).append("\n");
            currentPosition.append("GPS时间：").append(location.getTime());
            context = currentPosition.toString();
            SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
            editor.putString("location", context);
            editor.apply();

        }

        @Override
        public void onConnectHotSpotMessage(String s, int i) {

        }
    }


    @Override
    public void onDestroy() {
        mLocationClient.stop();
        super.onDestroy();
    }
}
