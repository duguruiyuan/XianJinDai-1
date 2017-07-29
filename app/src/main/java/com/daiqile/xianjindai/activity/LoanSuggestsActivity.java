package com.daiqile.xianjindai.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.daiqile.xianjindai.Constants;
import com.daiqile.xianjindai.Fragment.bean.LoanInfoBean;
import com.daiqile.xianjindai.R;
import com.daiqile.xianjindai.view.TopBar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import suangrenduobao.daiqile.com.mvlib.mv.BaseActivity;
import suangrenduobao.daiqile.com.mvlib.utils.GsonUtil;

public class LoanSuggestsActivity extends BaseActivity {

    @BindView(R.id.topbar)
    TopBar topbar;
    @BindView(R.id.tv_loan_describe)
    TextView tvLoanDescribe;

    @Override
    protected boolean switchToolbar() {
        return false;
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_loan_suggests;
    }

    List<LoanInfoBean> loanInfoBseens;

    private void getLoanInfoBean() {
        try {
            InputStreamReader inputReader = new InputStreamReader(getAssets().open(Constants.LOAN_JSON));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            String result = "";
            while ((line = bufReader.readLine()) != null) {
                result += line;
            }
            loanInfoBseens = GsonUtil.GsonToList(result, LoanInfoBean.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void loadData() {
        topbar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {

            }
        });
        getLoanInfoBean();
        tvLoanDescribe.setText(loanInfoBseens.get(Integer.parseInt(getIntent().getStringExtra(Constants.LOANTYPE))).getDescribe());
//        if (getIntent().hasExtra(Constants.LOANTYPE)) {
//            switch (Integer.parseInt(getIntent().getStringExtra(Constants.LOANTYPE))) {
//                case 0:
//                    tvLoanDescribe.setText("贷款金额：1000-5000元，周期7天一期  区域：全国 \\n\\n费用：按照借款金额比例20%  \\n\\n信审费：10%   服务费：5%     账户管理费：5%   利息：万5/天利息-减免    续借费用：支付该期手续费20% 加30元续借费用");
//                    break;
//                case 1:
//                    tvLoanDescribe.setText("丽人贷介绍：本产品主要针对爱美女性定制的一款整形分期产品，不同程度满足您的“爱美之心”而又资金不足的年轻女性 \\n\\n贷款金额5000-50000元，  周期：12月  区域：目前宁波大市，20-35岁女性\\n\\n还款方式：按照借款金额等本等息还款  每月利息2%，平台管理费用1%，合计3%    信审费1%一次性收取");
//                    break;
//                case 2:
//
//                    break;
//                case 3:
//                    break;
//            }
//        }
    }

    @OnClick(R.id.btn_card)
    public void onClick() {
        Intent intent = new Intent();
        intent.setClass(mActivity, BorrowActivity.class);
        intent.putExtra(Constants.LOANTYPE, getIntent().getStringExtra(Constants.LOANTYPE));
        startActivity(intent);
        finish();
    }
}
