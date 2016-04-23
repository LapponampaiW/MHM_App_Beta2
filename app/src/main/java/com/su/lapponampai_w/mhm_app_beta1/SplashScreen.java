package com.su.lapponampai_w.mhm_app_beta1;

import android.content.Intent;
import android.database.Cursor;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class SplashScreen extends AppCompatActivity {

    MyManage myManage;
    int anInt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        myManage = new MyManage(this);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                anInt = myManage.check_null_userTABLE();

                if (anInt == 1) {
                    startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                    Toast t = Toast.makeText(SplashScreen.this, Integer.toString(anInt), Toast.LENGTH_LONG); // ลบภายหลัง
                    t.show();
                    finish();
                } else {
                    startActivity(new Intent(SplashScreen.this, SignUpActivity.class)); // เดี่ยวต้องเปลี่ยน
                    Toast t = Toast.makeText(SplashScreen.this, Integer.toString(anInt) , Toast.LENGTH_LONG); //ลบภายหลัง
                    t.show();
                    finish();
                }


            }
        },2000); // 2 วินาที
    }  //Main Method
}  //Main Class
