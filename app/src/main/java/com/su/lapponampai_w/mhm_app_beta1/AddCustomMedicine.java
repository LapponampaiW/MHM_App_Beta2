package com.su.lapponampai_w.mhm_app_beta1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AddCustomMedicine extends AppCompatActivity {

    //Explicit
    TextView textViewWarning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_custom_medicine);

        bindWidget();

        showView();


    }

    private void showView() {

        String s = "คำเตือน!!!\nการเพิ่มข้อมูลยาแบบกำหนดเอง จะไม่สามารถตรวจสอบปฏิกิริยาระหว่างยาได้";
        textViewWarning.setText(s);
    }

    private void bindWidget() {
        textViewWarning = (TextView) findViewById(R.id.textView218);

    }
}
