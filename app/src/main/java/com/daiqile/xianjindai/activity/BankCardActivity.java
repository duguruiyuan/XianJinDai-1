package com.daiqile.xianjindai.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.daiqile.xianjindai.R;
import com.daiqile.xianjindai.base.BaseActivity;
import com.daiqile.xianjindai.view.TopBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BankCardActivity extends BaseActivity {

    @BindView(R.id.topbar)
    TopBar topbar;
    @BindView(R.id.iv_bind)
    ImageView ivBind;
    private Activity mActivity;

    @Override
    public void init() {
        mActivity = BankCardActivity.this;
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

    @OnClick(R.id.iv_bind)
    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_bind :
                startActivity(new Intent(mActivity,BindCardActivity.class));
                break;
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_bank_card;
    }

    @Override
    public Activity bindActivity() {
        return this;
    }

}
