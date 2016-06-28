package com.su.lapponampai_w.mhm_app_beta1;

import android.app.Activity;
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
import android.support.v7.widget.RecyclerView;
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

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    //Explicit
    public static Activity activityMainActivity;

    //Widget ต่างๆ
    ImageButton imageButtonPop1, imageButtonPop2, imageButtonPop3, imageButtonPop4, imageButtonPop5, imageButtonPop6;
    ImageButton imageCalendar;
    ImageButton imageButtonM1, imageButtonM2, imageButtonM3, imageButtonM4,
            imageButtonM5, imageButtonM6, imageButtonM7, imageButtonM8, imageButtonM9,
            imageButtonA1, imageButtonA2, imageButtonA3, imageButtonA4, imageButtonA5,
            imageButtonA6, imageButtonA7, imageButtonA8, imageButtonA9, imageButtonE1,
            imageButtonE2, imageButtonE3, imageButtonE4, imageButtonE5, imageButtonE6,
            imageButtonE7, imageButtonE8, imageButtonE9, imageButtonB1, imageButtonB2,
            imageButtonB3, imageButtonB4, imageButtonB5, imageButtonB6, imageButtonB7,
            imageButtonB8, imageButtonB9;
    TextView textViewAdd, textViewMainDate, textViewMedication, textViewNews;
    private PopupWindow popupWindow;
    private LayoutInflater layoutInflater;
    private RelativeLayout relativeLayout;
    private TextView tvMorning, tvAfternoon, tvEvening, tvBedtime;
    String today;
    String[] stringsInterval, stringsStartTime, stringsEndTime, stringstimeTABLE;

    String[] stringsClick_Position, stringsClick_Main_id, stringsClick_TimeRef,
            stringsClick_Appearance; //clickTakeMedicine
    String[] stringsMainTABLE_TradeName, stringsMainTABLE_AmountTablet, stringsMainTABLE_Main_id; //clickTakeMedicine
    String strResult_Position, strResult_Main_id, strResult_TimeRef,
            strResult_Appearance, strResult_AmountTablet, strResult_Tradename; //clickTakeMedicine


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityMainActivity = this;

        //Bind Widget
        bindWidget();
        
        //Notification from SQLite
        //notificationFormSQLite();

        setDateAndTimeToday();

        delete_UnnecessaryData_sumTABLE();

        displayMedicineByDay(today);

        //คลิก เพิ่มเติม
        clickAddbtn();

        //คลิก รายการยา
        clickMedicationList();

        //คลิก ImageButtonCalendar
        click_ImageButtonCalendar();

        click_News();

        clickImagepill();

    } //Main method

    @Override
    public void onResume(){
        super.onResume();

        setDateAndTimeToday();

        delete_UnnecessaryData_sumTABLE();

        displayMedicineByDay(today);

    } //Override

    private void delete_UnnecessaryData_sumTABLE() {

        MyManage myManage = new MyManage(this);

        //เปิดmainTABLE จะเอาเฉพาะค่าที่ถูก cancel แล้ว
        String[] strMain_id_in_mainTABLE = myManage.read_mainTABLE_InCluded_DateTimeCanceled(0);
        String[] strDateTimeCanceled = myManage.read_mainTABLE_InCluded_DateTimeCanceled(20);
        String[] strMain_id_in_sumTABlE_Today = myManage.filter_sumTABLE__by_Date(today, 1);
        String[] str_id_in_sumTABLE_Today = myManage.filter_sumTABLE__by_Date(today, 0);
        String[] str_DateCheck_in_sumTABLE_Today = myManage.filter_sumTABLE__by_Date(today, 4);
        ArrayList<String> stringArrayList = new ArrayList<String>();
        ArrayList<String> stringArrayList1 = new ArrayList<String>();
        int arrayIndex = 0;
        if (!strMain_id_in_mainTABLE.equals("") && !strMain_id_in_sumTABlE_Today.equals("")) {
            for(int i = 0;i<strMain_id_in_mainTABLE.length;i++) {
                if (!strDateTimeCanceled[i].equals("")) {
                    stringArrayList.add(arrayIndex, strMain_id_in_mainTABLE[i]);
                    arrayIndex = arrayIndex + 1;
                }
            }
              //เช็คว่ามียาที่ถูก cancel หรือป่าว
            if (stringArrayList.size() > 0) {
                String[] strings = new String[stringArrayList.size()];
                strings = stringArrayList.toArray(strings);

                arrayIndex = 0;
                for (int w = 0;w<strMain_id_in_sumTABlE_Today.length;w++) {
                    for(int x = 0;x<strings.length;x++) {
                        if (strMain_id_in_sumTABlE_Today[w].equals(strings[x])) {
                            if (str_DateCheck_in_sumTABLE_Today[w].equals("")) {
                                SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyHelper.DATABASE_NAME, MODE_PRIVATE, null);
                                sqLiteDatabase.delete("sumTABLE", "_id = " + str_id_in_sumTABLE_Today[w], null);
                            }
                        }
                    }
                }
            }
        }


        /*
        // ถ้ามีให้ลบทิ้งไป
        MyManage myManage = new MyManage(this);
        //เปิดmainTABLE เฉพาะค่าที่ ACtive ขึ้นมา
        String[] strMain_id_in_mainTABLE = myManage.readAllMainTABLE_Full(0);
        //เปิด sumTABLE ของวันนี้ขึ้นมา (เอาเฉพาะวันนี้ก็ได้)  filter_sumTABLE__by_Date
        String[] strMain_id_in_sumTABlE_Today = myManage.filter_sumTABLE__by_Date(today, 1);
        String[] str_id_in_sumTABLE_Today = myManage.filter_sumTABLE__by_Date(today, 0);
        //เอาค่ามาเทียบกันว่า มีค่าใน sumTABLE (main_id) ที่ไม่มีใน mainTABLE หรือไม่

        if (!strMain_id_in_sumTABlE_Today.equals("")) {
            int[][] iCheckMain_id_in_sumTABLE_Today =
                    new int[strMain_id_in_sumTABlE_Today.length][strMain_id_in_mainTABLE.length];


            for(int i = 0;i<strMain_id_in_sumTABlE_Today.length;i++) {
                for (int x = 0;x<strMain_id_in_mainTABLE.length;x++) {
                    if (strMain_id_in_sumTABlE_Today[i].equals(strMain_id_in_mainTABLE[x])) {
                        iCheckMain_id_in_sumTABLE_Today[i][x] = 1;
                    } else {
                        iCheckMain_id_in_sumTABLE_Today[i][x] = 0;
                    }

                }
            }

            ArrayList<Integer> arrayList = new ArrayList<Integer>();

            for(int w = 0;w<iCheckMain_id_in_sumTABLE_Today[0].length;w++) {
                int arrayListIndex = 0;
                for(int z = 0;z<iCheckMain_id_in_sumTABLE_Today.length;z++) {
                    Log.d("Main_Delete", "iCheckMain_id_in_sumTABLE_Today[z][w] : " +Integer.toString(z)+Integer.toString(w) + " : " + Integer.toString(iCheckMain_id_in_sumTABLE_Today[z][w]));
                    arrayList.add(arrayListIndex,iCheckMain_id_in_sumTABLE_Today[z][w]); //ค่า main_id ในตำแหน่ง w มี 0 หรือ 1
                    arrayListIndex = arrayListIndex + 1;
                }
                Integer[] ii = new Integer[arrayList.size()];
                ii = arrayList.toArray(ii);

                Arrays.sort(ii);
                int i = Arrays.binarySearch(ii, 1);
                if (i < 0) {
                    //ทำการลบค่าที่ตำแหน่งดังกล่าว
                    //strMain_id_in_sumTABlE_Today[w] ลบค่านี้ ใช้ search ด้วย
                    // main_id ของ วันนี้(Date_Ref) และยังมี DateCheck เป็นค่าว่าง
                    Log.d("Main_Delete", "strMain_id_in_sumTABLE_Today[w] ที่จะลบ  : " + strMain_id_in_sumTABlE_Today[w]);
                    SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyHelper.DATABASE_NAME, MODE_PRIVATE, null);
                    sqLiteDatabase.delete("sumTABLE", "Main_id = " + str_id_in_sumTABLE_Today[w], null);
                }
                arrayList.clear();
            }


        }

        */





    }



    private void clickImagepill() {
        final MyManage myManage = new MyManage(this);

        stringsClick_Position = myManage.readAlldisplayTABLE(1);
        stringsClick_Main_id = myManage.readAlldisplayTABLE(3);
        stringsClick_TimeRef = myManage.readAlldisplayTABLE(5);
        stringsClick_Appearance = myManage.readAlldisplayTABLE(7);

        stringsMainTABLE_Main_id = myManage.readAllMainTABLE(0);
        stringsMainTABLE_TradeName = myManage.readAllMainTABLE(3);
        stringsMainTABLE_AmountTablet = myManage.readAllMainTABLE(6);

        //ลองทำ Morning ตำแหน่งที่ 1 ก่อน
        imageButtonM1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickTakeMedicine("M1");
            }


            //คลิก!!! จะทานยา
            private void clickTakeMedicine(String maeb) {
                Toast.makeText(getBaseContext(), "เริ่ม " + maeb, Toast.LENGTH_SHORT).show();


                for (int i = 0; i < stringsClick_Position.length; i++) {
                    if (stringsClick_Position[i].equals(maeb)) {
                        strResult_Position = stringsClick_Position[i];
                        strResult_Main_id = stringsClick_Main_id[i];  //ต้องเอา Main_id ไปทำต่อ
                        strResult_TimeRef = stringsClick_TimeRef[i];
                        strResult_Appearance = stringsClick_Appearance[i];
                    }
                }

                for (int i = 0; i < stringsMainTABLE_Main_id.length; i++) {
                    if (stringsMainTABLE_Main_id[i].equals(strResult_Main_id)) {
                        strResult_Tradename = stringsMainTABLE_TradeName[i];
                        strResult_AmountTablet = stringsMainTABLE_AmountTablet[i];
                    }

                }

                Log.d("clickTakeMedicine", strResult_Position + " " + strResult_Main_id +
                        " " + strResult_TimeRef + " " + strResult_Appearance + " " +
                        strResult_Tradename + " " + strResult_AmountTablet);

                Intent intent = new Intent(MainActivity.this, TakeSkipMedicineActivity.class);

                //ทำการ copy ข้อมูลไป TakeSkipMedicineActivity
                //intent.putExtra("Med_id",strings_receiver[0]);
                intent.putExtra("Tradename", strResult_Tradename);
                intent.putExtra("Appearance", strResult_Appearance);
                intent.putExtra("AmountTablet", strResult_AmountTablet);
                intent.putExtra("TimeRef", strResult_TimeRef);
                startActivity(intent);

            }
        });


    } //clickImagepill

    private void click_News() {
        textViewNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NewsActivity.class));
            }
        });
    }

    private void clickMedicationList() {
        textViewMedication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MedicationListActivity.class));
            }
        });
    }

    private void setDateAndTimeToday() {

        Log.d("abc", "เข้ามาที่ setDateAndTimeToday");

        MyManage myManage = new MyManage(this);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        today = dateFormat.format(System.currentTimeMillis());
        textViewMainDate.setText("วันที่ :" + today);

        stringsInterval = myManage.readTimeTABLE(1);
        stringsStartTime = myManage.readTimeTABLE(2);
        stringsEndTime = myManage.readTimeTABLE(3);
        stringstimeTABLE = new String[4];

        for (int x = 0; x < 4; x++) {
            stringstimeTABLE[x] = stringsInterval[x] + "(" + stringsStartTime[x] + " - " + stringsEndTime[x] + ")";
        }

        tvMorning.setText(stringstimeTABLE[0]);
        tvAfternoon.setText(stringstimeTABLE[1]);
        tvEvening.setText(stringstimeTABLE[2]);
        tvBedtime.setText(stringstimeTABLE[3]);
    }

    private void displayMedicineByDay(String date_specific) {

        MyManage myManage = new MyManage(this);

        String[] strings_Main_id = myManage.filter_sumTABLE__by_Date(date_specific, 1);  //ได้ Main_id จาก sumTABLE
        String[] strings_TimeRef = myManage.filter_sumTABLE__by_Date(date_specific, 3);  //ได้ Time_Ref จาก sumTABLE
        String[] strings_Sum_id = myManage.filter_sumTABLE__by_Date(date_specific, 0); //ได้ sum_id จาก sumTABLE
        String[] strings_TimeCheck = myManage.filter_sumTABLE__by_Date(date_specific, 5); //ได้ TimeCheck จาก sumTABLE
        String[] strings_Appearance = new String[strings_Main_id.length];

        if (!strings_Main_id[0].equals("")) {
            Log.d("ContentMainActivity", strings_Main_id[0] + strings_TimeRef[0]);

            for (int i = 0; i < strings_Main_id.length; i++) {

                String[] strings_medTABLE = myManage.filter_mainTABLE_by_id_Full(strings_Main_id[i]);
                strings_Appearance[i] = strings_medTABLE[6];
            }

            MyData myData = new MyData();
            int[] intsNotTakeYet = myData.translate_Small_Appearance(strings_Appearance); //รับค่ารูปจาก mainTABLE แล้วเปลี่ยนเป็นขนาดเล็ก
            int[] intsTake = myData.translate_Smallate_Appearance(strings_Appearance);

            //เริ่มทำการใส่ภาพของใน MainActivity

            String strDayM1 = date_specific;
            String strTimeM1 = stringsStartTime[0];
            String strTimeM2 = stringsEndTime[0];

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Date convertedDateMorning1 = new Date();
            Date convertedDateMorning2 = new Date();
            Date convertedDateAfternoon1 = new Date();
            Date convertedDateAfternoon2 = new Date();
            Date convertedDateEvening1 = new Date();
            Date convertedDateEvening2 = new Date();
            Date convertedBedtime1 = new Date();
            Date convertedBedtime2 = new Date();
            Date t = new Date();
            try {
                convertedDateMorning1 = dateFormat.parse(date_specific + " " + stringsStartTime[0]);
                convertedDateMorning2 = dateFormat.parse(date_specific + " " + stringsEndTime[0]);
                convertedDateAfternoon1 = dateFormat.parse(date_specific + " " + stringsStartTime[1]);
                convertedDateAfternoon2 = dateFormat.parse(date_specific + " " + stringsEndTime[1]);
                convertedDateEvening1 = dateFormat.parse(date_specific + " " + stringsStartTime[2]);
                convertedDateEvening2 = dateFormat.parse(date_specific + " " + stringsEndTime[2]);
                convertedBedtime1 = dateFormat.parse(date_specific + " " + stringsStartTime[3]);
                convertedBedtime2 = dateFormat.parse(date_specific + " " + stringsEndTime[3]);
                t = dateFormat.parse(date_specific + " " + strings_TimeRef[0]);

            } catch (ParseException e) {
                e.printStackTrace();
            }


            //แค่เช็ค Log.d ดูว่าถูกต้อง
            String s = dateFormat.format(convertedDateMorning1);
            String s1 = dateFormat.format(convertedDateMorning2);
            String s2 = dateFormat.format(convertedDateAfternoon1);
            String s3 = dateFormat.format(convertedDateAfternoon2);
            String s4 = dateFormat.format(convertedDateEvening1);
            String s5 = dateFormat.format(convertedDateEvening2);
            String s6 = dateFormat.format(convertedBedtime1);
            String s7 = dateFormat.format(convertedBedtime2);
            String st = dateFormat.format(t);
            Log.d("abc", "st :" + st);


            if (t.after(convertedDateMorning1)) {
                Log.d("abc", st + " ตามหลัง " + s);
            }

            Log.d("abc", s + " " + s1);
            Log.d("abc", s2 + " " + s3);
            Log.d("abc", s4 + " " + s5);
            Log.d("abc", s6 + " " + s7);  // ลบได้ต้องแต่ //แค่เช็ค ถึงบรรทัดนี้

            Date time = new Date();
            //ลบข้อมูลทั้งหมดใน displayTABLE
            SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyHelper.DATABASE_NAME, MODE_PRIVATE, null);
            sqLiteDatabase.delete("displayTABLE", null, null);


            for (int z = 0; z < strings_TimeRef.length; z++) {
                String strValue;
                try {
                    time = dateFormat.parse(date_specific + " " + strings_TimeRef[z]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }


                if (time.compareTo(convertedDateMorning1) >= 0 && time.compareTo(convertedDateMorning2) <= 0) {
                    Log.d("abc", "อยู่ระหว่าง 06:00 - 11:59");
                    strValue = myManage.filterdisplayTABLE_MAEB_By_Position("M");
                    Log.d("abc", "strREAD :" + strValue);
                    if (strValue.equals("Non value")) {
                        myManage.adddisplayTABLEValue("M1", strings_Sum_id[z], strings_Main_id[z], date_specific, strings_TimeRef[z], strings_TimeCheck[z], strings_Appearance[z]);
                        if (strings_TimeCheck[z].equals("")) {
                            Log.d("abc", "strings_TimeCheck[0] มีค่าว่าง");
                            imageButtonM1.setImageResource(intsNotTakeYet[z]);
                            imageButtonM1.setVisibility(View.VISIBLE);
                        } else {
                            imageButtonM1.setImageResource(intsTake[z]);
                            imageButtonM1.setVisibility(View.VISIBLE);
                        }
                        // Non Value เติมค่า M1
                    } else if (strValue.equals("M1")) {
                        myManage.adddisplayTABLEValue("M2", strings_Sum_id[z], strings_Main_id[z], date_specific, strings_TimeRef[z], strings_TimeCheck[z], strings_Appearance[z]);
                        if (strings_TimeCheck[z].equals("")) {
                            Log.d("abc", "strings_TimeCheck[1] มีค่าว่าง");
                            imageButtonM2.setImageResource(intsNotTakeYet[z]);
                            imageButtonM2.setVisibility(View.VISIBLE);
                        } else {
                            imageButtonM2.setImageResource(intsTake[z]);
                            imageButtonM2.setVisibility(View.VISIBLE);
                        }
                        // มี M1 แล้ว เติม M2
                    } else if (strValue.equals("M2")) {
                        myManage.adddisplayTABLEValue("M3", strings_Sum_id[z], strings_Main_id[z], date_specific, strings_TimeRef[z], strings_TimeCheck[z], strings_Appearance[z]);
                        if (strings_TimeCheck[z].equals("")) {
                            Log.d("abc", "strings_TimeCheck[2] มีค่าว่าง");
                            imageButtonM3.setImageResource(intsNotTakeYet[z]);
                            imageButtonM3.setVisibility(View.VISIBLE);
                        } else {
                            imageButtonM3.setImageResource(intsTake[z]);
                            imageButtonM3.setVisibility(View.VISIBLE);
                        }
                        // มี M2 แล้ว เติม M3
                    } else if (strValue.equals("M3")) {
                        myManage.adddisplayTABLEValue("M4", strings_Sum_id[z], strings_Main_id[z], date_specific, strings_TimeRef[z], strings_TimeCheck[z], strings_Appearance[z]);
                        if (strings_TimeCheck[z].equals("")) {
                            Log.d("abc", "strings_TimeCheck[3] มีค่าว่าง");
                            imageButtonM4.setImageResource(intsNotTakeYet[z]);
                            imageButtonM4.setVisibility(View.VISIBLE);
                        } else {
                            imageButtonM4.setImageResource(intsTake[z]);
                            imageButtonM4.setVisibility(View.VISIBLE);
                        }
                        // มี M3 แล้ว เติม M4
                    } else if (strValue.equals("M4")) {
                        myManage.adddisplayTABLEValue("M5", strings_Sum_id[z], strings_Main_id[z], date_specific, strings_TimeRef[z], strings_TimeCheck[z], strings_Appearance[z]);
                        if (strings_TimeCheck[z].equals("")) {
                            Log.d("abc", "strings_TimeCheck[4] มีค่าว่าง");
                            imageButtonM5.setImageResource(intsNotTakeYet[z]);
                            imageButtonM5.setVisibility(View.VISIBLE);
                        } else {
                            imageButtonM5.setImageResource(intsTake[z]);
                            imageButtonM5.setVisibility(View.VISIBLE);
                        }
                        // มี M4 แล้ว เติม M5
                    } else if (strValue.equals("M5")) {
                        myManage.adddisplayTABLEValue("M6", strings_Sum_id[z], strings_Main_id[z], date_specific, strings_TimeRef[z], strings_TimeCheck[z], strings_Appearance[z]);
                        if (strings_TimeCheck[z].equals("")) {
                            Log.d("abc", "strings_TimeCheck[5] มีค่าว่าง");
                            imageButtonM6.setImageResource(intsNotTakeYet[z]);
                            imageButtonM6.setVisibility(View.VISIBLE);
                        } else {
                            imageButtonM6.setImageResource(intsTake[z]);
                            imageButtonM6.setVisibility(View.VISIBLE);
                        }
                        // มี M5 แล้ว เติม M6
                    } else if (strValue.equals("M6")) {
                        myManage.adddisplayTABLEValue("M7", strings_Sum_id[z], strings_Main_id[z], date_specific, strings_TimeRef[z], strings_TimeCheck[z], strings_Appearance[z]);
                        if (strings_TimeCheck[z].equals("")) {
                            Log.d("abc", "strings_TimeCheck[6] มีค่าว่าง");
                            imageButtonM7.setImageResource(intsNotTakeYet[z]);
                            imageButtonM7.setVisibility(View.VISIBLE);
                        } else {
                            imageButtonM7.setImageResource(intsTake[z]);
                            imageButtonM7.setVisibility(View.VISIBLE);
                        }
                        // มี M6 แล้ว เติม M7
                    } else if (strValue.equals("M7")) {
                        myManage.adddisplayTABLEValue("M8", strings_Sum_id[z], strings_Main_id[z], date_specific, strings_TimeRef[z], strings_TimeCheck[z], strings_Appearance[z]);
                        if (strings_TimeCheck[z].equals("")) {
                            Log.d("abc", "strings_TimeCheck[7] มีค่าว่าง");
                            imageButtonM8.setImageResource(intsNotTakeYet[z]);
                            imageButtonM8.setVisibility(View.VISIBLE);
                        } else {
                            imageButtonM8.setImageResource(intsTake[z]);
                            imageButtonM8.setVisibility(View.VISIBLE);
                        }
                        // มี M7 แล้ว เติม M8
                    } else if (strValue.equals("M8")) {
                        myManage.adddisplayTABLEValue("M9", strings_Sum_id[z], strings_Main_id[z], date_specific, strings_TimeRef[z], strings_TimeCheck[z], strings_Appearance[z]);
                        if (strings_TimeCheck[z].equals("")) {
                            Log.d("abc", "strings_TimeCheck[8] มีค่าว่าง");
                            imageButtonM9.setImageResource(intsNotTakeYet[z]);
                            imageButtonM9.setVisibility(View.VISIBLE);
                        } else {
                            imageButtonM9.setImageResource(intsTake[z]);
                            imageButtonM9.setVisibility(View.VISIBLE);
                        }
                        // มี M8 แล้ว เติม M9
                    }

                } // if ของ Morning...

                if (time.compareTo(convertedDateAfternoon1) >= 0 && time.compareTo(convertedDateAfternoon2) <= 0) {
                    Log.d("afternoon", "อยู่ระหว่าง 12:00 - 17:59");
                    strValue = myManage.filterdisplayTABLE_MAEB_By_Position("A");
                    Log.d("afternoon", "strREAD :" + strValue);
                    if (strValue.equals("Non value")) {
                        myManage.adddisplayTABLEValue("A1", strings_Sum_id[z], strings_Main_id[z], date_specific, strings_TimeRef[z], strings_TimeCheck[z], strings_Appearance[z]);
                        if (strings_TimeCheck[z].equals("")) {
                            Log.d("afternoon", "strings_TimeCheck[0] มีค่าว่าง");
                            imageButtonA1.setImageResource(intsNotTakeYet[z]);
                            imageButtonA1.setVisibility(View.VISIBLE);
                        } else {
                            imageButtonA1.setImageResource(intsTake[z]);
                            imageButtonA1.setVisibility(View.VISIBLE);
                        }
                        // Non Value เติมค่า A1
                    } else if (strValue.equals("A1")) {
                        myManage.adddisplayTABLEValue("A2", strings_Sum_id[z], strings_Main_id[z], date_specific, strings_TimeRef[z], strings_TimeCheck[z], strings_Appearance[z]);
                        if (strings_TimeCheck[z].equals("")) {
                            Log.d("afternoon", "strings_TimeCheck[1] มีค่าว่าง");
                            imageButtonA2.setImageResource(intsNotTakeYet[z]);
                            imageButtonA2.setVisibility(View.VISIBLE);
                        } else {
                            imageButtonA2.setImageResource(intsTake[z]);
                            imageButtonA2.setVisibility(View.VISIBLE);
                        }
                        // มี A1 แล้ว เติม A2
                    } else if (strValue.equals("A2")) {
                        myManage.adddisplayTABLEValue("A3", strings_Sum_id[z], strings_Main_id[z], date_specific, strings_TimeRef[z], strings_TimeCheck[z], strings_Appearance[z]);
                        if (strings_TimeCheck[z].equals("")) {
                            Log.d("afternoon", "strings_TimeCheck[2] มีค่าว่าง");
                            imageButtonA3.setImageResource(intsNotTakeYet[z]);
                            imageButtonA3.setVisibility(View.VISIBLE);
                        } else {
                            imageButtonA3.setImageResource(intsTake[z]);
                            imageButtonA3.setVisibility(View.VISIBLE);
                        }
                        // มี A2 แล้ว เติม A3
                    } else if (strValue.equals("A3")) {
                        myManage.adddisplayTABLEValue("A4", strings_Sum_id[z], strings_Main_id[z], date_specific, strings_TimeRef[z], strings_TimeCheck[z], strings_Appearance[z]);
                        if (strings_TimeCheck[z].equals("")) {
                            Log.d("afternoon", "strings_TimeCheck[3] มีค่าว่าง");
                            imageButtonA4.setImageResource(intsNotTakeYet[z]);
                            imageButtonA4.setVisibility(View.VISIBLE);
                        } else {
                            imageButtonA4.setImageResource(intsTake[z]);
                            imageButtonA4.setVisibility(View.VISIBLE);
                        }
                        // มี A3 แล้ว เติม A4
                    } else if (strValue.equals("A4")) {
                        myManage.adddisplayTABLEValue("A5", strings_Sum_id[z], strings_Main_id[z], date_specific, strings_TimeRef[z], strings_TimeCheck[z], strings_Appearance[z]);
                        if (strings_TimeCheck[z].equals("")) {
                            Log.d("afternoon", "strings_TimeCheck[4] มีค่าว่าง");
                            imageButtonA5.setImageResource(intsNotTakeYet[z]);
                            imageButtonA5.setVisibility(View.VISIBLE);
                        } else {
                            imageButtonA5.setImageResource(intsTake[z]);
                            imageButtonA5.setVisibility(View.VISIBLE);
                        }
                        // มี A4 แล้ว เติม A5
                    } else if (strValue.equals("A5")) {
                        myManage.adddisplayTABLEValue("A6", strings_Sum_id[z], strings_Main_id[z], date_specific, strings_TimeRef[z], strings_TimeCheck[z], strings_Appearance[z]);
                        if (strings_TimeCheck[z].equals("")) {
                            Log.d("abc", "strings_TimeCheck[5] มีค่าว่าง");
                            imageButtonA6.setImageResource(intsNotTakeYet[z]);
                            imageButtonA6.setVisibility(View.VISIBLE);
                        } else {
                            imageButtonA6.setImageResource(intsTake[z]);
                            imageButtonA6.setVisibility(View.VISIBLE);
                        }
                        // มี A5 แล้ว เติม A6
                    } else if (strValue.equals("A6")) {
                        myManage.adddisplayTABLEValue("A7", strings_Sum_id[z], strings_Main_id[z], date_specific, strings_TimeRef[z], strings_TimeCheck[z], strings_Appearance[z]);
                        if (strings_TimeCheck[z].equals("")) {
                            Log.d("afternoon", "strings_TimeCheck[6] มีค่าว่าง");
                            imageButtonA7.setImageResource(intsNotTakeYet[z]);
                            imageButtonA7.setVisibility(View.VISIBLE);
                        } else {
                            imageButtonA7.setImageResource(intsTake[z]);
                            imageButtonA7.setVisibility(View.VISIBLE);
                        }
                        // มี A6 แล้ว เติม A7
                    } else if (strValue.equals("A7")) {
                        myManage.adddisplayTABLEValue("A8", strings_Sum_id[z], strings_Main_id[z], date_specific, strings_TimeRef[z], strings_TimeCheck[z], strings_Appearance[z]);
                        if (strings_TimeCheck[z].equals("")) {
                            Log.d("afternoon", "strings_TimeCheck[7] มีค่าว่าง");
                            imageButtonA8.setImageResource(intsNotTakeYet[z]);
                            imageButtonA8.setVisibility(View.VISIBLE);
                        } else {
                            imageButtonA8.setImageResource(intsTake[z]);
                            imageButtonA8.setVisibility(View.VISIBLE);
                        }
                        // มี A7 แล้ว เติม A8
                    } else if (strValue.equals("A8")) {
                        myManage.adddisplayTABLEValue("A9", strings_Sum_id[z], strings_Main_id[z], date_specific, strings_TimeRef[z], strings_TimeCheck[z], strings_Appearance[z]);
                        if (strings_TimeCheck[z].equals("")) {
                            Log.d("afternoon", "strings_TimeCheck[8] มีค่าว่าง");
                            imageButtonA9.setImageResource(intsNotTakeYet[z]);
                            imageButtonA9.setVisibility(View.VISIBLE);
                        } else {
                            imageButtonA9.setImageResource(intsTake[z]);
                            imageButtonA9.setVisibility(View.VISIBLE);
                        }
                        // มี A8 แล้ว เติม A9
                    }

                } // if ของ Afternoon


                //เริ่มทำ Evening
                if (time.compareTo(convertedDateEvening1) >= 0 && time.compareTo(convertedDateEvening2) <= 0) {
                    Log.d("Evening", "อยู่ระหว่าง 18:00 - 23:59");
                    strValue = myManage.filterdisplayTABLE_MAEB_By_Position("E");
                    Log.d("Evening", "strREAD :" + strValue);
                    if (strValue.equals("Non value")) {
                        myManage.adddisplayTABLEValue("E1", strings_Sum_id[z], strings_Main_id[z], date_specific, strings_TimeRef[z], strings_TimeCheck[z], strings_Appearance[z]);
                        if (strings_TimeCheck[z].equals("")) {
                            Log.d("Evening", "strings_TimeCheck[0] มีค่าว่าง");
                            imageButtonE1.setImageResource(intsNotTakeYet[z]);
                            imageButtonE1.setVisibility(View.VISIBLE);
                        } else {
                            imageButtonE1.setImageResource(intsTake[z]);
                            imageButtonE1.setVisibility(View.VISIBLE);
                        }
                        // Non Value เติมค่า E1
                    } else if (strValue.equals("E1")) {
                        myManage.adddisplayTABLEValue("E2", strings_Sum_id[z], strings_Main_id[z], date_specific, strings_TimeRef[z], strings_TimeCheck[z], strings_Appearance[z]);
                        if (strings_TimeCheck[z].equals("")) {
                            Log.d("Evening", "strings_TimeCheck[1] มีค่าว่าง");
                            imageButtonE2.setImageResource(intsNotTakeYet[z]);
                            imageButtonE2.setVisibility(View.VISIBLE);
                        } else {
                            imageButtonE2.setImageResource(intsTake[z]);
                            imageButtonE2.setVisibility(View.VISIBLE);
                        }
                        // มี E1 แล้ว เติม E2
                    } else if (strValue.equals("E2")) {
                        myManage.adddisplayTABLEValue("E3", strings_Sum_id[z], strings_Main_id[z], date_specific, strings_TimeRef[z], strings_TimeCheck[z], strings_Appearance[z]);
                        if (strings_TimeCheck[z].equals("")) {
                            Log.d("Evening", "strings_TimeCheck[2] มีค่าว่าง");
                            imageButtonE3.setImageResource(intsNotTakeYet[z]);
                            imageButtonE3.setVisibility(View.VISIBLE);
                        } else {
                            imageButtonE3.setImageResource(intsTake[z]);
                            imageButtonE3.setVisibility(View.VISIBLE);
                        }
                        // มี E2 แล้ว เติม E3
                    } else if (strValue.equals("E3")) {
                        myManage.adddisplayTABLEValue("E4", strings_Sum_id[z], strings_Main_id[z], date_specific, strings_TimeRef[z], strings_TimeCheck[z], strings_Appearance[z]);
                        if (strings_TimeCheck[z].equals("")) {
                            Log.d("Evening", "strings_TimeCheck[3] มีค่าว่าง");
                            imageButtonE4.setImageResource(intsNotTakeYet[z]);
                            imageButtonE4.setVisibility(View.VISIBLE);
                        } else {
                            imageButtonE4.setImageResource(intsTake[z]);
                            imageButtonE4.setVisibility(View.VISIBLE);
                        }
                        // มี E3 แล้ว เติม E4
                    } else if (strValue.equals("E4")) {
                        myManage.adddisplayTABLEValue("E5", strings_Sum_id[z], strings_Main_id[z], date_specific, strings_TimeRef[z], strings_TimeCheck[z], strings_Appearance[z]);
                        if (strings_TimeCheck[z].equals("")) {
                            Log.d("Evening", "strings_TimeCheck[4] มีค่าว่าง");
                            imageButtonE5.setImageResource(intsNotTakeYet[z]);
                            imageButtonE5.setVisibility(View.VISIBLE);
                        } else {
                            imageButtonE5.setImageResource(intsTake[z]);
                            imageButtonE5.setVisibility(View.VISIBLE);
                        }
                        // มี E4 แล้ว เติม E5
                    } else if (strValue.equals("E5")) {
                        myManage.adddisplayTABLEValue("E6", strings_Sum_id[z], strings_Main_id[z], date_specific, strings_TimeRef[z], strings_TimeCheck[z], strings_Appearance[z]);
                        if (strings_TimeCheck[z].equals("")) {
                            Log.d("Evening", "strings_TimeCheck[5] มีค่าว่าง");
                            imageButtonE6.setImageResource(intsNotTakeYet[z]);
                            imageButtonE6.setVisibility(View.VISIBLE);
                        } else {
                            imageButtonE6.setImageResource(intsTake[z]);
                            imageButtonE6.setVisibility(View.VISIBLE);
                        }
                        // มี E5 แล้ว เติม E6
                    } else if (strValue.equals("E6")) {
                        myManage.adddisplayTABLEValue("E7", strings_Sum_id[z], strings_Main_id[z], date_specific, strings_TimeRef[z], strings_TimeCheck[z], strings_Appearance[z]);
                        if (strings_TimeCheck[z].equals("")) {
                            Log.d("Evening", "strings_TimeCheck[6] มีค่าว่าง");
                            imageButtonE7.setImageResource(intsNotTakeYet[z]);
                            imageButtonE7.setVisibility(View.VISIBLE);
                        } else {
                            imageButtonE7.setImageResource(intsTake[z]);
                            imageButtonE7.setVisibility(View.VISIBLE);
                        }
                        // มี E6 แล้ว เติม E7
                    } else if (strValue.equals("E7")) {
                        myManage.adddisplayTABLEValue("E8", strings_Sum_id[z], strings_Main_id[z], date_specific, strings_TimeRef[z], strings_TimeCheck[z], strings_Appearance[z]);
                        if (strings_TimeCheck[z].equals("")) {
                            Log.d("Evening", "strings_TimeCheck[7] มีค่าว่าง");
                            imageButtonE8.setImageResource(intsNotTakeYet[z]);
                            imageButtonE8.setVisibility(View.VISIBLE);
                        } else {
                            imageButtonE8.setImageResource(intsTake[z]);
                            imageButtonE8.setVisibility(View.VISIBLE);
                        }
                        // มี E7 แล้ว เติม E8
                    } else if (strValue.equals("E8")) {
                        myManage.adddisplayTABLEValue("E9", strings_Sum_id[z], strings_Main_id[z], date_specific, strings_TimeRef[z], strings_TimeCheck[z], strings_Appearance[z]);
                        if (strings_TimeCheck[z].equals("")) {
                            Log.d("Evening", "strings_TimeCheck[8] มีค่าว่าง");
                            imageButtonE9.setImageResource(intsNotTakeYet[z]);
                            imageButtonE9.setVisibility(View.VISIBLE);
                        } else {
                            imageButtonE9.setImageResource(intsTake[z]);
                            imageButtonE9.setVisibility(View.VISIBLE);
                        }
                        // มี E8 แล้ว เติม E9
                    }

                } // if ของ Evening


                if (time.compareTo(convertedBedtime1) >= 0 && time.compareTo(convertedBedtime2) <= 0) {
                    Log.d("Bedtime", "อยู่ระหว่าง 00:00 - 05:59");
                    strValue = myManage.filterdisplayTABLE_MAEB_By_Position("B");
                    Log.d("Bedtime", "strREAD :" + strValue);
                    if (strValue.equals("Non value")) {
                        myManage.adddisplayTABLEValue("B1", strings_Sum_id[z], strings_Main_id[z], date_specific, strings_TimeRef[z], strings_TimeCheck[z], strings_Appearance[z]);
                        if (strings_TimeCheck[z].equals("")) {
                            Log.d("Bedtime", "strings_TimeCheck[0] มีค่าว่าง");
                            imageButtonB1.setImageResource(intsNotTakeYet[z]);
                            imageButtonB1.setVisibility(View.VISIBLE);
                        } else {
                            imageButtonB1.setImageResource(intsTake[z]);
                            imageButtonB1.setVisibility(View.VISIBLE);
                        }
                        // Non Value เติมค่า B1
                    } else if (strValue.equals("B1")) {
                        myManage.adddisplayTABLEValue("B2", strings_Sum_id[z], strings_Main_id[z], date_specific, strings_TimeRef[z], strings_TimeCheck[z], strings_Appearance[z]);
                        if (strings_TimeCheck[z].equals("")) {
                            Log.d("Bedtime", "strings_TimeCheck[1] มีค่าว่าง");
                            imageButtonB2.setImageResource(intsNotTakeYet[z]);
                            imageButtonB2.setVisibility(View.VISIBLE);
                        } else {
                            imageButtonB2.setImageResource(intsTake[z]);
                            imageButtonB2.setVisibility(View.VISIBLE);
                        }
                        // มี B1 แล้ว เติม B2
                    } else if (strValue.equals("B2")) {
                        myManage.adddisplayTABLEValue("B3", strings_Sum_id[z], strings_Main_id[z], date_specific, strings_TimeRef[z], strings_TimeCheck[z], strings_Appearance[z]);
                        if (strings_TimeCheck[z].equals("")) {
                            Log.d("Bedtime", "strings_TimeCheck[2] มีค่าว่าง");
                            imageButtonB3.setImageResource(intsNotTakeYet[z]);
                            imageButtonB3.setVisibility(View.VISIBLE);
                        } else {
                            imageButtonB3.setImageResource(intsTake[z]);
                            imageButtonB3.setVisibility(View.VISIBLE);
                        }
                        // มี B2 แล้ว เติม B3
                    } else if (strValue.equals("B3")) {
                        myManage.adddisplayTABLEValue("B4", strings_Sum_id[z], strings_Main_id[z], date_specific, strings_TimeRef[z], strings_TimeCheck[z], strings_Appearance[z]);
                        if (strings_TimeCheck[z].equals("")) {
                            Log.d("Bedtime", "strings_TimeCheck[3] มีค่าว่าง");
                            imageButtonB4.setImageResource(intsNotTakeYet[z]);
                            imageButtonB4.setVisibility(View.VISIBLE);
                        } else {
                            imageButtonB4.setImageResource(intsTake[z]);
                            imageButtonB4.setVisibility(View.VISIBLE);
                        }
                        // มี B3 แล้ว เติม B4
                    } else if (strValue.equals("B4")) {
                        myManage.adddisplayTABLEValue("B5", strings_Sum_id[z], strings_Main_id[z], date_specific, strings_TimeRef[z], strings_TimeCheck[z], strings_Appearance[z]);
                        if (strings_TimeCheck[z].equals("")) {
                            Log.d("Bedtime", "strings_TimeCheck[4] มีค่าว่าง");
                            imageButtonB5.setImageResource(intsNotTakeYet[z]);
                            imageButtonB5.setVisibility(View.VISIBLE);
                        } else {
                            imageButtonB5.setImageResource(intsTake[z]);
                            imageButtonB5.setVisibility(View.VISIBLE);
                        }
                        // มี B4 แล้ว เติม B5
                    } else if (strValue.equals("B5")) {
                        myManage.adddisplayTABLEValue("B6", strings_Sum_id[z], strings_Main_id[z], date_specific, strings_TimeRef[z], strings_TimeCheck[z], strings_Appearance[z]);
                        if (strings_TimeCheck[z].equals("")) {
                            Log.d("Bedtime", "strings_TimeCheck[5] มีค่าว่าง");
                            imageButtonB6.setImageResource(intsNotTakeYet[z]);
                            imageButtonB6.setVisibility(View.VISIBLE);
                        } else {
                            imageButtonB6.setImageResource(intsTake[z]);
                            imageButtonB6.setVisibility(View.VISIBLE);
                        }
                        // มี B5 แล้ว เติม B6
                    } else if (strValue.equals("B6")) {
                        myManage.adddisplayTABLEValue("B7", strings_Sum_id[z], strings_Main_id[z], date_specific, strings_TimeRef[z], strings_TimeCheck[z], strings_Appearance[z]);
                        if (strings_TimeCheck[z].equals("")) {
                            Log.d("Bedtime", "strings_TimeCheck[6] มีค่าว่าง");
                            imageButtonB7.setImageResource(intsNotTakeYet[z]);
                            imageButtonB7.setVisibility(View.VISIBLE);
                        } else {
                            imageButtonB7.setImageResource(intsTake[z]);
                            imageButtonB7.setVisibility(View.VISIBLE);
                        }
                        // มี B6 แล้ว เติม B7
                    } else if (strValue.equals("B7")) {
                        myManage.adddisplayTABLEValue("B8", strings_Sum_id[z], strings_Main_id[z], date_specific, strings_TimeRef[z], strings_TimeCheck[z], strings_Appearance[z]);
                        if (strings_TimeCheck[z].equals("")) {
                            Log.d("Bedtime", "strings_TimeCheck[7] มีค่าว่าง");
                            imageButtonB8.setImageResource(intsNotTakeYet[z]);
                            imageButtonB8.setVisibility(View.VISIBLE);
                        } else {
                            imageButtonB8.setImageResource(intsTake[z]);
                            imageButtonB8.setVisibility(View.VISIBLE);
                        }
                        // มี B7 แล้ว เติม B8
                    } else if (strValue.equals("B8")) {
                        myManage.adddisplayTABLEValue("B9", strings_Sum_id[z], strings_Main_id[z], date_specific, strings_TimeRef[z], strings_TimeCheck[z], strings_Appearance[z]);
                        if (strings_TimeCheck[z].equals("")) {
                            Log.d("Bedtime", "strings_TimeCheck[8] มีค่าว่าง");
                            imageButtonB9.setImageResource(intsNotTakeYet[z]);
                            imageButtonB9.setVisibility(View.VISIBLE);
                        } else {
                            imageButtonB9.setImageResource(intsTake[z]);
                            imageButtonB9.setVisibility(View.VISIBLE);
                        }
                        // มี B8 แล้ว เติม B9
                    }

                } // if ของ Bedtime


            }


        }


    }


    private void click_ImageButtonCalendar() {
        imageCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Calendartest.class));
            }
        });
    }


    private void clickAddbtn() {

        textViewAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                ViewGroup container = (ViewGroup) layoutInflater.inflate(R.layout.popup_main_add, null);

                popupWindow = new PopupWindow(container, ListPopupWindow.WRAP_CONTENT, ListPopupWindow.WRAP_CONTENT, true);
                popupWindow.showAtLocation(relativeLayout, Gravity.BOTTOM, 0, 0);

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
                        startActivity(new Intent(MainActivity.this, AddMedicineActivity.class));
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
                MODE_PRIVATE, null); //เชื่อมต่อกับ SQLiteDatabase
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM sumTABLE", null);
        cursor.moveToFirst();

        String[] dateStrings = new String[cursor.getCount()];
        String[] dayStrings = new String[cursor.getCount()];
        String[] monthStrings = new String[cursor.getCount()];
        String[] timeStrings = new String[cursor.getCount()];
        String[] hrStrings = new String[cursor.getCount()];
        String[] minStrings = new String[cursor.getCount()];

        for (int i = 0; i < cursor.getCount(); i++) {

            dateStrings[i] = cursor.getString(2);
            String[] strings = dateStrings[i].split("/");
            dayStrings[i] = strings[0];
            monthStrings[i] = strings[1];

            timeStrings[i] = cursor.getString(3);
            String[] strings1 = timeStrings[i].split(":");
            hrStrings[i] = strings1[0];
            minStrings[i] = strings1[1];

        } //ตอนนี้คาดว่าน่าจะยังไม่จำเป็นนะจ๊ะ

        cursor.close();

    }  //notication

    private void updatesumTABLE001() {
        Calendar calendar = Calendar.getInstance();  //ค้นหาเวลาในเครื่อง
        Calendar myCalendar1 = (Calendar) calendar.clone(); //clone เวลาในเครื่องเข้ามาใช้


        //myCalendar1.set(calendar.DAY_OF_MONTH,15);
        myCalendar1.set(Calendar.HOUR_OF_DAY, 23);
        myCalendar1.set(Calendar.MINUTE, 59);
        myCalendar1.set(Calendar.SECOND, 59);
        myCalendar1.set(Calendar.MILLISECOND, 59);

        Log.d("10MayV1", "myCaledar ==> " + myCalendar1.getTime().toString()); //กำหนดค่าค่าเวลาในการเตือน

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String currentDate = dateFormat.format(date);

        Intent intent = new Intent(getBaseContext(), DailyUpdateReceiver.class);
        //Intent intent = new Intent(getBaseContext(),AlarmReceiver.class);


        //Date
        //intent.putExtra("intDay",calendar.get(Calendar.DAY_OF_MONTH) );

        intent.putExtra("Date", currentDate);

        Random random = new Random();
        int myRandom = random.nextInt(1000);


        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(),
                myRandom, intent, 0);


        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, myCalendar1.getTimeInMillis(), pendingIntent); //Wakeuppppppp

    }

    private void bindWidget() {
        textViewAdd = (TextView) findViewById(R.id.textView_Main_Add);
        textViewNews = (TextView) findViewById(R.id.textView_News);
        relativeLayout = (RelativeLayout) findViewById(R.id.relative);
        imageCalendar = (ImageButton) findViewById(R.id.imageButtonCalendar);
        textViewMainDate = (TextView) findViewById(R.id.textViewMainDate);
        textViewMedication = (TextView) findViewById(R.id.textView_Medicine);
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
        tvMorning = (TextView) findViewById(R.id.tvMorning);
        tvAfternoon = (TextView) findViewById(R.id.tvAfternoon);
        tvEvening = (TextView) findViewById(R.id.tvEvening);
        tvBedtime = (TextView) findViewById(R.id.tvBedtime);

        //Invisible
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


    }


} //Main Class
