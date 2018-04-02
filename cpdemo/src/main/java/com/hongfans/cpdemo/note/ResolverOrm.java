package com.hongfans.cpdemo.note;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.hongfans.common.log.SL;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 * Created by MEI on 2018/4/2.
 */

public class ResolverOrm {

    private Context mContext;

    public ResolverOrm(Context context) {
        mContext = context.getApplicationContext();
    }

    public int insert() {
        return 0;
    }

    public int delete() {

        return 0;
    }


    public int update() {
        return 0;
    }


    public List<Note> query() {
        List<Note> list = new ArrayList<>();
        String sortOrder = null;
        Uri uri = Note.Table.CONTENT_URI;
        String[] projection = null;
        String selection = null;
        String[] selectionArgs = null;

        Cursor query = mContext.getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);
        if (query != null) {
            Note note;
            while (query.moveToNext()) {
                note = new Note();
                note.set_id(query.getInt(0));
                note.setContent(query.getString(1));
                note.setPubDate(query.getLong(2));
                list.add(note);
            }
            query.close();
        }

        SL.i(list.toString());
        return list;
    }

    public <T> List<T> query(Class<T> t) {
        return null;
    }
}
