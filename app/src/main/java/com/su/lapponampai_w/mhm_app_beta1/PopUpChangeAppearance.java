package com.su.lapponampai_w.mhm_app_beta1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PopUpChangeAppearance extends AppCompatActivity {


    //Explicit
    ImageView imageView1, imageView2, imageView3,imageViewFinal;
    TextView textViewStep2Tablet, textViewStep3Tablet,textViewStep4;
    TextView textViewCancel, textViewOK;
    ListView listViewTablet2,listViewTablet3;
    int[] intsImageWhiteTablet,intsImageSelectedTablet;
    String[] stringsWhiteTablet, stringsTotalTablet,stringsSelectedTablet;
    String stringProcessCompleted;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_change_appearance);

        bindWidget();

        displayMetrics();

        showView();

        setValueInAllAdaptor(); //Setค่าของ Adaptor ทั้งหมดลงใน Adaptor

        clickImageView(); //คลิก ImageView เปลี่ยนสี

        //clickListViewAdaptor();

        clickOKCancelbutton();


    }

    private void clickOKCancelbutton() {

        textViewCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        textViewOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (stringProcessCompleted.equals("N")) {

                }


            }
        });



    }

    private void clickListViewAdaptor() {

        listViewTablet2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                String[] stringsTotalTablet1 = {"img0101", "img0102", "img0104", "img0201",
                        "img0202", "img0203", "img0204", "img0301", "img0302", "img0303", "img0304",
                        "img0309", "img0312", "img0315", "img0501", "img0511", "img0601", "img0602",
                        "img0603", "img0604", "img0607", "img0609", "img0611", "img0612", "img0615",
                        "img0701", "img0702", "img0703", "img0711", "img0712", "img0714", "img0801",
                        "img0802", "img0803", "img0901", "img0902", "img0903", "img0912", "img1002",
                        "img1103"};


                stringsTotalTablet = stringsTotalTablet1;


                String sSubstring = stringsWhiteTablet[position].substring(3, 5);

                //Toast.makeText(getBaseContext(), sSubstring, Toast.LENGTH_SHORT).show();

                ArrayList<String> stringTablet3ArrayList = new ArrayList<String>();
                int iIndex = 0;
                for (int y = 0; y < stringsTotalTablet.length; y++) {
                    if (stringsTotalTablet[y].substring(3, 5).equals(sSubstring)) {
                        stringTablet3ArrayList.add(iIndex,stringsTotalTablet[y]);
                        iIndex = iIndex + 1;

                    }
                }

                stringsSelectedTablet = new String[stringTablet3ArrayList.size()];
                stringsSelectedTablet = stringTablet3ArrayList.toArray(stringsSelectedTablet);
                MyData myData = new MyData();
                intsImageSelectedTablet = myData.translate_Appearance(stringsSelectedTablet);

                MyAdaptorChangeAppearance myAdaptorChangeAppearance = new MyAdaptorChangeAppearance(getBaseContext(), intsImageSelectedTablet);
                listViewTablet3.setAdapter(myAdaptorChangeAppearance);
                listViewTablet3.setVisibility(View.VISIBLE);
                textViewStep3Tablet.setVisibility(View.VISIBLE);

            }
        });


    }

    private void setValueInAllAdaptor() {

        //Step 2 Tablet
        String[] stringsWhiteTablet1 = {"img0101", "img0201", "img0301", "img0501", "img0601",
                "img0701", "img0801", "img0901"};

        stringsWhiteTablet = stringsWhiteTablet1;

        final MyData myData = new MyData();

        intsImageWhiteTablet = myData.translate_Appearance(stringsWhiteTablet);

        MyAdaptorChangeAppearance myAdaptorChangeAppearance = new MyAdaptorChangeAppearance(getBaseContext(), intsImageWhiteTablet);
        listViewTablet2.setAdapter(myAdaptorChangeAppearance);


        listViewTablet2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {




                String[] stringsTotalTablet1 = {"img0101", "img0102", "img0104", "img0201",
                        "img0202", "img0203", "img0204", "img0301", "img0302", "img0303", "img0304",
                        "img0309", "img0312", "img0315", "img0501", "img0511", "img0601", "img0602",
                        "img0603", "img0604", "img0607", "img0609", "img0611", "img0612", "img0615",
                        "img0701", "img0702", "img0703", "img0711", "img0712", "img0714", "img0801",
                        "img0802", "img0803", "img0901", "img0902", "img0903", "img0912", "img1002",
                        "img1103"};


                stringsTotalTablet = stringsTotalTablet1;


                String sSubstring = stringsWhiteTablet[position].substring(3, 5);

                //Toast.makeText(getBaseContext(), sSubstring, Toast.LENGTH_SHORT).show();

                ArrayList<String> stringTablet3ArrayList = new ArrayList<String>();
                int iIndex = 0;
                for (int y = 0; y < stringsTotalTablet.length; y++) {
                    if (stringsTotalTablet[y].substring(3, 5).equals(sSubstring)) {
                        stringTablet3ArrayList.add(iIndex,stringsTotalTablet[y]);
                        iIndex = iIndex + 1;

                    }
                }

                stringsSelectedTablet = new String[stringTablet3ArrayList.size()];
                stringsSelectedTablet = stringTablet3ArrayList.toArray(stringsSelectedTablet);
                MyData myData = new MyData();
                intsImageSelectedTablet = myData.translate_Appearance(stringsSelectedTablet);

                MyAdaptorChangeAppearance myAdaptorChangeAppearance = new MyAdaptorChangeAppearance(getBaseContext(), intsImageSelectedTablet);
                listViewTablet3.setAdapter(myAdaptorChangeAppearance);
                listViewTablet3.setVisibility(View.VISIBLE);
                textViewStep3Tablet.setVisibility(View.VISIBLE);
                textViewStep4.setVisibility(View.INVISIBLE);
                imageViewFinal.setVisibility(View.INVISIBLE);

            }
        });


        //คลิกขั้นตอนที่ 3
        listViewTablet3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                int[] intsFinalImage = myData.translate_Appearance(stringsSelectedTablet);

                imageViewFinal.setImageResource(intsFinalImage[position]);
                textViewStep4.setVisibility(View.VISIBLE);
                imageViewFinal.setVisibility(View.VISIBLE);
            }
        });



    }

    private void clickImageView() {

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imageView1.setImageResource(R.drawable.icon_tablet2);
                imageView2.setImageResource(R.drawable.icon_capsule1);

                listViewTablet2.setVisibility(View.VISIBLE);
                textViewStep2Tablet.setVisibility(View.VISIBLE);
                textViewStep3Tablet.setVisibility(View.INVISIBLE);
                listViewTablet3.setVisibility(View.INVISIBLE);
                textViewStep4.setVisibility(View.INVISIBLE);
                imageViewFinal.setVisibility(View.INVISIBLE);

            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imageView1.setImageResource(R.drawable.icon_tablet1);
                imageView2.setImageResource(R.drawable.icon_capsule2);

                listViewTablet2.setVisibility(View.INVISIBLE);
                textViewStep2Tablet.setVisibility(View.INVISIBLE);
                textViewStep3Tablet.setVisibility(View.INVISIBLE);
                listViewTablet3.setVisibility(View.INVISIBLE);
                textViewStep4.setVisibility(View.INVISIBLE);
                imageViewFinal.setVisibility(View.INVISIBLE);

            }
        });

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "ยังไม่มีฟังก์ชั่นยาน้ำในเว่อร์ชั่นนี้", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void showView() {
        textViewStep2Tablet.setText("ขั้นตอนที่ 2 :\nเลือกรูปแบบยาเม็ด");
        textViewStep3Tablet.setText("ขั้นตอนที่ 3 :\nเลือกเม็ดยาที่ต้องการ");
        textViewStep4.setText("ขั้นตอนที่ 4\nเสร็จสิ้นการเลือกรูปเสมือนเม็ดยา\nกด 'เปลี่ยนรูป' เพื่อจบการทำงาน");


        listViewTablet2.setVisibility(View.INVISIBLE);
        textViewStep2Tablet.setVisibility(View.INVISIBLE);
        textViewStep3Tablet.setVisibility(View.INVISIBLE);
        listViewTablet3.setVisibility(View.INVISIBLE);
        textViewStep4.setVisibility(View.INVISIBLE);
        imageViewFinal.setVisibility(View.INVISIBLE);

        stringProcessCompleted = "N";


    }

    private void bindWidget() {

        imageView1 = (ImageView) findViewById(R.id.imageView18);
        imageView2 = (ImageView) findViewById(R.id.imageView19);
        imageView3 = (ImageView) findViewById(R.id.imageView20);
        imageViewFinal = (ImageView) findViewById(R.id.imageView17);

        textViewStep2Tablet = (TextView) findViewById(R.id.textView209);
        textViewStep3Tablet = (TextView) findViewById(R.id.textView211);
        textViewStep4 = (TextView) findViewById(R.id.textView215);

        textViewOK = (TextView) findViewById(R.id.textView213);
        textViewCancel = (TextView) findViewById(R.id.textView212);

        listViewTablet2 = (ListView) findViewById(R.id.listViewTablet2);
        listViewTablet3 = (ListView) findViewById(R.id.listViewTablet3);

    }

    private void displayMetrics() {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        getWindow().setLayout((int) (width * .9), (int) (height * .9));
    }
}
