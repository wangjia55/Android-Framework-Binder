package com.jacob.android.binder;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;

import com.jacob.android.binder.one.MyClientActivity;
import com.jacob.android.binder.two.RemoteActivity;


public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void simpleDemo(View view){
        Intent intent =new Intent(this,MyClientActivity.class);
        startActivity(intent);
    }

    public void download(View view){
        Intent intent =new Intent(this,RemoteActivity.class);
        startActivity(intent);
    }

}
