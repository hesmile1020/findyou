package com.paul.findyou;

import android.content.Context;

import com.baidu.location.LocationClient;

/**
 * Created by me on 2015/8/7.
 */
public class ApplicationContext {
    public static Context APPLICATION_CONTEXT;

    public static void setApplicationContext(Context context){
        APPLICATION_CONTEXT = context;
    }

    public static Context getApplicationContext(){
        return APPLICATION_CONTEXT;
    }
}
