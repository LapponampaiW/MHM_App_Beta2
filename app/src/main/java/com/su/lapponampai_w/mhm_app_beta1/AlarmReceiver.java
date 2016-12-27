package com.su.lapponampai_w.mhm_app_beta1;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by apple on 5/10/16.
 */
public class AlarmReceiver extends BroadcastReceiver {

    //Explicit
    String string_AlarmTABLEId;
    String string_AlarmTABLEDateTime;
    String string_DailyUpdateTimeNof;
    int notifID;


    @Override
    public void onReceive(Context context, Intent intent) {

        MyManage myManage = new MyManage(context);
        String[] strNotification = myManage.filter_userTABLE(6);



        receiveIntent(intent);

        if (notifID < 50) {
            if (strNotification[0].equals("Default")) {
                if (string_DailyUpdateTimeNof.equals("1")) {
                    createNotificationMedicine(context, "MHM Application", "ถึงเวลาแล้วครับ", "MHM Application",string_AlarmTABLEId,string_AlarmTABLEDateTime);
                } else if (string_DailyUpdateTimeNof.equals("2")) {
                    createNotificationMedicine(context, "MHM Application", "(ครั้งที่ 2) ถึงเวลาแล้วครับ", "MHM Application",string_AlarmTABLEId,string_AlarmTABLEDateTime);
                }

            } else {
                if (string_DailyUpdateTimeNof.equals("1")) {
                    createNotificationMedicine(context,"MHM Application", strNotification[0], "MHM Application",string_AlarmTABLEId,string_AlarmTABLEDateTime);
                } else if (string_DailyUpdateTimeNof.equals("2")) {
                    createNotificationMedicine(context,"MHM Application", "(ครั้งที่ 2 )" + strNotification[0], "MHM Application",string_AlarmTABLEId,string_AlarmTABLEDateTime);
                }

            }
        } else if (notifID < 150) {

            if (string_DailyUpdateTimeNof.equals("AppointmentDoctor")) {
                createNotificationAppointment(context,"MHM Application","เตือนวันนัด!!","MHMApplication",string_AlarmTABLEId,string_AlarmTABLEDateTime); // id,CurrentDay
            } else if (string_DailyUpdateTimeNof.equals("AppointmentLab")) {
                createNotificationAppointment(context,"MHM Application","เตือนวันนัด!!","MHMApplication",string_AlarmTABLEId,string_AlarmTABLEDateTime); // id,CurrentDay
            }

        }
    } // onReceive

    private void receiveIntent(Intent intent) {

        string_AlarmTABLEId = intent.getStringExtra("DailyUpdateIntent");
        string_AlarmTABLEDateTime = intent.getStringExtra("DailyUpdateIntentTime");
        string_DailyUpdateTimeNof = intent.getStringExtra("DailyUpdateTimeNof");
        notifID = intent.getIntExtra("notifID", 10000);
        Log.d("25/10/2559", "4 : DailyUpdateIntent : " + string_AlarmTABLEId);
    }

    private void createNotificationMedicine(Context context, String s, String s1, String alert,String sIntent,String sDateTime) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.logo_mhm48)
                .setContentTitle(s)
                .setTicker(alert)
                .setContentText(s1);


        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("PopUpMaster", "AlarmReceiver");
        intent.putExtra("SumId_AlarmReceiver", sIntent);
        intent.putExtra("SumDateTime_AlarmReceiver", sDateTime);

        /*
        for(int i = 0;i<=1;i++) {
            PendingIntent notificIntent = PendingIntent
                    .getActivity(context, 1, intent, 0); //ให้เปิด MainActivity
            notificIntent.cancel();
        }
        */
        for (int x = notifID; x <= 40; x++) {
            PendingIntent notificIntent = PendingIntent
                    .getActivity(context, notifID, intent, 0); //ให้เปิด MainActivity
            notificIntent.cancel();
        }


        PendingIntent notificIntent = PendingIntent
                .getActivity(context, notifID, intent, 0); //ให้เปิด MainActivity



        builder.setContentIntent(notificIntent);
        builder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE);
        builder.setAutoCancel(false);

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        //Random random = new Random();
        //int m = random.nextInt(9999 - 1000) + 1000;

        //notificationManager.notify(m,builder.build());
        notificationManager.notify(notifID,builder.build());

    }


    private void createNotificationAppointment(Context context, String s, String s1, String alert,String sIdAppointment,String sDay) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.logo_mhm48)
                .setContentTitle(s)
                .setTicker(alert)
                .setContentText(s1);


        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("PopUpMaster", "AlarmAppointmentDoctor");
        intent.putExtra("SumId_AlarmReceiver", sIdAppointment);
        intent.putExtra("SumDateTime_AlarmReceiver", sDay);
        intent.putExtra("sId", Integer.toString(notifID));

        /*
        for(int i = 0;i<=1;i++) {
            PendingIntent notificIntent = PendingIntent
                    .getActivity(context, 1, intent, 0); //ให้เปิด MainActivity
            notificIntent.cancel();
        }
        */
        for (int x = notifID; x <= 140; x++) {
            PendingIntent notificIntent = PendingIntent
                    .getActivity(context, notifID, intent, 0); //ให้เปิด MainActivity
            notificIntent.cancel();
        }


        PendingIntent notificIntent = PendingIntent
                .getActivity(context, notifID, intent, 0); //ให้เปิด MainActivity



        builder.setContentIntent(notificIntent);
        builder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE);
        builder.setAutoCancel(false);

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        //Random random = new Random();
        //int m = random.nextInt(9999 - 1000) + 1000;

        //notificationManager.notify(m,builder.build());
        notificationManager.notify(notifID,builder.build());

    }
} //Main Class
