package com.paul.findyou;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Trace;
import android.util.Log;

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

        test();
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

    private void test(){
        Context context = this.getApplicationContext();

        String pname = context.getPackageName();
        try {
            Signature[] sigs = getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES).signatures;
            for (Signature sig : sigs) {
                System.out.println(sig);
                String sign = new String(sig.toByteArray(),"GBK");
                //Trace.i("MyApp", "Signature hashcode : " + sig.hashCode());
                String str1 = sig.toCharsString();
                String str2 = sig.toString();
                char[] str3 = sig.toChars();
                System.out.println(sign);
                Log.i("sign", sign);
            }

        }catch (Exception e){
            e.printStackTrace();;
        }
    }
}
