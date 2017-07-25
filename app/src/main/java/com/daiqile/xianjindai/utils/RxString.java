package com.daiqile.xianjindai.utils;

import android.util.Log;

public final class RxString {

    public static String dataReplace(String data) {
        return data.replace("&nbsp;", " ");
    }

    public static String repPhone(String sParam) {
        try {
            return sParam.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String repBank(String sParam) {
//        Log.d("RxString", sParam.substring(sParam.length() - 4, sParam.length()));
        return "****"+sParam.substring(sParam.length() - 4, sParam.length());
//        try {
//            return sParam.replaceAll("\\d{12}(\\d{4})", "****$2");
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "";
//        }
    }

    public static boolean passwordlength(String str) {
        return !(str.length() >= 6 && str.length() <= 12);
    }


}
