package com.sonuan.xxxx.http.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;

import java.io.IOException;

/**
 * @author wusongyuan
 * @date 2017.09.30
 * @desc
 */

public class JsonHttpService implements IHttpService {
    private IHttpListener mIHttpListener;
    private String mUrl;
    private XHttpParams mHttpParams;
    private HttpClient mHttpClient;
    private HttpPost mHttpPost;
    public JsonHttpService() {
        mHttpClient = new DefaultHttpClient();

    }

    @Override
    public void setUrl(String url) {
        mUrl = url;
    }

    @Override
    public void excute() {
        mHttpPost = new HttpPost(mUrl);
        try {
            if (mHttpParams != null) {
                mHttpPost.setEntity(new UrlEncodedFormEntity(mHttpParams.getPost(), HTTP.UTF_8));
            }
            mHttpClient.execute(mHttpPost, new ResponseHandler() {
                @Override
                public Object handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
                    int code = httpResponse.getStatusLine().getStatusCode();
                    if (code == 200) {
                        if (mIHttpListener != null) {
                            mIHttpListener.onSuccess(httpResponse.getEntity());
                        }
                    } else {
                        if (mIHttpListener != null) {
                            mIHttpListener.onFailure();
                        }
                    }
                    return httpResponse;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            if (mIHttpListener != null) {
                mIHttpListener.onFailure();
            }
        }
    }

    @Override
    public void setHttpListener(IHttpListener listener) {
        mIHttpListener = listener;
    }

    @Override
    public void setHttpParams(XHttpParams httpParams) {
        mHttpParams = httpParams;
    }

    @Override
    public void setHttpHeaders(XHttpHeaders headers) {

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
