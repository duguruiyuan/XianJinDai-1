package com.daiqile.xianjindai.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.daiqile.xianjindai.Constants;
import com.daiqile.xianjindai.Fragment.bean.UserInfoBean;
import com.daiqile.xianjindai.MyApplication;
import com.daiqile.xianjindai.R;
import com.daiqile.xianjindai.base.BaseActivity;
import com.daiqile.xianjindai.utils.ApiRequest;
import com.daiqile.xianjindai.view.TopBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import suangrenduobao.daiqile.com.mvlib.utils.SPUtils;
import suangrenduobao.daiqile.com.mvlib.utils.ToastUtils;

//关联贷款页面
public class LoanActivity extends BaseActivity {
    @BindView(R.id.topbar)
    TopBar topbar;
    @BindView(R.id.rl_legal_person)
    RelativeLayout rlLegalPerson;
    @BindView(R.id.rl_house)
    RelativeLayout rlHouse;
//    private Activity mActivity;

    @Override
    public void init() {
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
    public int getLayoutId() {
        return R.layout.activity_loan;
    }

    @Override
    public Activity bindActivity() {
        return this;
    }


    @OnClick({R.id.rl_legal_person, R.id.rl_house})
    public void onViewClicked(final View view) {
        if (MyApplication.getInstance().isLogin()) {
            ApiRequest.request(MyApplication.getInstance().apiService.
                            requestUserMyinfo(MyApplication.getInstance().getUid(),
                                    SPUtils.get(MyApplication.getInstance(), Constants.PHONE, "").toString(),
                                    SPUtils.get(MyApplication.getInstance(), Constants.LOGINPASSWORD, "").toString()),
                    new Subscriber<UserInfoBean>() {

                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                            ToastUtils.showMessage(getResources().getString(R.string.str_http_network_error));
                        }

                        @Override
                        public void onNext(UserInfoBean userInfoBean) {
                            UserInfoBean.UsersBean usersBean = userInfoBean.getUsers().get(0);
                            if (0 == usersBean.getHasIdcardInfo()) {
                                ToastUtils.showMessage("请先完成实名认证");
                                startActivity(new Intent(LoanActivity.this, IdentityCardActivity.class));
                            } else if (0 == usersBean.getHasBank()) {
                                ToastUtils.showMessage("请先绑定银行卡");
                                startActivity(new Intent(LoanActivity.this, ThirdPartCertificationActivity.class));
                                //是否使用了芝麻信用
                            } else if (!SPUtils.contains(MyApplication.getInstance().getApplicationContext(), Constants.ZHIMAXINYONG_BAIQISHI)) {
                                ToastUtils.showMessage("请先完成芝麻信用");
                                startActivity(new Intent(LoanActivity.this, ThirdPartCertificationActivity.class));
                            } else if (!usersBean.getCity().equals("宁波")) {
                                ToastUtils.showMessage(getString(R.string.str_rule));
                            } else {
                                // TODO: 2017/7/27 需要修改的
                                Intent intent = new Intent();
                                intent.setClass(LoanActivity.this, BorrowActivity.class);
                                switch (view.getId()) {
                                    case R.id.rl_legal_person:
                                        intent.putExtra(Constants.LOANTYPE, "3");
                                        break;
                                    case R.id.rl_house:
                                        intent.putExtra(Constants.LOANTYPE, "4");
                                        break;
                                }
                                startActivity(intent);
                            }
                        }
                    });

        } else {
            startActivity(new Intent(LoanActivity.this, LoginActivity.class));
        }
    }

}
