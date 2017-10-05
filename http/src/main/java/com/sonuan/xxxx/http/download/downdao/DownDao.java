package com.sonuan.xxxx.http.download.downdao;

import com.sonuan.xxxx.http.download.DownInfo;
import com.sonuan.xxxx.http.download.db.dao.BaseDao;

/**
 * @author wusongyuan
 * @date 2017.10.05
 * @desc
 */

public class DownDao extends BaseDao<DownInfo> {

    @Override
    protected String getCreateTableSql() {
        return null;
    }
}
