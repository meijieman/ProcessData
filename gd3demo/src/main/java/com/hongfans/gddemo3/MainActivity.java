package com.hongfans.gddemo3;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hongfans.gddemo3.dao.Note;
import com.hongfans.gddemo3.dao.NoteDao;

import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private NoteDao mNoteDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_insert).setOnClickListener(this);
        findViewById(R.id.btn_del).setOnClickListener(this);
        findViewById(R.id.btn_modify).setOnClickListener(this);
        findViewById(R.id.btn_query).setOnClickListener(this);

        mNoteDao = ((BaseApp) getApplication()).getDaoSession().getNoteDao();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_insert:
                Note note = new Note();
                note.setName("哈哈哈");
                note.setTempUsageCount(200);
                mNoteDao.insert(note);
                break;
            case R.id.btn_del:

                break;
            case R.id.btn_modify:
                Cursor cursor = mNoteDao.getDatabase().rawQuery("", new String[]{});

                break;
            case R.id.btn_query:
                Query<Note> build = mNoteDao.queryBuilder().build();
                System.out.println("----- " + build.list());
                break;

        }
    }
}
