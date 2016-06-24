package com.su.lapponampai_w.mhm_app_beta1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MedicationDetailActivity extends AppCompatActivity {


    //Explicit
    private TextView textView1,textView2, textView3,textView4,textView5,textView6,textView7,
            textView8,textView9,textView10,textView11,textView12,textView13,textView14,textView15;
    private TextView textViewAddAmountMedicine, textViewtotalAmountTablet;
    private ImageView imageView1;
    //receiveIntent
    private String string0,string1,string2,string3,string4,string5,string6,string7,string7_Translate,
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

        //Click AddAmountMedicine
        clickAddAmountMedicine();

        //show จำนวนเม็ดยาคงเหลือ
        showtotalAmountTablet();


    }



    private void showtotalAmountTablet() {
        MyManage myManage = new MyManage(this);
        MyData myData = new MyData();
        String[][] stringstotalAmount = {myManage.readAlltotalAmountTABLE(0),
                myManage.readAlltotalAmountTABLE(1),myManage.readAlltotalAmountTABLE(2),
                myManage.readAlltotalAmountTABLE(3)};
        String s_totalAmount;

        if (stringstotalAmount[0][0].equals("")) {
            //Toast.makeText(MedicationDetailActivity.this,"a;dsjf;sdfj",Toast.LENGTH_LONG).show();
            s_totalAmount = "0 ";
            string7_Translate = myData.translate_EA(string7);
            s_totalAmount = s_totalAmount.concat(string7_Translate);
            textViewtotalAmountTablet.setText(s_totalAmount);
        }



    }

    private void clickAddAmountMedicine() {
        textViewAddAmountMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MedicationDetailActivity.this, PopUpAddAmountMedicine.class);
                startActivity(intent);




            }
        });

    }

    private void showView() {

        MyData myData = new MyData();
        String[] stringAppearance = new String[1];
        stringAppearance[0] = string6;
        int[] intsIndex = myData.translate_Appearance(stringAppearance);
        imageView1.setBackgroundResource(intsIndex[0]);

        textView1.setText(string2);
        textView2.setText(string3);
        //แปลงหน่วย
        string7_Translate = myData.translate_EA(string7);

        textView3.setText("รับประทานครั้งละ " + string4 + " " + string7_Translate);

        String string5_Translate = myData.translate_Which_Date_D(string5);
        textView4.setText(string5_Translate);

        textView5.setText("วันที่เริ่มต้นรับประทานยา : " + string9);
        if (string10.equals("")) {
            textView6.setVisibility(View.GONE);
        } else {
            textView6.setText("วันสิ้นสุดรับประทานยา : " + string10);
        }

            textView7.setText("เวลาในการรับประทานยา :");

        if (string11.equals("Y")) {
            textView8.setText("รับประทานยาเป็นครั้งคราว!!!");
        } else if (string11.equals("N")) {
            TextView[] textViews = {textView8, textView9, textView10, textView11, textView12,
                    textView13, textView14, textView15};
            String[] strings = {string12,string13,string14,string15,string16,string17,
                    string18,string19};

            for(int i=0;i<strings.length;i++) {
                if (strings[i].equals("")) {
                    textViews[i].setVisibility(View.GONE);
                } else {
                    textViews[i].setText("เวลาที่ " + Integer.toString(i+1) + " :  " + strings[i] + " น.");
                }
            }
        }


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
        textView3 = (TextView) findViewById(R.id.textView62); //รับประทานครั้งละ .... EA
        textView4 = (TextView) findViewById(R.id.textView64);
        textView5 = (TextView) findViewById(R.id.textView68); //StartDate
        textView6 = (TextView) findViewById(R.id.textView69); //FinishDate
        textView7 = (TextView) findViewById(R.id.textView70); //เวลาที่รับประทาน,เป็นครั้งคราว

        textView8 = (TextView) findViewById(R.id.textView71); //t1
        textView9 = (TextView) findViewById(R.id.textView72); //t2
        textView10 = (TextView) findViewById(R.id.textView73); //t3
        textView11 = (TextView) findViewById(R.id.textView74); //t4
        textView12 = (TextView) findViewById(R.id.textView75); //t5
        textView13 = (TextView) findViewById(R.id.textView76); //t6
        textView14 = (TextView) findViewById(R.id.textView77); //t7
        textView15 = (TextView) findViewById(R.id.textView78); //t8

        textViewAddAmountMedicine = (TextView) findViewById(R.id.textView79); //เพิ่มจำนวนยา
        textViewtotalAmountTablet = (TextView) findViewById(R.id.textView88); //จำนวนยาคงเหลือพร้อม UOM


    }
}
