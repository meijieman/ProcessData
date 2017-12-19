package com.hongfans.cpdemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * TODO
 * Created by MEI on 2017/12/5.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String TAG = "tag_dbhelper";

    public DBHelper(Context context) {
        super(context, "test.db", null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TestContract.TestEntry.TABLE_NAME + "("
                + TestContract.TestEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + TestContract.TestEntry.COLUMN_NAME + " TEXT NOT NULL);";
        Log.i(TAG, "sql " + sql);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "onUpgrade");
        db.execSQL("DROP TABLE IF EXISTS " + TestContract.TestEntry.TABLE_NAME);
        onCreate(db);
    }
}
