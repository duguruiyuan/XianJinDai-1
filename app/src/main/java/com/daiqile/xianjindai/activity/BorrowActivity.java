package com.daiqile.xianjindai.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.daiqile.xianjindai.R;
import com.daiqile.xianjindai.base.BaseActivity;
import com.daiqile.xianjindai.view.TopBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BorrowActivity extends BaseActivity {
    @BindView(R.id.topbar)//topbar
    TopBar topBar;
    @BindView(R.id.tv_borrow_money_day)//借款日
    TextView tvBorrowMoneyDay;
    @BindView(R.id.tv_repayment_day)//还款日
    TextView tvRepaymentDay;
    @BindView(R.id.tv_borrow_money)//借款金额
    TextView tvBorrowMoney;
    @BindView(R.id.tv_borrow_up)//提高额度
    TextView tvBorrowUp;
    @BindView(R.id.tv_procedures_money)
    TextView tvProceduresMoney;
    @BindView(R.id.procedures_money)//手续费用
    TextView proceduresMoney;
    @BindView(R.id.tv_borrow_day)
    TextView tvBorrowDay;
    @BindView(R.id.borrow_day)//借款天数
    TextView borrowDay;
    @BindView(R.id.seekbar_money)//贷款额度进度条
    SeekBar seekbarMoney;
    @BindView(R.id.tv_select_money)//贷款的金额
    TextView tvSelectMoney;
    @BindView(R.id.tv_money_min)//金额的最小值
    TextView tvMoneyMin;
    @BindView(R.id.tv_money_max)//金额的最大值
    TextView tvMoneyMax;
    @BindView(R.id.seekbar_day)//贷款天数的进度条
    SeekBar seekbarDay;
    @BindView(R.id.tv_select_day)//贷款天数
    TextView tvSelectDay;
    @BindView(R.id.tv_day_min)//贷款天数的最小值
    TextView tvDayMin;
    @BindView(R.id.tv_day_max)//贷款天数的最大值
    TextView tvDayMax;
    @BindView(R.id.rl1)
    RelativeLayout rl1;
    @BindView(R.id.btn_borrow)//确认借款按钮
    Button btnBorrow;

    private Activity mActivity;

    @Override
    public void init() {
        mActivity = BorrowActivity.this;
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
    @OnClick(R.id.btn_borrow)
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_borrow:
               startActivity(new Intent(mActivity,BorrowConfirmActivity.class));
                break;
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_borrow;
    }

    @Override
    public Activity bindActivity() {
        return this;
    }

}
