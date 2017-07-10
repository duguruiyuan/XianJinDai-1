package com.daiqile.xianjindai.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.daiqile.xianjindai.R;
import com.daiqile.xianjindai.utils.TimeUtils;
import com.daiqile.xianjindai.view.TopBar;

import butterknife.BindView;

import butterknife.OnClick;
import suangrenduobao.daiqile.com.mvlib.mv.BaseActivity;


public class BorrowActivity extends BaseActivity implements SeekBar.OnSeekBarChangeListener {
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


//    private Activity mActivity;

//    @Override
//    public void init() {
//        mActivity = BorrowActivity.this;
//        topBar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
//            @Override
//            public void leftClick() {
//                finish();
//            }
//
//            @Override
//            public void rightClick() {
//
//            }
//        });
//
//
//        //借款日
//        tvBorrowMoneyDay.setText(String.format("借款日:%s", TimeUtils.timeslashData()));
//        //还款日
//        tvRepaymentDay.setText(String.format("还款日:%s", "7"));
//
//        borrowDay.setText("21天");
//
//        Log.d("BorrowActivity", TimeUtils.timeslashData());
//
//    }

    @OnClick(R.id.btn_borrow)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_borrow:
                startActivity(new Intent(mContext, BorrowConfirmActivity.class));
                break;
        }
    }


    @Override
    protected int initLayout() {
        return R.layout.activity_borrow;
    }

    @Override
    protected void loadData() {
        topBar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {

            }
        });


        //借款日
        tvBorrowMoneyDay.setText(String.format("借款日:%s", TimeUtils.timeslashData()));
        //还款日
        tvRepaymentDay.setText(String.format("还款日:%s", "7"));

        borrowDay.setText("21天");

        Log.d("BorrowActivity", TimeUtils.timeslashData());

        seekbarMoney.setOnSeekBarChangeListener(this);
        seekbarDay.setOnSeekBarChangeListener(this);
    }

    @Override
    protected boolean switchToolbar() {
        return false;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        int progress = seekBar.getProgress();
        if (R.id.seekbar_money == seekBar.getId()) {
            seekBar.setProgress((progress / 25) * 25);//500 0 1000 1 1500 2 2000 3

            tvSelectMoney.setText(String.format("%d元", 500 * ((progress / 25 + 1))));
            tvBorrowMoney.setText(String.format("¥%d", 500 * ((progress / 25 + 1))));

            if (progress > 0 && progress < 33) {
                seekBar.setProgress(0);
                tvSelectMoney.setText("500元");
            } else if (progress >= 33 && progress < 66) {
                seekBar.setProgress(33);
            } else if (progress >= 66 && progress <= 100) {
                seekBar.setProgress(100);
            }


        } else if (R.id.seekbar_day == seekBar.getId()) {
            Log.d("BorrowActivity", "progress:" + progress);

            seekBar.setProgress(((progress / 50) + 1) * 50);
            tvSelectDay.setText(progress / 50 == 1 ? "14天" : "7天");
        }

    }
}
