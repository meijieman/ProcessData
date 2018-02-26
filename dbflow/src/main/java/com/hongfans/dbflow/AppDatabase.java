package com.hongfans.dbflow;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * TODO
 * Created by MEI on 2018/2/25.
 */
@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION)
public class AppDatabase {

    public static final String NAME = "AppDatabase";
    public static final int VERSION = 1;
}
