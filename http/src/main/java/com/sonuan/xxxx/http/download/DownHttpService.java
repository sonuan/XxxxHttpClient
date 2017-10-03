package com.sonuan.xxxx.http.download;

import com.sonuan.xxxx.http.http.IHttpListener;
import com.sonuan.xxxx.http.http.IHttpService;
import com.sonuan.xxxx.http.http.XHttpHeaders;
import com.sonuan.xxxx.http.http.XHttpParams;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * @author wusongyuan
 * @date 2017.10.03
 * @desc
 */

public class DownHttpService implements IHttpService {

    private String mUrl;
    private XHttpHeaders mHttpHeaders;
    private IHttpListener mHttpListener;
    private XHttpParams mHttpParams;
    private HttpGet mHttpGet;
    private HttpClient mHttpClient = new DefaultHttpClient();

    @Override
    public void setUrl(String url) {
        mUrl =url;
    }

    @Override
    public void excute() {
        try {
            mHttpGet = new HttpGet(mUrl);
            initHeaders();
            mHttpClient.execute(mHttpGet, new ResponseHandler<Object>() {
                @Override
                public Object handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
                    if (httpResponse.getStatusLine()
                            .getStatusCode() == 200) {
                        if (mHttpListener != null) {
                            mHttpListener.onSuccess(httpResponse.getEntity());
                        }
                    } else {
                        // TODO: 2017/10/3
                        if (mHttpListener != null) {
                            mHttpListener.onFailure();
                        }
                    }
                    return null;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initHeaders() {
        if (mHttpHeaders != null) {
            Map<String, String> headers = mHttpHeaders.getHeaders();
            if (headers != null) {
                Iterator<String> iterator = headers.keySet()
                        .iterator();
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    String value = headers.get(key);
                    if (mHttpGet != null) {
                        mHttpGet.addHeader(key, value);
                    }
                }
            }
        }

    }

    @Override
    public void setHttpListener(IHttpListener listener) {
        mHttpListener = listener;
    }

    @Override
    public void setHttpParams(XHttpParams httpParams) {
        mHttpParams = httpParams;
    }

    @Override
    public void setHttpHeaders(XHttpHeaders headers) {
        mHttpHeaders = headers;
    }

    @Override
    public void pause() {

    }

    @Override
    public boolean isPause() {
        return false;
    }

    @Override
    public boolean isCanceled() {
        return false;
    }

    @Override
    public boolean cancel() {
        return false;
    }
}
