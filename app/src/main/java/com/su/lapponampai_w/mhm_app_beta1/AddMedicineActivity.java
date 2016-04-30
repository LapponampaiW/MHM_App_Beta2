package com.su.lapponampai_w.mhm_app_beta1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class AddMedicineActivity extends AppCompatActivity {

    //Explicit
    String stringeditTextAddTG;
    MyManage myManage;

    //นำค่าจาก filterAddMed มาเก็บในค่า String[] และ int[] .... ลองเดี่ยวต้องลบ
    String[] stringsTradename, stringsDosage1, stringsUOM1, stringsDosage3, stringsUOM3, stringsappearance, stringsGeneric1, stringsGeneric3;

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

        pressbuttonfilterListView();

    }

    private void pressbuttonfilterListView() {
        buttonFilterListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stringeditTextAddTG = editTextAddTG.getText().toString().trim();

                //ลอง เดี่ยวต้องลบ
                stringsTradename = myManage.filterAddMed(1,stringeditTextAddTG);
                stringsGeneric1 = myManage.filterAddMed(2, stringeditTextAddTG);
                stringsGeneric3 = myManage.filterAddMed(8, stringeditTextAddTG);
                stringsappearance = myManage.filterAddMed(15, stringeditTextAddTG);

                int[] ints = {R.drawable.addmedicine, R.drawable.mainbg};
                int[] intsIndex = new int[stringsTradename.length];


                for (int i = 0; i < stringsTradename.length; i++) {
                    if (stringsappearance[i].equals("1")) {
                        intsIndex[i] = R.drawable.exampletablet;
                    } else if (stringsappearance[i].equals("2")) {
                        intsIndex[i] = R.drawable.mainbg;
                    }
                }

                MyAdaptor myAdaptor = new MyAdaptor(AddMedicineActivity.this, stringsTradename, stringsGeneric1, stringsGeneric3, intsIndex);
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
