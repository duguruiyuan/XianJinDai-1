package com.daiqile.xianjindai.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.bqs.crawler.cloud.sdk.BqsParams;
import com.bqs.crawler.cloud.sdk.LoginWebView;
import com.bqs.crawler.cloud.sdk.MnoAction;
import com.bqs.crawler.cloud.sdk.OnSDKInitListener;
import com.bqs.crawler.cloud.sdk.OnWebLoginSuccessListener;
import com.bqs.crawler.cloud.sdk.SDKInitialize;
import com.bqs.crawler.cloud.sdk.ServiceId;
import com.daiqile.xianjindai.Constants;
import com.daiqile.xianjindai.MyApplication;
import com.daiqile.xianjindai.PhoneServertActivity;
import com.daiqile.xianjindai.R;
import com.daiqile.xianjindai.view.ClearEditText;
import com.daiqile.xianjindai.view.TopBar;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.OnClick;
import haixianwang.daiqile.com.baiqishi.PermissionUtils;
import okhttp3.Call;
import suangrenduobao.daiqile.com.mvlib.mv.BaseActivity;
import suangrenduobao.daiqile.com.mvlib.utils.RegexValidateUtil;
import suangrenduobao.daiqile.com.mvlib.utils.SPUtils;
import suangrenduobao.daiqile.com.mvlib.utils.ToastUtils;

import static com.yintong.secure.e.m.j.C;

public class AuthorizationActivity extends BaseActivity {

    @BindView(R.id.topbar)
    TopBar topBar;

    @BindView(R.id.myll)
    LinearLayout linearLayout;

    @BindView(R.id.et_name)
    ClearEditText etName;
    @BindView(R.id.et_carid)
    ClearEditText etCarid;
    @BindView(R.id.et_phone)
    ClearEditText etPhone;

    @BindView(R.id.btn_authorization)
    Button btnAuthorization;

    @BindView(R.id.login_webview)
    LoginWebView loginWebview;

    @Override
    protected boolean switchToolbar() {
        return false;
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_authorization;
    }

    @Override
    protected void initConfig() {
        super.initConfig();
        topBar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {

            }
        });
    }

    int id;

    @Override
    protected void loadData() {
        if (getIntent().hasExtra(Constants.BAIQISHISTATUS)) {
            id = getIntent().getIntExtra(Constants.BAIQISHISTATUS, -1);
        }
        if (id == ServiceId.MNO_SERVICE_ID) {
            //手机
            topBar.setTitle("手机运营商授权");
            loginWebview.setVisibility(View.GONE);
            linearLayout.setVisibility(View.VISIBLE);
        } else {
            topBar.setTitle("芝麻信用分授权");
            loginWebview.setVisibility(View.GONE);
//            linearLayout.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.btn_authorization)
    public void onClick() {
        String mName = etName.getText().toString().trim();
        String mCarId = etCarid.getText().toString().trim();
        String mPhone = etPhone.getText().toString().trim();

        if (TextUtils.isEmpty(mName)) {
            ToastUtils.showMessage("请输入姓名");
            return;
        }

        if (TextUtils.isEmpty(mCarId)) {
            ToastUtils.showMessage("请输入身份证");
            return;
        }

        if (!RegexValidateUtil.checkIDCard(mCarId)) {
            ToastUtils.showMessage("请输入正确身份证");
            return;
        }
        if (TextUtils.isEmpty(mPhone)) {
            ToastUtils.showMessage("请输入手机号");
            return;
        }

        if (!RegexValidateUtil.checkCellphone(mPhone)) {
            ToastUtils.showMessage("请输入正确手机号");
            return;
        }
        if (id == ServiceId.MNO_SERVICE_ID) {
            //手机
            SPUtils.put(MyApplication.getInstance().getApplicationContext(), Constants.PHONE_BAIQISHI, "");
            SPUtils.put(MyApplication.getInstance().getApplicationContext(), MyApplication.getInstance().getUid() + "phone_name", mName);
            SPUtils.put(MyApplication.getInstance().getApplicationContext(), MyApplication.getInstance().getUid() + "phone_card", mCarId);
            SPUtils.put(MyApplication.getInstance().getApplicationContext(), MyApplication.getInstance().getUid() + "phone_phone", mPhone);
        } else if (id == ServiceId.ALIPAY_SERVICE_ID) {
            //芝麻
            SPUtils.put(MyApplication.getInstance().getApplicationContext(), Constants.ZHIMAXINYONG_BAIQISHI, "");
            SPUtils.put(MyApplication.getInstance().getApplicationContext(), MyApplication.getInstance().getUid() + "taobao_name", mName);
            SPUtils.put(MyApplication.getInstance().getApplicationContext(), MyApplication.getInstance().getUid() + "taobao_card", mCarId);
            SPUtils.put(MyApplication.getInstance().getApplicationContext(), MyApplication.getInstance().getUid() + "taobao_phone", mPhone);
        }

        Log.d("AuthorizationActivity", mName + " " + mCarId + " " + mPhone);

        PermissionUtils.initialize(mName, mCarId, mPhone, new OnSDKInitListener() {
            @Override
            public void onSuccess() {

                if (id == ServiceId.MNO_SERVICE_ID) {
                    startActivity(new Intent(mActivity, PhoneServertActivity.class));
                } else {
                    loginWebview.setVisibility(View.VISIBLE);
                    loginWebview.setServiceId(id);
                    loginWebview.setOnWebLoginSuccessListener(new OnWebLoginSuccessListener() {
                        @Override
                        public void onLoginSuccess(int i) {
                            Log.d("AuthorizationActivity", "i:" + i);
                        }

                        @Override
                        public void onLoginFailure(int i, String s, String s1) {

                        }

                        @Override
                        public void onLoadUrlFailure(int i, String s, String s1) {

                        }

                        @Override
                        public void onLoadUrlProgress(int i) {

                        }

                        @Override
                        public void onLoadUrlStart() {

                        }

                        @Override
                        public void onLoadUrlFinish() {

                        }
                    });
                }
            }

            @Override
            public void onFailure(String s, String s1) {
                ToastUtils.showMessage(s1);
            }
        });
    }

}
