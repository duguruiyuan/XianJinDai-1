package com.daiqile.xianjindai.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.daiqile.xianjindai.R;
import com.daiqile.xianjindai.base.BaseActivity;
import com.daiqile.xianjindai.view.TopBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
//关联贷款页面
public class LoanActivity extends BaseActivity {
    @BindView(R.id.topbar)
    TopBar topbar;
    @BindView(R.id.rl_legal_person)
    RelativeLayout rlLegalPerson;
    @BindView(R.id.rl_house)
    RelativeLayout rlHouse;
    private Activity mActivity;

    @Override
    public void init() {
        topbar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
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
        return R.layout.activity_loan;
    }

    @Override
    public Activity bindActivity() {
        return this;
    }


    @OnClick({R.id.rl_legal_person, R.id.rl_house})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_legal_person:
                break;
            case R.id.rl_house:
                break;
        }
    }
}
