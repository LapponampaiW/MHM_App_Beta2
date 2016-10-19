package com.su.lapponampai_w.mhm_app_beta1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.EditText;
import android.widget.TextView;

public class PopUpChangeTradeGenericName extends AppCompatActivity {

    //Explicit
    TextView textViewTradeName, textViewGenericName,textViewOK,textViewCancel;
    EditText editTextTradeName, editTextGenericName;
    String stringTradeName, stringGenericName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_change_trade_generic_name);

        bindWidget();
        displayMetrics();

        recieveIntent();

        showView();

        clickOKCancel();




    }

    private void clickOKCancel() {



    }

    private void recieveIntent() {

        stringTradeName = getIntent().getStringExtra("sendTradeName");
        stringGenericName = getIntent().getStringExtra("sendGenericName");

    }

    private void showView() {

        textViewTradeName.setText("ชื่อการค้า :");
        textViewGenericName.setText("ชื่อสามัญทางยา :");
        editTextTradeName.setText(stringTradeName);
        editTextGenericName.setText(stringGenericName);

    }

    private void displayMetrics() {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        getWindow().setLayout((int) (width*.85),(int) (height*.4));
    }

    private void bindWidget() {
        textViewTradeName = (TextView) findViewById(R.id.textView179);
        textViewGenericName = (TextView) findViewById(R.id.textView181);
        editTextTradeName = (EditText) findViewById(R.id.editText18);
        editTextGenericName = (EditText) findViewById(R.id.editText19);
        textViewCancel = (TextView) findViewById(R.id.textView182);
        textViewOK = (TextView) findViewById(R.id.textView184);

    }
}
