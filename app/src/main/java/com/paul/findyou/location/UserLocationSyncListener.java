package com.paul.findyou.location;

import android.content.Context;
import android.os.Environment;

import com.baidu.location.BDLocation;
import com.paul.findyou.AppDefine;
import com.paul.findyou.ApplicationContext;
import com.paul.mode.http.HttpRequestService;
import com.paul.mode.http.ProtocalWrap;
import com.paul.mode.util.Util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2015/8/15.
 */
public class UserLocationSyncListener implements ALocationListener{
    @Override
    public void handleLocationReceived(BDLocation location){
        Map<String,String> params = buildPostParams(location);

        ProtocalWrap protocalWrap = new ProtocalWrap();
        protocalWrap.setHost(AppDefine.URL_POST_CURRNET_LOCATION);
        protocalWrap.setParams(params);

        HttpRequestService httpRequestService = new HttpRequestService(protocalWrap);

        try{
            //httpRequestService.doService();

            FileOutputStream fos = ApplicationContext.getApplicationContext().openFileOutput("locationdata", Context.MODE_APPEND);
            OutputStreamWriter fosWritter = new OutputStreamWriter(fos);
            BufferedWriter bufferedWriter = new BufferedWriter(fosWritter);

            bufferedWriter.newLine();
            bufferedWriter.write(params.toString());
            bufferedWriter.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public String getLocationListenerName(){
        return "synchronize_user_location_listener";
    }

    /**
     * 构建定位结果回传请求参数
     * @param location
     * @return
     */
    private Map<String,String> buildPostParams(BDLocation location){
        Map<String,String> params = new HashMap<String,String>();
        params.put("time", location.getTime());
        params.put("errorcode",String.valueOf(location.getLocType()));
        params.put("latitude",String.valueOf(location.getLatitude()));
        params.put("lontitude",String.valueOf(location.getLongitude()));
        params.put("radius",String.valueOf(location.getRadius()));

        if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
            params.put("speed",String.valueOf(location.getSpeed()));
            params.put("satellite",String.valueOf(location.getSatelliteNumber()));
            params.put("height",String.valueOf(location.getAltitude()));
            params.put("direction",String.valueOf(location.getDirection()));
            params.put("addr",location.getAddrStr());
            params.put("describe","gps定位成功");

        } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
            params.put("addr",location.getAddrStr());
            params.put("operationers",String.valueOf(location.getOperators()));
            params.put("addr",location.getAddrStr());
            params.put("addr",location.getAddrStr());
            params.put("describe","网络定位成功");

        }else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
            params.put("describe","离线定位成功，离线定位结果也是有效的");

        }else if (location.getLocType() == BDLocation.TypeServerError) {
            params.put("describe","服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");

        }else if (location.getLocType() == BDLocation.TypeNetWorkException) {
            params.put("describe","网络不同导致定位失败，请检查网络是否通畅");

        }else if (location.getLocType() == BDLocation.TypeCriteriaException) {
            params.put("describe","无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
        }

        return params;
    }
}
