package com.su.lapponampai_w.mhm_app_beta1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

        clickDeleteInListView(view);


    }

    private void clickDeleteInListView(final View v) {

        MyManage myManage = new MyManage(v.getContext());
        final String[][] stringsAppointment = {myManage.readAllappointmentTABLE(0),
                myManage.readAllappointmentTABLE(2),
                myManage.readAllappointmentTABLE(3),myManage.readAllappointmentTABLE(4)
                ,myManage.readAllappointmentTABLE(5)};


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setCancelable(false);
                builder.setIcon(R.drawable.logo_mhm);
                builder.setTitle("ลบข้อมูลวันนัด");
                builder.setMessage("ยืนยันการลบข้อมูลวันนัด");
                builder.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        String id = stringsAppointment[0][position];
                        Log.d("13JulyV1", "id : " + id);


                        MyHelper helper = new MyHelper(v.getContext());

                        helper = new MyHelper(v.getContext());

                        SQLiteDatabase readSqLiteDatabase = helper.getReadableDatabase();
                        readSqLiteDatabase.delete("appointmentTABLE", "_id = " + id, null);


                        Toast.makeText(getActivity().getBaseContext(),"Delete in appointmentTABLE",Toast.LENGTH_SHORT).show();


                        Intent intent = new Intent(getActivity().getBaseContext(),AppointmentActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        getActivity().finish();


                    }
                });
                builder.show();

                return false;
            }
        });

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
                Intent intent = new Intent(v.getContext(), PopUpAppDoctorActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showListView(View v) {

        MyManage myManage = new MyManage(v.getContext());
        MyData myData = new MyData();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date_Specific = new Date();
        Date date_current = new Date();
        String sDate = myData.currentDay();

        //ShowListView ขึ้นมา
        final String[][] stringsAppointment = {myManage.readAllappointmentTABLE(0),
                myManage.readAllappointmentTABLE(2),
                myManage.readAllappointmentTABLE(3),myManage.readAllappointmentTABLE(4)
                ,myManage.readAllappointmentTABLE(5)};
        //0 id,1 Date,2 Time,3 Doctor,4 Note

        Boolean aBoolean = true; //ตรวจสอบว่า Doctor ตำแหน่งที่ 3 เป็นค่าว่างทั้งหมดหรือปล่าว
        ArrayList<String> stringArrayListId = new ArrayList<String>();
        int iIndex = 0;
        if (!stringsAppointment[0][0].equals("")) {
            for (int i = 0; i < stringsAppointment[0].length; i++) {
                // Check ว่ายังไม่เกินวันที่กำหนด
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

                if (!stringsAppointment[3][i].equals("") && date_Specific.compareTo(date_current) >= 0) {
                    stringArrayListId.add(iIndex,stringsAppointment[0][i]); //เอาค่า id ของ AppointmentTABLE มาทำ ArrayList
                    iIndex = iIndex + 1;
                    aBoolean = false;
                }
            } //for
        } // if

        if (!aBoolean) { //มีค่า ของ Doctor ให้ทำ listView
            String[] stringsSelectedId = new String[stringArrayListId.size()];
            stringsSelectedId = stringArrayListId.toArray(stringsSelectedId); //สร้าง Array
            String[] stringsSelectedDate = new String[stringsSelectedId.length];
            String[] stringsSelectedTime = new String[stringsSelectedId.length];
            String[] stringsSelectedDoctor = new String[stringsSelectedId.length];
            String[] stringsSelectedNote = new String[stringsSelectedId.length];



            for(int i = 0;i <stringsAppointment[0].length;i++) {
                for(int x =0 ;x <stringsSelectedId.length;x++) {
                    if (stringsAppointment[0][i].equals(stringsSelectedId[x])) {

                        //stringsSelectedDate[x] = stringsAppointment[1][i];
                        //stringsSelectedTime[x] = stringsAppointment[2][i];
                        //stringsSelectedDoctor[x] = stringsAppointment[3][i];
                        //stringsSelectedNote[x] = stringsAppointment[4][i];
                        if (!stringsAppointment[4][i].equals("")) {
                            stringsSelectedNote[x] = "หมายเหตุ :".concat(stringsAppointment[4][i]);
                        } else {
                            stringsSelectedNote[x] = "";
                        }
                        stringsSelectedDate[x] = "วันที่นัด : ".concat(stringsAppointment[1][i]);
                        if (!stringsAppointment[2][i].equals("")) {
                            stringsSelectedTime[x] = "เวลาที่นัด : ".concat(stringsAppointment[2][i]);
                        } else {
                            stringsSelectedTime[x] = "เวลาที่นัด : ไม่ได้ระบุ";
                        }
                        stringsSelectedDoctor[x] = "ชื่อแพทย์ผู้นัด : ".concat(stringsAppointment[3][i]);
                    }
                } //for2
            } //for
            MyAdaptorAppointment myAdaptorAppointment = new MyAdaptorAppointment(v.getContext(),
                    stringsSelectedDoctor, stringsSelectedDate, stringsSelectedTime, stringsSelectedNote);
            listView.setAdapter(myAdaptorAppointment);



        }







        /*
        if (!stringsAppointment[0][0].equals("")) {

            //int intIndex = 0;
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date_Specific = new Date();
            Date date_current = new Date();
            String sDate = myData.currentDay();

            for(int i = 0;i<stringsAppointment[0].length;i++) { //row

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


        } //if
        */

    }

    private void bindWidget(View v) {
        listView = (ListView) v.findViewById(R.id.listViewAppointment);
        imageButton = (ImageButton) v.findViewById(R.id.imageButton11);
        textViewBackToMain = (TextView) v.findViewById(R.id.textView106);
    }

}
