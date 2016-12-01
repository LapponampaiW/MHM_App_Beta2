package com.su.lapponampai_w.mhm_app_beta1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddCustomMedicine extends AppCompatActivity {

    //Explicit
    TextView textViewWarning;
    Button buttonCancel, buttonOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_custom_medicine);

        bindWidget();

        showView();

        clickButtonCancelOK();


    }

    private void clickButtonCancelOK() {

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void showView() {

        String s = "คำเตือน!!!\nการเพิ่มข้อมูลยาแบบกำหนดเอง จะไม่สามารถตรวจสอบปฏิกิริยาระหว่างยาได้";
        textViewWarning.setText(s);
    }

    private void bindWidget() {
        textViewWarning = (TextView) findViewById(R.id.textView218);
        buttonCancel = (Button) findViewById(R.id.button10);
        buttonOK = (Button) findViewById(R.id.button11);

    }
}
