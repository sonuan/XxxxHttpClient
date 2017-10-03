package com.sonuan.xxxx.http.http;

import org.apache.http.HttpEntity;

/**
 * @author wusongyuan
 * @date 2017.09.30
 * @desc
 */

public interface IHttpListener {

    void onSuccess(HttpEntity entity);

    void onFailure();
}
