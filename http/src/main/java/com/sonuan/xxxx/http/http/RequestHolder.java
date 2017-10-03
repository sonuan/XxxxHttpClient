package com.sonuan.xxxx.http.http;

/**
 * @author wusongyuan
 * @date 2017.10.01
 * @desc
 */

public class RequestHolder {
    private IHttpListener mIHttpListener;
    private String mUrl;
    private XHttpParams mHttpParams;
    private IHttpService mHttpService;

    public IHttpListener getIHttpListener() {
        return mIHttpListener;
    }

    public void setIHttpListener(IHttpListener IHttpListener) {
        mIHttpListener = IHttpListener;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public XHttpParams getHttpParams() {
        return mHttpParams;
    }

    public void setHttpParams(XHttpParams httpParams) {
        mHttpParams = httpParams;
    }

    public IHttpService getHttpService() {
        return mHttpService;
    }

    public void setHttpService(IHttpService httpService) {
        mHttpService = httpService;
    }
}
