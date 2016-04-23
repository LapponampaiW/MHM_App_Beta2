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


    public MyHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_userTABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}  //Main Class
