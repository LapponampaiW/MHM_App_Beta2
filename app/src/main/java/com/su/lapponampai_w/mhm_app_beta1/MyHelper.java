package com.su.lapponampai_w.mhm_app_beta1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by apple on 4/20/16.
 */
public class MyHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MHM.db";
    private static final int DATABASE_VERSION = 1;

    //Create userTABLE
    private static final String CREATE_userTABLE = "create table userTABLE" +
            "(_id integer primary key, User text, Password text, Stay text, Email text);";

    //Create table medTABLE
    private static final String CREATE_medTABLE = "create table medTABLE" +
            "(_id integer primary key, Trade_name text, Key_search text, " +
            "Generic_name1 integer, Dosage1 text, UOM1 text, " +
            "Generic_name2 integer, Dosage2 text, UOM2 text, " +
            "Generic_name3 integer, Dosage3 text, UOM3 text, " +
            "Generic_name4 integer, Dosage4 text, UOM4 text, " +
            "Which_Date_D text, Appearance text, Pharmaco text, T1 text, T2 text," +
            "T3 text, T4 text,T5 text, T6 text,T7 text, T8 text);";


    //Create table mainTABLE
    private static final String CREATE_mainTABLE = "create table mainTABLE" +
            "(_id long primary key, Med_id integer, Trade_name text, " +
            "Generic_line text, Which_Date_D text, Appearance text, pharmaco text, " +
            "T1 text, T2 text, T3 text, T4 text,T5 text, T6 text, " +
            "T7 text, T8 text);";


    //Create table nameGenericTABLE
    private static final String CREATE_nameGenericTABLE = "create table nameGenericTABLE" +
            "(_id integer primary key, Generic_name text);";


    //Create table sumTABLE
    private static final String CREATE_sumTABLE = "create table sumTABLE " +
            "(_id integer primary key, Main_id text, DateRef text, TimeRef text," +
            "DateCheck text, TimeCheck text, SkipHold text);";

    //Create table drugInteractionTABLE
    private static final String CREATE_drugInteractionTABLE = "create table drugInteractionTABLE " +
            "(_id integer primary key, Medicine1 integer, Medicine2 integer, " +
            "Type_interaction text, Message text, TimeMedicine1_2 integer, TimeMedicine2_1 integer);";

    //Create table drugInteractionTABLE_For_Query
    private static final String CREATE_drugInteractionTABLE_For_Query = "create table drugInteractionTABLE_For_Query " +
            "(_id integer primary key, Initial_medicine integer, Medicine1 integer, Medicine2 integer, " +
            "Type_interaction text, Message text, TimeMedicine1_2 integer, TimeMedicine2_1 integer);";



    public MyHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_userTABLE);
        db.execSQL(CREATE_medTABLE);
        db.execSQL(CREATE_nameGenericTABLE);
        db.execSQL(CREATE_mainTABLE);
        db.execSQL(CREATE_sumTABLE);
        db.execSQL(CREATE_drugInteractionTABLE);
        db.execSQL(CREATE_drugInteractionTABLE_For_Query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}  //Main Class
