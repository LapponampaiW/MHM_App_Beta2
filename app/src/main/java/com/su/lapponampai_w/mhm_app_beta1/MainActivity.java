package com.su.lapponampai_w.mhm_app_beta1;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    //Explicit

    //Widget ต่างๆ
    Button buttonAddMedicine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Widget
        bindWidget();

        //Update sumTABLE 0:00
        updatesumTABLE00();

        buttonAddMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AddMedicineActivity.class));
            }
        });

    } //Main Method

    private void updatesumTABLE00() {
        Calendar calendar = Calendar.getInstance();  //ค้นหาเวลาในเครื่อง
        Calendar myCalendar1 = (Calendar) calendar.clone(); //clone เวลาในเครื่องเข้ามาใช้

        myCalendar1.set(Calendar.HOUR_OF_DAY, 0);
        myCalendar1.set(Calendar.MINUTE, 6);
        myCalendar1.set(Calendar.SECOND, 0);
        myCalendar1.set(Calendar.MILLISECOND, 0);

        Log.d("10MayV1", "myCaledar ==> " + myCalendar1.getTime().toString()); //กำหนดค่าค่าเวลาในการเตือน

        Intent intent = new Intent(getBaseContext(),DailyUpdateReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(),
                1005,intent,0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, myCalendar1.getTimeInMillis(),pendingIntent); //Wakeuppppppp

    }

    private void bindWidget() {
        buttonAddMedicine = (Button) findViewById(R.id.buttonAddMedicine);

    }


} //Main Class
