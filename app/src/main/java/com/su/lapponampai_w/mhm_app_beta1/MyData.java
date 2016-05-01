package com.su.lapponampai_w.mhm_app_beta1;

/**
 * Created by apple on 5/1/16.
 */
public class MyData {

    public String[] translate_uom(String[] uom) {

        String[] strRead = new String[uom.length];

        for (int i = 0; i < uom.length ; i++) {

            if (uom[i].equals("")) {
                strRead[i] = "";
            } else if (uom[i].equals("1")) {
                strRead[i] = "mg";
            } else if (uom[i].equals("2")) {
                strRead[i] = "cc";
            }
        }
        return strRead;
    } //translate_uom

    public int[] translate_Appearance(String[] appearance) {

        int[] intsRead = new int[appearance.length];
        for (int i = 0; i < appearance.length; i++) {
            if (appearance[i].equals("1")) {
                intsRead[i] = R.drawable.exampletablet;
            } else if (appearance[i].equals("2")) {
                intsRead[i] = R.drawable.mainbg;
            }
        }
        return intsRead;
    }

} //Main class

