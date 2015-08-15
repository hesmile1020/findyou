package com.paul.findyou.location;

import com.baidu.location.BDLocation;

/**
 * Created by me on 2015/8/15.
 */
public interface ALocationListener {
    /**
     * 处理定位
     * @param location
     */
    public void handleLocationReceived(BDLocation location);

    /**
     * 获取定位监听器名称
     * @return
     */
    public String getLocationListenerName();
}
