package com.su.lapponampai_w.mhm_app_beta1;

import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AddMedicine2Activity extends AppCompatActivity {

    //Explicit
    TextView textView_r_t1;

    //receive ค่า
    String string_r_t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine2);

        bindWidget();

        showView();


    }

    private void showView() {

        string_r_t1 = getIntent().getStringExtra("T1");


        setTextView();

        clickTimeTextView();

    }

    private void clickTimeTextView() {

        final Calendar calendar = Calendar.getInstance();

        textView_r_t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(AddMedicine2Activity.this,onTimeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true).show();
            }
        });
    }

    private void setTextView() {
        String head, tail;

        if (string_r_t1.length() == 3) {
            string_r_t1 = "0".concat(string_r_t1);
            head = string_r_t1.substring(0, 2);
            tail = string_r_t1.substring(2);

            textView_r_t1.setText(head + " : " + tail);
        } else {
            head = string_r_t1.substring(0, 2);
            tail = string_r_t1.substring(2);

            textView_r_t1.setText(head + " : " + tail);
        }


    }

    private void bindWidget() {
        textView_r_t1 = (TextView) findViewById(R.id.r_t1);
    }

    TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            

            textView_r_t1.setText(hourOfDay + " : " + minute);
        }
    };
}
