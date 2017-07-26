package com.daiqile.xianjindai.utils;

import android.app.Activity;
import android.graphics.Color;

import com.yanzhenjie.album.Album;

import java.util.ArrayList;

/**
 * Created by zkw on 2017/7/26.
 */

public class SelectorImageUtils {

    public final static void selectByofOne(Activity context, ArrayList<String> imageList) {
        Album.album(context)
                .toolBarColor(Color.RED)
                .statusBarColor(Color.BLUE)
                .navigationBarColor(Color.GRAY)
                .title("图片选择")
                .selectCount(1)
                .columnCount(3)
                .camera(true)
                .checkedList(imageList)
                .start(999);
    }
}
