package com.daiqile.xianjindai.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;

import com.daiqile.xianjindai.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import okhttp3.Call;
import suangrenduobao.daiqile.com.mvlib.utils.ToastUtils;

/**
 * @author Mr.Bean
 * @date Created on 2016/7/6.
 */
public class ImageUtils {

    private static String TAG = "ll_yh_ImageUtils";

    private static ImageUtils imageUtils;
    private android.util.LruCache<String, Bitmap> mMemoryCache;
    private LruCache<String, String> hashMap;

    private ImageUtils() {
    }

    /**
     * 采用的是单例
     */
    public static ImageUtils getInstance() {
        if (imageUtils == null) {
            imageUtils = new ImageUtils();
        }

        return imageUtils;
    }

    /**
     * 获取缓存的对象
     */
    public android.util.LruCache<String, Bitmap> getmMemoryCache() {

        if (mMemoryCache == null) {
//            int maxMemory = (int) Runtime.getRuntime().maxMemory();
//            int mCacheSize = maxMemory / 16;
//            //给LruCache分配1/8 4M
//            mMemoryCache = new LruCache<String, Bitmap>(mCacheSize) {
//                //必须重写此方法，来测量Bitmap的大小
//                @Override
//                protected int sizeOf(String key, Bitmap value) {
//                    return value.getRowBytes() * value.getHeight();
//                }
//            };

            mMemoryCache = new ImageCache().getCache();
        }

        return mMemoryCache;

    }

    public LruCache<String, String> GetKeyHashMap() {
        if (hashMap == null) {
            hashMap = new LruCache<String, String>(1024 * 1024);
        }
        return hashMap;
    }

    /**
     * 获取ImageView视图的同时加载显示url  得到一个Imageview对象
     */
//    public static ImageView getImageView(final Context context, String tag, final String url) {
//        final ImageView imageView = (ImageView) LayoutInflater.from(context).inflate(
//                R.layout.view_banner, null);
//        if (url.contains("http")) {
//            if (getInstance().getmMemoryCache().get(url) != null) {
//                imageView.setImageBitmap(getInstance().getmMemoryCache().get(url));
//            } else {
//                imageView.setImageResource(R.drawable.dowmload_image);
//                OkHttpUtils.get().url(url).tag(tag).build().execute(new BitmapCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//                        ToastUtils.showMessage(url + " 图片下载失败");
//                        imageView.setImageResource(R.drawable.download_image_fail);
//                    }
//
//                    @Override
//                    public void onResponse(Bitmap response, int id) {
//                        if (response != null) {
//                            getInstance().getmMemoryCache().put(url, response);
//                            imageView.setImageBitmap(response);
//                        } else {
//                            ToastUtils.showMessage(url + " 图片为空");
//                            imageView.setImageResource(R.drawable.download_image_fail);
//                        }
//                    }
//                });
//            }
//        } else {
//            imageView.setImageResource(R.drawable.download_image_fail);
//            ToastUtils.showMessage("url格式不对");
//        }
//
//        return imageView;
//    }

    /**
     * 下载图片 传入ImageView对象
     */
//    public void getImage(final ImageView imageView, String tag, final String url) {
//        if (url.contains("http")) {
//            if (getInstance().getmMemoryCache().get(url) != null) {
//                if (imageView != null) {
//                    imageView.setImageBitmap(getInstance().getmMemoryCache().get(url));
//                }
//            } else {
//                if (imageView != null) {
//                    imageView.setImageResource(R.drawable.dowmload_image);
//                }
//                OkHttpUtils.get().url(url).tag(tag).build().execute(new BitmapCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//                        ToastUtils.showMessage(url + " 图片下载失败");
//                        if (imageView != null) {
//                            imageView.setImageResource(R.drawable.download_image_fail);
//                        }
//                    }
//
//                    @Override
//                    public void onResponse(Bitmap response, int id) {
//                        if (response != null) {
//                            getInstance().getmMemoryCache().put(url, response);
//                            if (imageView != null) {
//                                imageView.setImageBitmap(response);
//                            }
//                        } else {
//                            ToastUtils.showMessage(url + " 图片为空");
//                            if (imageView != null) {
//                                imageView.setImageResource(R.drawable.download_image_fail);
//                            }
//                        }
//                    }
//                });
//            }
//        } else {
//            if (imageView != null) {
//                imageView.setImageResource(R.drawable.download_image_fail);
//            }
//            ToastUtils.showMessage("url格式不对");
//        }
//    }

    /**
     * 下载图片 通过回调返回图片
     */
    public void getImage(String tag, final String url, final LoadImageCallBack loadImageCallBack) {
        if (url.contains("http")) {
            if (getInstance().getmMemoryCache().get(url) != null) {
                if (loadImageCallBack != null) {
                    loadImageCallBack.loadSuccess(getInstance().getmMemoryCache().get(url));
                }
            } else {
                OkHttpUtils.get().url(url).tag(tag).build().execute(new BitmapCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtils.showMessage(url + " 图片下载失败");
                        if (loadImageCallBack != null) {
                            loadImageCallBack.loadFail("图片下载失败");
                        }
                    }

                    @Override
                    public void onResponse(Bitmap response, int id) {
                        if (response != null) {
                            getInstance().getmMemoryCache().put(url, response);
                            if (loadImageCallBack != null) {
                                loadImageCallBack.loadSuccess(response);
                            }
                        } else {
                            ToastUtils.showMessage(url + " 图片为空");
                            if (loadImageCallBack != null) {
                                loadImageCallBack.loadFail("图片为空");
                            }
                        }
                    }
                });
            }
        } else {
            if (loadImageCallBack != null) {
                loadImageCallBack.loadFail("url格式不对");
            }
            ToastUtils.showMessage("url格式不对");
        }
    }

    public interface LoadImageCallBack {
        void loadSuccess(Bitmap bitmap);//下载图片成功的回调

        void loadFail(String msg);//下载图片失败的回调
    }

    /**
     * 用于下载网络图片 并保存到本地
     */
    public void getImageByUrl(final String url, final ImageView imageView, final String fileName) {
        if (!TextUtils.isEmpty(url)) {
            if (getInstance().getmMemoryCache().get(url) != null) {
                Log.e("ll_yh", "图片" + url + "在缓存中有保存");
                if (imageView != null) {
                    imageView.setImageBitmap(getmMemoryCache().get(url));
                }
                if (getInstance().GetKeyHashMap().get(url) == null) {
                    Log.e("ll_yh", "图片在缓存中有保存，但是没有保存到本地" + url);
                    File f = new File(fileName);
                    if (f.exists()) {
                        f.delete();
                    }
                    try {
                        FileOutputStream out = new FileOutputStream(f);
                        getInstance().getmMemoryCache().get(url).compress(Bitmap.CompressFormat.PNG, 100, out);
                        out.flush();
                        out.close();
                        Log.e(TAG, "已经保存" + url);
                        getInstance().GetKeyHashMap().put(url, fileName);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(TAG, "保存失败" + url);
                    }
                }
            } else {
                Log.e("ll_yh", "图片" + url + "在缓存中没有保存");
                if (url.contains("http")) {
                    if (imageView != null) {
//                        imageView.setImageResource(R.drawable.dowmload_image);
                    }
                    OkHttpUtils.get().url(url).tag("load").build().execute(new BitmapCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            ToastUtils.showMessage(url + " 图片下载失败");
                            if (imageView != null) {
//                                imageView.setImageResource(R.drawable.download_image_fail);
                            }
                        }

                        @Override
                        public void onResponse(Bitmap response, int id) {
                            if (response != null) {
                                getInstance().getmMemoryCache().put(url, response);
                                if (imageView != null) {
                                    imageView.setImageBitmap(response);
                                }
                                getInstance().GetKeyHashMap().remove(url);
                                File f = new File(fileName);
                                if (f.exists()) {
                                    f.delete();
                                }
                                try {
                                    FileOutputStream out = new FileOutputStream(f);
                                    response.compress(Bitmap.CompressFormat.PNG, 100, out);
                                    out.flush();
                                    out.close();
                                    Log.e(TAG, "已经保存" + url);
                                    getInstance().GetKeyHashMap().put(url, fileName);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Log.e(TAG, "保存失败" + url);
                                }
                            } else {
                                ToastUtils.showMessage(url + " 图片为空");
                                if (imageView != null) {
//                                    imageView.setImageResource(R.drawable.download_image_fail);
                                }
                            }
                        }
                    });
                } else {
                    Log.e("ll_yh", "图片在缓存中没有保存，并且是本地文件");
                    try {
                        FileInputStream fis = new FileInputStream(url);
                        Bitmap bitmap = BitmapFactory.decodeStream(fis);
                        getInstance().getmMemoryCache().put(url, bitmap);
                        if (imageView != null) {
                            imageView.setImageBitmap(bitmap);
                        }
                        getInstance().GetKeyHashMap().remove(url);
                        File f = new File(fileName);
                        if (f.exists()) {
                            f.delete();
                        }
                        FileOutputStream out = new FileOutputStream(f);
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                        out.flush();
                        out.close();
                        Log.e(TAG, "已经保存");
                        getInstance().GetKeyHashMap().put(url, fileName);
                    } catch (Exception e) {
                        e.printStackTrace();
                        ToastUtils.showMessage(url + " 图片为空");
                        if (imageView != null) {
//                            imageView.setImageResource(R.drawable.download_image_fail);
                        }
                    }
                }
            }
        }
    }
}
