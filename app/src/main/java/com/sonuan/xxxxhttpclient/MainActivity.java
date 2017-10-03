package com.sonuan.xxxxhttpclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.sonuan.xxxx.http.HttpUtil;
import com.sonuan.xxxx.http.download.DownInfo;
import com.sonuan.xxxx.http.download.DownloadManager;
import com.sonuan.xxxx.http.download.IDownCallback;
import com.sonuan.xxxx.http.http.IHttpDataListener;
import com.sonuan.xxxx.http.http.XHttpParams;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv_test_http).setOnClickListener(this);
        findViewById(R.id.tv_test_down).setOnClickListener(this);
    }

    public void testHttp(View view) {
        HttpUtil.excute("http://www.mocky.io/v2/59cfc5d70f00000f0193dc68", null, new IHttpDataListener<DataBean>() {
            @Override
            public void onSuccess(DataBean data) {
                Log.i(TAG, "onSuccess: " + data);
            }

            @Override
            public void onFailure(String code) {
                Log.i(TAG, "onFailure: ");
            }
        }, DataBean.class);
    }

    public void testMenuHttp() {
        XHttpParams params = new XHttpParams();
        params.put("menu", "红烧肉");
        params.put("key", "c4135f7446594889664ff09f8bfb8406");
        params.put("dtype", "json");
        params.put("pn", "1");
        params.put("rn", "10");
        HttpUtil.excute("http://apis.juhe.cn/cook/query", params, new IHttpDataListener<MenuBean>() {
            @Override
            public void onSuccess(MenuBean data) {
                Log.i(TAG, "onSuccess: testMenuHttp" + data.getResult()
                        .getData()
                        .size());
            }

            @Override
            public void onFailure(String code) {
                Log.i(TAG, "onFailure: testMenuHttp");
            }
        }, MenuBean.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_test_http:
                testMenuHttp();
                break;
            case R.id.tv_test_down:
                testDown();
                break;
        }
    }

    private void testDown() {
        Log.i(TAG, "testDown: ");
        DownloadManager.down("https://raw.githubusercontent.com/sonuan/hosts/master/hosts-files/hosts",
                new IDownCallback() {
                    @Override
                    public void onSuccess(DownInfo info) {
                        Log.i(TAG, "onSuccess: testDown");
                    }

                    @Override
                    public void onFailure(DownInfo info) {
                        Log.i(TAG, "onFailure: testDown");
                    }

                    @Override
                    public void onDownStatusChanged(DownInfo info) {
                        Log.i(TAG, "onDownStatusChanged: ");
                    }

                    @Override
                    public void onSizeChanged(DownInfo info, double progress, long speed) {
                        Log.i(TAG, "onSizeChanged: " + info.getCurrentLength() + " " + info.getTotalLength()+ " " + progress + " " + speed);
                    }
                });

    }
}
