package com.su.lapponampai_w.mhm_app_beta1;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PopUpAppLabActivity extends AppCompatActivity implements
        TimePickerDialog.OnTimeSetListener,DatePickerDialog.OnDateSetListener{

    TextView dateTextView, timeTextView;
    CheckBox checkBox,checkBoxL0,checkBoxL1
            ,checkBoxL2,checkBoxL3,checkBoxL4,checkBoxL5,checkBoxL6,checkBoxL7,checkBoxL8,
            checkBoxL9,checkBoxL10,checkBoxL11;
    Button saveButton, cancelButton;
    LinearLayout linearLayout;
    EditText noteEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_app_lab);

        bindWidget();

        showView();

        clickAppointmentDateTime();

        displayMetrics();

        clickSaveCancel();


    }



    private void clickSaveCancel() {

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String stringNote = noteEditText.getText().toString().trim();
                String stringDate = dateTextView.getText().toString();
                String stringTime = timeTextView.getText().toString();

                Boolean aBooleanTime = true;
                if (!checkBox.isChecked() && stringTime.equals("")) {
                    aBooleanTime = false;
                }

                CheckBox[] checkBoxes = {checkBoxL0,checkBoxL1,checkBoxL2,checkBoxL3,checkBoxL4,
                        checkBoxL5,checkBoxL6,checkBoxL7,checkBoxL8,checkBoxL9,checkBoxL10,checkBoxL11};
                String[] strings = new String[12];

                Boolean aBooleanLabDetail = true;
                for(int i = 0;i <checkBoxes.length;i++) {
                    if (checkBoxes[i].isChecked()) {
                        strings[i] = "1";
                        aBooleanLabDetail = false;
                    } else {
                        strings[i] = "0";
                    }
                } //for

                String s = "";
                for(int x =0;x <strings.length;x++) {
                    if (s.equals("")) {
                        s = strings[x];
                    } else {
                        s = s + "," + strings[x];
                    }
                } //for

                //if (aBooleanTime && !stringDate.equals("") && !aBooleanLabDetail) {
                if (aBooleanTime && !stringDate.equals("")) {
                    MyManage myManage = new MyManage(PopUpAppLabActivity.this);
                    MyData myData = new MyData();
                    String strCurrentDateTime = myData.currentDateTime();
                    myManage.addValueToAppointmentTABLE(strCurrentDateTime, stringDate,
                            stringTime, "", stringNote, "Y", s);

                    Intent intent = new Intent(PopUpAppLabActivity.this, AppointmentActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(getBaseContext(), "โปรดกรอกข้อมูลให้ครบถ้วน", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }

    private void showView() {

        dateTextView.setText("");
        timeTextView.setText("");

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    linearLayout.setVisibility(View.INVISIBLE);
                    timeTextView.setText("");
                } else {
                    linearLayout.setVisibility(View.VISIBLE);
                }
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

        dateTextView = (TextView) findViewById(R.id.textView108); //ปุ่ม Date
        timeTextView = (TextView) findViewById(R.id.textView110); //ปุ่ม Time

        checkBox = (CheckBox) findViewById(R.id.checkBox); //checkBox ว่าต้องมีเวลาหรือไม่
        saveButton = (Button) findViewById(R.id.buttonAppointmentSave);
        linearLayout = (LinearLayout) findViewById(R.id.linAppointmentTime);
        cancelButton = (Button) findViewById(R.id.buttonAppointmentCancel);
        checkBoxL0 = (CheckBox) findViewById(R.id.checkBoxL0);
        checkBoxL1 = (CheckBox) findViewById(R.id.checkBoxL1);
        checkBoxL2 = (CheckBox) findViewById(R.id.checkBoxL2);
        checkBoxL3 = (CheckBox) findViewById(R.id.checkBoxL3);
        checkBoxL4 = (CheckBox) findViewById(R.id.checkBoxL4);
        checkBoxL5 = (CheckBox) findViewById(R.id.checkBoxL5);
        checkBoxL6 = (CheckBox) findViewById(R.id.checkBoxL6);
        checkBoxL7 = (CheckBox) findViewById(R.id.checkBoxL7);
        checkBoxL8 = (CheckBox) findViewById(R.id.checkBoxL8);
        checkBoxL9 = (CheckBox) findViewById(R.id.checkBoxL9);
        checkBoxL10 = (CheckBox) findViewById(R.id.checkBoxL10);
        checkBoxL11 = (CheckBox) findViewById(R.id.checkBoxL11);
        noteEditText = (EditText) findViewById(R.id.editText5);

    }

    public void showDatePickerDialog(View v) {
        MyDatePickerFragment myDatePickerFragment = new MyDatePickerFragment();
        myDatePickerFragment.show(getFragmentManager(), "datePicker");

    } //showDatePickerDialog

    public void showTimePickerDialog(View v) {

        MyTimePickerFragment myTimePickerFragment = new MyTimePickerFragment();
        myTimePickerFragment.show(getFragmentManager(), "timePicker");

    } //showTimePickerDialog

    private void clickAppointmentDateTime() {

        dateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });

        timeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(v);
            }
        });



    } //clickAppointmentDate

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

        MyData myData = new MyData();
        String sSpecificDate = myData.createStringDay(dayOfMonth, monthOfYear + 1, year);
        String sDate = myData.currentDay();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date_Specific = new Date();
        Date date_current = new Date();

        try {
            date_Specific = dateFormat.parse(sSpecificDate);
            date_current = dateFormat.parse(sDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (date_Specific.compareTo(date_current) < 0) {
            Toast.makeText(getBaseContext(), "ไม่สามารถตั้งวันนัดน้อยกว่าวันที่ปัจจุบันได้", Toast.LENGTH_SHORT).show();
            dateTextView.setText("");
        } else {
            dateTextView.setText(sSpecificDate);
        }
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        MyData myData = new MyData();
        String sTime = myData.createStringTime(hourOfDay, minute);
        timeTextView.setText(sTime);

    }
}
