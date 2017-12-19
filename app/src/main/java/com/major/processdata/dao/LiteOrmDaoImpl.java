package com.major.processdata.dao;

import android.content.Context;

import com.litesuits.orm.LiteOrm;

import java.util.ArrayList;

/**
 * TODO
 * Created by MEI on 2017/12/19.
 */

public class LiteOrmDaoImpl implements Dao {

    private LiteOrm mLiteOrm;

    public LiteOrmDaoImpl(Context context, String dbName) {
        mLiteOrm = LiteOrm.newSingleInstance(context, dbName);
    }

    @Override
    public <T> ArrayList<T> query(Class<T> clazz) {
        return mLiteOrm.query(clazz);
    }

    @Override
    public <T> long insert(T bean) {
        return mLiteOrm.insert(bean);
    }
}
