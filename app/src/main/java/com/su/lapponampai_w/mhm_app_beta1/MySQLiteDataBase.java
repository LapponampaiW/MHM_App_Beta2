package com.su.lapponampai_w.mhm_app_beta1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;

import static com.su.lapponampai_w.mhm_app_beta1.MyManage.alertcolumn_alert_Date_Lab;
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
import static com.su.lapponampai_w.mhm_app_beta1.MyManage.warningTABLE;
import static com.su.lapponampai_w.mhm_app_beta1.MyManage.wcolumn_id;
import static com.su.lapponampai_w.mhm_app_beta1.MyManage.wcolumn_med_id;
import static com.su.lapponampai_w.mhm_app_beta1.MyManage.wcolumn_pharmaco_id;
import static com.su.lapponampai_w.mhm_app_beta1.MyManage.wcolumn_warning_Detail;

/**
 * Created by apple on 11/6/16.
 */


public class MySQLiteDataBase extends AppCompatActivity {
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

    public long addValueToWaringTABLE(String str_medId,String str_pharmacoId,String str_warning_Detail) {
        ContentValues contentValues = new ContentValues();
        long addlong = 0;
        contentValues.put(wcolumn_med_id,str_medId);
        contentValues.put(wcolumn_pharmaco_id, str_pharmacoId);
        contentValues.put(wcolumn_warning_Detail, str_warning_Detail);
        addlong = writeSqLiteDatabase.insert(warningTABLE, null, contentValues);
        return addlong;
    }




    public void medTABLEData() {

        Cursor cursor = readSqLiteDatabase.query(medTABLE, column_medTABLE, null, null, null, null, null);

        if (cursor.getCount() == 0) {

            addMedTABLEValue("Efaviren GPO", "Efavirenz", 2, "600", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0302", "A", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Stocrin", "Efavirenz", 2, "600", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0302", "A", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Stocrin", "Efavirenz", 2, "200", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0602", "A", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Efamat FC", "Efavirenz", 2, "200", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0104", "A", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Efamat FC", "Efavirenz", 2, "600", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0304", "A", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Lamivir", "Lamivudine", 3, "150", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0101", "A", "08:00", "20:00", "", "", "", "", "", "");
            addMedTABLEValue("Combid 300 FC", "Lamivudine;zidovudine", 3, "150", "1", 13, "300", "1", 1, null, null, 1, null, null, "1", 1, "ED:0", "img0301", "A", "08:00", "20:00", "", "", "", "", "", "");
            addMedTABLEValue("Epivir FC", "Lamivudine", 3, "150", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img1101", "A", "08:00", "20:00", "", "", "", "", "", "");
            addMedTABLEValue("Lamivudine Mylan FC", "Lamivudine", 3, "150", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0301", "A", "08:00", "20:00", "", "", "", "", "", "");
            addMedTABLEValue("Ziffix Fc", "Lamivudine", 3, "100", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0302", "A", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Zovilam FC", "Lamivudine;zidovudine", 3, "150", "1", 13, "300", "1", 1, null, null, 1, null, null, "1", 1, "ED:0", "img0301", "A", "08:00", "20:00", "", "", "", "", "", "");
            addMedTABLEValue("GPO-vir S30", null, 3, "150", "1", 4, "200", "1", 5, "30", "1", 1, null, null, "1", 1, "ED:0", "img0201", "A", "08:00", "20:00", "", "", "", "", "", "");
            addMedTABLEValue("Tenofovir GPO", "Tenofovir", 6, "300", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0503", "A", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Tenof", "Tenofovir", 6, "300", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0503", "A", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Viread", "Tenofovir", 6, "300", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0501", "A", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Reyataz", "Atazanavir", 7, "300", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img90506", "A", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Reyataz", "Atazanavir", 7, "200", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img90808", "A", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Stribild FC", "Elivitegravir;Cabicistat;Emtricitabine;Tenofovir", 106, "150", "1", 107, "150", "1", 15, "200", "1", 6, "300", "1", "1", 1, "ED:0", "img0301", "A", "20:00", "", "", "", "", "", "", "");
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
            addMedTABLEValue("Teevir FC", "", 6, "300", "1", 2, "600", "1", 15, "200", "1", 1, null, null, "1", 1, "ED:0", "img0304", "A", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Truvada", "Emtricitabine;Tenofovir", 15, "200", "1", 6, "300", "1", 1, null, null, 1, null, null, "1", 1, "ED:0", "img0303", "A", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Ricovir-Em", "Emtricitabine;Tenofovir", 15, "200", "1", 6, "300", "1", 1, null, null, 1, null, null, "1", 1, "ED:0", "img0303", "A", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Ricovir", "Tenofovir", 6, "300", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0603", "A", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Antivir", "Zidovudine", 13, "100", "1", 1, null, null, 1, null, null, 1, null, null, "2", 3, "ED:0", "img90101", "A", "08:00", "20:00", "", "", "", "", "", "");
            addMedTABLEValue("Antivir", "Zidovudine", 13, "300", "1", 1, null, null, 1, null, null, 1, null, null, "2", 3, "ED:0", "img90910", "A", "08:00", "20:00", "", "", "", "", "", "");
            addMedTABLEValue("Aluvia", "Lopinavir;Ritonavir", 16, "100", "1", 12, "25", "1", 1, null, null, 1, null, null, "1", 4, "ED:0", "img0304", "A", "08:00", "20:00", "", "", "", "", "", "");
            addMedTABLEValue("Lopinavir/Ritonavir", "Lopinavir;Ritonavir", 16, "200", "1", 12, "50", "1", 1, null, null, 1, null, null, "1", 2, "ED:0", "img0302", "A", "08:00", "20:00", "", "", "", "", "", "");
            addMedTABLEValue("Neravir", "Nevirapine", 4, "200", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0701", "A", "08:00", "20:00", "", "", "", "", "", "");
            addMedTABLEValue("Nevirapine Mylan", "Nevirapine", 4, "200", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0701", "A", "08:00", "20:00", "", "", "", "", "", "");
            addMedTABLEValue("Edurant", "Rilpivirine", 17, "25", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0601", "A", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Atripla", "Efavirenz;Tenofovir;Emtricitabine", 2, "600", "1", 6, "300", "1", 15, "200", "1", 1, null, null, "1", 1, "ED:0", "img0301", "A", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Videx EC", "Didanosine;ddl", 18, "400", "1", 1, null, null, 1, null, null, 1, null, null, "2", 1, "ED:0", "img90101", "A", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Videx EC", "Didanosine;ddl", 18, "250", "1", 1, null, null, 1, null, null, 1, null, null, "2", 1, "ED:0", "img90101", "A", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Prezista", "Darunavir", 19, "600", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0101", "A", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Prezista", "Darunavir", 19, "400", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0104", "A", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Viracept", "Nelfinavir", 110, "250", "1", 1, null, null, 1, null, null, 1, null, null, "2", 3, "ED:0", "img0303", "A", "08:00", "16:00", "24:00", "", "", "", "", "");
            addMedTABLEValue("Viracept", "Nelfinavir", 110, "625", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img1401", "A", "08:00", "20:00", "", "", "", "", "", "");
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
            addMedTABLEValue("Nortyline", "Nortriptyline", 58, "10", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0617", "", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Nortrilen", "Nortriptyline", 58, "10", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0601", "", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Ortrip", "Nortriptyline", 58, "10", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0601", "", "22:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Ortrip", "Nortriptyline", 58, "25", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0601", "", "22:00", "", "", "", "", "", "", "");
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
            addMedTABLEValue("Tylenol", "Paracetamol", 87, "500", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0301", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Claron", "Clarithromycin", 88, "500", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0302", "", "08:00", "18:00", "", "", "", "", "", "");
            addMedTABLEValue("Claron", "Clarithromycin", 88, "250", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0302", "", "08:00", "18:00", "", "", "", "", "", "");
            addMedTABLEValue("Klacid FC", "Clarithromycin", 88, "500", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0302", "", "08:00", "18:00", "", "", "", "", "", "");
            addMedTABLEValue("Klacid FC", "Clarithromycin", 88, "250", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0302", "", "08:00", "18:00", "", "", "", "", "", "");
            addMedTABLEValue("Klacid MR", "Clarithromycin", 88, "500", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0302", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Pyrazinamide Atlantic", "Pyrazinamide", 89, "500", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0601", "", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Rifafour e-275", "INH;Rifampicin,Pyrazinamide;Ethambutol,Isoniazid", 36, "75", "1", 35, "150", "1", 89, "400", "1", 90, "275", "1", "1", 1, "ED:0", "img0612", "", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Rimstar 4-FDC", "INH;Isoniazid;Rifampicin,Pyrazinamide;Ethambutol", 36, "75", "1", 35, "150", "1", 89, "400", "1", 90, "275", "1", "1", 1, "ED:0", "img0304", "", "20:00", "", "", "", "", "", "", "");
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
            addMedTABLEValue("Corodil", "isosorbide;dinitrate", 94, "10", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0901", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Hartsorb sublingual", "isosorbide;dinitrate", 94, "5", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0907", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Hartsorb", "isosorbide;dinitrate", 94, "10", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0901", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Hartsorb", "isosorbide;dinitrate", 94, "30", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0901", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("isobinate", "isosorbide;dinitrate", 94, "10", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0702", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("isotrate", "isosorbide;dinitrate", 94, "10", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0901", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Imdex CR", "isosorbide;5-mononitrate", 95, "60", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0702", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Ismo-20", "isosorbide;5-mononitrate", 95, "20", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0901", "", "08:00", "18:00", "", "", "", "", "", "");
            addMedTABLEValue("Isopen-20", "isosorbide;5-mononitrate", 95, "20", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0901", "", "08:00", "18:00", "", "", "", "", "", "");
            addMedTABLEValue("Monolin SR", "isosorbide;5-mononitrate", 95, "60", "1", 1, null, null, 1, null, null, 1, null, null, "2", 1, "ED:0", "img90107", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Monolin SR", "isosorbide;5-mononitrate", 95, "20", "1", 1, null, null, 1, null, null, 1, null, null, "2", 1, "ED:0", "img90101", "", "08:00", "18:00", "", "", "", "", "", "");
            addMedTABLEValue("Monolin", "isosorbide;5-mononitrate", 95, "20", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0901", "", "08:00", "18:00", "", "", "", "", "", "");
            addMedTABLEValue("Monosorb", "isosorbide;5-mononitrate", 95, "20", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0901", "", "08:00", "18:00", "", "", "", "", "", "");
            addMedTABLEValue("Harnal OCAS", "Tamsulosin", 96, "0.4", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0604", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Uroflow PR", "Tamsulosin", 96, "0.4", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0601", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Cardil CR", "Diltiazem", 97, "120", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0201", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Dilcardia", "Diltiazem", 97, "30", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0607", "", "08:00", "13:00", "18:00", "", "", "", "", "");
            addMedTABLEValue("Dilizem", "Diltiazem", 97, "30", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0917", "", "08:00", "13:00", "18:00", "", "", "", "", "");
            addMedTABLEValue("Dilizem", "Diltiazem", 97, "60", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0902", "", "08:00", "13:00", "18:00", "", "", "", "", "");
            addMedTABLEValue("Ditizem", "Diltiazem", 97, "30", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0601", "", "08:00", "13:00", "18:00", "", "", "", "", "");
            addMedTABLEValue("Ditizem", "Diltiazem", 97, "60", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0601", "", "08:00", "13:00", "18:00", "", "", "", "", "");
            addMedTABLEValue("Herbesser R200", "Diltiazem", 97, "200", "1", 1, null, null, 1, null, null, 1, null, null, "2", 1, "ED:0", "img90115", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Herbesser R100", "Diltiazem", 97, "100", "1", 1, null, null, 1, null, null, 1, null, null, "2", 1, "ED:0", "img90101", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Madiplot", "Manidipine", 98, "10", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0902", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Madiplot", "Manidipine", 98, "20", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0902", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Kerdica", "Manidipine", 98, "20", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0902", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Nimotop", "Nimodipine", 99, "30", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0604", "", "08:00", "13:00", "18:00", "", "", "", "", "");
            addMedTABLEValue("Lercadip FC", "Lercanidipine", 100, "10", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0902", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Lercadip FC", "Lercanidipine", 100, "20", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0912", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Zanidip FC", "Lercanidipine", 100, "10", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0902", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Zanidip FC", "Lercanidipine", 100, "20", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0912", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Felodipin Stada", "Felodipine", 101, "5", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0612", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Felodipin Stada", "Felodipine", 101, "10", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0612", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Felodipin Stada", "Felodipine", 101, "2.5", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0602", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Felodipine Sandoz SR", "Felodipine", 101, "5", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0612", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Felodipine Sandoz SR", "Felodipine", 101, "10", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0612", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Feloten XR", "Felodipine", 101, "5", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0604", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Feloten XR", "Felodipine", 101, "10", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0612", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Topidil", "Felodipine", 101, "5", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0604", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Plendil", "Felodipine", 101, "5", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0604", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Plendil", "Felodipine", 101, "10", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0612", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Adalat CR", "Nifedipine", 102, "20", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0612", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Adalat CR", "Nifedipine", 102, "60", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0612", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Adalat CR", "Nifedipine", 102, "30", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0612", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Nelapine", "Nifedipine", 102, "10", "1", 1, null, null, 1, null, null, 1, null, null, "2", 1, "ED:0", "img99911", "", "08:00", "13:00", "18:00", "", "", "", "", "");
            addMedTABLEValue("Nelapine", "Nifedipine", 102, "10", "1", 1, null, null, 1, null, null, 1, null, null, "2", 1, "ED:0", "img91316", "", "08:00", "13:00", "18:00", "", "", "", "", "");
            addMedTABLEValue("Nelapine", "Nifedipine", 102, "5", "1", 1, null, null, 1, null, null, 1, null, null, "2", 1, "ED:0", "img99912", "", "08:00", "13:00", "18:00", "", "", "", "", "");
            addMedTABLEValue("Nicardia", "Nifedipine", 102, "5", "1", 1, null, null, 1, null, null, 1, null, null, "2", 1, "ED:0", "img99911", "", "08:00", "13:00", "18:00", "", "", "", "", "");
            addMedTABLEValue("Nicardia", "Nifedipine", 102, "10", "1", 1, null, null, 1, null, null, 1, null, null, "2", 1, "ED:0", "img99911", "", "08:00", "13:00", "18:00", "", "", "", "", "");
            addMedTABLEValue("Nicardia Retard", "Nifedipine", 102, "20", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0604", "", "08:00", "18:00", "", "", "", "", "", "");
            addMedTABLEValue("Nicardia Retard", "Nifedipine", 102, "10", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0604", "", "08:00", "18:00", "", "", "", "", "", "");
            addMedTABLEValue("Amlopine", "Amlodipine", 103, "5", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img1201", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Amlopine", "Amlodipine", 103, "10", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img1201", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Norvasc", "Amlodipine", 103, "5", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img1201", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Norvasc", "Amlodipine", 103, "10", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img1201", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Deten", "Amlodipine", 103, "5", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img1201", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Deten", "Amlodipine", 103, "10", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img1201", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Caduet 10/20", "Amlodipine;Atorvastatin", 103, "10", "1", 64, "20", "1", 1, null, null, 1, null, null, "1", 1, "ED:0", "img0103", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Caduet 10/40", "Amlodipine;Atorvastatin", 103, "10", "1", 64, "40", "1", 1, null, null, 1, null, null, "1", 1, "ED:0", "img0103", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Caduet 5/10", "Amlodipine;Atorvastatin", 103, "5", "1", 64, "10", "1", 1, null, null, 1, null, null, "1", 1, "ED:0", "img0101", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Caduet 10/10", "Amlodipine;Atorvastatin", 103, "10", "1", 64, "10", "1", 1, null, null, 1, null, null, "1", 1, "ED:0", "img0103", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Sulfadiazine GDH", "Sulfadiazine", 104, "500", "1", 1, null, null, 1, null, null, 1, null, null, "1", 3, "ED:0", "img0601", "", "06:00", "12:00", "18:00", "24:00", "", "", "", "");
            addMedTABLEValue("Pyrimethamine", "Pyrimethamine", 105, "25", "1", 1, null, null, 1, null, null, 1, null, null, "1", 3, "ED:0", "img0602", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Celsentri", "Maraviroc", 108, "300", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img1403", "A", "08:00", "20:00", "", "", "", "", "", "");
            addMedTABLEValue("Celsentri", "Maraviroc", 108, "150", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img1403", "A", "08:00", "20:00", "", "", "", "", "", "");
            addMedTABLEValue("Isentress FC", "Raltegravir", 109, "400", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED", "img1413", "A", "20:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Proscar", "Finasteride", 111, "5", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img1503", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Firide", "Finasteride", 111, "5", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0103", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Propecia", "Finasteride", 111, "1", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img1612", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Firide", "Finasteride", 111, "1", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0104", "", "08:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Matcine", "Chlorpromazine", 112, "100", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0601", "", "08:00", "13:00", "18:00", "", "", "", "", "");
            addMedTABLEValue("Matcine", "Chlorpromazine", 112, "50", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0601", "", "08:00", "13:00", "18:00", "", "", "", "", "");
            addMedTABLEValue("Matcine", "Chlorpromazine", 112, "25", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0602", "", "08:00", "13:00", "18:00", "", "", "", "", "");
            addMedTABLEValue("Pernazine", "Perphenazine", 113, "2", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0604", "", "08:00", "13:00", "18:00", "", "", "", "", "");
            addMedTABLEValue("Pernazine", "Perphenazine", 113, "4", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0617", "", "08:00", "13:00", "18:00", "", "", "", "", "");
            addMedTABLEValue("Pernazine", "Perphenazine", 113, "8", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0614", "", "08:00", "13:00", "18:00", "", "", "", "", "");
            addMedTABLEValue("Stemetil", "Prochlorperazine", 114, "5", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0601", "", "08:00", "13:00", "18:00", "", "", "", "", "");
            addMedTABLEValue("Thiosia", "Thioridazine", 115, "10", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0614", "", "08:00", "13:00", "18:00", "", "", "", "", "");
            addMedTABLEValue("Thiosia", "Thioridazine", 115, "25", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0616", "", "08:00", "13:00", "18:00", "", "", "", "", "");
            addMedTABLEValue("Erycin", "Erythromycin", 116, "250", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0915", "", "07:00", "11:00", "17:00", "21:00", "", "", "", "");
            addMedTABLEValue("Stacin", "Erythromycin", 116, "250", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0915", "", "07:00", "11:00", "17:00", "21:00", "", "", "", "");
            addMedTABLEValue("Tomcin", "Erythromycin", 116, "250", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0615", "", "07:00", "11:00", "17:00", "21:00", "", "", "", "");

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
            addnameGenericTABLEValue("Isosorbide dinitrate"); //94
            addnameGenericTABLEValue("isosorbide 5-mononitrate"); //95
            addnameGenericTABLEValue("Tamsulosin"); //96
            addnameGenericTABLEValue("Diltiazem"); //97
            addnameGenericTABLEValue("Manidipine"); //98
            addnameGenericTABLEValue("Nimodipine"); //99
            addnameGenericTABLEValue("Lercanidipine"); //100
            addnameGenericTABLEValue("Felodipine"); //101
            addnameGenericTABLEValue("Nifedipine"); //102
            addnameGenericTABLEValue("Amlodipine"); //103
            addnameGenericTABLEValue("Sulfadiazine"); //104
            addnameGenericTABLEValue("Pyrimethamine"); //105
            addnameGenericTABLEValue("Elivitegravir"); //106
            addnameGenericTABLEValue("Cabicistat"); //107
            addnameGenericTABLEValue("Maraviroc"); //108
            addnameGenericTABLEValue("Raltegravir"); //109
            addnameGenericTABLEValue("Nelfinavir"); //110
            addnameGenericTABLEValue("Finasteride"); //111
            addnameGenericTABLEValue("Chlorpromazine"); //112
            addnameGenericTABLEValue("Perphenazine"); //113
            addnameGenericTABLEValue("Prochlorperazine"); //114
            addnameGenericTABLEValue("Thioridazine"); //115
            addnameGenericTABLEValue("Erythromycin stearate"); //116




        }
    }  //nameGenericTABLEData

    public void waringTABLEData() {
        String column_TABLE[] = {wcolumn_id, wcolumn_med_id, wcolumn_pharmaco_id, wcolumn_warning_Detail};
        Cursor cursor = readSqLiteDatabase.query(warningTABLE, column_TABLE, null, null, null, null, null);
        if (cursor.getCount() == 0) {

            addValueToWaringTABLE("7", "", "  - ห้าม!!! ใช้ยานี้ร่วมกับยารักษาอาการปวดศีรษะไมเกรน (Cafergot," +
                    " Bellergal หรือ ergotamine) เพราะอาจทำให้เกิดอาการเส้นเลือดที่ไป" +
                    "เลี้ยงปลายมือปลายเท้าหดตัวอย่างรุนแรง เกิดเนื้อตายได้ \nมีปัญาหปรึกษาเภสัชกร");


        }
    } //warningTABLEData

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
            adddrugInteractionTABLEValue(7, 21, "2", "ไม่ควรรับประทานร่วมกัน", 0, 0);

        }
    } //drugInteractionTABLEDate







}


