package com.sonuan.xxxx.http.http;

/**
 * @author wusongyuan
 * @date 2017.10.01
 * @desc
 */

public class HttpTask implements Runnable {
    private IHttpService mHttpService;

    public HttpTask(RequestHolder holder) {
        mHttpService = holder.getHttpService();
        mHttpService.setUrl(holder.getUrl());
        mHttpService.setHttpParams(holder.getHttpParams());
        mHttpService.setHttpListener(holder.getIHttpListener());
    }

    @Override
    public void run() {
        if (mHttpService != null) {
            mHttpService.excute();
        }
    }
}
