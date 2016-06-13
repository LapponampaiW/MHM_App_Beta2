package com.su.lapponampai_w.mhm_app_beta1;

import java.text.SimpleDateFormat;

/**
 * Created by apple on 5/1/16.
 */
public class MyData {

    public String[] translate_uomArray(String[] uom) {

        String[] strRead = new String[uom.length];

        for (int i = 0; i < uom.length ; i++) {

            if (uom[i].equals("1")) {
                strRead[i] = "mg";
            } else if (uom[i].equals("2")) {
                strRead[i] = "cc";
            } else if (uom[i] == null || uom[i].equals("")) {
                strRead[i] = "N/A";
            }
        }
        return strRead;
    } //translate_uom


    public String translate_EA(String ea) {
        String strREAD = null;

        if (ea.equals("1")) {
            strREAD = "เม็ด";
        }

        return strREAD;
    }

    public String translate_uom(String uom) {

        String strREAD = "N/A";
        if (uom.equals("1")) {
            strREAD = "mg";
        } else if (uom.equals("2")) {
            strREAD = "cc";
        }

        return strREAD;
    }

    public String[] translate_Which_Date_D(String which_date_d) {
        String[] strREAD = null;
        strREAD = new String[2];

        if (which_date_d.equals("ED:0")) {
            strREAD[0] = "1";
            strREAD[1] = "ทานทุกวัน";

        }

        return strREAD;
    }


    public int[] translate_Appearance(String[] appearance) {

        int[] intsRead = new int[appearance.length];
        for (int i = 0; i < appearance.length; i++) {
            if (appearance[i].equals("1")) {
                intsRead[i] = R.drawable.img0101;
            } else if (appearance[i].equals("2")) {
                intsRead[i] = R.drawable.img0102;
            } else if (appearance[i].equals("3")) {
                intsRead[i] = R.drawable.img0201;
            } else if (appearance[i].equals("4")) {
                intsRead[i] = R.drawable.img0302;
            } else if (appearance[i].equals("5")) {
                intsRead[i] = R.drawable.img0203;
            } else if (appearance[i].equals("6")) {
                intsRead[i] = R.drawable.img0501;
            } else if (appearance[i].equals("icon_question")) {
                intsRead[i] = R.drawable.icon_question;
            } else if (appearance[i].equals("7")) {
                intsRead[i] = R.drawable.img90506;
            } else if (appearance[i].equals("8")) {
                intsRead[i] = R.drawable.img0604;
            } else if (appearance[i].equals("9")) {
                intsRead[i] = R.drawable.img0607;
            }
        }
        return intsRead;
    }


    public int[] translate_Small_Appearance(String[] appearance) {

        int[] intsRead = new int[appearance.length];
        for (int i = 0; i < appearance.length; i++) {
            if (appearance[i].equals("1")) {
                intsRead[i] = R.drawable.ib0101;
            } else if (appearance[i].equals("2")) {
                intsRead[i] = R.drawable.ib0102;
            } else if (appearance[i].equals("3")) {
                intsRead[i] = R.drawable.ib0201;
            } else if (appearance[i].equals("4")) {
                intsRead[i] = R.drawable.ib0302;
            } else if (appearance[i].equals("5")) {
                intsRead[i] = R.drawable.ib0203;
            } else if (appearance[i].equals("6")) {
                intsRead[i] = R.drawable.ib0501;
            } else if (appearance[i].equals("7")) {
                intsRead[i] = R.drawable.ib90506;
            } else if (appearance[i].equals("8")) {
                intsRead[i] = R.drawable.ib0604;
            } else if (appearance[i].equals("9")) {
                intsRead[i] = R.drawable.ib0607;
            }
        }
        return intsRead;
    }

    public int[] translate_Smallate_Appearance(String[] appearance) {

        int[] intsRead = new int[appearance.length];
        for (int i = 0; i < appearance.length; i++) {



            if (appearance[i].equals("1")) {
                intsRead[i] = R.drawable.ibc0101;
            } else if (appearance[i].equals("2")) {
                intsRead[i] = R.drawable.ibc0102;
            } else if (appearance[i].equals("3")) {
                intsRead[i] = R.drawable.ibc0201;
            } else if (appearance[i].equals("4")) {
                intsRead[i] = R.drawable.ibc0302;
            } else if (appearance[i].equals("5")) {
                intsRead[i] = R.drawable.ibc0203;
            } else if (appearance[i].equals("6")) {
                intsRead[i] = R.drawable.ibc0501;
            } else if (appearance[i].equals("7")) {
                intsRead[i] = R.drawable.ibc90506;
            } else if (appearance[i].equals("8")) {
                intsRead[i] = R.drawable.ibc0604;
            } else if (appearance[i].equals("9")) {
                intsRead[i] = R.drawable.ibc0607;
            }

        }
        return intsRead;
    }

    public String createStringTime(int hour,int minute) {

        String strHour, strMinute, strTime;

        strHour = Integer.toString(hour);
        strMinute = Integer.toString(minute);

        if (strHour.length() == 1) {
            strHour = "0".concat(strHour);
        }
        if (strMinute.length() == 1) {
            strMinute = "0".concat(strMinute);
        }
        strTime = strHour.concat(":");
        strTime = strTime.concat(strMinute);

        return strTime;
    }

    public String createStringDay(int day,int month,int year) {

        String strDay, strMonth, strYear,strDateMonthYear;
        strDay = Integer.toString(day);
        strMonth = Integer.toString(month);

        strYear = Integer.toString(year);

        if (strDay.length() == 1) {
            strDay = "0".concat(strDay);
        }
        if (strMonth.length() == 1) {
            strMonth = "0".concat(strMonth);
        }

        strDateMonthYear = strDay + "/" + strMonth + "/" + strYear;

        return strDateMonthYear;
    }

    public String currentDay() {
        String today;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        today = dateFormat.format(System.currentTimeMillis());

        return today;
    }




    /*
    public String[] stringsNews(String[] med_id) {

        String[] stringsREAD = new String[med_id.length];
        for (int i = 0;i<med_id.length;i++) {

            if (med_id[i].equals("1")) {
                stringsREAD[i] = "เนื้อหาความรู้เกี่ยวกับยา Efaviren GPO";
            } else if (med_id[i].equals("2")) {
                stringsREAD[i] = "เนื้อหาความรู้เกี่ยวกับยา Stocrin";
            }
        }
        return stringsREAD;

    }  //stringsNews

    */


} //Main class

