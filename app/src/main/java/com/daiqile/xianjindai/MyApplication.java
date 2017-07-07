package com.daiqile.xianjindai;

import android.app.Application;
import android.content.SharedPreferences;

import com.daiqile.xianjindai.http.ApiService;
import com.daiqile.xianjindai.http.RetrofitClient;
import com.daiqile.xianjindai.model.MyInfo;
import com.daiqile.xianjindai.model.User;
import com.daiqile.xianjindai.utils.SPUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.cookie.store.PersistentCookieStore;
import com.zhy.http.okhttp.https.HttpsUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/*
 * Created by G150T on 2017/6/21.
 */

public class MyApplication extends Application {
    public User mUser = null;
    public MyInfo.DataBean myInfo = null;

    public boolean mRememberPassword = false;
    public boolean isFirstStart = true;
    public SharedPreferences mSharedPreferences = null;
    public ApiService apiService;

    private static MyApplication mInstance;

    public static MyApplication getInstance() {

        return mInstance;
    }

    @Override
    public void onCreate() {


        super.onCreate();
        mInstance = this;

        SPUtils.init("xianjindai");
        mSharedPreferences = getSharedPreferences(AppConfig.SHARED_PATH, MODE_PRIVATE);
        initLoginParams();
        initOkHttp();
        Retrofit retrofit = RetrofitClient.getInstance();
        apiService = retrofit.create(ApiService.class);
        UserPrefs.init(getApplicationContext());
    }

    public void initLoginParams() {

    }

    /**
     * 更新登陆信息
     */
    public void updateLoginParams(){
    }

    /**
     * 清空登录信息
     */
    public void clearLoginParams() {
        SPUtils.clear(this);
    }

    private void initOkHttp() {
        CookieJarImpl cookieJar = new CookieJarImpl(new PersistentCookieStore(getApplicationContext()));
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .cookieJar(cookieJar)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);

    }
}
