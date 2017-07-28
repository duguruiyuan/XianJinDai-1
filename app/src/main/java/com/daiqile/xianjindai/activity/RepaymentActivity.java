package com.daiqile.xianjindai.activity;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;

import com.daiqile.xianjindai.Constants;
import com.daiqile.xianjindai.Fragment.bean.AllBorrowBean;

import com.daiqile.xianjindai.MyApplication;
import com.daiqile.xianjindai.R;

import com.daiqile.xianjindai.Result;
import com.daiqile.xianjindai.utils.ApiRequest;
import com.daiqile.xianjindai.utils.utils.BaseHelper;
import com.daiqile.xianjindai.utils.utils.MobileSecurePayer;
import com.daiqile.xianjindai.utils.utils.PayUtils;
import com.daiqile.xianjindai.utils.utils.ResultChecker;
import com.daiqile.xianjindai.view.TopBar;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;
import suangrenduobao.daiqile.com.mvlib.mv.BaseActivity;
import suangrenduobao.daiqile.com.mvlib.utils.SPUtils;
import suangrenduobao.daiqile.com.mvlib.utils.ToastUtils;


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
    AllBorrowBean.ListBean borrowBean;

    @Override
    protected void loadData() {
        if (getIntent().hasExtra(Constants.BEAN)) {
            borrowBean = (AllBorrowBean.ListBean) getIntent().getSerializableExtra(Constants.BEAN);
            loanAmount = borrowBean.getLoanAmount();
            tvMoney.setText("还款金额  " + loanAmount + "元");

            bankNo = borrowBean.getBankNo();
            tvBankNumber.setText("还款卡号  " + bankNo);

            lid = borrowBean.getId();
        }
    }

    @OnClick(R.id.button)
    public void onClick() {
        if (!TextUtils.isEmpty(bankNo)) {
            String content4Pay = BaseHelper.toJSONString(PayUtils.CONSTRUCTGESTUREPAYORDER(borrowBean));
            new MobileSecurePayer().payRepayment(content4Pay, mHandler,
                    com.daiqile.xianjindai.utils.utils.Constants.RQF_PAY, mActivity, false);
        }
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            String strRet = (String) msg.obj;
            Log.d("BorrowingRecordFragment", strRet);
            switch (msg.what) {
                case com.daiqile.xianjindai.utils.utils.Constants.RQF_PAY: {
                    JSONObject objContent = BaseHelper.string2JSON(strRet);
                    String retCode = objContent.optString("ret_code");
                    String retMsg = objContent.optString("ret_msg");
                    if (com.daiqile.xianjindai.utils.utils.Constants.RET_CODE_SUCCESS.equals(retCode)) {
                        ResultChecker checker = new ResultChecker(strRet);
                        checker.checkSign();
                        ToastUtils.showMessage(mActivity, retMsg, Gravity.CENTER);
                        PayCallback();

                    } else if (com.daiqile.xianjindai.utils.utils.Constants.RET_CODE_PROCESS.equals(retCode)) {
                        ToastUtils.showMessage(mActivity, retMsg, Gravity.CENTER);
                    } else {
                        ToastUtils.showMessage(mActivity, retMsg, Gravity.CENTER);
                    }
                }
                break;
            }
            super.handleMessage(msg);
        }
    };

    //支付成功的回调
    private void PayCallback() {
        ApiRequest.request(MyApplication.getInstance().apiService.paycallback(lid + "", loanAmount + ""), new Subscriber<Result>() {
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
