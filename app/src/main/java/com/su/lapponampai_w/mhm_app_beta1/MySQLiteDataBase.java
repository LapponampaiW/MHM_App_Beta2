package com.su.lapponampai_w.mhm_app_beta1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import static com.su.lapponampai_w.mhm_app_beta1.MyManage.column_drugInteractionTABLE;
import static com.su.lapponampai_w.mhm_app_beta1.MyManage.column_medTABLE;
import static com.su.lapponampai_w.mhm_app_beta1.MyManage.column_nameGenericTABLE;
import static com.su.lapponampai_w.mhm_app_beta1.MyManage.column_newsTABLE;
import static com.su.lapponampai_w.mhm_app_beta1.MyManage.column_timeTABLE;
import static com.su.lapponampai_w.mhm_app_beta1.MyManage.dcolumn_medicine1;
import static com.su.lapponampai_w.mhm_app_beta1.MyManage.dcolumn_medicine2;
import static com.su.lapponampai_w.mhm_app_beta1.MyManage.dcolumn_message;
import static com.su.lapponampai_w.mhm_app_beta1.MyManage.dcolumn_timeMedicine1_2;
import static com.su.lapponampai_w.mhm_app_beta1.MyManage.dcolumn_timeMedicine2_1;
import static com.su.lapponampai_w.mhm_app_beta1.MyManage.dcolumn_type_interaction;
import static com.su.lapponampai_w.mhm_app_beta1.MyManage.drugInteractionTABLE;
import static com.su.lapponampai_w.mhm_app_beta1.MyManage.medTABLE;
import static com.su.lapponampai_w.mhm_app_beta1.MyManage.mcolumn_trade_name;
import static com.su.lapponampai_w.mhm_app_beta1.MyManage.mcolumn_key_search;
import static com.su.lapponampai_w.mhm_app_beta1.MyManage.mcolumn_generic_name1;
import static com.su.lapponampai_w.mhm_app_beta1.MyManage.mcolumn_dosage1;
import static com.su.lapponampai_w.mhm_app_beta1.MyManage.mcolumn_uom1;
import static com.su.lapponampai_w.mhm_app_beta1.MyManage.mcolumn_generic_name2;
import static com.su.lapponampai_w.mhm_app_beta1.MyManage.mcolumn_dosage2;
import static com.su.lapponampai_w.mhm_app_beta1.MyManage.mcolumn_uom2;
import static com.su.lapponampai_w.mhm_app_beta1.MyManage.mcolumn_generic_name3;
import static com.su.lapponampai_w.mhm_app_beta1.MyManage.mcolumn_dosage3;
import static com.su.lapponampai_w.mhm_app_beta1.MyManage.mcolumn_uom3;
import static com.su.lapponampai_w.mhm_app_beta1.MyManage.mcolumn_generic_name4;
import static com.su.lapponampai_w.mhm_app_beta1.MyManage.mcolumn_dosage4;
import static com.su.lapponampai_w.mhm_app_beta1.MyManage.mcolumn_uom4;
import static com.su.lapponampai_w.mhm_app_beta1.MyManage.mcolumn_ea;
import static com.su.lapponampai_w.mhm_app_beta1.MyManage.mcolumn_amount_tablet;
import static com.su.lapponampai_w.mhm_app_beta1.MyManage.mcolumn_which_date_d;
import static com.su.lapponampai_w.mhm_app_beta1.MyManage.mcolumn_appearance;
import static com.su.lapponampai_w.mhm_app_beta1.MyManage.mcolumn_pharmaco;
import static com.su.lapponampai_w.mhm_app_beta1.MyManage.mcolumn_t1;
import static com.su.lapponampai_w.mhm_app_beta1.MyManage.mcolumn_t2;
import static com.su.lapponampai_w.mhm_app_beta1.MyManage.mcolumn_t3;
import static com.su.lapponampai_w.mhm_app_beta1.MyManage.mcolumn_t4;
import static com.su.lapponampai_w.mhm_app_beta1.MyManage.mcolumn_t5;
import static com.su.lapponampai_w.mhm_app_beta1.MyManage.mcolumn_t6;
import static com.su.lapponampai_w.mhm_app_beta1.MyManage.mcolumn_t7;
import static com.su.lapponampai_w.mhm_app_beta1.MyManage.mcolumn_t8;
import static com.su.lapponampai_w.mhm_app_beta1.MyManage.gcolumn_generic_name;
import static com.su.lapponampai_w.mhm_app_beta1.MyManage.nameGenericTABLE;
import static com.su.lapponampai_w.mhm_app_beta1.MyManage.ncolumn_activity;
import static com.su.lapponampai_w.mhm_app_beta1.MyManage.ncolumn_appearance_News;
import static com.su.lapponampai_w.mhm_app_beta1.MyManage.ncolumn_criteria;
import static com.su.lapponampai_w.mhm_app_beta1.MyManage.ncolumn_generic_id;
import static com.su.lapponampai_w.mhm_app_beta1.MyManage.ncolumn_message;
import static com.su.lapponampai_w.mhm_app_beta1.MyManage.newsTABLE;
import static com.su.lapponampai_w.mhm_app_beta1.MyManage.tcolumn_end_time;
import static com.su.lapponampai_w.mhm_app_beta1.MyManage.tcolumn_start_time;
import static com.su.lapponampai_w.mhm_app_beta1.MyManage.tcolumn_time_interval;
import static com.su.lapponampai_w.mhm_app_beta1.MyManage.timeTABLE;


/**
 * Created by apple on 11/6/16.
 */


public class MySQLiteDataBase {
    MyHelper helper;
    SQLiteDatabase readSqLiteDatabase, writeSqLiteDatabase;

    public MySQLiteDataBase(Context context) {
        helper = new MyHelper(context);

        readSqLiteDatabase = helper.getReadableDatabase();
        writeSqLiteDatabase = helper.getWritableDatabase();

    } //Constructor


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

    public long addValueToTimeTable(String strTime_interval,
                                    String strStart_time,
                                    String strEnd_time) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(tcolumn_time_interval, strTime_interval);
        contentValues.put(tcolumn_start_time, strStart_time);
        contentValues.put(tcolumn_end_time, strEnd_time);

        return writeSqLiteDatabase.insert(timeTABLE, null, contentValues);
    } //addValueToTimeTable

    public long addNewsTABLEValue(String strGeneric_id, String strMessage,
                                  String strAppearence_News, String strCriteria,
                                  String strActivity) {

        long addlong;
        ContentValues contentValues = new ContentValues();
        contentValues.put(ncolumn_generic_id, strGeneric_id);
        contentValues.put(ncolumn_message, strMessage);
        contentValues.put(ncolumn_appearance_News, strAppearence_News);
        contentValues.put(ncolumn_criteria, strCriteria);
        contentValues.put(ncolumn_activity, strActivity);
        addlong = writeSqLiteDatabase.insert(newsTABLE, null, contentValues);
        return addlong;
    } //addNewsTABLEValue


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




    public void medTABLEData() {

        Cursor cursor = readSqLiteDatabase.query(medTABLE, column_medTABLE, null, null, null, null, null);

        if (cursor.getCount() == 0) {

            addMedTABLEValue("Efaviren GPO", "Efavirenz", 2, "600", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0302", "A", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Stocrin", "Efavirenz", 2, "600", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0302", "A", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Stocrin", "Efavirenz", 2, "200", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0602", "A", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Efamat", "Efavirenz", 2, "200", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0104", "A", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Efamat", "Efavirenz", 2, "600", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0304", "A", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Lamivir", "Lamivudine", 3, "150", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0101", "A", "08:00", "20:00", "", "", "", "", "", "");
            addMedTABLEValue("GPO-vir S30", null, 3, "150", "1", 4, "200", "1", 5, "30", "1", 1, null, null, "1", 1, "ED:0", "img0201", "A", "08:00", "20:00", "", "", "", "", "", "");
            addMedTABLEValue("Tenofovir GPO", "Tenofovir", 6, "300", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0203", "A", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Viread", "Tenofovir", 6, "300", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0501", "A", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Reyataz", "Atazanavir", 7, "300", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img90506", "A", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Cafergot", "Ergotamine Tartrate", 8, "1", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0604", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Prevacid", "Lansoprazole", 9, "30", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0607", "", "07:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Prevacid", "Lansoprazole", 9, "15", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0607", "", "07:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Antacin", "Antacid", 10, "100", "1", 11, "100", "1", 1, null, null, 1, null, null, "1", 1, "ED:0", "img0602", "", "08:00", "13:00", "18:00", "", "", "", "", "");
            addMedTABLEValue("Norvir", "Ritonavir", 12, "100", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0301", "A", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("GPO-VIR Z250", "Nevirapine", 4, "200", "1", 2, "150", "1", 13, "250", "1", 1, null, null, "1", 1, "ED:0", "img0701", "A", "08:00", "20:00", "", "", "", "", "", "");
            addMedTABLEValue("Ziagenavir", "Abacavir", 14, "300", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0302", "A", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Teevir", "", 6, "300", "1", 2, "600", "1", 15, "200", "1", 1, null, null, "1", 1, "ED:0", "img0304", "A", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Truvada", "Emtricitabine;Tenofovir", 15, "200", "1", 6, "300", "1", 1, null, null, 1, null, null, "1", 1, "ED:0", "img0303", "A", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Ricovir-Em", "Emtricitabine;Tenofovir", 15, "200", "1", 6, "300", "1", 1, null, null, 1, null, null, "1", 1, "ED:0", "img0303", "A", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Ricovir", "Tenofovir", 6, "300", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0603", "A", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Antivir", "Zidovudine", 13, "100", "1", 1, null, null, 1, null, null, 1, null, null, "2", 3, "ED:0", "img90101", "A", "08:00", "20:00", "", "", "", "", "", "");
            addMedTABLEValue("Antivir", "Zidovudine", 13, "300", "1", 1, null, null, 1, null, null, 1, null, null, "2", 3, "ED:0", "img90910", "A", "08:00", "20:00", "", "", "", "", "", "");
            addMedTABLEValue("Aluvia", "Lopinavir;ritonavir", 16, "100", "1", 12, "25", "1", 1, null, null, 1, null, null, "1", 4, "ED:0", "img0304", "A", "08:00", "20:00", "", "", "", "", "", "");
            addMedTABLEValue("Neravir", "Nevirapine", 4, "200", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0701", "A", "08:00", "20:00", "", "", "", "", "", "");
            addMedTABLEValue("Edurant", "Rilpivirine", 17, "25", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0601", "A", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Atripla", "Efavirenz;Tenofovir;Emtricitabine", 2, "600", "1", 6, "300", "1", 15, "200", "1", 1, null, null, "1", 1, "ED:0", "img0301", "A", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Videx EC", "Didanosine;ddl", 18, "400", "1", 1, null, null, 1, null, null, 1, null, null, "2", 1, "ED:0", "img90101", "A", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Prezista", "Darunavir", 19, "600", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0104", "A", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Prezista", "Darunavir", 19, "400", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0104", "A", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Invirase", "Saquinavir", 20, "500", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0304", "A", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Kivexa", "Abacavir;Lamivudine", 14, "600", "1", 3, "300", "1", 1, null, null, 1, null, null, "1", 1, "ED:0", "img0304", "A", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Stavir", "Stavudine", 5, "30", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img90103", "A", "08:00", "20:00", "", "", "", "", "", "");
            addMedTABLEValue("Opaz", "Omeprazole", 21, "20", "1", 1, null, null, 1, null, null, 1, null, null, "2", 1, "ED:0", "img91104", "", "07:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Nexium", "esomeprazole", 22, "20", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0312", "", "07:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Nexium", "esomeprazole", 22, "40", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0312", "", "07:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Controloc", "Pantoprazole", 23, "20", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0102", "", "07:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Controloc", "Pantoprazole", 23, "40", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0102", "", "07:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Pariet", "Rabeprazole", 24, "10", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0602", "", "07:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Pariet", "Rabeprazole", 24, "20", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0602", "", "07:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Dexilant", "Dexlansoprazole", 25, "30", "1", 1, null, null, 1, null, null, 1, null, null, "2", 1, "ED:0", "img90813", "", "07:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Dexilant", "Dexlansoprazole", 25, "60", "1", 1, null, null, 1, null, null, 1, null, null, "2", 1, "ED:0", "img90808", "", "07:00", "", "", "", "", "", "", "");

        }

    } //medTABLE_Data

    public void nameGenericTABLEData() {

        Cursor cursor = readSqLiteDatabase.query(nameGenericTABLE, column_nameGenericTABLE, null, null, null, null, null);

        if (cursor.getCount() == 0) {

            addnameGenericTABLEValue("N/A"); //1
            addnameGenericTABLEValue("Efavirenz"); //2
            addnameGenericTABLEValue("Lamivudine"); //3
            addnameGenericTABLEValue("Nevirapine"); //4
            addnameGenericTABLEValue("Stavudine"); //5
            addnameGenericTABLEValue("Tenofovir"); //6
            addnameGenericTABLEValue("Atazanavir"); //7
            addnameGenericTABLEValue("Ergotamine Tartrate"); //8
            addnameGenericTABLEValue("Lansoprazole"); //9
            addnameGenericTABLEValue("Aluminium Hydroxide"); //10
            addnameGenericTABLEValue("Magnesium Hydroxide"); //11
            addnameGenericTABLEValue("Ritonavir"); //12
            addnameGenericTABLEValue("Zidovudine"); //13
            addnameGenericTABLEValue("Abacavir"); //14
            addnameGenericTABLEValue("Emtricitabine"); //15
            addnameGenericTABLEValue("Lopinavir"); //16
            addnameGenericTABLEValue("Rilpivirine"); //17
            addnameGenericTABLEValue("Didanosine"); //18
            addnameGenericTABLEValue("Darunavir"); //19
            addnameGenericTABLEValue("Saquinavir"); //20
            addnameGenericTABLEValue("Omeprazole"); //21
            addnameGenericTABLEValue("esomeprazole"); //22
            addnameGenericTABLEValue("Pantoprazole"); //23
            addnameGenericTABLEValue("Rabeprazole"); //24
            addnameGenericTABLEValue("Dexlansoprazole"); //25

        }
    }  //nameGenericTABLEData

    public void timeTABLEData() {
        Cursor cursor = readSqLiteDatabase.query(timeTABLE, column_timeTABLE, null, null, null, null, null);

        if (cursor.getCount() == 0) {

            addValueToTimeTable("เช้า", "00:00", "11:59");
            addValueToTimeTable("กลางวัน", "12:00", "17:59");
            addValueToTimeTable("เย็น", "18:00", "20:59");
            addValueToTimeTable("ก่อนนอน", "21:00", "23:59");
        }
    } //timeTABLEData

    public void newsTABLEData() {
        Cursor cursor = readSqLiteDatabase.query(newsTABLE, column_newsTABLE, null, null, null, null, null);
        if (cursor.getCount() == 0) {
            addNewsTABLEValue("2", "", "N1","1", "DrugInformationActivity");
            addNewsTABLEValue("3", "", "N1", "1", "DrugInformationActivity");
            addNewsTABLEValue("4", "", "N1", "1", "DrugInformationActivity");
            addNewsTABLEValue("5", "", "N1", "1", "DrugInformationActivity");
            addNewsTABLEValue("6", "", "N1", "1", "DrugInformationActivity");
            addNewsTABLEValue("7", "", "N1", "1", "DrugInformationActivity");
            addNewsTABLEValue("13", "", "N1", "1", "DrugInformationActivity");
            addNewsTABLEValue("14", "", "N1", "1", "DrugInformationActivity");
            addNewsTABLEValue("17", "", "N1", "1", "DrugInformationActivity");

        }
    } //newsTABLEData

    public void drugInteractionTABLEData() {
        Cursor cursor = readSqLiteDatabase.query(drugInteractionTABLE, column_drugInteractionTABLE, null, null, null, null, null);

        if (cursor.getCount() == 0) {

            adddrugInteractionTABLEValue(7, 8, "1", "Fatal DrugInteraction Cannot Take with", 0, 0);
            adddrugInteractionTABLEValue(7, 9, "2", "ไม่ควรรับประทานร่วมกัน", 0, 0);
            adddrugInteractionTABLEValue(7, 10, "3", "ควรรับประทานยาห่างกัน", 240, 120);

        }
    } //drugInteractionTABLEDate





}


