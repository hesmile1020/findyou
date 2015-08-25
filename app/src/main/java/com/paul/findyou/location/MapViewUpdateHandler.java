package com.paul.findyou.location;

import android.content.Context;
import android.os.Message;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.paul.findyou.R;

import android.os.Handler;
import android.widget.Button;

/**
 * 地图更新器
 * Created by me on 2015/8/23.
 */
public class MapViewUpdateHandler extends Handler {
    private Context applicatioNContext;
    //百度地图
    private BaiduMap mBaiduMap;
    //是否第一次定位
    private boolean isFirstLoc;

    public MapViewUpdateHandler(Context applicatioNContext, BaiduMap mBaiduMap) {
        this.applicatioNContext = applicatioNContext;
        this.mBaiduMap = mBaiduMap;
        this.isFirstLoc = true;
    }

    @Override
    public void handleMessage(Message msg) {
        BDLocation location = (BDLocation) msg.obj;
        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(location.getRadius())//
                .direction(100).latitude(location.getLatitude())//
                .longitude(location.getLongitude()).build();//
        mBaiduMap.setMyLocationData(locData);

        if (isFirstLoc) {
            isFirstLoc = false;

            MapStatus.Builder mapStatusBuilder = new MapStatus.Builder();
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            MapStatus mapStatus = mapStatusBuilder.zoom(16f).target(latLng).build();
            MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mapStatus);

            mBaiduMap.animateMapStatus(mapStatusUpdate);

            Button button = new Button(applicatioNContext);
            //button.setBackgroundResource(R.drawable.popup);
            //TODO
            button.setText("hello");
            //定义用于显示该InfoWindow的坐标点
            //创建InfoWindow , 传入 view， 地理坐标， y 轴偏移量
            InfoWindow mInfoWindow = new InfoWindow(button, latLng, -47);
            //显示InfoWindow
            mBaiduMap.showInfoWindow(mInfoWindow);
        }
    }
}
