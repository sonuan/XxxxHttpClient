package com.sonuan.xxxx.http.http;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.alibaba.fastjson.JSON;

import org.apache.http.HttpEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author wusongyuan
 * @date 2017.09.30
 * @desc
 */

public class JsonParseListener<T> implements IHttpListener {
    private static final String TAG = "JsonParseListener";
    Class<T> mClazz;
    IHttpDataListener<T> mHttpDataListener;
    Handler mHandler = new Handler(Looper.myLooper());
    public JsonParseListener(Class<T> clazz, IHttpDataListener<T> httpDataListener) {
        mClazz = clazz;
        mHttpDataListener = httpDataListener;
    }

    @Override
    public void onSuccess(HttpEntity entity) {
        InputStream inputStream = null;
        try {
            inputStream = entity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            line = sb.toString();
            Log.i(TAG, "onSuccess: " + line);
            final T t = JSON.parseObject(line, mClazz);
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    if (mHttpDataListener != null) {
                        mHttpDataListener.onSuccess(t);
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            if (mHttpDataListener != null) {
                mHttpDataListener.onFailure("failure");
            }
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                inputStream = null;
            }
        }

    }

    @Override
    public void onFailure() {
        if (mHttpDataListener != null) {
            mHttpDataListener.onFailure("error");
        }
    }
}
