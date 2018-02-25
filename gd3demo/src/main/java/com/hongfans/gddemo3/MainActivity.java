package com.hongfans.gddemo3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.hongfans.gddemo3.entity.DailyNote;
import com.hongfans.gddemo3.entity.ModifyDate;
import com.hongfans.gddemo3.entity.Note;
import com.hongfans.gddemo3.gen.DailyNoteDao;
import com.hongfans.gddemo3.gen.ModifyDateDao;
import com.hongfans.gddemo3.gen.NoteDao;

import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "tag_ma";

    private NoteDao mNoteDao;
    private DailyNoteDao mDailyNoteDao;
    private ModifyDateDao mModifyDateDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_insert).setOnClickListener(this);
        findViewById(R.id.btn_del).setOnClickListener(this);
        findViewById(R.id.btn_modify).setOnClickListener(this);
        findViewById(R.id.btn_query).setOnClickListener(this);

        findViewById(R.id.btn_insert_1).setOnClickListener(this);
        findViewById(R.id.btn_del_1).setOnClickListener(this);
        findViewById(R.id.btn_query_1).setOnClickListener(this);

        mNoteDao = ((BaseApp) getApplication()).getDaoSession().getNoteDao();
        mDailyNoteDao = ((BaseApp) getApplication()).getDaoSession().getDailyNoteDao();
        mModifyDateDao = ((BaseApp) getApplication()).getDaoSession().getModifyDateDao();


        // 设置打印 sql
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
    }

    @Override
    public void onClick(View v) {
        Log.i(TAG, "onClick: " + v.getId());
        switch (v.getId()) {
            case R.id.btn_insert:
                Note note = new Note();
                note.setName("哈哈哈");
                note.setTempUsageCount(200);
                List<ModifyDate> dates = new ArrayList<>();
            {
                ModifyDate date = new ModifyDate();
                date.setDate("2017年12月26日 15:34:23");
                dates.add(date);
            }
            {
                ModifyDate date = new ModifyDate();
                date.setDate("2017年10月10日 10:10:10");
                dates.add(date);
            }
                note.setModifyDates(dates);
                long insert = mNoteDao.insert(note);
                Log.i(TAG, "insert1 " + insert);
                break;
            case R.id.btn_del:

                break;
            case R.id.btn_modify:

                break;
            case R.id.btn_query:
                Query<Note> build = mNoteDao.queryBuilder().build();
                Log.i(TAG, "" + build.list());

                Query<ModifyDate> build1 = mModifyDateDao.queryBuilder().build();
                Log.i(TAG, "modify date " + build1.list());

                break;
// ----------------------------------------------
            case R.id.btn_insert_1:
                DailyNote dn = new DailyNote();
                dn.setName("每日一篇");
                dn.setDate("2017年12月19日 19:01:27");
                dn.setSave(false);
                long insert2 = mDailyNoteDao.insert(dn);
                Log.i(TAG, "insert2 " + insert2);
                break;
            case R.id.btn_del_1:

                break;
            case R.id.btn_query_1:
                List<DailyNote> list = mDailyNoteDao.queryBuilder().list();
                Log.i(TAG, "list " + list);
                break;
        }
    }
}
