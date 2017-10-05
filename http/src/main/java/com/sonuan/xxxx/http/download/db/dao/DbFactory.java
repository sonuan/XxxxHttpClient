package com.sonuan.xxxx.http.download.db.dao;

import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;

/**
 * @author wusongyuan
 * @date 2017.09.28
 * @desc
 */

public class DbFactory {
    private String mDbPath;
    private SQLiteDatabase mSQLiteDatabase;
    private static DbFactory sIntance;

    private DbFactory() {
        mDbPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/xxxx/";
        File file = new File(mDbPath);
        if (!file.exists()) {
            file.mkdir();
        }
        mSQLiteDatabase = SQLiteDatabase.openOrCreateDatabase(mDbPath + "xxxxdown.db", null);
    }

    public static DbFactory getIntance() {
        if (sIntance == null) {
            synchronized (DbFactory.class) {
                if (sIntance == null) {
                    sIntance = new DbFactory();
                }
            }
        }
        return sIntance;
    }

    public <T extends BaseDao<M>, M> T create(Class<T> daoClazz, Class<M> dataClazz) {
        try {
            BaseDao dao = daoClazz.newInstance();
            dao.init(dataClazz, mSQLiteDatabase);
            return (T) dao;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
