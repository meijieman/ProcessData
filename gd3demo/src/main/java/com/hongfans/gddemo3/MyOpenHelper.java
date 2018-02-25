package com.hongfans.gddemo3;

import android.content.Context;

import com.github.yuweiguocn.library.greendao.MigrationHelper;
import com.hongfans.gddemo3.gen.DailyNoteDao;
import com.hongfans.gddemo3.gen.DaoMaster;
import com.hongfans.gddemo3.gen.NoteDao;

import org.greenrobot.greendao.database.Database;

/**
 * 自定义升级操作
 * Created by MEI on 2017/12/26.
 */

public class MyOpenHelper extends DaoMaster.OpenHelper {

    public MyOpenHelper(Context context, String name) {
        super(context, name);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
//        super.onUpgrade(db, oldVersion, newVersion);

        MigrationHelper.migrate(db, NoteDao.class, DailyNoteDao.class);

    }
}
