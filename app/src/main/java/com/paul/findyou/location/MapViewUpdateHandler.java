package com.paul.findyou.location;

import android.content.Context;
import android.os.Message;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.paul.findyou.ApplicationContext;
import com.paul.findyou.R;

import android.os.Handler;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.Random;

/**
 * 地图更新器
 * Created by me on 2015/8/23.
 */
public class MapViewUpdateHandler extends Handler {
    //百度地图
    private BaiduMap mBaiduMap;
    //是否第一次定位
    private boolean isFirstLoc;
    private int count;

    public MapViewUpdateHandler(BaiduMap mBaiduMap) {
        this.mBaiduMap = mBaiduMap;
        this.isFirstLoc = true;
        this.count = 0;
    }

    @Override
    public void handleMessage(Message msg) {
        BDLocation location = (BDLocation) msg.obj;
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(location.getRadius())//
                .direction(100).latitude(location.getLatitude())//
                .longitude(location.getLongitude()).build();//
        mBaiduMap.setMyLocationData(locData);

        if (isFirstLoc) {
            isFirstLoc = false;

            MapStatus.Builder mapStatusBuilder = new MapStatus.Builder();
            MapStatus mapStatus = mapStatusBuilder.zoom(16f).target(latLng).build();
            MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mapStatus);

            mBaiduMap.animateMapStatus(mapStatusUpdate);
        }

        mBaiduMap.clear();

        ImageButton button = new ImageButton(ApplicationContext.getApplicationContext());
        button.setBackgroundResource(R.drawable.icon_mark);
        button.setImageResource(R.drawable.icon_mark);
        button.getRootView().setRotation(90);
        //button.setBackgroundResource(R.drawable.popup);
        Button button1 = new Button(ApplicationContext.getApplicationContext());
        button1.setText("定位：" + count);

        BitmapDescriptor bdA = BitmapDescriptorFactory
                .fromResource(R.drawable.sto);
        OverlayOptions ooA = new MarkerOptions().position(latLng).icon(bdA)
                .zIndex(9).draggable(true);
        Marker marker = (Marker) (mBaiduMap.addOverlay(ooA));
        marker.setRotate(90);


        //button.setText("定位："+count);
        //定义用于显示该InfoWindow的坐标点
        //创建InfoWindow , 传入 view， 地理坐标， y 轴偏移量
        InfoWindow mInfoWindow = new InfoWindow(button, latLng, -47);
        //显示InfoWindow
        //mBaiduMap.showInfoWindow(mInfoWindow);

        count++;
    }
}
