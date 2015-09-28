package com.paul.mode.http;

import java.util.Arrays;
import java.util.Map;

/**
 * Created by me on 2015/8/14.
 */
public class ProtocalWrap {
    public static final String ARG_H_ENCODING_GZIP = "gzip";

    public static final String H_POST_COMPRESS = "Accept-Encoding";

    public static final String H_RESPONSE_COMPRESS = "Content-Encoding";

    public static final int COMPRESSION_MIN_SIZE = 2048;

    private Map<String, String> headerValues;

    private Map<String, String> getData;

    private byte[] postData;

    private byte[] headerData;

    // params
    private Map<String, String> params;

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public static final String H_RESPONSE_LOCATION = "Location";

    public static final int MAX_TOTAL_CONNECTIONS = 2;

    public static final int MAX_ROUTE_CONNECTIONS = 5;

    public static final long DEFAULT_CONNMANAGER_TIMEOUT = 15000;

    // ----------------------------- HttpClient
    public static final int DEFAULT_SO_TIMEOUT = 15000;

    public static final int DEFAULT_CONNECT_TIMEOUT = 15000;

    private String host;
    private int connectTimeout = DEFAULT_CONNECT_TIMEOUT;
    private int soTimeout = DEFAULT_SO_TIMEOUT;
    private long connectionManagerTimeout = DEFAULT_CONNMANAGER_TIMEOUT;
    private boolean reTry = true;
    private boolean canCompressPost = true;
    private boolean redir = false;
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
