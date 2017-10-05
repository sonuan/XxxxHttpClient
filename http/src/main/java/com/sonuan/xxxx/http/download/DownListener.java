package com.sonuan.xxxx.http.download;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.sonuan.xxxx.http.http.IHttpService;

import org.apache.http.HttpEntity;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author wusongyuan
 * @date 2017.10.03
 * @desc
 */

public class DownListener implements IDownListener {
    private static final String TAG = "DownListener";
    private DownInfo mDownInfo;
    private IHttpService mIHttpService;
    private IDownCallback mCallback;

    private String mUrl;
    private File mFile;
    private long mLastLength;
    Handler mHandler = new Handler(Looper.myLooper());

    public DownListener(DownInfo downInfo, IHttpService IHttpService, IDownCallback callback) {
        mDownInfo = downInfo;
        mIHttpService = IHttpService;
        mCallback = callback;
        mFile = new File(downInfo.getFilePath());
        if (mFile.exists()) {
            // FIXME: 2017/10/4
            mFile.delete();
            mLastLength = 0;
        }
    }

    @Override
    public void onSuccess(HttpEntity entity) {
        InputStream inputStream = null;
        try {
            inputStream = entity.getContent();
        } catch (IOException e) {
            e.printStackTrace();
        }

        long startTime = System.currentTimeMillis();
        long speed = 0L;
        long useTime = 0L;
        long getLength = 0L;
        long receiveLength = 0L;
        boolean bufferLen = false;
        long dataLength = entity.getContentLength();
        long caluSpeedLen = 0L;
        long totalLength = mLastLength + dataLength;
        Log.i(TAG, "DownListener: " + mLastLength + " " + dataLength + " " + totalLength);

        downloadStatusChanged(DownloadStatus.DOWNLOADING);
        int count = 0;
        long currentTime;


        byte[] buffer = new byte[1024];
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File parentFile = mFile.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        try {
            fos = new FileOutputStream(mFile, true);
            bos = new BufferedOutputStream(fos);
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                if (mIHttpService.isCanceled()) {
                    downloadFailure(1, "用户取消");
                    return;
                }
                if (mIHttpService.isPause()) {
                    downloadFailure(2, "用户暂停");
                    return;
                }
                bos.write(buffer, 0, length);
                ++count;
                getLength += length;
                receiveLength += length;
                caluSpeedLen += length;
                if (receiveLength * 100L / totalLength > 1L || count >= 5000) {
                    currentTime = System.currentTimeMillis();
                    useTime = currentTime - startTime;
                    if (useTime == 0) {
                        continue;
                    }
                    startTime = currentTime;
                    speed = caluSpeedLen * 1000L / useTime;
                    count = 0;
                    caluSpeedLen = 0L;
                    receiveLength = 0L;
                    downloadSizeChanged(mLastLength + getLength, totalLength, speed);
                }
            }
            if (dataLength != getLength) {
                downloadFailure(3, "下载长度不一致");
            }else {
                downloadSuccessed();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bos != null) {
                    bos.flush();
                    bos.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void downloadFailure(final int code, final String text) {
        if (mCallback != null) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mCallback.onFailure(mDownInfo.clone(), code, text);
                }
            });
        }
    }

    private void downloadSuccessed() {
        if (mCallback != null) {
            final DownInfo copy = mDownInfo.clone();
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mCallback.onSuccess(copy);
                }
            });
        }
    }

    private void downloadSizeChanged(final long currentLength, final long totalLength, final long speed) {
        mDownInfo.setCurrentLength(currentLength);
        mDownInfo.setTotalLength(totalLength);
        if (mCallback != null) {
            final DownInfo copy = mDownInfo.clone();
            if (copy != null) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mCallback.onSizeChanged(copy, currentLength * 1.0 / totalLength, speed);
                    }
                });
            }
        }
    }

    private void downloadStatusChanged(@DownloadStatus String status) {
        mDownInfo.setStatus(status);
        if (mCallback != null) {
            final DownInfo copy = mDownInfo.clone();
            if (copy != null) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mCallback.onDownStatusChanged(copy);
                    }
                });
            }
        }

    }

    @Override
    public void onFailure() {
        if (mCallback != null) {
            mCallback.onFailure(mDownInfo);
        }
    }

    @Override
    public void setHttpService(IHttpService httpService) {

    }

    @Override
    public void cancel() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void pause() {

    }
}
