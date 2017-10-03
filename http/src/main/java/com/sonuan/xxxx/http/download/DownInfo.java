package com.sonuan.xxxx.http.download;

import com.sonuan.xxxx.http.http.HttpTask;

/**
 * @author wusongyuan
 * @date 2017.10.03
 * @desc
 */

public class DownInfo implements Cloneable {

    private String url;
    private String filePath;
    private long currentLength;
    private long totalLength;
    private HttpTask httpTask;
    private
    @DownloadStatus
    String status;

    public DownInfo(String url, String filePath) {
        this.url = url;
        this.filePath = filePath;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public long getCurrentLength() {
        return currentLength;
    }

    public void setCurrentLength(long currentLength) {
        this.currentLength = currentLength;
    }

    public long getTotalLength() {
        return totalLength;
    }

    public void setTotalLength(long totalLength) {
        this.totalLength = totalLength;
    }

    public HttpTask getHttpTask() {
        return httpTask;
    }

    public void setHttpTask(HttpTask httpTask) {
        this.httpTask = httpTask;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    protected DownInfo clone(){
        try {

            DownInfo info = (DownInfo) super.clone();
            if (info != null) {
                info.setTotalLength(totalLength);
                info.setCurrentLength(currentLength);
                info.setUrl(url);
                info.setFilePath(filePath);
                info.setStatus(status);
                info.setHttpTask(httpTask);
            }
            return info;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return null;
    }
}
