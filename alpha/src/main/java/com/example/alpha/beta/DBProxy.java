package com.example.alpha.beta;

import java.util.Collection;
import java.util.List;

/**
 * TODO
 * Created by MEI on 2018/2/28.
 */

public interface DBProxy {

    <T> List<T> query(Class<T> clazz, String[] projection, String selection, String[] selectionArgs, String sortOrder);

    <T> List<T> query(Class<T> clazz);

    <T> long insert(Class<T> clazz, Collection<T> collection);

    long insert(Object obj);

    <T> long update(Class<T> clazz, Collection<T> collection);

    int update(Object obj);

    <T> int delete(Class<T> clazz);

    int delete(Object obj);

}
