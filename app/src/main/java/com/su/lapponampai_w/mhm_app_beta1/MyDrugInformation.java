package com.su.lapponampai_w.mhm_app_beta1;

/**
 * Created by apple on 7/5/16.
 */
public class MyDrugInformation {
        //เอาไว้ใส่ข้อมูลยาแบบ Offline

    public String[] receiveInformation(String med_Id) {
        String[] strREAD = new String[5];

        if (med_Id.equals("2")) {

            strREAD[0] = "Efavirenz";
            strREAD[1] = "Stocrin\nEfavirenz GPO";
            strREAD[2] = "- รับประทานยาตรงเวลา วันละ 1 ครั้ง ถ้าไม่ตรงเวลา บวกลบไม่เกิน 30 นาทีเพื่อป้องกันการดื้อยา" +
                    "\n- แนะนำให้รับประทานยาท้องว่างหลังอาหาร 2 ชั่วโมงหรือก่อนนอน" +
                    "\n- หลีกเลี่ยงอาหารไขมันสูงในมื้อก่อนรับประทานยา เพื่อลดอาการข้างเคียง" +
                    "\n\n- อาการข้างเคียงระยะสั้น (หลังเริ่มรับประทานยาครั้งแรกไม่เกิน 6 เดือน)" +
                    "\n     + คลื่นไส้" +
                    "\n     + อาเจียน" +
                    "\n     + เวียนศีรษะ " +
                    "\n     + มึนศีรษะ" +
                    "\n     + ฝันร้าย" +
                    "\n     + สับสน" +
                    "\n     + ผื่นขึ้น คัน มีไข้ ปวดเมื่อยตามเนื้อตัว คลื่นไส้ อาเจียน ปวดข้อ เจ็บคอร่วมกับผื่นแดงเป็นจุด" +
                    "เล็กและเป็นปื้นใหญ่ตรงกลาง ต่อมาเป็นผื่นพุพองและแตกบริเวณเยื่อบุช่องปาก ตา หรืออวัยวะเพศ และอาจ" +
                    "หลุดลอกเป็นแผ่น (ผื่นรุนแรง)" +
                    "\n\n- อาการข้างเคียงระยะยาว (หลังเริ่มรับประทานยาครั้งแรกนานเกิน 6 เดือน)" +
                    "\n     + ภาวะเต้านมโต" +
                    "\n- อาการที่ต้องรีบกลับมาพบแพทย์โดยเร็วที่สุด ห้ามหยุดยาเอง : ผื่นรุนแรง" +
                    "\n- ห้ามรับประทานร่วมกับยาแก้ปวดไมเกรนกลุ่ม ergotamine เช่น Cafergot, Avamigran, " +
                    "Neuramizone และยาแก้ปวดท้อง Cisapride (Cipasid, Cisaride) ";
            strREAD[3] = "Ya & You ยากับคุณ;http://www.yaandyou.net/index.php/component/drug/?nsetidT=1372&drugname=EFAVIRENZ+TABLETS+600+MG&drugtype=t";
            strREAD[4] = "AIDSinfo;https://aidsinfo.nih.gov/drugs/269/efavirenz/0/patient";

        } else if (med_Id.equals("3")) {
            strREAD[0] = "Lamivudine";
            strREAD[1] = "Lamivir";
            strREAD[2] = "- รับประทานยาตรงเวลา ถ้าไม่ตรงเวลา บวกลบไม่เกิน 30 นาที เพื่อป้องกันการดื้อยา" +
                    "\n\n- อาการข้างเคียงระยะสั้น (หลังเริ่มรับประทานยาครั้งแรกไม่เกิน 6 เดือน)" +
                    "\n     + คลื่นไส้" +
                    "\n     + อาเจียน" +
                    "\n\n- อาการข้างเคียงระยะยาว (หลังเริ่มรับประทานยาครั้งแรกนานเกิน 6 เดือน)" +
                    "\n     + ภาวะเลือดเป็นกรด : คลื่นไส้ อาเจียน หายใจเร็ว ใจเต้นเร็ว ความดันต่ำ ช็อค" +
                    "\n\n- อาการที่ต้องรีบกลับมาพบแพทย์โดยเร็วที่สุด ห้ามหยุดยาเอง" +
                    "\n     + ภาวะเลือดเป็นกรด";
            strREAD[3] = "Ya & You ยากับคุณ;https://www.yaandyou.net/index_list.php?drugname=lamivir";
            strREAD[4] = "AIDSinfo;http://aidsinfo.nih.gov/drugs/126/lamivudine/0/patient";

        } else if (med_Id.equals("4")) {
            strREAD[0] = "Nevirapine";
            strREAD[1] = "Neravir";
            strREAD[2] = "- รับประทานยาตรงเวลา ถ้าไม่ตรงเวลา บวกลบไม่เกิน 30 นาที เพื่อป้องกันการดื้อยา" +
                    "\n\n- อาการข้างเคียงระยะสั้น (หลังเริ่มรับประทานยาครั้งแรกไม่เกิน 6 เดือน)" +
                    "\n     + คลื่นไส้" +
                    "\n     + อาเจียน" +
                    "\n     + ผื่นขึ้น คัน มีไข้ ปวดเมื่อยตามเนื้อตัว คลื่นไส้ อาเจียน ปวดข้อ เจ็บคอร่วมกับ" +
                    "ผื่นแดงเป็นจุดเล็กและเป็นปื้นใหญ่ตรงกลาง ต่อมาเป็นผื่นพุพองและแตกบริเวณ เยื่อบุช่องปาก ตา" +
                    "หรืออวัยวะเพศ และอาจหลุดลอกเป็นแผ่น(ผื่นรุนแรง)" +
                    "\n\n- อาการที่ต้องรีบกลับมาพบแพทย์โดยเร็วที่สุด ห้ามหยุดยาเอง" +
                    "\n     + ผื่นรุนแรง";
            strREAD[3] = "Ya & You ยากับคุณ;http://www.yaandyou.net/index_list.php?drugname=NEVIRAPINE";
            strREAD[4] = "AIDSinfo;https://aidsinfo.nih.gov/drugs/116/nevirapine/0/patient";
        } else if (med_Id.equals("5")) {
            strREAD[0] = "Stavudine";
            strREAD[1] = "Stavir";
            strREAD[2] = "- รับประทานยาตรงเวลา วันละ 2 ครั้ง ห่างกัน 12 ชั่วโมง ถ้าไม่ตรงเวลาบวกลบไม่เกิน 30 นาที เพื่อป้องกันการดื้อยา" +
                    "\n\n- อาการข้างเคียงระยะสั้น (หลังเริ่มรับประทานยาครั้งแรกไม่เกิน 6 เดือน)" +
                    "\n     + คลื่นไส้" +
                    "\n     + อาเจียน" +
                    "\n\n- อาการข้างเคียงระยะยาว (หลังเริ่มรับประทานยาครั้งแรกนานเกิน 6 เดือน)" +
                    "\n     + ภาวะเลือดเป็นกรด : คลื่นไส้ อาเจียน หายใจเร็ว ใจเต้นเร็ว ความดันต่ำ ช็อคอาจมีแขนขาอ่อนแรงร่วมด้วย" +
                    "\n     + ภาวะไขมันที่แขนขาลดลง ก้นฝ่อ หน้าตอบ อ้วนลงพุง เต้านมโต มีการสะสมของไขมันที่คอด้านหลัง" +
                    "\n     + อาการชา ไม่มีความรู้สึกที่ปลายนิ้วมือนิ้วเท้า ปวดเส้นประสาทตามแขนขา" +
                    "\n     + ภาวะน้ำตาลในเลือดสูง" +
                    "\n     + ภาวะไขมันในเลือดสูง" +
                    "\n\n- อาการที่ต้องรีบกลับมาพบแพทย์โดยเร็วที่สุด ห้ามหยุดยาเอง" +
                    "\n     + ภาวะเลือดเป็นกรด";
            strREAD[3] = "Ya & You ยากับคุณ;http://www.yaandyou.net/index_list.php?drugname=stavudine";
            strREAD[4] = "AIDSinfo;https://aidsinfo.nih.gov/drugs/43/stavudine/0/patient";
        } else if (med_Id.equals("6")) {
            strREAD[0] = "Tenoforvir";
            strREAD[1] = "Viread\nTenoforvir GPO";
            strREAD[2] = "- รับประทานยาตรงเวลาวันละ 1 ครั้ง ถ้าไม่ตรงเวลา บวกลบไม่เกิน 30 นาทีเพื่อป้องกันการดื้อยา" +
                    "\n- แนะนำให้ดื่มน้ำมากๆเพื่อป้องกันพิษต่อไต" +
                    "\n\n- อาการข้างเคียงระยะสั้น (หลังเริ่มรับประทานยาครั้งแรกไม่เกิน 6 เดือน)" +
                    "\n     + คลื่นไส้" +
                    "\n     + อาเจียน" +
                    "\n     + ปัสสาวะไม่ออกหรือออกมากผิดปกติ, บวม, กระหายน้ำ, หายใจเร็ว(พิษต่อไต)" +
                    "\n\n- อาการข้างเคียงระยะยาว (หลังเริ่มรับประทานยาครั้งแรกนานเกิน 6 เดือน)" +
                    "\n     + ปัสสาวะไม่ออกหรือออกมากผิดปกติ, บวม, กระหายน้ำ, หายใจเร็ว(พิษต่อไต)" +
                    "\n     + ภาวะเลือดเป็นกรด : คลื่นไส้ อาเจียน หายใจเร็ว ใจเต้นเร็ว ความดันต่ำ ช็อค" +
                    "\n     + กระดูกบาง กระดูกพรุน";
            strREAD[3] = "Ya & You ยากับคุณ;http://www.yaandyou.net/index_list.php?drugname=TENOFOVIR%20DISOPROXIL%20FUMARATE";
            strREAD[4] = "AIDSinfo;https://aidsinfo.nih.gov/drugs/290/tenofovir-disoproxil-fumarate/0/patient";
        } else if (med_Id.equals("7")) {
            strREAD[0] = "Atazanavir";
            strREAD[1] = "Reyataz";
            strREAD[2] = "- รับประทานยาตรงเวลา วันละ 1 ครั้ง ถ้าไม่ตรงเวลา บวกลบไม่เกิน 30 นาทีเพื่อป้องกันการดื้อยา" +
                    "\n- แนะนำให้รับประทานยาหลังอาหารทันทีเพื่อเพิ่มการดูดซึมและลดผลข้างเคียงทางเดินอาหาร" +
                    "\n\n- อาการข้างเคียงระยะสั้น (หลังเริ่มรับประทานยาครั้งแรกไม่เกิน 6 เดือน)" +
                    "\n     + คลื่นไส้" +
                    "\n     + อาเจียน" +
                    "\n     + ท้องเสีย" +
                    "\n     + ตัวเหลือง ตาเหลือง(ดีซ่าน)" +
                    "\n     + ฝันร้าย" +
                    "\n     + สับสน" +
                    "\n\n- อาการข้างเคียงระยะยาว (หลังเริ่มรับประทานยาครั้งแรกนานเกิน 6 เดือน)" +
                    "\n     + ภาวะน้ำตาลในเลือดสูง" +
                    "\n     + ภาวะไขมันในเลือดสูง" +
                    "\n     + ภาวะไขมันที่แขนขาลดลง ก้นฝ่อ หน้าตอบ อ้วนลงพุง เต้านมโต มีการสะสมของไขมันที่คอด้านหลัง" +
                    "\n     + มีอาการปวดกระดูกและปวดมากขึ้นเรื่อยๆ" +
                    "\n\n- อาการที่ต้องรีบกลับมาพบแพทย์โดยเร็วที่สุด ห้ามหยุดยาเอง" +
                    "\n     + ดีซ่าน" +
                    "\n     + ปวดกระดูกมากผิดปกติ" +
                    "\n- ห้ามรับประทานร่วมกับยาแก้ปวดไมเกรนกลุ่ม ergotamine เช่น Cafergot, Avamigran, " +
                    "Neuramizone และยาแก้ปวดท้อง Cisapride (Cipasid, Cisaride) ";
            strREAD[3] = "Ya & You ยากับคุณ;http://www.yaandyou.net/index_list.php?drugname=reyataz";
            strREAD[4] = "AIDSinfo;https://aidsinfo.nih.gov/drugs/314/atazanavir/0/patient";
        }else if (med_Id.equals("13")) {
            strREAD[0] = "Zidovudine";
            strREAD[1] = "T.O. vir";
            strREAD[2] = "- รับประทานยาตรงเวลา วันละ 2 ครั้ง ห่างกัน 12 ชั่วโมง ถ้าไม่ตรงเวลาบวกลบไม่เกิน 30 นาที เพื่อป้องกันการดื้อยา" +
                    "\n\n- อาการข้างเคียงระยะสั้น (หลังเริ่มรับประทานยาครั้งแรกไม่เกิน 6 เดือน)" +
                    "\n     + คลื่นไส้" +
                    "\n     + อาเจียน" +
                    "\n     + เล็บดำ" +
                    "\n     + ผมร่วง" +
                    "\n     + ซีด" +
                    "\n     + ติดเชื้อง่าย" +
                    "\n\n- อาการข้างเคียงระยะยาว (หลังเริ่มรับประทานยาครั้งแรกนานเกิน 6 เดือน)" +
                    "\n     + ภาวะเลือดเป็นกรด : คลื่นไส้ อาเจียน หายใจเร็ว ใจเต้นเร็ว ความดันต่ำ ช็อคอาจมีแขนขาอ่อนแรงร่วมด้วย" +
                    "\n     + ภาวะไขมันที่แขนขาลดลง ก้นฝ่อ หน้าตอบ อ้วนลงพุง เต้านมโต มีการสะสมของไขมันที่คอด้านหลัง" +
                    "\n\n- อาการที่ต้องรีบกลับมาพบแพทย์โดยเร็วที่สุด ห้ามหยุดยาเอง" +
                    "\n     + ภาวะเลือดเป็นกรด";
            strREAD[3] = "Ya & You ยากับคุณ;http://www.yaandyou.net/index_list.php?drugname=Zidovudine";
            strREAD[4] = "AIDSinfo;https://aidsinfo.nih.gov/drugs/4/zidovudine/0/patient";
        }else {
            strREAD[0] = "";
            strREAD[1] = "";
            strREAD[2] = "";
            strREAD[3] = "";
            strREAD[4] = "";
        }


        return strREAD;
    }

} //Main Class
