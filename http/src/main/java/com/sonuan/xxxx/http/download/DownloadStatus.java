package com.sonuan.xxxx.http.download;

/**
 * @author wusongyuan
 * @date 2017.10.03
 * @desc
 */

public @interface DownloadStatus {
    String WAITTING = "1";
    String STARTED = "2";
    String DOWNLOADING = "3";
    String PAUSE = "4";
    String FINISH = "5";
    String FAILURE = "6";
}
