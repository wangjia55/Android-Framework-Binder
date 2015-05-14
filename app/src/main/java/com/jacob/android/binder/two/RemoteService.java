package com.jacob.android.binder.two;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/**
 * Created by jacob-wj on 2015/5/14.
 */
public class RemoteService extends Service {
    public static final int MAX = 1000;
    private OnProgressListener mProgressListener;
    private int progress;

    @Override
    public IBinder onBind(Intent intent) {
        return new RemoteBinder();
    }


    public interface  OnProgressListener{
        void  onProgress(int progress);
    }

    public void setOnProgressListener(OnProgressListener listener){
        this.mProgressListener = listener;
    }

    public void startDownLoad(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progress < MAX){
                    progress+=5;
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //通过回调的方式可以将数据传递到Client。

                    if (mProgressListener != null){
                        mProgressListener.onProgress(progress);
                    }
                }
            }
        }).start();
    }

    public class RemoteBinder extends Binder{
        public RemoteService getService(){
            return  RemoteService.this;
        }
    }

}
