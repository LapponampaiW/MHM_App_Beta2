package com.su.lapponampai_w.mhm_app_beta1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
        MyManage myManage = new MyManage(this);
        strings_id = myManage.readAllMainTABLE(0);
        strings_tradname = myManage.readAllMainTABLE(3);
        strings_med_id = myManage.readAllMainTABLE(2);
        strings_generic_line = myManage.readAllMainTABLE(4);
        strings_appearance = myManage.readAllMainTABLE(5);

        MyData myData = new MyData();
        int[] intsIndex = myData.translate_Appearance(strings_appearance);

        //MyAdaptor myAdaptor = new MyAdaptor(AddMedicineActivity.this, stringsTradename, stringsGenericLine1, intsIndex);
        //listViewAddTG.setAdapter(myAdaptor);

        MyAdaptor myAdaptor = new MyAdaptor(MedicationListActivity.this,strings_tradname,strings_generic_line,intsIndex);
        listView.setAdapter(myAdaptor);


    }

    private void bindWidget() {
        listView = (ListView) findViewById(R.id.listView_MedicationList);
        imageButtonCalendar = (ImageButton) findViewById(R.id.imageButtonCalendar2);
    }


}  // Main Class
