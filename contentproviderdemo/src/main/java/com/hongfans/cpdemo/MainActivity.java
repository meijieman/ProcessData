package com.hongfans.cpdemo;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "tag_cursor";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.lv_main);
        final ArrayList<String> list = new ArrayList<>();
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        findViewById(R.id.btn_query).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                adapter.notifyDataSetInvalidated();
                Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        list.add(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)) + ", "
                                + cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)) + ", "
                                + cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI)) + ", "
                                + cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID)) + ", "

                        );
                    }
                    cursor.close();
                }

                adapter.notifyDataSetChanged();
            }
        });


        findViewById(R.id.btn_insert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                method2();
            }
        });

        findViewById(R.id.btn_query_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                method3();
            }
        });

//        method1();

    }

    private void method2() {
        ContentValues cv = new ContentValues();
        cv.put(TestContract.TestEntry.COLUMN_NAME, "peng");
//        cv.put(TestContract.TestEntry._ID, System.currentTimeMillis() );
        Uri insert = getContentResolver().insert(TestContract.TestEntry.CONTENT_URI, cv);
        Log.i(TAG, "method2: insert " + insert + ", " + cv);
    }

    private void method3() {
        Cursor cursor = getContentResolver().query(TestContract.TestEntry.CONTENT_URI, null, null, null, null);
        if (cursor != null) {
            StringBuilder sb = new StringBuilder();
            while (cursor.moveToNext()) {
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    sb.append(cursor.getColumnName(i) + ": " + cursor.getString(i) + "; ");
                }
                sb.append("\n");
            }
            Log.w(TAG, sb.toString());
            cursor.close();
        }
    }


    private void method1() {
        /*
        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        MediaStore.Audio.Media.INTERNAL_CONTENT_URI

        MediaStore.Images.Media.EXTERNAL_CONTENT_URI

        MediaStore.Audio.Media.INTERNAL_CONTENT_URI
         */
        Cursor cursor = getContentResolver().query(MediaStore.Audio.Media.INTERNAL_CONTENT_URI, null, null, null, null);
        if (cursor != null) {
            String[] columnNames = cursor.getColumnNames();
            Log.i(TAG, "method1: " + Arrays.toString(columnNames));
            StringBuilder sb = new StringBuilder();
            while (cursor.moveToNext()) {
                int columnCount = cursor.getColumnCount();
                for (int i = 0; i < columnCount; i++) {
                    sb.append(cursor.getString(i) + ",");
                }
                sb.append("\n");
            }

            Log.i(TAG, "method1: " + sb);

            cursor.close();
        } else {
            Log.e(TAG, "cursor is null");
        }


    }
}
