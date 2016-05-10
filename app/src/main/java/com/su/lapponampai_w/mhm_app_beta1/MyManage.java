package com.su.lapponampai_w.mhm_app_beta1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.sql.Time;

/**
 * Created by apple on 4/20/16.
 */
public class MyManage {

    MyHelper helper;
    SQLiteDatabase readSqLiteDatabase, writeSqLiteDatabase;

    //userTABLE
    private static final String userTABLE = "userTABLE";
    private static final String ucolumn_id = "_id";
    private static final String ucolumn_User = "User";
    private static final String ucolumn_Password = "Password";
    private static final String ucolumn_Stay = "Stay";
    private static final String ucolumn_Email = "Email";
    private static final String[] column_userTABLE = {ucolumn_id,ucolumn_User,ucolumn_Password,ucolumn_Stay,ucolumn_Email};

    //medTABLE
    private static final String medTABLE = "medTABLE";
    private static final String mcolumn_id = "_id";
    private static final String mcolumn_trade_name = "Trade_name";
    private static final String mcolumn_key_search = "Key_search";
    private static final String mcolumn_generic_name1 = "Generic_name1";
    private static final String mcolumn_dosage1 = "Dosage1";
    private static final String mcolumn_uom1 = "UOM1";
    private static final String mcolumn_generic_name2 = "Generic_name2";
    private static final String mcolumn_dosage2 = "Dosage2";
    private static final String mcolumn_uom2 = "UOM2";
    private static final String mcolumn_generic_name3 = "Generic_name3";
    private static final String mcolumn_dosage3 = "Dosage3";
    private static final String mcolumn_uom3 = "UOM3";
    private static final String mcolumn_generic_name4 = "Generic_name4";
    private static final String mcolumn_dosage4 = "Dosage4";
    private static final String mcolumn_uom4 = "UOM4";
    private static final String mcolumn_which_date_d = "Which_Date_D";
    private static final String mcolumn_appearance = "Appearance";
    private static final String mcolumn_pharmaco = "Pharmaco";
    private static final String mcolumn_t1 = "T1";
    private static final String mcolumn_t2 = "T2";
    private static final String mcolumn_t3 = "T3";
    private static final String mcolumn_t4 = "T4";
    private static final String mcolumn_t5 = "T5";
    private static final String mcolumn_t6 = "T6";
    private static final String mcolumn_t7 = "T7";
    private static final String mcolumn_t8 = "T8";
    private static final String[] column_medTABLE = {mcolumn_id,mcolumn_trade_name,mcolumn_key_search,mcolumn_generic_name1,mcolumn_dosage1,
                                    mcolumn_uom1,mcolumn_generic_name2,mcolumn_dosage2,mcolumn_uom2,mcolumn_generic_name3,
                                    mcolumn_dosage3,mcolumn_uom3,mcolumn_generic_name4,mcolumn_dosage4,mcolumn_uom4,
                                    mcolumn_which_date_d,mcolumn_appearance,mcolumn_pharmaco,mcolumn_t1,mcolumn_t2,
                                    mcolumn_t3,mcolumn_t4,mcolumn_t5,mcolumn_t6,mcolumn_t7,mcolumn_t8};

    //nameGenericTABLE
    private static final String nameGenericTABLE = "nameGenericTABLE";
    private static final String gcolumn_id = "_id";
    private static final String gcolumn_generic_name = "Generic_name";
    private static final String[] column_nameGenericTABLE = {gcolumn_id,gcolumn_generic_name};

    //sumTABLE
    private static final String sum_table = "sumTABLE";
    private static final String column_Main_id = "Main_id";
    private static final String column_DateRef = "DateRef";
    private static final String column_TimeRef = "TimeRef";
    private static final String column_DateCheck = "DateCheck";
    private static final String column_TimeCheck = "TimeCheck";
    private static final String column_SkipHold = " SkipHold";


    public MyManage(Context context) {
        helper = new MyHelper(context);

        readSqLiteDatabase = helper.getReadableDatabase();
        writeSqLiteDatabase = helper.getWritableDatabase();

    } //Constructor

    // Read All mainTABLE
    public String[] readAllMainTABLE(int intColumn) { //0 หมายถึง ==> _id , 1 ==>T1

        String[] resultStrings = null;
        Cursor cursor = readSqLiteDatabase.query("mainTABLE",
                new String[]{"_id", "T1"},
                null,null,null,null,null);

        resultStrings = new String[cursor.getCount()];

        cursor.moveToFirst();
        for (int i=0; i<cursor.getCount();i++) {

            resultStrings[i] = cursor.getString(intColumn);
            cursor.moveToNext();

        } //for
        cursor.close();

            return resultStrings;
    }


    public long addValueTomainTABLE(String strMed_id,
                                    String strTrade_name,
                                    String strGeneric_line,
                                    String strWhich_Date_D,
                                    String strAppearance,
                                    String strPharmaco,
                                    String strT1,
                                    String strT2,
                                    String strT3,
                                    String strT4,
                                    String strT5,
                                    String strT6,
                                    String strT7,
                                    String strT8) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("Med_id", strMed_id);
        contentValues.put("Trade_name", strTrade_name);
        contentValues.put("Generic_line", strGeneric_line);
        contentValues.put("Which_Date_D", strWhich_Date_D);
        contentValues.put("Appearance", strAppearance);
        contentValues.put("Pharmaco", strPharmaco);
        contentValues.put("T1", strT1);
        contentValues.put("T2", strT2);
        contentValues.put("T3", strT3);
        contentValues.put("T4", strT4);
        contentValues.put("T5", strT5);
        contentValues.put("T6", strT6);
        contentValues.put("T7", strT7);
        contentValues.put("T8", strT8);


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
        contentValues.put(ucolumn_User,user);
        contentValues.put(ucolumn_Password,password);
        contentValues.put(ucolumn_Stay,stay);
        contentValues.put(ucolumn_Email,email);
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

    public String getArrayStringIndex(String[] strAry,String keyWord_String) {

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
        contentValues.put(ucolumn_Stay,"1");

        writeSqLiteDatabase.update(userTABLE, contentValues, "_id =?",new String[]{String.valueOf(id)});

    }

    public String[] filterAddMed(int intColumn,String strWord) {
        String[] strread = null;


       //Cursor cursor = readSqLiteDatabase.query(medTABLE,column_medTABLE,"Trade_name =?",new String[]{String.valueOf(strWord)},null,null,null);
        //ทำ Search ตัวอักษรแบบไม่ต้องเขียนครบก็ได้ โดย Search ตาม key search และ tradename
       Cursor cursor = readSqLiteDatabase.query(medTABLE,column_medTABLE,"Trade_name" + " LIKE '%" + strWord + "%'" + " or " + "Key_search" + " LIKE '%" + strWord + "%'",null,null,null,null);
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

    //รับค่าจาก id
    public String[] searchById(String id) {
        String[] strread = null;
        Cursor cursor = readSqLiteDatabase.query(medTABLE, column_medTABLE, "_id =?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            strread = new String[26];
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
        }
        return strread;
    }



    public String[] translate_GenericName(String[] genericname) {

        String[] strRead = new String[genericname.length];

        for (int i = 0; i < genericname.length; i++) {
            Cursor cursor = readSqLiteDatabase.query(nameGenericTABLE,column_nameGenericTABLE,"_id =?",new String[]{String.valueOf(genericname[i])},null,null,null);
            if (cursor != null) {
                cursor.moveToFirst();
                strRead[i] = cursor.getString(cursor.getColumnIndex(gcolumn_generic_name));
            } else {
                strRead[i] = null;
            }
        }

        return strRead;
    } //translate_GenericName

    public double addMedTABLEValue(String s_trade_name, String s_key_search,
                              int s_generic_name1, String s_dosage1, String s_uom1,
                              int s_generic_name2, String s_dosage2, String s_uom2,
                              int s_generic_name3, String s_dosage3, String s_uom3,
                              int s_generic_name4, String s_dosage4, String s_uom4,
                              String s_which_date_d, String s_appearance, String s_pharmaco,
                              String s_t1, String s_t2, String s_t3, String s_t4,
                              String s_t5, String s_t6, String s_t7, String s_t8) {

        ContentValues contentValues = new ContentValues();
        double adddouble = 0;
        contentValues.put(mcolumn_trade_name,s_trade_name);
        contentValues.put(mcolumn_key_search,s_key_search);
        contentValues.put(mcolumn_generic_name1,s_generic_name1);
        contentValues.put(mcolumn_dosage1,s_dosage1);
        contentValues.put(mcolumn_uom1,s_uom1);
        contentValues.put(mcolumn_generic_name2,s_generic_name2);
        contentValues.put(mcolumn_dosage2,s_dosage2);
        contentValues.put(mcolumn_uom2,s_uom2);
        contentValues.put(mcolumn_generic_name3,s_generic_name3);
        contentValues.put(mcolumn_dosage3,s_dosage3);
        contentValues.put(mcolumn_uom3,s_uom3);
        contentValues.put(mcolumn_generic_name4,s_generic_name4);
        contentValues.put(mcolumn_dosage4,s_dosage4);
        contentValues.put(mcolumn_uom4,s_uom4);
        contentValues.put(mcolumn_which_date_d,s_which_date_d);
        contentValues.put(mcolumn_appearance,s_appearance);
        contentValues.put(mcolumn_pharmaco,s_pharmaco);
        contentValues.put(mcolumn_t1,s_t1);
        contentValues.put(mcolumn_t2,s_t2);
        contentValues.put(mcolumn_t3,s_t3);
        contentValues.put(mcolumn_t4,s_t4);
        contentValues.put(mcolumn_t5,s_t5);
        contentValues.put(mcolumn_t6,s_t6);
        contentValues.put(mcolumn_t7,s_t7);
        contentValues.put(mcolumn_t8,s_t8);

        adddouble = writeSqLiteDatabase.insert(medTABLE, null, contentValues);


        return adddouble;
    } //addMedTABLE

    public long addnameGenericTABLEValue(String s_generic_name) {
        ContentValues contentValues = new ContentValues();
        long addlong = 0;
        contentValues.put(gcolumn_generic_name,s_generic_name);

        return writeSqLiteDatabase.insert(nameGenericTABLE, null, contentValues);
    }



    //ทำการ add ค่าเข้าไปถ้า table มันว่างอะนะ
    public void medTABLEData() {

        Cursor cursor = readSqLiteDatabase.query(medTABLE, column_medTABLE, null, null, null, null, null);

        if (cursor.getCount()==0) {
            addMedTABLEValue("Antivir","Zidovudine", 5, "250", "1", 1, null, null, 1, null, null, 1, null, null, "1", "1", null, "8:00", "", "", "", "", "", "", "");
            addMedTABLEValue("GPO vir S",null, 2, "100", "1", 3, "100", "1", 4, "100", "1", 1, null, null, "1", "1", null, "9:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Curam", null, 6, "1", "2", 7, "100", "1", 2, "1000", "1", 3, "100", "1", "1", "1", null, "10:00", "", "", "", "", "", "", "");
            addMedTABLEValue("GPO vir Z",null, 5, "250", "1", 3, "100", "1", 4, "100", "1", 1, null, null, "1", "1", null, "11:00", "", "", "", "", "", "", "");
            addMedTABLEValue("Augmentin", "Amoxicillin", 6, "1", "2", 7, "125", "1", 1, null, null, 1, null, null, "1", "1", "1", "11:30", "", "", "", "", "", "", "");


        }

    } //medTABLEDate

    public void nameGenericTABLEData() {
        Cursor cursor = readSqLiteDatabase.query(nameGenericTABLE, column_nameGenericTABLE, null, null, null, null, null);

        if (cursor.getCount()==0) {

            addnameGenericTABLEValue("N/A"); //1
            addnameGenericTABLEValue("Stavudine"); //2
            addnameGenericTABLEValue("Lamivudine"); //3
            addnameGenericTABLEValue("Nevirapine"); //4
            addnameGenericTABLEValue("Zidovudine"); //5
            addnameGenericTABLEValue("Amoxicillin"); //6
            addnameGenericTABLEValue("Clavulanic"); //7
        }
    }


} //Main class
