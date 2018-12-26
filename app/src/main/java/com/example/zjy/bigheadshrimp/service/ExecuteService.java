package com.example.zjy.bigheadshrimp.service;

import android.app.Notification;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import com.example.zjy.bigheadshrimp.ProcessInterface;

public class ExecuteService extends Service {

    private final String TAG = ExecuteService.class.getSimpleName();

    public ExecuteService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(5000);
                        long curr = System.currentTimeMillis();
                        Log.d(TAG, "run: " + curr);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Log.e(TAG, "run: " + e.getLocalizedMessage());
                    }
                }
            }
        }).start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startForeground(1, new Notification());
        bindService(new Intent(this, GuardService.class), connection, BIND_IMPORTANT);
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
            startService(new Intent(ExecuteService.this, GuardService.class));
            bindService(new Intent(ExecuteService.this, GuardService.class), connection, BIND_IMPORTANT);
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
