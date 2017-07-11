package com.daiqile.xianjindai.utils;

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

    public static boolean passwordlength(String str) {
        return !(str.length() >= 6 && str.length() <= 12);
    }


}
