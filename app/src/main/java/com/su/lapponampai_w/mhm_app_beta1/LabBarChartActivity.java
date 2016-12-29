package com.su.lapponampai_w.mhm_app_beta1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class LabBarChartActivity extends AppCompatActivity {


    Spinner spinner;
    TextView textViewHeading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_bar_chart);

        bindWidget();

        setAndclickSpinner();

    }

    private void setAndclickSpinner() {

        MyHeadingDetail myHeadingDetail = new MyHeadingDetail(getBaseContext());
        myHeadingDetail.spinnerLabSetup(getBaseContext(),spinner);
        final String[] strTextSpinnerLab = myHeadingDetail.strTextSpinnerLab;

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (strTextSpinnerLab[position].equals(strTextSpinnerLab[0])) {
                    //need IsEmpty
                } else if (strTextSpinnerLab[position].equals(strTextSpinnerLab[1])) {

                    textViewHeading.setText(strTextSpinnerLab[1]);

                } else if (strTextSpinnerLab[position].equals(strTextSpinnerLab[2])) {

                    textViewHeading.setText(strTextSpinnerLab[2]);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    private void bindWidget() {

        //textViewSpinner = (TextView) findViewById(R.id.textView132);
        spinner = (Spinner) findViewById(R.id.spinner6);
        textViewHeading = (TextView) findViewById(R.id.textView133);

    }
}
