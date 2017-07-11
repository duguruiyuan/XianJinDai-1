package com.daiqile.xianjindai.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.daiqile.xianjindai.Constants;
import com.daiqile.xianjindai.R;
import com.daiqile.xianjindai.view.TopBar;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import suangrenduobao.daiqile.com.mvlib.mv.BaseActivity;

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
    @BindView(R.id.tv_bank_no)
    TextView tvBankNo;
    @BindView(R.id.tv_procedure)
    TextView tvProcedure;


    @Override
    protected boolean switchToolbar() {
        return false;
    }

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
    }

    @Override
    public int initLayout() {
        return R.layout.activity_borrow_confirm;
    }

    String loanAmount, term, loanType, poundage;

    @Override
    protected void loadData() {
        if (getIntent().hasExtra(Constants.APPLY)) {
            Map<String, String> map = (Map<String, String>) getIntent().getSerializableExtra(Constants.APPLY);
            loanAmount = map.get(Constants.LOANAMOUNT);
            term = map.get(Constants.TERM);
            loanType = map.get(Constants.LOANTYPE);
            poundage = map.get(Constants.POUNDAGE);

            tvAccountMoney.setText(loanAmount);
            tvBorrowDay.setText(term);
            tvProcedure.setText(poundage);

            tvDueMoney.setText((Integer.parseInt(loanAmount) + Integer.parseInt(poundage)) + "");
        }

    }


    @OnClick(R.id.btn_borrow_comfirm)
    public void onClick() {
        // TODO: 2017/7/11  贷款申请
    }
}
