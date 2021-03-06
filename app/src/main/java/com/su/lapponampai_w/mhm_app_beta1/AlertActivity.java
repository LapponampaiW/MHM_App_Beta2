package com.su.lapponampai_w.mhm_app_beta1;

import android.content.DialogInterface;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AlertActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);

        bindWidget();

        showViewAndSetListView();






    }

    private void showViewAndSetListView() {

        final MyManage myManage = new MyManage(this);
        String[] stringsAlert_ArrayList = myManage.readAllalertTABLE(5);
        String[] stringsAlert_Type = myManage.readAllalertTABLE(1);

        ArrayList<String> stringArrayListDetail = new ArrayList<>();
        ArrayList<String> stringArrayListCriteria = new ArrayList<>();
        ArrayList<String> stringArrayListId = new ArrayList<>();
        ArrayList<String> stringArrayListPic = new ArrayList<>();

        int iIndex = 0;

        for(int i=0;i<stringsAlert_ArrayList.length;i++) {
            if (!stringsAlert_ArrayList[i].equals("")) {
                if (stringsAlert_Type[i].equals("C") || stringsAlert_Type[i].equals("V") ||
                        stringsAlert_Type[i].equals("CV")) {

                    String[] strings = stringsAlert_ArrayList[i].split(";"); //0,1,2
                    stringArrayListId.add(iIndex, strings[0]); //เก็บ id
                    stringArrayListCriteria.add(iIndex, strings[1]); // เก็บ A B C
                    stringArrayListDetail.add(iIndex, strings[2]); // เก็บ คำอธิบาย
                    stringArrayListPic.add(iIndex, "A1"); //คำ แทนค่ารูป
                    iIndex = iIndex + 1;
                }
            } //if
        } //for

        String[] stringsId = new String[stringArrayListId.size()];
        String[] stringsCriteria = new String[stringArrayListId.size()];
        String[] stringsDetail  = new String[stringArrayListId.size()];
        String[] stringsPic  = new String[stringArrayListId.size()];
        stringsId = stringArrayListId.toArray(stringsId);
        stringsCriteria = stringArrayListCriteria.toArray(stringsCriteria); //เก็บ A B C
        stringsDetail = stringArrayListDetail.toArray(stringsDetail);
        stringsPic = stringArrayListPic.toArray(stringsPic);

        final MyData myData = new MyData();
        int[] intsIndex = myData.translate_Appearance_Alert(stringsPic);
        //ต่อไปคือแสดง listView
        MyAdaptorAlert myAdaptorAlert = new MyAdaptorAlert(getBaseContext(), stringsDetail, intsIndex);
        listView.setAdapter(myAdaptorAlert);

        final String[] alertId = stringsId;
        final String[] alertCriteria = stringsCriteria;


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                //Click ListView
                String[] stringsAlert_Id = myManage.readAllalertTABLE(0);
                String[] stringsAlert_DateLab = myManage.readAllalertTABLE(3);
                String[] stringsAlert_Type = myManage.readAllalertTABLE(1);
                String[] stringsAlert_Detail = myManage.readAllalertTABLE(4);

                String sDayLab = "";
                String sType = "";
                String sDetail = "";
                for(int i=0;i<stringsAlert_Type.length;i++) {
                    if (alertId[position].equals(stringsAlert_Id[i])) {
                        sDayLab = stringsAlert_DateLab[i];
                        sType = stringsAlert_Type[i];
                        sDetail = stringsAlert_Detail[i];
                    }
                }

                final String sDetail_Final = sDetail;

                Date dateDayLab = myData.stringChangetoDateWithOutTime(sDayLab); //Date ของวันตรวจ Lab
                Date dateCurrentDay = myData.stringChangetoDateWithOutTime(myData.currentDay());
                //ได้ค่าที่ต้องการมาแล้วคราวนี้มานับกันว่าเหลืออีกกี่วันที่จะถึงวันตรวจ Lab
                Calendar calendarDateLab = Calendar.getInstance();
                calendarDateLab.setTime(dateDayLab);
                calendarDateLab.set(Calendar.HOUR_OF_DAY,0);
                calendarDateLab.set(Calendar.MINUTE,0);
                calendarDateLab.set(Calendar.SECOND,0);
                calendarDateLab.set(Calendar.MILLISECOND,0);

                Calendar calendarCurrentDay = Calendar.getInstance();
                calendarCurrentDay.setTime(dateCurrentDay);
                calendarCurrentDay.set(Calendar.HOUR_OF_DAY,0);
                calendarCurrentDay.set(Calendar.MINUTE,0);
                calendarCurrentDay.set(Calendar.SECOND,0);
                calendarCurrentDay.set(Calendar.MILLISECOND,0);

                //calendarDateLab มีค่ามากกว่า
                long diffTime = calendarDateLab.getTimeInMillis() - calendarCurrentDay.getTimeInMillis();
                long diffDay = diffTime / (24 * 60 * 60 * 1000); //ได้เลขค่าวันมาแล้ว

                //Toast.makeText(getBaseContext(),Long.toString(diffDay),Toast.LENGTH_LONG).show();

                String sType_Translate = "";
                if (sType.equals("C")) {
                    sType_Translate = "CD4";
                } else if (sType.equals("V")) {
                    sType_Translate = "Viral load";
                } else {
                    sType_Translate = "CD4, Viral load";
                }

                String sInformation = "อีก " + Long.toString(diffDay) + " วันจะมีตรวจค่าเลือด (" +
                        sType_Translate + ") แนะนำให้กินยาอย่างสม่ำเสมอ เพื่อค่า Lab ที่ดีและเพื่อสุขภาพของท่าน";



                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setIcon(R.drawable.logo_mhm);
                builder.setTitle("ข่าวสารทั่วไป");
                builder.setMessage(sInformation);
                builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("ลบแถบการเตือน", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //ทำให้ไม่ต้องเตือนขึ้นมาอีก
                        //041260
                        String sFirst = "";
                        String sSecond = "";
                        String sThird = "";
                        String sFirstABC = "";
                        String s0 = "";
                        String s1 = "";
                        String s2 = "";
                        String sNewAlertDetail = "";
                        int iPosition = 4;
                        String[] stringsDetailQuery = sDetail_Final.split(";");

                        for(int i=0;i<stringsDetailQuery.length;i++) {
                            //Loop กี่รอบหละ
                            String[] stringsDetailQueryQuery = stringsDetailQuery[i].split(",");
                            if (i == 0) {
                                sFirstABC = stringsDetailQueryQuery[0]; //เอาตัวแรกของรอบแรกมา
                            } //if
                            //stringsDetailQueryQuery มี 3 Array แน่ๆ
                            if (stringsDetailQueryQuery[0].equals(alertCriteria[position])) {
                                //Toast.makeText(getBaseContext(), alertCriteria[position],Toast.LENGTH_LONG).show();
                                iPosition = i;
                                s0 = stringsDetailQueryQuery[0];
                                s1 = stringsDetailQueryQuery[1];
                                s2 = "Y";
                            } //if
                        }//for
                        //ได้ค่าครบแล้วนะ

                        Log.d("040160V1", "s0 : " + s0);
                        Log.d("040160V1", "s1 : " +s1);
                        Log.d("040160V1", "s2 : " +s2);




                        if (stringsDetailQuery.length == 3) {
                            if (iPosition == 0) {
                                sNewAlertDetail = s0 + "," + s1 + "," + s2 + ";" + stringsDetailQuery[1] + ";" + stringsDetailQuery[2];
                            } else if (iPosition == 1) {
                                sNewAlertDetail = stringsDetailQuery[0] + ";" + s0 + "," + s1 + "," + s2 + ";" + stringsDetailQuery[2];
                            } else if (iPosition == 2) {
                                sNewAlertDetail = stringsDetailQuery[0] + ";" + stringsDetailQuery[1] + ";" + s0 + "," + s1 + "," + s2;
                            }


                        } else if (stringsDetailQuery.length == 2) {
                            if (iPosition == 0) {
                                sNewAlertDetail = s0 + "," + s1 + "," + s2 + ";" + stringsDetailQuery[1];
                            } else if (iPosition == 1) {
                                sNewAlertDetail = stringsDetailQuery[0] + ";" + s0 + "," + s1 + "," + s2;
                            }

                        } else {
                            sNewAlertDetail = s0 + "," + s1 + "," + s2;
                        }

                        //update ค่า alert_Detail และ alert_ArrayList ^ ^
                        myManage.updateAlertTABLE_alert_Detail(alertId[position], sNewAlertDetail);
                        myManage.updateAlertTABLE_alert_ArrayList(alertId[position], "");

                        //เปิดหน้านี้อีกรอบ
                        Intent intent = new Intent(getBaseContext(),AlertActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();


                        dialog.dismiss();
                    }
                });
                builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        dialog.dismiss();
                    }
                });
                builder.show();






            }
        });

    }


    private void bindWidget() {
        listView = (ListView) findViewById(R.id.listViewAlertActivity);



    }
}
