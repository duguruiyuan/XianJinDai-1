package com.daiqile.xianjindai;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.bqs.crawler.cloud.sdk.OnMnoLoginListener;
import com.daiqile.xianjindai.activity.ThirdPartCertificationActivity;
import com.daiqile.xianjindai.view.ClearEditText;
import com.daiqile.xianjindai.view.TopBar;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import butterknife.BindView;
import butterknife.OnClick;
import haixianwang.daiqile.com.baiqishi.PermissionUtils;
import okhttp3.Call;
import suangrenduobao.daiqile.com.mvlib.mv.BaseActivity;
import suangrenduobao.daiqile.com.mvlib.utils.GsonUtil;
import suangrenduobao.daiqile.com.mvlib.utils.ToastUtils;

public class PhoneServertActivity extends BaseActivity {

    @BindView(R.id.topbar)
    TopBar topBar;
    @BindView(R.id.et_servert_code)
    ClearEditText etServertCode;

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
//        new Thread() {
//            @Override
//            public void run() {
//                super.run();
//                try {
//                    String str = URLEncoder.encode(GsonUtil.GsonString(new UserInfo("ebc860eb9d28438b9e37ff579405591e", "daiqile",
//                            "郭枫", "362428199301052713", "15949629529")), "UTF-8");
//                    Log.d("MainActivity", MainActivity.sendPost("https://credit.baiqishi.com/clweb/api/mno/getoriginal", str));
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }.start();
    }

    @Override
    protected boolean switchToolbar() {
        return false;
    }

    class UserInfo {
        String verifyKey;
        String partnerId;
        String name;
        String certNo;
        String mobile;

        public UserInfo(String verifyKey, String partnerId, String name, String certNo, String mobile) {
            this.verifyKey = verifyKey;
            this.partnerId = partnerId;
            this.name = name;
            this.certNo = certNo;
            this.mobile = mobile;
        }

        public UserInfo() {
        }
    }


    @Override
    protected int initLayout() {
        return R.layout.activity_phone_servert;
    }

    @OnClick(R.id.btn_query)
    public void onClick() {
        String mEtServertCode = etServertCode.getText().toString().trim();
        if (TextUtils.isEmpty(mEtServertCode)) {
            ToastUtils.showMessage("请输入服务号");
            return;
        }

        PermissionUtils.service(mEtServertCode, new OnMnoLoginListener() {
            @Override
            public void onInputAuthSmsCode() {

            }

            @Override
            public void onInputLoginSmsCode() {

            }

            @Override
            public void onLoginSuccess() {
                //验证成功
                startActivity(new Intent(mActivity, ThirdPartCertificationActivity.class));
                finish();
            }

            @Override
            public void onLoginFailure(String s, String s1) {
                ToastUtils.showMessage(s1);
            }
        });

    }

    @Override
    protected void loadData() {

    }
}
