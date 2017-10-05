package com.sonuan.xxxx.http.download;

import com.sonuan.xxxx.http.download.db.annotation.DbTable;
import com.sonuan.xxxx.http.http.HttpTask;

/**
 * @author wusongyuan
 * @date 2017.10.03
 * @desc
 */
@DbTable("down")
public class DownInfo implements Cloneable {

    private String url;
    private String filePath;
    private long currentLength;
    private long totalLength;
    private HttpTask httpTask;
    @DownloadStatus
    private String status;

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

    private String id;
    private String startTime;
    private String finishTime;
    private String displayName;
    private String userId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
