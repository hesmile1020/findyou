package com.paul.findyou.util;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.List;

/**
 * Created by me on 2015/8/12.
 */
public class HttpUtil {
    public static void postRequest(String url, Map<String, String> params) throws  Exception{
        if(params == null){
            throw new Exception("params should not be null!");
        }

        List<BasicNameValuePair> psotParams = new LinkedList<BasicNameValuePair>();
        Set<String> keys = params.keySet();
        for(String key : keys){
            psotParams.add(new BasicNameValuePair(key, params.get(key)));
        }

        HttpPost postMethod = new HttpPost(url);
        postMethod.setEntity(new UrlEncodedFormEntity(params, "utf-8")); //将参数填入POST Entity中

        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse response = httpClient.execute(postMethod);
    }

    //参考http://ipjmc.iteye.com/blog/1577495
}
