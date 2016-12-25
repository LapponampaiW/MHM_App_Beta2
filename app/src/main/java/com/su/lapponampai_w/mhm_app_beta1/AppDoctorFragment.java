package com.su.lapponampai_w.mhm_app_beta1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class AppDoctorFragment extends Fragment {

    private Context globalContext = null;
    EditText doctoreditText, noteEditText;
    TextView dateTextView, timeTextView,textViewBackToMain;
    CheckBox checkBox;
    LinearLayout linearLayout;
    Button cancelButton, saveButton;
    ImageButton imageButton;
    ListView listView;
    Fragment fragmentAppDoctorFragment;



    public AppDoctorFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_app_doctor, container, false);
        globalContext = this.getActivity();
        fragmentAppDoctorFragment = this;

        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bindWidget(view);

        showListView(view);

        clickImageButton(view);

        clickBackToMain(view);


    }

    private void clickBackToMain(View view) {

        textViewBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }


    private void clickImageButton(View v) {

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PopUpAppDoctorFragment.class);
                startActivity(intent);
            }
        });
    }

    private void showListView(View v) {

        MyManage myManage = new MyManage(v.getContext());
        MyData myData = new MyData();

        //ShowListView ขึ้นมา
        final String[][] stringsAppointment = {myManage.readAllappointmentTABLE(0),
                myManage.readAllappointmentTABLE(2),
                myManage.readAllappointmentTABLE(3),myManage.readAllappointmentTABLE(4)
                ,myManage.readAllappointmentTABLE(5)};
        //0 id,1 Date,2 Time,3 Doctor,4 Note

        if (!stringsAppointment[0][0].equals("")) {

            //ArrayList<String> stringArrayList = new ArrayList<String>();

            //int intIndex = 0;
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date_Specific = new Date();
            Date date_current = new Date();
            String sDate = myData.currentDay();

            for(int i = 0;i<stringsAppointment[0].length;i++) {

                try {
                    date_Specific = dateFormat.parse(stringsAppointment[1][i]);
                    date_current = dateFormat.parse(sDate);

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                String test_Specific = dateFormat.format(date_Specific);
                String test_current = dateFormat.format(date_current);
                Log.d("12July16V1", "test_Specific : " + test_Specific);
                Log.d("12July16V1", "test_current : " + test_current);

                if (date_Specific.compareTo(date_current) < 0) {
                    stringsAppointment[4][i] = "หมายเหตุ :".concat(stringsAppointment[4][i]).concat(" (เลยวันนัดที่กำหนดแล้ว!!!)");
                } else {
                    if (!stringsAppointment[4][i].equals("")) {
                        stringsAppointment[4][i] = "หมายเหตุ :".concat(stringsAppointment[4][i]);
                    }
                }

                stringsAppointment[1][i] = "วันที่นัด : ".concat(stringsAppointment[1][i]);
                Log.d("12July16V1", "วันที่นัด : " + stringsAppointment[1][i]);

                if (!stringsAppointment[2][i].equals("")) {
                    stringsAppointment[2][i] = "เวลาที่นัด : ".concat(stringsAppointment[2][i]);
                } else {
                    stringsAppointment[2][i] = "เวลาที่นัด : ไม่ได้ระบุ";
                }
                stringsAppointment[3][i] = "ชื่อแพทย์ผู้นัด : ".concat(stringsAppointment[3][i]);
            }


            MyAdaptorAppointment myAdaptorAppointment = new MyAdaptorAppointment(v.getContext(),
                    stringsAppointment[3], stringsAppointment[1], stringsAppointment[2], stringsAppointment[4]);
            listView.setAdapter(myAdaptorAppointment);


        }
    }

    private void bindWidget(View v) {
        listView = (ListView) v.findViewById(R.id.listViewAppointment);
        imageButton = (ImageButton) v.findViewById(R.id.imageButton11);
        textViewBackToMain = (TextView) v.findViewById(R.id.textView106);
    }

}
