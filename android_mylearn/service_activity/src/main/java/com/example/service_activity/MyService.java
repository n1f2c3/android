package com.example.service_activity;



import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MyService extends Service {
    private static String TAG = MyService.class.getName();
    private static final long LOOP_TIME = 1; //循环时间
    private static ScheduledExecutorService mExecutorService;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");

        mExecutorService = Executors.newScheduledThreadPool(2);
        mExecutorService.scheduleAtFixedRate(mRunnable, LOOP_TIME, LOOP_TIME, TimeUnit.SECONDS);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        return null;
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
        mExecutorService.shutdown();
        mExecutorService = null;
        mRunnable = null;
    }

    private int count = 0;
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            count++;
            Log.d(TAG, "=== count:" + count);
        }
    };
}

