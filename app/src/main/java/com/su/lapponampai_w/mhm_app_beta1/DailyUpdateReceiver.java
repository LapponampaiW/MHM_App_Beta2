package com.su.lapponampai_w.mhm_app_beta1;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Created by apple on 5/10/16.
 */
public class DailyUpdateReceiver extends BroadcastReceiver {

    //เมื่อถึงเวลาตามที่กำหนดจะ Saveข้อมูลเข้าตาราง SumTable อัตโนมัติ


    //Explicit
    private String[] mainIDStrings, timeRefStrings;
    private String dateString, stringCurrentAdd7;
    public String[] stringsREAD0, stringsREAD1, stringsREAD2,
            stringsREAD3, stringsREAD4, stringsREAD5, stringsREAD6, stringsREAD7, stringsREAD8,
            stringsREAD9, stringsREAD10, stringsREAD11, stringsREAD12, stringsREAD13, stringsREAD14,
            stringsREAD15, stringsREAD16, stringsREAD17, stringsREAD18, stringsREAD19, stringsREAD20,
            stringsDateRef;

    public String[] stringsDate_ED_Ref, stringsSumId_Ref, stringsNewDate_ED_Ref;

    public String currentDay, checkStartDay, checkFinishDay, checkWhich_Date_D, checkAlternativeAppearance;
    public MyManage myManage;
    public MyData myData;
    public int notifID = 100;
    public NotificationManager notificationManager;


    @Override
    public void onReceive(final Context context, final Intent intent) {

        Toast.makeText(context, "เข้าหน้า DailyUpdateReceiver.Java", Toast.LENGTH_LONG).show();
        Log.d("UpdatesumTABLE", "เข้าที่หน้า DailyUpdateReceiver.Java แล้ว");


        //เอาข้อมูลของยาที่ตั้งทานในวันนั้นๆ ทั้งหมดเข้ามาในหน้านี้
        //00:00 ถือเป็นของวันใหม่ไปเลยนะ

        myManage = new MyManage(context);
        myData = new MyData();


        //checkAndAddTabletInPillBox(context);
        //สรุป ไม่ทำใน BoardCast แล้วจร้าาา

        //notificatonSetupAndDisplay(context);
        updatesumTABLE_AND_Notification(context);


    } // onReceive

    private void updatesumTABLE_AND_Notification(Context context) {

        DailyUpdateReceiver dailyUpdateReceiver = new DailyUpdateReceiver();
        dailyUpdateReceiver.stringsREAD0 = myManage.readAllMainTABLE_Full(0);
        dailyUpdateReceiver.stringsREAD1 = myManage.readAllMainTABLE_Full(1);  //Main_id
        dailyUpdateReceiver.stringsREAD2 = myManage.readAllMainTABLE_Full(2);
        dailyUpdateReceiver.stringsREAD3 = myManage.readAllMainTABLE_Full(3);
        dailyUpdateReceiver.stringsREAD4 = myManage.readAllMainTABLE_Full(4);
        dailyUpdateReceiver.stringsREAD5 = myManage.readAllMainTABLE_Full(5);
        dailyUpdateReceiver.stringsREAD6 = myManage.readAllMainTABLE_Full(6);
        dailyUpdateReceiver.stringsREAD7 = myManage.readAllMainTABLE_Full(7);
        dailyUpdateReceiver.stringsREAD8 = myManage.readAllMainTABLE_Full(8);
        dailyUpdateReceiver.stringsREAD9 = myManage.readAllMainTABLE_Full(9); //StartDate
        dailyUpdateReceiver.stringsREAD10 = myManage.readAllMainTABLE_Full(10);
        dailyUpdateReceiver.stringsREAD11 = myManage.readAllMainTABLE_Full(11); //prn
        dailyUpdateReceiver.stringsREAD12 = myManage.readAllMainTABLE_Full(12); //t1
        dailyUpdateReceiver.stringsREAD13 = myManage.readAllMainTABLE_Full(13);
        dailyUpdateReceiver.stringsREAD14 = myManage.readAllMainTABLE_Full(14);
        dailyUpdateReceiver.stringsREAD15 = myManage.readAllMainTABLE_Full(15);
        dailyUpdateReceiver.stringsREAD16 = myManage.readAllMainTABLE_Full(16);
        dailyUpdateReceiver.stringsREAD17 = myManage.readAllMainTABLE_Full(17);
        dailyUpdateReceiver.stringsREAD18 = myManage.readAllMainTABLE_Full(18);
        dailyUpdateReceiver.stringsREAD19 = myManage.readAllMainTABLE_Full(19); //T8
        dailyUpdateReceiver.stringsREAD20 = myManage.readAllMainTABLE_Full(20);


        String[] strLast_updated = myManage.filter_userTABLE(5); //วันที่ในระบบล่าสุด
        Date dateLast_updated = myData.stringChangetoDateWithOutTime(strLast_updated[0]); //ในระบบล่าสุด type Date
        String currentDay = myData.currentDay(); //วันที่ของวันนี้
        Date dateInitial = myData.stringChangetoDateWithOutTime(currentDay); //วันที่ของวันนี้ Type Date


        Calendar calendarLast_updated = Calendar.getInstance();
        calendarLast_updated.setTime(dateLast_updated);
        calendarLast_updated.add(Calendar.DAY_OF_MONTH, 9); //วันที่ในระบบล่าสุด + 9 วัน
        Date dateRef = calendarLast_updated.getTime(); //วันที่ในระบบล่าสุด + 9 วัน type Date


        Calendar calendarCurrent = Calendar.getInstance();
        calendarCurrent.setTime(dateInitial);  //วันนี้
        calendarCurrent.add(Calendar.DAY_OF_MONTH, 9);
        Date dateFinalProcess = calendarCurrent.getTime(); //วันที่วันนี้ในระบบล่าสุด + 9 วัน

        String[][] stringsReadAll_MainTABLE = {dailyUpdateReceiver.stringsREAD0,
                dailyUpdateReceiver.stringsREAD1, dailyUpdateReceiver.stringsREAD2,
                dailyUpdateReceiver.stringsREAD3, dailyUpdateReceiver.stringsREAD4,
                dailyUpdateReceiver.stringsREAD5, dailyUpdateReceiver.stringsREAD6,
                dailyUpdateReceiver.stringsREAD7, dailyUpdateReceiver.stringsREAD8,
                dailyUpdateReceiver.stringsREAD9, dailyUpdateReceiver.stringsREAD10,
                dailyUpdateReceiver.stringsREAD11, dailyUpdateReceiver.stringsREAD12,
                dailyUpdateReceiver.stringsREAD13, dailyUpdateReceiver.stringsREAD14,
                dailyUpdateReceiver.stringsREAD15, dailyUpdateReceiver.stringsREAD16,
                dailyUpdateReceiver.stringsREAD17, dailyUpdateReceiver.stringsREAD18,
                dailyUpdateReceiver.stringsREAD19, dailyUpdateReceiver.stringsREAD20};

        if (dateRef.compareTo(dateFinalProcess) == 0) {
            Toast.makeText(context, "ได้ค่าเท่ากันของ dateRef กับ dateFinalProcess", Toast.LENGTH_SHORT);
        }


        //(1/10/16)
        if (!stringsReadAll_MainTABLE[0][0].equals("")) {
            while (dateRef.compareTo(dateFinalProcess) < 0) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(dateRef);
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                dateRef = calendar.getTime(); //เปลี่ยนค่าของ dateRef ให้มีค่าวันที่มากขึ้น 1 วัน

                String stringDateRef = myData.string_ddMMyyyy_ConvertedFromSpecificDate(dateRef); // ค่า Text ของวันที่ที่ต้องการเพิ่มเข้าใน sumTABLE


                //เริ่มยากจากตรงนี้!!!!

                for (int i = 0; i < stringsReadAll_MainTABLE[i].length; i++) {  // Loop เท่ากับจำนวนแถว
                    dailyUpdateReceiver.checkFinishDay = "N";
                    dailyUpdateReceiver.checkStartDay = "N";
                    dailyUpdateReceiver.checkWhich_Date_D = "N";
                    dailyUpdateReceiver.checkAlternativeAppearance = "N";
                    if (stringsReadAll_MainTABLE[20][i].equals("")) { //ยาถูก Cancel ไปหรือยัง!!!
                        Log.d("UpdatesumTABLE", "ค่าตำแหน่งที่ 20 ว่าง : " + i);
                        //ถ้ายายัง Active อยู่!!!


                        //currentDay = myData.currentDay();  //ค่าของวันนี้
                        //Date dateToday = myData.stringChangetoDateWithOutTime(currentDay);
                        Date dateStartDate = myData.stringChangetoDateWithOutTime(stringsReadAll_MainTABLE[9][i]); //ค่า Date ของ StartDate

                        // เช็ค FinishDate ว่ายังต้องทานต่อหรือไม่
                        if (!stringsReadAll_MainTABLE[10][i].equals("")) { //ถ้า FinishDate ไม่เท่ากับค่าว่าง แปลว่ามีวันที่ต้องหยุดทาน
                            Date dateFinishDate = myData.stringChangetoDateWithOutTime(stringsReadAll_MainTABLE[10][i]); //ค่า Date ของ FinishDate
                            Log.d("UpdatesumTABLE", "dateFinishDate  : " + dateFinishDate);
                            if (dateRef.compareTo(dateFinishDate) <= 0) {
                                dailyUpdateReceiver.checkFinishDay = "Y";
                            } else {
                                dailyUpdateReceiver.checkFinishDay = "N"; //แปลว่าเลยวันที่ต้องใช้มาแล้ว
                            }

                        } else {
                            dailyUpdateReceiver.checkFinishDay = "Y";
                        }

                        //เช็ค StartDate ว่าเริ่มหรือยัง
                        if (dateRef.compareTo(dateStartDate) >= 0) {
                            dailyUpdateReceiver.checkStartDay = "Y";
                        } else {
                            dailyUpdateReceiver.checkStartDay = "N";
                        }


                        //String current_DayOfWeek = myData.current_DayOfWeek();  //ค่าเป็นเลข ของ DayofWeek
                        //String current_DayOfMonth = myData.current_DayOfMonth(); //ค่าเป็นเลข ของ DayofMonth
                        String current_DayOfWeek = Integer.toString(calendar.get(Calendar.DAY_OF_WEEK)); //เอาค่าตัวเลขของวันประจำสัปดาห์จาก calendar ที่ทำการเพิ่มวันตั้งแต่ 0 - 6 เรียบร้อยแล้ว
                        String current_DayOfMonth = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)); //เอาค่าตัวเลขของวันประจำเดือนจาก calendar ที่ทำการเพิ่มวันตั้งแต่ 0 - 6 เรียบร้อยแล้ว
                        //เช็ค ว่าตาม Which_Date_D วันนี้ต้องทานหรือไม่!!!
                        String[] queryDay = stringsReadAll_MainTABLE[5][i].split(":");
                        String[] querySelectedDay = null;


                        if (!queryDay[0].equals("ED")) {
                            querySelectedDay = queryDay[1].split(",");
                            for (int w = 0; w < querySelectedDay.length; w++) {
                                Log.d("queryDay", "querySelectedDay[] : " + querySelectedDay[w]);
                                if (queryDay[0].equals("DOW")) {
                                    if (querySelectedDay[w].equals(current_DayOfWeek)) {
                                        dailyUpdateReceiver.checkWhich_Date_D = "Y";
                                    }
                                }
                                if (queryDay[0].equals("DOM")) {
                                    if (querySelectedDay[w].equals(current_DayOfMonth)) {
                                        dailyUpdateReceiver.checkWhich_Date_D = "Y";
                                    }
                                }
                            }

                        } else {  //ถ้าเป็น ED จะมี 0 1 2 3 4 5


                            stringsDate_ED_Ref = myManage.filter_sumTABLE_finding_DateRef_by_MainId_idDESC(stringsReadAll_MainTABLE[0][i]); //เอาค่า Main_id มา
                            stringsSumId_Ref = myManage.filter_sumTABLE_finding_SumId_by_MainId_idDESC(stringsReadAll_MainTABLE[0][i]); //เอาค่าSum_id มา

                            Toast.makeText(context, stringsDate_ED_Ref[0], Toast.LENGTH_LONG).show();

                            //02/12/2559 ทำการตรวจสอบว่าถ้าเป็น ค่าที่ Add จะไม่เกี่ยวข้อง

                            ArrayList<String> stringArrayList = new ArrayList<String>();
                            int iIndex = 0;

                            for (int x = 0; x < stringsDate_ED_Ref.length; x++) {

                                String[] stringsAddMedicine = myManage.filter_sumTABLE_AddMedicine_by_sum_id(stringsSumId_Ref[x]);
                                if (stringsAddMedicine[0].equals("")) {
                                    stringArrayList.add(iIndex, stringsDate_ED_Ref[x]);
                                    iIndex = iIndex + 1;
                                }
                            } //for

                            stringsNewDate_ED_Ref = new String[stringArrayList.size()];
                            stringsNewDate_ED_Ref = stringArrayList.toArray(stringsNewDate_ED_Ref);


                            Date date_ED_Ref = myData.stringChangetoDateWithOutTime(stringsNewDate_ED_Ref[0]); //dateRef ก่อนนำไป add ค่Calendar calendarRef = Calendar.getInstance();
                            Calendar calendarRef = Calendar.getInstance();
                            calendarRef.setTime(date_ED_Ref);  //calendarRef ก่อนนำไป add ค่า

                            //เทียบ date ที่ add ค่าแล้ว กับ dateAfterProcess จากข้างบน

                            if (queryDay[1].equals("0")) {
                                dailyUpdateReceiver.checkWhich_Date_D = "Y";
                            } else if (queryDay[1].equals("1")) {
                                //วันเว้นวัน
                                calendarRef.add(Calendar.DAY_OF_MONTH, 2);
                                Date date = calendarRef.getTime();
                                if (date.compareTo(dateRef) == 0) {
                                    dailyUpdateReceiver.checkWhich_Date_D = "Y";
                                }
                            } else if (queryDay[1].equals("2")) {
                                //วันเว้นวัน
                                calendarRef.add(Calendar.DAY_OF_MONTH, 3);
                                Date date = calendarRef.getTime();
                                if (date.compareTo(dateRef) == 0) {
                                    dailyUpdateReceiver.checkWhich_Date_D = "Y";
                                }
                            } else if (queryDay[1].equals("3")) {
                                //วันเว้นวัน
                                calendarRef.add(Calendar.DAY_OF_MONTH, 4);
                                Date date = calendarRef.getTime();
                                if (date.compareTo(dateRef) == 0) {
                                    dailyUpdateReceiver.checkWhich_Date_D = "Y";
                                }
                            } else if (queryDay[1].equals("4")) {
                                //วันเว้นวัน
                                calendarRef.add(Calendar.DAY_OF_MONTH, 5);
                                Date date = calendarRef.getTime();
                                if (date.compareTo(dateRef) == 0) {
                                    dailyUpdateReceiver.checkWhich_Date_D = "Y";
                                }
                            } else if (queryDay[1].equals("5")) {
                                //วันเว้นวัน
                                calendarRef.add(Calendar.DAY_OF_MONTH, 6);
                                Date date = calendarRef.getTime();
                                if (date.compareTo(dateRef) == 0) {
                                    dailyUpdateReceiver.checkWhich_Date_D = "Y";
                                }
                            }

                            //25/11/2559 เริ่มทำการ แสดงค่าตามวันที่ๆ กำหนดของยาคุมกำเนิด
                            else if (queryDay[1].equals("OCs")) {
                                //เอาวันที่เริ่มกินวันแรกก่อน
                                Date dStartOCs = myData.stringChangetoDateWithOutTime(queryDay[6]);
                                Calendar calendarStartOCs = Calendar.getInstance();
                                calendarStartOCs.setTime(dStartOCs);  //calendarOCs ก่อนเพิ่มค่า วันที่เข้าไป
                                Date dVariable = calendarStartOCs.getTime();

                                int iActivePill = Integer.parseInt(queryDay[2]);
                                int iPlacebo = Integer.parseInt(queryDay[3]);
                                String sTake_everyDay_Pill;
                                if (iPlacebo == 0) {
                                    sTake_everyDay_Pill = "N";
                                } else {
                                    sTake_everyDay_Pill = "Y";
                                }
                                int iTotalPill = Integer.parseInt(queryDay[4]);
                                int iPlaceboInterval = iTotalPill - iActivePill;


                                do {
                                    calendarStartOCs.add(Calendar.DAY_OF_MONTH, iActivePill - 1);
                                    dVariable = calendarStartOCs.getTime(); //ช่วงวันที่ยัง Active อยู่
                                    if (dVariable.compareTo(dateRef) >= 0) {
                                        dailyUpdateReceiver.checkWhich_Date_D = "Y";
                                    } else {
                                        calendarStartOCs.add(Calendar.DAY_OF_MONTH, iPlaceboInterval);
                                        dVariable = calendarStartOCs.getTime(); //ช่วงที่เลย Active แล้วยังอยู่ใน Placebo
                                        if (dVariable.compareTo(dateRef) >= 0) {
                                            if (sTake_everyDay_Pill.equals("N")) {
                                                dailyUpdateReceiver.checkWhich_Date_D = "N";
                                            } else {
                                                //ใส่ค่า img ของรูปอื่นเข้าไป เพื่อใช้แสดง ในอีก TABlE หนึ่ง
                                                dailyUpdateReceiver.checkWhich_Date_D = "Y";
                                                dailyUpdateReceiver.checkAlternativeAppearance = queryDay[5];
                                            }
                                        }
                                    }
                                } while (dVariable.compareTo(dateRef) < 0);
                            }


                        }


                    } // stringsReadAll_MainTABLE[10][i].equals("")

                    //Check ค่าทั้งหมดแล้วว่าผ่าน ให้ทำการ add ค่าเข้ามาได้

                    if (dailyUpdateReceiver.checkWhich_Date_D.equals("Y")
                            && dailyUpdateReceiver.checkStartDay.equals("Y")
                            && dailyUpdateReceiver.checkFinishDay.equals("Y")) {
                        Log.d("UpdatesumTABLE", "ตำแหน่งที่ i addค่าเข้า SumTABLE ได้ : " + i);
                        Toast.makeText(context, "Addข้อมูลลง sumTABLE : (Y,Y,Y)", Toast.LENGTH_SHORT).show();

                        //เริ่ม addข้อมูลลง sumTABLE
                        //1 row ของ mainTABLE add ได้หลาย row ของ sumTABLE ตาม T1-T8 (column 12 - 19)
                        for (int x = 12; x <= 19; x++) {
                            if (!stringsReadAll_MainTABLE[x][i].equals("")) {
                                String stringMain_id = stringsReadAll_MainTABLE[0][i];  //Main_id
                                String stringTimeRef = stringsReadAll_MainTABLE[x][i];  //TimeRef ตำแหน่งต่างๆ
                                myManage.addValueToSumTable(stringMain_id, stringDateRef, stringTimeRef, "", "", "", "");
                                //Log.d("UpdatesumTABLE", "addValueToSumTable : " + stringMain_id + " " + stringDateRef + " " + stringTimeRef);


                                if (!dailyUpdateReceiver.checkAlternativeAppearance.equals("N")) {
                                    Log.d("24Nov16", checkAlternativeAppearance + " " + stringDateRef);
                                    //หา sumid ก่อน
                                    String[] stringsFindSum_id = myManage.filter_sumTABLE_finding_SumId_by_MainId_idDESC(stringMain_id);
                                    if (!stringsFindSum_id[0].equals("")) {
                                        myManage.addValueTo_sumTABLE_alternativeImage(stringsFindSum_id[0], dailyUpdateReceiver.checkAlternativeAppearance);

                                    }
                                }


                            }


                        }

                    } else {
                        Log.d("UpdatesumTABLE", "ตำแหน่งที่ i addค่าเข้า SumTABLE ไม่ได้ : " + i);
                    }


                } //first "For"


            } //while
            String[] strUser = myManage.filter_userTABLE(1);
            String strDate = myData.currentDay();
            myManage.update_Last_updated(strUser[0], strDate);
        }  //ถ้า ยังไม่มีค่าในนี้ให้ boardcast อย่างเดียว
        //เปลี่ยนตรงนี้...08/10/2559
        //เปลี่ยนตรงนี้...14/10/2559

        broadcastAndAddNotification(context, myData, myManage);


    }

    public void broadcastAndAddNotification(Context context, MyData myData, MyManage myManage) {


        Intent alertIntent = new Intent(context, AlarmReceiver.class); //1

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE); //2


        for (int a = 0; a <= 200; a++) {
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, a, alertIntent, PendingIntent.FLAG_UPDATE_CURRENT); //3
            pendingIntent.cancel();
        }
        int a = 0;

        //เอาค่า Allow Nof
        String[] strings_Allow_Nof = myManage.filter_userTABLE(7);

        if (strings_Allow_Nof[0].equals("Y")) {
            for (int i = 0; i < 2; i++) {
                String sDate = myData.currentDay();
                Date dDate = myData.stringChangetoDateWithOutTime(sDate);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(dDate);
                calendar.add(Calendar.DAY_OF_MONTH, i);
                dDate = calendar.getTime();
                String sSpecificDate = myData.string_ddMMyyyy_ConvertedFromSpecificDate(dDate);

                String sCurrentDateTime = myData.currentDateTime_Withoutsecond();
                Date dCurrentDateTime = myData.stringChangetoDate(sCurrentDateTime);


                Log.d("14/10/2559", "1 : sSpecificDate : " + sSpecificDate);

                String[] strings_sumTABLE_id = myManage.filter_sumTABLE__by_Date(sSpecificDate, 0);
                String[] strings_sumTABLE_MainId = myManage.filter_sumTABLE__by_Date(sSpecificDate, 1);
                //String[] strings_sumTABLE_DateRef = myManage.filter_sumTABLE__by_Date(sSpecificDate, 2);
                String[] strings_sumTABLE_TimeRef = myManage.filter_sumTABLE__by_Date(sSpecificDate, 3);
                String[] strings_sumTABLE_DateCheck = myManage.filter_sumTABLE__by_Date(sSpecificDate, 4);
                String[] strings_sumTABLE_TimeCheck = myManage.filter_sumTABLE__by_Date(sSpecificDate, 5);
                String[] strings_sumTABLE_SkipHold = myManage.filter_sumTABLE__by_Date(sSpecificDate, 6);

                if (!strings_sumTABLE_id[0].equals("")) {
                    Log.d("14/10/2559", "1 : เข้า if1 : ");
                    //String sCurrentTime = myData.currentTime_Minus();
                    //Date dCurrentTime = myData.stringChangetoTime_Minute(sCurrentTime);

                    for (int x = 0; x < strings_sumTABLE_id.length; x++) {
                        if (strings_sumTABLE_DateCheck[x].equals("") && strings_sumTABLE_SkipHold[x].equals("")) {
                            Log.d("14/10/2559", "1 : เข้า if2 และ for");

                            //เพิ่ม
                            String stringDateTime = sSpecificDate + " " + strings_sumTABLE_TimeRef[x];
                            Date d_sumTABLE_DateTimeRef = myData.stringChangetoDate(stringDateTime);

                            //Date d_sumTABLE_TimeRef = myData.stringChangetoTime_Minute(strings_sumTABLE_TimeRef[x]);

                            if (dCurrentDateTime.compareTo(d_sumTABLE_DateTimeRef) <= 0) {
                                //เริ่ม boardcast

                                Calendar calendarCurrent = Calendar.getInstance();
                                Calendar myCalendarAlarm = (Calendar) calendarCurrent.clone(); //clone เวลาในเครื่องเข้ามาใช้

                                String stringAlarm = sSpecificDate + " " + strings_sumTABLE_TimeRef[x];

                                Log.d("14/10/2559", "2 : stringAlarm : " + stringAlarm);
                                Date dAlarm = myData.stringChangetoDate(stringAlarm);
                                myCalendarAlarm.setTime(dAlarm);

                                //24/10/2559 ส่งค่าไปกับ intent
                                alertIntent.putExtra("DailyUpdateIntent", strings_sumTABLE_id[x]);
                                Log.d("25/10/2559", "3 : strings_sumTABLE_id : " + strings_sumTABLE_id[x]);

                                PendingIntent pendingIntent = PendingIntent.getBroadcast(context, a, alertIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                                a = a + 1;


                                alarmManager.set(1, myCalendarAlarm.getTimeInMillis(), pendingIntent); //4


                                Log.d("14/10/2559", "3 : เข้า alarmManager");


                            }

                        }
                    }


                }

            }
        }


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


    public void checkAndAddTabletInPillBox(Context context) {

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

        String[] strLast_updated = myManage.filter_userTABLE(5); //วันที่ในระบบล่าสุด
        Date dateLast_updated = myData.stringChangetoDateWithOutTime(strLast_updated[0]);
        String currentDay = myData.currentDay();
        Date dateInitial = myData.stringChangetoDateWithOutTime(currentDay);
        Calendar calendarLast_updated = Calendar.getInstance();
        calendarLast_updated.setTime(dateLast_updated);
        calendarLast_updated.add(Calendar.DAY_OF_MONTH, 9);
        Date dateRef = calendarLast_updated.getTime();


        Calendar calendarCurrent = Calendar.getInstance();
        calendarCurrent.setTime(dateInitial);
        calendarCurrent.add(Calendar.DAY_OF_MONTH, 9);
        Date dateFinalProcess = calendarCurrent.getTime();


        /*
        String[] stringsDateRef = myManage.readAllsumTABLE_Full_Order_id_DESC(2); //check วันที่มีการ Add ยาลง sumTABLE ล่าสุด
        // ปัญหา ไม่สามารถหาวันของใน stringsDateRef ได้
        Date dateRef = myData.stringChangetoDateWithOutTime(stringsDateRef[0]); //ได้ค่า Date ที่มีอยู่ใน sumTABLE ล่าสุด
        Log.d("30/09/2559",myData.string_ddMMyyyy_ConvertedFromSpecificDate(dateRef)); //แสดง Log ที่มีในปัจจุบัน
        //ดูว่าอีก 9 วันข้างหน้าเป็นวันที่เท่าไหร่
        String currentDay = myData.currentDay();
        Date dateInitial = myData.stringChangetoDateWithOutTime(currentDay);
        Calendar calendarCurrent = Calendar.getInstance();
        calendarCurrent.setTime(dateInitial);
        calendarCurrent.add(Calendar.DAY_OF_MONTH,9);
        Date dateFinalProcess = calendarCurrent.getTime(); //ได้ Date ของวันที่ควรจะเป็นมาแล้ว(9 วันข้างหน้า)
        */

        String strCheckPRN = "Y";
        for (int i = 0; i < stringsREAD11.length; i++) {
            if (stringsREAD11[i].equals("N")) {
                strCheckPRN = "N";
            }
        }
        //มีถึงอีก 9 วันข้างหน้าแล้วหรือยัง
        String strDateRef = "N";
        if (dateRef.compareTo(dateFinalProcess) >= 0) {
            strDateRef = "Y";
        }


        if (stringsREAD0[0].equals("")) {
            Log.d("UpdatesumTABLE", "ไม่มียาใน mainTABLE : ค่าว่าง ยุติการ UpdateReceiver");
            Toast.makeText(context, "ไม่มียาใน mainTABLE : ค่าว่าง ยุติการ UpdateReceiver", Toast.LENGTH_LONG).show();
            return;
        } else if (strCheckPRN.equals("Y")) {
            Log.d("UpdatesumTABLE", "ยาใน mainTABLE มีแต่ยา PRN : ยุติการ UpdateReceiver");
            Toast.makeText(context, "ยาใน mainTABLE มีแต่ยา PRN :ยุติการ UpdateReceiver", Toast.LENGTH_LONG).show();
            return;
        }
        //ถ้าจะ Test การเอาเข้าให้เอา else if อันนี้ออกไป
        else if (strDateRef.equals("Y")) {
            Log.d("UpdatesumTABLE", "มีค่าวันนี้ใน sumTABLE ของวันนี้แล้ว : ยุติการ UpdateReceiver");
            Toast.makeText(context, "มีค่าวันนี้ใน sumTABLE ของวันนี้แล้ว : ยุติการ UpdateReceiver", Toast.LENGTH_LONG).show();
            return;
        } else {
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


            //(1/10/16)
            do {
                //String stringDateRef = myData.string_ddMMyyyy_ConvertedFromSpecificDate(dateRef);

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(dateRef);
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                dateRef = calendar.getTime(); //เปลี่ยนค่าของ dateRef ให้มีค่าวันที่มากขึ้น 1 วัน

                String stringDateRef = myData.string_ddMMyyyy_ConvertedFromSpecificDate(dateRef); // ค่า Text ของวันที่ที่ต้องการเพิ่มเข้าใน sumTABLE


                //เริ่มยากจากตรงนี้!!!!
                for (int i = 0; i < stringsReadAll_MainTABLE[i].length; i++) {  // Loop เท่ากับจำนวนแถว
                    checkFinishDay = "N";
                    checkStartDay = "N";
                    checkWhich_Date_D = "N";
                    if (stringsReadAll_MainTABLE[20][i].equals("")) { //ยาถูก Cancel ไปหรือยัง!!!
                        Log.d("UpdatesumTABLE", "ค่าตำแหน่งที่ 20 ว่าง : " + i);
                        //ถ้ายายัง Active อยู่!!!


                        //currentDay = myData.currentDay();  //ค่าของวันนี้
                        //Date dateToday = myData.stringChangetoDateWithOutTime(currentDay);
                        Date dateStartDate = myData.stringChangetoDateWithOutTime(stringsReadAll_MainTABLE[9][i]); //ค่า Date ของ StartDate

                        // เช็ค FinishDate ว่ายังต้องทานต่อหรือไม่
                        if (!stringsReadAll_MainTABLE[10][i].equals("")) { //ถ้า FinishDate ไม่เท่ากับค่าว่าง แปลว่ามีวันที่ต้องหยุดทาน
                            Date dateFinishDate = myData.stringChangetoDateWithOutTime(stringsReadAll_MainTABLE[10][i]); //ค่า Date ของ FinishDate
                            Log.d("UpdatesumTABLE", "dateFinishDate  : " + dateFinishDate);
                            if (dateRef.compareTo(dateFinishDate) <= 0) {
                                checkFinishDay = "Y";
                            } else {
                                checkFinishDay = "N"; //แปลว่าเลยวันที่ต้องใช้มาแล้ว
                            }

                        } else {
                            checkFinishDay = "Y";
                        }

                        //เช็ค StartDate ว่าเริ่มหรือยัง
                        if (dateRef.compareTo(dateStartDate) >= 0) {
                            checkStartDay = "Y";
                        } else {
                            checkStartDay = "N";
                        }


                        //String current_DayOfWeek = myData.current_DayOfWeek();  //ค่าเป็นเลข ของ DayofWeek
                        //String current_DayOfMonth = myData.current_DayOfMonth(); //ค่าเป็นเลข ของ DayofMonth
                        String current_DayOfWeek = Integer.toString(calendar.get(Calendar.DAY_OF_WEEK)); //เอาค่าตัวเลขของวันประจำสัปดาห์จาก calendar ที่ทำการเพิ่มวันตั้งแต่ 0 - 6 เรียบร้อยแล้ว
                        String current_DayOfMonth = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)); //เอาค่าตัวเลขของวันประจำเดือนจาก calendar ที่ทำการเพิ่มวันตั้งแต่ 0 - 6 เรียบร้อยแล้ว
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

                        } else {  //ถ้าเป็น ED จะมี 0 1 2 3 4 5


                            String[] stringsDate_ED_Ref = myManage.filter_sumTABLE_finding_DateRef_by_MainId_idDESC(stringsReadAll_MainTABLE[1][i]); //เอาค่า Main_id มา
                            Toast.makeText(context, stringsDate_ED_Ref[0], Toast.LENGTH_SHORT).show();
                            Date date_ED_Ref = myData.stringChangetoDateWithOutTime(stringsDate_ED_Ref[0]); //dateRef ก่อนนำไป add ค่Calendar calendarRef = Calendar.getInstance();

                            Calendar calendarRef = Calendar.getInstance();
                            calendarRef.setTime(date_ED_Ref);  //calendarRef ก่อนนำไป add ค่า

                            //เทียบ date ที่ add ค่าแล้ว กับ dateAfterProcess จากข้างบน

                            if (queryDay[1].equals("0")) {
                                checkWhich_Date_D = "Y";
                            } else if (queryDay[1].equals("1")) {
                                //วันเว้นวัน
                                calendarRef.add(Calendar.DAY_OF_MONTH, 2);
                                Date date = calendarRef.getTime();
                                if (date.compareTo(dateRef) == 0) {
                                    checkWhich_Date_D = "Y";
                                }
                            } else if (queryDay[1].equals("2")) {
                                //วันเว้นวัน
                                calendarRef.add(Calendar.DAY_OF_MONTH, 3);
                                Date date = calendarRef.getTime();
                                if (date.compareTo(dateRef) == 0) {
                                    checkWhich_Date_D = "Y";
                                }
                            } else if (queryDay[1].equals("3")) {
                                //วันเว้นวัน
                                calendarRef.add(Calendar.DAY_OF_MONTH, 4);
                                Date date = calendarRef.getTime();
                                if (date.compareTo(dateRef) == 0) {
                                    checkWhich_Date_D = "Y";
                                }
                            } else if (queryDay[1].equals("4")) {
                                //วันเว้นวัน
                                calendarRef.add(Calendar.DAY_OF_MONTH, 5);
                                Date date = calendarRef.getTime();
                                if (date.compareTo(dateRef) == 0) {
                                    checkWhich_Date_D = "Y";
                                }
                            } else if (queryDay[1].equals("5")) {
                                //วันเว้นวัน
                                calendarRef.add(Calendar.DAY_OF_MONTH, 6);
                                Date date = calendarRef.getTime();
                                if (date.compareTo(dateRef) == 0) {
                                    checkWhich_Date_D = "Y";
                                }
                            }


                        }


                    } // stringsReadAll_MainTABLE[10][i].equals("")


                    //Check ค่าทั้งหมดแล้วว่าผ่าน ให้ทำการ add ค่าเข้ามาได้

                    if (checkWhich_Date_D.equals("Y") && checkStartDay.equals("Y") && checkFinishDay.equals("Y")) {
                        Log.d("UpdatesumTABLE", "ตำแหน่งที่ i addค่าเข้า SumTABLE ได้ : " + i);
                        //Toast.makeText(context, "Addข้อมูลลง sumTABLE : (Y,Y,Y)", Toast.LENGTH_LONG).show();

                        //เริ่ม addข้อมูลลง sumTABLE
                        //1 row ของ mainTABLE add ได้หลาย row ของ sumTABLE ตาม T1-T8 (column 12 - 19)
                        for (int x = 12; x <= 19; x++) {
                            if (!stringsReadAll_MainTABLE[x][i].equals("")) {
                                String stringMain_id = stringsReadAll_MainTABLE[0][i];  //Main_id
                                String stringTimeRef = stringsReadAll_MainTABLE[x][i];  //TimeRef ตำแหน่งต่างๆ
                                myManage.addValueToSumTable(stringMain_id, stringDateRef, stringTimeRef, "", "", "", "");
                                Log.d("UpdatesumTABLE", "addValueToSumTable : " + stringMain_id + " " + stringDateRef + " " + stringTimeRef);
                            }


                        }

                    } else {
                        Log.d("UpdatesumTABLE", "ตำแหน่งที่ i addค่าเข้า SumTABLE ไม่ได้ : " + i);
                    }


                } //first "For"

            } while (dateRef.compareTo(dateFinalProcess) < 0);



            /*

            for (int i = 0; i < stringsReadAll_MainTABLE[i].length; i++) {  // Loop เท่ากับจำนวนแถว
                checkFinishDay = "N";
                checkStartDay = "N";
                checkWhich_Date_D = "N";
                if (stringsReadAll_MainTABLE[20][i].equals("")) { //ยาถูก Cancel ไปหรือยัง!!!
                    Log.d("UpdatesumTABLE", "ค่าตำแหน่งที่ 20 ว่าง : " + i);
                    //ถ้ายายัง Active อยู่!!!
                    currentDay = myData.currentDay();  //ค่าของวันนี้
                    Date dateToday = myData.stringChangetoDateWithOutTime(currentDay);
                    Date dateStartDate = myData.stringChangetoDateWithOutTime(stringsReadAll_MainTABLE[9][i]); //ค่า Date ของ StartDate

                    // เช็ค FinishDate ว่ายังต้องทานต่อหรือไม่
                    if (!stringsReadAll_MainTABLE[10][i].equals("")) { //ถ้า FinishDate ไม่เท่ากับค่าว่าง แปลว่ามีวันที่ต้องหยุดทาน
                        Date dateFinishDate = myData.stringChangetoDateWithOutTime(stringsReadAll_MainTABLE[10][i]); //ค่า Date ของ FinishDate
                        Log.d("UpdatesumTABLE", "dateFinishDate  : " + dateFinishDate);
                        if (dateToday.compareTo(dateFinishDate) <= 0) {
                            checkFinishDay = "Y";
                        } else {
                            checkFinishDay = "N"; //แปลว่าเลยวันที่ต้องใช้มาแล้ว
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
                        // เพิ่ม ED ตรงนี้
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


            */

            String[] strUser = myManage.filter_userTABLE(1);
            myManage.update_Last_updated(strUser[0], currentDay);
        }


    } //วงเล็บ
}  //Main Class
