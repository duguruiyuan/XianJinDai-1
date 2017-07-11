package com.daiqile.xianjindai.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.daiqile.xianjindai.Constants;
import com.daiqile.xianjindai.MyApplication;
import com.daiqile.xianjindai.R;
import com.daiqile.xianjindai.UserPrefs;
import com.daiqile.xianjindai.model.MyInfo;
import com.daiqile.xianjindai.model.User;
import com.daiqile.xianjindai.utils.ToastUtil;
import com.daiqile.xianjindai.view.ClearEditText;
import com.daiqile.xianjindai.view.TopBar;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import suangrenduobao.daiqile.com.mvlib.mv.BaseActivity;
import suangrenduobao.daiqile.com.mvlib.utils.GsonUtil;
import suangrenduobao.daiqile.com.mvlib.utils.SPUtils;


/**
 * 登录页面
 */
public class LoginActivity extends BaseActivity {
    @BindView(R.id.et_phone)
    ClearEditText etPhone;
    @BindView(R.id.et_password)
    ClearEditText etPassword;
    @BindView(R.id.topbar)
    TopBar topbar;

    private static String Tag = "LoginActivity";

    @Override
    protected boolean switchToolbar() {
        return false;
    }

    @Override
    protected void initConfig() {
        super.initConfig();
        topbar.setRightButton(true);
        topbar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {
                startActivity(new Intent(mContext, RegistAvtivity.class));
            }
        });
    }

    private void login() {
        final String phone = etPhone.getText().toString().trim();
        final String password = etPassword.getText().toString().trim();
        if (phone.isEmpty()) {
            ToastUtil.showToast(mContext, "请输入手机号");
            return;
        }
        if (password.isEmpty()) {
            ToastUtil.showToast(mContext, "请输入密码");
            return;
        }
        if (phone.toString().length() < 11 || phone.toString().length() > 11) {
            ToastUtil.showToast(mContext, "请输入正确的手机号");
            return;
        }

        Log.d(TAG, Constants.BASE_URL + "xjd/front/user/login");
        OkHttpUtils
                .post()//
                .url(Constants.BASE_URL + "xjd/front/user/login")//
                .addParams("phone", phone)
                .addParams("password", password)
                .tag(Tag)
                .build()//
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("ll_yh", "登录失败404" + e.toString());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("ll_yh", "注册结果： " + response);
                        User user = GsonUtil.GsonToBean(response, User.class);
                        ToastUtil.showToast(mContext, user.getMsg());
                        if (user.isSuccess()) {
                            UserPrefs.getInstance().setToken(user.getToken());
                            UserPrefs.getInstance().setUid(user.getUid());
                            UserPrefs.getInstance().setPhone(etPhone.getText().toString());
                            UserPrefs.getInstance().setPassword(etPassword.getText().toString());

                            MyApplication.getInstance().initLoginParams(user);

                            MyApplication.getInstance().setPhone(phone);
                            MyApplication.getInstance().setLoginPassword(password);

                            finish();
                        }
                    }
                });
    }

    private static final String TAG = "LoginActivity";

    @OnClick({R.id.tv_forget, R.id.btn_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_forget:
                startActivity(new Intent(this, FindPwdByPhoneActivity.class));
                break;
            case R.id.btn_login:
                login();
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        OkHttpUtils.getInstance().cancelTag(Tag);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkHttpUtils.getInstance().cancelTag(Tag);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void loadData() {

    }

}
