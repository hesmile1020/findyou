package com.paul.mode.http;

import java.util.Arrays;
import java.util.Map;

/**
 * Created by me on 2015/8/14.
 */
public class ProtocalWrap {
    // ------------------------------- 数据请求/接收 协议
    // http请求的默认值
    public static final String ARG_H_ENCODING_GZIP = "gzip";

    // http请求的KEY
    public static final String H_POST_COMPRESS = "Accept-Encoding";

    // http返回的KEY
    public static final String H_RESPONSE_COMPRESS = "Content-Encoding";

    // 最小post 数据是启动压缩
    public static final int COMPRESSION_MIN_SIZE = 2048;

    // header 数据部分
    private Map<String, String> headerValues;

    // 处理好的参数数据： qt=xx&qid=xx
    private Map<String, String> getData;

    // post数据部分
    private byte[] postData;

    // 头部数据(非必须设置字段,一般用于post数据结构说明)
    private byte[] headerData;

    // params
    private Map<String, String> params;

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    // ------------------------------ 重定向协议
    public static final String H_RESPONSE_LOCATION = "Location";

    // ------------------------------ 连接池
    // 连接池里的最大连接数
    public static final int MAX_TOTAL_CONNECTIONS = 2;

    // 每个路由的默认最大连接数
    public static final int MAX_ROUTE_CONNECTIONS = 5;

    // 连接池超时时间
    public static final long DEFAULT_CONNMANAGER_TIMEOUT = 15000;

    // ----------------------------- HttpClient
    // 默认读取超时时间
    public static final int DEFAULT_SO_TIMEOUT = 15000;

    // 默认连接超时时间
    public static final int DEFAULT_CONNECT_TIMEOUT = 15000;

    // 连接主机，如同这个值没有设置将使用默认的主机
    private String host;
    // 连接超时
    private int connectTimeout = DEFAULT_CONNECT_TIMEOUT;
    // 读取超时(负数的时候不起作用)
    private int soTimeout = DEFAULT_SO_TIMEOUT;
    // 连接池阻塞时间
    private long connectionManagerTimeout = DEFAULT_CONNMANAGER_TIMEOUT;
    // 是否启动连接重试机制，默认是重试的。
    private boolean reTry = true;
    // 是否可以压缩post数据
    private boolean canCompressPost = true;
    // 是否自动重定向
    private boolean redir = false;
    // 重试次数
    private int retryCount;

    /**
     * @return the getData
     */
    public Map<String, String> getGetData() {
        return getData;
    }

    /**
     * @param getData
     *            the getData to set
     */
    public void setGetData(Map<String, String> getData) {
        this.getData = getData;
    }

    /**
     * @return the postData
     */
    public byte[] getPostData() {
        return postData;
    }

    /**
     * @param postData
     *            the postData to set
     */
    public void setPostData(byte[] postData) {
        this.postData = postData;
    }

    public Map<String, String> getHeaderValues() {
        return headerValues;
    }

    public void setHeaderValues(Map<String, String> headerValues) {
        this.headerValues = headerValues;
    }

    /**
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * @param host
     *            the host to set
     */
    public void setHost(String host) {
        this.host = host;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    /**
     * @return the soTimeout
     */
    public int getSoTimeout() {
        return soTimeout;
    }

    /**
     * @param soTimeout
     *            the soTimeout to set
     */
    public void setSoTimeout(int soTimeout) {
        this.soTimeout = soTimeout;
    }

    public long getConnectionManagerTimeout() {
        return connectionManagerTimeout;
    }

    public void setConnectionManagerTimeout(long connectionManagerTimeout) {
        this.connectionManagerTimeout = connectionManagerTimeout;
    }

    /**
     * @return the reTry
     */
    public boolean isReTry() {
        return reTry;
    }

    /**
     * @param reTry
     *            the reTry to set
     */
    public void setReTry(boolean reTry) {
        this.reTry = reTry;
    }

    public boolean isCanCompressPost() {
        return canCompressPost;
    }

    public void setCanCompressPost(boolean canCompressPost) {
        this.canCompressPost = canCompressPost;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public byte[] getHeaderData() {
        return headerData;
    }

    public void setHeaderData(byte[] headerData) {
        this.headerData = headerData;
    }

    @Override
    public String toString() {
        return "ProtocalWrap [headerValues=" + headerValues + ", getData=" + getData + ", postData="
                + Arrays.toString(postData) + ", params=" + params + ", host=" + host + ", connectTimeout="
                + connectTimeout + ", soTimeout=" + soTimeout + ", connectionManagerTimeout="
                + connectionManagerTimeout + ", reTry=" + reTry + ", canCompressPost=" + canCompressPost + ", redir="
                + redir + ",headerData=" + headerData + "]";
    }

}
