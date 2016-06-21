package com.su.lapponampai_w.mhm_app_beta1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MedicationDetailActivity extends AppCompatActivity {


    //Explicit
    private TextView textView1,textView2, textView3;
    private ImageView imageView1;
    //receiveIntent
    private String string0,string1,string2,string3,string4,string5,string6,string7,
            string8,string9,string10,string11,string12,string13,string14,string15,string16,
            string17,string18,string19,string20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication_detail);

        bindWidget();

        //Receive From Intent
        receiveIntent();

        //Show View
        showView();
    }

    private void showView() {

        MyData myData = new MyData();
        String[] stringAppearance = new String[1];
        stringAppearance[0] = string6;
        int[] intsIndex = myData.translate_Appearance(stringAppearance);
        imageView1.setBackgroundResource(intsIndex[0]);

        textView1.setText(string2);
        textView2.setText(string3);
    }

    private void receiveIntent() {


        string0 = getIntent().getStringExtra("MedicationListActivity_id");
        string1 = getIntent().getStringExtra("MedicationListActivity_med_id");
        string2 = getIntent().getStringExtra("MedicationListActivity_trade_name");
        string3 = getIntent().getStringExtra("MedicationListActivity_generic_line");
        string4 = getIntent().getStringExtra("MedicationListActivity_amount_tablet");
        string5 = getIntent().getStringExtra("MedicationListActivity_which_date_d");
        string6 = getIntent().getStringExtra("MedicationListActivity_appearance");
        string7 = getIntent().getStringExtra("MedicationListActivity_ea");
        string8 = getIntent().getStringExtra("MedicationListActivity_pharmaco");
        string9 = getIntent().getStringExtra("MedicationListActivity_startdate");
        string10 = getIntent().getStringExtra("MedicationListActivity_finishdate");
        string11 = getIntent().getStringExtra("MedicationListActivity_prn");
        string12 = getIntent().getStringExtra("MedicationListActivity_t1");
        string13 = getIntent().getStringExtra("MedicationListActivity_t2");
        string14 = getIntent().getStringExtra("MedicationListActivity_t3");
        string15 = getIntent().getStringExtra("MedicationListActivity_t4");
        string16 = getIntent().getStringExtra("MedicationListActivity_t5");
        string17 = getIntent().getStringExtra("MedicationListActivity_t6");
        string18 = getIntent().getStringExtra("MedicationListActivity_t7");
        string19 = getIntent().getStringExtra("MedicationListActivity_t8");
        string20 = getIntent().getStringExtra("MedicationListActivity_datetimecanceled");
    }

    private void bindWidget() {
        imageView1 = (ImageView) findViewById(R.id.imageView3); //รูปเม็ดยา
        textView1 = (TextView) findViewById(R.id.textView66); //ชื่อการค้า
        textView2 = (TextView) findViewById(R.id.textView20); //ชื่อสามัญทางยา

    }
}
