package com.sonuan.xxxx.http.download.db.dao;

/**
 * @author wusongyuan
 * @date 2017.09.28
 * @desc
 */

public interface IBaseDao<T> {

    void insert(T data);

    void delete(T where);

    void update(T data, T where);

}
