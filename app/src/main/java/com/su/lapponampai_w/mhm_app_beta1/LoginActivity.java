package com.su.lapponampai_w.mhm_app_beta1;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    MyManage myManage;

    //ปุ่มต่างๆจาก activity_login.xml
    Button buttonLogin;
    EditText editTextUser, editTextPassword;

    //ค่า String ของ editText
    String stringUser, stringPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myManage = new MyManage(this);

        // เชื่อมปุ่มต่างๆ
        bindWidget();

        // เมื่อทำการกดปุ่ม buttonLogin
        Click_buttonLogin();


    } //Main Method

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

        final String arrayIndexStringUsername = myManage.getArrayStringIndex(strUsername, stringUser);
        final String arrayIndexStringPassword = myManage.getArrayStringIndex(strPassword, stringPassword);

        if (arrayIndexStringUsername.equals("-555") || arrayIndexStringPassword.equals("-555")) {
            Toast t = Toast.makeText(LoginActivity.this, "ไม่มี Username หรือ Password (-555)", Toast.LENGTH_SHORT);
            t.show();
        } else if (!arrayIndexStringUsername.equals(arrayIndexStringPassword)) {
            Toast t = Toast.makeText(LoginActivity.this, "Username ไม่สัมพันธ์กับ password", Toast.LENGTH_SHORT);
            t.show();
        } else if (arrayIndexStringUsername.equals(arrayIndexStringPassword)) {
            Toast t = Toast.makeText(LoginActivity.this, "One process Goto Next Activity", Toast.LENGTH_SHORT);
            t.show();
        } else {
            Toast t = Toast.makeText(LoginActivity.this, "Cannot Define What's Happen!!!!!", Toast.LENGTH_SHORT);
            t.show();
        }

    }

    private void bindWidget() {
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        editTextUser = (EditText) findViewById(R.id.editTextLoginUser);
        editTextPassword = (EditText) findViewById(R.id.editTextLoginPassword);
    } // bindWidget Method


}  //Main Class
