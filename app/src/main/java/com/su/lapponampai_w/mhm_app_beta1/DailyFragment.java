package com.su.lapponampai_w.mhm_app_beta1;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class DailyFragment extends Fragment {



    public DailyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_daily, container, false);

        //bindWidget
        CalendarView calendarView = (CalendarView) view.findViewById(R.id.calendarView);


        //ให้ calendar สัปดาห์ต่อปีไม่มี
        calendarView.setShowWeekNumber(false);
        Toast.makeText(getContext(),";sfs;lfj",Toast.LENGTH_LONG).show();

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

            }
        });




        return view;
    }




}
