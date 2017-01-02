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
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class AppLabFragment extends Fragment {

    private Context globalContext = null;
    ImageButton imageButton;
    ListView listView;
    TextView textViewBackToMain;
    Fragment fragmentAppDoctorFragment;


    public AppLabFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_app_lab, container, false);


    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bindWidget(view);

        clickBackToMain(view);

        clickImageButton(view);

        showListView(view);



    }



    private void showListView(View v) {

        MyManage myManage = new MyManage(v.getContext());
        MyData myData = new MyData();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date_Specific = new Date();
        Date date_current = new Date();
        String sDate = myData.currentDay();

        //ShowListView ขึ้นมา
        final String[][] stringsLab = {myManage.readAllappointmentTABLE(0),
                myManage.readAllappointmentTABLE(2),
                myManage.readAllappointmentTABLE(3),myManage.readAllappointmentTABLE(5)
                ,myManage.readAllappointmentTABLE(7)};
        //0 id,1 Date,2 Time,3 Note,4 LabDetail

        Boolean aBoolean = true; //ตรวจสอบว่า LabDetail ตำแหน่งที่ 4 เป็นค่าว่างทั้งหมดหรือปล่าว
        ArrayList<String> stringArrayListId = new ArrayList<String>();
        int iIndex = 0;

        if (!stringsLab[0][0].equals("")) {
            for (int i = 0; i < stringsLab[0].length; i++) {
                // Check ว่ายังไม่เกินวันที่กำหนด
                try {
                    date_Specific = dateFormat.parse(stringsLab[1][i]);
                    date_current = dateFormat.parse(sDate);

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                String test_Specific = dateFormat.format(date_Specific);
                String test_current = dateFormat.format(date_current);
                Log.d("12July16V1", "test_Specific : " + test_Specific);
                Log.d("12July16V1", "test_current : " + test_current);

                if (!stringsLab[4][i].equals("") && date_Specific.compareTo(date_current) >= 0) {
                    stringArrayListId.add(iIndex,stringsLab[0][i]); //เอาค่า id ของ AppointmentTABLE มาทำ ArrayList
                    iIndex = iIndex + 1;
                    aBoolean = false;
                }
            } //for
        } // if


        if (!aBoolean) { //มีค่า ของ LabDetail ให้ทำ listView
            String[] stringsSelectedId = new String[stringArrayListId.size()];
            stringsSelectedId = stringArrayListId.toArray(stringsSelectedId); //สร้าง Array
            String[] stringsSelectedDate = new String[stringsSelectedId.length];
            String[] stringsSelectedTime = new String[stringsSelectedId.length];
            String[] stringsSelectedNote = new String[stringsSelectedId.length];
            String[] stringsSelectedLabDetail = new String[stringsSelectedId.length];



            for(int i = 0;i <stringsLab[0].length;i++) {
                for(int x =0 ;x <stringsSelectedId.length;x++) {
                    if (stringsLab[0][i].equals(stringsSelectedId[x])) {
                        stringsSelectedDate[x] = "วันนัดหมายตรวจแล๊ป : ".concat(stringsLab[1][i]);
                        if (!stringsLab[2][i].equals("")) {
                            stringsSelectedTime[x] = "เวลาที่นัด : ".concat(stringsLab[2][i]);
                        } else {
                            stringsSelectedTime[x] = "เวลาที่นัด : ไม่ได้ระบุ";
                        }
                        if (!stringsLab[3][i].equals("")) {
                            stringsSelectedNote[x] = "หมายเหตุ :".concat(stringsLab[3][i]);
                        } else {
                            stringsSelectedNote[x] = "";
                        }

                        String[] stringsQueryLab = stringsLab[4][i].split(",");
                        LabActivity labActivity = new LabActivity(); //เอาค่าของ LabDetail มาจากที่นั้น
                        stringsSelectedLabDetail[x] = "";
                        Boolean aBooleanLabDetail = true;
                        for(int y =0;y<stringsQueryLab.length;y++) {
                            if (stringsQueryLab[y].equals("1")) {
                                if (stringsSelectedLabDetail[x].equals("")) {
                                    stringsSelectedLabDetail[x] = "ค่าแล๊ปที่ตรวจ : ".concat(labActivity.stringsLabHeading[y]);
                                    aBooleanLabDetail = false;
                                } else {
                                    stringsSelectedLabDetail[x] = stringsSelectedLabDetail[x] + ", " + labActivity.stringsLabHeading[y];
                                }
                            }// if
                        } //for

                        if (aBooleanLabDetail) {
                            stringsSelectedLabDetail[x] = "ค่าแล๊ปที่ตรวจ : ไม่ได้ระบุ";
                        }



                    }
                } //for2
            } //for

            MyAdaptorAppLab myAdaptorAppLab = new MyAdaptorAppLab(v.getContext()
                    , stringsSelectedDate, stringsSelectedTime
                    , stringsSelectedLabDetail, stringsSelectedNote);
            listView.setAdapter(myAdaptorAppLab);

            final String[] strings = stringsSelectedId;


            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, final View view, final int position, long id) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setCancelable(false);
                    builder.setIcon(R.drawable.logo_mhm);
                    builder.setTitle("ลบข้อมูลวันนัดตรวจแล๊ป");
                    builder.setMessage("ยืนยันการลบข้อมูลวันนัดตรวจแล๊ป");
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
                            String id = strings[position];
                            Log.d("13JulyV1", "id : " + id);


                            MyHelper helper = new MyHelper(view.getContext());

                            SQLiteDatabase readSqLiteDatabase = helper.getReadableDatabase();
                            readSqLiteDatabase.delete("appointmentTABLE", "_id = " + id, null); //ลบใน id ของ appointmentTABLE
                            readSqLiteDatabase.delete("alertTABLE", "alert_Lab_Id = " + id, null); //ลบใน alertTABLE


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



        /*
        String[] s1 = {"1", "2", "3"};
        MyAdaptorAppLab myAdaptorAppLab = new MyAdaptorAppLab(v.getContext(), s1, s1, s1, s1);
        listView.setAdapter(myAdaptorAppLab);
        */


    }

    private void clickImageButton(View v) {

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PopUpAppLabActivity.class);
                startActivity(intent);
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

    private void bindWidget(View v) {
        listView = (ListView) v.findViewById(R.id.listViewAppointment);
        imageButton = (ImageButton) v.findViewById(R.id.imageButton11);
        textViewBackToMain = (TextView) v.findViewById(R.id.textView106);
    }


}
