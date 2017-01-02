package com.su.lapponampai_w.mhm_app_beta1;

import android.graphics.Color;
import android.support.annotation.BoolRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class LabBarChartActivity extends AppCompatActivity {


    Spinner spinner;
    TextView textViewHeading,textViewTailing,textViewComment;
    BarChart barChart;
    BarChart barChart2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_bar_chart);

        bindWidget();

        setView();

        setAndclickSpinner();

        goBackToMain();


    }

    private void setView() {
        barChart.setVisibility(View.INVISIBLE);
        barChart2.setVisibility(View.INVISIBLE);
        textViewHeading.setText("");
        textViewHeading.setVisibility(View.INVISIBLE);
        textViewComment.setVisibility(View.INVISIBLE);

    }

    private void goBackToMain() {

        textViewTailing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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

                    if (textViewHeading.getText().toString().trim().equals("")) {
                        // IsEmpty
                    } else {
                        ((TextView)view).setText(textViewHeading.getText().toString());
                    }
                    //((TextView)view).setText(null); //สำคัญมากได้แล้ว

                } else if (strTextSpinnerLab[position].equals(strTextSpinnerLab[1])) {

                    textViewHeading.setText(strTextSpinnerLab[1]);
                    createChart(strTextSpinnerLab[1]);

                } else if (strTextSpinnerLab[position].equals(strTextSpinnerLab[2])) {

                    textViewHeading.setText(strTextSpinnerLab[2]);
                    createChart(strTextSpinnerLab[2]);
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
        final String[] strUnitLab = labActivity.stringsUnitLab;

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

                String[] xAxisChart = new String[dateArrayList.size()];
                xAxisChart = dateArrayList.toArray(xAxisChart);
                String[] yAxisChart = new String[xAxisChart.length];

                for(int i =0;i<xAxisChart.length;i++) {
                    for(int z=0;z<stringsChart[0].length;z++) {
                        if (xAxisChart[i].equals(stringsChart[0][z])) {
                            yAxisChart[i] = stringsChart[1][z];
                        }
                    }
                } //for

                //test
                for(int i = 0;i<yStrings.length;i++) {
                    Log.d("291259V1","y : " + yAxisChart[i]);
                    Log.d("291259V1","x : " + xAxisChart[i]);
                }

                //ถูกต้องถึงตรงนี้แล้ว

                int iArray = yAxisChart.length;
                ArrayList<BarEntry> barEntries = new ArrayList<>();
                BarDataSet barDataSet;
                String[] xAxisName;
                if (iArray >= 7) {
                    barEntries.add(new BarEntry(7f, Float.parseFloat(yAxisChart[iArray - 1])));
                    barEntries.add(new BarEntry(6f, Float.parseFloat(yAxisChart[iArray - 2])));
                    barEntries.add(new BarEntry(5f, Float.parseFloat(yAxisChart[iArray - 3])));
                    barEntries.add(new BarEntry(4f, Float.parseFloat(yAxisChart[iArray - 4])));
                    barEntries.add(new BarEntry(3f, Float.parseFloat(yAxisChart[iArray - 5])));
                    barEntries.add(new BarEntry(2f, Float.parseFloat(yAxisChart[iArray - 6])));
                    barEntries.add(new BarEntry(1f, Float.parseFloat(yAxisChart[iArray - 7])));
                    barDataSet = new BarDataSet(barEntries, sLabName + "(" + strUnitLab[iSpecificColumnChoose - 3] + ")"); //เดี๋ยวต้องแก้


                    xAxisName = new String[7];
                    int iCount = yAxisChart.length;
                    for (int i = 6; i >= 0; i--) { //ทำ 7 loop
                        xAxisName[i] = xAxisChart[iCount - 1];
                        xAxisName[i] = xAxisName[i].substring(0, 6) + xAxisName[i].substring(8);
                        iCount = iCount - 1;
                        Log.d("291259V1", "xAxisName[i] : "+ i +":" + xAxisName[i]);
                    } //for


                    final String[] xNameLabel = xAxisName;


                    IAxisValueFormatter formatter = new IAxisValueFormatter() {
                        @Override
                        public String getFormattedValue(float value, AxisBase axis) {

                            return xNameLabel[(int) value - 1];
                        }
                    };

                    XAxis xAxis = barChart.getXAxis();
                    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                    xAxis.setTextSize(9f);
                    xAxis.setTextColor(Color.RED);
                    xAxis.setDrawAxisLine(true);
                    xAxis.setDrawGridLines(false);
                    xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
                    xAxis.setValueFormatter(formatter);


                    BarData data = new BarData(barDataSet);
                    data.setBarWidth(0.7f); // set custom bar width
                    barChart.setData(data);
                    barChart.setFitBars(true); // make the x-axis fit exactly all bars
                    barChart.invalidate(); // refresh

                    barChart.setVisibility(View.VISIBLE);
                    barChart2.setVisibility(View.INVISIBLE);
                    textViewHeading.setVisibility(View.VISIBLE);

                } else {


                    //Toast.makeText(getBaseContext(),"Array มีค่าน้อยกว่า 7 ค่า",Toast.LENGTH_SHORT).show();
                    xAxisName = new String[iArray];
                    //Toast.makeText(getBaseContext(),Integer.toString(xAxisName.length),Toast.LENGTH_SHORT).show(); //2
                    for(int i = iArray;i>=1;i = i-1) {
                        Log.d("301259V1", "เข้า loop ค่า i = " + i);
                        barEntries.add(new BarEntry(i, Float.parseFloat(yAxisChart[i - 1])));
                        xAxisName[i-1] = xAxisChart[i-1];
                        xAxisName[i-1] = xAxisName[i-1].substring(0, 6) + xAxisName[i-1].substring(8);
                    } //for
                    barDataSet = new BarDataSet(barEntries, sLabName + "(" + strUnitLab[iSpecificColumnChoose - 3] + ")"); //เดี๋ยวต้องแก้

                    final String[] xNameLabel = xAxisName;

                    for(int i = 0;i<xAxisName.length;i++) {
                        Log.d("31122559V1", "ค่า xAxisName : " + xAxisName[i]);
                    }


                    IAxisValueFormatter valueFormatter = new IAxisValueFormatter() {
                        @Override
                        public String getFormattedValue(float value, AxisBase axis) {
                            return xNameLabel[(int) value - 1];
                        }
                    };

                    XAxis xAxis = barChart2.getXAxis();
                    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                    xAxis.setTextSize(9f);
                    xAxis.setTextColor(Color.RED);
                    xAxis.setDrawAxisLine(true);
                    xAxis.setDrawGridLines(false);
                    xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
                    xAxis.setValueFormatter(valueFormatter);



                    BarData data = new BarData(barDataSet);
                    data.setBarWidth(0.7f); // set custom bar width
                    barChart2.setData(data);
                    barChart2.setFitBars(true); // make the x-axis fit exactly all bars
                    barChart2.invalidate(); // refresh

                    barChart.setVisibility(View.INVISIBLE);
                    barChart2.setVisibility(View.VISIBLE);
                    textViewHeading.setVisibility(View.VISIBLE);
                } //elseif


                /*
                IAxisValueFormatter formatter = new IAxisValueFormatter() {
                    @Override
                    public String getFormattedValue(float value, AxisBase axis) {
                        return xAxisName[(int) value - 1];
                    }
                };

                XAxis xAxis = barChart.getXAxis();
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                xAxis.setTextSize(9f);
                xAxis.setTextColor(Color.RED);
                xAxis.setDrawAxisLine(true);
                xAxis.setDrawGridLines(false);
                xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
                xAxis.setValueFormatter(formatter);

                BarData data = new BarData(barDataSet);
                data.setBarWidth(0.7f); // set custom bar width
                barChart.setData(data);
                barChart.setFitBars(true); // make the x-axis fit exactly all bars
                barChart.invalidate(); // refresh
                */


            }



        } // first if




    }

    private void bindWidget() {

        //textViewSpinner = (TextView) findViewById(R.id.textView132);
        spinner = (Spinner) findViewById(R.id.spinner6);
        textViewHeading = (TextView) findViewById(R.id.textView133);
        barChart = (BarChart) findViewById(R.id.chart);
        barChart2 = (BarChart) findViewById(R.id.chart2);
        textViewTailing = (TextView) findViewById(R.id.textView106);
        textViewComment = (TextView) findViewById(R.id.textView132);


    }
}
