package com.su.lapponampai_w.mhm_app_beta1;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

/**
 * Created by apple on 5/10/16.
 */
public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {


        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.drawable.logo_carabao48);
        builder.setTicker("ถึงเวลาทานยาแล้ว");
        builder.setWhen(System.currentTimeMillis());
        builder.setContentTitle("มาสเตอร์ เตือนทานยาแล้ว");
        builder.setContentText("รายละเอียด");
        builder.setAutoCancel(true);

        Uri uri = RingtoneManager.getDefaultUri(Notification.DEFAULT_SOUND); //Defeault ของเสียง
        builder.setSound(uri);

        Notification notification = builder.build();
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1000,notification);

        




    } // onReceive
} //Main Class
