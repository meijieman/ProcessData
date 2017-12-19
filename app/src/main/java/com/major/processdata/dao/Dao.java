package com.major.processdata.dao;

import java.util.ArrayList;

/**
 * TODO
 * Created by MEI on 2017/12/8.
 */

public interface Dao {

    <T> ArrayList<T> query(Class<T> clazz);

    <T> long insert(T bean);
}
