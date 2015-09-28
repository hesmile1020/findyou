package com.paul.mode.http;

import com.paul.mode.util.Util;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by me on 2015/8/14.
 */
@SuppressWarnings("deprecation")
public class HttpRequestService {
    private ProtocalWrap protocal;

    public HttpRequestService(ProtocalWrap protocal) {
        this.protocal = protocal;
    }

    public byte[] doService() throws Exception {
        byte[] result = null;
        DefaultHttpClient httpClient = null;
        try {
            httpClient = new DefaultHttpClient();

            if (protocal.getRetryCount() > 0) {
                httpClient.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(protocal.getRetryCount(),
                        false));
            }

            HttpRequestBase httpRequest = createRequest();

            result = execute(httpClient, httpRequest);

        }catch (Exception e) {
            throw e;

        } finally {
            if (httpClient != null) {
                httpClient.getConnectionManager().shutdown();
            }
        }

        return result;
    }

    /**
     *
     * @throws java.io.UnsupportedEncodingException
     *
     */
    private HttpRequestBase createRequest() throws UnsupportedEncodingException {
        HttpRequestBase httpRequest = null;
        if (protocal.getPostData() != null) {
            httpRequest = new HttpPost(protocal.getHost());
            byte[] sendData = protocal.getPostData();

            if (protocal.isCanCompressPost() && sendData.length > protocal.COMPRESSION_MIN_SIZE) {
                // post���ѹ��
                sendData = gzipWrapPostData(sendData);
                if (sendData == null) {
                    sendData = protocal.getPostData();

                } else {
                    httpRequest.setHeader(ProtocalWrap.H_POST_COMPRESS, ProtocalWrap.ARG_H_ENCODING_GZIP);
                }
            }

            // ��ȡ��ݰ�ͷ��Ϣ
            byte[] headerData = protocal.getHeaderData();
            if (headerData != null) {
                sendData = Util.byteMerge(headerData, sendData);
            }

            ((HttpPost) httpRequest).setEntity(new ByteArrayEntity(sendData));

        } else if (protocal.getParams() != null && protocal.getParams().size() > 0) {
            httpRequest = new HttpPost(protocal.getHost());
            List<NameValuePair> requestDatas = new ArrayList<NameValuePair>();
            Map<String, String> params = protocal.getParams();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                requestDatas.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));

            }

            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(requestDatas, "UTF-8");
            ((HttpPost) httpRequest).setEntity(entity);

        } else {
            httpRequest = new HttpGet(protocal.getHost());

        }

        return httpRequest;
    }

    protected  byte[] execute(DefaultHttpClient httpClient, HttpRequestBase httpRequest) throws Exception{
        byte[] result = null;
        HttpResponse httpResponse = httpClient.execute(httpRequest);
        int httpCode = httpResponse.getStatusLine().getStatusCode();
        if (httpCode == HttpURLConnection.HTTP_OK) {
            Header compressHeader = httpResponse.getFirstHeader(ProtocalWrap.H_RESPONSE_COMPRESS);
            if (compressHeader != null && compressHeader.getValue().equals(ProtocalWrap.ARG_H_ENCODING_GZIP)) {
                result = handleReponse(httpResponse, true);

            } else {
                result = handleReponse(httpResponse, false);

            }

        } else {
            throw new Exception("请求失败：" + httpCode);

        }

        return result;
    }

    protected byte[] handleReponse(HttpResponse response, boolean gzip) throws IOException {
        InputStream is = null;
        HttpEntity entity = response.getEntity();
        ByteArrayOutputStream bab = new ByteArrayOutputStream();
        if (entity != null) {
            if (gzip) {
                is = new BufferedInputStream(new GZIPInputStream(entity.getContent(), 4096), 8192);
            } else {
                is = new BufferedInputStream(entity.getContent(), 8192);
            }
            byte[] datas = new byte[8192];
            int count = -1;
            while ((count = is.read(datas, 0, datas.length)) != -1) {
                bab.write(datas, 0, count);
            }
        }

        return bab.toByteArray();
    }

    private byte[] gzipWrapPostData(byte[] postData) {
        GZIPOutputStream gos = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            gos = new GZIPOutputStream(bos);
            gos.write(postData);
            gos.flush();
            gos.finish();

            return bos.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                gos.close();
            } catch (Exception e2) {
            }
        }

        return null;
    }
}
