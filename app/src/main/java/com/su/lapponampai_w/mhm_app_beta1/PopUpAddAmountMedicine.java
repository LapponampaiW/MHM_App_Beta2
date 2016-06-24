package com.su.lapponampai_w.mhm_app_beta1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PopUpAddAmountMedicine extends AppCompatActivity {

    //Explicit
    private Button buttonAdd10, buttonAdd1, buttonMinus1, buttonMinus10;
    private String string_Main_id;
    private EditText editTextAmountMedicine;
    private Integer integerAmountMedicine;
    private Double doubleAmountMedicine;
    private TextView textViewOK, textViewCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_add_amount_medicine);


        bindWidget();

        displayMetrics();

        receiveIntent();

        showView();

        clickCalculateAmountMedicine();


        //Click button(OK or Cancel)
        clickOKCancel();

    }

    private void receiveIntent() {

        string_Main_id = getIntent().getStringExtra("MedicationDetailActivity_id"); //เอาค่า Main_id มา
        Log.d("MedicationAdd", string_Main_id);

    }

    private void clickOKCancel() {
        final MyData myData = new MyData();
        final MyManage myManage = new MyManage(this);

        textViewOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doubleAmountMedicine = Double.parseDouble(editTextAmountMedicine.getText().toString());

                if (doubleAmountMedicine == 0) {
                    Toast.makeText(PopUpAddAmountMedicine.this, "ไม่สามารถบันทึกจำนวนค่า 0 ได้", Toast.LENGTH_SHORT).show();
                } else {

                    MyManage myManage = new MyManage(PopUpAddAmountMedicine.this);
                    String[][] stringsaddUseTABLE = {myManage.readAlladdUseTABLE(0),
                            myManage.readAlladdUseTABLE(1),myManage.readAlladdUseTABLE(2),
                            myManage.readAlladdUseTABLE(3),myManage.readAlladdUseTABLE(4)};

                    String[][] stringstotalAmountTABLE = {myManage.readAlltotalAmountTABLE(0),
                            myManage.readAlltotalAmountTABLE(1),myManage.readAlltotalAmountTABLE(2),
                            myManage.readAlltotalAmountTABLE(3)};

                    String sDate = myData.currentDay();

                    myManage.addValueTo_addUseTABLE(string_Main_id,"Add",doubleAmountMedicine,sDate);
                    Toast.makeText(PopUpAddAmountMedicine.this,"addValueTo_addUseTABLE",Toast.LENGTH_SHORT).show();

                    // ทำการแทนค่าในเตัว totalAmountTABLE


                }

            }
        });
    }

    private void showView() {
        editTextAmountMedicine.setText("0");
    }

    private void clickCalculateAmountMedicine() {

        integerAmountMedicine = Integer.parseInt(editTextAmountMedicine.getText().toString());

        buttonAdd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                integerAmountMedicine = integerAmountMedicine + 1;
                editTextAmountMedicine.setText(Integer.toString(integerAmountMedicine));
            }
        });

        buttonAdd10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                integerAmountMedicine = integerAmountMedicine + 10;
                editTextAmountMedicine.setText(Integer.toString(integerAmountMedicine));
            }
        });

        buttonMinus10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                integerAmountMedicine = integerAmountMedicine - 10;
                if (integerAmountMedicine <= 0) {
                    integerAmountMedicine = 0;
                }
                editTextAmountMedicine.setText(Integer.toString(integerAmountMedicine));
            }
        });

        buttonMinus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                integerAmountMedicine = integerAmountMedicine - 1;
                if (integerAmountMedicine <= 0) {
                    integerAmountMedicine = 0;
                }
                editTextAmountMedicine.setText(Integer.toString(integerAmountMedicine));
            }
        });

        
    }

    private void bindWidget() {

        editTextAmountMedicine = (EditText) findViewById(R.id.editTextAmountMedicine);
        buttonAdd10 = (Button) findViewById(R.id.buttonAdd10);
        buttonAdd1 = (Button) findViewById(R.id.buttonAdd1);
        buttonMinus1 = (Button) findViewById(R.id.buttonMinus1);
        buttonMinus10 = (Button) findViewById(R.id.buttonMinus10);
        textViewOK = (TextView) findViewById(R.id.textView86);  //ตกลง
        textViewCancel = (TextView) findViewById(R.id.textView85); //ยกเลิก

    }

    private void displayMetrics() {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        getWindow().setLayout((int) (width*.8),(int) (height*.4));
    }
}
