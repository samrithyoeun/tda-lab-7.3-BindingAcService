package com.fluffy.samrith.binding;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import java.net.URL;

/**
 * Created by samrith on 8/12/17.
 */

public class MyIntentService extends IntentService{

    public Thread thread = new Thread();

    public MyIntentService() {
        super("MyIntentServiceName");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        thread.start();
        int result=0;
        try{
            result = DownloadFile(new URL("https://goo.gl/2asYYt"));
            Log.d("IntentService","Downloaded"+result+" bytes");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private int DownloadFile(URL urls){
        try{
            //--simulate taking some time to download a file --
            Thread.sleep(5000);
        }catch (Exception e){
            e.printStackTrace();
        }

        //--return an arbitrary number representing the size of the file download --
        return 100;
    }

    }



