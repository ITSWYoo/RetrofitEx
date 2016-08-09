package com.example.yoo.retrofitex;

import android.app.Application;
import android.content.Context;

/**
 * Created by Yoo on 2016-08-03.
 */
public class MyApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }
    public static Context getContext(){
        return mContext;
    }
}
