package com.daiqile.xianjindai.activity;

import android.text.TextUtils;
import android.widget.TextView;

import com.daiqile.xianjindai.Constants;
import com.daiqile.xianjindai.Fragment.bean.AllBorrowBean;

import com.daiqile.xianjindai.R;

import com.daiqile.xianjindai.view.TopBar;

import butterknife.BindView;
import butterknife.OnClick;
import suangrenduobao.daiqile.com.mvlib.mv.BaseActivity;


public class RepaymentActivity extends BaseActivity {

    @BindView(R.id.topbar)
    TopBar topbar;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_bank_number)
    TextView tvBankNumber;

    @Override
    protected boolean switchToolbar() {
        return false;
    }

    @Override
    protected void initConfig() {
        super.initConfig();
        topbar.setRightButton(true);
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
    protected int initLayout() {
        return R.layout.activity_repayment;
    }

    String bankNo;
    int loanAmount;
    int lid;

    @Override
    protected void loadData() {
        if (getIntent().hasExtra(Constants.BEAN)) {
            AllBorrowBean.ListBean borrowBean = (AllBorrowBean.ListBean) getIntent().getSerializableExtra(Constants.BEAN);
            loanAmount = borrowBean.getLoanAmount();
            tvMoney.setText(loanAmount+"");

            bankNo = borrowBean.getBankNo();
            tvBankNumber.setText(bankNo);

            lid = borrowBean.getId();
        }
    }

    @OnClick(R.id.button)
    public void onClick() {
        if (!TextUtils.isEmpty(bankNo)) {
//            ApiRequest.request(MyApplication.getInstance().apiService.requestAllBorrow(MyApplication.getInstance().getUid()),
//                    new Subscriber<AllBorrowBean>() {
//
//                        @Override
//                        public void onCompleted() {
//                        }
//
//                        @Override
//                        public void onError(Throwable e) {
//                            ToastUtils.showMessage(getResources().getString(R.string.str_http_network_error));
//                        }
//
//                        @Override
//                        public void onNext(AllBorrowBean allBorrowBean) {
//
//                        }
//                    });
        }
    }
}
