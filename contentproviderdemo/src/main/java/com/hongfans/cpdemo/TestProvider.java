package com.hongfans.cpdemo;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * TODO
 * Created by MEI on 2017/12/5.
 */

public class TestProvider extends ContentProvider {

    private static final String TAG = "tag_provider";

    public static final int TEST = 100;
    public static final int BATCH_TEST = 101;

    private static UriMatcher sUriMatcher;

    private DBHelper mDBHelper;

    static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(TestContract.CONTENT_AUTHORITY, TestContract.PATH_TEST, TEST);
        sUriMatcher.addURI(TestContract.CONTENT_AUTHORITY, TestContract.BATCH_PATH_TEST, BATCH_TEST);

    }

    @Override
    public boolean onCreate() {
        mDBHelper = new DBHelper(getContext());


        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        Cursor cursor = null;
        Log.i(TAG, "query: " + uri);
        switch (sUriMatcher.match(uri)) {
            case TEST:
                cursor = db.query(TestContract.TestEntry.TABLE_NAME, projection, selection, selectionArgs, sortOrder, null, null);
                break;
            case BATCH_TEST:
                db.beginTransaction();


                db.setTransactionSuccessful();
                db.endTransaction();
                break;
            default:

                break;
        }

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        long insert = -1;
        Log.i(TAG, "insert: " + uri);

        switch (sUriMatcher.match(uri)) {
            case TEST:
                try {
                    insert = db.insert(TestContract.TestEntry.TABLE_NAME, null, values);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (insert <= 0) {
                    throw new SQLException("Failed to insert row into " + uri);
                }
//                return TestContract.TestEntry.buildUri(uri, insert);
                return TestContract.TestEntry.buildUri(TestContract.TestEntry.CONTENT_URI, insert);
            case BATCH_TEST:

                return TestContract.TestEntry.buildUri(TestContract.TestEntry.BATCH_CONTENT_URI, insert);
            default:
                throw new SQLException("UnKnown Uri: " + uri);
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
