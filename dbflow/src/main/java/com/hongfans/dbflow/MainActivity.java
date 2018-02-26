package com.hongfans.dbflow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hongfans.common.log.LogUtil;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.ModelAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ModelAdapter<UserData> mAdapter;
    private UserData mUserData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_insert).setOnClickListener(this);
        findViewById(R.id.btn_insert_1).setOnClickListener(this);
        findViewById(R.id.btn_delete).setOnClickListener(this);
        findViewById(R.id.btn_update).setOnClickListener(this);
        findViewById(R.id.btn_update_1).setOnClickListener(this);
        findViewById(R.id.btn_update_2).setOnClickListener(this);
        findViewById(R.id.btn_query).setOnClickListener(this);

        mUserData = new UserData();
        mUserData.id = 1;
        mUserData.name = "张三";
        mUserData.age = 22;
        mUserData.sex = true;

        mAdapter = FlowManager.getModelAdapter(UserData.class);

        LogUtil.init("com.hongfans.dbflow", "elegant", true, true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_insert:
                long insert = mAdapter.insert(mUserData);
                LogUtil.i("insert " + insert);
                break;
            case R.id.btn_insert_1:
                boolean save = mUserData.save();
                LogUtil.i("save " + save);
                break;
            case R.id.btn_delete:
                boolean delete = mAdapter.delete(mUserData);
                LogUtil.i("delete " + delete);
                break;
            case R.id.btn_update:
                boolean update = mAdapter.update(mUserData);
                LogUtil.i("update " + update);
                break;
            case R.id.btn_update_1:
                UserData userData = new UserData();
                userData.id = 1;
                userData.name = "999";
                boolean update1 = userData.update();
                LogUtil.i("update1 " + update1);
                break;
            case R.id.btn_update_2:
                // UserData_Table 就是 DBFlow 自动生成的表名
                SQLite.update(UserData.class).set(UserData_Table.name.eq("888")).where(UserData_Table.id.eq(1L)).execute();
                break;
            case R.id.btn_query:
                List<UserData> list = SQLite.select().from(UserData.class).queryList();
                LogUtil.i("list " + list);
                break;
        }
    }
}
