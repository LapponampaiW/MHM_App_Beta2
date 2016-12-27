package com.su.lapponampai_w.mhm_app_beta1;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class LabActivity extends AppCompatActivity {

    //Explicit
    TextView textViewCalendar,textViewBackToMainActivity;
    String strReceiveIntent;
    ListView listView;
    Button saveButton, cancelButton;

    EditText editText1,editText2,editText3,editText4,editText5,editText6, editText7;
    String s1,s2,s3,s4,s5,s6, s7,sCalendar;
    ImageButton imageButton;
    final public String[] stringsLabHeading = {"Body weight", "FBS", "Blood pressure", "Total Chlol",
            "Triglyceride", "HDL", "LDL", "SGPT/ALT", "Creatinine", "BUN", "CD4", "Viral load"};

    final public String[] stringsUnitLab = {"Kg.", "mg/dL", "mmHg", "mg/dL", "mg/dL", "mg/dL",
            "mg/dL", "U/L", "mg/dL", "mg/dL", "cell/mm3", "copies/ml"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab);

        bindwidget();

        clickImageButton();

        listView.setVisibility(View.INVISIBLE);

        //textViewCalendar.setText("");

        showListView();

        clickBackToMain();


        //clickTextViewCalendar();

        //clickButtonSaveCancel();

        //clickDeleteListView();

    }

    private void clickBackToMain() {

        textViewBackToMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }


    private void clickImageButton() {
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getBaseContext(), PopUpLabActivity.class);
                startActivity(intent);
            }
        });
    }

    private void clickDeleteListView() {

        MyManage myManage = new MyManage(LabActivity.this);

        final String[][] stringsList = {myManage.readAlllabTABLE(0),myManage.readAlllabTABLE(1),
                myManage.readAlllabTABLE(2),myManage.readAlllabTABLE(3),myManage.readAlllabTABLE(4),
                myManage.readAlllabTABLE(5),myManage.readAlllabTABLE(6),myManage.readAlllabTABLE(7),
                myManage.readAlllabTABLE(8),myManage.readAlllabTABLE(9)};

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setCancelable(false);
                builder.setIcon(R.drawable.logo_mhm);
                builder.setTitle("ลบข้อมูลค่าแล็ป");
                builder.setMessage("ยืนยันการลบข้อมูลค่าแล็ป");
                builder.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("ยืนยัน", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        String id = stringsList[0][position];
                        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyHelper.DATABASE_NAME,
                                MODE_PRIVATE, null);
                        sqLiteDatabase.delete("LabTABLE", "_id = " + id, null);

                        Toast.makeText(LabActivity.this,"Delete in LabTABLE",Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(LabActivity.this,LabActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }
                });
                builder.show();


            }
        });

    }  //clickDeleteListView

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

                s1 = editText1.getText().toString().trim();
                s2 = editText2.getText().toString().trim();
                s3 = editText3.getText().toString().trim();
                s4 = editText4.getText().toString().trim();
                s5 = editText5.getText().toString().trim();
                s6 = editText6.getText().toString().trim();
                s7 = editText7.getText().toString().trim();
                sCalendar = textViewCalendar.getText().toString();

                if (sCalendar.equals("")) {
                    Toast.makeText(LabActivity.this,"กรุณาระบุวันทีที่ต้องการบันทึก",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (s1.equals("") && s2.equals("")
                        && s3.equals("") && s4.equals("")
                        && s5.equals("") && s6.equals("")
                        && s7.equals("")) {

                    Toast.makeText(LabActivity.this,"กรุณาระบุค่าแล็ปอย่างน้อย 1 ค่า",Toast.LENGTH_SHORT).show();
                    return;

                }

                final MyManage myManage = new MyManage(LabActivity.this);
                MyData myData = new MyData();
                final String sSaveDateTime = myData.currentDay();


                final String[][] stringsList = {myManage.readAlllabTABLE(0),myManage.readAlllabTABLE(1),
                        myManage.readAlllabTABLE(2),myManage.readAlllabTABLE(3),myManage.readAlllabTABLE(4),
                        myManage.readAlllabTABLE(5),myManage.readAlllabTABLE(6),myManage.readAlllabTABLE(7),
                        myManage.readAlllabTABLE(8),myManage.readAlllabTABLE(9)};

                String sDuplicate = "N";

                for(int i = 0;i<stringsList[0].length;i++) {
                    if (stringsList[2][i].equals(sCalendar)) {
                        final int ii = i;
                        sDuplicate = "Y";
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

                                //myManage.updateLabTABLE(stringsList[0][ii],sSaveDateTime,sCalendar
                                //        ,s1,s2,s3,s4,s5,s6,s7);

                                Intent intent = new Intent(LabActivity.this,LabActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();

                            }
                        });
                        builder.show();


                    }
                }


                if (sDuplicate.equals("N")) {
                    //myManage.addValueToLabTABLE(sSaveDateTime, sCalendar, s1, s2, s3, s4, s5, s6, s7);
                    Toast.makeText(LabActivity.this,"Success!!!!",Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(LabActivity.this,LabActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();

                }


            }
        });

    } //clickButtonSaveCancel

    private void showListView() {

        MyManage myManage = new MyManage(this);
        MyData myData = new MyData();

        final String[][] stringsLab = {myManage.readAlllabTABLE(3), myManage.readAlllabTABLE(4),
                myManage.readAlllabTABLE(5), myManage.readAlllabTABLE(6), myManage.readAlllabTABLE(7),
                myManage.readAlllabTABLE(8), myManage.readAlllabTABLE(9), myManage.readAlllabTABLE(10)
                , myManage.readAlllabTABLE(11), myManage.readAlllabTABLE(12), myManage.readAlllabTABLE(13)
                , myManage.readAlllabTABLE(14)};
        final String[] stringsDateLab = myManage.readAlllabTABLE(2);
        final String[] stringsId = myManage.readAlllabTABLE(0);

        if (!stringsDateLab[0].equals("")) {
            Log.d("241259V1", "เข้า if");

            for(int i = 0;i<stringsDateLab.length;i++) {
                stringsDateLab[i] = "วันที่ตรวจแล๊ป : ".concat(stringsDateLab[i]);
            }




            //เรียง ตามวันที่ ให้วันที่มากๆ มาก่อน (2)

            String[] stringsExplainLab = new String[stringsLab[0].length];
            for(int i = 0;i < stringsExplainLab.length;i++) {
                stringsExplainLab[i] = "";
            }
            //ArrayList<String> stringArrayList = new ArrayList<String>();
            //int iIndex = 0;
            for(int r = 0;r < stringsLab[r].length;r++) { //loop เท่าจำนวนแถว
                for(int c = 0;c <stringsLab.length;c++) { //loop ตามจำนวน Column
                    if (!stringsLab[c][r].equals("")) {
                        Log.d("231256V1", stringsLab[c][r]);


                        if (stringsExplainLab[r].equals("")) {
                            stringsExplainLab[r] = "".concat(stringsLabHeading[c]);//stringsLabHeading[c];
                        } else {
                            stringsExplainLab[r] = stringsExplainLab[r].concat(", ");//stringsExplainLab[c].concat(", ");
                            stringsExplainLab[r] = stringsExplainLab[r].concat(stringsLabHeading[c]);//stringsExplainLab[c].concat(stringsLabHeading[c]);
                        }

                    }

                }

            }

            //String[] strings = {"0", "1"};
            MyAdaptorLab myAdaptorLab = new MyAdaptorLab(getBaseContext(), stringsDateLab, stringsExplainLab);
            listView.setAdapter(myAdaptorLab);
            listView.setVisibility(View.VISIBLE);



            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    String s = stringsDateLab[position] + "\n\nค่าแล็ป :";
                    for (int i = 0; i < stringsLab.length; i++) {
                        if (!stringsLab[i][position].equals("")) {
                            s = s + "\n     " + stringsLabHeading[i]
                                    + " " + stringsLab[i][position] + " " + stringsUnitLab[i];
                        }
                    } //for
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setIcon(R.drawable.logo_mhm);
                    builder.setTitle("ค่าแล็ป");
                    builder.setMessage(s);
                    builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
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

            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setCancelable(false);
                    builder.setIcon(R.drawable.logo_mhm);
                    builder.setTitle("ลบข้อมูลค่าแล็ป");
                    builder.setMessage("ยืนยันการลบข้อมูลค่าแล็ป");
                    builder.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.setPositiveButton("ยืนยัน", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();

                            //231259
                            String id = stringsId[position];
                            SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyHelper.DATABASE_NAME,
                                    MODE_PRIVATE, null);
                            sqLiteDatabase.delete("LabTABLE", "_id = " + id, null);


                            Intent intent = new Intent(LabActivity.this, LabActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                        }
                    });
                    builder.show();
                    return true;
                }
            });
        } //first if




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

        /*
        textViewCalendar = (TextView) findViewById(R.id.textView128);
        listView = (ListView) findViewById(R.id.listViewLab);
        saveButton = (Button) findViewById(R.id.buttonLabSave);
        cancelButton = (Button) findViewById(R.id.buttonLabCancel);
        editText1 = (EditText) findViewById(R.id.editText6);
        editText2 = (EditText) findViewById(R.id.editText7);
        editText3 = (EditText) findViewById(R.id.editText8);
        editText4 = (EditText) findViewById(R.id.editText9);
        editText5 = (EditText) findViewById(R.id.editText10);
        editText6 = (EditText) findViewById(R.id.editText11);
        editText7 = (EditText) findViewById(R.id.editText12);
        */
        imageButton = (ImageButton) findViewById(R.id.imageButton11);
        listView = (ListView) findViewById(R.id.listViewLab);
        textViewBackToMainActivity = (TextView) findViewById(R.id.textView106);


    } //bindwidget
}
