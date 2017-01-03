package com.su.lapponampai_w.mhm_app_beta1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class AlertActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);

        bindWidget();

        showViewAndSetListView();




    }

    private void showViewAndSetListView() {
    }


    private void bindWidget() {
        listView = (ListView) findViewById(R.id.listViewAlertActivity);



    }
}
