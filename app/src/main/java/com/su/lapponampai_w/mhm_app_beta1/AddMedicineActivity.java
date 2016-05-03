package com.su.lapponampai_w.mhm_app_beta1;

import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class AddMedicineActivity extends AppCompatActivity {

    //Explicit
    String stringeditTextAddTG;
    MyManage myManage;

    //นำค่าจาก filterAddMed
    String[] stringsTradename, stringsappearance, stringsGeneric1, stringsGeneric3, stringsGeneric4,
            stringsDosage1, stringsDosage2, stringsDosage3, stringsDosage4, stringsUOM1,
            stringsUOM2, stringsUOM3, stringsUOM4, stringsGeneric2;


    //widget
    EditText editTextAddTG;
    ListView listViewAddTG;
    Button buttonFilterListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine);

        myManage = new MyManage(this);

        bindWidget();


        //pressbuttonfilterListView1();
        pressbuttonfilterListView();


    }

    private void pressbuttonfilterListView() {
        buttonFilterListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stringeditTextAddTG = editTextAddTG.getText().toString().trim();
                //รับค่า
                stringsTradename = myManage.filterAddMed(1, stringeditTextAddTG);
                stringsGeneric1 = myManage.filterAddMed(3, stringeditTextAddTG);
                stringsDosage1 = myManage.filterAddMed(4, stringeditTextAddTG);
                stringsUOM1 = myManage.filterAddMed(5, stringeditTextAddTG);
                stringsGeneric2 = myManage.filterAddMed(6, stringeditTextAddTG);
                stringsDosage2 = myManage.filterAddMed(7, stringeditTextAddTG);
                stringsUOM2 = myManage.filterAddMed(8, stringeditTextAddTG);
                stringsGeneric3 = myManage.filterAddMed(9, stringeditTextAddTG);
                stringsDosage3 = myManage.filterAddMed(10, stringeditTextAddTG);
                stringsUOM3 = myManage.filterAddMed(11, stringeditTextAddTG);
                stringsGeneric4 = myManage.filterAddMed(12, stringeditTextAddTG);
                stringsDosage4 = myManage.filterAddMed(13, stringeditTextAddTG);
                stringsUOM4 = myManage.filterAddMed(14, stringeditTextAddTG);
                stringsappearance = myManage.filterAddMed(16, stringeditTextAddTG);

                //แปลค่า
                MyData myData = new MyData();

                stringsGeneric1 = myManage.translate_GenericName(stringsGeneric1);
                stringsGeneric2 = myManage.translate_GenericName(stringsGeneric2);
                stringsGeneric3 = myManage.translate_GenericName(stringsGeneric3);
                stringsGeneric4 = myManage.translate_GenericName(stringsGeneric4);

                stringsUOM1 = myData.translate_uom(stringsUOM1);

                //มีปัญหาตรงนี้

                if (stringsUOM2!=null) {
                    stringsUOM2 = myData.translate_uom(stringsUOM2);
                }
                if (stringsUOM3!=null) {
                    stringsUOM3 = myData.translate_uom(stringsUOM3);
                }

                if (stringsUOM4.length==0) {
                    stringsUOM4 = myData.translate_uom(stringsUOM4);
                }




                String[] stringsGenericLine1 = new String[stringsGeneric1.length];
                for(int i = 0;i < stringsGeneric1.length;i++) {
                    if (stringsGeneric2[i].equals("N/A")) {
                        stringsGenericLine1[i] = stringsGeneric1[i] + " " + stringsDosage1[i] + " " + stringsUOM1[i];
                    } else if (stringsGeneric3[i].equals("N/A")) {
                        stringsGenericLine1[i] = stringsGeneric1[i] + " " + stringsDosage1[i] +
                                " " + stringsUOM1[i] + " / " + stringsGeneric2[i] + " " +
                                stringsDosage2[i] + " " + stringsUOM2[i];
                    } else if (stringsGeneric4[i].equals("N/A")) {
                        stringsGenericLine1[i] = stringsGeneric1[i] + " " + stringsDosage1[i] +
                                " " + stringsUOM1[i] + " / " + stringsGeneric2[i] + " " +
                                stringsDosage2[i] + " " + stringsUOM2[i] + " / " +
                                stringsGeneric3[i] + " " + stringsDosage3[i] + " " +
                                stringsUOM3[i];
                    } else {
                        stringsGenericLine1[i] = stringsGeneric1[i] + " " + stringsDosage1[i] +
                                " " + stringsUOM1[i] + " / " + stringsGeneric2[i] + " " +
                                stringsDosage2[i] + " " + stringsUOM2[i] + " / " +
                                stringsGeneric3[i] + " " + stringsDosage3[i] + " " +
                                stringsUOM3[i] + " / " + stringsGeneric4[i] + " " +
                                stringsDosage4[i] + " " + stringsUOM4[i];
                    }

                } //for

                //ใส่ภาพเข้าไป
                int[] intsIndex = myData.translate_Appearance(stringsappearance);

                MyAdaptor myAdaptor = new MyAdaptor(AddMedicineActivity.this, stringsTradename, stringsGenericLine1, intsIndex);
                listViewAddTG.setAdapter(myAdaptor);


            }
        });
    }

    private void pressbuttonfilterListView1() {

        buttonFilterListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stringeditTextAddTG = editTextAddTG.getText().toString().trim();
                //รับค่า
                stringsTradename = myManage.filterAddMed(1, stringeditTextAddTG);
                stringsGeneric1 = myManage.filterAddMed(3, stringeditTextAddTG);
                stringsDosage1 = myManage.filterAddMed(4, stringeditTextAddTG);
                stringsUOM1 = myManage.filterAddMed(5, stringeditTextAddTG);
                stringsGeneric2 = myManage.filterAddMed(6, stringeditTextAddTG);
                stringsDosage2 = myManage.filterAddMed(7, stringeditTextAddTG);
                stringsUOM2 = myManage.filterAddMed(8, stringeditTextAddTG);
                stringsGeneric3 = myManage.filterAddMed(9, stringeditTextAddTG);
                stringsDosage3 = myManage.filterAddMed(10, stringeditTextAddTG);
                stringsUOM3 = myManage.filterAddMed(11, stringeditTextAddTG);
                stringsGeneric4 = myManage.filterAddMed(12, stringeditTextAddTG);
                stringsDosage4 = myManage.filterAddMed(13, stringeditTextAddTG);
                stringsUOM4 = myManage.filterAddMed(14, stringeditTextAddTG);
                stringsappearance = myManage.filterAddMed(16, stringeditTextAddTG);


                //แปลค่า
                MyData myData = new MyData();


                stringsGeneric1 = myManage.translate_GenericName(stringsGeneric1);
                stringsGeneric2 = myManage.translate_GenericName(stringsGeneric2);
                stringsGeneric3 = myManage.translate_GenericName(stringsGeneric3);
                stringsGeneric4 = myManage.translate_GenericName(stringsGeneric4);

                stringsUOM1 = myData.translate_uom(stringsUOM1);
                stringsUOM2 = myData.translate_uom(stringsUOM2);
                stringsUOM3 = myData.translate_uom(stringsUOM3);
                stringsUOM4 = myData.translate_uom(stringsUOM4);

                //ทำ String ยาวๆ ของ 2 บรรทัด
                String[] stringsGenericLine1 = new String[stringsGeneric1.length];
                for(int i = 0;i < stringsGeneric1.length;i++) {
                    if (!stringsGeneric4.equals("")) {
                        stringsGenericLine1[i] = stringsGeneric1[i] + " " + stringsDosage1[i] +
                                " " + stringsUOM1[i] + " / " + stringsGeneric2[i] + " " +
                                stringsDosage2[i] + " " + stringsUOM2[i] + " / " +
                                stringsGeneric3[i] + " " + stringsDosage3[i] + " " +
                                stringsUOM3[i] + " / " + stringsGeneric4[i] + " " +
                                stringsDosage4[i] + " " + stringsUOM4[i];
                    } else if (!stringsGeneric3.equals("")){
                        stringsGenericLine1[i] = stringsGeneric1[i] + " " + stringsDosage1[i] +
                                " " + stringsUOM1[i] + " / " + stringsGeneric2[i] + " " +
                                stringsDosage2[i] + " " + stringsUOM2[i] + " / " +
                                stringsGeneric3[i] + " " + stringsDosage3[i] + " " +
                                stringsUOM3[i];
                    } else if (!stringsGeneric2.equals("")) {
                        stringsGenericLine1[i] = stringsGeneric1[i] + " " + stringsDosage1[i] +
                                " " + stringsUOM1[i] + " / " + stringsGeneric2[i] + " " +
                                stringsDosage2[i] + " " + stringsUOM2[i];
                    } else {
                        stringsGenericLine1[i] = stringsGeneric1[i] + " " + stringsDosage1[i] + " " + stringsUOM1[i];
                    }
                }


                int[] intsIndex = myData.translate_Appearance(stringsappearance);


                MyAdaptor myAdaptor = new MyAdaptor(AddMedicineActivity.this, stringsTradename, stringsGenericLine1, intsIndex);
                listViewAddTG.setAdapter(myAdaptor);

            }
        });
    }



    private void bindWidget() {
        editTextAddTG = (EditText) findViewById(R.id.editText_Add_TG);
        listViewAddTG = (ListView) findViewById(R.id.listView_Add_TG);
        buttonFilterListView = (Button) findViewById(R.id.buttonFilterListView);
    }
}
