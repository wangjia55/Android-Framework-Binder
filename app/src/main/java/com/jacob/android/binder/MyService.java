package com.jacob.android.binder;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by jacob-wj on 2015/5/14.
 */
public class MyService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    public void sayHello(){
        Log.e("Service","hello world");
    }

    public class MyBinder extends Binder {
        MyService getService(){
            return MyService.this;
        }
    }
}

