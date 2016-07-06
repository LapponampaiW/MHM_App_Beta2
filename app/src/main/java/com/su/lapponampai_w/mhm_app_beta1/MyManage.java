package com.su.lapponampai_w.mhm_app_beta1;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.AvoidXfermode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.widget.Toast;

import java.math.BigInteger;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by apple on 4/20/16.
 */
public class MyManage {

    MyHelper helper;
    SQLiteDatabase readSqLiteDatabase, writeSqLiteDatabase;

    //userTABLE
    public static final String userTABLE = "userTABLE";
    public static final String ucolumn_id = "_id";
    public static final String ucolumn_User = "User";
    public static final String ucolumn_Password = "Password";
    public static final String ucolumn_Stay = "Stay";
    public static final String ucolumn_Email = "Email";
    public static final String[] column_userTABLE = {ucolumn_id, ucolumn_User, ucolumn_Password, ucolumn_Stay, ucolumn_Email};

    //medTABLE
    public static final String medTABLE = "medTABLE";
    public static final String mcolumn_id = "_id";
    public static final String mcolumn_trade_name = "Trade_name";
    public static final String mcolumn_key_search = "Key_search";
    public static final String mcolumn_generic_name1 = "Generic_name1";
    public static final String mcolumn_dosage1 = "Dosage1";
    public static final String mcolumn_uom1 = "UOM1";
    public static final String mcolumn_generic_name2 = "Generic_name2";
    public static final String mcolumn_dosage2 = "Dosage2";
    public static final String mcolumn_uom2 = "UOM2";
    public static final String mcolumn_generic_name3 = "Generic_name3";
    public static final String mcolumn_dosage3 = "Dosage3";
    public static final String mcolumn_uom3 = "UOM3";
    public static final String mcolumn_ea = "EA";
    public static final String mcolumn_generic_name4 = "Generic_name4";
    public static final String mcolumn_dosage4 = "Dosage4";
    public static final String mcolumn_uom4 = "UOM4";
    public static final String mcolumn_amount_tablet = "Amount_tablet";
    public static final String mcolumn_which_date_d = "Which_Date_D";
    public static final String mcolumn_appearance = "Appearance";
    public static final String mcolumn_pharmaco = "Pharmaco";
    public static final String mcolumn_t1 = "T1";
    public static final String mcolumn_t2 = "T2";
    public static final String mcolumn_t3 = "T3";
    public static final String mcolumn_t4 = "T4";
    public static final String mcolumn_t5 = "T5";
    public static final String mcolumn_t6 = "T6";
    public static final String mcolumn_t7 = "T7";
    public static final String mcolumn_t8 = "T8";
    public static final String[] column_medTABLE = {mcolumn_id, mcolumn_trade_name, mcolumn_key_search, mcolumn_generic_name1, mcolumn_dosage1,
            mcolumn_uom1, mcolumn_generic_name2, mcolumn_dosage2, mcolumn_uom2, mcolumn_generic_name3,
            mcolumn_dosage3, mcolumn_uom3, mcolumn_generic_name4, mcolumn_dosage4, mcolumn_uom4, mcolumn_ea, mcolumn_amount_tablet,
            mcolumn_which_date_d, mcolumn_appearance, mcolumn_pharmaco, mcolumn_t1, mcolumn_t2,
            mcolumn_t3, mcolumn_t4, mcolumn_t5, mcolumn_t6, mcolumn_t7, mcolumn_t8};

    //nameGenericTABLE
    private static final String nameGenericTABLE = "nameGenericTABLE";
    private static final String gcolumn_id = "_id";
    private static final String gcolumn_generic_name = "Generic_name";
    private static final String[] column_nameGenericTABLE = {gcolumn_id, gcolumn_generic_name};


    //drugInteractionTABLE
    private static final String drugInteractionTABLE = "drugInteractionTABLE";
    private static final String dcolumn_id = "_id";
    private static final String dcolumn_medicine1 = "Medicine1";
    private static final String dcolumn_medicine2 = "Medicine2";
    private static final String dcolumn_type_interaction = "Type_interaction";
    private static final String dcolumn_message = "Message";
    private static final String dcolumn_timeMedicine1_2 = "TimeMedicine1_2";
    private static final String dcolumn_timeMedicine2_1 = "TimeMedicine2_1";
    private static final String[] column_drugInteractionTABLE = {dcolumn_id, dcolumn_medicine1, dcolumn_medicine2,
            dcolumn_type_interaction, dcolumn_message, dcolumn_timeMedicine1_2, dcolumn_timeMedicine2_1};


    //drugInteractionTABLE_For_Query
    private static final String drugInteractionTABLE_For_Query = "drugInteractionTABLE_For_Query";
    private static final String qcolumn_id = "_id";
    private static final String qcolunm_initial_medicine = "Initial_medicine";
    private static final String qcolumn_medicine1 = "Medicine1";
    private static final String qcolumn_medicine2 = "Medicine2";
    private static final String qcolumn_type_interaction = "Type_interaction";
    private static final String qcolumn_message = "Message";
    private static final String qcolumn_timeMedicine1_2 = "TimeMedicine1_2";
    private static final String qcolumn_timeMedicine2_1 = "TimeMedicine2_1";
    private static final String[] column_drugInteractionTABLE_For_Query = {qcolumn_id,
            qcolunm_initial_medicine, qcolumn_medicine1, qcolumn_medicine2,
            qcolumn_type_interaction, qcolumn_message,
            qcolumn_timeMedicine1_2, qcolumn_timeMedicine2_1};


    //mainTABLE
    //ใช้ ตัวแปรเดียวกับ medTABLE
    public static final String mainTABLE = "mainTABLE";
    public static final String mcolumn_Med_id = "Med_id";
    public static final String mcolumn_generic_line = "Generic_line";
    public static final String mcolumn_Main_pharmaco = "pharmaco";
    public static final String mcolumn_startdate = "StartDate";
    public static final String mcolumn_finishdate = "FinishDate";
    public static final String mcolumn_prn = "PRN";
    public static final String mcolumn_datetimecanceled = "DateTimeCanceled";
    public static final String[] column_mainTABLE = {mcolumn_id, mcolumn_Med_id, mcolumn_trade_name,
            mcolumn_generic_line, mcolumn_amount_tablet, mcolumn_which_date_d, mcolumn_appearance, mcolumn_ea, mcolumn_Main_pharmaco,
            mcolumn_startdate, mcolumn_finishdate, mcolumn_prn, mcolumn_t1,
            mcolumn_t2, mcolumn_t3, mcolumn_t4, mcolumn_t5, mcolumn_t6, mcolumn_t7, mcolumn_t8, mcolumn_datetimecanceled};

    //sumTABLE
    private static final String sum_table = "sumTABLE";
    private static final String column_Main_id = "Main_id";
    private static final String column_DateRef = "DateRef";
    private static final String column_TimeRef = "TimeRef";
    private static final String column_DateCheck = "DateCheck";
    private static final String column_TimeCheck = "TimeCheck";
    private static final String column_SkipHold = "SkipHold";
    private static final String[] column_sumTABLE = {"_id", column_Main_id, column_DateRef, column_TimeRef, column_DateCheck, column_TimeCheck, column_SkipHold};

    //timeTABLE
    private static final String timeTABLE = "timeTABLE";
    private static final String tcolumn_id = "_id";
    private static final String tcolumn_time_interval = "Time_interval";
    private static final String tcolumn_start_time = "Start_time";
    private static final String tcolumn_end_time = "End_time";
    private static final String[] column_timeTABLE = {tcolumn_id, tcolumn_time_interval, tcolumn_start_time, tcolumn_end_time};

    //displayTABLE
    private static final String displayTABLE = "displayTABLE";
    private static final String displaycolumn_id = "_id";
    private static final String displaycolumn_position = "Position";
    private static final String displaycolumn_sum_id = "Sum_id";
    private static final String displaycolumn_main_id = "Main_id";
    private static final String displaycolumn_day = "Day";
    private static final String displaycolumn_timeref = "TimeRef";
    private static final String displaycolumn_datetimecheck = "DateTimeCheck";
    private static final String displaycolumn_appearance = "Appearance";
    private static final String displaycolumn_skiphold = "SkipHold";
    private static final String[] column_displayTABLE = {displaycolumn_id, displaycolumn_position, displaycolumn_sum_id,
            displaycolumn_main_id, displaycolumn_day, displaycolumn_timeref,
            displaycolumn_datetimecheck, displaycolumn_appearance,displaycolumn_skiphold};

    //newsTABLE
    private static final String newsTABLE = "newsTABLE";
    private static final String ncolumn_id = "_id";
    private static final String ncolumn_med_id = "Med_id";
    private static final String ncolumn_message = "Message";
    private static final String ncolumn_appearance_edit = "Appearance_edit";
    private static final String ncolumn_activity = "Activity";
    private static final String[] column_newsTABLE = {ncolumn_id, ncolumn_med_id, ncolumn_message, ncolumn_appearance_edit, ncolumn_activity};

    //totalAmountTABLE
    private static final String totalAmountTABLE = "totalAmountTABLE";
    //tcolumn_id ใช้อันเดียวกั่บ timeTABLE
    private static final String tcolumn_Main_id = "Main_id";
    private static final String tcolumn_TotalAmount = "TotalAmount";
    private static final String tcolumn_DateUpdated = "DateUpdated";
    private static final String[] column_totalAmountTABLE = {tcolumn_id,
            tcolumn_Main_id, tcolumn_TotalAmount, tcolumn_DateUpdated};

    //addUseTABLE
    private static final String addUseTABLE = "addUseTABLE";
    private static final String acolumn_id = "_id";
    private static final String acolumn_Main_id = "Main_id";
    private static final String acolum_Add_Use_Adjust_txt = "Add_Use_Adjust_txt";
    private static final String acolumn_Amount = "Amount";
    private static final String acolumn_Date = "Date";
    private static final String[] column_addUseTABLE = {acolumn_id, acolumn_Main_id,
            acolum_Add_Use_Adjust_txt, acolumn_Amount, acolumn_Date};

    public MyManage(Context context) {
        helper = new MyHelper(context);

        readSqLiteDatabase = helper.getReadableDatabase();
        writeSqLiteDatabase = helper.getWritableDatabase();

    } //Constructor

    public String[] filtersumTABLE_by_DateRef(String dateref, int intColumn) {
        String[] strREAD = null;

        Cursor cursor = readSqLiteDatabase.query(sum_table, column_sumTABLE, "DateRef " + "LIKE '"+ dateref + "'", null, null, null,null);
        int iCount = cursor.getCount();
        if (iCount > 0) {
            cursor.moveToFirst();
            strREAD = new String[cursor.getCount()];
            for(int i = 0;i<cursor.getCount();i++) {
                switch (intColumn) {
                    case 0:
                        strREAD[i] = cursor.getString(0);
                        break;
                    case 1:
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(column_Main_id));
                        break;
                    case 2:
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(column_DateRef));
                        break;
                    case 3:
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(column_TimeRef));
                        break;
                    case 4:
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(column_DateCheck));
                        break;
                    case 5:
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(column_TimeCheck));
                        break;
                    case 6:
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(column_SkipHold));
                        break;
                    default:
                        break;
                }
                cursor.moveToNext();
            }

        } else {
            strREAD = new String[1];
            strREAD[0] = "";
        }

        return strREAD;
    }

    //Update sumTABLE ยกเลิก การAdd DateCheck DateTime
    public long updatesumTABLE_Canceled_ADD_DateCheckTimeCheck(String str_id) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(column_DateCheck,"");
        contentValues.put(column_TimeCheck,"");
        return writeSqLiteDatabase.update(sum_table,contentValues, "_id = " + str_id,null);
    }

    //Update ยกเลิก SkipHold ใน sumTABLE
    public long updatesumTABLE_Canceled_SkipHold(String str_id) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(column_DateCheck,"");
        contentValues.put(column_TimeCheck,"");
        contentValues.put(column_SkipHold,"");
        return writeSqLiteDatabase.update(sum_table,contentValues, "_id = " + str_id,null);
    }

    //Update SkipHold ใน sumTABLE
    public long updatesumTABLE_ADD_SkipHold_Now(String str_id) {
        MyData myData = new MyData();
        String strDateTime = myData.currentDateTime();
        ContentValues contentValues = new ContentValues();
        contentValues.put(column_DateCheck,"");
        contentValues.put(column_TimeCheck,"");
        contentValues.put(column_SkipHold,strDateTime);
        return writeSqLiteDatabase.update(sum_table,contentValues, "_id = " + str_id,null);
    }

    //Update sumTABLE โดยใส่ค่าของ DateCheck TimeCheck เข้าไป
    public long updatesumTABLE_ADD_DateCheckTimeCheck_Now(String str_id) {
        MyData myData = new MyData();
        String strDateCheck = myData.currentDay();
        String strTimeCheck = myData.currentTime_Minus();
        ContentValues contentValues = new ContentValues();
        contentValues.put(column_DateCheck,strDateCheck);
        contentValues.put(column_TimeCheck,strTimeCheck);
        contentValues.put(column_SkipHold,"");
        return writeSqLiteDatabase.update(sum_table,contentValues, "_id = " + str_id,null);
    }


    //Update mainTABLE_DateTimeCanceled
    public long updatemainTABLE_DateTimeCanceled(String str_id) {

        MyData myData = new MyData();
        String strDateTime = myData.currentDateTime();
        ContentValues contentValues = new ContentValues();
        contentValues.put(mcolumn_datetimecanceled,strDateTime);
        return writeSqLiteDatabase.update(mainTABLE, contentValues, "_id = " + str_id,null);
    }

    //Update totalAmountTABLE
    public long updateTotalAmountTABLE(String str_id,
                                       String strTotalAmount,
                                       String strDateUpdated) {
        long readlong = 0;
        ContentValues contentValues = new ContentValues();
        contentValues.put(tcolumn_TotalAmount,strTotalAmount);
        contentValues.put(tcolumn_DateUpdated, strDateUpdated);
        readlong = writeSqLiteDatabase.update(totalAmountTABLE, contentValues, "_id = " + str_id,null);
        return readlong;
    }

    //Read All addUseTABLE
    public String[] readAlladdUseTABLE(int intColumn) {
        String strREAD[] = null;
        Cursor cursor = readSqLiteDatabase.query(addUseTABLE, column_addUseTABLE, null, null, null, null, null);
        int intCount = cursor.getCount();
        if (intCount > 0) {
            cursor.moveToFirst();
            strREAD = new String[cursor.getCount()];
            for (int i = 0; i < cursor.getCount(); i++) {
                switch (intColumn) {
                    case 0:
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(acolumn_id));
                        break;
                    case 1:
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(acolumn_Main_id));
                        break;
                    case 2:
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(acolum_Add_Use_Adjust_txt));
                        break;
                    case 3:
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(acolumn_Amount));
                        break;
                    case 4:
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(acolumn_Date));
                    default:
                        break;
                }

            }
        } else {
            strREAD = new String[1];
            strREAD[0] = "";
        }

        return strREAD;
    }

    //Read All totalAmountTABLE
    public String[] readAlltotalAmountTABLE(int intColumn) {
        String[] strREAD = null;
        Cursor cursor = readSqLiteDatabase.query(totalAmountTABLE, column_totalAmountTABLE, null, null, null, null, null);
        int intCount = cursor.getCount();
        if (intCount > 0) {
            cursor.moveToFirst();
            strREAD = new String[cursor.getCount()];
            for (int i = 0; i < cursor.getCount(); i++) {
                switch (intColumn) {
                    case 0:
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(tcolumn_id));
                        break;
                    case 1:
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(tcolumn_Main_id));
                        break;
                    case 2:
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(tcolumn_TotalAmount));
                        break;
                    case 3:
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(tcolumn_DateUpdated));
                        break;
                    default:
                        break;
                }
                cursor.moveToNext();
            }
        } else {
            strREAD = new String[1];
            strREAD[0] = "";
        }

        return strREAD;
    }

    //Read All sumTABLE
    public String[] readAllsumTABLE_Full(int intColumn) {
        String[] strREAD = null;

        Cursor cursor = readSqLiteDatabase.query(sum_table, column_sumTABLE, null, null, null, null, "DateRef DESC");
        int intCount = cursor.getCount();
        if (intCount > 0) {
            cursor.moveToFirst();
            strREAD = new String[cursor.getCount()];
            for (int i = 0; i < cursor.getCount(); i++) {
                strREAD[i] = cursor.getString(intColumn);
                cursor.moveToNext();
            } //for
        } else {
            strREAD = new String[1];
            strREAD[0] = "";
        }
        return strREAD;
    }

    //Read All displayTABLE
    public String[] readAlldisplayTABLE(int intColumn) {
        String[] strREAD = null;
        Cursor cursor = readSqLiteDatabase.query(displayTABLE, column_displayTABLE, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            strREAD = new String[cursor.getCount()];

            for (int i = 0; i < cursor.getCount(); i++) {
                switch (intColumn) {
                    case 0:
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(displaycolumn_id));
                        break;
                    case 1:
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(displaycolumn_position));
                        break;
                    case 2:
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(displaycolumn_sum_id));
                        break;
                    case 3:
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(displaycolumn_main_id));
                        break;
                    case 4:
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(displaycolumn_day));
                        break;
                    case 5:
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(displaycolumn_timeref));
                        break;
                    case 6:
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(displaycolumn_datetimecheck));
                        break;
                    case 7:
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(displaycolumn_appearance));
                        break;
                    case 8:
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(displaycolumn_skiphold));
                        break;
                    default:
                        break;
                }
                cursor.moveToNext();
            }
        }
        return strREAD;
    }

    //Read All medTABLE
    public String[] readAllmedTABLE(int intColumn) {
        String[] strread = null;
        Cursor cursor = readSqLiteDatabase.query(medTABLE, column_medTABLE, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            strread = new String[cursor.getCount()];

            for (int i = 0; i < cursor.getCount(); i++) {
                switch (intColumn) {
                    case 0:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_id));
                        break;
                    case 1:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_trade_name));
                        break;
                    case 2:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_key_search));
                        break;
                    case 3:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_generic_name1));
                        break;
                    case 4:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_dosage1));
                        break;
                    case 5:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_uom1));
                        break;
                    case 6:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_generic_name2));
                        break;
                    case 7:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_dosage2));
                        break;
                    case 8:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_uom2));
                        break;
                    case 9:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_generic_name3));
                        break;
                    case 10:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_dosage3));
                        break;
                    case 11:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_uom3));
                        break;
                    case 12:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_generic_name4));
                        break;
                    case 13:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_dosage4));
                        break;
                    case 14:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_uom4));
                        break;
                    case 15:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_which_date_d));
                        break;
                    case 16:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_appearance)); //ใช้ใน NewsActivity
                        break;
                    case 17:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_pharmaco));
                        break;
                    case 18:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_t1));
                        break;
                    case 19:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_t2));
                        break;
                    case 20:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_t3));
                        break;
                    case 21:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_t4));
                        break;
                    case 22:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_t5));
                        break;
                    case 23:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_t6));
                        break;
                    case 24:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_t7));
                        break;
                    case 25:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_t8));
                        break;
                    case 26:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_amount_tablet));
                        break;
                    case 27:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_ea));
                    default:
                        break;
                }
                cursor.moveToNext();
            }
        }

        return strread;

    } //finish readAllmedTABLE

    //Read All newsTABLE
    public String[] readAllnewsTABLE(int intColumn) {
        String[] strREAD = null;
        Cursor cursor = readSqLiteDatabase.query(newsTABLE, column_newsTABLE, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            strREAD = new String[cursor.getCount()];
            for (int i = 0; i < cursor.getCount(); i++) {
                switch (intColumn) {
                    case 0:
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(ncolumn_id));
                        break;
                    case 1:
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(ncolumn_med_id));
                        break;
                    case 2:
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(ncolumn_message));
                        break;
                    case 3:
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(ncolumn_appearance_edit));
                        break;
                    case 4:
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(ncolumn_activity));
                        break;
                    default:
                        break;
                }
                cursor.moveToNext();
            }

        }
        return strREAD;
    }


    //Read mainTABLE ที่ DateTimeCanceled ยังเป็น "" (คือยัง Active อยู่)
    public String[] read_mainTABLE_DateTimeCanceled_N(int intColumn) {
        String[] strREAD = null;
        Cursor cursor = readSqLiteDatabase.query(mainTABLE,column_mainTABLE, "DateTimeCanceled " + "LIKE ''", null, null, null, "_id ASC");
        int intCount = cursor.getCount();
        if (intCount > 0) {

            strREAD = new String[cursor.getCount()];
            cursor.moveToFirst();
            for(int i = 0;i < cursor.getCount();i++) {
                strREAD[i] = cursor.getString(intColumn);
                cursor.moveToNext();
            } //for
            cursor.close();
        } else {
            strREAD = new String[1];
            strREAD[0] = "";
        }
        return strREAD;
    }

    //Read mainTABLE ที่ DateTimeCanceled ยังเป็น "" (คือยัง Active อยู่)
    public String[] read_mainTABLE_InCluded_DateTimeCanceled(int intColumn) {
        String[] strREAD = null;
        Cursor cursor = readSqLiteDatabase.query(mainTABLE,column_mainTABLE, null, null, null, null, "_id ASC");
        int intCount = cursor.getCount();
        if (intCount > 0) {

            strREAD = new String[cursor.getCount()];
            cursor.moveToFirst();
            for(int i = 0;i < cursor.getCount();i++) {
                strREAD[i] = cursor.getString(intColumn);
                cursor.moveToNext();
            } //for
            cursor.close();
        } else {
            strREAD = new String[1];
            strREAD[0] = "";
        }
        return strREAD;
    }


    // Read All mainTABLE
    public String[] readAllMainTABLE(int intColumn) { //0 หมายถึง ==> _id , 1 ==>T1

        String[] resultStrings = null;
        Cursor cursor = readSqLiteDatabase.query("mainTABLE",
                new String[]{"_id", "T1", "Med_id", "Trade_name", "Generic_line", "Appearance", "Amount_tablet", "EA"}, //2,3,4,5,6,7
                null, null, null, null, null);

        resultStrings = new String[cursor.getCount()];

        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            resultStrings[i] = cursor.getString(intColumn);
            cursor.moveToNext();

        } //for
        cursor.close();

        return resultStrings;
    }

    public String[] readAllMainTABLE_Full(int intColumn) { //ไม่อยากแก้ เอา DateTimeCanceled ออก
        String[] strREAD = null;
        Cursor cursor = readSqLiteDatabase.query(mainTABLE, column_mainTABLE,"DateTimeCanceled " + "LIKE ''", null, null, null, "_id ASC");
        int intCount = cursor.getCount();
        if (intCount > 0) {
            cursor.moveToFirst();
            strREAD = new String[cursor.getCount()];
            for (int i = 0; i < cursor.getCount(); i++) {
                switch (intColumn) {
                    case (0):
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(mcolumn_id));
                        break;
                    case (1):
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(mcolumn_Med_id));
                        break;
                    case (2):
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(mcolumn_trade_name));
                        break;
                    case (3):
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(mcolumn_generic_line));
                        break;
                    case (4):
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(mcolumn_amount_tablet));
                        break;
                    case (5):
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(mcolumn_which_date_d));
                        break;
                    case (6):
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(mcolumn_appearance));
                        break;
                    case (7):
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(mcolumn_ea));
                        break;
                    case (8):
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(mcolumn_Main_pharmaco));
                        break;
                    case (9):
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(mcolumn_startdate)); //StartDate
                        break;
                    case (10):
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(mcolumn_finishdate)); //FinishDate
                        break;
                    case (11):
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(mcolumn_prn)); //PRN
                        break;
                    case (12):
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(mcolumn_t1));
                        break;
                    case (13):
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(mcolumn_t2));
                        break;
                    case (14):
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(mcolumn_t3));
                        break;
                    case (15):
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(mcolumn_t4));
                        break;
                    case (16):
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(mcolumn_t5));
                        break;
                    case (17):
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(mcolumn_t6));
                        break;
                    case (18):
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(mcolumn_t7));
                        break;
                    case (19):
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(mcolumn_t8));
                        break;
                    case (20):
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(mcolumn_datetimecanceled)); //DateTimeCanceled
                        break;
                    default:
                        break;
                }
                cursor.moveToNext();
            }

        } else {
            strREAD = new String[1];
            strREAD[0] = "";
        }
        return strREAD;
    }

    public String filterdisplayTABLE_MAEB_By_Position(String position) {

        String strREAD = null;

        Cursor cursor = readSqLiteDatabase.query(displayTABLE, column_displayTABLE, "Position" + " LIKE '" + position + "%'", null, null, null, "_id DESC");

        if (cursor != null) {
            cursor.moveToFirst();
            int i = cursor.getCount();
            if (i > 0) {
                strREAD = cursor.getString(cursor.getColumnIndex(displaycolumn_position));
            } else {
                strREAD = "Non value";
            }
        }

        return strREAD;
    }  //filterdisplayTABLE_MAEB_By_Position

    public long adddisplayTABLEValue(String strdisplaycolumn_position,
                                     String strdisplaycolumn_sum_id,
                                     String strdisplaycolumn_main_id,
                                     String strdisplaycolumn_day,
                                     String strdisplaycolumn_timeref,
                                     String strdisplaycolumn_datetimecheck,
                                     String strdisplaycolumn_appearance,
                                     String strdisplaycolumn_skiphold) { //String strdisplaycolumn_skiphold

        ContentValues contentValues = new ContentValues();
        contentValues.put(displaycolumn_position, strdisplaycolumn_position);
        contentValues.put(displaycolumn_sum_id, strdisplaycolumn_sum_id);
        contentValues.put(displaycolumn_main_id, strdisplaycolumn_main_id);
        contentValues.put(displaycolumn_day, strdisplaycolumn_day);
        contentValues.put(displaycolumn_timeref, strdisplaycolumn_timeref);
        contentValues.put(displaycolumn_datetimecheck, strdisplaycolumn_datetimecheck);
        contentValues.put(displaycolumn_appearance, strdisplaycolumn_appearance);
        contentValues.put(displaycolumn_skiphold,strdisplaycolumn_skiphold);

        return writeSqLiteDatabase.insert(displayTABLE, null, contentValues);

    }

    public String currentDay() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String currentday = dateFormat.format(date);
        return currentday;
    }

    public String[] readAllMainTABLE_string(String med_id, int intColumn) {
        String[] strREAD = null;
        Cursor cursor = readSqLiteDatabase.query(mainTABLE, column_mainTABLE, "Med_id LIKE '" +
                med_id + "' AND DateTimeCanceled LIKE ''", null, null, null, "_id DESC");
        //Cursor cursor = readSqLiteDatabase.query(mainTABLE, column_mainTABLE, "Med_id LIKE '" + med_id + "'", null, null, null, "_id DESC");
        if (cursor != null) {
            cursor.moveToFirst();
            strREAD = new String[cursor.getCount()];
            for (int i = 0; i < cursor.getCount(); i++) {
                switch (intColumn) {
                    case 0:
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(mcolumn_id));
                        break;
                    case 1:
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(mcolumn_Med_id));
                        break;
                    case 2:
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(mcolumn_trade_name));
                        break;
                    case 3:
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(mcolumn_generic_line));
                        break;
                    case 4:
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(mcolumn_which_date_d));
                        break;
                    case 5:
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(mcolumn_appearance));
                        break;
                    case 6:
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(mcolumn_pharmaco));
                        break;
                    case 7:
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(mcolumn_t1));
                        break;
                    case 8:
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(mcolumn_t2));
                        break;
                    case 9:
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(mcolumn_t3));
                        break;
                    case 10:
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(mcolumn_t4));
                        break;
                    case 11:
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(mcolumn_t5));
                        break;
                    case 12:
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(mcolumn_t6));
                        break;
                    case 13:
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(mcolumn_t7));
                        break;
                    case 14:
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(mcolumn_t8));
                        break;
                    case 15:
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(mcolumn_amount_tablet));
                        break;
                }
                cursor.moveToNext();
            }

        }
        return strREAD;

    } //readAllmainTABLE1


    public String[] filter_sumTABLE__by_Date(String time, int intcolumn) {

        String[] strREAD = null;
        Cursor cursor = readSqLiteDatabase.query(sum_table, column_sumTABLE, "DateRef LIKE '" + time + "'", null, null, null, null);
        int intCount = cursor.getCount();
        if (intCount > 0) {
            cursor.moveToFirst();
            strREAD = new String[cursor.getCount()];

            for (int i = 0; i < cursor.getCount(); i++) {

                switch (intcolumn) {
                    case (0):
                        strREAD[i] = cursor.getString(0);
                        break;
                    case (1):
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(column_Main_id));
                        break;
                    case (2):
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(column_DateRef));
                        break;
                    case (3):
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(column_TimeRef));
                        break;
                    case (4):
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(column_DateCheck));
                        break;
                    case (5):
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(column_TimeCheck));
                        break;
                    case (6):
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(column_SkipHold));
                        break;
                    default:
                        break;

                }

                cursor.moveToNext();
            }

        } else {
            strREAD = new String[1];
            strREAD[0] = "";


        }

        return strREAD;
    } //filter_sumTABLE_by_Date


    public long addValueTomainTABLE(String strMed_id,
                                    String strTrade_name,
                                    String strGeneric_line,
                                    String strAmount_tablet,
                                    String strWhich_Date_D,
                                    String strAppearance,
                                    String strEA,
                                    String strPharmaco,
                                    String strStartDate,
                                    String strFinishDate,
                                    String strPRN,
                                    String strT1,
                                    String strT2,
                                    String strT3,
                                    String strT4,
                                    String strT5,
                                    String strT6,
                                    String strT7,
                                    String strT8,
                                    String strDateTimeCanceled) {

        ContentValues contentValues = new ContentValues();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String time = dateFormat.format(System.currentTimeMillis());
        time = time.substring(2);
        long longTime = Long.parseLong(time);

        contentValues.put("_id", longTime);
        contentValues.put("Med_id", strMed_id);
        contentValues.put("Trade_name", strTrade_name);
        contentValues.put("Generic_line", strGeneric_line);
        contentValues.put("Amount_tablet", strAmount_tablet);
        contentValues.put("Which_Date_D", strWhich_Date_D);
        contentValues.put("Appearance", strAppearance);
        contentValues.put("EA", strEA);
        contentValues.put("Pharmaco", strPharmaco);
        contentValues.put("StartDate", strStartDate);
        contentValues.put("FinishDate", strFinishDate);
        contentValues.put("PRN", strPRN);
        contentValues.put("T1", strT1);
        contentValues.put("T2", strT2);
        contentValues.put("T3", strT3);
        contentValues.put("T4", strT4);
        contentValues.put("T5", strT5);
        contentValues.put("T6", strT6);
        contentValues.put("T7", strT7);
        contentValues.put("T8", strT8);
        contentValues.put("DateTimeCanceled", strDateTimeCanceled);


        return writeSqLiteDatabase.insert("mainTABLE", null, contentValues);
    }

    public long addValueToSumTable(String strMain_id,
                                   String strDateRef,
                                   String strTimeRef,
                                   String strDateCheck,
                                   String strTimeCheck,
                                   String strSkipHold) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(column_Main_id, strMain_id);
        contentValues.put(column_DateRef, strDateRef);
        contentValues.put(column_TimeRef, strTimeRef);
        contentValues.put(column_DateCheck, strDateCheck);
        contentValues.put(column_TimeCheck, strTimeCheck);
        contentValues.put(column_SkipHold, strSkipHold); // ใช้ Clrl + Enter

        return writeSqLiteDatabase.insert(sum_table, null, contentValues);
    } //addValueToSumTable

    public long addValueToTimeTable(String strTime_interval,
                                    String strStart_time,
                                    String strEnd_time) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(tcolumn_time_interval, strTime_interval);
        contentValues.put(tcolumn_start_time, strStart_time);
        contentValues.put(tcolumn_end_time, strEnd_time);

        return writeSqLiteDatabase.insert(timeTABLE, null, contentValues);
    }

    public long addValueTodrugInteractionTABLE_For_Query(int d_initial_medicine, int d_medicine1, int d_medicine2, String d_type_interaction,
                                                         String d_message, int d_timemedicine1_2, int d_timemedicine2_1) {

        ContentValues contentValues = new ContentValues();
        long addlong = 0;
        contentValues.put(qcolunm_initial_medicine, d_initial_medicine);
        contentValues.put(qcolumn_medicine1, d_medicine1);
        contentValues.put(qcolumn_medicine2, d_medicine2);
        contentValues.put(qcolumn_type_interaction, d_type_interaction);
        contentValues.put(qcolumn_message, d_message);
        contentValues.put(qcolumn_timeMedicine1_2, d_timemedicine1_2);
        contentValues.put(qcolumn_timeMedicine2_1, d_timemedicine2_1);

        addlong = writeSqLiteDatabase.insert(drugInteractionTABLE_For_Query, null, contentValues);
        return addlong;

    }


    public int check_null_userTABLE() {

        Cursor cursor = readSqLiteDatabase.query(userTABLE, column_userTABLE, null, null, null, null, null);

        int i = cursor.getCount();

        return i;
    }

    public void addValueSignUp(String user,
                               String password,
                               String stay,
                               String email) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(ucolumn_User, user);
        contentValues.put(ucolumn_Password, password);
        contentValues.put(ucolumn_Stay, stay);
        contentValues.put(ucolumn_Email, email);
        writeSqLiteDatabase.insert(userTABLE, null, contentValues);
    }

    public String[] readSQLite_userTABLE(int number_of_column) {
        String[] strAllFood = null;
        Cursor cursor = readSqLiteDatabase.query(userTABLE, column_userTABLE, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            strAllFood = new String[cursor.getCount()];
            for (int i = 0; i < cursor.getCount(); i++) {
                switch (number_of_column) {
                    case 0:
                        strAllFood[i] = cursor.getString(cursor.getColumnIndex(ucolumn_id));
                        break;
                    case 1:
                        strAllFood[i] = cursor.getString(cursor.getColumnIndex(ucolumn_User));
                        break;
                    case 2:
                        strAllFood[i] = cursor.getString(cursor.getColumnIndex(ucolumn_Password));
                        break;
                    case 3:
                        strAllFood[i] = cursor.getString(cursor.getColumnIndex(ucolumn_Stay));
                        break;
                    case 4:
                        strAllFood[i] = cursor.getString(cursor.getColumnIndex(ucolumn_Email));
                        break;
                    default:
                        break;
                }
                cursor.moveToNext();
            }

        }

        return strAllFood;
    }

    public String getArrayStringIndex(String[] strAry, String keyWord_String) {

        int fake = -500;
        for (int i = 0; i < strAry.length; i++) {

            if (strAry[i].equals(keyWord_String)) {
                fake = i;
            } else {
                fake = -555;
            }
        }
        return Integer.toString(fake);
    }

    public void updateStayLogin(String username) {

        Cursor cursor = readSqLiteDatabase.query(userTABLE, column_userTABLE, "User =?", new String[]{String.valueOf(username)}, null, null, null);

        cursor.moveToFirst();


        String id = cursor.getString(0);


        ContentValues contentValues = new ContentValues();
        contentValues.put(ucolumn_Stay, "1");

        writeSqLiteDatabase.update(userTABLE, contentValues, "_id =?", new String[]{String.valueOf(id)});

    }

    public String[] filterAddMed(int intColumn, String strWord) {
        String[] strread = null;


        //Cursor cursor = readSqLiteDatabase.query(medTABLE,column_medTABLE,"Trade_name =?",new String[]{String.valueOf(strWord)},null,null,null);
        //ทำ Search ตัวอักษรแบบไม่ต้องเขียนครบก็ได้ โดย Search ตาม key search และ tradename
        Cursor cursor = readSqLiteDatabase.query(medTABLE, column_medTABLE, "Trade_name" + " LIKE '%" + strWord + "%'" + " or " + "Key_search" + " LIKE '%" + strWord + "%'", null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            strread = new String[cursor.getCount()];

            for (int i = 0; i < cursor.getCount(); i++) {
                switch (intColumn) {
                    case 0:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_id));
                        break;
                    case 1:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_trade_name));  //ใส่ใน listViewAddTG
                        break;
                    case 2:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_key_search));  //ใส่ใน listViewAddTG
                        break;
                    case 3:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_generic_name1));  //ใส่ใน listViewAddTG
                        break;
                    case 4:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_dosage1));
                        break;
                    case 5:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_uom1));
                        break;
                    case 6:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_generic_name2));  //ใส่ใน listViewAddTG
                        break;
                    case 7:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_dosage2));
                        break;
                    case 8:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_uom2));
                        break;
                    case 9:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_generic_name3));  //ใส่ใน listViewAdTG
                        break;
                    case 10:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_dosage3));
                        break;
                    case 11:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_uom3));
                        break;
                    case 12:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_generic_name4));
                        break;
                    case 13:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_dosage4));
                        break;
                    case 14:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_uom4));
                        break;
                    case 15:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_which_date_d));
                        break;
                    case 16:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_appearance));
                        break;
                    case 17:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_pharmaco));
                        break;
                    case 18:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_t1));
                        break;
                    case 19:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_t2));
                        break;
                    case 20:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_t3));
                        break;
                    case 21:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_t4));
                        break;
                    case 22:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_t5));
                        break;
                    case 23:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_t6));
                        break;
                    case 24:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_t7));
                        break;
                    case 25:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_t8));
                        break;
                    case 26:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_amount_tablet));
                        break;
                    case 27:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_ea));
                    default:
                        break;
                }
                cursor.moveToNext();
            }

        } else {
            strread = null;
        }

        return strread;
    }

    public String[] filter_medTABLE_by_id(String id) {
        Cursor cursor = readSqLiteDatabase.query(medTABLE, column_medTABLE, "_id =?", new String[]{String.valueOf(id)}, null, null, null);
        String[] strREAD = new String[4];
        if (cursor != null) {
            cursor.moveToFirst();

            strREAD[0] = cursor.getString(cursor.getColumnIndex(mcolumn_generic_name1));
            strREAD[1] = cursor.getString(cursor.getColumnIndex(mcolumn_generic_name2));
            strREAD[2] = cursor.getString(cursor.getColumnIndex(mcolumn_generic_name3));
            strREAD[3] = cursor.getString(cursor.getColumnIndex(mcolumn_generic_name4));

        } else {
            strREAD = null;
        }

        return strREAD;
    }

    public String[] filter_mainTABLE_by_id_Full(String id) {
        Cursor cursor = readSqLiteDatabase.query(mainTABLE, column_mainTABLE, "_id =?", new String[]{String.valueOf(id)}, null, null, null);
        String[] strREAD = new String[16];
        if (cursor != null) {
            cursor.moveToFirst();

            strREAD[0] = cursor.getString(cursor.getColumnIndex(mcolumn_id));
            strREAD[1] = cursor.getString(cursor.getColumnIndex(mcolumn_Med_id));
            strREAD[2] = cursor.getString(cursor.getColumnIndex(mcolumn_trade_name));
            strREAD[3] = cursor.getString(cursor.getColumnIndex(mcolumn_generic_line));
            strREAD[4] = cursor.getString(cursor.getColumnIndex(mcolumn_amount_tablet));
            strREAD[5] = cursor.getString(cursor.getColumnIndex(mcolumn_which_date_d));
            strREAD[6] = cursor.getString(cursor.getColumnIndex(mcolumn_appearance));
            strREAD[7] = cursor.getString(cursor.getColumnIndex("pharmaco"));
            strREAD[8] = cursor.getString(cursor.getColumnIndex(mcolumn_t1));
            strREAD[9] = cursor.getString(cursor.getColumnIndex(mcolumn_t2));
            strREAD[10] = cursor.getString(cursor.getColumnIndex(mcolumn_t3));
            strREAD[11] = cursor.getString(cursor.getColumnIndex(mcolumn_t4));
            strREAD[12] = cursor.getString(cursor.getColumnIndex(mcolumn_t5));
            strREAD[13] = cursor.getString(cursor.getColumnIndex(mcolumn_t6));
            strREAD[14] = cursor.getString(cursor.getColumnIndex(mcolumn_t7));
            strREAD[15] = cursor.getString(cursor.getColumnIndex(mcolumn_t8));

        } else {
            strREAD = null;
        }

        return strREAD;

    }

    public String findGenerinName_nameGenericTABLE_by_id(String id) {


        Cursor cursor = readSqLiteDatabase.query(nameGenericTABLE, column_nameGenericTABLE, "_id LIKE '" + id + "'", null, null, null, null);
        cursor.moveToFirst();

        String s;
        s = cursor.getString(cursor.getColumnIndex(gcolumn_generic_name));

        return s;
    }

    public String[] find_id_medTABLE_by_Generic_name(String generic_name) {

        Cursor cursor = readSqLiteDatabase.query(medTABLE, column_medTABLE,
                "Generic_name1 LIKE '" + generic_name + "'" + " or "
                        + "Generic_name2 LIKE '" + generic_name + "'" + " or "
                        + "Generic_name3 LIKE '" + generic_name + "'" + " or "
                        + "Generic_name4 LIKE '" + generic_name + "'", null, null, null, null);

        String[] strREAD_id = null;
        String[] strREAD_Tradename = null;
        if (cursor != null) {
            cursor.moveToFirst();
            strREAD_id = new String[cursor.getCount()];
            strREAD_Tradename = new String[cursor.getCount()];
            for (int i = 0; i < cursor.getCount(); i++) {
                strREAD_id[i] = cursor.getString(cursor.getColumnIndex(mcolumn_id));

                Cursor cursor1 = readSqLiteDatabase.query(mainTABLE, column_mainTABLE,
                        "Med_id LIKE '" + strREAD_id[i] + "'", null, null, null, null);
                cursor1.moveToFirst();
                strREAD_Tradename[i] = cursor1.getString(cursor1.getColumnIndex(mcolumn_trade_name));

                cursor.moveToNext();
            }

            /*
            for(int x = 0; x <cursor.getCount(); x++) {
                Cursor cursor1 = readSqLiteDatabase.query(mainTABLE, column_mainTABLE,
                        "Med_id LIKE '" + strREAD_id[x] + "'", null, null, null, null);

                cursor1.moveToFirst();
                strREAD_Tradename[x] = cursor1.getString(2);
            }
            */

        } else {
            strREAD_Tradename = null;
        }

        return strREAD_Tradename;
    }

    //Drug Interaction
    public void checkDrugInteraction(String drugname) {


        String[] strREAD = filter_medTABLE_by_id(drugname);

        Log.d("checkDrugInteraction", strREAD[0] + strREAD[1] + strREAD[2] + strREAD[3]);

        String[] stringsMed_id = null;

        String[] stringsGeneric1 = null;
        String[] stringsGeneric2 = null;
        String[] stringsGeneric3 = null;
        String[] stringsGeneric4 = null;

        for (int i = 0; i < 4; i++) {
            if (!strREAD[i].equals("1")) {
                Log.d("checkDrugInteraction", "strREAD :" + strREAD[i]);

                Cursor cursormainTABLE = readSqLiteDatabase.query(mainTABLE, column_mainTABLE, null, null, null, null, null);
                stringsMed_id = new String[cursormainTABLE.getCount()];
                stringsGeneric1 = new String[cursormainTABLE.getCount()];
                stringsGeneric2 = new String[cursormainTABLE.getCount()];
                stringsGeneric3 = new String[cursormainTABLE.getCount()];
                stringsGeneric4 = new String[cursormainTABLE.getCount()];

                //mainTABLE
                if (cursormainTABLE != null) {
                    cursormainTABLE.moveToFirst();


                    for (int x = 0; x < cursormainTABLE.getCount(); x++) {
                        stringsMed_id[x] = cursormainTABLE.getString(cursormainTABLE.getColumnIndex(mcolumn_Med_id));


                        Cursor cursormedTABLE = readSqLiteDatabase.query(medTABLE, column_medTABLE, "_id =?", new String[]{String.valueOf(stringsMed_id[x])}, null, null, null);
                        if (cursormedTABLE != null) {
                            cursormedTABLE.moveToFirst();
                            stringsGeneric1[x] = cursormedTABLE.getString(cursormedTABLE.getColumnIndex(mcolumn_generic_name1));
                            stringsGeneric2[x] = cursormedTABLE.getString(cursormedTABLE.getColumnIndex(mcolumn_generic_name2));
                            stringsGeneric3[x] = cursormedTABLE.getString(cursormedTABLE.getColumnIndex(mcolumn_generic_name3));
                            stringsGeneric4[x] = cursormedTABLE.getString(cursormedTABLE.getColumnIndex(mcolumn_generic_name4));
                        } //if medTABLE
                        Log.d("checkDrugInteraction", stringsMed_id[x] + stringsGeneric1[x] + stringsGeneric2[x] + stringsGeneric3[x] + stringsGeneric4[x]);
                        cursormainTABLE.moveToNext();
                    }


                    for (int y = 0; y < stringsMed_id.length; y++) {
                        Cursor cursorInteraction = readSqLiteDatabase.query(drugInteractionTABLE, column_drugInteractionTABLE,
                                "(Medicine1" + " LIKE '" + strREAD[i] + "'" + " and " + "Medicine2" +
                                        " LIKE '" + stringsGeneric1[y] + "')" + " or " + "(Medicine1" + " LIKE '" +
                                        strREAD[i] + "'" + " and " + "Medicine2" +
                                        " LIKE '" + stringsGeneric2[y] + "')" + " or " + "(Medicine1" + " LIKE '" +
                                        strREAD[i] + "'" + " and " + "Medicine2" +
                                        " LIKE '" + stringsGeneric3[y] + "')" + " or " + "(Medicine1" + " LIKE '" +
                                        strREAD[i] + "'" + " and " + "Medicine2" +
                                        " LIKE '" + stringsGeneric4[y] + "')" + " or " + "(Medicine2" + " LIKE '" + strREAD[i] + "'" + " and " + "Medicine1" +
                                        " LIKE '" + stringsGeneric1[y] + "')" + " or " + "(Medicine2" + " LIKE '" +
                                        strREAD[i] + "'" + " and " + "Medicine1" +
                                        " LIKE '" + stringsGeneric2[y] + "')" + " or " + "(Medicine2" + " LIKE '" +
                                        strREAD[i] + "'" + " and " + "Medicine1" +
                                        " LIKE '" + stringsGeneric3[y] + "')" + " or " + "(Medicine2" + " LIKE '" +
                                        strREAD[i] + "'" + " and " + "Medicine1" +
                                        " LIKE '" + stringsGeneric4[y] + "')", null, null, null, null);


                        if (cursorInteraction != null) {
                            Log.d("checkDrugInteraction", "cursorInteraction count :" + cursorInteraction.getCount());
                            cursorInteraction.moveToFirst();
                            String[] strings = new String[cursorInteraction.getCount()];
                            for (int z = 0; z < cursorInteraction.getCount(); z++) {
                                addValueTodrugInteractionTABLE_For_Query(Integer.parseInt(strREAD[i]), cursorInteraction.getInt(cursorInteraction.getColumnIndex(dcolumn_medicine1)),
                                        cursorInteraction.getInt(cursorInteraction.getColumnIndex(dcolumn_medicine2)),
                                        cursorInteraction.getString(cursorInteraction.getColumnIndex(dcolumn_type_interaction)),
                                        cursorInteraction.getString(cursorInteraction.getColumnIndex(dcolumn_message)),
                                        cursorInteraction.getInt(cursorInteraction.getColumnIndex(dcolumn_timeMedicine1_2)),
                                        cursorInteraction.getInt(cursorInteraction.getColumnIndex(dcolumn_timeMedicine2_1)));
                                cursorInteraction.moveToNext();
                            }

                        }
                    }
                } //for mainTABLE

            } //first if
        } //first loop

    } //checkDrugInteraction

    //ทำ query drugInteractionTABLE_For_Query
    public String[] filter_drugInteractionTABLE_For_Query(int intColumn) {
        Cursor cursor = readSqLiteDatabase.query(drugInteractionTABLE_For_Query, column_drugInteractionTABLE_For_Query, null, null, null, null, "Type_interaction ASC, _id ASC");

        String[] strREAD = null;
        if (cursor != null) {
            cursor.moveToFirst();
            strREAD = new String[cursor.getCount()];

            for (int i = 0; i < cursor.getCount(); i++) {
                switch (intColumn) {
                    case (0):
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(qcolumn_id));
                        break;
                    case (1):
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(qcolunm_initial_medicine));
                        break;
                    case (2):
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(qcolumn_medicine1));
                        break;
                    case (3):
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(qcolumn_medicine2));
                        break;
                    case (4):
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(qcolumn_type_interaction));
                        break;
                    case (5):
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(qcolumn_message));
                        break;
                    case (6):
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(qcolumn_timeMedicine1_2));
                        break;
                    case (7):
                        strREAD[i] = cursor.getString(cursor.getColumnIndex(qcolumn_timeMedicine2_1));
                    default:
                        break;
                }
                cursor.moveToNext();
            }
        }
        return strREAD;
    }


    //รับค่าจาก id
    public String[] searchById(String id) {
        String[] strread = null;
        Cursor cursor = readSqLiteDatabase.query(medTABLE, column_medTABLE, "_id =?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            strread = new String[28];

            strread[0] = cursor.getString(cursor.getColumnIndex(mcolumn_id));
            strread[1] = cursor.getString(cursor.getColumnIndex(mcolumn_trade_name));
            strread[2] = cursor.getString(cursor.getColumnIndex(mcolumn_key_search));
            strread[3] = cursor.getString(cursor.getColumnIndex(mcolumn_generic_name1));
            strread[4] = cursor.getString(cursor.getColumnIndex(mcolumn_dosage1));
            strread[5] = cursor.getString(cursor.getColumnIndex(mcolumn_uom1));
            strread[6] = cursor.getString(cursor.getColumnIndex(mcolumn_generic_name2));
            strread[7] = cursor.getString(cursor.getColumnIndex(mcolumn_dosage2));
            strread[8] = cursor.getString(cursor.getColumnIndex(mcolumn_uom2));
            strread[9] = cursor.getString(cursor.getColumnIndex(mcolumn_generic_name3));
            strread[10] = cursor.getString(cursor.getColumnIndex(mcolumn_dosage3));
            strread[11] = cursor.getString(cursor.getColumnIndex(mcolumn_uom3));
            strread[12] = cursor.getString(cursor.getColumnIndex(mcolumn_generic_name4));
            strread[13] = cursor.getString(cursor.getColumnIndex(mcolumn_dosage4));
            strread[14] = cursor.getString(cursor.getColumnIndex(mcolumn_uom4));
            strread[15] = cursor.getString(cursor.getColumnIndex(mcolumn_which_date_d));
            strread[16] = cursor.getString(cursor.getColumnIndex(mcolumn_appearance));
            strread[17] = cursor.getString(cursor.getColumnIndex(mcolumn_pharmaco));
            strread[18] = cursor.getString(cursor.getColumnIndex(mcolumn_t1));
            strread[19] = cursor.getString(cursor.getColumnIndex(mcolumn_t2));
            strread[20] = cursor.getString(cursor.getColumnIndex(mcolumn_t3));
            strread[21] = cursor.getString(cursor.getColumnIndex(mcolumn_t4));
            strread[22] = cursor.getString(cursor.getColumnIndex(mcolumn_t5));
            strread[23] = cursor.getString(cursor.getColumnIndex(mcolumn_t6));
            strread[24] = cursor.getString(cursor.getColumnIndex(mcolumn_t7));
            strread[25] = cursor.getString(cursor.getColumnIndex(mcolumn_t8));
            strread[26] = cursor.getString(cursor.getColumnIndex(mcolumn_amount_tablet));
            strread[27] = cursor.getString(cursor.getColumnIndex(mcolumn_ea));
        }
        return strread;
    }

    public String[] readTimeTABLE(int intcolumn) {

        Cursor cursor = readSqLiteDatabase.query(timeTABLE, column_timeTABLE, null, null, null, null, null);
        cursor.moveToFirst();
        String[] strREAD = new String[cursor.getCount()];

        for (int i = 0; i < cursor.getCount(); i++) {
            switch (intcolumn) {
                case 0:
                    strREAD[i] = cursor.getString(cursor.getColumnIndex(tcolumn_id));
                    break;
                case 1:
                    strREAD[i] = cursor.getString(cursor.getColumnIndex(tcolumn_time_interval));
                    break;
                case 2:
                    strREAD[i] = cursor.getString(cursor.getColumnIndex(tcolumn_start_time));
                    break;
                case 3:
                    strREAD[i] = cursor.getString(cursor.getColumnIndex(tcolumn_end_time));
                    break;
                default:
                    break;

            }
            cursor.moveToNext();
        }
        return strREAD;
    }

    public String[] translate_GenericName(String[] genericname) {

        String[] strRead = new String[genericname.length];

        for (int i = 0; i < genericname.length; i++) {
            Cursor cursor = readSqLiteDatabase.query(nameGenericTABLE, column_nameGenericTABLE, "_id =?", new String[]{String.valueOf(genericname[i])}, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                strRead[i] = cursor.getString(cursor.getColumnIndex(gcolumn_generic_name));
            } else {
                strRead[i] = null;
            }
        }

        return strRead;
    } //translate_GenericName

    public long addValueTo_addUseTABLE(String strMain_id,
                                       String strAdd_Use_Adjust_txt,
                                       double doubleAmount,
                                       String strDate) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(acolumn_Main_id, strMain_id);
        contentValues.put(acolum_Add_Use_Adjust_txt,strAdd_Use_Adjust_txt);
        contentValues.put(acolumn_Amount,doubleAmount);
        contentValues.put(acolumn_Date,strDate);

        return writeSqLiteDatabase.insert(addUseTABLE, null, contentValues);
    }

    public long addValueTo_totalAmountTABLE(String strMain_id,
                                            double doubleTotalAmount,
                                            String strDateUpdated) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(tcolumn_Main_id,strMain_id);
        contentValues.put(tcolumn_TotalAmount,doubleTotalAmount);
        contentValues.put(tcolumn_DateUpdated,strDateUpdated);

        return writeSqLiteDatabase.insert(totalAmountTABLE, null, contentValues);
    }


    public long adddrugInteractionTABLEValue(int d_medicine1, int d_medicine2, String d_type_interaction,
                                             String d_message, int d_timemedicine1_2, int d_timemedicine2_1) {

        ContentValues contentValues = new ContentValues();
        long addlong = 0;
        contentValues.put(dcolumn_medicine1, d_medicine1);
        contentValues.put(dcolumn_medicine2, d_medicine2);
        contentValues.put(dcolumn_type_interaction, d_type_interaction);
        contentValues.put(dcolumn_message, d_message);
        contentValues.put(dcolumn_timeMedicine1_2, d_timemedicine1_2);
        contentValues.put(dcolumn_timeMedicine2_1, d_timemedicine2_1);

        addlong = writeSqLiteDatabase.insert(drugInteractionTABLE, null, contentValues);
        return addlong;

    } //adddrugInteractionTABLEValue

    public long addNewsTABLEValue(String strMed_id, String strMessage,
                                  String strAppearence_edit,
                                  String strActivity) {

        long addlong;
        ContentValues contentValues = new ContentValues();
        contentValues.put(ncolumn_med_id, strMed_id);
        contentValues.put(ncolumn_message, strMessage);
        contentValues.put(ncolumn_appearance_edit, strAppearence_edit);
        contentValues.put(ncolumn_activity, strActivity);
        addlong = writeSqLiteDatabase.insert(newsTABLE, null, contentValues);
        return addlong;
    }

    public double addMedTABLEValue(String s_trade_name, String s_key_search,
                                   int s_generic_name1, String s_dosage1, String s_uom1,
                                   int s_generic_name2, String s_dosage2, String s_uom2,
                                   int s_generic_name3, String s_dosage3, String s_uom3,
                                   int s_generic_name4, String s_dosage4, String s_uom4,
                                   String s_ea, double s_amount_tablet, String s_which_date_d,
                                   String s_appearance, String s_pharmaco,
                                   String s_t1, String s_t2, String s_t3, String s_t4,
                                   String s_t5, String s_t6, String s_t7, String s_t8) {

        ContentValues contentValues = new ContentValues();
        double adddouble = 0;
        contentValues.put(mcolumn_trade_name, s_trade_name);
        contentValues.put(mcolumn_key_search, s_key_search);
        contentValues.put(mcolumn_generic_name1, s_generic_name1);
        contentValues.put(mcolumn_dosage1, s_dosage1);
        contentValues.put(mcolumn_uom1, s_uom1);
        contentValues.put(mcolumn_generic_name2, s_generic_name2);
        contentValues.put(mcolumn_dosage2, s_dosage2);
        contentValues.put(mcolumn_uom2, s_uom2);
        contentValues.put(mcolumn_generic_name3, s_generic_name3);
        contentValues.put(mcolumn_dosage3, s_dosage3);
        contentValues.put(mcolumn_uom3, s_uom3);
        contentValues.put(mcolumn_generic_name4, s_generic_name4);
        contentValues.put(mcolumn_dosage4, s_dosage4);
        contentValues.put(mcolumn_uom4, s_uom4);
        contentValues.put(mcolumn_ea, s_ea);
        contentValues.put(mcolumn_amount_tablet, s_amount_tablet);
        contentValues.put(mcolumn_which_date_d, s_which_date_d);
        contentValues.put(mcolumn_appearance, s_appearance);
        contentValues.put(mcolumn_pharmaco, s_pharmaco);
        contentValues.put(mcolumn_t1, s_t1);
        contentValues.put(mcolumn_t2, s_t2);
        contentValues.put(mcolumn_t3, s_t3);
        contentValues.put(mcolumn_t4, s_t4);
        contentValues.put(mcolumn_t5, s_t5);
        contentValues.put(mcolumn_t6, s_t6);
        contentValues.put(mcolumn_t7, s_t7);
        contentValues.put(mcolumn_t8, s_t8);

        adddouble = writeSqLiteDatabase.insert(medTABLE, null, contentValues);


        return adddouble;
    } //addMedTABLE

    public long addnameGenericTABLEValue(String s_generic_name) {
        ContentValues contentValues = new ContentValues();
        long addlong = 0;
        contentValues.put(gcolumn_generic_name, s_generic_name);

        return writeSqLiteDatabase.insert(nameGenericTABLE, null, contentValues);
    }

    //ทำ Drug Interaction
    public String filter_drugInteractionTABLE_Dialog() {
        Cursor cursor = readSqLiteDatabase.query(drugInteractionTABLE_For_Query, column_drugInteractionTABLE_For_Query, "Type_interaction LIKE '2'", null, null, null, "_id ASC");
        cursor.moveToFirst();

        return null;
    }


    //ทำการ add ค่าเข้าไปถ้า table มันว่างอะนะ
    public void medTABLEData() {

        Cursor cursor = readSqLiteDatabase.query(medTABLE, column_medTABLE, null, null, null, null, null);

        if (cursor.getCount() == 0) {
            addMedTABLEValue("Efaviren GPO", "Efaviren", 2, "600", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "4", "A", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Stocrin", "Efaviren", 2, "600", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "4", "A", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Lamivir", "Lamivudine", 3, "150", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "1", "A", "08:00", "20:00", "", "", "", "", "", "");
            addMedTABLEValue("GPO-vir S30", null, 3, "150", "1", 4, "200", "1", 5, "30", "1", 1, null, null, "1", 1, "ED:0", "3", "A", "08:00", "20:00", "", "", "", "", "", "");
            addMedTABLEValue("Tenofovir GPO", "Tenofovir", 6, "300", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "5", "A", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Viread", "Tenofovir", 6, "300", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "6", "A", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Reyataz", "Atazanavir", 7, "300", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "7", "A", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Cafergot", "Ergotamine Tartrate", 8, "1", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "8", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Prevacid", "Lansoprazole", 9, "30", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "9", "", "07:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Antacin", "Antacid", 10, "100", "1", 11, "100", "1", 1, null, null, 1, null, null, "1", 1, "ED:0", "10", "", "08:00", "13:00", "18:00", "", "", "", "", "");

        }

    } //medTABLEDate

    public void nameGenericTABLEData() {
        Cursor cursor = readSqLiteDatabase.query(nameGenericTABLE, column_nameGenericTABLE, null, null, null, null, null);

        if (cursor.getCount() == 0) {

            addnameGenericTABLEValue("N/A"); //1
            addnameGenericTABLEValue("Efaviren"); //2
            addnameGenericTABLEValue("Lamivudine"); //3
            addnameGenericTABLEValue("Nevirapine"); //4
            addnameGenericTABLEValue("Stavudine"); //5
            addnameGenericTABLEValue("Tenofovir"); //6
            addnameGenericTABLEValue("Atazanavir"); //7
            addnameGenericTABLEValue("Ergotamine Tartrate"); //8
            addnameGenericTABLEValue("Lansoprazole"); //9
            addnameGenericTABLEValue("Aluminium Hydroxide"); //10
            addnameGenericTABLEValue("Magnesium Hydroxide"); //11


        }
    }  //nameGenericTABLEData

    public void drugInteractionTABLEData() {
        Cursor cursor = readSqLiteDatabase.query(drugInteractionTABLE, column_drugInteractionTABLE, null, null, null, null, null);

        if (cursor.getCount() == 0) {

            adddrugInteractionTABLEValue(7, 8, "1", "Fatal DrugInteraction Cannot Take with", 0, 0);
            adddrugInteractionTABLEValue(7, 9, "2", "ไม่ควรรับประทานร่วมกัน", 0, 0);
            adddrugInteractionTABLEValue(7, 9, "2", "ไม่ควรรับประทานร่วมกัน", 0, 0);
            adddrugInteractionTABLEValue(7, 10, "3", "ควรรับประทานยาห่างกัน", 240, 120);
            adddrugInteractionTABLEValue(7, 10, "3", "ควรรับประทานยาห่างกัน", 240, 120);

        }
    }

    public void timeTABLEData() {
        Cursor cursor = readSqLiteDatabase.query(timeTABLE, column_timeTABLE, null, null, null, null, null);

        if (cursor.getCount() == 0) {

            addValueToTimeTable("เช้า", "00:00", "11:59");
            addValueToTimeTable("กลางวัน", "12:00", "17:59");
            addValueToTimeTable("เย็น", "18:00", "20:59");
            addValueToTimeTable("ก่อนนอน", "21:00", "23:59");
        }
    }

    public void newsTABLEData() {
        Cursor cursor = readSqLiteDatabase.query(newsTABLE, column_newsTABLE, null, null, null, null, null);
        if (cursor.getCount() == 0) {
            addNewsTABLEValue("1", "รายละเอียดข้อมูลยา Efaviren GPO", "", "DrugInformationActivity");
            addNewsTABLEValue("2", "รายละเอียดข้อมูลยา Stocrin", "", "DrugInformationActivity");
        }
    }


} //Main class
