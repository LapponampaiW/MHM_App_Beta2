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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PopUpAppointmentActivity extends AppCompatActivity implements
        TimePickerDialog.OnTimeSetListener,DatePickerDialog.OnDateSetListener{

    //Explicit
    EditText doctoreditText, noteEditText;
    TextView dateTextView, timeTextView;
    CheckBox checkBox;
    LinearLayout linearLayout;
    Button cancelButton, saveButton;
    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_appointment);


        bindWidget();

        clickAppointmentDateTime();

        showView();

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
                String stringDoctor = doctoreditText.getText().toString().trim();
                String stringNote = noteEditText.getText().toString();
                String stringDate = dateTextView.getText().toString();
                String stringTime = timeTextView.getText().toString();
                Log.d("AppointmentActivity", "stringTime : " + stringTime);
                String sSkip;
                stringNote = noteEditText.getText().toString().trim();
                Log.d("AppointmentActivity", stringDoctor);

                if (checkBox.isChecked()) {
                    sSkip = "Y";
                } else {
                    sSkip = "N";
                }

                if (sSkip.equals("N") && stringTime.equals("")) {
                    Toast.makeText(getBaseContext(), "โปรดกรอกข้อมูลให้ครบถ้วน", Toast.LENGTH_SHORT).show();
                } else {
                    if (stringDoctor.equals("") || stringDate.equals("")) {
                        Toast.makeText(getBaseContext(), "โปรดกรอกข้อมูลให้ครบถ้วน", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getBaseContext(), "success!!", Toast.LENGTH_SHORT).show();
                        //ทำไปต่อจากตรงนี้
                        MyManage myManage = new MyManage(PopUpAppointmentActivity.this);
                        MyData myData = new MyData();
                        String strCurrentDateTime = myData.currentDateTime();
                        myManage.addValueToAppointmentTABLE(strCurrentDateTime, stringDate,
                                stringTime, stringDoctor, stringNote,"Y");

                        Intent intent = new Intent(PopUpAppointmentActivity.this,AppointmentActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
    }


    private void displayMetrics() {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        getWindow().setLayout((int) (width*.85),(int) (height*.55));
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

        final MyManage myManage = new MyManage(this);
        MyData myData = new MyData();


    } //showView



    private void bindWidget() {

        doctoreditText = (EditText) findViewById(R.id.editText4);
        noteEditText = (EditText) findViewById(R.id.editText5);
        dateTextView = (TextView) findViewById(R.id.textView108); //ปุ่ม Date
        timeTextView = (TextView) findViewById(R.id.textView110); //ปุ่ม Time
        checkBox = (CheckBox) findViewById(R.id.checkBox); //checkBox ว่าต้องมีเวลาหรือไม่
        linearLayout = (LinearLayout) findViewById(R.id.linAppointmentTime);
        saveButton = (Button) findViewById(R.id.buttonAppointmentSave);
        cancelButton = (Button) findViewById(R.id.buttonAppointmentCancel);

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
