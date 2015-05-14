package com.jacob.android.binder.two;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.jacob.android.binder.R;

/**
 * Created by jacob-wj on 2015/5/14.
 */
public class RemoteActivity extends FragmentActivity {
    private ServiceConnection mServiceConn;
    private ProgressBar mProgressBar;
    private RemoteService mRemoteService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mServiceConn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.e("TAG","onServiceConnected");
                mRemoteService = ((RemoteService.RemoteBinder)service).getService();
                mRemoteService.setOnProgressListener(new RemoteService.OnProgressListener() {
                    @Override
                    public void onProgress(int progress) {
                        mProgressBar.setProgress(progress);
                    }
                });


            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.e("TAG","onServiceDisconnected");
            }
        };
    }

    public void download(View view){
        mRemoteService.startDownLoad();
    }


    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, RemoteService.class);
        bindService(intent, mServiceConn, Context.BIND_AUTO_CREATE);
    }


    @Override
    protected void onStop() {
        super.onStop();
        unbindService(mServiceConn);
    }


}
