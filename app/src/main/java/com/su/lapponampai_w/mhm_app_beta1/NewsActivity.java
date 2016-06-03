package com.su.lapponampai_w.mhm_app_beta1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NewsActivity extends AppCompatActivity {

    //Explicit

    String[] stringsReadMed_id,stringsNewsAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);


        MyManage myManage = new MyManage(this);
        MyData myData = new MyData();

        stringsReadMed_id = myManage.readAllMainTABLE(2);
        //stringsNewsAdaptor = myData.stringsNews(stringsReadMed_id);




    }  //Main Method


}  //Main Class
