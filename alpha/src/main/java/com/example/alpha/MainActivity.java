package com.example.alpha;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hongfans.common.log.SL;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private IOperation mOperation;

    private ServiceConnection mConn = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mOperation = IOperation.Stub.asInterface(service);
            SL.i("onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mOperation = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_bind).setOnClickListener(this);
        findViewById(R.id.btn_unbind).setOnClickListener(this);
        findViewById(R.id.btn_insert).setOnClickListener(this);
        findViewById(R.id.btn_delete).setOnClickListener(this);
        findViewById(R.id.btn_update).setOnClickListener(this);
        findViewById(R.id.btn_query).setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mOperation != null) {
            unbindService(mConn);
            mOperation = null;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_bind:
                bindService(new Intent(this, DatabaseService.class), mConn, Context.BIND_AUTO_CREATE);
                break;
            case R.id.btn_unbind:
                unbindService(mConn);
                mOperation = null;
                break;
            case R.id.btn_insert: {
                if (mOperation != null) {
                    ContentValues cv = new ContentValues();
                    cv.put("id", 2L);
                    cv.put("name", "mei");
                    cv.put("age", 27);
                    try {
                        long insert = mOperation.insert(cv);
                        SL.i("insert " + insert);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
            break;
            case R.id.btn_delete: {
                if (mOperation != null) {
                    try {
                        ArrayList<String> selectionArgs = new ArrayList<>();
                        selectionArgs.add("27");
                        long delete = mOperation.delete("where age=?", selectionArgs);
                        SL.i("delete " + delete);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
            break;
            case R.id.btn_update: {
                if (mOperation != null) {
                    ContentValues cv = new ContentValues();
                    cv.put("name", "major");
                    try {
                        int update = mOperation.update(cv, null, null);
                        SL.i("update " + update);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
            break;
            case R.id.btn_query: {

            }
            break;
            default:

                break;
        }
    }
}
