package com.sonuan.xxxx.http.http;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author wusongyuan
 * @date 2017.10.03
 * @desc
 */

public class XHttpParams {
    private Map<String, String> mParams;
    public XHttpParams() {
        mParams = new HashMap<>();
    }

    public void put(String key, String value) {
        mParams.put(key, value);
    }

    public Map<String, String> getParams() {
        return mParams;
    }

    public List<NameValuePair> getPost() {
        List<NameValuePair> pairs = new ArrayList<>();
        Iterator<String> iterator = mParams.keySet()
                .iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            String value = mParams.get(key);
            pairs.add(new BasicNameValuePair(key, value));
        }
        return pairs;
    }
}
