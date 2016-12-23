package com.su.lapponampai_w.mhm_app_beta1;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PopUpLabActivity extends AppCompatActivity {

    //Explicit
    TextView textViewCalendar;
    String strReceiveIntent,sCalendar;
    String[] stringslab;
    EditText[]  editTexts = new EditText[12];
    Button saveButton, cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_lab);

        setInitialParameter();

        bindWidget();

        setView();

        textViewCalendar.setText("");

        displayMetrics();

        clickTextViewCalendar();

        clickButtonSaveCancel();


    }

    private void setInitialParameter() {

        stringslab = new String[12];
        sCalendar = "";
        for(int i =0;i < stringslab.length;i++) {
            stringslab[i] = "";

        }







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

                Boolean aBoolean = true;
                for(int i =0;i < stringslab.length;i++) {
                    stringslab[i] = editTexts[i].getText().toString().trim();
                    if (!stringslab[i].equals("")) {
                        aBoolean = false;
                    } else {
                        //aBoolean เป็น true เหมือนเดิม !!!!
                    }
                }

                sCalendar = textViewCalendar.getText().toString();
                if (sCalendar.equals("")) {
                    Toast.makeText(getBaseContext(),"กรุณาระบุวันทีที่ต้องการบันทึก",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (aBoolean) {
                    Toast.makeText(getBaseContext(), "กรุณาระบุค่าแล็ปอย่างน้อย 1 ค่า", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Log.d("221216V1", "เข้า 1");
                    //aBoolean = false
                    final MyManage myManage = new MyManage(v.getContext());
                    MyData myData = new MyData();
                    final String sSaveDateTime = myData.currentDateTime();
                    final String[] stringsDate = myManage.readAlllabTABLE(2);
                    final String[] stringsId = myManage.readAlllabTABLE(0);
                    for(int x = 0;x<stringsDate.length;x++) {
                        if (stringsDate[x].equals(sCalendar)) {
                            final int i = x;
                            aBoolean = true;
                            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                            builder.setCancelable(false);
                            builder.setIcon(R.drawable.logo_mhm);
                            builder.setTitle("คำเตือน!!!");
                            builder.setMessage("วันที่ " + sCalendar + " เคยมีการบันทึกไว้อยู่แล้วท่านต้องการบันทึกข้อมูลทับหรือไม่");
                            builder.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            builder.setPositiveButton("ยืนยัน", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //ทำการ Overwite (Update) ข้อมูลใน labTABLE


                                    myManage.updateLabTABLE(stringsId[i], sSaveDateTime, sCalendar, stringslab[0],
                                            stringslab[1], stringslab[2], stringslab[3], stringslab[4], stringslab[5],
                                            stringslab[6],
                                            stringslab[7], stringslab[8], stringslab[9], stringslab[10], stringslab[11]);

                                    Intent intent = new Intent(PopUpLabActivity.this,LabActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                    finish();

                                }
                            });
                            builder.show();
                        }
                    }


                    Log.d("221216V1","เข้า 2");


                    if (!aBoolean) {
                        //Toast.makeText(getBaseContext(), "s;dfjsl;djf;dsj", Toast.LENGTH_LONG).show();
                        Log.d("221216V1", "False");
                        myManage.addValueToLabTABLE(sSaveDateTime, sCalendar, stringslab[0],
                                stringslab[1], stringslab[2], stringslab[3], stringslab[4], stringslab[5],
                                stringslab[6],
                                stringslab[7], stringslab[8], stringslab[9], stringslab[10], stringslab[11]);
                        Toast.makeText(PopUpLabActivity.this,"Success!!!!",Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(PopUpLabActivity.this,LabActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();

                    } else {
                        Log.d("221216V1", "True");
                    }




                } //else

            }

        });



    }

    private void setView() {




    }

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
                Intent intent =  new Intent(PopUpLabActivity.this,PopUpCalendarActivity.class);
                startActivityForResult(intent, 2);

            }
        });
    }



    private void displayMetrics() {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        getWindow().setLayout((int) (width*.85),(int) (height*.80));

    }

    private void bindWidget() {

        textViewCalendar = (TextView) findViewById(R.id.textView128);
        editTexts[0] = (EditText) findViewById(R.id.editText1);
        editTexts[1] = (EditText) findViewById(R.id.editText2);
        editTexts[2] = (EditText) findViewById(R.id.editText3);
        editTexts[3] = (EditText) findViewById(R.id.editText4);
        editTexts[4] = (EditText) findViewById(R.id.editText5);
        editTexts[5] = (EditText) findViewById(R.id.editText6);
        editTexts[6] = (EditText) findViewById(R.id.editText7);
        editTexts[7] = (EditText) findViewById(R.id.editText8);
        editTexts[8] = (EditText) findViewById(R.id.editText9);
        editTexts[9] = (EditText) findViewById(R.id.editText10);
        editTexts[10] = (EditText) findViewById(R.id.editText11);
        editTexts[11] = (EditText) findViewById(R.id.editText12);
        saveButton = (Button) findViewById(R.id.buttonLabSave);
        cancelButton = (Button) findViewById(R.id.buttonLabCancel);
    }
}
