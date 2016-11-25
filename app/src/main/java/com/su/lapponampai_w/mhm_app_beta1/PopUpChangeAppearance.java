package com.su.lapponampai_w.mhm_app_beta1;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.su.lapponampai_w.mhm_app_beta1.AddMedicine2Activity.string1;
import static com.su.lapponampai_w.mhm_app_beta1.AddMedicine2Activity.string10;
import static com.su.lapponampai_w.mhm_app_beta1.AddMedicine2Activity.string11;
import static com.su.lapponampai_w.mhm_app_beta1.AddMedicine2Activity.string12;
import static com.su.lapponampai_w.mhm_app_beta1.AddMedicine2Activity.string13;
import static com.su.lapponampai_w.mhm_app_beta1.AddMedicine2Activity.string14;
import static com.su.lapponampai_w.mhm_app_beta1.AddMedicine2Activity.string15;
import static com.su.lapponampai_w.mhm_app_beta1.AddMedicine2Activity.string16;
import static com.su.lapponampai_w.mhm_app_beta1.AddMedicine2Activity.string17;
import static com.su.lapponampai_w.mhm_app_beta1.AddMedicine2Activity.string2;
import static com.su.lapponampai_w.mhm_app_beta1.AddMedicine2Activity.string3;
import static com.su.lapponampai_w.mhm_app_beta1.AddMedicine2Activity.string4;
import static com.su.lapponampai_w.mhm_app_beta1.AddMedicine2Activity.string5;
import static com.su.lapponampai_w.mhm_app_beta1.AddMedicine2Activity.string6;
import static com.su.lapponampai_w.mhm_app_beta1.AddMedicine2Activity.string7;
import static com.su.lapponampai_w.mhm_app_beta1.AddMedicine2Activity.string8;
import static com.su.lapponampai_w.mhm_app_beta1.AddMedicine2Activity.string9;

public class PopUpChangeAppearance extends AppCompatActivity {


    //Explicit
    ImageView imageView1, imageView2, imageView3,imageViewFinal;
    TextView textViewStep2Tablet, textViewStep3Tablet,textViewStep4;
    TextView textViewCancel, textViewOK;
    ListView listViewTablet2,listViewTablet3;
    int[] intsImageWhiteTablet,intsImageSelectedTablet;
    String[] stringsWhiteTablet, stringsTotalTablet,stringsSelectedTablet,stringsBGColor;
    String stringProcessCompleted;
    LinearLayout linearLayoutCenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_change_appearance);

        stringProcessCompleted = "N";

        bindWidget();

        receiveIntent();

        displayMetrics();

        showView();

        clickAdaptor(); //Setค่าของ Adaptor ทั้งหมดลงใน Adaptor

        clickImageView(); //คลิก ImageView เปลี่ยนสี

        //clickListViewAdaptor();

        clickOKCancelbutton();


    }

    private void receiveIntent() {
        string1 = getIntent().getStringExtra("Med_id");
        string2 = getIntent().getStringExtra("Trade_name");
        string3 = getIntent().getStringExtra("Generic_line");
        string15 = getIntent().getStringExtra("Amount_tablet");
        string16 = getIntent().getStringExtra("EA");
        string4 = getIntent().getStringExtra("Which_Date_D");
        string5 = getIntent().getStringExtra("Appearance");
        string6 = getIntent().getStringExtra("Pharmaco");
        string7 = getIntent().getStringExtra("T1");
        string8 = getIntent().getStringExtra("T2");
        string9 = getIntent().getStringExtra("T3");
        string10 = getIntent().getStringExtra("T4");
        string11 = getIntent().getStringExtra("T5");
        string12 = getIntent().getStringExtra("T6");
        string13 = getIntent().getStringExtra("T7");
        string14 = getIntent().getStringExtra("T8");
        string17 = getIntent().getStringExtra("TimesPerDay");
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setIcon(R.drawable.stop_sign);
                    builder.setTitle("ไม่สามารถดำเนินการได้");
                    builder.setMessage("กรุณาทำตามขั้นตอนที่ระบุไว้");
                    builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            dialog.dismiss();
                        }
                    });
                    builder.show();
                } else {


                    AddMedicine2Activity.activityAddMedicine2Activity.finish();
                    //เปลี่ยน String 5
                    string5 = stringProcessCompleted;
                    Intent intent = new Intent(getBaseContext(), AddMedicine2Activity.class);
                    intent.putExtra("Med_id",string1);
                    intent.putExtra("Trade_name", string2);
                    intent.putExtra("Generic_line", string3);
                    intent.putExtra("Amount_tablet", string15);
                    intent.putExtra("EA", string16);
                    intent.putExtra("Which_Date_D", string4);
                    intent.putExtra("Appearance", string5);
                    intent.putExtra("Pharmaco", string6);
                    intent.putExtra("T1",string7);
                    intent.putExtra("T2",string8);
                    intent.putExtra("T3",string9);
                    intent.putExtra("T4",string10);
                    intent.putExtra("T5",string11);
                    intent.putExtra("T6",string12);
                    intent.putExtra("T7",string13);
                    intent.putExtra("T8",string14);
                    intent.putExtra("TimesPerDay", string17);


                    startActivity(intent);
                    finish();





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

    private void clickAdaptor() {

        //Step 2 Tablet
        final MyData myData = new MyData();



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
                        stringTablet3ArrayList.add(iIndex, stringsTotalTablet[y]);
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
                stringProcessCompleted = stringsSelectedTablet[position];
            }
        });


    }

    private void clickImageView() {

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] stringsWhiteTablet1 = {"img0101", "img0201", "img0301", "img0501", "img0601",
                        "img0701", "img0801", "img0901"};
                stringsWhiteTablet = stringsWhiteTablet1;
                MyData myData = new MyData();
                intsImageWhiteTablet = myData.translate_Appearance(stringsWhiteTablet);

                MyAdaptorChangeAppearance myAdaptorChangeAppearance = new MyAdaptorChangeAppearance(getBaseContext(), intsImageWhiteTablet);
                listViewTablet2.setAdapter(myAdaptorChangeAppearance);

                textViewStep2Tablet.setText("ขั้นตอนที่ 2 :\nเลือกรูปแบบยาเม็ด");


                imageView1.setImageResource(R.drawable.icon_tablet2);
                imageView2.setImageResource(R.drawable.icon_capsule1);

                listViewTablet2.setVisibility(View.VISIBLE);
                textViewStep2Tablet.setVisibility(View.VISIBLE);
                textViewStep3Tablet.setVisibility(View.INVISIBLE);
                listViewTablet3.setVisibility(View.INVISIBLE);
                textViewStep4.setVisibility(View.INVISIBLE);
                imageViewFinal.setVisibility(View.INVISIBLE);
                linearLayoutCenter.setVisibility(View.VISIBLE);
                stringProcessCompleted = "N";
            }
        });


        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] stringsBGColor1 = {"bgc01", "bgc02", "bgc04", "bgc03", "bgc08", "bgc06", "bgc14"
                        , "bgc05", "bgc11", "bgc12", "bgc07", "bgc09", "bgc10", "bgc13"};

                stringsBGColor = stringsBGColor1;
                MyData myData = new MyData();
                int[] intsImageBGColor = {R.drawable.bgc01,R.drawable.bgc02,R.drawable.bgc04,
                        R.drawable.bgc03,R.drawable.bgc08,R.drawable.bgc06,R.drawable.bgc14,
                        R.drawable.bgc05,R.drawable.bgc11,R.drawable.bgc12,R.drawable.bgc07,
                        R.drawable.bgc09,R.drawable.bgc10,R.drawable.bgc13};

                MyAdaptorChangeAppearance myAdaptorChangeAppearance = new MyAdaptorChangeAppearance(getBaseContext(), intsImageBGColor);
                listViewTablet2.setAdapter(myAdaptorChangeAppearance);

                textViewStep2Tablet.setText("ขั้นตอนที่ 2 :\nเลือกสีด้านแคปซูล");

                imageView1.setImageResource(R.drawable.icon_tablet1);
                imageView2.setImageResource(R.drawable.icon_capsule2);

                listViewTablet2.setVisibility(View.VISIBLE);
                textViewStep2Tablet.setVisibility(View.VISIBLE);
                textViewStep3Tablet.setVisibility(View.INVISIBLE);
                listViewTablet3.setVisibility(View.INVISIBLE);
                textViewStep4.setVisibility(View.INVISIBLE);
                imageViewFinal.setVisibility(View.INVISIBLE);
                linearLayoutCenter.setVisibility(View.VISIBLE);
                stringProcessCompleted = "N";


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


        textViewStep3Tablet.setText("ขั้นตอนที่ 3 :\nเลือกเม็ดยาที่ต้องการ");
        textViewStep4.setText("ขั้นตอนที่ 4\nเสร็จสิ้นการเลือกรูปเสมือนเม็ดยา\nกด 'เปลี่ยนรูป' เพื่อจบการทำงาน");


        listViewTablet2.setVisibility(View.INVISIBLE);
        textViewStep2Tablet.setVisibility(View.INVISIBLE);
        textViewStep3Tablet.setVisibility(View.INVISIBLE);
        listViewTablet3.setVisibility(View.INVISIBLE);
        textViewStep4.setVisibility(View.INVISIBLE);
        imageViewFinal.setVisibility(View.INVISIBLE);
        linearLayoutCenter.setVisibility(View.INVISIBLE);


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

        linearLayoutCenter = (LinearLayout) findViewById(R.id.linPUCACenter);

    }

    private void displayMetrics() {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        getWindow().setLayout((int) (width * .9), (int) (height * .9));
    }
}
