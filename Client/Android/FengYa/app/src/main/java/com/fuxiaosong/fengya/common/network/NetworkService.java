package com.fuxiaosong.fengya.common.network;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by fuxiaosong on 16/9/1.
 */
public class NetworkService extends IntentService {
    public NetworkService() {
        super("networkService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {


    }
}
