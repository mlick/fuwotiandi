package com.lxx;

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;

import com.litesuits.orm.LiteOrm;
import com.lxx.util.UserUtils;

/**
 * Created by Administrator on 2018/4/19.
 */

public class MyApplication extends Application {

    private static final String TAG = MyApplication.class.getName();

    public LiteOrm liteOrm;

    public String userName;

    private static MyApplication myApplication;


    @Override
    public void onCreate() {
        super.onCreate();

        if (liteOrm == null) {
            liteOrm = LiteOrm.newSingleInstance(this, "jsb.db");
        }
        liteOrm.setDebugged(true); // open the log

        myApplication = this;

        userName = UserUtils.getUserName(this);
    }

    public static MyApplication getInstance() {
        return myApplication != null ? myApplication : new MyApplication();
    }

    @Override
    public void onTerminate() {
        // 程序终止的时候执行
        Log.d(TAG, "onTerminate");
        super.onTerminate();

        if (liteOrm != null) {
            liteOrm.close();
        }
    }

    @Override
    public void onLowMemory() {
        // 低内存的时候执行
        Log.d(TAG, "onLowMemory");
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        // 程序在内存清理的时候执行
        Log.d(TAG, "onTrimMemory");
        super.onTrimMemory(level);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.d(TAG, "onConfigurationChanged");
        super.onConfigurationChanged(newConfig);
    }


}
