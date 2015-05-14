package com.jacob.android.binder.one;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.jacob.android.binder.R;

/**
 * Created by jacob-wj on 2015/5/14.
 */
public class MyClientActivity extends FragmentActivity {

    private ServiceConnection mServiceCon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        mServiceCon  = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.e("TAG","onServiceConnected");
                MyService myService  = ((MyService.MyBinder)service).getService();
                myService.sayHello();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.e("TAG","onServiceDisconnected");
            }
        };

    }

    @Override
    protected void onStart() {
        super.onStart();
        //绑定Service
        Intent intent = new Intent(this,MyService.class);
        this.bindService(intent,mServiceCon, Context.BIND_AUTO_CREATE);
    }


    @Override
    protected void onStop() {
        super.onStop();
        //解绑service
        this.unbindService(mServiceCon);
    }
}
