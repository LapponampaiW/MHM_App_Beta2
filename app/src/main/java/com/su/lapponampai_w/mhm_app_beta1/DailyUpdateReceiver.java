package com.su.lapponampai_w.mhm_app_beta1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by apple on 5/10/16.
 */
public class DailyUpdateReceiver extends BroadcastReceiver {

    //เมื่อถึงเวลาตามที่กำหนดจะ Saveข้อมูลเข้าตาราง SumTable อัตโนมัติ


    //Explicit
    private String[] mainIDStrings, timeRefStrings;
    private String dateString,stringCurrentAdd7;
    private String[] stringsREAD0, stringsREAD1, stringsREAD2,
            stringsREAD3, stringsREAD4, stringsREAD5, stringsREAD6, stringsREAD7, stringsREAD8,
            stringsREAD9, stringsREAD10, stringsREAD11, stringsREAD12, stringsREAD13, stringsREAD14,
            stringsREAD15, stringsREAD16, stringsREAD17, stringsREAD18, stringsREAD19, stringsREAD20,
            stringsDateRef;

    private String currentDay, checkStartDay, checkFinishDay, checkWhich_Date_D;


    @Override
    public void onReceive(Context context, Intent intent) {


        Log.d("UpdatesumTABLE", "เข้าที่หน้า DailyUpdateReceiver.Java แล้ว");


        //เอาข้อมูลของยาที่ตั้งทานในวันนั้นๆ ทั้งหมดเข้ามาในหน้านี้
        //00:00 ถือเป็นของวันใหม่ไปเลยนะ

        MyManage myManage = new MyManage(context);
        MyData myData = new MyData();

        //รับค่า mainTABLE มาทั้งหมด

        stringsREAD0 = myManage.readAllMainTABLE_Full(0);
        stringsREAD1 = myManage.readAllMainTABLE_Full(1);  //Main_id
        stringsREAD2 = myManage.readAllMainTABLE_Full(2);
        stringsREAD3 = myManage.readAllMainTABLE_Full(3);
        stringsREAD4 = myManage.readAllMainTABLE_Full(4);
        stringsREAD5 = myManage.readAllMainTABLE_Full(5);
        stringsREAD6 = myManage.readAllMainTABLE_Full(6);
        stringsREAD7 = myManage.readAllMainTABLE_Full(7);
        stringsREAD8 = myManage.readAllMainTABLE_Full(8);
        stringsREAD9 = myManage.readAllMainTABLE_Full(9); //StartDate
        stringsREAD10 = myManage.readAllMainTABLE_Full(10);
        stringsREAD11 = myManage.readAllMainTABLE_Full(11); //prn
        stringsREAD12 = myManage.readAllMainTABLE_Full(12); //t1
        stringsREAD13 = myManage.readAllMainTABLE_Full(13);
        stringsREAD14 = myManage.readAllMainTABLE_Full(14);
        stringsREAD15 = myManage.readAllMainTABLE_Full(15);
        stringsREAD16 = myManage.readAllMainTABLE_Full(16);
        stringsREAD17 = myManage.readAllMainTABLE_Full(17);
        stringsREAD18 = myManage.readAllMainTABLE_Full(18);
        stringsREAD19 = myManage.readAllMainTABLE_Full(19); //T8
        stringsREAD20 = myManage.readAllMainTABLE_Full(20);  //TimeDateCanceled



        stringsDateRef = myManage.readAllsumTABLE_Full(2); //ค่า DateRef แบบ DESC ถ้ามีค่าของวันนี้แล้ว ก็ return
        currentDay = myData.currentDay();  //ค่าของวันนี้

        //ทำ Ref อีก 7 วันขึ้นหน้า
        Calendar calendarCurrent = Calendar.getInstance();
        calendarCurrent.add(Calendar.DAY_OF_MONTH,7);
        Date dateCurrent = calendarCurrent.getTime(); //อีก 7 วันนับจากวันนี้ ของ smartPhone

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        stringCurrentAdd7 = dateFormat.format(dateCurrent); //ใช้อันนี้

        Log.d("29/09/16V1", stringCurrentAdd7);

        //29/09/16 เสร็จสิ้นที่ทำใหม่


        String strCheckPRN = "Y";
        for(int i = 0;i<stringsREAD11.length;i++) {
            if (stringsREAD11[i].equals("N")) {
                strCheckPRN = "N";
            }
        }
        //มีวันนี้แล้วหรือยัง
        String strDateRef = "N";
        for(int x = 0;x<stringsDateRef.length;x++) {
            if (stringsDateRef[x].equals(currentDay)) { // 290916 เปลี่ยนจาก currentDay เป็น อีก 7 วัน
                strDateRef = "Y";
            }
        }


        if (stringsREAD0[0].equals("")) {
            Log.d("UpdatesumTABLE", "ไม่มียาใน mainTABLE : ค่าว่าง ยุติการ UpdateReceiver");
            Toast.makeText(context,"ไม่มียาใน mainTABLE : ค่าว่าง ยุติการ UpdateReceiver",Toast.LENGTH_LONG).show();
            return;
        }
        else if (strCheckPRN.equals("Y")) {
            Log.d("UpdatesumTABLE", "ยาใน mainTABLE มีแต่ยา PRN : ยุติการ UpdateReceiver");
            Toast.makeText(context,"ยาใน mainTABLE มีแต่ยา PRN :ยุติการ UpdateReceiver",Toast.LENGTH_LONG).show();
            return;
        }
        //ถ้าจะ Test การเอาเข้าให้เอา else if อันนี้ออกไป
        else if (strDateRef.equals("Y")) {
            Log.d("UpdatesumTABLE", "มีค่าวันนี้ใน sumTABLE ของวันนี้แล้ว : ยุติการ UpdateReceiver");
            Toast.makeText(context,"มีค่าวันนี้ใน sumTABLE ของวันนี้แล้ว : ยุติการ UpdateReceiver",Toast.LENGTH_LONG).show();
            return;
        }
        else {
            //เริ่มทำการ Add ค่าของวันนี้ลงใน sumTABLE
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
                            Log.d("queryDay", "querySelectedDay[] : " + querySelectedDay[w]);
                            if (queryDay[0].equals("DOW")) {
                                if (querySelectedDay[w].equals(current_DayOfWeek)) {
                                    checkWhich_Date_D = "Y";
                                }
                            }
                            if (queryDay[0].equals("DOM")) {
                                if (querySelectedDay[w].equals(current_DayOfMonth)) {
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
                    Toast.makeText(context,"Addข้อมูลลง sumTABLE : (Y,Y,Y)",Toast.LENGTH_LONG).show();

                    //เริ่ม addข้อมูลลง sumTABLE
                    //1 row ของ mainTABLE add ได้หลาย row ของ sumTABLE ตาม T1-T8 (column 12 - 19)
                    for(int x = 12;x<=19;x++) {
                        if (!stringsReadAll_MainTABLE[x][i].equals("")) {
                            String stringMain_id = stringsReadAll_MainTABLE[0][i];  //Main_id
                            String stringTimeRef = stringsReadAll_MainTABLE[x][i];  //TimeRef ตำแหน่งต่างๆ
                            myManage.addValueToSumTable(stringMain_id, currentDay, stringTimeRef, "", "", "");
                            Log.d("UpdatesumTABLE", "addValueToSumTable : " + stringMain_id + " " + currentDay + " " + stringTimeRef);
                        }


                    }

                } else {
                    Log.d("UpdatesumTABLE", "ตำแหน่งที่ i addค่าเข้า SumTABLE ไม่ได้ : " + i);
                }


            } //first "For"
        }





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
