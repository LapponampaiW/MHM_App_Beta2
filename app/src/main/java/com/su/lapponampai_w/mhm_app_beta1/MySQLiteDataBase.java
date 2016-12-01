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
            addMedTABLEValue("Avamigran", "Ergotamine Tartrate", 8, "1", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0604", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Tofago", "Ergotamine;Tartrate", 8, "1", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0902", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Neuramizone", "Ergotamine;Tartrate;Phenobarbital", 8, "0.3", "1", 55, "20", "1", 1, null, null, 1, null, null, "1", 1, "ED:0", "img0604", "", "20:00", "", "", "", "", "", "", "");
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
            addMedTABLEValue("Miracid", "Omeprazole", 21, "20", "1", 1, null, null, 1, null, null, 1, null, null, "2", 1, "ED:0", "img91104", "", "07:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Ocid", "Omeprazole", 21, "20", "1", 1, null, null, 1, null, null, 1, null, null, "2", 1, "ED:0", "img91104", "", "07:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Nexium", "esomeprazole", 22, "20", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0312", "", "07:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Nexium", "esomeprazole", 22, "40", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0312", "", "07:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Controloc", "Pantoprazole", 23, "20", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0102", "", "07:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Controloc", "Pantoprazole", 23, "40", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0102", "", "07:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Pariet", "Rabeprazole", 24, "10", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0602", "", "07:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Pariet", "Rabeprazole", 24, "20", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0602", "", "07:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Dexilant", "Dexlansoprazole", 25, "30", "1", 1, null, null, 1, null, null, 1, null, null, "2", 1, "ED:0", "img90813", "", "07:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Dexilant", "Dexlansoprazole", 25, "60", "1", 1, null, null, 1, null, null, 1, null, null, "2", 1, "ED:0", "img90808", "", "07:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Flucozole", "Fluconazole", 26, "50", "1", 1, null, null, 1, null, null, 1, null, null, "2", 1, "ED:0", "img90103", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Flucozole", "Fluconazole", 26, "100", "1", 1, null, null, 1, null, null, 1, null, null, "2", 1, "ED:0", "img90108", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Flucozole", "Fluconazole", 26, "150", "1", 1, null, null, 1, null, null, 1, null, null, "2", 1, "ED:0", "img90808", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Flucozole", "Fluconazole", 26, "200", "1", 1, null, null, 1, null, null, 1, null, null, "2", 1, "ED:0", "img90114", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Sporal", "Itraconazole", 27, "100", "1", 1, null, null, 1, null, null, 1, null, null, "2", 1, "ED:0", "img90312", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Sponar", "Itraconazole", 27, "100", "1", 1, null, null, 1, null, null, 1, null, null, "2", 1, "ED:0", "img90312", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Spazol", "Itraconazole", 27, "100", "1", 1, null, null, 1, null, null, 1, null, null, "2", 1, "ED:0", "img90312", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Prednisolone", "Prednisolone", 28, "5", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0607", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Dexamethasone", "Dexamethasone", 29, "0.5", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0309", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Dexamethasone", "Dexamethasone", 29, "4", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0609", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Valtrex", "Valacyclovir;Valaciclovir", 30, "500", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0301", "", "08:00", "18:00", "", "", "", "", "", "");
            addMedTABLEValue("Dalacin-C", "Clindamycin", 31, "300", "1", 1, null, null, 1, null, null, 1, null, null, "2", 1, "ED:0", "img91414", "", "08:00", "13:00", "18:00", "", "", "", "", "");
            addMedTABLEValue("Dalacin-C", "Clindamycin", 31, "150", "1", 1, null, null, 1, null, null, 1, null, null, "2", 1, "ED:0", "img91414", "", "08:00", "13:00", "18:00", "", "", "", "", "");
            addMedTABLEValue("Dacin-F", "Clindamycin", 31, "300", "1", 1, null, null, 1, null, null, 1, null, null, "2", 1, "ED:0", "img91414", "", "08:00", "13:00", "18:00", "", "", "", "", "");
            addMedTABLEValue("Vitamin B6", "Vitamin;B6;Pyridoxine", 32, "25", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0104", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Flagyl", "Metronidazole", 33, "400", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0301", "", "08:00", "13:00", "18:00", "", "", "", "", "");
            addMedTABLEValue("Metrolex", "Metronidazole", 33, "400", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0602", "", "08:00", "13:00", "18:00", "", "", "", "", "");
            addMedTABLEValue("PAS", "Para-aminosalicylic acid;Paraaminosalicylic acid;PAS", 34, "1", "3", 1, null, null, 1, null, null, 1, null, null, "1", 2, "ED:0", "img0615", "", "08:00", "13:00", "18:00", "22:00", "", "", "", "");
            addMedTABLEValue("Ricin", "Rifampicin", 35, "300", "1", 1, null, null, 1, null, null, 1, null, null, "2", 1, "ED:0", "img91515", "", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Ricin", "Rifampicin", 35, "450", "1", 1, null, null, 1, null, null, 1, null, null, "2", 1, "ED:0", "img90910", "", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Ricin", "Rifampicin", 35, "600", "1", 1, null, null, 1, null, null, 1, null, null, "2", 1, "ED:0", "img0315", "", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Isoniazid(INH)", "INH", 36, "100", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0601", "", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Dapsone", "Dapsone", 37, "100", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0601", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Zentel", "Albendazole", 38, "400", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0601", "", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Zithromax (Tablet)", "Azithromycin", 39, "250", "1", 1, null, null, 1, null, null, 1, null, null, "1", 2, "ED:0", "img0301", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Zithromax (Capsule)", "Azithromycin", 39, "250", "1", 1, null, null, 1, null, null, 1, null, null, "2", 2, "ED:0", "img90101", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Azycin", "Azithromycin", 39, "250", "1", 1, null, null, 1, null, null, 1, null, null, "2", 2, "ED:0", "img90101", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Azith", "Azithromycin", 39, "250", "1", 1, null, null, 1, null, null, 1, null, null, "2", 2, "ED:0", "img90101", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Azithrin", "Azithromycin", 39, "250", "1", 1, null, null, 1, null, null, 1, null, null, "2", 2, "ED:0", "img90101", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Binozyt", "Azithromycin", 39, "250", "1", 1, null, null, 1, null, null, 1, null, null, "2", 2, "ED:0", "img90101", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Vilerm", "Acyclovir;Aciclovir", 40, "200", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0803", "", "08:00", "11:00", "14:00", "17:00", "20:00", "", "", "");
            addMedTABLEValue("Vilerm", "Acyclovir;Aciclovir", 40, "400", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0802", "", "08:00", "11:00", "14:00", "17:00", "20:00", "", "", "");
            addMedTABLEValue("Vilerm", "Acyclovir;Aciclovir", 40, "800", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0303", "", "08:00", "13:00", "18:00", "", "", "", "", "");
            addMedTABLEValue("Intelence", "Etravirine", 41, "100", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0101", "A", "08:00", "20:00", "", "", "", "", "", "");
            addMedTABLEValue("Divir", "Didanosine", 18, "125", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0612", "A", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Cravit", "Levofloxacin", 42, "500", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0204", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Cravit", "Levofloxacin", 42, "750", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0204", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Cravit", "Levofloxacin", 42, "250", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0304", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Lefloxin", "Levofloxacin", 42, "500", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0201", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Hidil", "Gemfibrozil", 43, "300", "1", 1, null, null, 1, null, null, 1, null, null, "2", 1, "ED:0", "img90115", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Hidil", "Gemfibrozil", 43, "600", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0701", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Gemfibril FC", "Gemfibrozil", 43, "300", "1", 1, null, null, 1, null, null, 1, null, null, "2", 1, "ED:0", "img90115", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Gemfibril FC", "Gemfibrozil", 43, "600", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0701", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Fibril", "Fenofibrate", 44, "160", "1", 1, null, null, 1, null, null, 1, null, null, "2", 1, "ED:0", "img90308", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Ranidine", "Ranitidine", 45, "300", "1", 1, null, null, 1, null, null, 1, null, null, "2", 1, "ED:0", "img0202", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Ranidine", "Ranitidine", 45, "150", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0601", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Xanidine", "Ranitidine", 45, "150", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0601", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Tarivid", "Ofloxacin", 46, "200", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0601", "", "08:00", "20:00", "", "", "", "", "", "");
            addMedTABLEValue("Ofloxin FC", "Ofloxacin", 46, "200", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0601", "", "08:00", "20:00", "", "", "", "", "", "");
            addMedTABLEValue("Ofloxin FC", "Ofloxacin", 46, "100", "1", 1, null, null, 1, null, null, 1, null, null, "1", 2, "ED:0", "img0601", "", "08:00", "20:00", "", "", "", "", "", "");
            addMedTABLEValue("Siamidine", "Cimetidine", 47, "200", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0609", "", "08:00", "20:00", "", "", "", "", "", "");
            addMedTABLEValue("Siamidine", "Cimetidine", 47, "400", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0309", "", "08:00", "20:00", "", "", "", "", "", "");
            addMedTABLEValue("Cigamet", "Cimetidine", 47, "200", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0609", "", "08:00", "20:00", "", "", "", "", "", "");
            addMedTABLEValue("Cigamet", "Cimetidine", 47, "400", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0609", "", "08:00", "20:00", "", "", "", "", "", "");
            addMedTABLEValue("Cycloserine Meiji", "Cycloserine", 48, "250", "1", 1, null, null, 1, null, null, 1, null, null, "2", 1, "ED:0", "img90112", "", "08:00", "20:00", "", "", "", "", "", "");
            addMedTABLEValue("Proserine", "Cycloserine", 48, "250", "1", 1, null, null, 1, null, null, 1, null, null, "2", 1, "ED:0", "img90112", "", "08:00", "20:00", "", "", "", "", "", "");
            addMedTABLEValue("Ethionamide", "Ethionamide", 49, "250", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0602", "", "08:00", "20:00", "", "", "", "", "", "");
            addMedTABLEValue("Ativan", "Lorazepam;Ativan", 50, "1", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0601", "", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Ativan", "Lorazepam;Ativan", 50, "0.5", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0603", "", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Lonza", "Lorazepam;Ativan", 50, "1", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0601", "", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Lonza", "Lorazepam;Ativan", 50, "0.5", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0603", "", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Lorazepam", "Lorazepam;Ativan", 50, "1", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0601", "", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Lorazepam", "Lorazepam;Ativan", 50, "0.5", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0603", "", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Hawkcopak", "Lorazepam;Ativan", 50, "0.5", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0603", "", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Dormicum", "Midazolam", 51, "1", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0703", "", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Alnax", "Alprazolam", 52, "0.5", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0712", "", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Alnax", "Alprazolam", 52, "0.25", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0701", "", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Alnax", "Alprazolam", 52, "1", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0714", "", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Cordarone", "Amiodarone", 53, "1", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0901", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Diazepam GPO", "Diazepam", 54, "2", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0901", "", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Diazepam GPO", "Diazepam", 54, "5", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0902", "", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Zam", "Diazepam", 54, "2", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0901", "", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Zam", "Diazepam", 54, "5", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0902", "", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Phenobarbital Grian 1/2", "Phenobarbital", 55, "32.5", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0601", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Phenobarbital Grian 1", "Phenobarbital", 55, "65", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0601", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Tegretol", "Carbamazepine", 56, "100", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0901", "", "08:00", "13:00", "18:00", "", "", "", "", "");
            addMedTABLEValue("Tegretol CR", "Carbamazepine", 56, "200", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0204", "", "08:00", "20:00", "", "", "", "", "", "");
            addMedTABLEValue("Tegretol CR", "Carbamazepine", 56, "400", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0204", "", "08:00", "20:00", "", "", "", "", "", "");
            addMedTABLEValue("Tripta", "Amitriptyline", 57, "10", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0602", "", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Tripta", "Amitriptyline", 57, "25", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0607", "", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Tripta", "Amitriptyline", 57, "50", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0611", "", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Tryptanol", "Amitriptyline", 57, "25", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0602", "", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Nortyline", "Nortriptyline", 58, "10", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0603", "", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Zoloft", "Sertraline", 59, "50", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0201", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Zoloft", "Sertraline", 59, "100", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0301", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Sertraline Sandoz FC", "Sertraline", 59, "50", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0201", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Dilantin", "Phenytoin", 60, "100", "1", 1, null, null, 1, null, null, 1, null, null, "2", 3, "ED:0", "img90115", "", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Dilantin", "Phenytoin", 60, "50", "1", 1, null, null, 1, null, null, 1, null, null, "1", 2, "ED:0", "img1002", "", "08:00", "13:00", "18:00", "", "", "", "", "");
            addMedTABLEValue("Trazodone", "Trazodone", 61, "150", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0903", "", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Desirel", "Trazodone", 61, "150", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0903", "", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Zimmex", "Simvastatin", 62, "10", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0712", "", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Zimmex", "Simvastatin", 62, "20", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0712", "", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Zimmex", "Simvastatin", 62, "40", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0712", "", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Bestatin", "Simvastatin", 62, "10", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0702", "", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Bestatin", "Simvastatin", 62, "20", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0711", "", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Bestatin", "Simvastatin", 62, "40", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0712", "", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Vytorin 10/10", "Simvastatin;Ezetimibe", 63, "10", "1", 62, "10", "1", 1, null, null, 1, null, null, "1", 1, "ED:0", "img0301", "", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Vytorin 10/20", "Simvastatin;Ezetimibe", 63, "10", "1", 62, "20", "1", 1, null, null, 1, null, null, "1", 1, "ED:0", "img0301", "", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Vytorin 10/40", "Simvastatin;Ezetimibe", 63, "10", "1", 62, "40", "1", 1, null, null, 1, null, null, "1", 1, "ED:0", "img0301", "", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Lipitor", "Atorvastatin", 64, "10", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0101", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Lipitor", "Atorvastatin", 64, "20", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0101", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Lipitor", "Atorvastatin", 64, "40", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0101", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Lipitor", "Atorvastatin", 64, "80", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0101", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Xarator", "Atorvastatin", 64, "10", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0101", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Xarator", "Atorvastatin", 64, "20", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0101", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Xarator", "Atorvastatin", 64, "40", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0101", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Atorvastatin Sandoz", "Atorvastatin", 64, "10", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0601", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Atorvastatin Sandoz", "Atorvastatin", 64, "20", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0601", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Atorvastatin Sandoz", "Atorvastatin", 64, "40", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0602", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Befarin", "Warfarin", 65, "2", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0901", "", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Befarin", "Warfarin", 65, "3", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0903", "", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Befarin", "Warfarin", 65, "4", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0902", "", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Befarin", "Warfarin", 65, "5", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0912", "", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Orfarin", "Warfarin", 65, "3", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0903", "", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Orfarin", "Warfarin", 65, "5", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0912", "", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Famvir", "Famciclovir", 66, "125", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0601", "", "08:00", "13:00", "18:00", "", "", "", "", "");
            addMedTABLEValue("Famvir", "Famciclovir", 66, "250", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0601", "", "08:00", "13:00", "18:00", "", "", "", "", "");
            addMedTABLEValue("Levitra", "Vardenafil", 67, "10", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0611", "22:00", "", "", "", "", "", "", "","" );
            addMedTABLEValue("Levitra", "Vardenafil", 67, "20", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0611", "22:00", "", "", "", "", "", "", "","" );
            addMedTABLEValue("Cialis", "Tedalafil", 68, "10", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0511", "", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Cialis", "Tedalafil", 68, "20", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0511", "", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Viagra", "Sildenafil", 69, "50", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img1103", "", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Viagra", "Sildenafil", 69, "100", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img1103", "", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Sidegra", "Sildenafil", 69, "50", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img1103", "", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Sidegra", "Sildenafil", 69, "100", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img1103", "", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Plavix", "Clopidogrel", 70, "75", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0612", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Apolets", "Clopidogrel", 70, "75", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0612", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("CoPlavix", "Clopidogrel;Aspirin;acetylsalicylic;acid", 70, "75", "1", 71, "75", "1", 1, null, null, 1, null, null, "1", 1, "ED:0", "img0302", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Methadone", "Methadone", 72, "5", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0602", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Mevalotin Protect", "Pravastatin", 73, "20", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0202", "", "18:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Mevalotin Protect", "Pravastatin", 73, "40", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0202", "", "18:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Lescol", "Fluvastatin", 74, "40", "1", 1, null, null, 1, null, null, 1, null, null, "2", 1, "ED:0", "img0602", "", "18:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Lescol XL", "Fluvastatin", 74, "80", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0602", "", "18:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Rosuvastatin Sandoz", "Rosuvastatin", 75, "10", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0611", "", "18:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Rosuvastatin Sandoz", "Rosuvastatin", 75, "20", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0611", "", "18:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Crestor", "Rosuvastatin", 75, "10", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0611", "", "18:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Crestor", "Rosuvastatin", 75, "20", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0611", "", "18:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Yaz (28)", "Drospirenone;Ethinylestradiol", 76, "3", "1", 77, "0.02", "1", 1, null, null, 1, null, null, "1", 1, "ED:OCs:24:4:28:img0601", "img0604", "", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Marvelon (21)", "Desogestrel;Ethinylestradiol", 78, "0.15", "1", 77, "0.03", "1", 1, null, null, 1, null, null, "1", 1, "ED:OCs:21:0:28:N/A", "img0601", "", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Marvelon (28)", "Desogestrel;Ethinylestradiol", 78, "0.15", "1", 77, "0.03", "1", 1, null, null, 1, null, null, "1", 1, "ED:OCs:21:7:28:img0601", "img0601", "", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Diora 21 (21)", "D-Norgestrel;Ethinylestradiol", 81, "0.15", "1", 77, "0.03", "1", 1, null, null, 1, null, null, "1", 1, "ED:OCs:21:0:28:N/A", "img0601", "", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Diora 28 (81)", "D-Norgestrel;Ethinylestradiol", 81, "0.15", "1", 77, "0.03", "1", 1, null, null, 1, null, null, "1", 1, "ED:OCs:21:0:28:img0601", "img0601", "", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Minidoz (28)", "Gestodene;Ethinylestradiol", 79, "60", "4", 77, "15", "4", 1, null, null, 1, null, null, "1", 1, "ED:OCs:24:4:28:img0309", "img0604", "", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Meliane (21)", "Gestodene;Ethinylestradiol", 79, "0.075", "1", 77, "0.02", "1", 1, null, null, 1, null, null, "1", 1, "ED:OCs:21:0:28:N/A", "img0601", "", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Meliane ED (28)", "Gestodene;Ethinylestradiol", 79, "0.075", "1", 77, "0.02", "1", 1, null, null, 1, null, null, "1", 1, "ED:OCs:21:7:28:img0604", "img0601", "", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Mercilon (21)", "Desogestrel;Ethinylestradiol", 78, "0.15", "1", 77, "0.02", "1", 1, null, null, 1, null, null, "1", 1, "ED:OCs:21:0:28:N/A", "img0601", "", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Mercilon (28)", "Desogestrel;Ethinylestradiol", 78, "0.15", "1", 77, "0.02", "1", 1, null, null, 1, null, null, "1", 1, "ED:OCs:21:7:28:img0601", "img0601", "", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Synfonia (28)", "Drospirenone;Ethinylestradiol", 76, "3", "1", 77, "0.02", "1", 1, null, null, 1, null, null, "1", 1, "ED:OCs:24:4:28:img0601", "img0604", "", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Yasmin (21)", "Drospirenone;Ethinylestradiol", 76, "3", "1", 77, "0.03", "1", 1, null, null, 1, null, null, "1", 1, "ED:OCs:21:0:28:N/A", "img0602", "", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Melodia (21)", "Drospirenone;Ethinylestradiol", 76, "3", "1", 77, "0.03", "1", 1, null, null, 1, null, null, "1", 1, "ED:OCs:21:0:28:N/A", "img0602", "", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Oilezz (28)", "Desogestrel;Ethinylestradiol", 78, "", "1", 77, "", "1", 1, null, null, 1, null, null, "1", 1, "ED:OCs:7:21:28:img0601", "img0603", "", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Gynera (21)", "Gestodene;Ethinylestradiol", 79, "0.075", "1", 77, "0.03", "1", 1, null, null, 1, null, null, "1", 1, "ED:OCs:21:0:28:N/A", "img0601", "", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Gynera (28)", "Gestodene;Ethinylestradiol", 79, "0.075", "1", 77, "0.03", "1", 1, null, null, 1, null, null, "1", 1, "ED:OCs:21:7:28:img0601", "img0601", "", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Diane-35 (21)", "Cyproterone acetate;Ethinylestradiol", 80, "2", "1", 77, "0.035", "1", 1, null, null, 1, null, null, "1", 1, "ED:OCs:21:0:28:N/A", "img0604", "", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Sucee (21)", "Cyproterone acetate;Ethinylestradiol", 80, "2", "1", 77, "0.035", "1", 1, null, null, 1, null, null, "1", 1, "ED:OCs:21:0:28:N/A", "img0604", "", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Sucee 28 (28)", "Cyproterone acetate;Ethinylestradiol", 80, "2", "1", 77, "0.035", "1", 1, null, null, 1, null, null, "1", 1, "ED:OCs:21:7:28:img0601", "img0604", "", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Preme (21)", "Cyproterone acetate;Ethinylestradiol", 80, "2", "1", 77, "0.035", "1", 1, null, null, 1, null, null, "1", 1, "ED:OCs:21:0:28:N/A", "img0604", "", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Exluton (28)", "Lynestrenol", 82, "0.5", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:OCs:28:0:28:N/A", "img0601", "", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Cerazette (28)", "Desogestrel", 78, "0.075", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:OCs:28:0:28:N/A", "img0601", "", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Anna (28)", "Levonorgestrel;Ethinylestradiol", 83, "0.15", "1", 77, "0.03", "1", 1, null, null, 1, null, null, "1", 1, "ED:OCs:21:7:28:img0601", "img0602", "", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Anamai (28)", "Norethisterone;Mestranol", 84, "1", "1", 85, "0.05", "1", 1, null, null, 1, null, null, "1", 1, "ED:OCs:21:7:28:img0604", "img0601", "", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Marnon (28)", "Norgestrel;Ethinylestradiol", 86, "0.5", "1", 77, "0.05", "1", 1, null, null, 1, null, null, "1", 1, "ED:OCs:21:7:28:img0616", "img0601", "", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Primolut N", "Norethisterone", 84, "5", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0601", "", "08:00", "18:00", "", "", "", "", "", "");
            addMedTABLEValue("Tylenol", "Paracetamol", 87, "500", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0601", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Claron", "Clarithromycin", 88, "500", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0302", "", "08:00", "18:00", "", "", "", "", "", "");
            addMedTABLEValue("Claron", "Clarithromycin", 88, "250", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0302", "", "08:00", "18:00", "", "", "", "", "", "");
            addMedTABLEValue("Klacid FC", "Clarithromycin", 88, "500", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0302", "", "08:00", "18:00", "", "", "", "", "", "");
            addMedTABLEValue("Klacid FC", "Clarithromycin", 88, "250", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0302", "", "08:00", "18:00", "", "", "", "", "", "");
            addMedTABLEValue("Klacid MR", "Clarithromycin", 88, "500", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0302", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Pyrazinamide Atlantic", "Pyrazinamide", 89, "500", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0601", "", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Rifafour e-275", "INH;Rifampicin,Pyrazinamide;Ethambutol,Isoniazid", 36, "75", "1", 35, "150", "1", 89, "400", "1", 90, "275", "1", "1", 1, "ED:0", "img0612", "", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Lambutol", "Ethambutol", 90, "400", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0902", "", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Lambutol", "Ethambutol", 90, "500", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0901", "", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Tobutol", "Ethambutol", 90, "400", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0901", "", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Ciprobay", "Ciprofloxacin", 91, "250", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0901", "", "08:00", "18:00", "", "", "", "", "", "");
            addMedTABLEValue("Ciprobay", "Ciprofloxacin", 91, "500", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0201", "", "08:00", "18:00", "", "", "", "", "", "");
            addMedTABLEValue("Ciproxyl", "Ciprofloxacin", 91, "500", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0101", "", "08:00","18:00", "", "", "", "", "", "");
            addMedTABLEValue("Ciploxin", "Ciprofloxacin", 91, "500", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0101", "", "08:00", "20:00", "", "", "", "", "", "");
            addMedTABLEValue("Floxcipro", "Ciprofloxacin", 91, "500", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0201", "", "08:00", "20:00", "", "", "", "", "", "");
            addMedTABLEValue("Metrim", "Trimethoprim;Sulfamethoxazole", 92, "80", "1", 93, "400", "1", 1, null, null, 1, null, null, "1", 1, "ED:0", "img0901", "", "08:00", "13:00", "20:00", "", "", "", "", "");
            addMedTABLEValue("Metrim-F", "Trimethoprim;Sulfamethoxazole", 92, "160", "1", 93, "800", "1", 1, null, null, 1, null, null, "1", 1, "ED:0", "img0201", "", "08:00", "13:00", "20:00", "", "", "", "", "");




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
            addnameGenericTABLEValue("Fluconazole"); //26
            addnameGenericTABLEValue("Itraconazole"); //27
            addnameGenericTABLEValue("Prednisolone"); //28
            addnameGenericTABLEValue("Dexamethasone"); //29
            addnameGenericTABLEValue("Valaciclovir"); //30
            addnameGenericTABLEValue("Clindamycin"); //31
            addnameGenericTABLEValue("Pyridoxine"); //32
            addnameGenericTABLEValue("Metronidazole"); //33
            addnameGenericTABLEValue("Para-aminosalicylic acid"); //34
            addnameGenericTABLEValue("Rifampicin"); //35
            addnameGenericTABLEValue("Isoniazid"); //36
            addnameGenericTABLEValue("Dapsone"); //37
            addnameGenericTABLEValue("Albendazole"); //38
            addnameGenericTABLEValue("Azithromycin"); //39
            addnameGenericTABLEValue("Acyclovir"); //40
            addnameGenericTABLEValue("Etravirine"); //41
            addnameGenericTABLEValue("Levofloxacin"); //42
            addnameGenericTABLEValue("Gemfibrozil"); //43
            addnameGenericTABLEValue("Fenofibrate"); //44
            addnameGenericTABLEValue("Ranitidine"); //45
            addnameGenericTABLEValue("Ofloxacin"); //46
            addnameGenericTABLEValue("Cimetidine"); //47
            addnameGenericTABLEValue("Cycloserine"); //48
            addnameGenericTABLEValue("Ethionamide"); //49
            addnameGenericTABLEValue("Lorazepam"); //50
            addnameGenericTABLEValue("Midazolam"); //51
            addnameGenericTABLEValue("Alprazolam"); //52
            addnameGenericTABLEValue("Amiodarone"); //53
            addnameGenericTABLEValue("Diazepam"); //54
            addnameGenericTABLEValue("Phenobarbital"); //55
            addnameGenericTABLEValue("Carbamazepine"); //56
            addnameGenericTABLEValue("Amitriptyline"); //57
            addnameGenericTABLEValue("Nortriptyline"); //58
            addnameGenericTABLEValue("Sertraline"); //59
            addnameGenericTABLEValue("Phenytoin"); //60
            addnameGenericTABLEValue("Trazodone"); //61
            addnameGenericTABLEValue("Simvastatin"); //62
            addnameGenericTABLEValue("Ezetimibe"); //63
            addnameGenericTABLEValue("Atorvastatin"); //64
            addnameGenericTABLEValue("Warfarin"); //65
            addnameGenericTABLEValue("Famciclovir"); //66
            addnameGenericTABLEValue("Vardenafil"); //67
            addnameGenericTABLEValue("Tedalafil"); //68
            addnameGenericTABLEValue("Sildenafil"); //69
            addnameGenericTABLEValue("Clopidogrel"); //70
            addnameGenericTABLEValue("Aspirin"); //71
            addnameGenericTABLEValue("Methadone"); //72
            addnameGenericTABLEValue("Pravastatin"); //73
            addnameGenericTABLEValue("Fluvastatin"); //74
            addnameGenericTABLEValue("Rosuvastatin"); //75
            addnameGenericTABLEValue("Drospirenone"); //76
            addnameGenericTABLEValue("Ethinylestradiol"); //77
            addnameGenericTABLEValue("Desogestrel"); //78
            addnameGenericTABLEValue("Gestodene"); //79
            addnameGenericTABLEValue("Cyproterone acetate"); //80
            addnameGenericTABLEValue("D-Norgestrel"); //81
            addnameGenericTABLEValue("Lynestrenol"); //82
            addnameGenericTABLEValue("Levonorgestrel"); //83
            addnameGenericTABLEValue("Norethisterone"); //84
            addnameGenericTABLEValue("Mestranol"); //85
            addnameGenericTABLEValue("Norgestrel"); //86
            addnameGenericTABLEValue("Paracetamol"); //87
            addnameGenericTABLEValue("Clarithromycin"); //88
            addnameGenericTABLEValue("Pyrazinamide"); //89
            addnameGenericTABLEValue("Ethambutol"); //90
            addnameGenericTABLEValue("Ciprofloxacin"); //91
            addnameGenericTABLEValue("Trimethoprim"); //92
            addnameGenericTABLEValue("Sulfamethoxazole"); //93



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


