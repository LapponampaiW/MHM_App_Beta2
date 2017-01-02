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
            "(_id integer primary key, " +
            "User text, Password text, Stay text, " +
            "HN text, Last_updated text, Notification text, Allowed_notif text," +
            " Always_username text, Times_notif text, Appointment_notif text, Advance_mode text);";

    //Create table medTABLE
    private static final String CREATE_medTABLE = "create table medTABLE" +
            "(_id integer primary key, Trade_name text, Key_search text, " +
            "Generic_name1 integer, Dosage1 text, UOM1 text, " +
            "Generic_name2 integer, Dosage2 text, UOM2 text, " +
            "Generic_name3 integer, Dosage3 text, UOM3 text, " +
            "Generic_name4 integer, Dosage4 text, UOM4 text, " +
            "EA text, Amount_tablet double, " +
            "Which_Date_D text, Appearance text, Pharmaco text, T1 text, T2 text," +
            "T3 text, T4 text,T5 text, T6 text,T7 text, T8 text);";


    //Create table mainTABLE
    private static final String CREATE_mainTABLE = "create table mainTABLE" +
            "(_id integer primary key, Med_id integer, Trade_name text, " +
            "Generic_line text, Amount_tablet double, Which_Date_D text, Appearance text, " +
            "EA text, pharmaco text, StartDate text, FinishDate text, PRN text, " +
            "T1 text, T2 text, T3 text, T4 text,T5 text, T6 text, " +
            "T7 text, T8 text, DateTimeCanceled text);";


    //Create table nameGenericTABLE
    private static final String CREATE_nameGenericTABLE = "create table nameGenericTABLE" +
            "(_id integer primary key, Generic_name text);";


    //Create table sumTABLE
    private static final String CREATE_sumTABLE = "create table sumTABLE " +
            "(_id integer primary key, Main_id text, DateRef text, TimeRef text," +
            "DateCheck text, TimeCheck text, SkipHold text, AddMedicine text);";

    //Create table drugInteractionTABLE
    private static final String CREATE_drugInteractionTABLE = "create table drugInteractionTABLE " +
            "(_id integer primary key, Medicine1 integer, Medicine2 integer, " +
            "Type_interaction text, Message text, TimeMedicine1_2 integer, TimeMedicine2_1 integer);";

    //Create table drugInteractionTABLE_For_Query
    private static final String CREATE_drugInteractionTABLE_For_Query = "create table drugInteractionTABLE_For_Query " +
            "(_id integer primary key, Initial_medicine integer, Medicine1 integer, Medicine2 integer, " +
            "Type_interaction text, Message text, TimeMedicine1_2 integer, TimeMedicine2_1 integer);";

    //Create table timeTABLE
    private static final String CREATE_timeTABLE = "create table timeTABLE " +
            "(_id integer primary key, Time_interval text, Start_time text, End_time text);";

    //Create table displayTABLE
    private static final String CREATE_displayTABLE = "create table displayTABLE " +
            "(_id integer primary key, Position text, Sum_id text, Main_id text, Day text, " +
            "TimeRef text, DateTimeCheck text, Appearance text, SkipHold text);";

    //Create table newsTABLE
    private static final String CREATE_newsTABLE = "create table newsTABLE " +
            "(_id integer primary key, Generic_id text, Message text," +
            " Appearance_News text, Criteria text, Activity text);";

    //Create table addUseTABLE
    private static final String CREATE_addUseTABLE = "create table addUseTABLE " +
            "(_id integer primary key, Main_id text, Add_Use_Adjust_txt text, Amount double, " +
            "Date text);";

    //Create table totalAmountTABLE
    private static final String CREATE_totalAmountTABLE = "create table totalAmountTABLE " +
            "(_id integer primary key, Main_id text, TotalAmount double, DateUpdated text);";

    //Create table appointmentTABLE
    private static final String CREATE_appointmentTABLE = "create table appointmentTABLE " +
            "(_id integer primary key, DateTimeSave Text, AppointmentDate Text, " +
            "AppointmentTime Text, AppointmentDoctor Text, AppointmentNote Text, " +
            "AppointmentSnooze text, AppointmentLab text);";

    //Create table noteTABLE
    private static final String CREATE_noteTABLE = "create table noteTABLE " +
            "(_id integer primary key, DateTimeSave Text, NoteDate Text, " +
            "NoteText Text, Allergy_SideEffect Text);";


    /*
    private static final String CREATE_labTABLE = "create table labTABLE " +
            "(_id integer primary key, DateTimeSave Text, LabDate Text, BloodGlucose Text, BloodPressure Text, " +
            "Weight Text, Temperature Text, LDLCholesterol Text, CD4 Text, ViralLoad Text);";
    */
    //Create table labTABLE
    private static final String CREATE_labTABLE = "create table labTABLE " +
            "(_id integer primary key, DateTimeSave Text, LabDate Text, Body_weight Text, FBS Text, " +
            "Blood_pressure Text, Total_chol Text, Triglyceride Text, HDL Text, LDL Text, SGPT_ALT Text, " +
            "Creatinine Text, BUN Text, CD4 Text, Viral_load Text);";



    //Create table sumTABLE_alternativeImage
    private static final String CREATE_sumTABLE_alternativeImage = "create table sumTABLE_alternativeImage " +
            "(_id integer primary key, Sum_id Text, Appearance Text);";


    //Create table alarmReceiverTABLE
    private static final String CREATE_alarmReceiverTABLE = "create table alarmReceiverTABLE " +
            "(_id integer primary key, Date_time_receiver Text, Sum_id1 Text, Sum_id2 Text" +
            ", Sum_id3 Text, Sum_id4 Text, Sum_id5 Text, Sum_id6 Text, Sum_id7 Text" +
            ", Sum_id8 Text, Sum_id9 Text);";

    //Create table alarmReceiverTABLEAfter15Min
    private static final String CREATE_alarmReceiverTABLEAfter15Min = "create table alarmReceiverTABLEAfter15Min " +
            "(_id integer primary key, Date_time_receiver_After15Min Text, Sum_id1 Text, Sum_id2 Text" +
            ", Sum_id3 Text, Sum_id4 Text, Sum_id5 Text, Sum_id6 Text, Sum_id7 Text" +
            ", Sum_id8 Text, Sum_id9 Text);";

    //Create table alertTABLE
    private static final String CREATE_alertTABLE = "create table alertTABLE " +
            "(_id integer primary key, alert_Type Text, alert_Lab_Id Text, " +
            "alert_Date_Lab Text, alert_Detail Text, alert_ArrayList Text);";

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
        db.execSQL(CREATE_timeTABLE);
        db.execSQL(CREATE_displayTABLE);
        db.execSQL(CREATE_newsTABLE);
        db.execSQL(CREATE_addUseTABLE);
        db.execSQL(CREATE_totalAmountTABLE);
        db.execSQL(CREATE_appointmentTABLE);
        db.execSQL(CREATE_noteTABLE);
        db.execSQL(CREATE_labTABLE);
        db.execSQL(CREATE_sumTABLE_alternativeImage);
        db.execSQL(CREATE_alarmReceiverTABLE);
        db.execSQL(CREATE_alarmReceiverTABLEAfter15Min);
        db.execSQL(CREATE_alertTABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    protected void finalize() throws Throwable {
        this.close();
        super.finalize();
    }





}  //Main Class
