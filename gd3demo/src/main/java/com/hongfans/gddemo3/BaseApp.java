package com.hongfans.gddemo3;

import android.app.Application;

import com.hongfans.gddemo3.gen.DaoMaster;
import com.hongfans.gddemo3.gen.DaoSession;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseOpenHelper;

/**
 * Created by Administrator on 2017/3/3.
 */
public class BaseApp extends Application {

    public static final boolean ENCRYPTED = false;
    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        DatabaseOpenHelper helper;

        helper= new DaoMaster.DevOpenHelper(this, ENCRYPTED ? "notes-db_encrypted" : "notes-db");
//        helper = new MyOpenHelper(this, ENCRYPTED ? "notes-db_encrypted" : "notes-db");

        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }
}
