package com.su.lapponampai_w.mhm_app_beta1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class PopUpChangeAppearance extends AppCompatActivity {
    
    
    //Explicit
    ImageView imageView1,imageView2, imageView3;
    TextView textViewStep2Tablet;
    ListView listViewTablet2;
    int[] intsImageWhiteTablet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_change_appearance);

        bindWidget();
        
        displayMetrics();

        showView();

        setValueInAllAdaptor(); //Setค่าของ Adaptor ทั้งหมดลงใน Adaptor

        clickImageView(); //คลิก ImageView เปลี่ยนสี
        
        
    }

    private void setValueInAllAdaptor() {

        //Step 2 Tablet
        String[] stringsWhiteTablet = {"img0101","img0201","img0301","img0501","img0601",
                "img0701","img0801","img0901"};
        MyData myData = new MyData();

        intsImageWhiteTablet = myData.translate_Appearance(stringsWhiteTablet);

        MyAdaptorChangeAppearance myAdaptorChangeAppearance = new MyAdaptorChangeAppearance(getBaseContext(),intsImageWhiteTablet);
        listViewTablet2.setAdapter(myAdaptorChangeAppearance);





    }

    private void clickImageView() {

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imageView1.setImageResource(R.drawable.icon_tablet2);
                imageView2.setImageResource(R.drawable.icon_capsule1);

                listViewTablet2.setVisibility(View.VISIBLE);
                textViewStep2Tablet.setVisibility(View.VISIBLE);

            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imageView1.setImageResource(R.drawable.icon_tablet1);
                imageView2.setImageResource(R.drawable.icon_capsule2);

                listViewTablet2.setVisibility(View.INVISIBLE);
                textViewStep2Tablet.setVisibility(View.INVISIBLE);

            }
        });

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(),"ยังไม่มีฟังก์ชั่นยาน้ำในเว่อร์ชั่นนี้",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void showView() {
        textViewStep2Tablet.setText("ขั้นตอนที่ 2 :\nเลือกรูปแบบยาเม็ด");
        listViewTablet2.setVisibility(View.INVISIBLE);
        textViewStep2Tablet.setVisibility(View.INVISIBLE);


    }

    private void bindWidget() {

        imageView1 = (ImageView) findViewById(R.id.imageView18);
        imageView2 = (ImageView) findViewById(R.id.imageView19);
        imageView3 = (ImageView) findViewById(R.id.imageView20);

        textViewStep2Tablet = (TextView) findViewById(R.id.textView209);

        listViewTablet2 = (ListView) findViewById(R.id.listViewTablet2);
        
    }

    private void displayMetrics() {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        getWindow().setLayout((int) (width*.9),(int) (height*.9));
    }
}
