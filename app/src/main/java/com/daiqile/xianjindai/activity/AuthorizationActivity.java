package com.daiqile.xianjindai.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;

import com.bqs.crawler.cloud.sdk.MnoAction;
import com.bqs.crawler.cloud.sdk.OnSDKInitListener;
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
import suangrenduobao.daiqile.com.mvlib.utils.ToastUtils;

public class AuthorizationActivity extends BaseActivity {

    @BindView(R.id.topbar)
    TopBar topBar;

    @BindView(R.id.et_name)
    ClearEditText etName;
    @BindView(R.id.et_carid)
    ClearEditText etCarid;
    @BindView(R.id.et_phone)
    ClearEditText etPhone;

    @BindView(R.id.btn_authorization)
    Button btnAuthorization;

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

    @Override
    protected void loadData() {

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

        Log.d("AuthorizationActivity", mName + " " + mCarId + " " + mPhone);

        PermissionUtils.initialize(mName, mCarId, mPhone, new OnSDKInitListener() {
            @Override
            public void onSuccess() {
                startActivity(new Intent(mActivity, PhoneServertActivity.class));
            }

            @Override
            public void onFailure(String s, String s1) {
                ToastUtils.showMessage(s1);
            }
        });
    }

}
