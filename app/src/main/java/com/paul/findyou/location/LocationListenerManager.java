package com.paul.findyou.location;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2015/8/15.
 */
public class LocationListenerManager implements BDLocationListener {
    private static final LocationListenerManager instance = new LocationListenerManager();

    private  Map<String,ALocationListener> listenerMaps;

    public LocationListenerManager(){
        listenerMaps = new HashMap<String,ALocationListener>();
    }

    public static  void registerLocationListner(ALocationListener locationListener) throws Exception {
        if (locationListener != null && locationListener.getLocationListenerName() != null) {
            String listenerName = locationListener.getLocationListenerName();
            if (!instance.listenerMaps.containsKey(listenerName)) {
                instance.listenerMaps.put(listenerName, locationListener);

            } else {
                throw new Exception("listenername has already be registered!");
            }

        } else {
            throw new Exception("invalid location listener!");
        }
    }

    public static LocationListenerManager getLocationListenerManagerInstance(){
        return instance;
    }

    @Override
    public void onReceiveLocation(BDLocation location) {
        if(!listenerMaps.isEmpty()){
            for(ALocationListener locationListener : listenerMaps.values()){
                locationListener.handleLocationReceived(location);
            }
        }
    }
}
