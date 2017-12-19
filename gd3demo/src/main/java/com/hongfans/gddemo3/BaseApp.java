package com.hongfans.gddemo3;

import android.app.Application;


import com.hongfans.gddemo3.dao.DaoMaster;
import com.hongfans.gddemo3.dao.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by Administrator on 2017/3/3.
 */
public class BaseApp extends Application {

    public static final boolean ENCRYPTED = false;
    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ? "notes-db_encrypted" : "notes-db");
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }
}
