package com.su.lapponampai_w.mhm_app_beta1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LabActivity extends AppCompatActivity {

    //Explicit
    TextView textViewCalendar;
    String strReceiveIntent;
    ListView listView;
    Button saveButton, cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab);

        bindwidget();

        textViewCalendar.setText("");

        showListView();

        clickTextViewCalendar();

        clickButtonSaveCancel();



    }

    private void clickButtonSaveCancel() {

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    } //clickButtonSaveCancel

    private void showListView() {

        MyManage myManage = new MyManage(this);
        MyData myData = new MyData();

        String[][] stringsList = {myManage.readAlllabTABLE(0),myManage.readAlllabTABLE(1),
                myManage.readAlllabTABLE(2),myManage.readAlllabTABLE(3),myManage.readAlllabTABLE(4),
                myManage.readAlllabTABLE(5),myManage.readAlllabTABLE(6),myManage.readAlllabTABLE(7),
                myManage.readAlllabTABLE(8),myManage.readAlllabTABLE(9)};


        if (!stringsList[0][0].equals("")) {

            MyAdaptorLab myAdaptorLab = new MyAdaptorLab(LabActivity.this, stringsList[3],
                    stringsList[4], stringsList[5], stringsList[6], stringsList[7], stringsList[8],
                    stringsList[9], stringsList[2]);
            listView.setAdapter(myAdaptorLab);




        }


    } //showListView


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 2) {
            Log.d("14July16V1", "in onActivityResult");
            if (resultCode == RESULT_OK) {

                strReceiveIntent = data.getStringExtra("PopupCalendarActivity");
                Log.d("14July16V1", "strReceiveIntent : " + strReceiveIntent);
                MyData myData = new MyData();

                String strCurrentDate = myData.currentDay();

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date date_Specific = new Date();
                Date date_current = new Date();

                try {
                    date_Specific = dateFormat.parse(strReceiveIntent);
                    date_current = dateFormat.parse(strCurrentDate);

                } catch (ParseException e) {
                    e.printStackTrace();
                }


                if (date_Specific.compareTo(date_current) > 0) {

                    Toast.makeText(getBaseContext(), "ไม่สามารถตั้งบันทึกค่าแล็ปมากกว่าวันที่ปัจจุบันได้", Toast.LENGTH_SHORT).show();
                    strReceiveIntent = "";
                }

                textViewCalendar.setText(strReceiveIntent);
            }
        }
    }

    private void clickTextViewCalendar() {

        textViewCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(LabActivity.this,PopUpCalendarActivity.class);
                startActivityForResult(intent, 2);

            }
        });
    }

    private void bindwidget() {

        textViewCalendar = (TextView) findViewById(R.id.textView128);
        listView = (ListView) findViewById(R.id.listViewLab);
        saveButton = (Button) findViewById(R.id.buttonLabSave);
        cancelButton = (Button) findViewById(R.id.buttonLabCancel);

    } //bindwidget
}
