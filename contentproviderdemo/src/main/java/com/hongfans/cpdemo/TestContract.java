package com.hongfans.cpdemo;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * TODO
 * Created by MEI on 2017/12/5.
 */

public class TestContract {


    public static final String CONTENT_AUTHORITY = "com.hongfans.cpdemo";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_TEST = "test";
    public static final String BATCH_PATH_TEST = "batch_test";

    public static final class TestEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_TEST).build();
        public static final Uri BATCH_CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(BATCH_PATH_TEST).build();

        protected static Uri buildUri(Uri uri, long id) {
            return ContentUris.withAppendedId(uri, id);
        }

        protected static final String TABLE_NAME = "test";
        public static final String COLUMN_NAME = "name";
    }
}
