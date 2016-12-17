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
import android.support.v7.app.AppCompatActivity;
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
    private SQLiteDatabase writeSqLiteDatabase;
    String[] strings_alarmReceiverTABLE_id,strings_alarmReceiverTABLEAfter15Min_id;

    MyHelper myHelper;



    @Override
    public void onReceive(final Context context, final Intent intent) {


        //เอาข้อมูลของยาที่ตั้งทานในวันนั้นๆ ทั้งหมดเข้ามาในหน้านี้
        //00:00 ถือเป็นของวันใหม่ไปเลยนะ

        myManage = new MyManage(context);
        myData = new MyData();
        myHelper = new MyHelper(context);
        writeSqLiteDatabase = myHelper.getWritableDatabase();


        //checkAndAddTabletInPillBox(context);
        //สรุป ไม่ทำใน BoardCast แล้วจร้าาา

        //notificatonSetupAndDisplay(context);
        updatesumTABLE_AND_Notification(context);

        updateAppointment_AND_Notification(context);


    } // onReceive

    private void updateAppointment_AND_Notification(Context context) {

        Toast.makeText(context,"เข้า 1",Toast.LENGTH_LONG).show();


        String[] stringsAppointmentId = myManage.readAllappointmentTABLE(0);
        String[] stringsAppointmentDate = myManage.readAllappointmentTABLE(2); //วันนัด
        String[] stringsAppointmentSnooze = myManage.readAllappointmentTABLE(6); //ดูว่าเตือน Notif หรือไม่

        Intent alertIntent = new Intent(context, AlarmReceiver.class); //1
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE); //2


        for (int a = 100; a <= 140; a++) {
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, a, alertIntent, PendingIntent.FLAG_UPDATE_CURRENT); //3
            pendingIntent.cancel();

        }

        int a = 100;


        if (!stringsAppointmentId[0].equals("")) {
            Toast.makeText(context,"เข้า 2",Toast.LENGTH_LONG).show();
            //อ่านค่า วันที่ต้องทำการ Notificationจาก userTABLE
            String[] stringsUserTABLE_Appointment_notif = myManage.filter_userTABLE(10); //วันที่ก่อนต้องการ Notif
            if (!stringsUserTABLE_Appointment_notif[0].equals("N")) {
                String[] queryDateTimeAppointmentRef = stringsUserTABLE_Appointment_notif[0].split(";");
                String sCurrentDay = myData.currentDay(); //วันที่วันนี้ไม่ต้องเอาเวลา
                Date dCurrentDay = myData.stringChangetoDate(sCurrentDay); //Date
                Toast.makeText(context,"เข้า 3",Toast.LENGTH_LONG).show();

                for(int i = 0;i< stringsAppointmentId.length;i++) {
                    Toast.makeText(context,"เข้า 4",Toast.LENGTH_LONG).show();
                    if (stringsAppointmentSnooze[i].equals("Y")) {
                        Toast.makeText(context,"เข้า 5",Toast.LENGTH_LONG).show();
                        Date dateAppointment = myData.stringChangetoDateWithOutTime(stringsAppointmentDate[i]); //ค่า Date วันนัด
                        //ต้องหาค่า Date ของ 3 วันก่อนด้วย
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(dateAppointment);
                        calendar.add(Calendar.DAY_OF_MONTH,Integer.parseInt(queryDateTimeAppointmentRef[0]) * -1);
                        Date dateAppointmentMinusRef = calendar.getTime(); //ค่า Date ก่อนวันนัด ลบตาม Ref


                        if (dCurrentDay.compareTo(dateAppointment) <= 0 &&
                                dCurrentDay.compareTo(dateAppointmentMinusRef) >= 0) {

                            Toast.makeText(context,"เตรียมทำ Noti",Toast.LENGTH_LONG).show();

                            Calendar calendarCurrent = Calendar.getInstance();
                            Calendar myCalendarAlarm = (Calendar) calendarCurrent.clone(); //clone เวลาในเครื่องเข้ามาใช้
                            String sTime = dCurrentDay + " " + queryDateTimeAppointmentRef[1];
                            Date dTime = myData.stringChangetoDate(sTime);
                            myCalendarAlarm.setTime(dTime);

                            //16/12/2559 ส่งค่าไปกับ intent
                            alertIntent.putExtra("DailyUpdateIntent", stringsAppointmentId[i]);
                            alertIntent.putExtra("DailyUpdateIntentTime", sTime);
                            alertIntent.putExtra("DailyUpdateTimeNof", "AppointmentTABLE");
                            alertIntent.putExtra("notifID", a);
                            //Log.d("25/10/2559", "3 : strings_sumTABLE_id : " + stringsAlarmId[x]);


                            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, a, alertIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                            a = a + 1;
                            alarmManager.set(1, myCalendarAlarm.getTimeInMillis(), pendingIntent); //4
                        }
                    }
                } //for
            }
        }
    }

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

        //broadcastAndAddNotification(context, myData, myManage);

        addDataToBroadcastTABLE(context, myData, myManage);





    }

    private void addDataToBroadcastTABLE(Context context, MyData myData, MyManage myManage) {

        //ลบข้อมูลท้งหมดในตาราง alarmReceiverTABLE
        writeSqLiteDatabase.delete("alarmReceiverTABLE", null, null);
        writeSqLiteDatabase.delete("alarmReceiverTABLEAfter15Min", null, null);

        Intent alertIntent = new Intent(context, AlarmReceiver.class); //1
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE); //2


        for (int a = 0; a <= 40; a++) {
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, a, alertIntent, PendingIntent.FLAG_UPDATE_CURRENT); //3
            pendingIntent.cancel();

        }

        int a = 0;

        //เอาค่า Allow Nof
        String[] strings_Allow_Nof = myManage.filter_userTABLE(7); //อนุญาติให้มี notification ได้


        if (strings_Allow_Nof[0].equals("Y")) {

            for (int i = 0; i < 2; i++) {
                String sDate = myData.currentDay();
                Date dDate = myData.stringChangetoDateWithOutTime(sDate);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(dDate);
                calendar.add(Calendar.DAY_OF_MONTH, i); //ตั้งไว้ 2 วัน วันนี้กับพรุ่งนี้
                dDate = calendar.getTime();
                String sSpecificDate = myData.string_ddMMyyyy_ConvertedFromSpecificDate(dDate);

                String sCurrentDateTime = myData.currentDateTime_Withoutsecond(); //วันเวลาปัจจุบันไม่เอาวินาที
                Date dCurrentDateTime = myData.stringChangetoDate(sCurrentDateTime); //เปรียนเป็น Date ใช้ในการเปรียบเทียบ


                Log.d("14/10/2559", "1 : sSpecificDate : " + sSpecificDate);

                String[] strings_sumTABLE_id = myManage.filter_sumTABLE__by_Date(sSpecificDate, 0);
                String[] strings_sumTABLE_MainId = myManage.filter_sumTABLE__by_Date(sSpecificDate, 1);
                //String[] strings_sumTABLE_DateRef = myManage.filter_sumTABLE__by_Date(sSpecificDate, 2);
                String[] strings_sumTABLE_TimeRef = myManage.filter_sumTABLE__by_Date(sSpecificDate, 3);
                String[] strings_sumTABLE_DateCheck = myManage.filter_sumTABLE__by_Date(sSpecificDate, 4);
                String[] strings_sumTABLE_TimeCheck = myManage.filter_sumTABLE__by_Date(sSpecificDate, 5);
                String[] strings_sumTABLE_SkipHold = myManage.filter_sumTABLE__by_Date(sSpecificDate, 6);


                String[] strTimeNof = myManage.filter_userTABLE(9); //หา TimeNof ว่าเป็น 1 หรือ 2

                if (!strings_sumTABLE_id[0].equals("")) { //มีค่าของวันนี้
                    Log.d("14/10/2559", "1 : เข้า if1 : ");
                    //String sCurrentTime = myData.currentTime_Minus();
                    //Date dCurrentTime = myData.stringChangetoTime_Minute(sCurrentTime);

                    for (int x = 0; x < strings_sumTABLE_id.length; x++) { //เอาทุกค่าของวันนี้มา Check //start ที่นี่
                        if (strings_sumTABLE_DateCheck[x].equals("") && strings_sumTABLE_SkipHold[x].equals("")) {
                            Log.d("14/10/2559", "1 : เข้า if2 และ for"); //DateCheck เป็นค่าว่างและ SkipHold เป็นค่าว่าง
                            //หมายถึง ต้องการค่าให้ ทำการ AlarmReceiver
                            //061256 เริ่ม update ข้อมูลลงในตาราง alarmreceiverTABLET
                            String stringDateTime = sSpecificDate + " " + strings_sumTABLE_TimeRef[x];
                            Date d_sumTABLE_DateTimeRef = myData.stringChangetoDate(stringDateTime);

                            //เริ่มวันที่ 13/12/59 เพิ่มการลบ 15 นาทีเข้าไป
                            Calendar calendarMinus_15Min = Calendar.getInstance();
                            calendarMinus_15Min.setTime(d_sumTABLE_DateTimeRef);
                            calendarMinus_15Min.add(Calendar.MINUTE,-15);
                            Date d_sumTABLE_DateTimeMinus_15Min = calendarMinus_15Min.getTime();

                            Calendar calendarAdd_15Min = Calendar.getInstance();
                            calendarAdd_15Min.setTime(d_sumTABLE_DateTimeRef);
                            calendarAdd_15Min.add(Calendar.MINUTE,15);
                            Date d_sumTABLE_DateTimeAdd_15Min = calendarAdd_15Min.getTime();
                            String stringDateTimeAfter15Min = myData.string_ddMMyyyy_HHmm_ConvertedFromSpecificDate(d_sumTABLE_DateTimeAdd_15Min);


                            //ทำ 13/12/59 ถึงตรงนี้

                            if (dCurrentDateTime.compareTo(d_sumTABLE_DateTimeRef) <= 0) {
                                //เริ่ม ใส่ข้อมูล ต้องทำ mymanage เป็น addValue + update + filter
                                strings_alarmReceiverTABLE_id = myManage.filteralarmReceiverTABLE_by_DateTimereceiver(stringDateTime, 0);

                                if (strings_alarmReceiverTABLE_id[0].equals("")) {
                                    //ถ้าเป็นค่าว่างแปลว่าให้ addValue ลงไปโดยมี 3 ค่า
                                    //_id,DateTime,Sum_id1
                                    myManage.addValueTo_alarmReceiverTABLE_Sumid1(stringDateTime, strings_sumTABLE_id[x]);

                                } else {
                                    //ถ้าไม่เป็นค่าว่างแปลว่ามีค่าอยู่แล้ว
                                    String[] strings_DateTime = myManage.filteralarmReceiverTABLE_by_DateTimereceiver(stringDateTime, 1);
                                    String[] strings_Sumid1 = myManage.filteralarmReceiverTABLE_by_DateTimereceiver(stringDateTime, 2);
                                    String[] strings_Sumid2 = myManage.filteralarmReceiverTABLE_by_DateTimereceiver(stringDateTime, 3);
                                    String[] strings_Sumid3 = myManage.filteralarmReceiverTABLE_by_DateTimereceiver(stringDateTime, 4);
                                    String[] strings_Sumid4 = myManage.filteralarmReceiverTABLE_by_DateTimereceiver(stringDateTime, 5);
                                    String[] strings_Sumid5 = myManage.filteralarmReceiverTABLE_by_DateTimereceiver(stringDateTime, 6);
                                    String[] strings_Sumid6 = myManage.filteralarmReceiverTABLE_by_DateTimereceiver(stringDateTime, 7);
                                    String[] strings_Sumid7 = myManage.filteralarmReceiverTABLE_by_DateTimereceiver(stringDateTime, 8);
                                    String[] strings_Sumid8 = myManage.filteralarmReceiverTABLE_by_DateTimereceiver(stringDateTime, 9);
                                    String[] strings_Sumid9 = myManage.filteralarmReceiverTABLE_by_DateTimereceiver(stringDateTime, 10);

                                    String[] strings_alarmReceiverTABLE_SumId = {strings_Sumid1[0],strings_Sumid2[0],
                                            strings_Sumid3[0],strings_Sumid4[0],strings_Sumid5[0],strings_Sumid6[0],strings_Sumid7[0],strings_Sumid8[0]
                                            ,strings_Sumid9[0]};

                                    if (strings_alarmReceiverTABLE_SumId[8].equals("")) {
                                        Boolean aBoolean = true;
                                        int ss = 1;
                                        while (aBoolean) {
                                            if (strings_alarmReceiverTABLE_SumId[ss].equals("")) {
                                                myManage.update_alarmReceiverTABLE_SumId(strings_alarmReceiverTABLE_id[0], ss, strings_sumTABLE_id[x]);
                                                aBoolean = false;
                                            } else {
                                                ss = ss + 1;
                                            }

                                        } //while
                                    } //ถ้าไม่ใช่ค่าว่างก็ข้ามไปเลยเพราะ แสดงบนหน้าจอไม่ได้แล้ว
                                }

                                //ทำ alarmReceiverTABLE สำเร็จ
                            } //if

                            //13/12/59 เพิ่ม Notification เป็น 2 ครั้ง
                            if (strTimeNof[0].equals("2")) {
                                    if (dCurrentDateTime.compareTo(d_sumTABLE_DateTimeAdd_15Min) <= 0) {
                                        //ดูว่า 15 นาทีก่อนหน้านี้มียาที่ยังไม่ได้กินอยู่หรือไม่จะได้ทำการ Notification หลังจากเวลา
                                        strings_alarmReceiverTABLEAfter15Min_id = myManage.filteralarmReceiverTABLEAfter15Min_by_DateTimereceiver
                                                (stringDateTimeAfter15Min, 0);

                                        if (strings_alarmReceiverTABLEAfter15Min_id[0].equals("")) {
                                            //ถ้าเป็นค่าว่างแปลว่าให้ addValue ลงไปโดยมี 3 ค่า
                                            //_id,DateTime,Sum_id1
                                            myManage.addValueTo_alarmReceiverTABLEAfter15Min_Sumid1(stringDateTimeAfter15Min, strings_sumTABLE_id[x]);

                                        } else {
                                            //ถ้าไม่เป็นค่าว่างแปลว่ามีค่าอยู่แล้ว
                                            String[] strings_DateTime = myManage.filteralarmReceiverTABLEAfter15Min_by_DateTimereceiver(stringDateTimeAfter15Min, 1);
                                            String[] strings_Sumid1 = myManage.filteralarmReceiverTABLEAfter15Min_by_DateTimereceiver(stringDateTimeAfter15Min, 2);
                                            String[] strings_Sumid2 = myManage.filteralarmReceiverTABLEAfter15Min_by_DateTimereceiver(stringDateTimeAfter15Min, 3);
                                            String[] strings_Sumid3 = myManage.filteralarmReceiverTABLEAfter15Min_by_DateTimereceiver(stringDateTimeAfter15Min, 4);
                                            String[] strings_Sumid4 = myManage.filteralarmReceiverTABLEAfter15Min_by_DateTimereceiver(stringDateTimeAfter15Min, 5);
                                            String[] strings_Sumid5 = myManage.filteralarmReceiverTABLEAfter15Min_by_DateTimereceiver(stringDateTimeAfter15Min, 6);
                                            String[] strings_Sumid6 = myManage.filteralarmReceiverTABLEAfter15Min_by_DateTimereceiver(stringDateTimeAfter15Min, 7);
                                            String[] strings_Sumid7 = myManage.filteralarmReceiverTABLEAfter15Min_by_DateTimereceiver(stringDateTimeAfter15Min, 8);
                                            String[] strings_Sumid8 = myManage.filteralarmReceiverTABLEAfter15Min_by_DateTimereceiver(stringDateTimeAfter15Min, 9);
                                            String[] strings_Sumid9 = myManage.filteralarmReceiverTABLEAfter15Min_by_DateTimereceiver(stringDateTimeAfter15Min, 10);

                                            String[] strings_alarmReceiverTABLE_SumId = {strings_Sumid1[0], strings_Sumid2[0],
                                                    strings_Sumid3[0], strings_Sumid4[0], strings_Sumid5[0], strings_Sumid6[0], strings_Sumid7[0], strings_Sumid8[0]
                                                    , strings_Sumid9[0]};


                                            if (strings_alarmReceiverTABLE_SumId[8].equals("")) {
                                                Boolean aBoolean = true;
                                                int ss = 1;
                                                while (aBoolean) {
                                                    if (strings_alarmReceiverTABLE_SumId[ss].equals("")) {
                                                        myManage.update_alarmReceiverTABLEAfter15Min_SumId
                                                                (strings_alarmReceiverTABLEAfter15Min_id[0], ss, strings_sumTABLE_id[x]);
                                                        aBoolean = false;
                                                    } else {
                                                        ss = ss + 1;
                                                    }

                                                } //while
                                            } //ถ้าไม่ใช่ค่าว่างก็ข้ามไปเลยเพราะ แสดงบนหน้าจอไม่ได้แล้ว
                                        }
                                    }

                            } //if จบ 13/12/59

                        } //ถ้าต้องมีการแจ้งเตือน
                    } //First for
                }
            } //for แรก!!!




            //เอาค่าทั้งหมดของ _id กับ DateTime ในตาราง alarmReceiverTABLE มา
            if (!myManage.readAllalarmReceiverTABLE(0)[0].equals("")) {
                updateAlarmReceiver(context,myManage.readAllalarmReceiverTABLE(0),myManage.readAllalarmReceiverTABLE(1),
                        myManage.readAllalarmReceiverTABLEAfter15Min(0),myManage.readAllalarmReceiverTABLEAfter15Min(1));
            }
        } //first if
    } //addDataToBroadcastTABLE

    private void updateAlarmReceiver(Context context,String[] stringsAlarmId,
                                     String[] stringsAlarmDateTime,
                                     String[] stringsAlarmId2,
                                     String[] stringsAlarmDateTime2) {

        Intent alertIntent = new Intent(context, AlarmReceiver.class); //1
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE); //2
        int a = 0;

        Calendar calendarCurrent = Calendar.getInstance();
        Calendar myCalendarAlarm = (Calendar) calendarCurrent.clone(); //clone เวลาในเครื่องเข้ามาใช้

        //String[] strTimeNof = myManage.filter_userTABLE(9); //หา TimeNof ว่าเป็น 1 หรือ 2

        for(int x =0 ;x < stringsAlarmId.length;x++) {

            String stringAlarm = stringsAlarmDateTime[x];
            Date dAlarm = myData.stringChangetoDate(stringAlarm);
            myCalendarAlarm.setTime(dAlarm);

                //24/10/2559 ส่งค่าไปกับ intent
            alertIntent.putExtra("DailyUpdateIntent", stringsAlarmId[x]);
            alertIntent.putExtra("DailyUpdateIntentTime", stringsAlarmDateTime[x]);
            alertIntent.putExtra("DailyUpdateTimeNof", "1");
            alertIntent.putExtra("notifID", a);
                //Log.d("25/10/2559", "3 : strings_sumTABLE_id : " + stringsAlarmId[x]);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, a, alertIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            a = a + 1;
            alarmManager.set(1, myCalendarAlarm.getTimeInMillis(), pendingIntent); //4

        } //for


        if (!stringsAlarmId2[0].equals("")) {
            for(int x =0;x <stringsAlarmId2.length;x++) {

                String stringAlarm = stringsAlarmDateTime2[x];
                Date dAlarm = myData.stringChangetoDate(stringAlarm);
                myCalendarAlarm.setTime(dAlarm);

                //24/10/2559 ส่งค่าไปกับ intent
                alertIntent.putExtra("DailyUpdateIntent", stringsAlarmId2[x]);
                alertIntent.putExtra("DailyUpdateIntentTime", stringsAlarmDateTime2[x]);
                alertIntent.putExtra("DailyUpdateTimeNof", "2");
                alertIntent.putExtra("notifID", a);

                PendingIntent pendingIntent = PendingIntent.getBroadcast(context, a, alertIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                a = a + 1;
                alarmManager.set(1, myCalendarAlarm.getTimeInMillis(), pendingIntent); //4


            }
        }






        /*
        else if (strTimeNof[0].equals("2")) {
            for(int x =0 ;x < stringsAlarmId.length;x++) {
                String stringAlarm = stringsAlarmDateTime[x];
                Date dAlarm = myData.stringChangetoDate(stringAlarm);
                myCalendarAlarm.setTime(dAlarm);

                //24/10/2559 ส่งค่าไปกับ intent
                alertIntent.putExtra("DailyUpdateIntent", stringsAlarmId[x]);


                PendingIntent pendingIntent = PendingIntent.getBroadcast(context, a, alertIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                a = a + 1;
                alarmManager.set(1, myCalendarAlarm.getTimeInMillis(), pendingIntent); //4
                a = a + 1;
                myCalendarAlarm.add(Calendar.MINUTE,1);
                PendingIntent pendingIntent1 = PendingIntent.getBroadcast(context, a, alertIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager.set(1,myCalendarAlarm.getTimeInMillis(),pendingIntent1);
            } //for
        }
        */







    }
}  //Main Class
