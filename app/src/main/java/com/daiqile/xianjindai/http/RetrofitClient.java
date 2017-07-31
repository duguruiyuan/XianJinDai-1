package com.daiqile.xianjindai.http;

import android.util.Log;

import com.daiqile.xianjindai.Constants;
import com.daiqile.xianjindai.MyApplication;

import com.daiqile.xianjindai.UserPrefs;
import com.daiqile.xianjindai.model.User;
import com.orhanobut.logger.Logger;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import okhttp3.Response;
import suangrenduobao.daiqile.com.mvlib.utils.GsonUtil;
import suangrenduobao.daiqile.com.mvlib.utils.SPUtils;


public class RetrofitClient {

    public static Retrofit mInstance = null;
//    private static ApiService apiService;

    public static Retrofit getInstance() {
        if (mInstance == null) {
            synchronized (RetrofitClient.class) {
                if (mInstance == null) {
                    mInstance = new Retrofit.Builder()
                            .client(genericClient())
                            .baseUrl(Constants.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .build();
                }
            }
        }
        return mInstance;
    }

    public static OkHttpClient genericClient() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader(Constants.TOKEN, MyApplication.getInstance().getToken())
                                .build();
                        Log.d("RetrofitClient", MyApplication.getInstance().getToken());
                        try {
                            String string = chain.proceed(request).body().string();
                            JSONObject jsonObject = new JSONObject(string);
                            Log.d("RetrofitClient", string);
                            Logger.json(string);
                            if (MyApplication.getInstance().isLogin() && jsonObject.has("success") && jsonObject.has("msg")) {
                                //15726818334 123456
//                                !jsonObject.getBoolean("success"))
                                if (jsonObject.getString("msg").contains("token") || jsonObject.getString("msg").contains("0000") &&
                                        jsonObject.getBoolean("success")||!jsonObject.getBoolean("success")) {
                                    OkHttpUtils.post()//
                                            .url(Constants.BASE_URL + "xjd/front/user/login")
                                            .addParams("phone", SPUtils.get(MyApplication.getInstance()
                                                    .getApplicationContext(), Constants.PHONE, "").toString())
                                            .addParams("password", SPUtils.get(MyApplication.getInstance()
                                                    .getApplicationContext(), Constants.LOGINPASSWORD, "").toString())
                                            .tag("Tag")
                                            .build()//
                                            .execute(new StringCallback() {
                                                @Override
                                                public void onError(Call call, Exception e, int id) {
                                                    Log.e("RetrofitClient", "登录失败404" + e.toString());
                                                }

                                                @Override
                                                public void onResponse(String response, int id) {
                                                    Log.d("RetrofitClient", response);
                                                    User user = GsonUtil.GsonToBean(response, User.class);
                                                    if (user.isSuccess()) {
                                                        UserPrefs.getInstance().setToken(user.getToken());
                                                        UserPrefs.getInstance().setUid(user.getUid());
                                                        MyApplication.getInstance().initLoginParams(user);
                                                        Log.d("RetrofitClient", "自动登录成功");
//                                                        httpClient.followRedirects();
                                                    }
                                                }
                                            });
                                } else if (jsonObject.getString("msg").contains("手机号或密码错误") && !jsonObject.has("success")) {
                                    Log.d("RetrofitClient", "手机号或密码错误");
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        return chain.proceed(request);
                    }
                })
                .build();
        return httpClient;
    }

//    public static void getData(String url, Map<String, String> map, Subscriber<ResponseBody> subscriber) {
//        apiService = RetrofitClient.getInstance().create(ApiService.class);
//        apiService.executePost(url, map)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
}
