package com.daiqile.xianjindai.utils;

import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.daiqile.xianjindai.Constants;
import com.daiqile.xianjindai.MyApplication;
import com.daiqile.xianjindai.R;


/**
 * Created by zkw on 2017/6/9.
 */
public final class ImageLoader {
    private volatile static ImageLoader SINGLETON;

    public static ImageLoader getInstance() {
        if (SINGLETON == null) {
            synchronized (ImageLoader.class) {
                if (SINGLETON == null) {
                    SINGLETON = new ImageLoader();
                }
            }
        }
        return SINGLETON;
    }

    private ImageLoader() {
    }

    public void load(String url, final ImageView imageView) {
        Glide.with(MyApplication.getInstance().getApplicationContext())
                .load(url)
                .error(R.mipmap.ic_icon_failure)
                .thumbnail(0.2f)
                .into(imageView);
    }

    public void load(String url, ImageView imageView, String s) {
        url = String.format("%s%s%s", Constants.BASE_URL, "xjd/", url);
        Log.d("ImageLoader", url);
        Glide.with(MyApplication.getInstance().getApplicationContext())
                .load(url)
                .error(R.mipmap.ic_icon_failure)
                .thumbnail(0.2f)
                .into(imageView);
    }

    public void load(String url, final ImageView imageView, boolean skipMemoryCache) {
        Glide.with(MyApplication.getInstance().getApplicationContext())
                .load(url)
                .error(R.mipmap.ic_icon_failure)
                .thumbnail(0.2f)
                .skipMemoryCache(skipMemoryCache)
                .into(imageView);
    }
}