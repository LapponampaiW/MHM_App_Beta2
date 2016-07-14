package com.su.lapponampai_w.mhm_app_beta1;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NoteActivity extends AppCompatActivity {

    //Explicit
    TextView textViewCalendar;
    CheckBox checkBox;
    String strReceiveIntent;
    Button saveButton, cancelButton;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        bindWidget();

        textViewCalendar.setText("");

        clickTextViewCalendar();

        clickSaveCancelButton();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 1) {
            Log.d("14July16V1", "in onActivityResult");
            if (resultCode == RESULT_OK) {

                strReceiveIntent = data.getStringExtra("PopupCalendarActivity");
                Log.d("14July16V1", "strReceiveIntent : " + strReceiveIntent);
                textViewCalendar.setText(strReceiveIntent);
            }
        }
    }

    private void clickSaveCancelButton() {

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strEditText = editText.getText().toString().trim();
                String strCalendar = textViewCalendar.getText().toString();

                if (strCalendar.equals("") || strEditText.equals("")) {
                    Toast.makeText(NoteActivity.this, "โปรดกรอกวันที่และข้อความทั้ง 2 ช่อง", Toast.LENGTH_SHORT).show();
                } else {
                    MyData myData = new MyData();
                    MyManage myManage = new MyManage(NoteActivity.this);
                    String strCheckBox;
                    String strDateTime = myData.currentDateTime();
                    if (checkBox.isChecked()) {
                        strCheckBox = "Y";
                    } else {
                        strCheckBox = "N";
                    }

                    myManage.addValueToNoteTABLE(strDateTime, strCalendar, strEditText, strCheckBox);
                }
            }
        });

    } //clickSaveCancelButton



    private void clickTextViewCalendar() {

        textViewCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(NoteActivity.this,PopUpCalendarActivity.class);
                startActivityForResult(intent, 1);
            }
        });

    }  //clickTextViewCalendar





    private void bindWidget() {

        textViewCalendar = (TextView) findViewById(R.id.textView121);
        checkBox = (CheckBox) findViewById(R.id.checkBoxNote);
        saveButton = (Button) findViewById(R.id.buttonNoteSave);
        cancelButton = (Button) findViewById(R.id.buttonNoteCancel);
        editText = (EditText) findViewById(R.id.editTextNote);

    }  //bindWidget
}
