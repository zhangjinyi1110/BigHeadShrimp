package com.example.zjy.bigheadshrimp.service;

import android.app.Notification;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.example.zjy.bigheadshrimp.ProcessInterface;

public class GuardService extends Service {

    private final String TAG = GuardService.class.getSimpleName();

    public GuardService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startForeground(1, new Notification());
        bindService(new Intent(this, ExecuteService.class), connection, BIND_IMPORTANT);
        return START_STICKY;
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG, "onServiceConnected: ");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG, "onServiceDisconnected: ");
            startService(new Intent(GuardService.this, ExecuteService.class));
            bindService(new Intent(GuardService.this, ExecuteService.class), connection, BIND_IMPORTANT);
        }
    };

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy: ");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new ProcessInterface.Stub() {
        };
    }
}
