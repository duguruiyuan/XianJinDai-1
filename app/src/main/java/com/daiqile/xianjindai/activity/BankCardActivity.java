package com.daiqile.xianjindai.activity;

import android.content.Intent;

import android.view.View;
import android.widget.ImageView;

import com.daiqile.xianjindai.MyApplication;
import com.daiqile.xianjindai.R;

import com.daiqile.xianjindai.activity.bean.BankInfoList;
import com.daiqile.xianjindai.view.TopBar;


import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import suangrenduobao.daiqile.com.mvlib.mv.BaseActivity;

public class BankCardActivity extends BaseActivity {

    @BindView(R.id.topbar)
    TopBar topbar;
    @BindView(R.id.iv_bind)
    ImageView ivBind;

    @Override
    protected void initConfig() {
        super.initConfig();
        topbar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {
            }
        });

        // TODO: 2017/7/10 添加银行卡成功的刷新
    }


    @Override
    protected int initLayout() {
        return R.layout.activity_bank_card;
    }

    @Override
    protected void loadData() {
        MyApplication.getInstance().apiService.getBankLists(null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<BankInfoList>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(BankInfoList bankInfoList) {
                // TODO: 2017/7/10 银行卡列表
                bankInfoList.getData();
            }
        });
    }

    @Override
    protected boolean switchToolbar() {
        return false;
    }

    @OnClick(R.id.iv_bind)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_bind:
                startActivity(new Intent(mContext, BindCardActivity.class));
                break;
        }
    }


}
