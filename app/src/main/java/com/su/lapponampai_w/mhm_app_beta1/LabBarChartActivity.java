package com.su.lapponampai_w.mhm_app_beta1;

import android.support.annotation.BoolRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
                    createChart(strTextSpinnerLab[1]);

                } else if (strTextSpinnerLab[position].equals(strTextSpinnerLab[2])) {

                    textViewHeading.setText(strTextSpinnerLab[2]);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    private void createChart(String sLabName) {
        Log.d("291259V1","เข้า createChart");

        MyManage myManage = new MyManage(this);

        LabActivity labActivity = new LabActivity();
        final String[] strLabHeading = labActivity.stringsLabHeading;

        int iSpecificColumnChoose = 100;
        for(int i = 0;i<strLabHeading.length;i++) {
            if (strLabHeading[i].equals(sLabName)) {
                iSpecificColumnChoose = i + 3;
            } else {
                // Need empty
            }
        }


        final String[][] stringsLab = {myManage.readAlllabTABLE(0),myManage.readAlllabTABLE(1),
                myManage.readAlllabTABLE(2),myManage.readAlllabTABLE(3), myManage.readAlllabTABLE(4),
                myManage.readAlllabTABLE(5), myManage.readAlllabTABLE(6), myManage.readAlllabTABLE(7),
                myManage.readAlllabTABLE(8), myManage.readAlllabTABLE(9), myManage.readAlllabTABLE(10)
                , myManage.readAlllabTABLE(11), myManage.readAlllabTABLE(12), myManage.readAlllabTABLE(13)
                , myManage.readAlllabTABLE(14)};


        ArrayList<String> yAxisArrayList = new ArrayList<String>();
        ArrayList<String> dateArrayList = new ArrayList<String>();
        int iIndex = 0;
        Boolean aBoolean = true;
        if (!stringsLab[0][0].equals("")) {
            for(int i = 0;i<stringsLab[0].length;i++) { // หา row
                if (!stringsLab[iSpecificColumnChoose][i].equals("")) { //ถ้ามีค่าตัวเลขอยู่
                    yAxisArrayList.add(iIndex, stringsLab[iSpecificColumnChoose][i]);
                    dateArrayList.add(iIndex, stringsLab[2][i]);
                    iIndex = iIndex + 1;
                    aBoolean = false;
                } //if
            } //for

            String[] yStrings = new String[yAxisArrayList.size()];
            String[] xStrings = new String[dateArrayList.size()];
            yStrings = yAxisArrayList.toArray(yStrings);
            xStrings = dateArrayList.toArray(xStrings);

            //test
            for(int i = 0;i<yStrings.length;i++) {
                Log.d("291259V1","y : " + yStrings[i]);
                Log.d("291259V1","x : " + xStrings[i]);

            }

            String[][] stringsChart = {xStrings, yStrings};

            if (!aBoolean) {
                //ถ้ามีค่าของ ตัวนั้นๆ ใน LabActivity ได้ตำแหน่งของ row มาแล้ว
                Collections.sort(dateArrayList, new Comparator<String>() {
                    DateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

                    @Override
                    public int compare(String o1, String o2) {
                        try {
                            return simpleDateFormat.parse(o1).compareTo(simpleDateFormat.parse(o2));
                        } catch (ParseException e) {
                            throw new IllegalArgumentException(e);
                        }
                    }
                }); //Collection

                String[] xAxis = new String[dateArrayList.size()];
                xAxis = dateArrayList.toArray(xAxis);
                String[] yAxis = new String[xAxis.length];

                for(int i =0;i<xAxis.length;i++) {
                    for(int z=0;z<stringsChart[0].length;z++) {
                        if (xAxis[i].equals(stringsChart[0][z])) {
                            yAxis[i] = stringsChart[1][z];
                        }
                    }
                } //for

                //test
                for(int i = 0;i<yStrings.length;i++) {
                    Log.d("291259V1","y : " + yAxis[i]);
                    Log.d("291259V1","x : " + xAxis[i]);

                }

                //ถูกต้องถึงตรงนี้แล้ว





            }



        } // first if




    }

    private void bindWidget() {

        //textViewSpinner = (TextView) findViewById(R.id.textView132);
        spinner = (Spinner) findViewById(R.id.spinner6);
        textViewHeading = (TextView) findViewById(R.id.textView133);

    }
}
