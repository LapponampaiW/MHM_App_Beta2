package com.su.lapponampai_w.mhm_app_beta1;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SettingActivity extends AppCompatActivity {

    //Explicit
    Button buttonConnect,buttonSuperUser;
    String strAddVN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        bindWidget();

        clickConnect();

        clickSuperUser();

    }

    private void clickSuperUser() {
        buttonSuperUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(SettingActivity.this,ForDoctorActivity.class));
            }
        });

    }

    private void bindWidget() {

        buttonConnect = (Button) findViewById(R.id.btnForTransferData);
        buttonSuperUser = (Button) findViewById(R.id.btnForSuperUser);
    }

    public void clickConnect() {
        buttonConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText editText = new EditText(SettingActivity.this);
                editText.setInputType(16);

                AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
                builder.setIcon(R.drawable.icon_question);
                builder.setTitle("ยืนยันการส่งข้อมูล!!!");
                builder.setMessage("กรุณาใส่รหัส และคลิกยืนยันเพื่อส่งข้อมูล");
                builder.setView(editText);
                builder.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("ยืนยัน", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String strEditText = editText.getText().toString().trim();
                        MyManage myManage = new MyManage(SettingActivity.this);
                        String[] strPassword = myManage.readAlluserTABLE(2);
                        if (strPassword[0].equals(strEditText)) {

                            Toast.makeText(SettingActivity.this,"Updated Data to Server",Toast.LENGTH_SHORT).show();
                            dialog.dismiss();

                            SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyHelper.DATABASE_NAME,
                                    MODE_PRIVATE, null);
                            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM userTABLE", null);
                            cursor.moveToFirst();
                            String strHN = cursor.getString(cursor.getColumnIndex(MyManage.ucolumn_hn));

                            Log.d("26July16", "strEmail :"+ strHN);

                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMddHHmmss");
                            String time = simpleDateFormat.format(System.currentTimeMillis());
                            strAddVN = time.concat(strHN);

                            //เริ่มทำการ Update Data to Server
                            updateDatamainTABLE_PHPMyAdmin(strHN,strAddVN);

                            updateDatasumTABLE_PHPMyAdmin(strAddVN);

                            updateDatatotalAmountTABLE_PHPMyAdmin(strAddVN);



                        } else {
                            Toast.makeText(getBaseContext(),"รหัสผิดพลาด",Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }



                    }
                });
                builder.show();

            }
        });

    }

    private void updateDatatotalAmountTABLE_PHPMyAdmin(String strAddVN) {
        String strURL = "http://www.swiftcodingthai.com/mhm/add_totalAmountTABLE.php";

        MyManage myManage = new MyManage(this);

        String[][] strings_totalAmountTABLE = {myManage.readAlltotalAmountTABLE(0),
                myManage.readAlltotalAmountTABLE(1), myManage.readAlltotalAmountTABLE(2),
                myManage.readAlltotalAmountTABLE(3)};

        if (!strings_totalAmountTABLE[0][0].equals("")) {
            for(int i = 0;i<strings_totalAmountTABLE[0].length;i++) {
                OkHttpClient okHttpClient = new OkHttpClient();
                RequestBody requestBody = new FormEncodingBuilder()
                        .add("isAdd", "true")
                        .add("VN", strAddVN)
                        .add(MyManage.tcolumn_Main_id, strings_totalAmountTABLE[1][i])
                        .add(MyManage.tcolumn_TotalAmount, strings_totalAmountTABLE[2][i])
                        .add(MyManage.tcolumn_DateUpdated, strings_totalAmountTABLE[3][i])
                        .build();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(strURL).post(requestBody).build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {
                        Log.d("26July16V3", "Failure in totalAmountTABLE");
                    }

                    @Override
                    public void onResponse(Response response) throws IOException {
                        Log.d("26July16V3", "success in totalAmountTABLE");
                    }
                });

            }

        }


    }

    private void updateDatasumTABLE_PHPMyAdmin(String strAddVN) {

        String strURL = "http://www.swiftcodingthai.com/mhm/add_sumTABLE.php";

        MyManage myManage = new MyManage(this);

        String[][] strings_sumTABLE = {myManage.readAllsumTABLE_Full(0),
                myManage.readAllsumTABLE_Full(1),myManage.readAllsumTABLE_Full(2),
                myManage.readAllsumTABLE_Full(3),myManage.readAllsumTABLE_Full(4),
                myManage.readAllsumTABLE_Full(5),myManage.readAllsumTABLE_Full(6)};

        if (!strings_sumTABLE[0][0].equals("")) {
            //เริ่มใส่ค่า
            for(int i = 0;i<strings_sumTABLE[0].length;i++) {
                OkHttpClient okHttpClient = new OkHttpClient();
                RequestBody requestBody = new FormEncodingBuilder()
                        .add("isAdd", "true")
                        .add("VN", strAddVN)
                        .add(MyManage.column_Main_id, strings_sumTABLE[1][i])
                        .add(MyManage.column_DateRef, strings_sumTABLE[2][i])
                        .add(MyManage.column_TimeRef, strings_sumTABLE[3][i])
                        .add(MyManage.column_DateCheck, strings_sumTABLE[4][i])
                        .add(MyManage.column_TimeCheck, strings_sumTABLE[5][i])
                        .add(MyManage.column_SkipHold, strings_sumTABLE[6][i])
                        .build();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(strURL).post(requestBody).build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {
                        Log.d("26July16", "Failure in sumTABLE");
                    }

                    @Override
                    public void onResponse(Response response) throws IOException {
                        Log.d("26July16", "success in sumTABLE");

                    }
                });



            }




        }
    }


    private void updateDatamainTABLE_PHPMyAdmin(String strAddHN,String strAddVN) {

        //ทำค่า VN ก่อน



        //String strURL = "http://www.swiftcodingthai.com/mhm/add_mainTABLE.php";
        String strURL = "http://www.swiftcodingthai.com/mhm/add_mainTABLE_edited2.php";

        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyHelper.DATABASE_NAME,
                MODE_PRIVATE, null);
        final Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM mainTABLE", null);
        int iCount = cursor.getCount();
        final int intTime = 0;
        if (iCount > 0) {
            cursor.moveToFirst();
            for(int i = 0;i<cursor.getCount();i++) {
                final String str1 = cursor.getString(cursor.getColumnIndex(MyManage.mcolumn_id));
                String str2 = cursor.getString(cursor.getColumnIndex(MyManage.mcolumn_Med_id));
                String str3 = cursor.getString(cursor.getColumnIndex(MyManage.mcolumn_trade_name));
                String str4 = cursor.getString(cursor.getColumnIndex(MyManage.mcolumn_generic_line));
                String str21 = cursor.getString(cursor.getColumnIndex(MyManage.mcolumn_amount_tablet));
                String str5 = cursor.getString(cursor.getColumnIndex(MyManage.mcolumn_which_date_d));
                String str6 = cursor.getString(cursor.getColumnIndex(MyManage.mcolumn_appearance));
                String str7 = cursor.getString(cursor.getColumnIndex(MyManage.mcolumn_ea));
                String str8 = cursor.getString(cursor.getColumnIndex(MyManage.mcolumn_Main_pharmaco));
                String str9 = cursor.getString(cursor.getColumnIndex(MyManage.mcolumn_startdate));
                String str10 = cursor.getString(cursor.getColumnIndex(MyManage.mcolumn_finishdate));
                String str11 = cursor.getString(cursor.getColumnIndex(MyManage.mcolumn_prn));
                String str12 = cursor.getString(cursor.getColumnIndex(MyManage.mcolumn_t1));
                String str13 = cursor.getString(cursor.getColumnIndex(MyManage.mcolumn_t2));
                String str14 = cursor.getString(cursor.getColumnIndex(MyManage.mcolumn_t3));
                String str15 = cursor.getString(cursor.getColumnIndex(MyManage.mcolumn_t4));
                String str16 = cursor.getString(cursor.getColumnIndex(MyManage.mcolumn_t5));
                String str17 = cursor.getString(cursor.getColumnIndex(MyManage.mcolumn_t6));
                String str18 = cursor.getString(cursor.getColumnIndex(MyManage.mcolumn_t7));
                String str19 = cursor.getString(cursor.getColumnIndex(MyManage.mcolumn_t8));
                String str20 = cursor.getString(cursor.getColumnIndex(MyManage.mcolumn_datetimecanceled));

                OkHttpClient okHttpClient = new OkHttpClient();
                RequestBody requestBody = new FormEncodingBuilder()
                        .add("isAdd", "true")
                        .add("VN", strAddVN)
                        .add("Main_id", str1)
                        .add("HN", strAddHN)
                        .add(MyManage.mcolumn_Med_id, str2)
                        .add(MyManage.mcolumn_trade_name, str3)
                        .add(MyManage.mcolumn_generic_line, str4)
                        .add(MyManage.mcolumn_amount_tablet, str21)
                        .add(MyManage.mcolumn_which_date_d, str5)
                        .add(MyManage.mcolumn_appearance, str6)
                        .add(MyManage.mcolumn_ea, str7)
                        .add(MyManage.mcolumn_Main_pharmaco, str8)
                        .add(MyManage.mcolumn_startdate, str9)
                        .add(MyManage.mcolumn_finishdate, str10)
                        .add(MyManage.mcolumn_prn, str11)
                        .add(MyManage.mcolumn_t1, str12)
                        .add(MyManage.mcolumn_t2, str13)
                        .add(MyManage.mcolumn_t3, str14)
                        .add(MyManage.mcolumn_t4, str15)
                        .add(MyManage.mcolumn_t5, str16)
                        .add(MyManage.mcolumn_t6, str17)
                        .add(MyManage.mcolumn_t7, str18)
                        .add(MyManage.mcolumn_t8, str19)
                        .add(MyManage.mcolumn_datetimecanceled, str20)
                        .build();

                Request.Builder builder = new Request.Builder();
                Request request = builder.url(strURL).post(requestBody).build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {

                    }

                    @Override
                    public void onResponse(Response response) throws IOException {
                        Log.d("26July16", "mcolumn_id :"+ str1);

                    }
                });
                cursor.moveToNext();
            } //for
            cursor.close();
        }

    }
}
