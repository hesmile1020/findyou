package com.paul.findyou.location;

import android.os.Handler;

import com.baidu.location.LocationClient;
import com.paul.findyou.ApplicationContext;

import java.util.TimerTask;

/**
 * Created by me on 2015/8/7.
 */
public class QueryNearCouriersTask extends TimerTask {
    private Handler locationMessageHandler;

    public QueryNearCouriersTask(Handler locationMessageHandler){
        this.locationMessageHandler = locationMessageHandler;
    }

    @Override
    public void run() {

    }
}
