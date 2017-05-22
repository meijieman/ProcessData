package com.major.processdata;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.litesuits.orm.LiteOrm;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    public static final String ELEGANT_DB = "elegant.db";

    private OperationBinder mBinder;
    private LiteOrm mLiteOrm;

    private ServiceConnection mConn = new ServiceConnection(){
        @Override
        public void onServiceConnected(ComponentName name, IBinder service){
            LogUtil.i("onServiceConnected");
            mBinder = OperationBinder.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name){
            LogUtil.i("onServiceDisconnected");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_get_data_service).setOnClickListener(this);
        findViewById(R.id.btn_save_data_service).setOnClickListener(this);
        findViewById(R.id.btn_get_data).setOnClickListener(this);
        findViewById(R.id.btn_save_data).setOnClickListener(this);

        bindService(new Intent(this, OperationService.class), mConn, BIND_AUTO_CREATE);

        mLiteOrm = LiteOrm.newSingleInstance(getApplicationContext(), ELEGANT_DB);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        unbindService(mConn);
        LogUtil.i("unbindService");
    }

    @Override
    public void onClick(View v){
        switch(v.getId()) {
            case R.id.btn_get_data_service: {
                try{
                    List<ElegantBean> data = mBinder.getData();
                    LogUtil.i("get data service " + data);
                } catch(RemoteException e){
                    e.printStackTrace();
                }
                break;
            }
            case R.id.btn_save_data_service: {
                ElegantBean bean = new ElegantBean();
                bean.setCount(222);
                bean.setNick("someone");
                try{
                    long l = mBinder.saveData(bean);
                    LogUtil.i("save data service insert " + l);
                } catch(RemoteException e){
                    e.printStackTrace();
                }
                break;
            }
            case R.id.btn_get_data: {
                ArrayList<ElegantBean> query = mLiteOrm.query(ElegantBean.class);
                LogUtil.i("get data " + query);
                break;
            }
            case R.id.btn_save_data: {
                ElegantBean bean = new ElegantBean();
                bean.setCount(111);
                bean.setNick("whocare");
                long insert = mLiteOrm.insert(bean);
                LogUtil.i("save data insert " + insert);
                break;
            }
        }
    }
}
