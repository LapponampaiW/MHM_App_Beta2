package com.su.lapponampai_w.mhm_app_beta1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

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
    private String stringStartPill,stringStartDate,stringActivePill,stringPlacebo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_add_amount_ocs);

        bindWidget();

        displayMetrics();

        receiveIntent();

        showView();

        clickCalculateAmountMedicine();
        
        
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
