package com.daiqile.xianjindai.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.daiqile.xianjindai.R;
import com.daiqile.xianjindai.base.BaseActivity;
import com.daiqile.xianjindai.view.TopBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    private Activity mActivity;

    @Override
    public void init() {
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
    public int getLayoutId() {
        return R.layout.activity_third_part_certification;
    }

    @Override
    public Activity bindActivity() {
        return this;
    }




    @OnClick({R.id.ll_mobile_phone_operator_authentication, R.id.ll_sesame_credit_score_certification, R.id.ll_bank_water_certification})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_mobile_phone_operator_authentication:
                break;
            case R.id.ll_sesame_credit_score_certification:
                break;
            case R.id.ll_bank_water_certification:
                break;
        }
    }
}
