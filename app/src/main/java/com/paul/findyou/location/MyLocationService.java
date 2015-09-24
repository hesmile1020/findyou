package com.paul.findyou.location;

import android.content.Context;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.paul.findyou.ApplicationContext;

import java.util.Timer;

/**
 * Created by me on 2015/8/7.
 */
public class MyLocationService {
    private static LocationClient mLocationClient;
    /**是否已经初始化*/
    private static boolean isInit = false;

    public static void initLocationService(Context context) throws Exception {
        if(isInit){
            throw new Exception("my location service has been inited!");
        }

        initLocationClient(context);

        initLocationListener();

        mLocationClient.start();
        isInit = true;
    }

    private static void initLocationClient(Context context){
        mLocationClient = new LocationClient(context);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);// ��gps
        option.setCoorType("bd09ll"); // �����������
        option.setScanSpan(5000);
        mLocationClient.setLocOption(option);
    }

    private static void initLocationListener(){
        //设置定位监听器
        LocationListenerManager locationListenerManager = LocationListenerManager.getLocationListenerManagerInstance();
        mLocationClient.registerLocationListener(locationListenerManager);
        try{
            locationListenerManager.registerLocationListner(new UserLocationSyncListener());

        }catch (Exception e){

        }
    }
}
