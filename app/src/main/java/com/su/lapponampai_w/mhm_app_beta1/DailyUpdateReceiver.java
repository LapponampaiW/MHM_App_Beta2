package com.su.lapponampai_w.mhm_app_beta1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.Date;

/**
 * Created by apple on 5/10/16.
 */
public class DailyUpdateReceiver extends BroadcastReceiver {

    //เมื่อถึงเวลาตามที่กำหนดจะ Saveข้อมูลเข้าตาราง SumTable อัตโนมัติ


    //Explicit
    private String[] mainIDStrings, timeRefStrings;
    private String dateString;
    private String[] stringsREAD0, stringsREAD1, stringsREAD2,
            stringsREAD3, stringsREAD4, stringsREAD5, stringsREAD6, stringsREAD7, stringsREAD8,
            stringsREAD9, stringsREAD10, stringsREAD11, stringsREAD12, stringsREAD13, stringsREAD14,
            stringsREAD15, stringsREAD16, stringsREAD17, stringsREAD18, stringsREAD19, stringsREAD20;

    private String currentDay, checkStartDay, checkFinishDay, checkWhich_Date_D;


    @Override
    public void onReceive(Context context, Intent intent) {


        Log.d("UpdatesumTABLE", "เข้าที่หน้า DailyUpdateReceiver.Java แล้ว");

        Toast.makeText(context, "Receiver Active", Toast.LENGTH_LONG).show();

        //เอาข้อมูลของยาที่ตั้งทานในวันนั้นๆ ทั้งหมดเข้ามาในหน้านี้
        //00:00 ถือเป็นของวันใหม่ไปเลยนะ

        MyManage myManage = new MyManage(context);

        //รับค่า mainTABLE มาทั้งหมด
        stringsREAD0 = myManage.readAllMainTABLE_Full(0);
        stringsREAD1 = myManage.readAllMainTABLE_Full(1);
        stringsREAD2 = myManage.readAllMainTABLE_Full(2);
        stringsREAD3 = myManage.readAllMainTABLE_Full(3);
        stringsREAD4 = myManage.readAllMainTABLE_Full(4);
        stringsREAD5 = myManage.readAllMainTABLE_Full(5);
        stringsREAD6 = myManage.readAllMainTABLE_Full(6);
        stringsREAD7 = myManage.readAllMainTABLE_Full(7);
        stringsREAD8 = myManage.readAllMainTABLE_Full(8);
        stringsREAD9 = myManage.readAllMainTABLE_Full(9); //StartDate
        stringsREAD10 = myManage.readAllMainTABLE_Full(10);
        stringsREAD11 = myManage.readAllMainTABLE_Full(11);
        stringsREAD12 = myManage.readAllMainTABLE_Full(12);
        stringsREAD13 = myManage.readAllMainTABLE_Full(13);
        stringsREAD14 = myManage.readAllMainTABLE_Full(14);
        stringsREAD15 = myManage.readAllMainTABLE_Full(15);
        stringsREAD16 = myManage.readAllMainTABLE_Full(16);
        stringsREAD17 = myManage.readAllMainTABLE_Full(17);
        stringsREAD18 = myManage.readAllMainTABLE_Full(18);
        stringsREAD19 = myManage.readAllMainTABLE_Full(19);
        stringsREAD20 = myManage.readAllMainTABLE_Full(20);  //TimeDateCanceled

        Log.d("UpdatesumTABLE", "ค่าตำแหน่งที่ 0 : " + stringsREAD0[0]);
        Log.d("UpdatesumTABLE", "ค่าตำแหน่งที่ 1 : " + stringsREAD1[0]);
        Log.d("UpdatesumTABLE", "ค่าตำแหน่งที่ 2 : " + stringsREAD2[0]);
        Log.d("UpdatesumTABLE", "ค่าตำแหน่งที่ 3 : " + stringsREAD3[0]);
        Log.d("UpdatesumTABLE", "ค่าตำแหน่งที่ 4 : " + stringsREAD4[0]);
        Log.d("UpdatesumTABLE", "ค่าตำแหน่งที่ 5 : " + stringsREAD5[0]); //Which_Date_D
        Log.d("UpdatesumTABLE", "ค่าตำแหน่งที่ 6 : " + stringsREAD6[0]);
        Log.d("UpdatesumTABLE", "ค่าตำแหน่งที่ 7 : " + stringsREAD7[0]);
        Log.d("UpdatesumTABLE", "ค่าตำแหน่งที่ 8 : " + stringsREAD8[0]);
        Log.d("UpdatesumTABLE", "ค่าตำแหน่งที่ 9 : " + stringsREAD9[0]);
        Log.d("UpdatesumTABLE", "ค่าตำแหน่งที่ 10 : " + stringsREAD10[0]);
        Log.d("UpdatesumTABLE", "ค่าตำแหน่งที่ 11 : " + stringsREAD11[0]);
        Log.d("UpdatesumTABLE", "ค่าตำแหน่งที่ 12 : " + stringsREAD12[0]);
        Log.d("UpdatesumTABLE", "ค่าตำแหน่งที่ 13 : " + stringsREAD13[0]);
        Log.d("UpdatesumTABLE", "ค่าตำแหน่งที่ 14 : " + stringsREAD14[0]);
        Log.d("UpdatesumTABLE", "ค่าตำแหน่งที่ 15 : " + stringsREAD15[0]);
        Log.d("UpdatesumTABLE", "ค่าตำแหน่งที่ 16 : " + stringsREAD16[0]);
        Log.d("UpdatesumTABLE", "ค่าตำแหน่งที่ 17 : " + stringsREAD17[0]);
        Log.d("UpdatesumTABLE", "ค่าตำแหน่งที่ 18 : " + stringsREAD18[0]);
        Log.d("UpdatesumTABLE", "ค่าตำแหน่งที่ 19 : " + stringsREAD19[0]);
        Log.d("UpdatesumTABLE", "ค่าตำแหน่งที่ 20 : " + stringsREAD20[0]);

        String[][] stringsReadAll_MainTABLE = {stringsREAD0, stringsREAD1, stringsREAD2, stringsREAD3, stringsREAD4,
                stringsREAD5, stringsREAD6, stringsREAD7, stringsREAD8, stringsREAD9, stringsREAD10, stringsREAD11,
                stringsREAD12, stringsREAD13, stringsREAD14, stringsREAD15, stringsREAD16, stringsREAD17,
                stringsREAD18, stringsREAD19, stringsREAD20};
        MyData myData = new MyData();

        //ตัด Cancel ออก
        for (int i = 0; i < stringsReadAll_MainTABLE[i].length; i++) {
            checkFinishDay = "N";
            checkStartDay = "N";
            checkWhich_Date_D = "N";
            if (stringsReadAll_MainTABLE[20][i].equals("")) {
                Log.d("UpdatesumTABLE", "ค่าตำแหน่งที่ 20 ว่าง : " + i);

                currentDay = myData.currentDay();  //ค่าของวันนี้
                Date dateToday = myData.stringChangetoDateWithOutTime(currentDay);
                Date dateStartDate = myData.stringChangetoDateWithOutTime(stringsReadAll_MainTABLE[9][i]);

                // เช็ค FinishDate ว่ายังต้องทานต่อหรือไม่
                if (!stringsReadAll_MainTABLE[10][i].equals("")) {
                    Date dateFinishDate = myData.stringChangetoDateWithOutTime(stringsReadAll_MainTABLE[10][i]);
                    Log.d("UpdatesumTABLE", "dateFinishDate  : " + dateFinishDate);
                    if (dateToday.compareTo(dateFinishDate) <= 0) {
                        checkFinishDay = "Y";
                    } else {
                        checkFinishDay = "N";
                    }

                } else {
                    checkFinishDay = "Y";
                }

                //เช็ค StartDate ว่าเริ่มหรือยัง
                if (dateToday.compareTo(dateStartDate) >= 0) {
                    checkStartDay = "Y";
                } else {
                    checkStartDay = "N";
                }

                String current_DayOfWeek = myData.current_DayOfWeek();  //ค่าเป็นเลข ของ DayofWeek
                String current_DayOfMonth = myData.current_DayOfMonth(); //ค่าเป็นเลข ของ DayofMonth
                //เช็ค ว่าตาม Which_Date_D วันนี้ต้องทานหรือไม่!!!
                String[] queryDay = stringsReadAll_MainTABLE[5][i].split(":");
                String[] querySelectedDay = null;

                if (!queryDay[0].equals("ED")) {
                    querySelectedDay = queryDay[1].split(",");
                    for (int w = 0; w < querySelectedDay.length; w++) {
                        Log.d("queryDay", "querySelectedDay[] : " + querySelectedDay[i]);
                        if (queryDay[0].equals("DOW")) {
                            if (querySelectedDay[i].equals(current_DayOfWeek)) {
                                checkWhich_Date_D = "Y";
                            }
                        }
                        if (queryDay[0].equals("DOM")) {
                            if (querySelectedDay[i].equals(current_DayOfMonth)) {
                                checkWhich_Date_D = "Y";
                            }
                        }
                    }

                } else {

                    checkWhich_Date_D = "Y";
                }


            } // stringsReadAll_MainTABLE[10][i].equals("")

            //Check ค่าทั้งหมดแล้วว่าผ่าน ให้ทำการ add ค่าเข้ามาได้

            if (checkWhich_Date_D.equals("Y") && checkStartDay.equals("Y") && checkFinishDay.equals("Y")) {
                Log.d("UpdatesumTABLE", "ตำแหน่งที่ i addค่าเข้า SumTABLE ได้ : " + i);
            } else {
                Log.d("UpdatesumTABLE", "ตำแหน่งที่ i addค่าเข้า SumTABLE ไม่ได้ : " + i);
            }


        } //first "For"


        /*
        stringsReadAll_MainTABLE[0] = myManage.readAllMainTABLE_Full(0);
        stringsReadAll_MainTABLE[1] = myManage.readAllMainTABLE_Full(1);
        stringsReadAll_MainTABLE[2] = myManage.readAllMainTABLE_Full(2);
        stringsReadAll_MainTABLE[3] = myManage.readAllMainTABLE_Full(3);
        stringsReadAll_MainTABLE[4] = myManage.readAllMainTABLE_Full(4);
        stringsReadAll_MainTABLE[5] = myManage.readAllMainTABLE_Full(5);
        stringsReadAll_MainTABLE[6] = myManage.readAllMainTABLE_Full(6);
        stringsReadAll_MainTABLE[7] = myManage.readAllMainTABLE_Full(7);
        stringsReadAll_MainTABLE[8] = myManage.readAllMainTABLE_Full(8);
        stringsReadAll_MainTABLE[9] = myManage.readAllMainTABLE_Full(9);
        stringsReadAll_MainTABLE[10] = myManage.readAllMainTABLE_Full(10);
        stringsReadAll_MainTABLE[11] = myManage.readAllMainTABLE_Full(11);
        stringsReadAll_MainTABLE[12] = myManage.readAllMainTABLE_Full(12);
        stringsReadAll_MainTABLE[13] = myManage.readAllMainTABLE_Full(13);
        stringsReadAll_MainTABLE[14] = myManage.readAllMainTABLE_Full(14);
        stringsReadAll_MainTABLE[15] = myManage.readAllMainTABLE_Full(15);
        stringsReadAll_MainTABLE[16] = myManage.readAllMainTABLE_Full(16);
        stringsReadAll_MainTABLE[17] = myManage.readAllMainTABLE_Full(17);
        stringsReadAll_MainTABLE[18] = myManage.readAllMainTABLE_Full(18);
        stringsReadAll_MainTABLE[19] = myManage.readAllMainTABLE_Full(19);
        stringsReadAll_MainTABLE[20] = myManage.readAllMainTABLE_Full(20);

        stringsReadAll_MainTABLE = new String[][]{myManage.readAllMainTABLE_Full(0),
                myManage.readAllMainTABLE_Full(1),myManage.readAllMainTABLE_Full(2),
                myManage.readAllMainTABLE_Full(3),myManage.readAllMainTABLE_Full(4),
                myManage.readAllMainTABLE_Full(5),myManage.readAllMainTABLE_Full(6),
                myManage.readAllMainTABLE_Full(7),myManage.readAllMainTABLE_Full(8),
                myManage.readAllMainTABLE_Full(9),myManage.readAllMainTABLE_Full(10),
                myManage.readAllMainTABLE_Full(11),myManage.readAllMainTABLE_Full(12),
                myManage.readAllMainTABLE_Full(13),myManage.readAllMainTABLE_Full(14),
                myManage.readAllMainTABLE_Full(15),myManage.readAllMainTABLE_Full(16),
                myManage.readAllMainTABLE_Full(17),myManage.readAllMainTABLE_Full(18),
                myManage.readAllMainTABLE_Full(19),myManage.readAllMainTABLE_Full(20)};
                */
        Toast.makeText(context, "Pass MyManage", Toast.LENGTH_LONG).show();


        //เอาค่าจากตาราง mainTABLE มาให้หมด





        /*
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


        */

    }
}  //Main Class
