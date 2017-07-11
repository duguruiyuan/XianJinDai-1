package com.daiqile.xianjindai.http;

import com.daiqile.xianjindai.Constants;
import com.daiqile.xianjindai.MyApplication;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import okhttp3.Response;


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
