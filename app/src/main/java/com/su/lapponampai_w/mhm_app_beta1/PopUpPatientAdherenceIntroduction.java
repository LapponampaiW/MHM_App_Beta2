package com.su.lapponampai_w.mhm_app_beta1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

public class PopUpPatientAdherenceIntroduction extends AppCompatActivity {

    TextView textViewBackToPatientAdherenceActivity,textViewStar_None,textViewStar_Gray,
            textViewStar_Gold,textViewGoodAdherence,textViewBadAdherence,textViewSoSoAdherence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_patient_adherence_introduction);

        bindWidget();

        displayMetrics();

        backToPatientAdherenceActivity();

        setTextView();

    }

    private void setTextView() {

        textViewStar_None.setText("ยาที่ยังไม่ได้รับประทาน");
        textViewStar_Gray.setText("รับประทานยาแล้วแต่ไม่ต้องตาม\nข้อกำหนด");
        textViewStar_Gold.setText("รับประทานยาแล้ว");
        textViewGoodAdherence.setText("ท่านรับประทานยาครบถ้วน");
        textViewSoSoAdherence.setText("ท่านรับประทานยาครบถ้วน\nแต่มียาที่ไม่ทานตามข้อกำหนด");
        textViewBadAdherence.setText("ท่านรับประทานยาไม่ครบถ้วน");


    }

    private void backToPatientAdherenceActivity() {

        textViewBackToPatientAdherenceActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void displayMetrics() {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        getWindow().setLayout((int) (width*.85),(int) (height*.75));


    }

    private void bindWidget() {
        textViewBackToPatientAdherenceActivity = (TextView) findViewById(R.id.textView106);
        textViewStar_None = (TextView) findViewById(R.id.textView136);
        textViewStar_Gray = (TextView) findViewById(R.id.textView137);
        textViewStar_Gold = (TextView) findViewById(R.id.textView138);
        textViewGoodAdherence = (TextView) findViewById(R.id.textView139);
        textViewSoSoAdherence = (TextView) findViewById(R.id.textView140);
        textViewBadAdherence = (TextView) findViewById(R.id.textView141);

    }
}
