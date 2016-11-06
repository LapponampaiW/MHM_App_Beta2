package com.su.lapponampai_w.mhm_app_beta1;

import android.content.Context;

/**
 * Created by apple on 11/6/16.
 */

public class MySQLiteDataBase {
    Context context;


    MyManage myManage = new MyManage(context);

    public void medTABLE() {

        myManage.addMedTABLEValue("Efaviren GPO", "Efavirenz", 2, "600", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0302", "A", "22:00", "", "", "", "", "", "", "");
        myManage.addMedTABLEValue("Stocrin", "Efavirenz", 2, "600", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0302", "A", "22:00", "", "", "", "", "", "", "");
        myManage.addMedTABLEValue("Lamivir", "Lamivudine", 3, "150", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0101", "A", "08:00", "20:00", "", "", "", "", "", "");
        myManage.addMedTABLEValue("GPO-vir S30", null, 3, "150", "1", 4, "200", "1", 5, "30", "1", 1, null, null, "1", 1, "ED:0", "img0201", "A", "08:00", "20:00", "", "", "", "", "", "");
        myManage.addMedTABLEValue("Tenofovir GPO", "Tenofovir", 6, "300", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0203", "A", "20:00", "", "", "", "", "", "", "");
        myManage.addMedTABLEValue("Viread", "Tenofovir", 6, "300", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0501", "A", "20:00", "", "", "", "", "", "", "");
        myManage.addMedTABLEValue("Reyataz", "Atazanavir", 7, "300", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img90506", "A", "20:00", "", "", "", "", "", "", "");
        myManage.addMedTABLEValue("Cafergot", "Ergotamine Tartrate", 8, "1", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0604", "", "08:00", "", "", "", "", "", "", "");
        myManage.addMedTABLEValue("Prevacid", "Lansoprazole", 9, "30", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0607", "", "07:00", "", "", "", "", "", "", "");
        myManage.addMedTABLEValue("Antacin", "Antacid", 10, "100", "1", 11, "100", "1", 1, null, null, 1, null, null, "1", 1, "ED:0", "img0602", "", "08:00", "13:00", "18:00", "", "", "", "", "");
        myManage.addMedTABLEValue("Norvir", "Ritonavir", 12, "100", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0301", "A", "20:00", "", "", "", "", "", "", "");
        myManage.addMedTABLEValue("GPO-VIR Z250", "Nevirapine", 4, "200", "1", 2, "150", "1", 13, "250", "1", 1, null, null, "1", 1, "ED:0", "img0701", "A", "08:00", "20:00", "", "", "", "", "", "");
        myManage.addMedTABLEValue("Ziagenavir", "Abacavir", 14, "300", "1", 1, null, null, 1, null, null, 1, null, null, "1", 1, "ED:0", "img0302", "A", "22:00", "", "", "", "", "", "", "");
        myManage.addMedTABLEValue("Teevir", "", 6, "300", "1", 2, "600", "1", 15, "200", "1", 1, null, null, "1", 1, "ED:0", "img0304", "A", "22:00", "", "", "", "", "", "", "");

    }
}


