package com.sonuan.xxxx.http.download;

import com.sonuan.xxxx.http.http.IHttpListener;
import com.sonuan.xxxx.http.http.IHttpService;

/**
 * @author wusongyuan
 * @date 2017.10.03
 * @desc
 */

public interface IDownListener extends IHttpListener {

    void setHttpService(IHttpService httpService);

    void cancel();

    void stop();

    void pause();
}
