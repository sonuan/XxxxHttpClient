package com.sonuan.xxxx.http.http;

/**
 * @author wusongyuan
 * @date 2017.09.30
 * @desc
 */

public interface IHttpService {
    void setUrl(String url);

    void excute();

    void setHttpListener(IHttpListener listener);

    void setHttpParams(XHttpParams httpParams);

    void setHttpHeaders(XHttpHeaders headers);

    void pause();

    boolean isPause();

    boolean isCanceled();

    boolean cancel();
}
