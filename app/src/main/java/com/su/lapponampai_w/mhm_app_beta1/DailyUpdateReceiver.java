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

    //เมื่อถึงเวลาตามที่กำหนดจะ Saveข้อมูลเข้าตาราง SumTable อัตโนมัติ


    //Explicit
    private String[] mainIDStrings, timeRefStrings;
    private String dateString;

    @Override
    public void onReceive(Context context, Intent intent) {


        dateString = intent.getStringExtra("Date");

        Toast.makeText(context, "Update sumTABLE Success", Toast.LENGTH_LONG).show();
        Log.d("10MayV1", "Receive ทำงาน");
        Log.d("10MayV1", "Date ที่ได้ ==>" + dateString);

        MyManage myManage = new MyManage(context);
        mainIDStrings = myManage.readAllMainTABLE(0);
        timeRefStrings = myManage.readAllMainTABLE(1);

        for(int i=0; i < mainIDStrings.length;i++) {
            Log.d("10MayV1", "id = " + mainIDStrings[i]);
            Log.d("10MayV1", "id = " + timeRefStrings[i]);

            myManage.addValueToSumTable(mainIDStrings[i], dateString, timeRefStrings[i], "", "", "");



        } //for




    }
}  //Main Class
