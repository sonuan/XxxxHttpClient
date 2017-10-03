package com.sonuan.xxxx.http.download;

import android.os.Environment;

import com.sonuan.xxxx.http.http.HttpTask;
import com.sonuan.xxxx.http.http.IHttpListener;
import com.sonuan.xxxx.http.http.IHttpService;
import com.sonuan.xxxx.http.http.RequestHolder;

import java.io.File;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author wusongyuan
 * @date 2017.10.03
 * @desc
 */

public class DownloadManager {

    public static void down(String url, IDownCallback callback) {
        String[] preFixs = url.split("/");
        String lastFix = preFixs[preFixs.length - 1];
        File file = new File(Environment.getExternalStorageDirectory() + "/xxxx/download/", lastFix);
        DownInfo info = new DownInfo(url, file.getAbsolutePath());
        RequestHolder holder = new RequestHolder();
        IHttpService httpService = new DownHttpService();
        IHttpListener httpListener = new DownListener(info, httpService, callback);
        holder.setHttpService(httpService);
        holder.setIHttpListener(httpListener);
        holder.setUrl(url);
        HttpTask httpTask = new HttpTask(holder);
        Executor executor = Executors.newCachedThreadPool();
        executor.execute(httpTask);
    }
}
