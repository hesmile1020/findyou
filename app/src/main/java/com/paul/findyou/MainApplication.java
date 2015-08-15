package com.paul.findyou;

import android.app.Application;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.paul.findyou.location.MyLocationService;


/**
 * Created by me on 2015/8/5.
 */
public class MainApplication extends Application {
    public LocationClient mLocationClient;
    @Override
    public void onCreate() {
        super.onCreate();

        SDKInitializer.initialize(this);

        initLocationService();
    }

    private void initLocationService(){
        mLocationClient = new LocationClient(this.getApplicationContext());
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);// 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        //option.setScanSpan(1000);
        mLocationClient.setLocOption(option);

        ApplicationContext.setLocationClient(mLocationClient);


        MyLocationService.initLocationService();
    }
}
