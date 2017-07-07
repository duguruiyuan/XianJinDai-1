package com.daiqile.xianjindai.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.daiqile.xianjindai.R;
import com.daiqile.xianjindai.base.BaseActivity;
import com.daiqile.xianjindai.view.TopBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BorrowConfirmActivity extends BaseActivity {

    @BindView(R.id.topbar)//topbar
    TopBar topbar;
    @BindView(R.id.tv_account_money)//到账金额
    TextView tvAccountMoney;
    @BindView(R.id.tv_due_money)//到期还款
    TextView tvDueMoney;
    @BindView(R.id.tv_borrow_day)//借款日
    TextView tvBorrowDay;
    @BindView(R.id.tv_due_day)//还款日
    TextView tvDueDay;
    @BindView(R.id.tv_choose_bank)//选择已经绑定的银行
    TextView tvChooseBank;
    @BindView(R.id.tv_agreement)//银行卡号
    TextView tvAgreement;
    @BindView(R.id.btn_borrow_comfirm)//借款按钮
    Button btnBorrowComfirm;
    private Activity mActivity;


    @Override
    public void init() {
        mActivity = BorrowConfirmActivity.this;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_borrow_confirm;
    }

    @Override
    public Activity bindActivity() {
        return this;
    }

   
}
