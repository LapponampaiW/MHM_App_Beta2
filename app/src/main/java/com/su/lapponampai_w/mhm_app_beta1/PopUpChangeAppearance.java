package com.su.lapponampai_w.mhm_app_beta1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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
    ImageView imageView1, imageView2, imageView3, imageViewFinal;
    ImageView imageViewL1, imageViewL2, imageViewL3, imageViewR1, imageViewR2, imageViewR3;
    TextView textViewStep2Tablet, textViewStep3Tablet, textViewStep4;
    Button buttonCancel;
    ListView listViewTablet2, listViewTablet3;
    int[] intsImageWhiteTablet, intsImageSelected;
    String[] stringsTotalTablet, stringsSelected, stringsTotalCapsule, stringsStep2;
    String stringProcessCompleted;
    LinearLayout linearLayoutCenter,linearLayoutL,linearLayoutR;
    String stringFrom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_change_appearance);


        bindWidget();

        receiveIntent();

        displayMetrics();

        showView();

        clickAdaptor(); //Setค่าของ Adaptor ทั้งหมดลงใน Adaptor

        clickImageView(); //คลิก ImageView เปลี่ยนสี

        //clickListViewAdaptor();

        clickCancelbutton();

        setImageViewL();
        setImageViewR();


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
        stringFrom = getIntent().getStringExtra("From");
    }

    private void clickCancelbutton() {

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }


    private void clickAdaptor() {

        //Step 2 Tablet
        final MyData myData = new MyData();


        listViewTablet2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                String[] stringsTotalTablet1 = {"img0101", "img0102", "img0103", "img0104", "img0201",
                        "img0202", "img0203", "img0204", "img0301", "img0302", "img0303", "img0304",
                        "img0309", "img0312", "img0315", "img0501", "img0503", "img0511", "img0601", "img0602",
                        "img0603", "img0604", "img0607", "img0609", "img0611", "img0612", "img0615","img0616", "img0617",
                        "img0701", "img0702", "img0703", "img0711", "img0712", "img0714", "img0801",
                        "img0802", "img0803", "img0901", "img0902", "img0903", "img0907", "img0912",
                        "img0917", "img1002", "img1101", "img1103", "img1201", "img1301", "img1401"
                        , "img1403", "img1413", "img1501", "img1503", "img1601", "img1612"};

                String[] stringsTotalCapsule1 = {"img90101", "img90103", "img90107", "img90108", "img90112",
                        "img90114", "img90115", "img90308", "img90312", "img90506", "img90808",
                        "img90813", "img90910", "img91104", "img91316", "img91414", "img91515", "img99911", "img99912"};




                stringsTotalTablet = stringsTotalTablet1;
                stringsTotalCapsule = stringsTotalCapsule1;
                ArrayList<String> stringStep3ArrayList = new ArrayList<String>();
                int iIndex = 0;

                int iFilterTabletCapsule = stringsStep2[position].length();

                if (iFilterTabletCapsule == 7) {
                    String sSubstring = stringsStep2[position].substring(3, 5);
                    //Toast.makeText(getBaseContext(), sSubstring, Toast.LENGTH_SHORT).show();

                    for (int y = 0; y < stringsTotalTablet.length; y++) {
                        if (stringsTotalTablet[y].substring(3, 5).equals(sSubstring)) {
                            stringStep3ArrayList.add(iIndex, stringsTotalTablet[y]);
                            iIndex = iIndex + 1;

                        }
                    }

                    stringsSelected = new String[stringStep3ArrayList.size()];
                    stringsSelected = stringStep3ArrayList.toArray(stringsSelected);
                    //MyData myData = new MyData();
                    intsImageSelected = myData.translate_Appearance(stringsSelected);

                    MyAdaptorChangeAppearance myAdaptorChangeAppearance = new MyAdaptorChangeAppearance(getBaseContext(), intsImageSelected);
                    listViewTablet3.setAdapter(myAdaptorChangeAppearance);

                    setVisibleStep2Success();

                } else if (iFilterTabletCapsule == 5) {


                    //Toast.makeText(getBaseContext(), "เข้า Capsule", Toast.LENGTH_SHORT).show();
                    String sSubstring = stringsStep2[position].substring(3);
                    if (sSubstring.equals("00")) {
                        intsImageSelected = myData.translate_Appearance(stringsTotalCapsule);
                        stringsSelected = stringsTotalCapsule;
                        MyAdaptorChangeAppearance myAdaptorChangeAppearance = new MyAdaptorChangeAppearance(getBaseContext(), intsImageSelected);
                        listViewTablet3.setAdapter(myAdaptorChangeAppearance);

                        setVisibleStep2Success();
                    } else if (sSubstring.equals("99")) {
                        //จิงๆ ไม่ต้อง Arraylist ก้ได้แต่ว่าขี้เกียจทำ
                        //สร้าง Arraylist ที่สามารถ Duplicate ได้ก่อน
                        for (int i = 0; i < stringsTotalCapsule.length; i++) {
                            String sSubStringCapsuleL = stringsTotalCapsule[i].substring(4, 6);
                            String sSubStringCapsuleR = stringsTotalCapsule[i].substring(6);
                            //แก้ให้ capsule นิ่มไม่ขึ้น
                            if (sSubstring.equals(sSubStringCapsuleL) || sSubstring.equals(sSubStringCapsuleR)) {

                                    stringStep3ArrayList.add(iIndex, stringsTotalCapsule[i]);
                                    iIndex = iIndex + 1;

                            }

                        }
                        stringsSelected = new String[stringStep3ArrayList.size()];
                        stringsSelected = stringStep3ArrayList.toArray(stringsSelected);


                        //MyData myData = new MyData();
                        intsImageSelected = myData.translate_Appearance(stringsSelected);
                        MyAdaptorChangeAppearance myAdaptorChangeAppearance = new MyAdaptorChangeAppearance(getBaseContext(), intsImageSelected);
                        listViewTablet3.setAdapter(myAdaptorChangeAppearance);

                        setVisibleStep2Success();

                    } else {

                        //สร้าง Arraylist ที่สามารถ Duplicate ได้ก่อน
                        for (int i = 0; i < stringsTotalCapsule.length; i++) {
                            String sSubStringCapsuleL = stringsTotalCapsule[i].substring(4, 6);
                            String sSubStringCapsuleR = stringsTotalCapsule[i].substring(6);
                            //แก้ให้ capsule นิ่มไม่ขึ้น
                            if (sSubstring.equals(sSubStringCapsuleL) || sSubstring.equals(sSubStringCapsuleR)) {
                                if (!sSubStringCapsuleL.equals("99")) {
                                    stringStep3ArrayList.add(iIndex, stringsTotalCapsule[i]);
                                    iIndex = iIndex + 1;
                                }
                            }

                        }
                        stringsSelected = new String[stringStep3ArrayList.size()];
                        stringsSelected = stringStep3ArrayList.toArray(stringsSelected);


                        //Toast.makeText(getBaseContext(),stringStep3ArrayList.size(),Toast.LENGTH_SHORT).show();
                        if (stringsSelected.length == 0) {
                            Toast.makeText(getBaseContext(), "ไม่มีรูปเสมือนเม็ดยา", Toast.LENGTH_SHORT).show();

                            setVisibleStep1();
                        } else {

                            //MyData myData = new MyData();
                            intsImageSelected = myData.translate_Appearance(stringsSelected);
                            MyAdaptorChangeAppearance myAdaptorChangeAppearance = new MyAdaptorChangeAppearance(getBaseContext(), intsImageSelected);
                            listViewTablet3.setAdapter(myAdaptorChangeAppearance);

                            setVisibleStep2Success();

                        }

                    }

                } //จบ If


            } //onItemClick
        });


        //คลิกขั้นตอนที่ 3
        listViewTablet3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                int[] intsFinalImage = myData.translate_Appearance(stringsSelected);

                imageViewFinal.setImageResource(intsFinalImage[position]);
                textViewStep4.setVisibility(View.VISIBLE);
                imageViewFinal.setVisibility(View.VISIBLE);
                stringProcessCompleted = stringsSelected[position];


                if (stringFrom.equals("AddMedicine2Activity")) {
                    AddMedicine2Activity.activityAddMedicine2Activity.finish();
                    //เปลี่ยน String 5
                    string5 = stringProcessCompleted;
                    Intent intent = new Intent(getBaseContext(), AddMedicine2Activity.class);
                    intent.putExtra("Med_id", string1);
                    intent.putExtra("Trade_name", string2);
                    intent.putExtra("Generic_line", string3);
                    intent.putExtra("Amount_tablet", string15);
                    intent.putExtra("EA", string16);
                    intent.putExtra("Which_Date_D", string4);
                    intent.putExtra("Appearance", string5);
                    intent.putExtra("Pharmaco", string6);
                    intent.putExtra("T1", string7);
                    intent.putExtra("T2", string8);
                    intent.putExtra("T3", string9);
                    intent.putExtra("T4", string10);
                    intent.putExtra("T5", string11);
                    intent.putExtra("T6", string12);
                    intent.putExtra("T7", string13);
                    intent.putExtra("T8", string14);
                    intent.putExtra("TimesPerDay", string17);
                    startActivity(intent);
                    finish();
                } else if (stringFrom.equals("AddCustomMedicine")) {
                    AddCustomMedicine.activityAddCustomMedicine.finish();
                    //เปลี่ยน String 5
                    string5 = stringProcessCompleted;
                    Intent intent = new Intent(getBaseContext(), AddCustomMedicine.class);
                    intent.putExtra("Med_id", string1);
                    intent.putExtra("Trade_name", string2);
                    intent.putExtra("Generic_line", string3);
                    intent.putExtra("Amount_tablet", string15);
                    intent.putExtra("EA", string16);
                    intent.putExtra("Which_Date_D", string4);
                    intent.putExtra("Appearance", string5);
                    intent.putExtra("Pharmaco", string6);
                    intent.putExtra("T1", string7);
                    intent.putExtra("T2", string8);
                    intent.putExtra("T3", string9);
                    intent.putExtra("T4", string10);
                    intent.putExtra("T5", string11);
                    intent.putExtra("T6", string12);
                    intent.putExtra("T7", string13);
                    intent.putExtra("T8", string14);
                    intent.putExtra("TimesPerDay", string17);
                    startActivity(intent);
                    finish();
                }





            }
        });


    }

    private void clickImageView() {

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] stringsWhiteTablet1 = {"img0101", "img0201", "img0301", "img0501", "img0601",
                        "img0701", "img0801", "img0901", "img1101", "img1201", "img1301", "img1401", "img1501", "img1601"};
                stringsStep2 = stringsWhiteTablet1;
                MyData myData = new MyData();
                intsImageWhiteTablet = myData.translate_Appearance(stringsStep2);

                MyAdaptorChangeAppearance myAdaptorChangeAppearance = new MyAdaptorChangeAppearance(getBaseContext(), intsImageWhiteTablet);
                listViewTablet2.setAdapter(myAdaptorChangeAppearance);

                textViewStep2Tablet.setText("ขั้นตอนที่ 2 :\nเลือกรูปแบบยาเม็ด");


                imageView1.setImageResource(R.drawable.icon_tablet2);
                imageView2.setImageResource(R.drawable.icon_capsule1);

                setVisibleStep1();


            }
        });


        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] stringsBGColor1 = {"bgc00", "bgc01", "bgc02", "bgc04", "bgc03", "bgc08", "bgc06", "bgc14"
                        , "bgc05", "bgc11", "bgc12", "bgc07", "bgc09", "bgc10", "bgc13", "bgc16", "bgc99"};

                stringsStep2 = stringsBGColor1;
                MyData myData = new MyData();
                int[] intsImageBGColor = {R.drawable.bgc00, R.drawable.bgc01, R.drawable.bgc02, R.drawable.bgc04,
                        R.drawable.bgc03, R.drawable.bgc08, R.drawable.bgc06, R.drawable.bgc14,
                        R.drawable.bgc05, R.drawable.bgc11, R.drawable.bgc12, R.drawable.bgc07,
                        R.drawable.bgc09, R.drawable.bgc10, R.drawable.bgc13,R.drawable.bgc16,R.drawable.bgc99};

                MyAdaptorChangeAppearance myAdaptorChangeAppearance = new MyAdaptorChangeAppearance(getBaseContext(), intsImageBGColor);
                listViewTablet2.setAdapter(myAdaptorChangeAppearance);

                textViewStep2Tablet.setText("ขั้นตอนที่ 2 :\nเลือกสีด้านแคปซูล");

                imageView1.setImageResource(R.drawable.icon_tablet1);
                imageView2.setImageResource(R.drawable.icon_capsule2);

                setVisibleStep1();


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
        textViewStep4.setText("ขั้นตอนที่ 4\nเสร็จสิ้นการทำงาน");

        listViewTablet2.setVisibility(View.INVISIBLE);
        textViewStep2Tablet.setVisibility(View.INVISIBLE);
        textViewStep3Tablet.setVisibility(View.INVISIBLE);
        listViewTablet3.setVisibility(View.INVISIBLE);
        textViewStep4.setVisibility(View.INVISIBLE);
        imageViewFinal.setVisibility(View.INVISIBLE);
        linearLayoutCenter.setVisibility(View.INVISIBLE);
        stringProcessCompleted = "N";

        linearLayoutL.setVisibility(View.INVISIBLE);
        linearLayoutR.setVisibility(View.INVISIBLE);

    }

    private void setVisibleStep1() {
        listViewTablet2.setVisibility(View.VISIBLE);
        textViewStep2Tablet.setVisibility(View.VISIBLE);
        textViewStep3Tablet.setVisibility(View.INVISIBLE);
        listViewTablet3.setVisibility(View.INVISIBLE);
        textViewStep4.setVisibility(View.INVISIBLE);
        imageViewFinal.setVisibility(View.INVISIBLE);
        linearLayoutCenter.setVisibility(View.VISIBLE);
        stringProcessCompleted = "N";
        imageViewR1.setVisibility(View.INVISIBLE);
        imageViewR2.setVisibility(View.INVISIBLE);
        imageViewR3.setVisibility(View.INVISIBLE);
        linearLayoutL.setVisibility(View.VISIBLE);


        //ให้มีการ กระพริบของรูป


    }

    private void setVisibleStep2Success() {
        listViewTablet3.setVisibility(View.VISIBLE);
        textViewStep3Tablet.setVisibility(View.VISIBLE);
        textViewStep4.setVisibility(View.INVISIBLE);
        imageViewFinal.setVisibility(View.INVISIBLE);
        stringProcessCompleted = "N";
        linearLayoutR.setVisibility(View.VISIBLE);




    }



    private void bindWidget() {

        imageView1 = (ImageView) findViewById(R.id.imageView18);
        imageView2 = (ImageView) findViewById(R.id.imageView19);
        imageView3 = (ImageView) findViewById(R.id.imageView20);
        imageViewFinal = (ImageView) findViewById(R.id.imageView17);

        textViewStep2Tablet = (TextView) findViewById(R.id.textView209);
        textViewStep3Tablet = (TextView) findViewById(R.id.textView211);
        textViewStep4 = (TextView) findViewById(R.id.textView215);

        buttonCancel = (Button) findViewById(R.id.buttonAPUC);



        listViewTablet2 = (ListView) findViewById(R.id.listViewTablet2);
        listViewTablet3 = (ListView) findViewById(R.id.listViewTablet3);

        linearLayoutCenter = (LinearLayout) findViewById(R.id.linPUCACenter);

        linearLayoutL = (LinearLayout) findViewById(R.id.linImageViewL);
        linearLayoutR = (LinearLayout) findViewById(R.id.linImageViewR);


        imageViewL1 = (ImageView) findViewById(R.id.imageView22);
        imageViewL2 = (ImageView) findViewById(R.id.imageView23);
        imageViewL3 = (ImageView) findViewById(R.id.imageView24);
        imageViewR1 = (ImageView) findViewById(R.id.imageView25);
        imageViewR2 = (ImageView) findViewById(R.id.imageView26);
        imageViewR3 = (ImageView) findViewById(R.id.imageView27);


    }

    private void displayMetrics() {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        getWindow().setLayout((int) (width * .9), (int) (height * .9));
    }

    private void setImageViewR() {
        final Handler handler = new Handler();

        for(int i = 0;i <= 1000;i++) {
            int x = i * 3000;

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            imageViewR1.setVisibility(View.VISIBLE);
                            imageViewR2.setVisibility(View.INVISIBLE);
                            imageViewR3.setVisibility(View.INVISIBLE);

                        }

                    }, 1000); // 2 วินาที


                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            imageViewR1.setVisibility(View.INVISIBLE);
                            imageViewR2.setVisibility(View.VISIBLE);
                            imageViewR3.setVisibility(View.INVISIBLE);

                        }
                    }, 2000); // 2 วินาที

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            imageViewR1.setVisibility(View.INVISIBLE);
                            imageViewR2.setVisibility(View.INVISIBLE);
                            imageViewR3.setVisibility(View.VISIBLE);

                        }
                    }, 3000); // 2 วินาที

                }

            }, x); // 2 วินาที
        }
    }

    private void setImageViewL() {

        final Handler handler = new Handler();

        for(int i = 0;i <= 1000;i++) {
            int x = i * 3000;

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            imageViewL1.setVisibility(View.VISIBLE);
                            imageViewL2.setVisibility(View.INVISIBLE);
                            imageViewL3.setVisibility(View.INVISIBLE);

                        }

                    }, 1000); // 2 วินาที


                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            imageViewL1.setVisibility(View.INVISIBLE);
                            imageViewL2.setVisibility(View.VISIBLE);
                            imageViewL3.setVisibility(View.INVISIBLE);

                        }
                    }, 2000); // 2 วินาที

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            imageViewL1.setVisibility(View.INVISIBLE);
                            imageViewL2.setVisibility(View.INVISIBLE);
                            imageViewL3.setVisibility(View.VISIBLE);

                        }
                    }, 3000); // 2 วินาที

                }

            }, x); // 2 วินาที

        }



    }
}
