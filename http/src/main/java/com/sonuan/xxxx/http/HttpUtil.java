package com.sonuan.xxxx.http;

import com.sonuan.xxxx.http.http.HttpTask;
import com.sonuan.xxxx.http.http.IHttpDataListener;
import com.sonuan.xxxx.http.http.IHttpListener;
import com.sonuan.xxxx.http.http.IHttpService;
import com.sonuan.xxxx.http.http.JsonHttpService;
import com.sonuan.xxxx.http.http.JsonParseListener;
import com.sonuan.xxxx.http.http.RequestHolder;
import com.sonuan.xxxx.http.http.XHttpParams;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author wusongyuan
 * @date 2017.10.01
 * @desc
 */

public class HttpUtil {

    public static  <T> void excute(String url,XHttpParams httpParams, IHttpDataListener<T> listener, Class<T> parseClazz) {
        RequestHolder requestHolder = new RequestHolder();
        requestHolder.setUrl(url);
        requestHolder.setHttpParams(httpParams);
        IHttpListener httpListener = new JsonParseListener<T>(parseClazz, listener);
        requestHolder.setIHttpListener(httpListener);
        IHttpService httpService = new JsonHttpService();
        requestHolder.setHttpService(httpService);
        HttpTask httpTask = new HttpTask(requestHolder);
        Executor executor = Executors.newCachedThreadPool();
        executor.execute(httpTask);
    }
}
