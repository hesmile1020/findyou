package com.paul.findyou;

import android.app.Application;

import com.baidu.location.LocationClient;

/**
 * Created by me on 2015/8/5.
 */
public class MainApplication extends Application {
    public LocationClient mLocationClient;
    @Override
    public void onCreate() {
        super.onCreate();
//        mLocationClient = new LocationClient(this.getApplicationContext());
//        mMyLocationListener = new MyLocationListener();
//        mLocationClient.registerLocationListener(mMyLocationListener);
//        mVibrator =(Vibrator)getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
    }

    private void initLocationService(){
        mLocationClient = new LocationClient(this.getApplicationContext());
    }
}
