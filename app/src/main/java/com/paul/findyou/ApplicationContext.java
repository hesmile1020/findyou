package com.paul.findyou;

import com.baidu.location.LocationClient;

/**
 * Created by me on 2015/8/7.
 */
public class ApplicationContext {
    private static LocationClient mLocationClient;

    public  static void setLocationClient(LocationClient mlocationClient){
        mLocationClient = mlocationClient;
    }

    public static LocationClient getLocationClient(){
        return mLocationClient;
    }
}
