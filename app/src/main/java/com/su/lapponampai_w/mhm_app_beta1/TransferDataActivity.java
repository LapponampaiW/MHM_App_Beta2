package com.su.lapponampai_w.mhm_app_beta1;

import android.content.DialogInterface;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class TransferDataActivity extends AppCompatActivity {

    Button button;
    private EditText editText;
    private String vnString, jsonString;
    private String str1,str2,str3,str4,str5,str6,str7,str8,str9,str10,
            str11,str12,str13,str14,str15,str16,str17,str18,str19,str20,str21,str22;

    int iIndex;
    JSONArray jsonArray;

    private static final String urlMainSTRING = "http://www.swiftcodingthai.com/mhm/get_main_where_email_edited.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_data);

        bindWidget();

        clickToTransfer();
        
    }

    private void clickToTransfer() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vnString = editText.getText().toString().trim();

                if (vnString.equals("")) {
                    Toast.makeText(TransferDataActivity.this,"Have Space",Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("6July", "pass1");
                    checkVN();
                }
            }
        });


        
    }

    private void checkVN() {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormEncodingBuilder()
                .add("isAdd", "true")
                .add("VN", vnString)
                .build();

        Request.Builder builder = new Request.Builder();
        final Request request = builder.url(urlMainSTRING).post(requestBody).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue((new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {


            }

            @Override
            public void onResponse(Response response) throws IOException {
                jsonString = response.body().string();
                response.body().close();

                Log.d("6JulyV2", "JSON ==>" + jsonString);

                updateNewMainTABLE(jsonString);

            } //onResponse
        }));

    }

    private void updateNewMainTABLE(String jsonString) {

        String strJSON = jsonString;
        Log.d("6JulyV3", "JSON at Update ==>" + strJSON);

        if (!strJSON.equals("null")) {
            //email True
            Log.d("6JulyV3", "Email True");

            //deleteMainTABLE();


            updateValueFromServerToMain(strJSON);


        } else {

            Log.d("6JulyV3", "Email False");

        }
    }

    private void updateValueFromServerToMain(String strJSON) {

        try {
            jsonArray = new JSONArray(strJSON);
            iIndex = jsonArray.length();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("6July", "iIndex :" + iIndex);


        for(int x = 0;x<iIndex;x++) {

            try {
                JSONObject jsonObject = jsonArray.getJSONObject(x);

                str1 = jsonObject.getString("VN");
                str2 = jsonObject.getString("Main_id");
                str3 = jsonObject.getString(MyManage.mcolumn_Med_id);
                str4 = jsonObject.getString(MyManage.mcolumn_trade_name);
                str5 = jsonObject.getString(MyManage.mcolumn_generic_line);
                str6 = jsonObject.getString("Amount_table");
                str7 = jsonObject.getString("Which_Date_D");
                str8 = jsonObject.getString(MyManage.mcolumn_appearance);
                str9 = jsonObject.getString(MyManage.mcolumn_ea);
                str10 = jsonObject.getString(MyManage.mcolumn_Main_pharmaco);
                str11 = jsonObject.getString(MyManage.mcolumn_startdate);
                str12 = jsonObject.getString(MyManage.mcolumn_finishdate);
                str13 = jsonObject.getString(MyManage.mcolumn_prn);
                str14 = jsonObject.getString(MyManage.mcolumn_t1);
                str15 = jsonObject.getString(MyManage.mcolumn_t2);
                str16 = jsonObject.getString(MyManage.mcolumn_t3);
                str17 = jsonObject.getString(MyManage.mcolumn_t4);
                str18 = jsonObject.getString(MyManage.mcolumn_t5);
                str19 = jsonObject.getString(MyManage.mcolumn_t6);
                str20 = jsonObject.getString(MyManage.mcolumn_t7);
                str21 = jsonObject.getString(MyManage.mcolumn_t8);
                str22 = jsonObject.getString(MyManage.mcolumn_datetimecanceled);

            } catch (Exception e) {
                e.printStackTrace();
            }

            Log.d("6July", "str1 : " + str1);
            Log.d("6July", "str2 : " + str2);
            Log.d("6July", "str3 : " + str3);
            Log.d("6July", "str4 : " + str4);
            Log.d("6July", "str5 : " + str5);
            Log.d("6July", "str6 : " + str6);
            Log.d("6July", "str7 : " + str7);
            Log.d("6July", "str8 : " + str8);
            Log.d("6July", "str9 : " + str9);
            Log.d("6July", "str10 : " + str10);
            Log.d("6July", "str11 : " + str11);
            Log.d("6July", "str12 : " + str12);
            Log.d("6July", "str13 : " + str13);
            Log.d("6July", "str14 : " + str14);
            Log.d("6July", "str15 : " + str15);
            Log.d("6July", "str16 : " + str16);
            Log.d("6July", "str17 : " + str17);
            Log.d("6July", "str18 : " + str18);
            Log.d("6July", "str19 : " + str19);
            Log.d("6July", "str20 : " + str20);
            Log.d("6July", "str21 : " + str21);
            Log.d("6July", "str22 : " + str22);




        } //for





        /*

        try {

            JSONArray jsonArray = new JSONArray(strJSON);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                //String strUnUse1 = jsonObject.getString("id");
                str1 = jsonObject.getString("VN");
                str2 = jsonObject.getString("Main_id");
                //String strUnUse2 = jsonObject.getString("HN");
                str3 = jsonObject.getString(MyManage.mcolumn_Med_id);
                str4 = jsonObject.getString(MyManage.mcolumn_trade_name);
                str5 = jsonObject.getString(MyManage.mcolumn_generic_line);
                str6 = jsonObject.getString(MyManage.mcolumn_amount_tablet);
                str7 = jsonObject.getString(MyManage.mcolumn_which_date_d);
                str8 = jsonObject.getString(MyManage.mcolumn_appearance);
                str9 = jsonObject.getString(MyManage.mcolumn_ea);
                str10 = jsonObject.getString(MyManage.mcolumn_Main_pharmaco);
                str11 = jsonObject.getString(MyManage.mcolumn_startdate);
                str12 = jsonObject.getString(MyManage.mcolumn_finishdate);
                str13 = jsonObject.getString(MyManage.mcolumn_prn);
                str14 = jsonObject.getString(MyManage.mcolumn_t1);
                str15 = jsonObject.getString(MyManage.mcolumn_t2);
                str16 = jsonObject.getString(MyManage.mcolumn_t3);
                str17 = jsonObject.getString(MyManage.mcolumn_t4);
                str18 = jsonObject.getString(MyManage.mcolumn_t5);
                str19 = jsonObject.getString(MyManage.mcolumn_t6);
                str20 = jsonObject.getString(MyManage.mcolumn_t7);
                str21 = jsonObject.getString(MyManage.mcolumn_t8);
                str22 = jsonObject.getString(MyManage.mcolumn_datetimecanceled);



            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        */



    }

    private void bindWidget() {

        button = (Button) findViewById(R.id.btnTransferDataActivity);
        editText = (EditText) findViewById(R.id.editText13);
    }
}
