package com.sonuan.xxxx.http.http;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wusongyuan
 * @date 2017.10.03
 * @desc
 */

public class XHttpHeaders {
    private Map<String, String> mHeaders;
    public XHttpHeaders() {
        mHeaders = new HashMap<>();
    }

    public void put(String key, String value) {
        mHeaders.put(key, value);
    }

    public Map<String, String> getHeaders() {
        return mHeaders;
    }

    //public List<NameValuePair> getPost() {
    //    List<NameValuePair> pairs = new ArrayList<>();
    //    Iterator<String> iterator = mHeaders.keySet()
    //            .iterator();
    //    while (iterator.hasNext()) {
    //        String key = iterator.next();
    //        String value = mHeaders.get(key);
    //        pairs.add(new BasicNameValuePair(key, value));
    //    }
    //    return pairs;
    //}
}
