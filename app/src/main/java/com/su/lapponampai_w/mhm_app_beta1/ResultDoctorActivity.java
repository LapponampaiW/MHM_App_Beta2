package com.su.lapponampai_w.mhm_app_beta1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.DropBoxManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class ResultDoctorActivity extends AppCompatActivity {

    private EditText editText;
    private String emailString, jsonString;

    private static final String urlMainSTRING = "http://www.swiftcodingthai.com/mhm/get_main_where_email.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_doctor);

        editText = (EditText) findViewById(R.id.editText3);



    } //Main Method

    private class SynMainTABLE extends AsyncTask<Void, Void, String> {

        //Explicit
        private String jsonString;
        private Context context;
        private String myEmailString;
        private String strUrlJSON;

        public SynMainTABLE(Context context,
                            String myEmailString,
                            String strUrlJSON) {
            this.context = context;
            this.myEmailString = myEmailString;
            this.strUrlJSON = strUrlJSON;
        }

        @Override
        protected String doInBackground(Void... params) {

            try {
                /*
                OkHttpClient okHttpClient = new OkHttpClient();
                RequestBody requestBody = new FormEncodingBuilder()
                        .add("isAdd", "true")
                        .add("EmailUser", myEmailString)
                        .build();

                Request.Builder builder = new Request.Builder();
                final Request request = builder.url(strUrlJSON).post(requestBody).build();
                Call call = okHttpClient.newCall(request);
                call.enqueue((new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {

                    }

                    @Override
                    public void onResponse(Response response) throws IOException {
                        jsonString = response.body().string();
                    }
                }));
                */
                return jsonString;


            } catch (Exception e) {
                Log.d("6JulyV2", "e doIn ==>" + e.toString());
                return null;
            }

        } // doInBack

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("6JulyV2", "JSON ==> " + s);

        } // onPost
    } // SynMain Class

    public void clickLoadData(View view) {

        emailString = editText.getText().toString().trim();

        //Check Space
        if (emailString.equals("")) {
            Toast.makeText(this,"Have Space",Toast.LENGTH_SHORT).show();
        } else {
            checkEmail();
        }

    } //clickLoad

    private void checkEmail() {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormEncodingBuilder()
                .add("isAdd", "true")
                .add("EmailUser", emailString)
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

                Log.d("6JulyV2", "JSON ==>" + jsonString);


                    updateNewMainTABLE(jsonString);


            } //onResponse
        }));






        //SynMainTABLE synMainTABLE = new SynMainTABLE(this, emailString, urlMainSTRING);
        //synMainTABLE.execute();



    } //checkEmail

    private void updateNewMainTABLE(String jsonString) {


        String strJSON = jsonString;
        Log.d("6JulyV3", "JSON at Update ==>" + strJSON);

        if (strJSON.length() != 4) {
            //email True
            Log.d("6JulyV3", "Email True");

            deleteMainTABLE();

            updateValueFromServerToMain(strJSON);


        } else {

            Log.d("6JulyV3", "Email False");
        }


    } //Update

    private void updateValueFromServerToMain(String strJSON) {

        try {

            JSONArray jsonArray = new JSONArray(strJSON);
            for(int i=0;i<jsonArray.length();i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String strID = jsonObject.getString("id");
                /*
                String strID = jsonObject.getString("id");
                String strID = jsonObject.getString("id");
                String strID = jsonObject.getString("id");
                String strID = jsonObject.getString("id");
                String strID = jsonObject.getString("id");
                String strID = jsonObject.getString("id");
                String strID = jsonObject.getString("id");
                String strID = jsonObject.getString("id");
                String strID = jsonObject.getString("id");
                String strID = jsonObject.getString("id");
                String strID = jsonObject.getString("id");
                String strID = jsonObject.getString("id");
                String strID = jsonObject.getString("id");
                String strID = jsonObject.getString("id");
                String strID = jsonObject.getString("id");
                String strID = jsonObject.getString("id");
                String strID = jsonObject.getString("id");
                */

                MyManage myManage = new MyManage(this);
                //myManage.addValueTomainTABLE();



            }




        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteMainTABLE() {
        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyHelper.DATABASE_NAME, MODE_PRIVATE, null);
        sqLiteDatabase.delete(MyManage.mainTABLE, null, null);
    }

} //Main Class
