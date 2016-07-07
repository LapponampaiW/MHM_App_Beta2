package com.su.lapponampai_w.mhm_app_beta1;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.NestedScrollingChild;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {

    //Explicit
    Activity activityNewsActivity;
    String[]stringsNewsTABLE_Med_id,stringsAdaptorid,
            stringsAppearance,stringsAdaptorAppearance,stringsAdaptorMessage,
            stringsNewsTABLE_Appearance_edit,stringsAdaptorAppearance_edit,
            stringsAdaptorActivity;
    String[] stringsMainTABLE_id,stringsMainTABLE_Med_id;

    String[] stringsMedTABLE_id,stringsMedTABLE_Generic1,stringsMedTABLE_Generic2,
            stringsMedTABLE_Generic3,stringsMedTABLE_Generic4,stringsArray_AllGeneric;

    String[] stringsNewsTABLE_Generic_id,stringsNewsTABLE_Appearance_News,stringsNewsTABLE_Message,
            stringsNewsTABLE_Criteria,stringsNewsTABLE_Activity,stringsNewsTABLE_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        activityNewsActivity = this;



        MyManage myManage = new MyManage(this);
        MyData myData = new MyData();


        stringsMainTABLE_id = myManage.readAllMainTABLE_Full(0);
        //ถ้าไม่มีข่าวสารให้ออกเลย
        if (stringsMainTABLE_id[0].equals("")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(R.drawable.icon_question);
            builder.setTitle("ไม่มีข่าวสาร!!!");
            builder.setMessage("ข่าวสารจะปรากฎตามรายการยาที่กำหนด\nกรุณาเพิ่มยา โดยเข้าไปที่ +เพิ่มเติม+ ==> เพิ่มรายการยา");
            builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    finish();
                }
            });
            builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    finish();
                }
            });
            builder.show();
        }

        //เอา Generic_id จาก MainTABLE จากยกทุกตัวมาก่อนเลย
        stringsMainTABLE_Med_id = myManage.readAllMainTABLE_Full(1); //Med_id ในตาราง mainTABLE ทั้งหมด
        stringsMedTABLE_id = myManage.readAllmedTABLE(0); //Med_id ในตาราง MedTABLE
        stringsMedTABLE_Generic1 = myManage.readAllmedTABLE(3); //Generic_name1 ในตาราง MedTABLE
        stringsMedTABLE_Generic2 = myManage.readAllmedTABLE(6); //Generic_name2 ในตาราง MedTABLE
        stringsMedTABLE_Generic3 = myManage.readAllmedTABLE(9); //Generic_name3 ในตาราง MedTABLE
        stringsMedTABLE_Generic4 = myManage.readAllmedTABLE(12); //Generic_name4 ในตาราง MedTABLE

        stringsNewsTABLE_id = myManage.readAllnewsTABLE(0);
        stringsNewsTABLE_Generic_id = myManage.readAllnewsTABLE(1); //Generic_id ในตาราง newsTABLE
        stringsNewsTABLE_Message = myManage.readAllnewsTABLE(2);
        stringsNewsTABLE_Appearance_News = myManage.readAllnewsTABLE(3);
        stringsNewsTABLE_Criteria = myManage.readAllnewsTABLE(4);
        stringsNewsTABLE_Activity = myManage.readAllnewsTABLE(5);




        ArrayList<String> genericStringArrayList = new ArrayList<String>();
        int iIndex = 0;
        for(int i = 0;i<stringsMainTABLE_Med_id.length;i++) {

            for(int x = 0;x<stringsMedTABLE_id.length;x++) {
                if (stringsMainTABLE_Med_id[i].equals(stringsMedTABLE_id[x])) {
                    genericStringArrayList.add(iIndex, stringsMedTABLE_Generic1[x]);
                    iIndex = iIndex + 1;
                    if (!stringsMedTABLE_Generic2[x].equals("1")) {
                        genericStringArrayList.add(iIndex, stringsMedTABLE_Generic2[x]);
                        iIndex = iIndex + 1;
                    }
                    if (!stringsMedTABLE_Generic3[x].equals("1")) {
                        genericStringArrayList.add(iIndex, stringsMedTABLE_Generic3[x]);
                        iIndex = iIndex + 1;
                    }
                    if (!stringsMedTABLE_Generic4[x].equals("1")) {
                        genericStringArrayList.add(iIndex, stringsMedTABLE_Generic4[x]);
                        iIndex = iIndex + 1;
                    }
                }
            }
        }

        stringsArray_AllGeneric = new String[genericStringArrayList.size()];
        stringsArray_AllGeneric = genericStringArrayList.toArray(stringsArray_AllGeneric);

        for (int i = 0;i<stringsArray_AllGeneric.length;i++) {
            for(int x = 0;x<stringsNewsTABLE_Generic_id.length;x++) {



            }
        }

        /*
        stringsMainTABLEMed_id = myManage.readAllMainTABLE_Full(1);  //Med_id ในตาราง mainTABLE ทั้งหมด
        stringsNewsTABLE_Med_id = myManage.readAllnewsTABLE(1); //Med_id ในตาราง newsTABLE ทั้งหมด
        stringsNewsTABLE_Message = myManage.readAllnewsTABLE(2); //Message ในตาราง newsTABLE ทั้งหมด
        stringsNewsTABLE_Appearance_edit = myManage.readAllnewsTABLE(3);  //Appearance ในตาราง newsTABLE ทั้งหมด
        stringsNewsTABLE_Activity = myManage.readAllnewsTABLE(4); //Appearance ในตาราง newsTABLE ทั้งหมด


        //ถ้าไม่มีข่าวสารให้ออกเลย
        if (stringsMainTABLEMed_id[0].equals("")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(R.drawable.icon_question);
            builder.setTitle("ไม่มีข่าวสาร!!!");
            builder.setMessage("ข่าวสารจะปรากฎตามรายการยาที่กำหนด\nกรุณาเพิ่มยา โดยเข้าไปที่ +เพิ่มเติม+ ==> เพิ่มรายการยา");
            builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    finish();
                }
            });
            builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    finish();
                }
            });
            builder.show();
        }


        ArrayList<String> arrayListString = new ArrayList<String>();
        ArrayList<String> arrayListString2 = new ArrayList<String>();
        ArrayList<String> arrayListString3 = new ArrayList<String>();
        ArrayList<String> arrayListString4 = new ArrayList<String>();

        int arrayListIndex = 0;

        Log.d("stringsAdaptor", "เริ่ม arrayListIndex = 0");

        for (int i = 0;i<stringsMainTABLEMed_id.length;i++) {

            for(int x = 0;x<stringsNewsTABLE_Med_id.length;x++) {
                if (stringsMainTABLEMed_id[i].equals(stringsNewsTABLE_Med_id[x])) {
                    arrayListString.add(arrayListIndex,stringsNewsTABLE_Med_id[x]);
                    arrayListString2.add(arrayListIndex,stringsNewsTABLE_Message[x]);
                    arrayListString3.add(arrayListIndex,stringsNewsTABLE_Appearance_edit[x]);
                    arrayListString4.add(arrayListIndex,stringsNewsTABLE_Activity[x]);
                    arrayListIndex = arrayListIndex + 1;
                }
            }
        }

        stringsAdaptorid = new String[arrayListString.size()];
        stringsAdaptorid = arrayListString.toArray(stringsAdaptorid); // Array ที่เก็บค่าที่ต้องแสดง (เป็น Med_id)

        stringsAdaptorMessage = new String[arrayListString2.size()];
        stringsAdaptorMessage = arrayListString2.toArray(stringsAdaptorMessage); //Array ที่เก็บค่าที่ต้องแสดง (Message)

        stringsAdaptorAppearance_edit = new String[arrayListString3.size()];
        stringsAdaptorAppearance_edit = arrayListString3.toArray(stringsAdaptorAppearance_edit);

        stringsAdaptorActivity = new String[arrayListString4.size()];
        stringsAdaptorActivity = arrayListString4.toArray(stringsAdaptorActivity);


        for (int z = 0;z<arrayListString.size();z++) {
            Log.d("stringsAdaptor", stringsAdaptorid[z] + "ได้ Med_id ที่ต้องใช้ทำ Adaptor มาแล้ว");
        }

        for (int cc = 0;cc<arrayListString2.size();cc++) {
            Log.d("stringsAdaptor", stringsAdaptorMessage[cc] + "ได้ Message ที่ต้องใช้ทำ Adaptor มาแล้ว");
        }

        for (int dd = 0;dd<arrayListString2.size();dd++) {
            Log.d("stringsAdaptor", stringsAdaptorAppearance_edit[dd] + "ได้ Appearance_edit ที่ต้องใช้ทำ Adaptor มาแล้ว");
        }


        stringsmedTABLE_id = myManage.readAllmedTABLE(0);
        stringsAppearance = myManage.readAllmedTABLE(16);  //Appearance ทั้งหมดในตาราง medTABLE

        arrayListString.clear();
        arrayListIndex = 0;

        //หาค่า Appearance ที่ตรงกับ med_id ใน stringsAdaptor
        for (int y = 0;y<stringsAdaptorid.length;y++) {
            for(int aa = 0; aa<stringsmedTABLE_id.length;aa++) {
                if (stringsAdaptorid[y].equals(stringsmedTABLE_id[aa])) {

                    if (!stringsAdaptorAppearance_edit[y].equals("")) {
                        arrayListString.add(arrayListIndex, stringsAdaptorAppearance_edit[y]);
                    } else {
                        arrayListString.add(arrayListIndex,stringsAppearance[aa]);
                    }

                    arrayListIndex = arrayListIndex + 1;
                }
            }
        }

        stringsAdaptorAppearance = new String[arrayListString.size()];
        stringsAdaptorAppearance = arrayListString.toArray(stringsAdaptorAppearance);
        for (int bb = 0;bb<arrayListString.size();bb++) {
            Log.d("stringsAdaptor", stringsAdaptorAppearance[bb] + "ได้ Appearance ที่ต้องใช้ทำ Adaptor มาแล้ว");
        }

        int[] intsIndexAppearance = myData.translate_Appearance(stringsAdaptorAppearance);


        MyNewsAdaptor myNewsAdaptor = new MyNewsAdaptor(getBaseContext(), stringsAdaptorMessage, intsIndexAppearance);
        listViewNewsActivity = (ListView) findViewById(R.id.listViewNewsActivity);
        listViewNewsActivity.setAdapter(myNewsAdaptor);


        Log.d("stringsAdaptor", "กำลังจะทำ setOnItemClickListener");
        listViewNewsActivity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String strActivity = stringsAdaptorActivity[position];
                Toast.makeText(NewsActivity.this,strActivity,Toast.LENGTH_LONG).show();

                //น่าเกลียดมากแต่คิดวิธีแก้ไม่ได้
                if (strActivity.equals("DrugInformationActivity")) {
                    Intent intent = new Intent(getBaseContext(),DrugInformationActivity.class);
                    startActivity(intent);

                }


            }
        });

        */

    }  //Main Method


}  //Main Class
