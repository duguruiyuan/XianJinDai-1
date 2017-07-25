package com.daiqile.xianjindai.activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.daiqile.xianjindai.R;
import com.daiqile.xianjindai.view.TopBar;

import butterknife.BindView;
import butterknife.OnClick;
import suangrenduobao.daiqile.com.mvlib.mv.BaseActivity;

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
                //手机
                intent.setClass(mActivity, AuthorizationActivity.class);
                break;
            case R.id.ll_sesame_credit_score_certification:
                intent.setClass(mActivity, AuthorizationActivity.class);
                //芝麻
                break;
            case R.id.ll_bank_water_certification:
                intent.setClass(mActivity, AuthorizationActivity.class);
                //银行
                break;
        }
        startActivity(intent);
    }


    @Override
    protected void loadData() {

    }
}
