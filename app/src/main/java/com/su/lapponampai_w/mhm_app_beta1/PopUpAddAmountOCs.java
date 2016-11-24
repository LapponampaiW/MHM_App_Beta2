package com.su.lapponampai_w.mhm_app_beta1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;

import static com.su.lapponampai_w.mhm_app_beta1.AddMedicine2Activity.string1;
import static com.su.lapponampai_w.mhm_app_beta1.AddMedicine2Activity.string10;
import static com.su.lapponampai_w.mhm_app_beta1.AddMedicine2Activity.string11;
import static com.su.lapponampai_w.mhm_app_beta1.AddMedicine2Activity.string12;
import static com.su.lapponampai_w.mhm_app_beta1.AddMedicine2Activity.string13;
import static com.su.lapponampai_w.mhm_app_beta1.AddMedicine2Activity.string14;
import static com.su.lapponampai_w.mhm_app_beta1.AddMedicine2Activity.string15;
import static com.su.lapponampai_w.mhm_app_beta1.AddMedicine2Activity.string16;
import static com.su.lapponampai_w.mhm_app_beta1.AddMedicine2Activity.string17;
import static com.su.lapponampai_w.mhm_app_beta1.AddMedicine2Activity.string2;
import static com.su.lapponampai_w.mhm_app_beta1.AddMedicine2Activity.string3;
import static com.su.lapponampai_w.mhm_app_beta1.AddMedicine2Activity.string4;
import static com.su.lapponampai_w.mhm_app_beta1.AddMedicine2Activity.string5;
import static com.su.lapponampai_w.mhm_app_beta1.AddMedicine2Activity.string6;
import static com.su.lapponampai_w.mhm_app_beta1.AddMedicine2Activity.string7;
import static com.su.lapponampai_w.mhm_app_beta1.AddMedicine2Activity.string8;
import static com.su.lapponampai_w.mhm_app_beta1.AddMedicine2Activity.string9;
import static com.su.lapponampai_w.mhm_app_beta1.PopUpAddAmountMedicine.editTextAmountMedicine;
import static com.su.lapponampai_w.mhm_app_beta1.PopUpAddAmountMedicine.buttonAdd10;
import static com.su.lapponampai_w.mhm_app_beta1.PopUpAddAmountMedicine.buttonAdd1;
import static com.su.lapponampai_w.mhm_app_beta1.PopUpAddAmountMedicine.buttonMinus1;
import static com.su.lapponampai_w.mhm_app_beta1.PopUpAddAmountMedicine.buttonMinus10;
import static com.su.lapponampai_w.mhm_app_beta1.PopUpAddAmountMedicine.integerAmountMedicine;
import static com.su.lapponampai_w.mhm_app_beta1.PopUpAddAmountMedicine.textViewOK;
import static com.su.lapponampai_w.mhm_app_beta1.PopUpAddAmountMedicine.textViewCancel;



public class PopUpAddAmountOCs extends AppCompatActivity {

    //Explicit
    private String stringStartPill,stringStartDate,stringActivePill,stringPlacebo,stringWhich_Date_D;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_add_amount_ocs);

        bindWidget();

        displayMetrics();

        receiveIntent();

        showView();

        clickCalculateAmountMedicine();

        //Click button(OK or Cancel)
        clickOKCancel();
        
        
    }

    private void clickOKCancel() {

        //Click OK
        textViewOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //เอาค่าจาก Edittext มาเป็น int
                int intAmountMedicine = Integer.parseInt(editTextAmountMedicine.getText().toString());
                //เอามาลบ 1 เพื่อให้สามารถคำนวนได้จริง
                intAmountMedicine = (intAmountMedicine - 1) * -1;

                MyData myData = new MyData();
                String sCurrentDate = myData.currentDay();
                Date dateInitial = myData.stringChangetoDateWithOutTime(sCurrentDate); //วันที่ของวันนี้ Type Date

                Calendar calendarCurrent = Calendar.getInstance();
                calendarCurrent.setTime(dateInitial);  //วันนี้
                calendarCurrent.add(Calendar.DAY_OF_MONTH, intAmountMedicine);
                Date dateRef = calendarCurrent.getTime();
                String stringDateRef = myData.string_ddMMyyyy_ConvertedFromSpecificDate(dateRef);

                //string4 คือ Which_Date_D
                string4 = string4.concat(":");
                string4 = string4.concat(stringDateRef);

                Toast.makeText(getBaseContext(),string4,Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(PopUpAddAmountOCs.this, AddMedicine2Activity.class);
                intent.putExtra("Med_id",string1);
                intent.putExtra("Trade_name", string2);
                intent.putExtra("Generic_line", string3);
                intent.putExtra("Amount_tablet", string15);
                intent.putExtra("EA", string16);
                intent.putExtra("Which_Date_D", string4);
                intent.putExtra("Appearance", string5);
                intent.putExtra("Pharmaco", string6);
                intent.putExtra("T1",string7);
                intent.putExtra("T2",string8);
                intent.putExtra("T3",string9);
                intent.putExtra("T4",string10);
                intent.putExtra("T5",string11);
                intent.putExtra("T6",string12);
                intent.putExtra("T7",string13);
                intent.putExtra("T8",string14);
                intent.putExtra("TimesPerDay", string17);

                startActivity(intent);
                finish();


            }
        });

        //Click Cancel
        textViewCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });


    }

    private void showView() {

        TextView textViewHeading1 = (TextView) findViewById(R.id.textView196);
        textViewHeading1.setText("โปรดระบุว่ายาที่ท่านเลือกวันนี้ ท่านรับประทานเป็นเม็ดที่เท่าไหร่ของแผง" +
                "\nเช่น\n    ถ้ารับประทานเป็นเม็ดแรกของแผงให้ใส่เลข 1, ท่านรับประทานไปแล้ว 3 เม็ด " +
                "วันนี้เป็นวันที่ 4 ให้ใส่เลข 4 เป็นต้น");

        editTextAmountMedicine.setText("1");


    }


    private void clickCalculateAmountMedicine() {

        integerAmountMedicine = Integer.parseInt(editTextAmountMedicine.getText().toString());

        buttonAdd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                integerAmountMedicine = integerAmountMedicine + 1;
                int iActivePill = Integer.parseInt(stringActivePill);
                int iPlacebo = Integer.parseInt(stringPlacebo);
                int iResult = iActivePill + iPlacebo;
                if (integerAmountMedicine >= iResult) {
                    integerAmountMedicine = iResult;
                }
                editTextAmountMedicine.setText(Integer.toString(integerAmountMedicine));

            }
        });

        buttonAdd10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                integerAmountMedicine = integerAmountMedicine + 10;
                int iActivePill = Integer.parseInt(stringActivePill);
                int iPlacebo = Integer.parseInt(stringPlacebo);
                int iResult = iActivePill + iPlacebo;
                if (integerAmountMedicine >= iResult) {
                    integerAmountMedicine = iResult;
                }
                editTextAmountMedicine.setText(Integer.toString(integerAmountMedicine));
            }
        });

        buttonMinus10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                integerAmountMedicine = integerAmountMedicine - 10;
                if (integerAmountMedicine <= 1) {
                    integerAmountMedicine = 1;
                }
                editTextAmountMedicine.setText(Integer.toString(integerAmountMedicine));
            }
        });

        buttonMinus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                integerAmountMedicine = integerAmountMedicine - 1;
                if (integerAmountMedicine <= 1) {
                    integerAmountMedicine = 1;
                }
                editTextAmountMedicine.setText(Integer.toString(integerAmountMedicine));
            }
        });

    }




    private void receiveIntent() {

        stringActivePill = getIntent().getStringExtra("ActivePill");
        stringPlacebo = getIntent().getStringExtra("Placebo");

        // เรียงตาม AddMedicineActivity จะได้ง่ายๆเวลาลอกไป
        string1 = getIntent().getStringExtra("Med_id");
        string2 = getIntent().getStringExtra("Trade_name");
        string3 = getIntent().getStringExtra("Generic_line");
        string15 = getIntent().getStringExtra("Amount_tablet");
        string16 = getIntent().getStringExtra("EA");
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
        string17 = getIntent().getStringExtra("TimesPerDay");



    }

    private void bindWidget() {

        editTextAmountMedicine = (EditText) findViewById(R.id.editTextOCsAmountMedicine);
        buttonAdd10 = (Button) findViewById(R.id.buttonOCsAdd10);
        buttonAdd1 = (Button) findViewById(R.id.buttonOCsAdd1);
        buttonMinus1 = (Button) findViewById(R.id.buttonOCsMinus1);
        buttonMinus10 = (Button) findViewById(R.id.buttonOCsMinus10);
        textViewOK = (TextView) findViewById(R.id.textView199);  //ตกลง
        textViewCancel = (TextView) findViewById(R.id.textView197); //ยกเลิก
        
    }

    private void displayMetrics() {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        getWindow().setLayout((int) (width*.85),(int) (height*.5));
    }
}
