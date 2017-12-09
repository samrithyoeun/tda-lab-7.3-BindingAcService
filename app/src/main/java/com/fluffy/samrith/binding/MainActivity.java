package com.fluffy.samrith.binding;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {


    IntentFilter intentFilter;
    MyService serviceBinder;
    Intent i;

    private ServiceConnection connection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            //—-called when the connection is made—-
            serviceBinder = ((MyService.MyBinder) service).getService();
            try {
                URL[] urls = new URL[]{new URL("http://www.amazon.com/somefiles.pdf"), new URL("http://www.wrox.com/somefiles.pdf"), new URL("http://www.google.com/somefiles.pdf"), new URL("http://www.learn2develop.net/somefiles.pdf")};
                // ---assign the URLs to the service through the
                // serviceBinder object---
                serviceBinder.urls = urls;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            startService(i);
        }

        public void onServiceDisconnected(ComponentName className) {
            //---called when the service disconnects---
            serviceBinder = null;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

//    public void startService(View view){
//        Intent intent = new Intent(getBaseContext(),MyService.class);
//        try {
//            URL[] urls = new URL[]{new URL("http://www.amazon.com/somefiles.pdf"),
//                    new URL("http://www.wrox.com/somefiles.pdf"),
//                    new URL("http://www.google.com/somefiles.pdf"),
//                    new URL("http://www.learn2develop.net/somefiles.pdf")};
//            intent.putExtra("URLs", urls);
//        } catch (MalformedURLException e)
//
//        {
//            e.printStackTrace();
//        }
//
//    startService(intent);

    public void startService(View view) {
        i = new Intent(MainActivity.this, MyService.class);
        bindService(i, connection, Context.BIND_AUTO_CREATE);


    }

    public void stopService(View view){
        stopService(new Intent(getBaseContext(),MyIntentService.class));
    }



}
