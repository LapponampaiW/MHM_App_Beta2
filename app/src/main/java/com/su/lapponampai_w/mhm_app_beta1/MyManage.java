package com.su.lapponampai_w.mhm_app_beta1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

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
    private static final String[] column_medTABLE = {mcolumn_id,mcolumn_trade_name,mcolumn_generic_name1,mcolumn_dosage1,
                                    mcolumn_uom1,mcolumn_generic_name2,mcolumn_dosage2,mcolumn_uom2,mcolumn_generic_name3,
                                    mcolumn_dosage3,mcolumn_uom3,mcolumn_generic_name4,mcolumn_dosage4,mcolumn_uom4,
                                    mcolumn_which_date_d,mcolumn_appearance,mcolumn_pharmaco,mcolumn_t1,mcolumn_t2,
                                    mcolumn_t3,mcolumn_t4,mcolumn_t5,mcolumn_t6,mcolumn_t7,mcolumn_t8};

    //nameGenericTABLE
    private static final String nameGenericTABLE = "nameGenericTABLE";
    private static final String gcolumn_id = "_id";
    private static final String gcolumn_generic_name = "Generic_name";
    private static final String[] column_nameGenericTABLE = {gcolumn_id,gcolumn_generic_name};


    public MyManage(Context context) {
        helper = new MyHelper(context);
        readSqLiteDatabase = helper.getReadableDatabase();
        writeSqLiteDatabase = helper.getWritableDatabase();
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
        //ทำ Search ตัวอักษรแบบไม่ต้องเขียนครบก็ได้
       Cursor cursor = readSqLiteDatabase.query(medTABLE,column_medTABLE,"Trade_name" + " LIKE '%" + strWord + "%'",null,null,null,null);
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
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_generic_name1));  //ใส่ใน listViewAddTG
                        break;
                    case 3:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_dosage1));
                        break;
                    case 4:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_uom1));
                        break;
                    case 5:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_generic_name2));  //ใส่ใน listViewAddTG
                        break;
                    case 6:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_dosage2));
                        break;
                    case 7:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_uom2));
                        break;
                    case 8:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_generic_name3));  //ใส่ใน listViewAdTG
                        break;
                    case 9:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_dosage3));
                        break;
                    case 10:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_uom3));
                        break;
                    case 11:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_generic_name4));
                        break;
                    case 12:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_dosage4));
                        break;
                    case 13:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_uom4));
                        break;
                    case 14:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_which_date_d));
                        break;
                    case 15:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_appearance));
                        break;
                    case 16:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_pharmaco));
                        break;
                    case 17:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_t1));
                        break;
                    case 18:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_t2));
                        break;
                    case 19:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_t3));
                        break;
                    case 20:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_t4));
                        break;
                    case 21:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_t5));
                        break;
                    case 22:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_t6));
                        break;
                    case 23:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_t7));
                        break;
                    case 24:
                        strread[i] = cursor.getString(cursor.getColumnIndex(mcolumn_t8));
                        break;
                    default:
                        break;
                }
                cursor.moveToNext();
            }

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


} //Main class
