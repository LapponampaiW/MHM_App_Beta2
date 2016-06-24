package com.su.lapponampai_w.mhm_app_beta1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PopUpAddAmountMedicine extends AppCompatActivity {

    //Explicit
    private Button buttonAdd10, buttonAdd1, buttonMinus1, buttonMinus10;
    private EditText editTextAmountMedicine;
    private Integer integerAmountMedicine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_add_amount_medicine);

        bindWidget();

        displayMetrics();

        showView();

        clickCalculateAmountMedicine();



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

    }

    private void displayMetrics() {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        getWindow().setLayout((int) (width*.8),(int) (height*.4));
    }
}
