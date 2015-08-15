package com.paul.findyou.location;

import android.os.Handler;
import android.os.Message;

import com.baidu.location.BDLocation;

/**
 * Created by me on 2015/8/15.
 */
public class UpdateMapViewLocationListener implements ALocationListener{
    private Handler locationMessageHandler;

    public UpdateMapViewLocationListener(Handler locationMessageHandler){
        this.locationMessageHandler = locationMessageHandler;
    }
    @Override
    public void handleLocationReceived(BDLocation location){
        Message message = new Message();
        message.obj= location;

        locationMessageHandler.sendMessage(message);
    }

    @Override
    public String getLocationListenerName(){
        return "update_map_view_location_listener";
    }
}
