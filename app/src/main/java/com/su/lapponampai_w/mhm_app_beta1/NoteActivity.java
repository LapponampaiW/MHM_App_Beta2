package com.su.lapponampai_w.mhm_app_beta1;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NoteActivity extends AppCompatActivity implements
        DatePickerDialog.OnDateSetListener{

    //Explicit
    TextView textViewCalendar;
    CheckBox checkBox;
    String strReceiveIntent;
    Button saveButton, cancelButton;
    EditText editText;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        bindWidget();

        textViewCalendar.setText("");

        clickTextViewCalendar();

        showListView();

        clickSaveCancelButton();

        clickListViewDelete();


    }

    private void clickListViewDelete() {

        /*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                MyManage myManage = new MyManage(NoteActivity.this);
                final String[] strings_id = myManage.readAllnoteTABLE(0);

                final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setCancelable(false);
                builder.setIcon(R.drawable.logo_mhm);
                builder.setTitle("ลบข้อมูลบันทึก");
                builder.setMessage("ยืนยันการลบข้อมูลบันทึก");
                builder.setPositiveButton("ยกเลิก", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("ยืนยัน", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                        String id = strings_id[position];
                        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyHelper.DATABASE_NAME,
                                MODE_PRIVATE, null);
                        sqLiteDatabase.delete("noteTABLE", "_id = " + id, null);

                        Toast.makeText(NoteActivity.this,"Delete in noteTABLE",Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(NoteActivity.this,NoteActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();

                    }
                });
                builder.show();

            }
        });
        */

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                MyManage myManage = new MyManage(NoteActivity.this);
                final String[] strings_id = myManage.readAllnoteTABLE(0);

                final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setCancelable(false);
                builder.setIcon(R.drawable.logo_mhm);
                builder.setTitle("ลบข้อมูลบันทึก");
                builder.setMessage("ยืนยันการลบข้อมูลบันทึก");
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
                        String id = strings_id[position];
                        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyHelper.DATABASE_NAME,
                                MODE_PRIVATE, null);
                        sqLiteDatabase.delete("noteTABLE", "_id = " + id, null);

                        Toast.makeText(NoteActivity.this,"Delete in noteTABLE",Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(NoteActivity.this,NoteActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();

                    }
                });
                builder.show();
                return true;
            }
        });






    } //clickListViewDelete

    private void showListView() {

        //ทำ ListView

        MyManage myManage = new MyManage(this);
        String[] stringsDate = myManage.readAllnoteTABLE(2);
        String[] stringsNote = myManage.readAllnoteTABLE(3);

        for(int i = 0;i<stringsDate.length;i++) {
            stringsDate[i] = "วันที่บันทึก :".concat(stringsDate[i]);
            stringsNote[i] = "ข้อความ : ".concat(stringsNote[i]);
        }

        MyAdaptorNote myAdaptorNote = new MyAdaptorNote(NoteActivity.this, stringsDate, stringsNote);
        listView.setAdapter(myAdaptorNote);


    } //showListView

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

                    Intent intent = new Intent(NoteActivity.this,NoteActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();





                }
            }
        });

    } //clickSaveCancelButton



    private void clickTextViewCalendar() {

        /*
        textViewCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(NoteActivity.this,PopUpCalendarActivity.class);
                startActivityForResult(intent, 1);
            }
        });
        */
        textViewCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });


    }  //clickTextViewCalendar

    private void showDatePickerDialog(View v) {
        MyDatePickerFragment myDatePickerFragment = new MyDatePickerFragment();
        myDatePickerFragment.show(getFragmentManager(), "datePicker");
    }


    private void bindWidget() {

        textViewCalendar = (TextView) findViewById(R.id.textView121);
        checkBox = (CheckBox) findViewById(R.id.checkBoxNote);
        saveButton = (Button) findViewById(R.id.buttonNoteSave);
        cancelButton = (Button) findViewById(R.id.buttonNoteCancel);
        editText = (EditText) findViewById(R.id.editTextNote);
        listView = (ListView) findViewById(R.id.listViewNote);

    }  //bindWidget

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

        textViewCalendar.setText(sSpecificDate);


    }
}
