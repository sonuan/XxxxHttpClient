package com.sonuan.xxxx.http.http;

/**
 * @author wusongyuan
 * @date 2017.09.30
 * @desc
 */

public interface IHttpDataListener<T> {
    void onSuccess(T data);

    void onFailure(String code);
}
