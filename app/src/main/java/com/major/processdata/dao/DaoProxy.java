package com.major.processdata.dao;

import android.content.Context;

import java.util.ArrayList;

/**
 * TODO
 * Created by MEI on 2017/12/19.
 */

public class DaoProxy implements Dao {

    private Dao mDao;

    public DaoProxy(Context context, String dbName) {
//        mDao = new LiteOrmDaoImpl(context, dbName);
        mDao = new GreenDaoImpl(context, dbName);
    }

    @Override
    public <T> ArrayList<T> query(Class<T> clazz) {
        return mDao.query(clazz);
    }

    @Override
    public <T> long insert(T bean) {
        return mDao.insert(bean);
    }
}
