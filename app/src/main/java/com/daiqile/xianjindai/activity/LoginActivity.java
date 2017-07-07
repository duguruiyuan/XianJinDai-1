package com.daiqile.xianjindai.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daiqile.xianjindai.Constants;
import com.daiqile.xianjindai.MyApplication;
import com.daiqile.xianjindai.R;
import com.daiqile.xianjindai.UserPrefs;
import com.daiqile.xianjindai.base.BaseActivity;
import com.daiqile.xianjindai.model.MyInfo;
import com.daiqile.xianjindai.model.User;
import com.daiqile.xianjindai.utils.SPUtils;
import com.daiqile.xianjindai.utils.ToastUtil;
import com.daiqile.xianjindai.view.TopBar;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 登录页面
 */
public class LoginActivity extends BaseActivity {
    @BindView(R.id.topbar)//topbar
            TopBar topbar;
    @BindView(R.id.et_username)//用户名
            EditText etUsername;
    @BindView(R.id.et_password)//登录密码
            EditText etPassword;
    @BindView(R.id.btn_login)//登录按钮
            Button btnLogin;
    @BindView(R.id.tv_forget)//忘记密码按钮
            TextView tvForget;
    @BindView(R.id.activity_login)
    LinearLayout activityLogin;

    private Activity mActivity;
    private static String Tag = "LoginActivity";
    private MyApplication application;
    private Context context;
    private User user;
    private MyInfo.DataBean myInfo;

    @Override
    public void init() {
        mActivity = LoginActivity.this;
        application = (MyApplication) getApplication();
        topbar.setRightButton(true);
        topbar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {
                startActivity(new Intent(mActivity, RegistAvtivity.class));
            }
        });
    }

    private void login() {
        final String phone = etUsername.getText().toString().trim();
        final String password = etPassword.getText().toString().trim();
        if (phone.isEmpty()) {
            ToastUtil.showToast(mActivity, "请输入手机号");
            return;
        }
        if (password.isEmpty()) {
            ToastUtil.showToast(mActivity, "请输入密码");
            return;
        }
        if (phone.toString().length() < 11 || phone.toString().length() > 11) {
            ToastUtil.showToast(mActivity, "请输入正确的手机号");
            return;
        }

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
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String isSuccess = jsonObject.getString("success");
                            String msg = jsonObject.getString("msg");
//                            Log.e("sss", "onResponse: " + isSuccess);
//                            Log.e("sss", "token: " + jsonObject.getString("token"));
                            ToastUtil.showToast(mActivity, msg);
                            if (isSuccess.equals("true")) {
                                UserPrefs.getInstance().setToken(jsonObject.getString("token"));
                                UserPrefs.getInstance().setUid(jsonObject.getString("uid"));
                                UserPrefs.getInstance().setPhone(etUsername.getText().toString());
                                UserPrefs.getInstance().setPassword(etPassword.getText().toString());

                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private static final String TAG = "LoginActivity";

    @OnClick({R.id.tv_forget, R.id.btn_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_forget:
                startActivity(new Intent(mActivity, FindPwdByPhoneActivity.class));
                break;
            case R.id.btn_login:
                login();
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkHttpUtils.getInstance().cancelTag(Tag);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public Activity bindActivity() {
        return this;
    }


}
