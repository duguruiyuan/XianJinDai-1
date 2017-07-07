package com.daiqile.xianjindai.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.daiqile.xianjindai.R;
import com.daiqile.xianjindai.base.BaseActivity;
import com.daiqile.xianjindai.view.TopBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 身份认证页面
 */
public class IdentityCardActivity extends BaseActivity {


    @BindView(R.id.topbar)//topbar
    TopBar topbar;
    @BindView(R.id.ll_front_card)//身份证正面
    LinearLayout llFrontCard;
    @BindView(R.id.ll_contrary_card)//身份证反面
    LinearLayout llContraryCard;
    @BindView(R.id.ll_face_photo)//拍摄人脸
    LinearLayout llFacePhoto;
    @BindView(R.id.btn_card)//提交按钮
    Button btnCard;
    @BindView(R.id.activity_identity_card)
    LinearLayout activityIdentityCard;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    private Activity mActivity;


    @Override
    public void init() {
        mActivity = IdentityCardActivity.this;
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
        return R.layout.activity_identity_card;
    }

    @Override
    public Activity bindActivity() {
        return this;
    }



    @OnClick({R.id.topbar, R.id.ll_front_card, R.id.ll_contrary_card, R.id.ll_face_photo, R.id.btn_card})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_front_card://正面
                break;
            case R.id.ll_contrary_card://反面
                break;
            case R.id.ll_face_photo://拍摄人脸
                break;
            case R.id.btn_card://提交按钮
                break;
        }
    }
}
