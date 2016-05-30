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
    TextView textViewAdd;
    private PopupWindow popupWindow;
    private LayoutInflater layoutInflater;
    private LinearLayout relativeLayout;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Widget
        bindWidget();

        //Update sumTABLE 0:00
        updatesumTABLE00();

        //Notification from SQLite
        notificationFormSQLite();  //เปิดในมือถือแล้วใช้ไม่ได้

        //คลิก เพิ่มเติม
        clickAddbtn();









    } //Main method




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


    public void clickCalendar(View view) {
        startActivity(new Intent(MainActivity.this,Calendartest.class));
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
        relativeLayout = (LinearLayout) findViewById(R.id.relative);


    }


} //Main Class
