package com.su.lapponampai_w.mhm_app_beta1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by apple on 5/10/16.
 */
public class DailyUpdateReceiver extends BroadcastReceiver{


    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, "Update sumTABLE Success", Toast.LENGTH_LONG).show();

        Log.d("10MayV1", "Receive ทำงาน");

    }
}  //Main Class
