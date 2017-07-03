package com.app.elixir.brewz_rock_cafe.fcm;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.app.elixir.brewz_rock_cafe.Model.NotiModel;
import com.app.elixir.brewz_rock_cafe.R;
import com.app.elixir.brewz_rock_cafe.activity.ViewDeliveryBoy;
import com.app.elixir.brewz_rock_cafe.activity.ViewSplash;
import com.app.elixir.brewz_rock_cafe.database.tbl_notification;
import com.app.elixir.brewz_rock_cafe.database.tbl_notificationNew;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";
    private static final String TAG1 = "logout";
    Context context;

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // TODO(developer): Handle FCM messages here.
        context = getApplicationContext();
        Map data = remoteMessage.getData();
        Set keys = data.keySet();
        Iterator itr = keys.iterator();
        String key;
        String value = "";
        while (itr.hasNext()) {
            key = (String) itr.next();
            value = (String) data.get(key);
            System.out.println(key + " - " + value);
        }


        Log.d(TAG, "From: " + remoteMessage.getFrom());
        sendNotification(remoteMessage.getNotification().getBody());

        if (remoteMessage.getNotification().getBody().equals("Delivery")) {
            Intent intent = new Intent("Delivery");
            // put whatever data you want to send, if any
            intent.putExtra("message", value.toString());
            //send broadcast
            sendBroadcast(intent);
            sendNotificationDelivery(remoteMessage.getNotification().getBody());
        } else if (remoteMessage.getNotification().getBody().equals("Customer")) {
            Intent intent = new Intent("Customer");
            intent.putExtra("message", value.toString());
            //send broadcast
            sendBroadcast(intent);
            sendNotification(remoteMessage.getNotification().getBody());
        } else {

        }

    }


    private void sendNotificationDelivery(String messageBody) {
        int requestID = (int) System.currentTimeMillis();
        Intent intent = new Intent(this, ViewDeliveryBoy.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, requestID, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setContentTitle(getString(R.string.app_name))
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);
        notificationBuilder.setSmallIcon(R.drawable.applogo);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(requestID, notificationBuilder.build());
        ArrayList<NotiModel> notiModels = new ArrayList<>();
        NotiModel notiModel = new NotiModel();
        notiModel.setKey(String.valueOf(requestID));
        notiModel.setIsViewed("0");
        notiModels.add(notiModel);
        tbl_notificationNew.Insert(notiModels);


    }


    private void sendNotification(String messageBody) {
        int requestID = (int) System.currentTimeMillis();
        Intent intent = new Intent(this, ViewSplash.class);
        ArrayList<NotiModel> notiModels = new ArrayList<>();
        NotiModel notiModel = new NotiModel();
        notiModel.setKey(String.valueOf(requestID));
        notiModel.setIsViewed("0");
        notiModels.add(notiModel);
        tbl_notification.Insert(notiModels);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, requestID, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setContentTitle(getString(R.string.app_name))
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);
        notificationBuilder.setSmallIcon(R.drawable.applogo);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(requestID, notificationBuilder.build());


    }
}
