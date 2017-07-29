package com.daiqile.xianjindai.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.daiqile.xianjindai.Constants;
import com.daiqile.xianjindai.MyApplication;
import com.daiqile.xianjindai.R;
import com.daiqile.xianjindai.Result;
import com.daiqile.xianjindai.activity.bean.BankInfoList;
import com.daiqile.xianjindai.utils.ApiRequest;
import com.daiqile.xianjindai.view.TopBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import suangrenduobao.daiqile.com.mvlib.mv.BaseActivity;
import suangrenduobao.daiqile.com.mvlib.utils.ToastUtils;

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
//    new Intent(mActivity, BankCardActivity.class);

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
    Map<String, String> map;

    @Override
    protected void loadData() {
        if (getIntent().hasExtra(Constants.APPLY)) {
            map = (Map<String, String>) getIntent().getSerializableExtra(Constants.APPLY);
            loanAmount = map.get(Constants.LOANAMOUNT);
            term = map.get(Constants.TERM);
            loanType = map.get(Constants.LOANTYPE);
            poundage = map.get(Constants.POUNDAGE);

            tvAccountMoney.setText(loanAmount.replace("元",""));
            tvBorrowDay.setText(term);
            tvProcedure.setText(poundage);

            tvDueMoney.setText((Double.parseDouble(loanAmount.replace("元", "")) + Double.parseDouble(poundage.replace("元", ""))) + "");
        }

        updataBank();

    }

    ArrayList<String> bankList;
    List<BankInfoList.BanksBean> banks;
    int id = -1;

    @OnClick(R.id.btn_borrow_comfirm)
    public void onClick() {
        if (-1 == id) {
            ToastUtils.showMessage("你还没有选择银行卡");
        } else {
            // TODO: 2017/7/11  贷款申请
            Map<String, String> map = new HashMap<>();
            map.put("userId", MyApplication.getInstance().getUid());
            map.put("loanAmount", loanAmount);
            map.put("term", term);
            map.put("bankId", id + "");//银行ID
            map.put("poundage", poundage);
            map.put("tokenKey", "");//tokenKey
            map.put("platform", "");//平台标示
            map.put("loanType", loanType);//类型
            ApiRequest.request(MyApplication.getInstance().apiService.apply(map), new Subscriber<Result>() {
                @Override
                public void onCompleted() {
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(Result result) {
                    if (result.isSuccess()) {
                        finish();
                    }
                    ToastUtils.showMessage(result.getMsg());
                }
            });
        }
    }

    @OnClick({R.id.tv_choose_bank, R.id.tv_bank_no})
    public void onClick(View view) {
        if (bankOptions == null) {
            bankOptions = new OptionsPickerView(mContext);
            bankOptions.setCancelable(true);
            bankOptions.setPicker(bankList);
            bankOptions.setCyclic(false);
//            bankOptions.setSelectOptions(0);
            bankOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int option2, int options3) {
                    BankInfoList.BanksBean banksBean = banks.get(options1);
                    tvBankNo.setText(banksBean.getBankNo());
                    tvChooseBank.setText(banksBean.getBankName());
                    id = banksBean.getId();
                }
            });
        }
        if (null != bankList && bankList.size() > 0) {
            switch (view.getId()) {
                case R.id.tv_choose_bank:
                case R.id.tv_bank_no:
                    bankOptions.show();
                    break;
            }
        } else {
            flag = false;
            new Intent(mActivity, BankCardActivity.class);
        }
    }

    boolean flag = true;

    @Override
    protected void onStart() {
        super.onStart();
        if (!flag) {
            updataBank();
            flag = true;
        }
    }

    private void updataBank() {
        map = new HashMap<>();
        map.put("userId", MyApplication.getInstance().getUid());
        map.put(Constants.PHONE, MyApplication.getInstance().getPhone());
        map.put(Constants.LOGINPASSWORD, MyApplication.getInstance().getLoginPassword());
        MyApplication.getInstance().apiService.getBankLists(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<BankInfoList>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
//                ToastUtils.showMessage("网络不好");
            }

            @Override
            public void onNext(BankInfoList bankInfoList) {
                banks = bankInfoList.getBanks();
                if (null != banks && banks.size() > 0) {
                    bankList = new ArrayList<>();
                    for (BankInfoList.BanksBean bank : banks) {
                        bankList.add(bank.getBankNo());
                    }
                    BankInfoList.BanksBean banksBean = banks.get(0);
                    tvBankNo.setText(banksBean.getBankNo());
                    tvChooseBank.setText(banksBean.getBankName());
                    id = banksBean.getId();
                } else {
                    tvBankNo.setText("请选择银行卡");
                    tvChooseBank.setText("请选择银行卡");
                }
            }
        });
    }

    OptionsPickerView bankOptions;
}
