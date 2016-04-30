package com.su.lapponampai_w.mhm_app_beta1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by apple on 4/20/16.
 */
public class MyHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MHM.db";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_userTABLE = "create table userTABLE" +
            "(_id integer primary key, User text, Password text, Stay text, Email text);";

    //Create table medTABLE
    private static final String CREATE_medTABLE = "create table medTABLE" +
            "(_id integer primary key, Trade_name text, " +
            "Generic_name1 integer, Dosage1 text, UOM1 text, " +
            "Generic_name2 integer, Dosage2 text, UOM2 text, " +
            "Generic_name3 integer, Dosage3 text, UOM3 text, " +
            "Generic_name4 integer, Dosage4 text, UOM4 text, " +
            "Which_Date_D text, Appearance text, Pharmaco text, T1 time, T2 time," +
            "T3 time, T4 time,T5 time, T6 time,T7 time, T8 time);";



    public MyHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_userTABLE);
        db.execSQL(CREATE_medTABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}  //Main Class
