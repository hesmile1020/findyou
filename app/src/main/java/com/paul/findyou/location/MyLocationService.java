package com.paul.findyou.location;

import com.baidu.location.LocationClient;
import com.paul.findyou.ApplicationContext;

import java.util.Timer;

/**
 * Created by me on 2015/8/7.
 */
public class MyLocationService {
    public static void initLocationService(){
        LocationClient mLocationClient = ApplicationContext.getLocationClient();

        //设置定位监听器
        LocationListenerManager locationListenerManager = LocationListenerManager.getLocationListenerManagerInstance();
        mLocationClient.registerLocationListener(locationListenerManager);
        try{
            locationListenerManager.registerLocationListner(new SyncUserLocationListener());

        }catch (Exception e){

        }

        //启动后台定位任务
        MyLocationTask locationTask = new MyLocationTask(mLocationClient);
        Timer timer = new Timer();
        timer.schedule(locationTask, 10000);
    }
}
