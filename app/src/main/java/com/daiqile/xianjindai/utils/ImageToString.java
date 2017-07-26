package com.daiqile.xianjindai.utils;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

/**
 * Created by zkw on 2017/3/20.
 */

public class ImageToString {
    /**
     * 将图片转换成十六进制字符串
     *
     * @param photo
     * @return
     */
    public static String sendPhoto(ImageView photo) {
        Drawable d = photo.getDrawable();
        Bitmap bitmap = ((BitmapDrawable) d).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);// (0 - 100)压缩文件
        byte[] bt = stream.toByteArray();
        int imageSize = 90;
        while ((bt.length / 1024) >= 300) {
            imageSize = imageSize - 5;
            bitmap.compress(Bitmap.CompressFormat.JPEG, imageSize, stream);// (0 - 100)压缩文件
            bt = stream.toByteArray();
        }
        String photoStr = byte2hex(bt);

        Log.d("zls", "图片大小: " + (bt.length / 1024) + "kb");

        bitmap.recycle();

        return photoStr;
    }

    public static String sendPhoto(Bitmap bitmap) {
//        Drawable d = photo.getDrawable();
//        Bitmap bitmap = ((BitmapDrawable) d).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);// (0 - 100)压缩文件
        byte[] bt = stream.toByteArray();
        int imageSize = 90;
        while ((bt.length / 1024) >= 300) {
            imageSize = imageSize - 5;
            bitmap.compress(Bitmap.CompressFormat.JPEG, imageSize, stream);// (0 - 100)压缩文件
            bt = stream.toByteArray();
        }
        String photoStr = byte2hex(bt);
        Log.d("zls", "图片大小: " + (bt.length / 1024) + "kb");
        bitmap.recycle();
        return photoStr;
    }


    /**
     * 二进制转字符串
     *
     * @param b
     * @return
     */
    public static String byte2hex(byte[] b) {
//        StringBuffer sb = new StringBuffer();
        StringBuilder sb = new StringBuilder();
        String stmp;
//        for (int n = 0; n < b.length; n++) {
//            stmp = Integer.toHexString(b[n] & 0XFF);
//            if (stmp.length() == 1) {
//                sb.append("0" + stmp);
//            } else {
//                sb.append(stmp);
//            }
//        }
        for (byte b1 : b) {
            stmp = Integer.toHexString(b1 & 0XFF);
            if (stmp.length() == 1) {
                sb.append("0" + stmp);
            } else {
                sb.append(stmp);
            }
        }
        return sb.toString();
    }

}
