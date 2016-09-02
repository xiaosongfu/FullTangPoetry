package com.fuxiaosong.fengya.common.network;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by fuxiaosong on 16/9/1.
 */
public class NetworkTask extends Thread{
    private String urlContainsParams;
    Handler handler;

    public NetworkTask(String urlContainsParams , Handler handler){
        this.urlContainsParams = urlContainsParams;
        this.handler = handler;
    }

    @Override
    public void run() {
        //
        StringBuffer sb = new StringBuffer();
        //
        Message msg = handler.obtainMessage();
        try {
            URL url = new URL(urlContainsParams);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");


            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String data ;
            while ((data = br.readLine()) != null){
                sb.append(data);
            }
            br.close();
            connection.disconnect();


//            Log.i("NetworkTask" , "request url " + urlContainsParams + " success");


        } catch (MalformedURLException e) {
            e.printStackTrace();
            msg.what = ResponseCode.REQUEST_FAIL;
        } catch (IOException e) {
            e.printStackTrace();
            msg.what = ResponseCode.REQUEST_FAIL;
        }

        //
        msg.what = ResponseCode.REQUEST_SUCCESS;
        msg.obj = sb.toString();
        //
        handler.sendMessage(msg);
    }
}
