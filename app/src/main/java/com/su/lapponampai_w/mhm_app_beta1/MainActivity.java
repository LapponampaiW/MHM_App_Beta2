package com.su.lapponampai_w.mhm_app_beta1;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.ListPopupWindow;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {



    //Explicit

    //Widget ต่างๆ
    ImageButton imageButtonPop1, imageButtonPop2, imageButtonPop3, imageButtonPop4, imageButtonPop5, imageButtonPop6;
    ImageButton imageCalendar;
    ImageButton imageButtonM1,imageButtonM2,imageButtonM3,imageButtonM4,
            imageButtonM5,imageButtonM6,imageButtonM7,imageButtonM8,imageButtonM9,
            imageButtonA1,imageButtonA2,imageButtonA3,imageButtonA4,imageButtonA5,
            imageButtonA6,imageButtonA7,imageButtonA8,imageButtonA9,imageButtonE1,
            imageButtonE2,imageButtonE3,imageButtonE4,imageButtonE5,imageButtonE6,
            imageButtonE7,imageButtonE8,imageButtonE9,imageButtonB1,imageButtonB2,
            imageButtonB3,imageButtonB4,imageButtonB5,imageButtonB6,imageButtonB7,
            imageButtonB8,imageButtonB9;
    TextView textViewAdd, textViewMainDate;
    private PopupWindow popupWindow;
    private LayoutInflater layoutInflater;
    private RelativeLayout relativeLayout;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //Bind Widget
        bindWidget();

        //Update sumTABLE 0:00
        //updatesumTABLE00();

        //Notification from SQLite
        //notificationFormSQLite();  //เปิดในมือถือแล้วใช้ไม่ได้

        //คลิก เพิ่มเติม
        clickAddbtn();

        //คลิก ImageButtonCalendar
        click_ImageButtonCalendar();

        showContentOnMainActivity_QuerySumTABLE();




    } //Main method

    private void showContentOnMainActivity_QuerySumTABLE() {

        imageButtonA1.setVisibility(View.INVISIBLE);
        imageButtonA2.setVisibility(View.INVISIBLE);
        imageButtonA3.setVisibility(View.INVISIBLE);
        imageButtonA4.setVisibility(View.INVISIBLE);
        imageButtonA5.setVisibility(View.INVISIBLE);
        imageButtonA6.setVisibility(View.INVISIBLE);
        imageButtonA7.setVisibility(View.INVISIBLE);
        imageButtonA8.setVisibility(View.INVISIBLE);
        imageButtonA9.setVisibility(View.INVISIBLE);
        imageButtonB1.setVisibility(View.INVISIBLE);
        imageButtonB2.setVisibility(View.INVISIBLE);
        imageButtonB3.setVisibility(View.INVISIBLE);
        imageButtonB4.setVisibility(View.INVISIBLE);
        imageButtonB5.setVisibility(View.INVISIBLE);
        imageButtonB6.setVisibility(View.INVISIBLE);
        imageButtonB7.setVisibility(View.INVISIBLE);
        imageButtonB8.setVisibility(View.INVISIBLE);
        imageButtonB9.setVisibility(View.INVISIBLE);
        imageButtonM1.setVisibility(View.INVISIBLE);
        imageButtonM2.setVisibility(View.INVISIBLE);
        imageButtonM3.setVisibility(View.INVISIBLE);
        imageButtonM4.setVisibility(View.INVISIBLE);
        imageButtonM5.setVisibility(View.INVISIBLE);
        imageButtonM6.setVisibility(View.INVISIBLE);
        imageButtonM7.setVisibility(View.INVISIBLE);
        imageButtonM8.setVisibility(View.INVISIBLE);
        imageButtonM9.setVisibility(View.INVISIBLE);
        imageButtonE1.setVisibility(View.INVISIBLE);
        imageButtonE2.setVisibility(View.INVISIBLE);
        imageButtonE3.setVisibility(View.INVISIBLE);
        imageButtonE4.setVisibility(View.INVISIBLE);
        imageButtonE5.setVisibility(View.INVISIBLE);
        imageButtonE6.setVisibility(View.INVISIBLE);
        imageButtonE7.setVisibility(View.INVISIBLE);
        imageButtonE8.setVisibility(View.INVISIBLE);
        imageButtonE9.setVisibility(View.INVISIBLE);

        MyManage myManage = new MyManage(this);



        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String day = dateFormat.format(System.currentTimeMillis());
        textViewMainDate.setText("วันที่" + day);



        String[] strings_Main_id  = myManage.filter_sumTABLE__by_Date(day,1);
        String[] strings_Time_Ref = myManage.filter_sumTABLE__by_Date(day,3);
        String[] strings_Appearance = new String[strings_Main_id.length];

        Log.d("ContentMainActivity", strings_Main_id[0] + strings_Time_Ref[0]);

        for(int i =0 ;i<strings_Main_id.length;i++) {

            String[] strings_medTABLE = myManage.filter_mainTABLE_by_id_Full(strings_Main_id[i]);
            strings_Appearance[i] = strings_medTABLE[6];
        }

        MyData myData = new MyData();
        int[] intsIndex = myData.translate_Appearance(strings_Appearance);






    } //showContentOnMainActivity_QuerySumTABLE


    private void click_ImageButtonCalendar() {
        imageCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Calendartest.class));
            }
        });
    }


    private void clickAddbtn() {

        textViewAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                ViewGroup container = (ViewGroup) layoutInflater.inflate(R.layout.popup_main_add, null);

                popupWindow = new PopupWindow(container, ListPopupWindow.WRAP_CONTENT,ListPopupWindow.WRAP_CONTENT,true);
                popupWindow.showAtLocation(relativeLayout,Gravity.BOTTOM,0,0);

                container.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popupWindow.dismiss();
                        return true;
                    }
                });


                imageButtonPop1 = (ImageButton) container.findViewById(R.id.btn_pop1);
                imageButtonPop1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MainActivity.this,AddMedicineActivity.class));
                    }
                });

                imageButtonPop6 = (ImageButton) container.findViewById(R.id.btn_pop6);
                imageButtonPop6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });


            }
        });
    }

    private void notificationFormSQLite() {

        //Read sumTABLE
        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyHelper.DATABASE_NAME,
                MODE_PRIVATE,null); //เชื่อมต่อกับ SQLiteDatabase
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM sumTABLE", null);
        cursor.moveToFirst();

        String[] dateStrings = new String[cursor.getCount()];
        String[] dayStrings = new String[cursor.getCount()];
        String[] monthStrings = new String[cursor.getCount()];
        String[] timeStrings = new String[cursor.getCount()];
        String[] hrStrings = new String[cursor.getCount()];
        String[] minStrings = new String[cursor.getCount()];

        for (int i=0;i<cursor.getCount();i++) {

            dateStrings[i] = cursor.getString(2);
            String[] strings = dateStrings[i].split("/");
            dayStrings[i] = strings[0];
            monthStrings[i] = strings[1];

            timeStrings[i] = cursor.getString(3);
            String[] strings1 = timeStrings[i].split(":");
            hrStrings[i] = strings1[0];
            minStrings[i] = strings1[1];

        }

        cursor.close();

    }  //notication

    private void updatesumTABLE00() {
        Calendar calendar = Calendar.getInstance();  //ค้นหาเวลาในเครื่อง
        Calendar myCalendar1 = (Calendar) calendar.clone(); //clone เวลาในเครื่องเข้ามาใช้


        //myCalendar1.set(calendar.DAY_OF_MONTH,15);
        myCalendar1.set(Calendar.HOUR_OF_DAY, 10);
        myCalendar1.set(Calendar.MINUTE, 30);
        myCalendar1.set(Calendar.SECOND, 59);
        myCalendar1.set(Calendar.MILLISECOND, 0);

        Log.d("10MayV1", "myCaledar ==> " + myCalendar1.getTime().toString()); //กำหนดค่าค่าเวลาในการเตือน

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String currentDate = dateFormat.format(date);

        Intent intent = new Intent(getBaseContext(),DailyUpdateReceiver.class);
        //Intent intent = new Intent(getBaseContext(),AlarmReceiver.class);


        //Date
        //intent.putExtra("intDay",calendar.get(Calendar.DAY_OF_MONTH) );

        intent.putExtra("Date", currentDate);

        Random random = new Random();
        int myRandom = random.nextInt(1000);


        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(),
                myRandom,intent,0);


        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, myCalendar1.getTimeInMillis(),pendingIntent); //Wakeuppppppp

    }

    private void bindWidget() {
        textViewAdd = (TextView) findViewById(R.id.textView_Main_Add);
        relativeLayout = (RelativeLayout) findViewById(R.id.relative);
        imageCalendar = (ImageButton) findViewById(R.id.imageButtonCalendar);
        textViewMainDate = (TextView) findViewById(R.id.textViewMainDate);
        imageButtonA1 = (ImageButton) findViewById(R.id.iB_A1);
        imageButtonA2 = (ImageButton) findViewById(R.id.iB_A2);
        imageButtonA3 = (ImageButton) findViewById(R.id.iB_A3);
        imageButtonA4 = (ImageButton) findViewById(R.id.iB_A4);
        imageButtonA5 = (ImageButton) findViewById(R.id.iB_A5);
        imageButtonA6 = (ImageButton) findViewById(R.id.iB_A6);
        imageButtonA7 = (ImageButton) findViewById(R.id.iB_A7);
        imageButtonA8 = (ImageButton) findViewById(R.id.iB_A8);
        imageButtonA9 = (ImageButton) findViewById(R.id.iB_A9);
        imageButtonB1 = (ImageButton) findViewById(R.id.iB_B1);
        imageButtonB2 = (ImageButton) findViewById(R.id.iB_B2);
        imageButtonB3 = (ImageButton) findViewById(R.id.iB_B3);
        imageButtonB4 = (ImageButton) findViewById(R.id.iB_B4);
        imageButtonB5 = (ImageButton) findViewById(R.id.iB_B5);
        imageButtonB6 = (ImageButton) findViewById(R.id.iB_B6);
        imageButtonB7 = (ImageButton) findViewById(R.id.iB_B7);
        imageButtonB8 = (ImageButton) findViewById(R.id.iB_B8);
        imageButtonB9 = (ImageButton) findViewById(R.id.iB_B9);
        imageButtonM1 = (ImageButton) findViewById(R.id.iB_M1);
        imageButtonM2 = (ImageButton) findViewById(R.id.iB_M2);
        imageButtonM3 = (ImageButton) findViewById(R.id.iB_M3);
        imageButtonM4 = (ImageButton) findViewById(R.id.iB_M4);
        imageButtonM5 = (ImageButton) findViewById(R.id.iB_M5);
        imageButtonM6 = (ImageButton) findViewById(R.id.iB_M6);
        imageButtonM7 = (ImageButton) findViewById(R.id.iB_M7);
        imageButtonM8 = (ImageButton) findViewById(R.id.iB_M8);
        imageButtonM9 = (ImageButton) findViewById(R.id.iB_M9);
        imageButtonE1 = (ImageButton) findViewById(R.id.iB_E1);
        imageButtonE2 = (ImageButton) findViewById(R.id.iB_E2);
        imageButtonE3 = (ImageButton) findViewById(R.id.iB_E3);
        imageButtonE4 = (ImageButton) findViewById(R.id.iB_E4);
        imageButtonE5 = (ImageButton) findViewById(R.id.iB_E5);
        imageButtonE6 = (ImageButton) findViewById(R.id.iB_E6);
        imageButtonE7 = (ImageButton) findViewById(R.id.iB_E7);
        imageButtonE8 = (ImageButton) findViewById(R.id.iB_E8);
        imageButtonE9 = (ImageButton) findViewById(R.id.iB_E9);


    }


} //Main Class
