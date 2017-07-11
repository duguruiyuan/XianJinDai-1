package com.daiqile.xianjindai.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.daiqile.xianjindai.Constants;
import com.daiqile.xianjindai.R;
import com.daiqile.xianjindai.utils.TimeUtils;
import com.daiqile.xianjindai.view.TopBar;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

import butterknife.OnClick;
import suangrenduobao.daiqile.com.mvlib.mv.BaseActivity;

import static com.daiqile.xianjindai.utils.TimeUtils.timeslashDay;

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
                Intent intent = new Intent(mContext, BorrowConfirmActivity.class);
                Map<String, String> map = new HashMap<>();
                map.put(Constants.LOANAMOUNT, tvBorrowMoney.getText().toString().trim().replace("¥", ""));

//                String.format("%s-%s", TimeUtils.timeslashData(), TimeUtils.timeslashDay(defaultNumber));
//                map.put(Constants.TERM, defaultNumber + "");
                map.put(Constants.TERM, String.format("%s-%s", TimeUtils.timeslashData(), TimeUtils.timeslashDay(defaultNumber)));

                map.put(Constants.LOANTYPE, loanType);
                map.put(Constants.POUNDAGE, proceduresMoney.getText().toString().trim().replace("¥", ""));
//                map.put(Constants.DUEMONEY, );
                intent.putExtra(Constants.APPLY, (Serializable) map);
                startActivity(intent);
                break;
        }
    }


    @Override
    protected int initLayout() {
        return R.layout.activity_borrow;
    }

    int defaultNumber = 14;

    String loanType;

    @Override
    protected void loadData() {

        if (getIntent().hasExtra(Constants.LOANTYPE)) {
            loanType = getIntent().getStringExtra(Constants.LOANTYPE);
            Log.d("BorrowActivity", loanType);
        }

        topBar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {

            }
        });
        //借款日  500 1000 1500 2000
        tvBorrowMoneyDay.setText(String.format("借款日:%s", TimeUtils.timeslashData()));
        //还款日
        tvRepaymentDay.setText(String.format("还款日:%s", timeslashDay(defaultNumber)));

        borrowDay.setText(defaultNumber + "天");

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
            seekBar.setProgress(progress / 25 == 3 ? 100 : (progress / 25 * 33));
            tvSelectMoney.setText(String.format("%d元", 500 * ((progress / 25 + 1))));
            tvBorrowMoney.setText(String.format("¥%d", 500 * ((progress / 25 + 1))));

        } else if (R.id.seekbar_day == seekBar.getId()) {
            defaultNumber = progress > 50 ? 14 : 7;
            seekBar.setProgress(progress > 50 ? 100 : 0);
            borrowDay.setText(defaultNumber + "天");
            tvSelectDay.setText(defaultNumber + "天");

            tvRepaymentDay.setText(String.format("还款日:%s", timeslashDay(defaultNumber)));
        }

    }
}
