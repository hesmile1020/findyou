package com.paul.findyou;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.paul.findyou.location.LocationListenerManager;
import com.paul.findyou.location.MapViewLocationUpdateListener;
import com.paul.findyou.location.MapViewUpdateHandler;
import com.paul.findyou.map.MyMapActivity;


public class MainActivity extends ActionBarActivity {
    MapView mMapView;
    BaiduMap mBaiduMap;
    Handler viewUpdateHandler;//地图更新处理器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.show();


        actionBar.setBackgroundDrawable(new ColorDrawable(Color.argb(0xff, 0xcc, 0x99, 0x33)));
//        TextView title = (TextView) findViewById(android.R.id.title);
//        title.setGravity(Gravity.CENTER);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);

        mMapView = (MapView) findViewById(R.id.bmapView);
        mMapView.showZoomControls(false);
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMyLocationEnabled(true);

        try {
            viewUpdateHandler = new MapViewUpdateHandler(mBaiduMap);
            MapViewLocationUpdateListener listener = new MapViewLocationUpdateListener(viewUpdateHandler);
            LocationListenerManager.registerLocationListner(listener);

        } catch (Exception e) {
            //TODO
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.mapItem) {
            Intent intent = new Intent(this, MyMapActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;

        } else if (id == R.id.settingsItem) {

        }

        return super.onOptionsItemSelected(item);
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
        //mLocClient.stop();
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
        super.onDestroy();
    }
}
