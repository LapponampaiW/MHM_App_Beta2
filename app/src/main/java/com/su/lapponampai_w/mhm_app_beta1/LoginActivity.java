package com.su.lapponampai_w.mhm_app_beta1;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class LoginActivity extends AppCompatActivity {

    MyManage myManage;

    //ปุ่มต่างๆจาก activity_login.xml
    Button buttonLogin;
    EditText editTextUser, editTextPassword;
    CheckBox checkBoxlogin;

    //ค่า String ของ editText
    String stringUser, stringPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myManage = new MyManage(this);

        // เชื่อมปุ่มต่างๆ
        bindWidget();
        checkBoxlogin.setVisibility(View.GONE);

        // เมื่อทำการกดปุ่ม buttonLogin
        Click_buttonLogin();


    } //Main Method


    public void click_Forgot(View view) {

        Log.d("2nov11", "Click ForgotPassword");
        MyAESHelper myAESHelper = new MyAESHelper();
        String seedValue = "MHM Application";
        String normalText = "VIJAY";

        String[] sUsername = myManage.filter_userTABLE(1);
        String[] sPassword = myManage.filter_userTABLE(2);

        String eNTextUsername;
        String eNTextPassword;
        try {
            eNTextUsername = MyAESHelper.encrypt(seedValue, sUsername[0]);
            eNTextPassword = MyAESHelper.encrypt(seedValue, sPassword[0]);
            //String normalTextDec = MyAESHelper.decrypt(seedValue, normalTextEnc);
            //TextView txe = new TextView(v.getContext());
            //txe.setTextSize(14);
            //txe.setText("Normal Text ::"+normalText +" \n Encrypted Value :: "+normalTextEnc +" \n Decrypted value :: "+normalTextDec);
            //setContentView(txe);
            //Toast.makeText(getBaseContext(),normalTextEnc,Toast.LENGTH_LONG).show();



            String[] TO = {"ballz_v@hotmail.com"};
            //String[] CC = {"weerachodphaesaj@gmail.com"};
            String sText = "โปรดส่งข้อความนี้ผ่าน E-mail โดยไม่ลบข้อความใดๆ(ท่านสามารถกดส่งได้ทันที) \n\n\n"
                    + eNTextUsername + "\n\n\n" + eNTextPassword + "\n\n\n" + "==================";


            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.setType("text/plain");


            emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
            //emailIntent.putExtra(Intent.EXTRA_CC, CC);
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "(MHM Application)...ลืมรหัสผ่าน!!!");
            emailIntent.putExtra(Intent.EXTRA_TEXT, sText);

            try {
                startActivity(Intent.createChooser(emailIntent, "โปรดเลือกโปรแกรมส่ง mail"));


            } catch (android.content.ActivityNotFoundException e) {
                Toast.makeText(this, "ไม่มีแอพส่งเมล นะ", Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        /*
        String strSubject = "Your Password";
        String strBody = "Your Password 1234";
        String[] TO = {"someone@gmail.com"};
        String[] CC = {"xyz@gmail.com"};
        Intent intent = new Intent(Intent.ACTION_SEND);

        intent.setData(Uri.parse("mailto:" + "ballz_v@hotmail.com"));
        intent.setType("text/plain");
        //intent.putExtra(Intent.EXTRA_EMAIL, new String[]{ strEmail});
        intent.putExtra(Intent.EXTRA_SUBJECT, strSubject);
        intent.putExtra(Intent.EXTRA_TEXT, strBody);
        */




        /*
        String[] TO = {"ballz_v@hotmail.com"};
        //String[] CC = {"weerachodphaesaj@gmail.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");


        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        //emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

        try {
            startActivity(Intent.createChooser(emailIntent, "โปรดเลือกโปรแกรมส่ง mail"));


        } catch (android.content.ActivityNotFoundException e) {
            Toast.makeText(this, "ไม่มีแอพส่งเมล นะ", Toast.LENGTH_LONG).show();
        }

        */
    } //Forgot

    private void Click_buttonLogin() {
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stringUser = editTextUser.getText().toString().trim();
                stringPassword = editTextPassword.getText().toString().trim();

                if (stringUser.equals("") || stringPassword.equals("")) {
                    Toast t = Toast.makeText(LoginActivity.this, "มีค่าว่าง", Toast.LENGTH_SHORT);
                    t.show();
                } else {
                    checkPasswordLoginAndGoToMainActivity();
                }


            }
        });

    } //Click_buttonLogin Method

    private void checkPasswordLoginAndGoToMainActivity() {

        stringUser = editTextUser.getText().toString().trim();
        stringPassword = editTextPassword.getText().toString().trim();
        String[] strUsername = myManage.readSQLite_userTABLE(1);
        String[] strPassword = myManage.readSQLite_userTABLE(2);

        String arrayIndexStringUsername = myManage.getArrayStringIndex(strUsername, stringUser);
        String arrayIndexStringPassword = myManage.getArrayStringIndex(strPassword, stringPassword);




        if (arrayIndexStringUsername.equals("-555") || arrayIndexStringPassword.equals("-555")) {
            Toast t = Toast.makeText(LoginActivity.this, "ไม่มี Username หรือ Password (-555)", Toast.LENGTH_SHORT);
            t.show();
        } else if (!arrayIndexStringUsername.equals(arrayIndexStringPassword)) {
            Toast t = Toast.makeText(LoginActivity.this, "Username ไม่สัมพันธ์กับ password", Toast.LENGTH_SHORT);
            t.show();
        } else if (arrayIndexStringUsername.equals(arrayIndexStringPassword)) {

             if (checkBoxlogin.isChecked()) {

                //ทำการเพิ่มค่า Stay เป็น 1
                myManage.updateStayLogin(stringUser,"1");

             }

                //gotoMainActivity
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                finish();


        } else {
            Toast t = Toast.makeText(LoginActivity.this, "Cannot Define What's Happen!!!!!", Toast.LENGTH_SHORT);
            t.show();
        }

    }

    private void bindWidget() {
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        editTextUser = (EditText) findViewById(R.id.editTextLoginUser);
        editTextPassword = (EditText) findViewById(R.id.editTextLoginPassword);
        checkBoxlogin = (CheckBox) findViewById(R.id.checkBox_login);
    } // bindWidget Method


}  //Main Class
