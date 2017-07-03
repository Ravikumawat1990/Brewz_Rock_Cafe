package com.app.elixir.brewz_rock_cafe.utils;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by User on 27-07-2016.
 */
public class messageListionerService extends Service {
    Timer timer;
    TimerTask task;

    private final IBinder binder = new LocalBinder();
    private ServiceCallbacks serviceCallbacks;
    Context context;

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        context = this;
    }

    public class LocalBinder extends Binder {
        public messageListionerService getService() {
            return messageListionerService.this;
        }
    }

    public void setCallbacks(ServiceCallbacks callbacks) {
        serviceCallbacks = callbacks;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        timer = new Timer();
        task = new TimerTask() {
            Handler mTimerHandler = new Handler();

            @Override
            public void run() {
                // TODO Auto-generated method stub
                final String status = CM.getSp(context, "msg", "").toString();
                mTimerHandler.post(new Runnable() {
                    public void run() {
                        // TODO
                        try {
                            Log.e("msg", "" + status);
                            serviceCallbacks.ShowConnectionPopup(status);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        };
        timer.schedule(task, 0, 1000);
        return binder;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
            task.cancel();
        }
    }


    public interface ServiceCallbacks {
        void ShowConnectionPopup(String status);
    }

}
