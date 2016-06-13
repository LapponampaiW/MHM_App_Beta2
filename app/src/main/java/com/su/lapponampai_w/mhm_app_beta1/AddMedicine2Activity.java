package com.su.lapponampai_w.mhm_app_beta1;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class AddMedicine2Activity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener,DatePickerDialog.OnDateSetListener{

    //Explicit
    private TextView textView1, textView2, textView3, textView4,
            textView5, textView6, textView7, textView8, textView9,
            textView10, textView11, textView12, textView13,
            textView14, textView15, textView16, textViewStartDate, textViewFinishDate;

    private String string1, string2, string3, string4, string5, string6,
            string7, string8, string9, string10, string11, string12,
            string13, string14, string15, string16, string17;
    private String string18,string19; //StartDate,FinishDate

    private CheckBox checkBox1, checkBox2, checkBox3, checkBox4,checkBox5,checkBox6;

    private EditText editText1;

    private ImageView imageView;

    private String string16_Translate;

    private String stringInteraction2;
    private String stringTime;

    private String[] strings0, strings2, strings3, strings4, strings5, strings6, strings1,
            strings7, stringGenericName2, stringsduplicate;
    private String[] stringsAppearance;
    private LinearLayout linearLayout1, linearLayout2, linearLayout3, linearLayout4,
            linearLayout5, linearLayout6, linearLayout7, linearLayout8, startDatelin,finishDatelin;


    private int pickerHour;
    private int pickerMin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine2);

        //BindWidget
        bindWidget();

        //Receive From Intent
        receiveIntent();

        //Show View
        showView();

        //click CheckBox
        clickCheckBox();

        //Click Amount_Tablet
        clickAmount_Tablet();

        //Click เพื่อใส่เวลา
        clickTimesAndDateTextView();



    } //Main Method

    @Override //จาก DatePickerFragment
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        MyData myData = new MyData();
        int pickYear = year;
        int pickMonthOfYear = monthOfYear;
        int pickDayOfMonth = dayOfMonth;

        Log.d("testTime", "DatePickerFragment = " + pickDayOfMonth + " " + pickMonthOfYear + " " + pickYear);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date_Specific = new Date();
        Date date = new Date();
        Date date_Start = new Date();
        Date date_Finish = new Date();

        String sSpecificDate = myData.createStringDay(pickDayOfMonth, pickMonthOfYear + 1, pickYear);
        String sDate = myData.currentDay();
        Log.d("testTime", "sSpecific = " + sSpecificDate);

        try {
            date_Specific = dateFormat.parse(sSpecificDate);
            date = dateFormat.parse(sDate);
            date_Start = dateFormat.parse(string18);
            if (!string19.equals("")) {
                date_Finish = dateFormat.parse(string19);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (date_Specific.compareTo(date) > 0) {
            //เงื่อนไขวันที่มากกว่าเท่ากับปัจจุบัน
            if (stringTime.equals("StartDate")) {
                if (!string19.equals("")) {
                    if (date_Specific.compareTo(date_Finish) > 0) {
                        Toast.makeText(AddMedicine2Activity.this,"ไม่สามารถตั้งวันเริ่มต้นให้มากกว่าวันที่สิ้นสุดรับประทานได้",Toast.LENGTH_SHORT).show();
                    }

                } else {
                    textViewStartDate.setText(sSpecificDate);
                    string18 = sSpecificDate;
                }
            }

            if (stringTime.equals("FinishDate")) {
                if (date_Specific.compareTo(date_Start) >= 0) {
                    textViewFinishDate.setText(sSpecificDate);
                    string19 = sSpecificDate;
                } else {
                    Toast.makeText(AddMedicine2Activity.this,"ไม่สามารถตั้งวันสิ้นสุดให้น้อยกว่าวันที่เริ่มต้นรับประทานได้",Toast.LENGTH_SHORT).show();
                }
            }
        } else { //ถ้าตั้งวันน้อยกว่าวันที่ปัจจุบัน
            Toast.makeText(AddMedicine2Activity.this, "ไม่สามารถตั้งเวลารับประทานเป็นอดีตได้", Toast.LENGTH_SHORT).show();
        }


    }

    @Override //การนำค่าเวลามาอยู่ในค่า Int //จาก TimePickerFragment
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        MyData myData = new MyData();
        pickerHour = hourOfDay;
        pickerMin = minute;
        Log.d("testTime", "hourOfDay = " + Integer.toString(pickerHour) + "minute = " + Integer.toString(pickerMin));
        if (stringTime.equals("T1")) {
            string7 = myData.createStringTime(pickerHour, pickerMin);
            textView7.setText(string7);
        } else if (stringTime.equals("T2")) {
            string8 = myData.createStringTime(pickerHour, pickerMin);
            textView8.setText(string8);
        } else if (stringTime.equals("T3")) {
            string9 = myData.createStringTime(pickerHour, pickerMin);
            textView9.setText(string9);
        } else if (stringTime.equals("T4")) {
            string10 = myData.createStringTime(pickerHour, pickerMin);
            textView10.setText(string10);
        } else if (stringTime.equals("T5")) {
            string11 = myData.createStringTime(pickerHour, pickerMin);
            textView11.setText(string11);
        } else if (stringTime.equals("T6")) {
            string12 = myData.createStringTime(pickerHour, pickerMin);
            textView12.setText(string12);
        } else if (stringTime.equals("T7")) {
            string13 = myData.createStringTime(pickerHour, pickerMin);
            textView13.setText(string13);
        } else if (stringTime.equals("T8")) {
            string14 = myData.createStringTime(pickerHour, pickerMin);
            textView14.setText(string14);
        }
    }


    private void clickTimesAndDateTextView() {

        //T1
        textView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stringTime = "T1";
                showTimePickerDialog(v);
            }
        });
        //T2
        textView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stringTime = "T2";
                showTimePickerDialog(v);
            }
        });
        //T3
        textView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stringTime = "T3";
                showTimePickerDialog(v);
            }
        });
        //T4
        textView10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stringTime = "T4";
                showTimePickerDialog(v);
            }
        });
        //T5
        textView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stringTime = "T5";
                showTimePickerDialog(v);
            }
        });
        //T6
        textView12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stringTime = "T6";
                showTimePickerDialog(v);
            }
        });
        //T7
        textView13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stringTime = "T7";
                showTimePickerDialog(v);
            }
        });
        //T8
        textView14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stringTime = "T8";
                showTimePickerDialog(v);
            }
        });


        //StartDate
        textViewStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stringTime = "StartDate";
                showDatePickerDialog(v);
            }
        });

        //FinishDate
        textViewFinishDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stringTime = "FinishDate";
                showDatePickerDialog(v);
            }
        });

    }

    public void showDatePickerDialog(View v) {
        MyDatePickerFragment myDatePickerFragment = new MyDatePickerFragment();
        myDatePickerFragment.show(getFragmentManager(), "datePicker");
    }

    public void showTimePickerDialog(View v) {

        MyTimePickerFragment myTimePickerFragment = new MyTimePickerFragment();
        myTimePickerFragment.show(getFragmentManager(), "timePicker");

    }



    private void clickAmount_Tablet() {
        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(v.getContext());
                final String[] strings = {"1","2","3","4","5","6","7","8"};
                builder.setTitle("โปรดระบุ!!! \nจำนวนครั้งที่ต้องทานยา(ใน 1 วัน)");
                builder.setSingleChoiceItems(strings, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int i = which + 1;
                        textView4.setText(Integer.toString(i));
                        //Toast.makeText(getApplicationContext(), Integer.toString(i), Toast.LENGTH_LONG).show();
                        if (i >= 8) {
                            linearLayout8.setVisibility(View.VISIBLE);
                        } else {
                            linearLayout8.setVisibility(View.GONE);
                            textView14.setText("");
                            string14 = "";
                        }
                        if (i >= 7) {
                            linearLayout7.setVisibility(View.VISIBLE);
                        } else {
                            linearLayout7.setVisibility(View.GONE);
                            textView13.setText("");
                            string13 = "";
                        }
                        if (i >= 6) {
                            linearLayout6.setVisibility(View.VISIBLE);
                        } else {
                            linearLayout6.setVisibility(View.GONE);
                            textView12.setText("");
                            string12 = "";
                        }
                        if (i >= 5) {
                            linearLayout5.setVisibility(View.VISIBLE);
                        } else {
                            linearLayout5.setVisibility(View.GONE);
                            textView11.setText("");
                            string11 = "";
                        }
                        if (i >= 4) {
                            linearLayout4.setVisibility(View.VISIBLE);
                        } else {
                            linearLayout4.setVisibility(View.GONE);
                            textView10.setText("");
                            string10 = "";
                        }
                        if (i >= 3) {
                            linearLayout3.setVisibility(View.VISIBLE);
                        } else {
                            linearLayout3.setVisibility(View.GONE);
                            textView9.setText("");
                            string9 = "";
                        }
                        if (i >= 2) {
                            linearLayout2.setVisibility(View.VISIBLE);
                        } else {
                            linearLayout2.setVisibility(View.GONE);
                            textView8.setText("");
                            string8 = "";
                        }
                        if (i >= 1) {
                            linearLayout1.setVisibility(View.VISIBLE);
                        } else {
                            linearLayout1.setVisibility(View.GONE);
                            textView7.setText("");
                            string7 = "";
                        }




                        dialog.dismiss();
                    }
                });

                builder.show();

            }
        });

    }

    private void clickCheckBox() {

        //Click CheckBox1
        checkBox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox1.isChecked()) {
                    checkBox2.setChecked(false);
                    checkBox3.setChecked(false);
                    checkBox4.setChecked(false);
                    string4 = "ED:0";  //เก็บค่าไว้ใน string4 เหมือนเดิม
                    textView1.setVisibility(View.INVISIBLE);

                }

            }
        });


        //Click CheckBox2 ... Day of Week (DOW:)
        checkBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox2.isChecked()) {
                    checkBox1.setChecked(false);
                    checkBox3.setChecked(false);
                    checkBox4.setChecked(false);


                    final ArrayList<Integer> arrayList = new ArrayList<Integer>();
                    final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(v.getContext());
                    final String[] strings = {"วันอาทิตย์", "วันจันทร์", "วันอังคาร", "วันพุธ", "วันพฤหัสบดี", "วันศุกร์", "วันเสาร์"};
                    builder.setTitle("โปรดเลือกวันที่รับประทาน");
                    builder.setMultiChoiceItems(strings, null, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                            if (isChecked) {
                                arrayList.add(which);
                            } else if (arrayList.contains(which)) {
                                arrayList.remove(Integer.valueOf(which));
                            }
                        }
                    });
                    builder.setPositiveButton("เลือกรายการ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int intWhich) {

                            Integer[] integers = new Integer[arrayList.size()];
                            integers = arrayList.toArray(integers);
                            Arrays.sort(integers);

                            if (arrayList.size() == 0) {
                                string4 = "";
                                checkBox2.setChecked(false);
                                Toast.makeText(getApplicationContext(), "โปรดเลือกวันที่ต้องการทานยา", Toast.LENGTH_SHORT).show();
                                textView1.setVisibility(View.INVISIBLE);
                            } else {
                                StringBuffer stringBuffer = new StringBuffer("วันที่ทานยา : ");
                                StringBuffer stringBufferCode = new StringBuffer("DOW:");
                                for (int i = 0; i < arrayList.size(); i++) {
                                    Log.d("Which", Integer.toString(integers[i]));
                                    for (int w = 0; w < strings.length; w++) {
                                        if (integers[i] == w) {
                                            stringBuffer.append(strings[w]);
                                            stringBuffer.append(" ");

                                            stringBufferCode.append(Integer.toString(integers[i]));
                                            stringBufferCode.append(",");
                                        }
                                    }
                                }
                                textView1.setText(stringBuffer);
                                textView1.setVisibility(View.VISIBLE);

                                String sCode = stringBufferCode.toString();
                                Log.d("Which", "sCode = " + sCode);
                                sCode = sCode.substring(0, sCode.length() - 1);
                                Log.d("Which", "sCode = " + sCode);
                                string4 = sCode;
                            }

                        }
                    });
                    builder.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            string4 = "";
                            checkBox2.setChecked(false);
                        }
                    });

                    builder.show();
                } else {
                    string4 = "";
                    textView1.setVisibility(View.INVISIBLE);
                    Log.d("Which", "string 4 :" + string4);
                }
            }
        });

        //Click CheckBox3 ... Day of Month (DOM:)
        checkBox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox3.isChecked()) {
                    checkBox1.setChecked(false);
                    checkBox2.setChecked(false);
                    checkBox4.setChecked(false);


                    final ArrayList<Integer> arrayList = new ArrayList<Integer>();
                    final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(v.getContext());
                    final String[] strings = new String[28];
                    for (int i = 0; i < 28; i++) {
                        strings[i] = Integer.toString(i + 1);
                    }
                    builder.setTitle("โปรดเลือกวันที่รับประทานในเดือนนั้น");
                    builder.setMultiChoiceItems(strings, null, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                            if (isChecked) {
                                arrayList.add(which);
                            } else if (arrayList.contains(which)) {
                                arrayList.remove(Integer.valueOf(which));
                            }

                        }
                    });
                    builder.setPositiveButton("เลือกรายการ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Integer[] integers = new Integer[arrayList.size()];
                            integers = arrayList.toArray(integers);
                            Arrays.sort(integers);

                            if (arrayList.size() == 0) {
                                string4 = "";
                                checkBox3.setChecked(false);
                                Toast.makeText(getApplicationContext(), "โปรดเลือกวันที่ต้องการทานยา", Toast.LENGTH_SHORT).show();
                                textView1.setVisibility(View.INVISIBLE);
                            } else {
                                StringBuffer stringBuffer = new StringBuffer("วันที่ทานยา : ");
                                StringBuffer stringBufferCode = new StringBuffer("DOM:");

                                for (int i = 0; i < arrayList.size(); i++) {
                                    Log.d("Which", Integer.toString(integers[i]));
                                    for (int w = 0; w < strings.length; w++) {
                                        if (integers[i] == w) {
                                            stringBuffer.append(strings[w]);
                                            stringBuffer.append(", ");

                                            stringBufferCode.append(Integer.toString(integers[i] + 1));  //DOM: ตัวเลขเป็นเลขวันไปเลยนะ
                                            stringBufferCode.append(",");
                                        }
                                    }
                                }
                                String s = stringBuffer.toString();
                                s = s.substring(0, s.length() - 2);
                                s = s.concat(" ของทุกเดือน");

                                textView1.setText(s);
                                textView1.setVisibility(View.VISIBLE);

                                String sCode = stringBufferCode.toString();
                                Log.d("Which", "sCode = " + sCode);
                                sCode = sCode.substring(0, sCode.length() - 1);
                                Log.d("Which", "sCode = " + sCode);
                                string4 = sCode;

                            }
                        }
                    });
                    builder.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            string4 = "";
                            checkBox3.setChecked(false);
                        }
                    });
                    builder.show();
                } else {
                    string4 = "";
                    textView1.setVisibility(View.INVISIBLE);
                    Log.d("Which", "string 4 :" + string4);
                }
            }
        });


        //Click CheckBox4 ... Every...day (ED:1-5)
        checkBox4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox4.isChecked()) {
                    checkBox1.setChecked(false);
                    checkBox2.setChecked(false);
                    checkBox3.setChecked(false);


                    final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(v.getContext());
                    final String[] strings = {"วันเว้นวัน", "1 วันเว้น 2 วัน(ทุก 3 วัน)", "1 วันเว้น 3 วัน(ทุก 4 วัน)", "1 วันเว้น 4 วัน(ทุก 5 วัน)", "1 วันเว้น 5 วัน(ทุก 6 วัน)"};
                    builder.setTitle("โปรดเลือกวันที่รับประทาน");
                    builder.setSingleChoiceItems(strings, -1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String s = strings[which];
                            if (which == 0) {
                                string4 = "ED:1";
                            } else if (which == 1) {
                                string4 = "ED:2";
                            } else if (which == 2) {
                                string4 = "ED:3";
                            } else if (which == 3) {
                                string4 = "ED:4";
                            } else if (which == 4) {
                                string4 = "ED:5";
                            }
                            textView1.setVisibility(View.VISIBLE);
                            textView1.setText(s);

                            Log.d("Which", "string 4 :" + string4);
                            dialog.dismiss();
                        }
                    });

                    builder.show();

                } else {
                    string4 = "";
                    textView1.setVisibility(View.INVISIBLE);
                    Log.d("Which", "string 4 :" + string4);
                }
            }
        });


        //Click CheckBox5
        checkBox5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox5.isChecked()) {
                    checkBox6.setChecked(false);
                    finishDatelin.setVisibility(View.VISIBLE);
                    textViewFinishDate.setText("");
                } else {
                    finishDatelin.setVisibility(View.GONE);
                    textViewFinishDate.setText("");
                    string19 = "";
                }
            }
        });

        //Click CheckBox6
        checkBox6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox6.isChecked()) {
                    checkBox5.setChecked(false);
                    finishDatelin.setVisibility(View.GONE);
                    textViewFinishDate.setText("");
                    string19 = "";
                }
            }
        });

    }

    private void showView() {
        MyData myData = new MyData();

        textView1.setVisibility(View.INVISIBLE);
        //ลบ textView1 ที่เป็น Med_id ออกแต่ยังคงเก็บค่าไว้ที่ String 1
        textView2.setText(string2);
        textView3.setText(string3);
        //textView4.setText(string4);
        //textView5.setText(string5);
        stringsAppearance = new String[1];
        stringsAppearance[0] = string5;
        int[] intsIndex = myData.translate_Appearance(stringsAppearance);
        imageView.setBackgroundResource(intsIndex[0]);
        //textView6.setText(string6);

        String[] stringsWhich_Date_D = myData.translate_Which_Date_D(string4);
        if (stringsWhich_Date_D[0].equals("1")) {
            checkBox1.setChecked(true);
        }
        textView4.setText(string17);

        textView7.setText(string7); //T1
        textView8.setText(string8); //T2
        textView9.setText(string9); //T3
        textView10.setText(string10); //T4
        textView11.setText(string11); //T5
        textView12.setText(string12); //T6
        textView13.setText(string13); //T7
        textView14.setText(string14); //T8

        //ทำให้ปรากฎตามจำนวนครั้งที่ต้องรับประทาน
        if (string14.equals("")) {
            linearLayout8.setVisibility(View.GONE);
        }
        if (string13.equals("")) {
            linearLayout7.setVisibility(View.GONE);
        }
        if (string12.equals("")) {
            linearLayout6.setVisibility(View.GONE);
        }
        if (string11.equals("")) {
            linearLayout5.setVisibility(View.GONE);
        }
        if (string10.equals("")) {
            linearLayout4.setVisibility(View.GONE);
        }
        if (string9.equals("")) {
            linearLayout3.setVisibility(View.GONE);
        }
        if (string8.equals("")) {
            linearLayout2.setVisibility(View.GONE);
        }
        if (string7.equals("")) {
            linearLayout1.setVisibility(View.GONE);
        }
        //linearLayout1.setVisibility(View.GONE);

        //textView15.setText(string15);
        editText1.setText(string15);
        string16_Translate = myData.translate_EA(string16);
        textView16.setText(string16_Translate);

        //checkBox 5,6 (ทานยาเป็นช่วง หรือต่อเนื่อง Default)
        checkBox5.setChecked(false);
        checkBox6.setChecked(true);
        finishDatelin.setVisibility(View.GONE);
        textViewStartDate.setText(myData.currentDay());
        Log.d("testTime",myData.currentDay());





    }

    private void receiveIntent() {
        MyData myData = new MyData();

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
        string18 = myData.currentDay();
        string19 = "";


    }

    private void bindWidget() {

        textView1 = (TextView) findViewById(R.id.textViewWhich_Date_D2);
        textView2 = (TextView) findViewById(R.id.textView12);
        textView3 = (TextView) findViewById(R.id.textView14);
        //textView4 = (TextView) findViewById(R.id.textView16); เอาวันที่รับประทาน ออก Which_Date
        textView4 = (TextView) findViewById(R.id.textView54);
        imageView = (ImageView) findViewById(R.id.imageViewAppearance);
        //textView5 = (TextView) findViewById(R.id.textView18);  จะเปลี่ยนให้เป็น StartDate
        //textView6 = (TextView) findViewById(R.id.textView20); จะเปลี่ยนให้เป็น FinishDate
        textView7 = (TextView) findViewById(R.id.textView22);
        textView8 = (TextView) findViewById(R.id.textView24);
        textView9 = (TextView) findViewById(R.id.textView26);
        textView10 = (TextView) findViewById(R.id.textView28);
        textView11 = (TextView) findViewById(R.id.textView30);
        textView12 = (TextView) findViewById(R.id.textView32);
        textView13 = (TextView) findViewById(R.id.textView34);
        textView14 = (TextView) findViewById(R.id.textView36);
        //textView15 = (TextView) findViewById(R.id.textView38);  เปลี่ยน Amount_Tablet ไปเป็น Edittext
        textViewStartDate = (TextView) findViewById(R.id.textViewStartDate);
        textViewFinishDate = (TextView) findViewById(R.id.textViewFinishDate);
        editText1 = (EditText) findViewById(R.id.editText1);
        textView16 = (TextView) findViewById(R.id.textView46);
        checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        checkBox3 = (CheckBox) findViewById(R.id.checkBox3);
        checkBox4 = (CheckBox) findViewById(R.id.checkBox4);
        checkBox5 = (CheckBox) findViewById(R.id.checkBox5);
        checkBox6 = (CheckBox) findViewById(R.id.checkBox6);
        linearLayout1 = (LinearLayout) findViewById(R.id.t1Layout);
        linearLayout2 = (LinearLayout) findViewById(R.id.t2Layout);
        linearLayout3 = (LinearLayout) findViewById(R.id.t3Layout);
        linearLayout4 = (LinearLayout) findViewById(R.id.t4Layout);
        linearLayout5 = (LinearLayout) findViewById(R.id.t5Layout);
        linearLayout6 = (LinearLayout) findViewById(R.id.t6Layout);
        linearLayout7 = (LinearLayout) findViewById(R.id.t7Layout);
        linearLayout8 = (LinearLayout) findViewById(R.id.t8Layout);
        startDatelin = (LinearLayout) findViewById(R.id.startDateLin);
        finishDatelin = (LinearLayout) findViewById(R.id.finishDateLin);


    }

    public void clickCancelAddMedicine(View view) {
        finish();
    }

    public void clickSaveAddMedicine(View view) {

        MyManage myManage = new MyManage(this);

        //เริ่มจากตรงนี้นะ

        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyHelper.DATABASE_NAME, MODE_PRIVATE, null);
        sqLiteDatabase.delete("drugInteractionTABLE_For_Query", null, null);


        myManage.checkDrugInteraction(string1);


        int countRowTABLE_Query = myManage.filter_drugInteractionTABLE_For_Query(0).length;
        Log.d("filter_drugInteraction", Integer.toString(countRowTABLE_Query));


        if (countRowTABLE_Query != 0) {
            strings0 = myManage.filter_drugInteractionTABLE_For_Query(0);
            strings1 = myManage.filter_drugInteractionTABLE_For_Query(1);
            strings2 = myManage.filter_drugInteractionTABLE_For_Query(2);
            strings3 = myManage.filter_drugInteractionTABLE_For_Query(3);
            strings4 = myManage.filter_drugInteractionTABLE_For_Query(4);
            strings5 = myManage.filter_drugInteractionTABLE_For_Query(5);
            strings6 = myManage.filter_drugInteractionTABLE_For_Query(6);
            strings7 = myManage.filter_drugInteractionTABLE_For_Query(7);


            Log.d("filter_drugInteraction", strings3[0]);


            if (strings4[0].equals("1")) {
                alertDialogFaltal();
                Log.d("filter_drugInteraction", "type 1:");
                return;
            } else {
                for (int i = 0; i < strings0.length; i++) {
                    if (strings4[i].equals("2")) {

                        Log.d("filter_drugInteraction", "type2 :" + strings0[i] + " :" + strings1[i] +
                                " :" + strings2[i] + " :" + strings3[i] + " :" + strings4[i] +
                                " :" + strings5[i] + " :" + strings6[i] + " :" + strings7[i]);

                        if (strings1[i].equals(strings2[i])) {
                            stringInteraction2 = strings3[i];
                        } else {
                            stringInteraction2 = strings2[i];
                        }

                        alertDialogInteraction(string2, strings1[i], stringInteraction2, strings5[i]);
                        Log.d("filter_drugInteraction", "Access via type2");

                    } else if (strings4[i].equals("3")) {
                        Log.d("filter_drugInteraction", "type 3 :" + strings0[i] + " :" + strings1[i] +
                                " :" + strings2[i] + " :" + strings3[i] + " :" + strings4[i] +
                                " :" + strings5[i] + " :" + strings6[i] + " :" + strings7[i]);
                        Toast.makeText(getBaseContext(), "ได้ค่า 3", Toast.LENGTH_LONG).show();
                    }
                }
                return;
            }

        }


        addValueTomainTABLEandIntent();


    } //clickSave

    private void alertDialogDuplicate() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.icon_question);
        builder.setTitle("Duplicate!!!");
        builder.setMessage("ไม่สามารถดำเนินการต่อได้ \nเนื่องจากมียาตัวนี้ในระบบแล้ว");
        builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        });
        builder.show();
    }


    private void addValueTomainTABLEandIntent() {
        MyManage myManage = new MyManage(this);

        stringsduplicate = myManage.readAllMainTABLE_string(string1, 0);
        if (stringsduplicate.length > 0) {
            alertDialogDuplicate();
            return;
        }
        myManage.addValueTomainTABLE(string1, string2, string3, string15, string4, string5, string6, string7, string8, string9, string10, string11, string12, string13, string14);

        String[] strings1 = myManage.readAllMainTABLE_string(string1, 0);
        String[] stringsT1 = myManage.readAllMainTABLE_string(string1, 7);
        String[] stringsT2 = myManage.readAllMainTABLE_string(string1, 8);
        String[] stringsT3 = myManage.readAllMainTABLE_string(string1, 9);
        String[] stringsT4 = myManage.readAllMainTABLE_string(string1, 10);
        String[] stringsT5 = myManage.readAllMainTABLE_string(string1, 11);
        String[] stringsT6 = myManage.readAllMainTABLE_string(string1, 12);
        String[] stringsT7 = myManage.readAllMainTABLE_string(string1, 13);
        String[] stringsT8 = myManage.readAllMainTABLE_string(string1, 14);

        String currentDay = myManage.currentDay();

        Log.d("addValueToSumTable", strings1[0] + " " + currentDay);


        myManage.addValueToSumTable(strings1[0], currentDay, stringsT1[0], "", "", "");

        if (!stringsT2[0].equals("")) {
            myManage.addValueToSumTable(strings1[0], currentDay, stringsT2[0], "", "", "");
        }
        if (!stringsT3[0].equals("")) {
            myManage.addValueToSumTable(strings1[0], currentDay, stringsT3[0], "", "", "");
        }
        if (!stringsT4[0].equals("")) {
            myManage.addValueToSumTable(strings1[0], currentDay, stringsT4[0], "", "", "");
        }
        if (!stringsT5[0].equals("")) {
            myManage.addValueToSumTable(strings1[0], currentDay, stringsT5[0], "", "", "");
        }
        if (!stringsT6[0].equals("")) {
            myManage.addValueToSumTable(strings1[0], currentDay, stringsT6[0], "", "", "");
        }
        if (!stringsT7[0].equals("")) {
            myManage.addValueToSumTable(strings1[0], currentDay, stringsT7[0], "", "", "");
        }
        if (!stringsT8[0].equals("")) {
            myManage.addValueToSumTable(strings1[0], currentDay, stringsT8[0], "", "", "");
        }


        Intent intent = new Intent(AddMedicine2Activity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void alertDialogInteraction(String s1, String s2, String s3, String s4) {


        MyManage myManage = new MyManage(this);
        s2 = myManage.findGenerinName_nameGenericTABLE_by_id(s2);


        stringGenericName2 = myManage.find_id_medTABLE_by_Generic_name(s3);
        //ต้องทำการ นับจำนวน stringGenericName2 แล้วทำการ bufferString ให้ได้ค่าของ String ออกมา
        //แล้วเอาไปใส่แทน ใน setMessage
        StringBuilder stringBuilder = new StringBuilder("ยา :");
        for (int i = 0; i < stringGenericName2.length; i++) {
            stringBuilder.append(stringGenericName2[i]);
        }


        myManage.filter_drugInteractionTABLE_Dialog();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.icon_question);
        builder.setTitle("เกิดปฏิกิริยาระหว่างยา (ยาตีกัน)");
        builder.setMessage("ยา " + s1 + " (" + s2 + ") \nเกิดปฏิกิริยาระหว่างยากับ\n" + stringBuilder + "\nเหตุผล : " + s4);
        builder.setPositiveButton("ยืนยันการรับประทาน", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                addValueTomainTABLEandIntent();
            }
        });
        builder.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        });
        builder.show();

    }

    private void alertDialogFaltal() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.faltal_drug);
        builder.setTitle("Fatal Drug Interaction");
        builder.setMessage("ยานี้ไม่สามารถทานร่วมกับ\n ยาบางตัวที่ท่านรับประทานอยู่ \n โปรดปรึกษาแพทย์หรือเภสัชกรก่อน \n (ไม่แนะนำให้รับประทานอย่างเด็ดขาด!!!)");
        builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        });
        builder.show();
    }



}  //Main Class
