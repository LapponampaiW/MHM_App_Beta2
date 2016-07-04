package com.su.lapponampai_w.mhm_app_beta1;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class DailyFragment extends Fragment {

    private Context globalContext = null;


    public DailyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_daily, container, false);
        globalContext = this.getActivity();

        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //bindWidget
        CalendarView calendarView = (CalendarView) view.findViewById(R.id.calendarView);
        final ListView listView = (ListView) view.findViewById(R.id.listViewDaily);

        calendarView.setShowWeekNumber(false);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                MyManage myManage = new MyManage(globalContext);
                MyData myData = new MyData();

                //เราเอาค่าจาก sum table querybyday
                String strDateRef = myData.createStringDay(dayOfMonth, month + 1, year);

                Log.d("DailyFragment", strDateRef);

                //เอาค่าของ sumTABLE มาทุกค่าโดย filter วันที่คลิกในปฏิทิน
                String[] strings_id = myManage.filtersumTABLE_by_DateRef(strDateRef, 0);
                String[] strings_Main_id = myManage.filtersumTABLE_by_DateRef(strDateRef, 1);
                String[] strings_DateRef = myManage.filtersumTABLE_by_DateRef(strDateRef, 2);
                String[] strings_TimeRef = myManage.filtersumTABLE_by_DateRef(strDateRef, 3);
                String[] strings_DateCheck = myManage.filtersumTABLE_by_DateRef(strDateRef, 4);
                String[] strings_TimeCheck = myManage.filtersumTABLE_by_DateRef(strDateRef, 5);
                String[] strings_SkipHold = myManage.filtersumTABLE_by_DateRef(strDateRef, 6);
                String[] stringsReadmainTABLE_id = myManage.readAllMainTABLE_Full(0);
                String[] stringsReadmainTABLE_Tradename = myManage.readAllMainTABLE_Full(2);
                String[] stringsReadmainTABLE_Pharmaco = myManage.readAllMainTABLE_Full(8);

                Log.d("DailyFragment", strings_Main_id[0]);
                Log.d("DailyFragment", strings_id[0]);
                Toast.makeText(getActivity(), strings_Main_id[0], Toast.LENGTH_SHORT).show();

                if (!strings_id[0].equals("")) {

                    //รวม DateRef,TimeRef เป็น DateTimeRef , รวม DateCheck,TimeCheck เป็น DateTimeCheck
                    String[] strings_DateTimeRef = new String[strings_id.length];
                    String[] strings_DateTimeCheck = new String[strings_id.length];
                    String[] strings_Tradename = new String[strings_id.length];
                    String[] strings_AdherenceIndicator = new String[strings_id.length];

                    for (int i = 0; i < strings_Main_id.length; i++) {

                        strings_DateTimeRef[i] = strings_DateRef[i] + " " + strings_TimeRef[i];
                        if (!strings_DateCheck[i].equals("")) {
                            strings_DateTimeCheck[i] = strings_DateCheck[i] + " " + strings_TimeCheck[i];
                        } else {
                            strings_DateTimeCheck[i] = "";
                        }

                        for (int w = 0; w < stringsReadmainTABLE_id.length; w++) {
                            if (strings_Main_id[i].equals(stringsReadmainTABLE_id[w])) {
                                strings_Tradename[i] = stringsReadmainTABLE_Tradename[w];
                                strings_AdherenceIndicator[i] = stringsReadmainTABLE_Pharmaco[w];
                            }
                        }

                    } //ได้ค่าครบทุกค่าแล้ว

                    Log.d("DailyFragment1", strings_DateTimeRef[0]);

                    Log.d("DailyFragment1", strings_Tradename[0]);
                    Log.d("DailyFragment1", strings_AdherenceIndicator[0]);


                    //คำนวณกันเถอะโดยมี เงื่อนไงดังนี้
                    //ถ้า Pharmaco มีค่าเป็น A ต้องมีระยะเวลาในการรับประทาน +- ไม่เกิน 30 นาที
                    //ถ้า Pharmaco เป็ฯอย่างอื่น แค่ check ว่าทานก็ Ok แล้วครับ

                    String[] strInitial = new String[strings_id.length];
                    String[] strResult = new String[strings_id.length];

                    for (int i = 0; i < strings_id.length; i++) {
                        int i_initial = 0;
                        int i_total = 0;
                        if (!strings_SkipHold[i].equals("")) {
                            i_initial = i_initial + 1;
                            i_total = i_total + 1;
                        } else {
                            if (strings_DateTimeCheck[i].equals("")) {
                                i_initial = i_initial + 0;
                                i_total = i_total + 1;
                            } else {
                                if (strings_AdherenceIndicator[i].equals("A")) {
                                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                                    java.util.Date date_Ref = new java.util.Date();
                                    java.util.Date date_Check = new java.util.Date();

                                    try {
                                        date_Ref = dateFormat.parse(strings_DateTimeRef[i]);
                                        date_Check = dateFormat.parse(strings_DateTimeCheck[i]);
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }

                                    //ยกตัวอย่างให้อยู่ใน ไม่เกิน 30 นาที
                                    Calendar calendar_Ref1 = Calendar.getInstance();
                                    Calendar calendar_Ref2 = Calendar.getInstance();
                                    calendar_Ref1.setTime(date_Ref);
                                    calendar_Ref2.setTime(date_Ref);
                                    calendar_Ref1.add(Calendar.MINUTE, -30);
                                    calendar_Ref2.add(Calendar.MINUTE, 30);

                                    java.util.Date date_Ref1 = calendar_Ref1.getTime();
                                    java.util.Date date_Ref2 = calendar_Ref1.getTime();

                                    if (date_Ref1.compareTo(date_Check) < 0 && date_Ref2.compareTo(date_Check) >= 0) {
                                        i_initial = i_initial + 1;
                                        i_total = i_total + 1;
                                    } else {
                                        i_initial = i_initial + 0;
                                        i_total = i_total + 1;
                                    }


                                } else {
                                    i_initial = i_initial + 1;
                                    i_total = i_total + 1;
                                }
                            }
                        }
                        strInitial[i] = Integer.toString(i_initial);
                        strResult[i] = Integer.toString(i_total);
                        Log.d("DailyFragment", "i_initial = " + Integer.toString(i_initial));
                        Log.d("DailyFragment", "i_result = " + Integer.toString(i_total));
                    }


                    //เอาค่า Tradename จาก Main_id



                /*
                MyData myData = new MyData();
                String[] strings1 = {"Metformin", "Simvastatin"};
                String[] strings2 = {"20", "80"};
                String[] s1 = {"1", "2"};
                int[] intsIndex1 = myData.translate_Small_Appearance(s1);
                int[] intsIndex2 = {R.drawable.bad_adherence,R.drawable.good_adherence};

                MyAdaptorAdherence myAdaptorAdherence = new
                        MyAdaptorAdherence(getContext(),
                        strings1, strings2, intsIndex2, intsIndex1);

                listView.setAdapter(myAdaptorAdherence);
                */

                }
            }

        });

    }


}
