package com.su.lapponampai_w.mhm_app_beta1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class TakeSkipMedicineActivity extends AppCompatActivity {

    String string1,string2,string3, string4;
    TextView textView1,textView2, textView3;
    ImageView imageView;
    int[] intsIndex;
    String[] stringsIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_skip_medicine);

        displayMetrics();

        bindWidget();

        receiveIntent();

        showView();


    }

    private void showView() {

        MyData myData = new MyData();
        stringsIndex = new String[string2.length()];
        stringsIndex[0] = string2;
        Log.d("abc",stringsIndex[0]);
        intsIndex = myData.translate_Small_Appearance(stringsIndex);


        textView1.setText("ชื่อยา : " + string1);
        textView2.setText("จำนวนที่รับประทาน : " + string3);
        textView3.setText("เวลาที่รับประทาน : " + string4);
        imageView.setImageResource(intsIndex[0]);
    }

    private void bindWidget() {
        textView1 = (TextView) findViewById(R.id.textViewTSMHeadline);
        textView2 = (TextView) findViewById(R.id.textViewTSML1);
        textView3 = (TextView) findViewById(R.id.textViewTSML2);
        imageView = (ImageView) findViewById(R.id.imageViewTSM);
    }

    private void receiveIntent() {
        string1 = getIntent().getStringExtra("Tradename");
        string2 = getIntent().getStringExtra("Appearance");
        string3 = getIntent().getStringExtra("AmountTablet");
        string4 = getIntent().getStringExtra("TimeRef");

    }

    private void displayMetrics() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        getWindow().setLayout((int) (width*.8),(int) (height*.4));
    }
}
