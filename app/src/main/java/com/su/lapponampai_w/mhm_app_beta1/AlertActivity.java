package com.su.lapponampai_w.mhm_app_beta1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

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

        MyManage myManage = new MyManage(this);
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
        stringsCriteria = stringArrayListCriteria.toArray(stringsCriteria);
        stringsDetail = stringArrayListDetail.toArray(stringsDetail);
        stringsPic = stringArrayListPic.toArray(stringsPic);

        MyData myData = new MyData();
        int[] intsIndex = myData.translate_Appearance_Alert(stringsPic);
        //ต่อไปคือแสดง listView
        MyAdaptorAlert myAdaptorAlert = new MyAdaptorAlert(getBaseContext(), stringsDetail, intsIndex);
        listView.setAdapter(myAdaptorAlert);

    }


    private void bindWidget() {
        listView = (ListView) findViewById(R.id.listViewAlertActivity);



    }
}
