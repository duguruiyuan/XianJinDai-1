package com.daiqile.xianjindai;

import android.os.Build;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;

import com.daiqile.xianjindai.http.ApiService;
import com.daiqile.xianjindai.http.RetrofitClient;

import com.daiqile.xianjindai.model.User;
import com.weavey.loading.lib.LoadingLayout;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.cookie.store.PersistentCookieStore;
import com.zhy.http.okhttp.https.HttpsUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import suangrenduobao.daiqile.com.mvlib.mv.BaseApp;
import suangrenduobao.daiqile.com.mvlib.utils.GsonUtil;
import suangrenduobao.daiqile.com.mvlib.utils.SPUtils;

/*
 * Created by G150T on 2017/6/21.
 */

public class MyApplication extends BaseApp {
    public ApiService apiService;

    private static MyApplication mInstance;

    public static MyApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {

        super.onCreate();
        mInstance = this;
        SPUtils.init(Constants.XIANJINDAI);
        initLoginParams(null);
        initOkHttp();

        Retrofit retrofit = RetrofitClient.getInstance();
        apiService = retrofit.create(ApiService.class);
        UserPrefs.init(getApplicationContext());

        LoadingLayout.getConfig()
//                .setLoadingPageLayout(R.layout.pager_loading)
                .setErrorText("主人,出错啦~请稍后重试！")
                .setEmptyText("主人，暂无数据")
                .setNoNetworkText("主人，网络报错了，刷新试试")
                .setErrorImage(R.mipmap.ic_launcher)
                .setEmptyImage(R.mipmap.ic_launcher)
                .setNoNetworkImage(R.drawable.ic_icon_define_nonetwork)
                .setAllTipTextColor(R.color.cccccc)
                .setAllTipTextSize(14)
                .setReloadButtonText("点我重试哦")
                .setReloadButtonTextSize(14)
                .setReloadButtonTextColor(R.color.cccccc)
                .setReloadButtonWidthAndHeight(150, 40);

        if (Build.VERSION.SDK_INT > 21) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
            builder.detectFileUriExposure();
        }
        Log.d("MyApplication", uid);
    }

    public void initLoginParams(User user) {
        if (null == user) {
            String sUser = SPUtils.get(MyApplication.getInstance().getApplicationContext(), Constants.USER, "").toString();
            if (!TextUtils.isEmpty(sUser)) {
                user = GsonUtil.GsonToBean(sUser, User.class);
                uid = user.getUid();
                token = user.getToken();
//                phone = user.getPhone();
//                loginPassword = user.getLoginPassword();
                flag = true;
            } else {
                uid = "";
                token = "";
                flag = false;
//                phone = "";
//                loginPassword = "";
            }
        } else {
            SPUtils.put(MyApplication.getInstance().getApplicationContext(), Constants.USER, GsonUtil.GsonString(user));
            uid = user.getUid();
            flag = true;
            token = user.getToken();
//            phone = user.getPhone();
//            loginPassword = user.getLoginPassword();
        }
//        Log.d("MyApplication", uid + flag);
//        Log.d("MyApplication", token);
//        Log.d("MyApplication", phone + " " + loginPassword);
    }

    public void setPhone(String phone) {
        this.phone = phone;
        SPUtils.put(getApplicationContext(), Constants.PHONE, phone);
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
        SPUtils.put(getApplicationContext(), Constants.LOGINPASSWORD, loginPassword);
    }

    public String getPhone() {
        if (TextUtils.isEmpty(phone)) {
            phone = SPUtils.get(getApplicationContext(), Constants.PHONE, "").toString();
        }
        return phone;
    }

    public String getLoginPassword() {
        if (TextUtils.isEmpty(loginPassword)) {
            loginPassword = SPUtils.get(getApplicationContext(), Constants.LOGINPASSWORD, "").toString();
        }
        return loginPassword;
    }

    private String uid;
    private String token;
    private String phone, loginPassword;
    private boolean flag = false;

    /**
     * 更新登陆信息
     */
    public void updateLoginParams() {
    }

    /**
     * 清空登录信息
     */
    public void clearLoginParams() {
        SPUtils.clear(MyApplication.getInstance().getApplicationContext());
        uid = "";
        flag = false;
        token = "";
        phone = "";
        loginPassword = "";
    }

    public String getUid() {
        Log.d("MyApplication", uid);
        return uid;
    }

    public String getToken() {
        return token;
    }

    public boolean isLogin() {
        return flag;
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

    @Override
    public void initConfig() {

    }
}
