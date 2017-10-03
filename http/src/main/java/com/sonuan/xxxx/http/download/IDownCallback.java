package com.sonuan.xxxx.http.download;

/**
 * @author wusongyuan
 * @date 2017.10.03
 * @desc
 */

public interface IDownCallback {

    void onSuccess(DownInfo info);

    void onFailure(DownInfo info);

    void onDownStatusChanged(DownInfo info);

    void onSizeChanged(DownInfo info, double progress, long speed);
}
