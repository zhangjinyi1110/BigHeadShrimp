package com.example.zjy.bigheadshrimp;

import android.app.Application;
import android.content.Intent;

import com.example.zjy.bigheadshrimp.service.ExecuteService;
import com.example.zjy.bigheadshrimp.service.GuardService;

/**
 * Created by 仕囵弹 on 2018/11/20.
 * application
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        startService(new Intent(this, ExecuteService.class));
        startService(new Intent(this, GuardService.class));
    }
}
