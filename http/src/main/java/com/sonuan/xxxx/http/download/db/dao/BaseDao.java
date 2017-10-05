package com.sonuan.xxxx.http.download.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import com.sonuan.xxxx.http.download.db.annotation.DbField;
import com.sonuan.xxxx.http.download.db.annotation.DbTable;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author wusongyuan
 * @date 2017.09.28
 * @desc
 */

public abstract class BaseDao<T> implements IBaseDao<T> {
    private static final String TAG = "BaseDao";
    SQLiteDatabase mSQLiteDatabase;
    String mTableName;
    Map<String, Field> mFieldHashMap;

    void init(Class<T> dataClazz, SQLiteDatabase database) {
        mSQLiteDatabase = database;
        if (dataClazz.getAnnotation(DbTable.class) != null) {
            mTableName = dataClazz.getAnnotation(DbTable.class)
                    .value();
        } else {
            mTableName = dataClazz.getSimpleName();
        }
        if (!mSQLiteDatabase.isOpen()) {
            return;
        }
        if (!TextUtils.isEmpty(getCreateTableSql())) {
            mSQLiteDatabase.execSQL(getCreateTableSql());
        }
        initFiled(dataClazz);
    }

    protected void initFiled(Class<T> data) {
        mFieldHashMap = new HashMap<>();
        String sql = "select * from " + mTableName + " limit 1, 0";
        Cursor cursor = null;
        try {
            cursor = mSQLiteDatabase.rawQuery(sql, null);
            if (cursor != null) {
                String[] columnNames = cursor.getColumnNames();
                if (columnNames != null) {
                    Field[] fields = data.getDeclaredFields();
                    for (Field field:fields) {
                        field.setAccessible(true);
                    }
                    for (String columnName:columnNames) {
                        Field columnField = null;
                        for (Field field:fields) {
                            String fieldName = null;
                            if (field.getAnnotation(DbField.class) != null) {
                                fieldName = field.getAnnotation(DbField.class)
                                        .value();
                            } else {
                                fieldName = field.getName();
                            }
                            if (columnName.equals(fieldName)) {
                                columnField = field;
                                break;
                            }
                        }
                        if (columnField != null) {
                            mFieldHashMap.put(columnName, columnField);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
                cursor = null;
            }
        }
        Log.i(TAG, "initFiled: " + mFieldHashMap.size());
    }

    @Override
    public void insert(T data) {
        HashMap<String, String> params = getValues(data);

        ContentValues contentValues = new ContentValues();
        Set keys = params.keySet();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            String value = params.get(key);
            contentValues.put(key, value);
        }
        mSQLiteDatabase.insert(mTableName, null, contentValues);
    }

    private HashMap<String, String> getValues(T data) {
        Log.i(TAG, "getValues: " + mFieldHashMap.size());
        HashMap<String, String> params = new HashMap<>();
        Iterator<Field> fieldIterator = mFieldHashMap.values().iterator();
        while (fieldIterator.hasNext()) {
            Field field = fieldIterator.next();
            String fieldName;
            if (field.getAnnotation(DbField.class) != null) {
                fieldName = field.getAnnotation(DbField.class)
                        .value();
            } else {
                fieldName = field.getName();
            }
            try {
                if (field.get(data) != null) {
                    params.put(fieldName, field.get(data)
                            .toString());
                }
                continue;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return params;
    }

    @Override
    public void delete(T where) {

    }

    @Override
    public void update(T data, T where) {

    }

    protected abstract String getCreateTableSql();
}
