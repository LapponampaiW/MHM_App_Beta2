package com.su.lapponampai_w.mhm_app_beta1;

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

    public String translate_uom(String uom) {

        String strREAD = "N/A";
        if (uom.equals("1")) {
            strREAD = "mg";
        } else if (uom.equals("2")) {
            strREAD = "cc";
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
            }


        }
        return intsRead;
    }




} //Main class

