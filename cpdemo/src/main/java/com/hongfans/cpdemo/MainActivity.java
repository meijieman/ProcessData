package com.hongfans.cpdemo;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.hongfans.cpdemo.note.Note;
import com.hongfans.cpdemo.note.ResolverOrm;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Uri mInsertedUri;
    private ListView mListView;

    private ResolverOrm mResolverOrm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = findViewById(R.id.lv_main);

        findViewById(R.id.btn_query).setOnClickListener(this);
        findViewById(R.id.btn_insert).setOnClickListener(this);
        findViewById(R.id.btn_delete).setOnClickListener(this);
        findViewById(R.id.btn_update).setOnClickListener(this);

        mResolverOrm = new ResolverOrm(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_query: {
                //查询数据

                mResolverOrm.query();

                Cursor cursor = getContentResolver().query(Note.Table.CONTENT_URI, null, null, null, null);
                SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1,
                        cursor, new String[]{"content"}, new int[]{android.R.id.text1});
                mListView.setAdapter(adapter);
            }
            break;
            case R.id.btn_insert: {
                //插入数据
                ContentValues values = new ContentValues();
                values.put(Note.Table.NOTE_CONTENT, "test " + new Date().toLocaleString());
                mInsertedUri = getContentResolver().insert(Note.Table.CONTENT_URI, values);
            }
            break;
            case R.id.btn_delete: {
                //删除数据
                getContentResolver().delete(mInsertedUri, null, null);
            }
            break;
            case R.id.btn_update: {
                //更新数据
                ContentValues values = new ContentValues();
                values.put(Note.Table.NOTE_CONTENT, "test updated");
                getContentResolver().update(Uri.withAppendedPath(Note.Table.CONTENT_URI, "/" + mInsertedUri.getPathSegments().get(1)), values, null, null);
            }
            break;
        }
    }
}
