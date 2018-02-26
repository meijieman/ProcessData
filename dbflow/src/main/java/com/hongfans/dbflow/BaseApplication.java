package com.hongfans.dbflow;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * TODO
 * Created by MEI on 2018/2/25.
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        FlowManager.init(new FlowConfig.Builder(this).build());
//      FlowManager.init(this);//这句也可以初始化
    }
}
