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

                        intent.putExtra("MedicationListActivity_id",strREAD[0][i]);
                        intent.putExtra("MedicationListActivity_med_id",strREAD[1][i]);
                        intent.putExtra("MedicationListActivity_trade_name",strREAD[2][i]);
                        intent.putExtra("MedicationListActivity_generic_line",strREAD[3][i]);
                        intent.putExtra("MedicationListActivity_amount_tablet",strREAD[4][i]);
                        intent.putExtra("MedicationListActivity_which_date_d",strREAD[5][i]);
                        intent.putExtra("MedicationListActivity_appearance",strREAD[6][i]);
                        intent.putExtra("MedicationListActivity_ea",strREAD[7][i]);
                        intent.putExtra("MedicationListActivity_pharmaco",strREAD[8][i]);
                        intent.putExtra("MedicationListActivity_startdate",strREAD[9][i]);
                        intent.putExtra("MedicationListActivity_finishdate",strREAD[10][i]);
                        intent.putExtra("MedicationListActivity_prn",strREAD[11][i]);
                        intent.putExtra("MedicationListActivity_t1",strREAD[12][i]);
                        intent.putExtra("MedicationListActivity_t2",strREAD[13][i]);
                        intent.putExtra("MedicationListActivity_t3",strREAD[14][i]);
                        intent.putExtra("MedicationListActivity_t4",strREAD[15][i]);
                        intent.putExtra("MedicationListActivity_t5",strREAD[16][i]);
                        intent.putExtra("MedicationListActivity_t6",strREAD[17][i]);
                        intent.putExtra("MedicationListActivity_t7",strREAD[18][i]);
                        intent.putExtra("MedicationListActivity_t8",strREAD[19][i]);
                        intent.putExtra("MedicationListActivity_datetimecanceled",strREAD[20][i]);

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
