package com.su.lapponampai_w.mhm_app_beta1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

public class MedicationListActivity extends AppCompatActivity {

    private ListView listView;
    private String[] strings_id, strings_tradname,strings_med_id, strings_generic_line, strings_appearance;
    ImageButton imageButtonCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication_list);



        bindWidget();

        recieveValue_mainTABLE();

        click_ImageButtonCalendar();

    } // Main Method

    private void click_ImageButtonCalendar() {
        imageButtonCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MedicationListActivity.this,Calendartest.class));
            }
        });
    }

    private void recieveValue_mainTABLE() {
        final MyManage myManage = new MyManage(this);
        strings_id = myManage.readAllMainTABLE(0);
        strings_tradname = myManage.readAllMainTABLE(3);
        strings_med_id = myManage.readAllMainTABLE(2);
        strings_generic_line = myManage.readAllMainTABLE(4);
        strings_appearance = myManage.readAllMainTABLE(5);

        MyData myData = new MyData();
        final int[] intsIndex = myData.translate_Appearance(strings_appearance);

        //MyAdaptor myAdaptor = new MyAdaptor(AddMedicineActivity.this, stringsTradename, stringsGenericLine1, intsIndex);
        //listViewAddTG.setAdapter(myAdaptor);

        MyAdaptor myAdaptor = new MyAdaptor(MedicationListActivity.this,strings_tradname,strings_generic_line,intsIndex);
        listView.setAdapter(myAdaptor);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String strIndex = strings_id[position];
                String[][] strREAD = {myManage.readAllMainTABLE_Full(0),
                        myManage.readAllMainTABLE_Full(1), myManage.readAllMainTABLE_Full(2),
                        myManage.readAllMainTABLE_Full(3), myManage.readAllMainTABLE_Full(4),
                        myManage.readAllMainTABLE_Full(5), myManage.readAllMainTABLE_Full(6),
                        myManage.readAllMainTABLE_Full(7), myManage.readAllMainTABLE_Full(8),
                        myManage.readAllMainTABLE_Full(9), myManage.readAllMainTABLE_Full(10),
                        myManage.readAllMainTABLE_Full(11), myManage.readAllMainTABLE_Full(12),
                        myManage.readAllMainTABLE_Full(13), myManage.readAllMainTABLE_Full(14),
                        myManage.readAllMainTABLE_Full(15), myManage.readAllMainTABLE_Full(16),
                        myManage.readAllMainTABLE_Full(17), myManage.readAllMainTABLE_Full(18),
                        myManage.readAllMainTABLE_Full(19), myManage.readAllMainTABLE_Full(20)};

                for (int i = 0;i<strREAD[0].length;i++) {
                    if (strIndex.equals(strREAD[0][i])) {
                        Log.d("MedicationListActivity", strREAD[0][i]);

                        Intent intent = new Intent(MedicationListActivity.this, MedicationDetailActivity.class);

                        intent.putExtra(strREAD[0][i], "MedicationListActivity_id");
                        intent.putExtra(strREAD[1][i], "MedicationListActivity_med_id");
                        intent.putExtra(strREAD[2][i], "MedicationListActivity_trade_name");
                        intent.putExtra(strREAD[3][i], "MedicationListActivity_generic_line");
                        intent.putExtra(strREAD[4][i], "MedicationListActivity_amount_tablet");
                        intent.putExtra(strREAD[5][i], "MedicationListActivity_which_date_d");
                        intent.putExtra(strREAD[6][i], "MedicationListActivity_appearance");
                        intent.putExtra(strREAD[7][i], "MedicationListActivity_ea");
                        intent.putExtra(strREAD[8][i], "MedicationListActivity_pharmaco");
                        intent.putExtra(strREAD[9][i], "MedicationListActivity_startdate");
                        intent.putExtra(strREAD[10][i], "MedicationListActivity_finishdate");
                        intent.putExtra(strREAD[11][i], "MedicationListActivity_prn");
                        intent.putExtra(strREAD[12][i], "MedicationListActivity_t1");
                        intent.putExtra(strREAD[13][i], "MedicationListActivity_t2");
                        intent.putExtra(strREAD[14][i], "MedicationListActivity_t3");
                        intent.putExtra(strREAD[15][i], "MedicationListActivity_t4");
                        intent.putExtra(strREAD[16][i], "MedicationListActivity_t5");
                        intent.putExtra(strREAD[17][i], "MedicationListActivity_t6");
                        intent.putExtra(strREAD[18][i], "MedicationListActivity_t7");
                        intent.putExtra(strREAD[19][i], "MedicationListActivity_t8");
                        intent.putExtra(strREAD[20][i], "MedicationListActivity_datetimecanceled");

                        startActivity(intent);

                    }
                }

            }
        });


    }

    private void bindWidget() {
        listView = (ListView) findViewById(R.id.listView_MedicationList);
        imageButtonCalendar = (ImageButton) findViewById(R.id.imageButtonCalendar2);
    }


}  // Main Class
