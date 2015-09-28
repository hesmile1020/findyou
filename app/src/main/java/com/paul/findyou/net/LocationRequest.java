package com.paul.findyou.net;

import com.paul.findyou.AppDefine;
import com.paul.mode.http.HttpRequestService;
import com.paul.mode.http.ProtocalWrap;

import java.util.List;
import java.util.Map;

/**
 * Created by me on 2015/8/13.
 */
public class LocationRequest {

    /**
     * 发送当前位置
     * @param params
     * @return
     * @throws Exception
     */
    public boolean postCurrentLocation(Map<String,String> params) throws Exception{
        ProtocalWrap protocalWrap = new ProtocalWrap();
        protocalWrap.setHost(AppDefine.URL_POST_CURRNET_LOCATION);
        protocalWrap.setParams(params);

        HttpRequestService httpRequestService = new HttpRequestService(protocalWrap);

        httpRequestService.doService();

        return true;
    }

    /**
     * 查询附近快递员
     * @return
     */
    public List<Object> queryNearCouriers(){

        return null;
    }
}
