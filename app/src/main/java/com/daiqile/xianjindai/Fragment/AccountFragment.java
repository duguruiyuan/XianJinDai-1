package com.daiqile.xianjindai.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.net.Uri;

import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;

import android.view.View;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daiqile.xianjindai.MyApplication;
import com.daiqile.xianjindai.R;
import com.daiqile.xianjindai.UserPrefs;
import com.daiqile.xianjindai.activity.BankCardActivity;
import com.daiqile.xianjindai.activity.ChangePwdActivity;
import com.daiqile.xianjindai.activity.EmergencyContactActivity;
import com.daiqile.xianjindai.activity.IdentityCardActivity;
import com.daiqile.xianjindai.activity.LoanActivity;
import com.daiqile.xianjindai.activity.LoginActivity;
import com.daiqile.xianjindai.activity.PersonalDetailsActivity;
import com.daiqile.xianjindai.activity.ThirdPartCertificationActivity;


import butterknife.BindView;
import butterknife.OnClick;

import suangrenduobao.daiqile.com.mvlib.mv.BaseFragment;

//我的
public class AccountFragment extends BaseFragment {

    @BindView(R.id.circle)
    ImageView circle;
    @BindView(R.id.ll_people_card)
    LinearLayout llPeopleCard;
    @BindView(R.id.ll_bank_card)
    LinearLayout llBankCard;
    @BindView(R.id.ll_zhima_credit)
    LinearLayout llZhimaCredit;
    @BindView(R.id.ll_person_detail)
    LinearLayout llPersonDetail;
    @BindView(R.id.ll_emergency_contact)
    LinearLayout llEmergencyContact;
    @BindView(R.id.ll_password)
    LinearLayout llPassword;
    @BindView(R.id.ll_service)
    LinearLayout llService;
    @BindView(R.id.ll_versions)
    LinearLayout llVersions;
    //    @BindView(R.id.ll_question)
//    LinearLayout llQuestion;
    @BindView(R.id.ll_loan)
    LinearLayout llLoan;
    @BindView(R.id.tv_exit)
    TextView tvExit;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    @Override
    public void onStart() {
        super.onStart();
        if (MyApplication.getInstance().isLogin()) {
            tvExit.setVisibility(View.VISIBLE);
        } else {
            tvExit.setVisibility(View.GONE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void loadData() {
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d("AccountFragment", "加载");
                if (null!=swipeRefresh&&swipeRefresh.isRefreshing()) {
                    swipeRefresh.setRefreshing(false);
                }
            }
        });

    }

    @NonNull
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_account;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.circle, R.id.ll_people_card, R.id.ll_bank_card, R.id.ll_zhima_credit, R.id.ll_person_detail, R.id.ll_emergency_contact, R.id.ll_password, R.id.ll_service, R.id.ll_versions/*, R.id.ll_question*/, R.id.ll_loan, R.id.tv_exit})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.circle:
                break;
            case R.id.ll_people_card: //跳转到身份认证页面
                goActivity(IdentityCardActivity.class, true);
//                startActivity(new Intent(mActivity, IdentityCardActivity.class));
                break;
            case R.id.ll_bank_card://跳转到绑定银行卡页面
//                startActivity(new Intent(mActivity, BankCardActivity.class));
                goActivity(BankCardActivity.class, true);
                break;
            case R.id.ll_zhima_credit://跳转三方认证页面
//                startActivity(new Intent(mActivity, ThirdPartCertificationActivity.class));
                goActivity(ThirdPartCertificationActivity.class, true);
                break;
            case R.id.ll_person_detail://跳转到个人信息页面
//                startActivity(new Intent(mActivity, PersonalDetailsActivity.class));
                goActivity(PersonalDetailsActivity.class, true);
                break;
            case R.id.ll_emergency_contact://跳转到紧急联系人页面
//                startActivity(new Intent(mActivity, EmergencyContactActivity.class));
                goActivity(EmergencyContactActivity.class, true);
                break;
            case R.id.ll_password://挑战到修改密码页面
//                startActivity(new Intent(mActivity, ChangePwdActivity.class));
                goActivity(ChangePwdActivity.class, true);
                break;
            case R.id.ll_service://拨打客服电话
                startActivity(new Intent("android.intent.action.CALL", Uri.parse("tel:" + "400-165-65656")));
                break;
            case R.id.ll_versions:
                break;
            case R.id.ll_loan:
//                startActivity(new Intent(mActivity, LoanActivity.class));
                goActivity(LoanActivity.class, true);
                break;
            case R.id.tv_exit:
                isExit();
                break;
        }
    }

    Intent intent = new Intent();

    private void goActivity(Class clazz, boolean falg) {
        if (falg) {
            if (!MyApplication.getInstance().isLogin()) {
                intent.setClass(mActivity, LoginActivity.class);
            }else{
                intent.setClass(mActivity, clazz);
            }
        } else {
            intent.setClass(mActivity, clazz);
        }
        startActivity(intent);
    }


    private void isExit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle("是否退出登陆");
        builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //application.clearLoginParams();
                UserPrefs.getInstance().clearUser();
                Log.e("sss", "onClick: " + UserPrefs.getInstance().getUid());
                startActivity(new Intent(mActivity, LoginActivity.class));

                MyApplication.getInstance().clearLoginParams();
            }
        });


        builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
