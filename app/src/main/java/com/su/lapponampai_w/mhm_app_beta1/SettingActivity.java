package com.su.lapponampai_w.mhm_app_beta1;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class SettingActivity extends AppCompatActivity implements
        TimePickerDialog.OnTimeSetListener{

    //Explicit
    public static Activity settingActivity;


    Button buttonConnect,buttonSuperUser,buttonNofSave;
    String strAddVN,stringEditText,s1,s2;
    TextView textViewid,textViewAbout,textViewChangePW,textViewSecurity,textViewFinish,
            textViewNotif_Explain,textViewAppointmentDay,textViewAppointmentTime;
    MyManage myManage;
    Switch aSwitch,aSwitch2;
    LinearLayout linearLayout,linearLayoutTimesNof,linearLayoutApp2,linearLayoutApp3;
    CheckBox checkBoxDefault, checkBoxCustom,checkBoxSecurityNone,
            checkBoxSecurity1,checkBoxSecurity2,checkBoxNof1,checkBoxNof2;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        myManage = new MyManage(this);
        settingActivity = this;


        bindWidget();
        buttonConnect.setVisibility(View.GONE);
        buttonSuperUser.setVisibility(View.GONE);
        linearLayout.setVisibility(View.GONE);
        linearLayoutTimesNof.setVisibility(View.GONE);

        setView();
        //set คำอธิบายใน Security

        clickSwithAllowedNotification();

        //คลิก CheckBox ที่อยู่กับ Notification
        clickCheckBox();

        //คลิก Checkbox ของการเปลี่ยนระดับของ Security
        clickCheckBoxSecurity();

        //คลิก CheckBok Times_nof
        clickCheckBoxNof();

        //คลิก AboutActivity
        clickAboutActivity();

        //คลิกเปลี่ยน Password
        clickPWActivity();

        //คลิก Save Nof
        //clickSaveNof();

        saveEditText();

        //คลิก Finish
        clickFinish();

        clickActiveTextView();

        clickConnect();

        clickSuperUser();

    }

    private void clickActiveTextView() {

        textViewAppointmentDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(v.getContext());
                final String[] strings = {"0 (ศูนย์)","1 (หนึ่ง)","2 (สอง)","3 (สาม)",
                        "4 (สี่)","5 (ห้า)","6 (หก)","7 (เจ็ด)","8 (แปด)",
                        "9 (เก้า)","10 (สิบ)","11 (สิบเอ็ด)","12 (สิบสอง)","13 (สิบสาม)",
                        "14 (สิบสี่)"};
                builder.setTitle("โปรดระบุ!!! \nจำนวนวันที่ต้องการเตือนก่อนถึงนัด");
                builder.setSingleChoiceItems(strings, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(getBaseContext(),Integer.toString(which),Toast.LENGTH_LONG).show();
                        textViewAppointmentDay.setText(strings[which]);
                        s1 = Integer.toString(which); //ตัวเลข จาก integer
                        String[] strAppointmentNof = myManage.filter_userTABLE(10); //หา AppointmentNof
                        //String[] queryDateTimeAppointmentRef = strAppointmentNof[0].split(";");
                        //s2 = queryDateTimeAppointmentRef[1];
                        String[] strUser = myManage.filter_userTABLE(1); //ค่า username
                        String s = s1 + ";" + s2;
                        myManage.update_Appointment_notif(strUser[0],s);
                        dialog.dismiss();
                    }
                });
                builder.show();
            }

        }); //textViewAppointmentDay

        textViewAppointmentTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(v);
            }
        });




    } //clickActiveTextView


    private void saveEditText() {

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                stringEditText = s.toString();
                if (stringEditText.equals("") || stringEditText.length() < 1) {
                    String[] strUser = myManage.filter_userTABLE(1); //ค่า username
                    myManage.update_notification(strUser[0], "Default", "1");
                } else {
                    String[] strUser = myManage.filter_userTABLE(1); //ค่า username
                    myManage.update_notification(strUser[0],stringEditText,"1");
                }


            }

            @Override
            public void afterTextChanged(Editable s) {
                SplashScreen splashScreen = new SplashScreen();
                splashScreen.updateDailyBroadcast(getBaseContext());
            }
        });

    }

    private void clickCheckBoxNof() {
        checkBoxNof1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    checkBoxNof1.setChecked(true);
                    checkBoxNof2.setChecked(false);
                String[] strUser = myManage.filter_userTABLE(1); //ค่า id
                myManage.update_notification(strUser[0],"1","2");
            }
        });


        checkBoxNof2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxNof1.setChecked(false);
                checkBoxNof2.setChecked(true);
                String[] strUser = myManage.filter_userTABLE(1); //ค่า id
                myManage.update_notification(strUser[0],"2","2");
            }
        });

    }

    private void clickFinish() {

        textViewFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void clickSaveNof() {

        buttonNofSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = editText.getText().toString().trim();

                if (s.equals("") && checkBoxCustom.isChecked()) {
                    Toast.makeText(getBaseContext(), "กรุณาระบุข้อความที่ต้องการก่อนบันทึก", Toast.LENGTH_SHORT).show();
                } else if(!s.equals("") && checkBoxCustom.isChecked()) {

                    String[] strUser = myManage.filter_userTABLE(1); //ค่า username
                    myManage.update_notification(strUser[0],s,"1");
                    Toast.makeText(getBaseContext(), "เปลี่ยนแปลงข้อความเรียบร้อย", Toast.LENGTH_SHORT).show();
                } else if (checkBoxDefault.isChecked()) {
                    String[] strUser = myManage.filter_userTABLE(1); //ค่า username
                    myManage.update_notification(strUser[0],"Default","1");
                    Toast.makeText(getBaseContext(), "เปลี่ยนแปลงข้อความเรียบร้อย", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void setViewTextViewSecurity1() {
        String sText;
        if (checkBoxSecurityNone.isChecked()) {
            sText = "คำอธิบาย\nไม่มีการทวนถาม 'รหัสผ่าน' ในทุกกระบวนการภายในแอพพลิเคชั่น";
            textViewSecurity.setText(sText);
        }

        if (checkBoxSecurity1.isChecked()) {
            sText = "คำอธิบาย\nมีการทวนถาม 'รหัสผ่าน' ในทุกกระบวนการภายในแอพพลิเคชั่น ยกเว้นหน้าแรกของการ Log-in";
            textViewSecurity.setText(sText);
        }

        if (checkBoxSecurity2.isChecked()) {
            sText = "คำอธิบาย\nมีการทวนถาม 'รหัสผ่าน' ในทุกกระบวนการภายในแอพพลิเคชั่น";
            textViewSecurity.setText(sText);
        }





    }

    private void clickCheckBoxSecurity() {

        final String[] strUser = myManage.filter_userTABLE(1); //ค่า id

        checkBoxSecurityNone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxSecurityNone.isChecked()) {
                    checkBoxSecurity1.setChecked(false);
                    checkBoxSecurity2.setChecked(false);
                    myManage.updateStayLogin(strUser[0],"0");
                    setViewTextViewSecurity("0");
                    Toast.makeText(getBaseContext(),"เปลี่ยนระดับการป้องกันเรียบร้อย",Toast.LENGTH_SHORT).show();
                } else {
                    checkBoxSecurityNone.setChecked(true);
                }
            }
        });


        checkBoxSecurity1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxSecurity1.isChecked()) {
                    checkBoxSecurityNone.setChecked(false);
                    checkBoxSecurity2.setChecked(false);
                    myManage.updateStayLogin(strUser[0],"1");
                    setViewTextViewSecurity("1");
                    Toast.makeText(getBaseContext(),"เปลี่ยนระดับการป้องกันเรียบร้อย",Toast.LENGTH_SHORT).show();
                } else {
                    checkBoxSecurity1.setChecked(true);
                }
            }
        });



        checkBoxSecurity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxSecurity2.isChecked()) {
                    checkBoxSecurityNone.setChecked(false);
                    checkBoxSecurity1.setChecked(false);
                    myManage.updateStayLogin(strUser[0],"2");
                    setViewTextViewSecurity("2");
                    Toast.makeText(getBaseContext(),"เปลี่ยนระดับการป้องกันเรียบร้อย",Toast.LENGTH_SHORT).show();
                } else {
                    checkBoxSecurity2.setChecked(true);
                }
            }
        });






    }

    private void clickPWActivity() {

        textViewChangePW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, ChangePWActivity.class);
                startActivity(intent);
            }
        });

    }

    private void clickAboutActivity() {

        textViewAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SettingActivity.this, AboutMHMActivity.class);
                startActivity(intent);

            }
        });

    }

    private void clickCheckBox() {

        checkBoxDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkBoxDefault.isChecked()) {
                    checkBoxCustom.setChecked(false);
                    String[] strUser = myManage.filter_userTABLE(1); //ค่า username
                    myManage.update_notification(strUser[0],"Default","1");
                    //editText.setText("");
                    editText.setVisibility(View.INVISIBLE);
                } else {
                    checkBoxDefault.setChecked(true);
                }

            }
        });

        checkBoxCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxCustom.isChecked()) {
                    checkBoxDefault.setChecked(false);
                    editText.setVisibility(View.VISIBLE);
                    if (!stringEditText.equals("")) {
                        String[] strUser = myManage.filter_userTABLE(1); //ค่า username
                        myManage.update_notification(strUser[0], stringEditText, "1");
                    } else {
                        String[] strUser = myManage.filter_userTABLE(1); //ค่า username
                        myManage.update_notification(strUser[0], "Default", "1");
                    }
                } else {
                    checkBoxCustom.setChecked(true);
                }
            }
        });

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxCustom.setChecked(true);
                checkBoxDefault.setChecked(false);
            }
        });

    }

    private void clickSwithAllowedNotification() {
        final String[] strUser = myManage.filter_userTABLE(1); //ค่า id
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    myManage.update_Allowed_notification(strUser[0], "Y");
                    linearLayout.setVisibility(View.VISIBLE);
                    linearLayoutTimesNof.setVisibility(View.VISIBLE);
                } else {
                    myManage.update_Allowed_notification(strUser[0],"N");
                    linearLayout.setVisibility(View.GONE);
                    linearLayoutTimesNof.setVisibility(View.GONE);
                }

                SplashScreen splashScreen = new SplashScreen();
                splashScreen.updateDailyBroadcast(getBaseContext());

            }
        });

        aSwitch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    linearLayoutApp2.setVisibility(View.VISIBLE);
                    linearLayoutApp3.setVisibility(View.VISIBLE);
                    String s = s1 + ";" + s2;
                    myManage.update_Appointment_notif(strUser[0],s);
                } else {
                    myManage.update_Appointment_notif(strUser[0],"N");
                    linearLayoutApp2.setVisibility(View.GONE);
                    linearLayoutApp3.setVisibility(View.GONE);
                }
                SplashScreen splashScreen = new SplashScreen();
                splashScreen.updateDailyBroadcast(getBaseContext());
            }
        });

    }

    private void setView() {

        textViewNotif_Explain.setText("ปิด/เปิด การเตือน\nเมื่อถึงเวลารับประทานยา");


        String[] strUser = myManage.filter_userTABLE(1); //ค่า id
        String[] strStay = myManage.filter_userTABLE(3); //ค่า Stay (0,1,2)
        String[] strAllowNotif = myManage.filter_userTABLE(7); //หา Allowed_notification
        String[] strNotif = myManage.filter_userTABLE(6); // ดูข้อความ Notification
        String[] strTimeNof = myManage.filter_userTABLE(9); //หา TimeNof ว่าเป็น 1 หรือ 2
        String[] strAppointmentNof = myManage.filter_userTABLE(10); //หา AppointmentNof
        Log.d("061259V1", strTimeNof[0]);
        textViewid.setText("ไอดีผู้ใช้ : ".concat(strUser[0]));

        if (strAllowNotif[0].equals("Y")) {
            aSwitch.setChecked(true);
            linearLayout.setVisibility(View.VISIBLE);
            linearLayoutTimesNof.setVisibility(View.VISIBLE);
        } else {
            aSwitch.setChecked(false);
        }

        if (strNotif[0].equals("Default")) {
            checkBoxDefault.setChecked(true);
            checkBoxCustom.setChecked(false);
            editText.setText("");
            editText.setVisibility(View.INVISIBLE);
            stringEditText = "";
        } else {
            checkBoxDefault.setChecked(false);
            checkBoxCustom.setChecked(true);
            editText.setVisibility(View.INVISIBLE);
            editText.setVisibility(View.VISIBLE);
            editText.setText(strNotif[0]);
            stringEditText = editText.getText().toString();
        }

        if (strTimeNof[0].equals("1")) {
            checkBoxNof1.setChecked(true);
            checkBoxNof2.setChecked(false);
        } else if (strTimeNof[0].equals("2")) {
            checkBoxNof2.setChecked(true);
            checkBoxNof1.setChecked(false);
        }

        setViewTextViewSecurity(strStay[0]);

        //Set วันของ Appointment
        if (strAppointmentNof[0].equals("N")) {
            aSwitch2.setChecked(false);
            linearLayoutApp2.setVisibility(View.GONE);
            linearLayoutApp3.setVisibility(View.GONE);
            textViewAppointmentDay.setText("3 (สาม)");
            textViewAppointmentTime.setText("09:00");
            s1 = "3";
            s2 = "09:00";
        } else {
            aSwitch2.setChecked(true);
            String[] queryDateTimeAppointmentRef = strAppointmentNof[0].split(";");
            textViewAppointmentDay.setText(queryDateTimeAppointmentRef[0]);
            textViewAppointmentTime.setText(queryDateTimeAppointmentRef[1]);
            s1 = queryDateTimeAppointmentRef[0];
            s2 = queryDateTimeAppointmentRef[1];
        }




    }

    private void setViewTextViewSecurity(String strStay) {



        if (strStay.equals("0")) {
            checkBoxSecurityNone.setChecked(true);
            checkBoxSecurity1.setChecked(false);
            checkBoxSecurity2.setChecked(false);
            textViewSecurity.setText("คำอธิบาย :\nไม่มีการทวนถาม 'รหัสผ่าน' ในทุกกระบวนการภายในแอพพลิเคชั่น");
        } else if (strStay.equals("1")) {
            checkBoxSecurityNone.setChecked(false);
            checkBoxSecurity1.setChecked(true);
            checkBoxSecurity2.setChecked(false);
            textViewSecurity.setText("คำอธิบาย :\nมีการทวนถาม 'รหัสผ่าน' ในทุกกระบวนการภายในแอพพลิเคชั่น ยกเว้นหน้าแรกของการ Log-in");

        } else {
            checkBoxSecurityNone.setChecked(false);
            checkBoxSecurity1.setChecked(false);
            checkBoxSecurity2.setChecked(true);
            textViewSecurity.setText("คำอธิบาย :\nมีการทวนถาม 'รหัสผ่าน' ในทุกกระบวนการภายในแอพพลิเคชั่น");
        }




    }

    private void clickSuperUser() {
        buttonSuperUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(SettingActivity.this,TransferDataActivity.class));
            }
        });

    }

    private void bindWidget() {

        buttonConnect = (Button) findViewById(R.id.btnForTransferData);
        buttonSuperUser = (Button) findViewById(R.id.btnForSuperUser);
        textViewid = (TextView) findViewById(R.id.textView173);
        aSwitch = (Switch) findViewById(R.id.switch1);
        linearLayout = (LinearLayout) findViewById(R.id.headSettingLayout2);
        linearLayoutTimesNof = (LinearLayout) findViewById(R.id.linNotification);
        checkBoxDefault = (CheckBox) findViewById(R.id.checkBoxSetting1);
        checkBoxCustom = (CheckBox) findViewById(R.id.checkBoxSetting2);
        editText = (EditText) findViewById(R.id.editText14);
        textViewAbout = (TextView) findViewById(R.id.textView158);
        textViewChangePW = (TextView) findViewById(R.id.textView157);
        checkBoxSecurityNone = (CheckBox) findViewById(R.id.checkBoxSetting3);
        checkBoxSecurity1 = (CheckBox) findViewById(R.id.checkBoxSetting4);
        checkBoxSecurity2 = (CheckBox) findViewById(R.id.checkBoxSetting5);
        checkBoxNof1 = (CheckBox) findViewById(R.id.checkBoxNof1);
        checkBoxNof2 = (CheckBox) findViewById(R.id.checkBoxNof2);
        textViewSecurity = (TextView) findViewById(R.id.textView187);
        //buttonNofSave = (Button) findViewById(R.id.buttonSettingSave);
        textViewFinish = (TextView) findViewById(R.id.textView199);
        textViewNotif_Explain = (TextView) findViewById(R.id.textView154);
        textViewAppointmentDay = (TextView) findViewById(R.id.textView224);
        textViewAppointmentTime = (TextView) findViewById(R.id.textView228);
        linearLayoutApp2 = (LinearLayout) findViewById(R.id.linApp2);
        linearLayoutApp3 = (LinearLayout) findViewById(R.id.linApp3);
        aSwitch2 = (Switch) findViewById(R.id.switch2);

    }

    public void clickConnect() {
        buttonConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText editText = new EditText(SettingActivity.this);
                editText.setInputType(16);

                AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
                builder.setIcon(R.drawable.icon_question);
                builder.setTitle("ยืนยันการส่งข้อมูล!!!");
                builder.setMessage("กรุณาใส่รหัส และคลิกยืนยันเพื่อส่งข้อมูล");
                builder.setView(editText);
                builder.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("ยืนยัน", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String strEditText = editText.getText().toString().trim();
                        MyManage myManage = new MyManage(SettingActivity.this);
                        String[] strPassword = myManage.readAlluserTABLE(2);
                        if (strPassword[0].equals(strEditText)) {

                            Toast.makeText(SettingActivity.this,"Updated Data to Server",Toast.LENGTH_SHORT).show();
                            dialog.dismiss();

                            SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyHelper.DATABASE_NAME,
                                    MODE_PRIVATE, null);
                            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM userTABLE", null);
                            cursor.moveToFirst();
                            String strHN = cursor.getString(cursor.getColumnIndex(MyManage.ucolumn_hn));

                            Log.d("26July16", "strEmail :"+ strHN);

                            //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMddHHmmss");
                            //String time = simpleDateFormat.format(System.currentTimeMillis());
                            Random random = new Random();
                            int myRandom = random.nextInt(9999);
                            strAddVN = Integer.toString(myRandom);

                            //เริ่มทำการ Update Data to Server
                            updateDatamainTABLE_PHPMyAdmin(strHN,strAddVN);

                            updateDatasumTABLE_PHPMyAdmin(strAddVN);

                            updateDatatotalAmountTABLE_PHPMyAdmin(strAddVN);


                            AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
                            builder.setIcon(R.drawable.icon_question);
                            builder.setTitle("Index Number");
                            builder.setMessage(strAddVN);
                            builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            builder.show();



                        } else {
                            Toast.makeText(getBaseContext(),"รหัสผิดพลาด",Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }



                    }
                });
                builder.show();

            }
        });

    }

    private void updateDatatotalAmountTABLE_PHPMyAdmin(String strAddVN) {
        String strURL = "http://www.swiftcodingthai.com/mhm/add_totalAmountTABLE.php";

        MyManage myManage = new MyManage(this);

        String[][] strings_totalAmountTABLE = {myManage.readAlltotalAmountTABLE(0),
                myManage.readAlltotalAmountTABLE(1), myManage.readAlltotalAmountTABLE(2),
                myManage.readAlltotalAmountTABLE(3)};

        if (!strings_totalAmountTABLE[0][0].equals("")) {
            for(int i = 0;i<strings_totalAmountTABLE[0].length;i++) {
                OkHttpClient okHttpClient = new OkHttpClient();
                RequestBody requestBody = new FormEncodingBuilder()
                        .add("isAdd", "true")
                        .add("VN", strAddVN)
                        .add(MyManage.tcolumn_Main_id, strings_totalAmountTABLE[1][i])
                        .add(MyManage.tcolumn_TotalAmount, strings_totalAmountTABLE[2][i])
                        .add(MyManage.tcolumn_DateUpdated, strings_totalAmountTABLE[3][i])
                        .build();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(strURL).post(requestBody).build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {
                        Log.d("26July16V3", "Failure in totalAmountTABLE");
                    }

                    @Override
                    public void onResponse(Response response) throws IOException {
                        Log.d("26July16V3", "success in totalAmountTABLE");
                        response.body().close();
                    }
                });

            }

        }


    }

    private void updateDatasumTABLE_PHPMyAdmin(String strAddVN) {

        String strURL = "http://www.swiftcodingthai.com/mhm/add_sumTABLE.php";

        MyManage myManage = new MyManage(this);

        String[][] strings_sumTABLE = {myManage.readAllsumTABLE_Full(0),
                myManage.readAllsumTABLE_Full(1),myManage.readAllsumTABLE_Full(2),
                myManage.readAllsumTABLE_Full(3),myManage.readAllsumTABLE_Full(4),
                myManage.readAllsumTABLE_Full(5),myManage.readAllsumTABLE_Full(6)};

        Log.d("28July16", strings_sumTABLE[1][0]);
        Log.d("28July16", strings_sumTABLE[2][0]);
        Log.d("28July16", strings_sumTABLE[3][0]);
        Log.d("28July16", strings_sumTABLE[4][0]);
        Log.d("28July16", strings_sumTABLE[5][0]);
        Log.d("28July16", strings_sumTABLE[6][0]);

        if (!strings_sumTABLE[0][0].equals("")) {
            //เริ่มใส่ค่า
            for(int i = 0;i<strings_sumTABLE[0].length;i++) {
                OkHttpClient okHttpClient = new OkHttpClient();
                RequestBody requestBody = new FormEncodingBuilder()
                        .add("isAdd", "true")
                        .add("VN", strAddVN)
                        .add(MyManage.column_Main_id, strings_sumTABLE[1][i])
                        .add(MyManage.column_DateRef, strings_sumTABLE[2][i])
                        .add(MyManage.column_TimeRef, strings_sumTABLE[3][i])
                        .add(MyManage.column_DateCheck, strings_sumTABLE[4][i])
                        .add(MyManage.column_TimeCheck, strings_sumTABLE[5][i])
                        .add(MyManage.column_SkipHold, strings_sumTABLE[6][i])
                        .build();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(strURL).post(requestBody).build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {
                        Log.d("26July16", "Failure in sumTABLE");
                    }

                    @Override
                    public void onResponse(Response response) throws IOException {
                        Log.d("26July16", "success in sumTABLE");
                        response.body().close();

                    }
                });



            }




        }
    }


    private void updateDatamainTABLE_PHPMyAdmin(String strAddHN,String strAddVN) {

        //ทำค่า VN ก่อน



        //String strURL = "http://www.swiftcodingthai.com/mhm/add_mainTABLE.php";
        String strURL = "http://www.swiftcodingthai.com/mhm/add_mainTABLE_edited2.php";

        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyHelper.DATABASE_NAME,
                MODE_PRIVATE, null);
        final Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM mainTABLE", null);
        int iCount = cursor.getCount();
        final int intTime = 0;
        if (iCount > 0) {
            cursor.moveToFirst();
            for(int i = 0;i<cursor.getCount();i++) {
                final String str1 = cursor.getString(cursor.getColumnIndex(MyManage.mcolumn_id));
                String str2 = cursor.getString(cursor.getColumnIndex(MyManage.mcolumn_Med_id));
                String str3 = cursor.getString(cursor.getColumnIndex(MyManage.mcolumn_trade_name));
                String str4 = cursor.getString(cursor.getColumnIndex(MyManage.mcolumn_generic_line));
                String str5 = cursor.getString(cursor.getColumnIndex(MyManage.mcolumn_amount_tablet));
                String str6 = cursor.getString(cursor.getColumnIndex(MyManage.mcolumn_which_date_d));
                String str7 = cursor.getString(cursor.getColumnIndex(MyManage.mcolumn_appearance));
                String str8 = cursor.getString(cursor.getColumnIndex(MyManage.mcolumn_ea));
                String str9 = cursor.getString(cursor.getColumnIndex(MyManage.mcolumn_Main_pharmaco));
                String str10 = cursor.getString(cursor.getColumnIndex(MyManage.mcolumn_startdate));
                String str11 = cursor.getString(cursor.getColumnIndex(MyManage.mcolumn_finishdate));
                String str12 = cursor.getString(cursor.getColumnIndex(MyManage.mcolumn_prn));
                String str13 = cursor.getString(cursor.getColumnIndex(MyManage.mcolumn_t1));
                String str14 = cursor.getString(cursor.getColumnIndex(MyManage.mcolumn_t2));
                String str15 = cursor.getString(cursor.getColumnIndex(MyManage.mcolumn_t3));
                String str16 = cursor.getString(cursor.getColumnIndex(MyManage.mcolumn_t4));
                String str17 = cursor.getString(cursor.getColumnIndex(MyManage.mcolumn_t5));
                String str18 = cursor.getString(cursor.getColumnIndex(MyManage.mcolumn_t6));
                String str19 = cursor.getString(cursor.getColumnIndex(MyManage.mcolumn_t7));
                String str20 = cursor.getString(cursor.getColumnIndex(MyManage.mcolumn_t8));
                String str21 = cursor.getString(cursor.getColumnIndex(MyManage.mcolumn_datetimecanceled));

                OkHttpClient okHttpClient = new OkHttpClient();
                RequestBody requestBody = new FormEncodingBuilder()
                        .add("isAdd", "true")
                        .add("VN", strAddVN)
                        .add("Main_id", str1)
                        .add("HN", strAddHN)
                        .add(MyManage.mcolumn_Med_id, str2)
                        .add(MyManage.mcolumn_trade_name, str3)
                        .add(MyManage.mcolumn_generic_line, str4)
                        .add(MyManage.mcolumn_amount_tablet, str5)
                        .add(MyManage.mcolumn_which_date_d, str6)
                        .add(MyManage.mcolumn_appearance, str7)
                        .add(MyManage.mcolumn_ea, str8)
                        .add(MyManage.mcolumn_Main_pharmaco, str9)
                        .add(MyManage.mcolumn_startdate, str10)
                        .add(MyManage.mcolumn_finishdate, str11)
                        .add(MyManage.mcolumn_prn, str12)
                        .add(MyManage.mcolumn_t1, str13)
                        .add(MyManage.mcolumn_t2, str14)
                        .add(MyManage.mcolumn_t3, str15)
                        .add(MyManage.mcolumn_t4, str16)
                        .add(MyManage.mcolumn_t5, str17)
                        .add(MyManage.mcolumn_t6, str18)
                        .add(MyManage.mcolumn_t7, str19)
                        .add(MyManage.mcolumn_t8, str20)
                        .add(MyManage.mcolumn_datetimecanceled, str21)
                        .build();

                Request.Builder builder = new Request.Builder();
                Request request = builder.url(strURL).post(requestBody).build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {

                    }

                    @Override
                    public void onResponse(Response response) throws IOException {
                        Log.d("26July16", "mcolumn_id :"+ str1);
                        response.body().close();

                    }
                });
                cursor.moveToNext();
            } //for
            cursor.close();
        }

    }

    public void hideSoftKeyboard() {
        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    public void showSoftKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        view.requestFocus();
        inputMethodManager.showSoftInput(view, 0);
    }



    public void showTimePickerDialog(View v) {

        MyTimePickerFragment myTimePickerFragment = new MyTimePickerFragment();
        myTimePickerFragment.show(getFragmentManager(), "timePicker");

    } //showTimePickerDialog

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        MyData myData = new MyData();
        String sTime = myData.createStringTime(hourOfDay, minute);
        textViewAppointmentTime.setText(sTime);
        //String[] strAppointmentNof = myManage.filter_userTABLE(10); //หา AppointmentNof
        //String[] queryDateTimeAppointmentRef = strAppointmentNof[0].split(";");
        s2 = sTime;

        //textViewAppointmentDay.setText(strings[which]);
        String[] strUser = myManage.filter_userTABLE(1); //ค่า username
        String s = s1 + ";" + s2;
        myManage.update_Appointment_notif(strUser[0],s);
        //dialog.dismiss();
        SplashScreen splashScreen = new SplashScreen();
        splashScreen.updateDailyBroadcast(getBaseContext());


    }
}
