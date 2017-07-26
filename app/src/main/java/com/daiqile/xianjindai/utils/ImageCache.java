package com.daiqile.xianjindai.utils;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * @author Mr.Bean
 * @date Created on 2016/12/29.
 */
public class ImageCache {
    public LruCache<String, Bitmap> cache;

    int maxMemory = (int) Runtime.getRuntime().maxMemory();
    int cacheSize = maxMemory / 16;

    public ImageCache(){
        if(cache == null){
            cache = new LruCache<String, Bitmap>(cacheSize){
                @Override
                protected int sizeOf(String key, Bitmap value)
                {
                    return value.getRowBytes() * value.getHeight();
                }
            };
        }

    }

    public LruCache<String, Bitmap> getCache(){
        return cache;
    }

    public Bitmap getBitmap(String url) {
        return cache.get(url);
    }

    public void putBitmap(String url, Bitmap bitmap) {
        cache.put(url, bitmap);
    }
}
