package com.su.lapponampai_w.mhm_app_beta1;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class SplashScreen extends AppCompatActivity {

    MyManage myManage;
    int anInt;
    String stringDateRefAdd7, stringCurrentAdd7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        myManage = new MyManage(this);

        //Test Add Value to sumTABLE
        //myManage.addValueToSumTable("Main_id", "DateRef", "TimeRef", "DateCheck",
          //      "TimeCheck", "SkipHold");

        updatesumTABLE00();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                anInt = myManage.check_null_userTABLE();

                if (anInt == 1) {

                    // ต้องแยก Stay ว่า 0 หรือ 1
                    String[] strings = myManage.readSQLite_userTABLE(3);
                    if (strings[0].equals("0")) {
                        startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                        Toast t = Toast.makeText(SplashScreen.this, "Stay = 0", Toast.LENGTH_LONG); // ลบภายหลัง
                        t.show();
                        finish();
                    } else if (strings[0].equals("1")) {
                        startActivity(new Intent(SplashScreen.this, MainActivity.class));
                        Toast t = Toast.makeText(SplashScreen.this, "Stay = 1", Toast.LENGTH_LONG); // ลบภายหลัง
                        t.show();
                        finish();
                    } else {
                        Toast t = Toast.makeText(SplashScreen.this, "Stay != '0' or '1' What's happen", Toast.LENGTH_LONG); // ลบภายหลัง
                        t.show();
                    }




                } else {
                    startActivity(new Intent(SplashScreen.this, SignUpActivity.class)); // เดี่ยวต้องเปลี่ยน
                    Toast t = Toast.makeText(SplashScreen.this, Integer.toString(anInt) , Toast.LENGTH_LONG); //ลบภายหลัง
                    t.show();
                    finish();
                }


            }
        },2000); // 2 วินาที

        myManage.nameGenericTABLEData();
        myManage.medTABLEData();
        myManage.drugInteractionTABLEData();
        myManage.timeTABLEData();
        myManage.newsTABLEData();

    }  //Main Method


    private void updatesumTABLE00() {

        MyData myData = new MyData();

        String[] stringsREAD_mainTABLE = myManage.readAllMainTABLE_Full(11); //เอามา check ว่า mainTABLE มียาป่าว หรือมีแต่ PRN (N หรือ Y)
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



        Log.d("30/09/2559",myData.string_ddMMyyyy_ConvertedFromSpecificDate(dateFinalProcess)); //แสดง Log ที่วันควรจะเป็น

        //ดูว่ามีแต่ prn ก็ต้องยกเลิก
        String strCheckPRN = "Y";
        for(int i = 0;i<stringsREAD_mainTABLE.length;i++) {
            if (stringsREAD_mainTABLE[i].equals("N")) {
                strCheckPRN = "N";
            }
        }
        //มีถึงอีก 9 วันข้างหน้าแล้วหรือยัง
        String strDateRef = "N";
        if (dateRef.compareTo(dateFinalProcess) >= 0) {
            strDateRef = "Y";
        }
        int x = dateRef.compareTo(dateFinalProcess);
        Log.d("30/09/2559",Integer.toString(x));
        Log.d("30/09/2559",strDateRef);

        if (stringsREAD_mainTABLE[0].equals("")) {
            Log.d("UpdatesumTABLE", "ไม่มียาใน mainTABLE : ค่าว่าง ยุติการ UpdateReceiver");
            Toast.makeText(SplashScreen.this,"ไม่มียาใน mainTABLE : ค่าว่าง ยุติการ UpdateReceiver",Toast.LENGTH_LONG).show();
            return;
        }
        //ดูว่าถ้ามีถ่าแต่ prn ก็ต้องยกเลิก
        else if (strCheckPRN.equals("Y")) {
            Log.d("UpdatesumTABLE", "ยาใน mainTABLE มีแต่ยา PRN : ยุติการ UpdateReceiver");
            Toast.makeText(SplashScreen.this,"ยาใน mainTABLE มีแต่ยา PRN :ยุติการ UpdateReceiver",Toast.LENGTH_LONG).show();
            return;

        }
        //ถ้าจะ Test การเอาเข้าให้เอา else if อันนี้ออกไป
        else if(strDateRef.equals("Y")) {
            Log.d("UpdatesumTABLE", "มีค่าวันนี้ใน sumTABLE ของวันนี้แล้ว : ยุติการ UpdateReceiver");
            Toast.makeText(SplashScreen.this, "มีค่าวันนี้ใน sumTABLE ของวันนี้แล้ว : ยุติการ UpdateReceiver", Toast.LENGTH_LONG).show();
            return;
        } else {

            Calendar calendar = Calendar.getInstance();
            Calendar myCalendar1 = (Calendar) calendar.clone();

            myCalendar1.set(Calendar.HOUR_OF_DAY, 0);
            myCalendar1.set(Calendar.MINUTE, 0);
            myCalendar1.set(Calendar.SECOND, 0);
            myCalendar1.set(Calendar.MILLISECOND, 10);

            Random random = new Random();
            int myRandom = random.nextInt(1000);

            Intent intent = new Intent(getBaseContext(), DailyUpdateReceiver.class);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(),
                    myRandom, intent, 0);


            AlarmManager alarmManager = (AlarmManager) getBaseContext().getSystemService(Context.ALARM_SERVICE);
            alarmManager.setRepeating(1, myCalendar1.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

            Log.d("UpdatesumTABLE", "ทำ Alarm ขึ้นเองได้แล้ว" + myCalendar1.getTime().toString());
            Toast.makeText(getBaseContext(), "เริ่มทำการ BroadCAst", Toast.LENGTH_LONG).show();

        }
    } //updatesumTABLE00



    /*
    private void updatesumTABLE00() {

        MyData myData = new MyData();

        String[] stringsREAD_mainTABLE = myManage.readAllMainTABLE_Full(11); //เอามา check ว่า mainTABLE มียาป่าว หรือมีแต่ PRN (N หรือ Y)

        //ต้องแก้ค่าใน sumTABLE ก่อนให้มีแต่ค่าที่แท้จริงเท่านั้นก่อนควรจะมีแค่บรรทัดเดียว

        String[] stringsDateRef = myManage.readAllsumTABLE_Full(2); //check วันที่มีการ Add ยาลง sumTABLE ล่าสุด
        String currentDay = myData.currentDay();  //ค่าของวันนี้


        // 29/09/16 ==>ดูว่าอีก 7 วันข้างหน้ามียาหรือยัง
        Calendar calendarCurrent = Calendar.getInstance();
        calendarCurrent.add(Calendar.DAY_OF_MONTH,7);
        Date dateCurrent = calendarCurrent.getTime(); //อีก 7 วันนับจากวันนี้ ของ smartPhone

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");


        //Calendar calendarRef = Calendar.getInstance();
        //calendarRef.setTime(date);
        //calendarRef.add(Calendar.DAY_OF_MONTH, 7);
        //Date dateRef = calendarRef.getTime(); //อีก 7 วันนับจากวันล่าสุดที่อยู่ใน sumTABLE

        //stringDateRefAdd7 = dateFormat.format(dateRef);
        stringCurrentAdd7 = dateFormat.format(dateCurrent); //ใช้อันนี้

        Log.d("29/09/16V1", stringCurrentAdd7);


        // 29/09/16V1 ลอง.....แค่นี้ก่อน



        //ดูว่ามีแต่ prn ก็ต้องยกเลิก
        String strCheckPRN = "Y";
        for(int i = 0;i<stringsREAD_mainTABLE.length;i++) {
            if (stringsREAD_mainTABLE[i].equals("N")) {
                strCheckPRN = "N";
            }
        }
        //มีวันนี้แล้วหรือยัง
        String strDateRef = "N";
        for(int x = 0;x<stringsDateRef.length;x++) {
            if (stringsDateRef[x].equals(currentDay)) {
                strDateRef = "Y";
            }
        }

        if (stringsREAD_mainTABLE[0].equals("")) {
            Log.d("UpdatesumTABLE", "ไม่มียาใน mainTABLE : ค่าว่าง ยุติการ UpdateReceiver");
            Toast.makeText(SplashScreen.this,"ไม่มียาใน mainTABLE : ค่าว่าง ยุติการ UpdateReceiver",Toast.LENGTH_LONG).show();
            return;
        }
        //ดูว่าถ้ามีถ่าแต่ prn ก็ต้องยกเลิก
        else if (strCheckPRN.equals("Y")) {
            Log.d("UpdatesumTABLE", "ยาใน mainTABLE มีแต่ยา PRN : ยุติการ UpdateReceiver");
            Toast.makeText(SplashScreen.this,"ยาใน mainTABLE มีแต่ยา PRN :ยุติการ UpdateReceiver",Toast.LENGTH_LONG).show();
            return;

        }
        //ถ้าจะ Test การเอาเข้าให้เอา else if อันนี้ออกไป
        else if (strDateRef.equals("Y")) {
            Log.d("UpdatesumTABLE", "มีค่าวันนี้ใน sumTABLE ของวันนี้แล้ว : ยุติการ UpdateReceiver");
            Toast.makeText(SplashScreen.this, "มีค่าวันนี้ใน sumTABLE ของวันนี้แล้ว : ยุติการ UpdateReceiver", Toast.LENGTH_LONG).show();
            return;
        } else {

            Calendar calendar = Calendar.getInstance();
            Calendar myCalendar1 = (Calendar) calendar.clone();

            myCalendar1.set(Calendar.HOUR_OF_DAY, 0);
            myCalendar1.set(Calendar.MINUTE, 0);
            myCalendar1.set(Calendar.SECOND, 0);
            myCalendar1.set(Calendar.MILLISECOND, 10);

            Random random = new Random();
            int myRandom = random.nextInt(1000);

            Intent intent = new Intent(getBaseContext(), DailyUpdateReceiver.class);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(),
                    myRandom, intent, 0);


            AlarmManager alarmManager = (AlarmManager) getBaseContext().getSystemService(Context.ALARM_SERVICE);
            alarmManager.setRepeating(1, myCalendar1.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

            Log.d("UpdatesumTABLE", "ทำ Alarm ขึ้นเองได้แล้ว" + myCalendar1.getTime().toString());
            Toast.makeText(getBaseContext(), "เริ่มทำการ BroadCAst", Toast.LENGTH_LONG).show();

        }
    } //updatesumTABLE00    */






}  //Main Class
