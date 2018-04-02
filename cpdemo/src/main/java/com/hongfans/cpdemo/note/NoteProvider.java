package com.hongfans.cpdemo.note;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.hongfans.cpdemo.DatabaseHelper;

/**
 * TODO
 * Created by MEI on 2018/4/2.
 */

public class NoteProvider extends ContentProvider {

    private DatabaseHelper mHelper;

    private static UriMatcher matcher;

    private static final int QUERY_LIST = 1;
    private static final int QUERY_ITEM = 2;

    static {
        matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(Note.Table.AUTHORITY, Note.Table.PATH_NOTES, QUERY_LIST);
        matcher.addURI(Note.Table.AUTHORITY, Note.Table.PATH_NOTE, QUERY_ITEM);
    }

    @Override
    public boolean onCreate() {
        mHelper = new DatabaseHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor;
        switch (matcher.match(uri)) {
            case QUERY_LIST: {
                cursor = db.query(Note.Table.TABLE_NAME, null, null, null, null, null, sortOrder);
            }
            break;
            case QUERY_ITEM: {
                cursor = db.query(Note.Table.TABLE_NAME, null, " where " + Note.Table.NOTE_ID + "=?", new String[]{uri.getPathSegments().get(1)}, null, null, sortOrder);
            }
            break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        if (cursor != null) {
            cursor.setNotificationUri(getContext().getContentResolver(), uri); //  Uri 有改变时对本 Cursor 发出通知
        }

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (matcher.match(uri)) {
            case QUERY_LIST:
                return Note.Table.CONTENT_TYPE;
            case QUERY_ITEM:
                return Note.Table.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        if (matcher.match(uri) != QUERY_LIST) {
            throw new IllegalArgumentException("Unknown URI " + uri);
        }

        if (!values.containsKey(Note.Table.NOTE_PUB_DATE)) {
            Long now = System.currentTimeMillis();
            values.put(Note.Table.NOTE_PUB_DATE, now);
        }

        SQLiteDatabase db = mHelper.getWritableDatabase();
        long rowID = db.insert(Note.Table.TABLE_NAME, Note.Table.NOTE_CONTENT, values);
        if (rowID > 0) {
            Uri insertedUri = ContentUris.withAppendedId(Note.Table.CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(insertedUri, null); // 通知所有注册的URI，数据库已经改变
            return insertedUri;
        }

        throw new android.database.SQLException("Failed to insert row into " + uri);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        int count;
        switch (matcher.match(uri)) {
            case QUERY_LIST: {
                count = db.delete(Note.Table.TABLE_NAME, selection, selectionArgs);
            }
            break;
            case QUERY_ITEM: {
                String rowID = uri.getPathSegments().get(1);
                String whereClause = Note.Table.NOTE_ID + "=" + rowID + (!TextUtils.isEmpty(selection) ? (" and ( " + selection + " ) ") : "");
                count = db.delete(Note.Table.TABLE_NAME, whereClause, selectionArgs);
            }
            break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        int count;
        switch (matcher.match(uri)) {
            case QUERY_LIST: {
                count = db.update(Note.Table.TABLE_NAME, values, selection, selectionArgs);
            }
            break;
            case QUERY_ITEM: {
                String rowID = uri.getPathSegments().get(1);
                String whereClause = Note.Table.NOTE_ID + "=" + rowID
                        + (!TextUtils.isEmpty(selection) ? ("and ( " + selection + "  ) ") : "");
                count = db.update(Note.Table.TABLE_NAME, values, whereClause, selectionArgs);
            }
            break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return count;
    }
}
