package com.example.alpha.beta;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * TODO
 * Created by MEI on 2018/2/28.
 */

public class DBProxyImpl implements DBProxy {

    private final ContentResolver mContentResolver;

    public DBProxyImpl(Context context) {
        mContentResolver = context.getContentResolver();
    }


    //　根据 Cursor 转化出不同的对象集合
    private <T> List<T> transform(Class<T> clazz, Cursor cursor) {
        List<T> list = new ArrayList<>();
        try {
            while (cursor.moveToNext()) {
                T t = getObject(clazz, cursor);
                list.add(t);
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return list;
    }

    // 根据一条记录生成一个对象，可以优化为注解
    private <T> T getObject(Class<T> clazz, Cursor cursor) throws IllegalAccessException, InstantiationException {
        Object obj = clazz.newInstance();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.set(obj, cursor.getColumnIndex(field.getName()));
        }

        T t = (T) obj;
        return t;
    }

    @Override
    public List query(Class clazz) {
        return query(clazz, null, null, null, null);
    }

    @Override
    public <T> List<T> query(Class<T> clazz, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        // FIXME: 2018/2/28
        Uri uri = getUri(clazz);
        Cursor cursor = mContentResolver.query(uri, projection, selection, selectionArgs, sortOrder);
        List<T> list = Collections.emptyList();
        if (cursor != null) {
            list = transform(clazz, cursor);
            cursor.close();
        }

        return list;
    }

    private <T> Uri getUri(Class<T> clazz) {
        return Uri.parse(clazz.getSimpleName());
    }

    @Override
    public <T> long insert(Class<T> clazz, Collection<T> collection) {
        Uri uri = getUri(clazz);
        Iterator<T> iterator = collection.iterator();
        Field[] fields = clazz.getFields();

        ContentValues cv = new ContentValues();
        try {
            while (iterator.hasNext()) {
                T next = iterator.next();
                for (Field field : fields) {
                    Object o = field.get(next);
                    if (o instanceof Integer) {
                        cv.put(field.getName(), (int) o);
                    } else {
                        // FIXME: 2018/2/28 定义各种类型
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Uri insert = mContentResolver.insert(uri, cv);
        //fixme 通过 uri 获取到行号
        return 0;
    }

    @Override
    public long insert(Object obj) {

        return 0;
    }

    @Override
    public <T> long update(Class<T> clazz, Collection<T> collection) {
        Uri uri = getUri(clazz);
        ContentValues cv = new ContentValues();

        int update = mContentResolver.update(uri, cv, null, null);

        return update;
    }

    @Override
    public int update(Object obj) {
        return 0;
    }

    @Override
    public <T> int delete(Class<T> clazz) {
        int delete = mContentResolver.delete(getUri(clazz), null, null);
        return delete;
    }

    @Override
    public int delete(Object obj) {
        // 删表操作

        return 0;
    }
}
