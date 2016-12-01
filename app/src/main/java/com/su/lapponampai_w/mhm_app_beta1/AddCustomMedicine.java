package com.su.lapponampai_w.mhm_app_beta1;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.su.lapponampai_w.mhm_app_beta1.AddMedicine2Activity.string1;
import static com.su.lapponampai_w.mhm_app_beta1.AddMedicine2Activity.string10;
import static com.su.lapponampai_w.mhm_app_beta1.AddMedicine2Activity.string11;
import static com.su.lapponampai_w.mhm_app_beta1.AddMedicine2Activity.string12;
import static com.su.lapponampai_w.mhm_app_beta1.AddMedicine2Activity.string13;
import static com.su.lapponampai_w.mhm_app_beta1.AddMedicine2Activity.string14;
import static com.su.lapponampai_w.mhm_app_beta1.AddMedicine2Activity.string15;
import static com.su.lapponampai_w.mhm_app_beta1.AddMedicine2Activity.string16;
import static com.su.lapponampai_w.mhm_app_beta1.AddMedicine2Activity.string17;
import static com.su.lapponampai_w.mhm_app_beta1.AddMedicine2Activity.string2;
import static com.su.lapponampai_w.mhm_app_beta1.AddMedicine2Activity.string3;
import static com.su.lapponampai_w.mhm_app_beta1.AddMedicine2Activity.string4;
import static com.su.lapponampai_w.mhm_app_beta1.AddMedicine2Activity.string5;
import static com.su.lapponampai_w.mhm_app_beta1.AddMedicine2Activity.string6;
import static com.su.lapponampai_w.mhm_app_beta1.AddMedicine2Activity.string7;
import static com.su.lapponampai_w.mhm_app_beta1.AddMedicine2Activity.string8;
import static com.su.lapponampai_w.mhm_app_beta1.AddMedicine2Activity.string9;

public class AddCustomMedicine extends AppCompatActivity {

    //Explicit
    TextView textViewWarning;
    Button buttonCancel, buttonOK;
    ImageView imageView;
    public static Activity activityAddCustomMedicine;
    String[] stringsAppearance;
    EditText editTextTradename, editTextGenericName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_custom_medicine);

        activityAddCustomMedicine = this;

        bindWidget();

        receiveIntent();

        showView();

        clickImageView();

        clickButtonCancelOK();


    }

    private void receiveIntent() {

        string1 = getIntent().getStringExtra("Med_id");
        string2 = getIntent().getStringExtra("Trade_name");
        string3 = getIntent().getStringExtra("Generic_line");
        string4 = getIntent().getStringExtra("Which_Date_D");
        string5 = getIntent().getStringExtra("Appearance");
        string6 = getIntent().getStringExtra("Pharmaco");
        string7 = getIntent().getStringExtra("T1");
        string8 = getIntent().getStringExtra("T2");
        string9 = getIntent().getStringExtra("T3");
        string10 = getIntent().getStringExtra("T4");
        string11 = getIntent().getStringExtra("T5");
        string12 = getIntent().getStringExtra("T6");
        string13 = getIntent().getStringExtra("T7");
        string14 = getIntent().getStringExtra("T8");
        string15 = getIntent().getStringExtra("Amount_tablet");
        string16 = getIntent().getStringExtra("EA");
        string17 = getIntent().getStringExtra("TimesPerDay");
    }

    private void clickImageView() {


        //เปลี่ยน String 5
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setValueEditText_TextView();

                Intent intent = new Intent(getBaseContext(), PopUpChangeAppearance.class);

                intent.putExtra("Med_id",string1);
                intent.putExtra("Trade_name", string2);
                intent.putExtra("Generic_line", string3);
                intent.putExtra("Amount_tablet", string15);
                intent.putExtra("EA", string16);
                intent.putExtra("Which_Date_D", string4);
                intent.putExtra("Appearance", string5);
                intent.putExtra("Pharmaco", string6);
                intent.putExtra("T1",string7);
                intent.putExtra("T2",string8);
                intent.putExtra("T3",string9);
                intent.putExtra("T4",string10);
                intent.putExtra("T5",string11);
                intent.putExtra("T6",string12);
                intent.putExtra("T7",string13);
                intent.putExtra("T8",string14);
                intent.putExtra("TimesPerDay", string17);
                intent.putExtra("From", "AddCustomMedicine");


                startActivity(intent);
            }
        });


    }

    private void setValueEditText_TextView() {

        string2 = editTextTradename.getText().toString().trim();
        string3 = editTextGenericName.getText().toString().trim();

    }

    private void clickButtonCancelOK() {

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (string2.equals("")) {
                    Toast.makeText(getBaseContext(), "โปรดระบุชื่อการค้าของยา", Toast.LENGTH_SHORT).show();
                } else {
                    setValueEditText_TextView();
                    string1 = "N/A"; //แปลว่าไม่มีค่า Med_id
                    if (string5.length() == 7) {
                        string16 = "1";
                    } else if (string5.length() == 8) {
                        string16 = "2";
                    }
                    Intent intent = new Intent(getBaseContext(), AddMedicine2Activity.class);
                    intent.putExtra("Med_id",string1); //set
                    intent.putExtra("Trade_name", string2); //set
                    intent.putExtra("Generic_line", string3); //set
                    intent.putExtra("Amount_tablet", "1");
                    intent.putExtra("EA", string16); //set
                    intent.putExtra("Which_Date_D", "ED:0");
                    intent.putExtra("Appearance", string5); //set
                    intent.putExtra("Pharmaco", "");
                    intent.putExtra("T1","08:00");
                    intent.putExtra("T2","");
                    intent.putExtra("T3","");
                    intent.putExtra("T4","");
                    intent.putExtra("T5","");
                    intent.putExtra("T6","");
                    intent.putExtra("T7","");
                    intent.putExtra("T8","");
                    intent.putExtra("TimesPerDay", "1");
                    startActivity(intent);
                    finish();
                }

            }
        });

    }

    private void showView() {
        MyData myData = new MyData();

        String s = "คำเตือน!!!\nการเพิ่มข้อมูลยาแบบกำหนดเอง จะไม่สามารถตรวจสอบปฏิกิริยาระหว่างยาได้";
        textViewWarning.setText(s);
        textViewWarning.setVisibility(View.GONE);

        if (!string5.equals("")) {
            stringsAppearance = new String[1];
            stringsAppearance[0] = string5;
            int[] intsIndex = myData.translate_Appearance(stringsAppearance);
            imageView.setBackgroundResource(intsIndex[0]);
        }

        if (!string2.equals("")) {
            editTextTradename.setText(string2);
        }
        if (!string3.equals("")) {
            editTextGenericName.setText(string3);
        }

    }

    private void bindWidget() {
        textViewWarning = (TextView) findViewById(R.id.textView218);
        buttonCancel = (Button) findViewById(R.id.button10);
        buttonOK = (Button) findViewById(R.id.button11);
        imageView = (ImageView) findViewById(R.id.imageView28);
        editTextTradename = (EditText) findViewById(R.id.editText20);
        editTextGenericName = (EditText) findViewById(R.id.editText21);

    }
}
