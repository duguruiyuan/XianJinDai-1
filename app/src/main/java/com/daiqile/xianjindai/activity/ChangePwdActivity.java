package com.daiqile.xianjindai.activity;

import android.app.Activity;
import android.content.Intent;
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
 * 修改密码列表
 */
public class ChangePwdActivity extends BaseActivity {

    @BindView(R.id.topbar)//topbar
    TopBar topBar;
    @BindView(R.id.ll_change_pwd)//修改密码
    LinearLayout llChangePwd;
    private Activity mActivity;

    @Override
    public void init() {
        mActivity = ChangePwdActivity.this;
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
        return R.layout.activity_change_pwd;
    }

    @Override
    public Activity bindActivity() {
        return this;
    }

    @OnClick(R.id.ll_change_pwd)
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_change_pwd:
                startActivity(new Intent(mActivity,ChangeLoginPwdActivity.class));//跳转到修改登录密码页面
                break;
        }
    }
}
