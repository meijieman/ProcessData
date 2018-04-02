package com.hongfans.cpdemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hongfans.cpdemo.note.Note;

/**
 * TODO
 * Created by MEI on 2018/4/2.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, Note.Table.DATABASE_NAME, null, Note.Table.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + Note.Table.TABLE_NAME + " ( "
                        + Note.Table.NOTE_ID + " INTEGER PRIMARY KEY, "
                        + Note.Table.NOTE_CONTENT + " VARCHAR(128), "
                        + Note.Table.NOTE_PUB_DATE + " INTEGER"
                        + ");"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Note.Table.TABLE_NAME);

        onCreate(db);
    }
}
