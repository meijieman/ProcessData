package com.major.processdata.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


import com.major.processdata.entity.ElegantBean;
import com.major.processdata.LogUtil;
import com.major.processdata.dao.gen.DaoMaster;
import com.major.processdata.dao.gen.DaoSession;
import com.major.processdata.dao.gen.ElegantBeanDao;

import java.util.ArrayList;

/**
 * TODO
 * Created by MEI on 2017/12/8.
 */

public class GreenDaoImpl implements Dao {

    private final DaoSession mDaoSession;

    public GreenDaoImpl(Context context, String dbName) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, dbName);
        SQLiteDatabase db = helper.getWritableDatabase();
        mDaoSession = new DaoMaster(db).newSession();
    }

    @Override
    public <T> ArrayList<T> query(Class<T> clazz) {
        if (ElegantBean.class.equals(clazz)) {

        }
        LogUtil.w("aaaa " + ElegantBean.class.equals(clazz));
        ElegantBeanDao mDao = mDaoSession.getElegantBeanDao();
        return (ArrayList<T>) mDao.queryBuilder().list();
    }

    @Override
    public <T> long insert(T bean) {
        if (ElegantBean.class.equals(bean.getClass())) {

        }
        ElegantBeanDao mDao = mDaoSession.getElegantBeanDao();
        return mDao.insert((ElegantBean) bean);
    }


}
