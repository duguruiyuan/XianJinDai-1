package com.daiqile.xianjindai.activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.bqs.crawler.cloud.sdk.ServiceId;
import com.daiqile.xianjindai.Constants;
import com.daiqile.xianjindai.MyApplication;
import com.daiqile.xianjindai.R;
import com.daiqile.xianjindai.view.TopBar;

import butterknife.BindView;
import butterknife.OnClick;
import suangrenduobao.daiqile.com.mvlib.mv.BaseActivity;
import suangrenduobao.daiqile.com.mvlib.utils.SPUtils;
import suangrenduobao.daiqile.com.mvlib.utils.ToastUtils;

/**
 * 三方认证
 */
public class ThirdPartCertificationActivity extends BaseActivity {
    @BindView(R.id.topbar)
    TopBar topBar;
    @BindView(R.id.ll_mobile_phone_operator_authentication)
    LinearLayout llMobilePhoneOperatorAuthentication;
    @BindView(R.id.ll_sesame_credit_score_certification)
    LinearLayout llSesameCreditScoreCertification;
    @BindView(R.id.ll_bank_water_certification)
    LinearLayout llBankWaterCertification;
//    private Activity mActivity;


    @Override
    protected boolean switchToolbar() {
        return false;
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
    public int initLayout() {
        return R.layout.activity_third_part_certification;
    }

    @OnClick({R.id.ll_mobile_phone_operator_authentication, R.id.ll_sesame_credit_score_certification, R.id.ll_bank_water_certification})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.ll_mobile_phone_operator_authentication:
                if (SPUtils.contains(MyApplication.getInstance().getApplicationContext(), Constants.PHONE_BAIQISHI)) {
//                    ToastUtils.showMessage("手机运营商认证成功");
                } else {
                    //手机
                }
                intent.setClass(mActivity, AuthorizationActivity.class);
                intent.putExtra(Constants.BAIQISHISTATUS, ServiceId.MNO_SERVICE_ID);
                break;
            case R.id.ll_sesame_credit_score_certification:
                if (SPUtils.contains(MyApplication.getInstance().getApplicationContext(), Constants.ZHIMAXINYONG_BAIQISHI)) {
//                    ToastUtils.showMessage("芝麻信用分认证成功");
                } else {
                    //芝麻
                }
                intent.setClass(mActivity, AuthorizationActivity.class);
                intent.putExtra(Constants.BAIQISHISTATUS, ServiceId.ALIPAY_SERVICE_ID);
                break;
            case R.id.ll_bank_water_certification:
                intent.setClass(mActivity, BankCertificateActivity.class);
                //银行
                break;
        }
        startActivity(intent);
    }


    @Override
    protected void loadData() {

    }
}
