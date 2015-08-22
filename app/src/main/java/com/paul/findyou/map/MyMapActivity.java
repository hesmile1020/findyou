package com.paul.findyou.map;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.view.Menu;

import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.paul.findyou.R;
import com.paul.findyou.location.LocationListenerManager;
import com.paul.findyou.location.MapViewLocationUpdateListener;

public class MyMapActivity extends Activity {
    // 定位相关
    LocationClient mLocClient;

    MapView mMapView;
    BaiduMap mBaiduMap;

    boolean isFirstLoc = true;// 是否首次定位

    //当前位置更新处理器
    Handler viewUpdateHandler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            BDLocation location = (BDLocation)msg.obj;
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())//
                            // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(location.getLatitude())//
                    .longitude(location.getLongitude()).build();//
            mBaiduMap.setMyLocationData(locData);

            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
                mBaiduMap.animateMapStatus(u);
            }
        }
    } ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_map);

        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);

        try{
            MapViewLocationUpdateListener listener = new MapViewLocationUpdateListener(viewUpdateHandler);
            LocationListenerManager.registerLocationListner(listener);

        }catch (Exception e){
            //TODO
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_map, menu);
        return true;
    }

    @Override
    protected void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mMapView.onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        // 退出时销毁定位
        mLocClient.stop();
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
        super.onDestroy();
    }
}
