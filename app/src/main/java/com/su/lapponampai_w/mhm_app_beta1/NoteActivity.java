package com.su.lapponampai_w.mhm_app_beta1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NoteActivity extends AppCompatActivity {

    //Explicit
    TextView textViewCalendar;
    CheckBox checkBox;
    EditText editTextNote;
    String strReceiveIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        bindWidget();

        textViewCalendar.setText("");

        clickTextViewCalendar();


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


    private void clickTextViewCalendar() {

        textViewCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(NoteActivity.this,PopUpCalendarActivity.class);
                startActivityForResult(intent, 1);
            }
        });

    }





    private void bindWidget() {

        textViewCalendar = (TextView) findViewById(R.id.textView121);
        checkBox = (CheckBox) findViewById(R.id.checkBox9);
        editTextNote = (EditText) findViewById(R.id.editText7);

    }
}
