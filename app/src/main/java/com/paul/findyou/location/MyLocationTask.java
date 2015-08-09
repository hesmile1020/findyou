package com.paul.findyou.location;

import com.baidu.location.LocationClient;
import com.paul.findyou.ApplicationContext;

import java.util.TimerTask;

/**
 * Created by me on 2015/8/7.
 */
public class MyLocationTask extends TimerTask {
    private LocationClient mLocationClient;

    public MyLocationTask(LocationClient mLocationClient){
       this.mLocationClient = mLocationClient;
    }

    @Override
    public void run() {
        mLocationClient.start();
    }
}
