package com.su.lapponampai_w.mhm_app_beta1;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AddMedicine2Activity extends AppCompatActivity {

    //Explicit
    private TextView textView1, textView2, textView3, textView4,
            textView5, textView6, textView7, textView8, textView9,
            textView10, textView11, textView12, textView13, textView14;

    private  String string1, string2, string3, string4, string5, string6,
            string7, string8, string9, string10, string11, string12,
            string13, string14;

    private String[] strings0,strings2,strings3,strings4,strings5,strings6,strings1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine2);

        //BindWidget
        bindWidget();

        //Receive From Intent
        receiveIntent();

        //Show View
        showView();

    } //Main Method

    private void showView() {
        textView1.setText(string1);
        textView2.setText(string2);
        textView3.setText(string3);
        textView4.setText(string4);
        textView5.setText(string5);
        textView6.setText(string6);
        textView7.setText(string7);
        textView8.setText(string8);
        textView9.setText(string9);
        textView10.setText(string10);
        textView11.setText(string11);
        textView12.setText(string12);
        textView13.setText(string13);
        textView14.setText(string14);



    }

    private void receiveIntent() {

        string1 = getIntent().getStringExtra("Med_id");
        string2 = getIntent().getStringExtra("Trade_name");
        string3 = getIntent().getStringExtra("Generic_line");
        string4 = getIntent().getStringExtra("Which_Date_D");
        string5 = getIntent().getStringExtra("Appearance");
        string6 = getIntent().getStringExtra("Pharmaco");
        string7 = getIntent().getStringExtra("T1");
        string8 = getIntent().getStringExtra("T2");
        string9 = getIntent().getStringExtra("T3");
        string10 = getIntent().getStringExtra("T4");
        string11 = getIntent().getStringExtra("T5");
        string12 = getIntent().getStringExtra("T6");
        string13 = getIntent().getStringExtra("T7");
        string14 = getIntent().getStringExtra("T8");



    }

    private void bindWidget() {
        textView1 = (TextView) findViewById(R.id.textView10);
        textView2 = (TextView) findViewById(R.id.textView12);
        textView3 = (TextView) findViewById(R.id.textView14);
        textView4 = (TextView) findViewById(R.id.textView16);
        textView5 = (TextView) findViewById(R.id.textView18);
        textView6 = (TextView) findViewById(R.id.textView20);
        textView7 = (TextView) findViewById(R.id.textView22);
        textView8 = (TextView) findViewById(R.id.textView24);
        textView9 = (TextView) findViewById(R.id.textView26);
        textView10 = (TextView) findViewById(R.id.textView28);
        textView11 = (TextView) findViewById(R.id.textView30);
        textView12 = (TextView) findViewById(R.id.textView32);
        textView13 = (TextView) findViewById(R.id.textView34);
        textView14 = (TextView) findViewById(R.id.textView36);

    }

    public void clickCancelAddMedicine(View view) {
        finish();
    }

    public void clickSaveAddMedicine(View view) {

        MyManage myManage = new MyManage(this);

        //เริ่มจากตรงนี้นะ

        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyHelper.DATABASE_NAME, MODE_PRIVATE, null);
        sqLiteDatabase.delete("drugInteractionTABLE_For_Query", null, null);

        myManage.checkDrugInteraction(string1);


        strings0 = myManage.filter_drugInteractionTABLE_For_Query(0);
        strings1 = myManage.filter_drugInteractionTABLE_For_Query(1);
        strings2 = myManage.filter_drugInteractionTABLE_For_Query(2);
        strings3 = myManage.filter_drugInteractionTABLE_For_Query(3);
        strings4 = myManage.filter_drugInteractionTABLE_For_Query(4);
        strings5 = myManage.filter_drugInteractionTABLE_For_Query(5);
        strings6 = myManage.filter_drugInteractionTABLE_For_Query(6);

        Log.d("filter_drugInteraction", strings3[0]);

        if (strings0.length != 0) {
            if (strings3[0].equals("1")) {
                alertDialogFaltal();
            }

            for(int i = 0; i < strings0.length; i++) {
                if (strings3[i].equals("2")) {
                    alertDialogInteraction();
                }
            }

        }



        /*

        myManage.addValueTomainTABLE(string1,string2,string3,string4,string5,string6,string7,string8,string9,string10,string11,string12,string13,string14);

        Intent intent = new Intent(AddMedicine2Activity.this, MainActivity.class);
        startActivity(intent);
        finish();
        */

    } //clickSave

    private void alertDialogInteraction() {

        AlertDialog.Builder builder = new  AlertDialog.Builder(this);
        builder.setIcon(R.drawable.icon_question);
        builder.setTitle("เกิดปฏิกิริยาระหว่างยา (ยาตีกัน)");
        builder.setMessage("");

    }

    private void alertDialogFaltal() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.faltal_drug);
        builder.setTitle("Fatal Drug Interaction");
        builder.setMessage("ยานี้ไม่สามารถทานร่วมกับ\n ยาบางตัวที่ท่านรับประทานอยู่ \n โปรดปรึกษาแพทย์หรือเภสัชกรก่อน \n (ไม่แนะนำให้รับประทานอย่างเด็ดขาด!!!)");
        builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        });
        builder.show();
    }

}  //Main Class
