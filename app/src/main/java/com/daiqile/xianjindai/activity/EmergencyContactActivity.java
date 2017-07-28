package com.daiqile.xianjindai.activity;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.daiqile.xianjindai.Constants;
import com.daiqile.xianjindai.Fragment.bean.UserInfoBean;
import com.daiqile.xianjindai.MyApplication;
import com.daiqile.xianjindai.R;
import com.daiqile.xianjindai.Result;
import com.daiqile.xianjindai.UserInfoRequest;
import com.daiqile.xianjindai.utils.ApiRequest;
import com.daiqile.xianjindai.utils.CallBack;
import com.daiqile.xianjindai.view.TopBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import suangrenduobao.daiqile.com.mvlib.mv.BaseActivity;
import suangrenduobao.daiqile.com.mvlib.utils.RegexValidateUtil;
import suangrenduobao.daiqile.com.mvlib.utils.ToastUtils;
import suangrenduobao.daiqile.com.mvlib.utils.http.BaseBean;

/**
 * 紧急联系人
 */
public class EmergencyContactActivity extends BaseActivity {

    @BindView(R.id.topbar)//topbar
            TopBar topbar;
    @BindView(R.id.tv_choose_person)//选择第一联系人
            TextView tvChoosePerson;
    @BindView(R.id.ll_relation)//第一联系人LinearLayout
            LinearLayout llRelation;
    @BindView(R.id.et_name)//第一联系人名字
            EditText etName;
    @BindView(R.id.et_phone)//第一联系人手机号
            EditText etPhone;
    @BindView(R.id.et_qq)//第一联系人手机号
            EditText etqq;
    @BindView(R.id.et_weixin)//第一联系人手机号
            EditText etWeixin;

    @BindView(R.id.tv_choose_person_two)//选择第二联系人
            TextView tvChoosePersonTwo;
    @BindView(R.id.ll_relation_two)//第二联系人LinearLayout
            LinearLayout llRelationTwo;
    @BindView(R.id.et_name_two)//第二联系人名字
            EditText etNameTwo;
    @BindView(R.id.et_phone_two)//第二联系人手机号
            EditText etPhoneTwo;
    @BindView(R.id.btn_submit)//提交按钮
            Button btnSubmit;


    @BindView(R.id.et_qq_two)//第一联系人手机号
            EditText etqqTwo;
    @BindView(R.id.et_weixin_two)//第一联系人手机号
            EditText etWeixinTwo;


    @OnClick(R.id.btn_submit)
    public void onClick() {
//        userId	是	string	用户id
//        firstContactName	是	string	第一联系人名字
//        firstContactType	是	string	第一联系人类型
//        firstContactCellPhone	是	string	第一联系人电话

//        secondContactName	是	string	第二联系人名字
//        secondContactType	是	string	第二联系人类型
//        secondContactCellPhone	是	string	第二联系人电话
//        qq	是	string	第一联系人对应的QQ
//        qq2	是	string	第二联系人对应的QQ
//        weChat	是	string	第一联系人对应的微信
//        weChat2
        String mChonosePerson = tvChoosePerson.getText().toString().trim();
        String mEtName = etName.getText().toString().trim();
        String mEtPhone = etPhone.getText().toString().trim();
        String mEtqq = etqq.getText().toString().trim();
        String mEtWeixin = etWeixin.getText().toString().trim();

        String mTchoosePersonTwo = tvChoosePersonTwo.getText().toString().trim();
        String mEtNameTwo = etNameTwo.getText().toString().trim();
        String mEtPhoneTwo = etPhoneTwo.getText().toString().trim();
        String mEtqqTwo = etqqTwo.getText().toString().trim();
        String mEtWeixinTwo = etWeixinTwo.getText().toString().trim();

        if (TextUtils.isEmpty(mChonosePerson)) {
            ToastUtils.showMessage("请选择与本人关系");
            return;
        }
        if (TextUtils.isEmpty(mEtName)) {
            ToastUtils.showMessage("请填写姓名");
            return;
        }
        if (TextUtils.isEmpty(mEtPhone)) {
            ToastUtils.showMessage("请填写手机号码");
            return;
        }
        if (!RegexValidateUtil.checkCellphone(mEtPhone)) {
            ToastUtils.showMessage("请填写正确手机号码");
            return;
        }
        if (TextUtils.isEmpty(mEtqq)) {
            ToastUtils.showMessage("请填写QQ号");
            return;
        }
        if (TextUtils.isEmpty(mEtWeixin)) {
            ToastUtils.showMessage("请填写微信号");
            return;
        }

        //第二个
        if (TextUtils.isEmpty(mTchoosePersonTwo)) {
            ToastUtils.showMessage("请选择与朋友关系");
            return;
        }
        if (TextUtils.isEmpty(mEtNameTwo)) {
            ToastUtils.showMessage("请填写姓名");
            return;
        }
        if (TextUtils.isEmpty(mEtPhoneTwo)) {
            ToastUtils.showMessage("请填写手机号码");
            return;
        }
        if (!RegexValidateUtil.checkCellphone(mEtPhoneTwo)) {
            ToastUtils.showMessage("请填写正确手机号码");
            return;
        }
        if (TextUtils.isEmpty(mEtqqTwo)) {
            ToastUtils.showMessage("请填写QQ号");
            return;
        }
        if (TextUtils.isEmpty(mEtWeixinTwo)) {
            ToastUtils.showMessage("请填写微信号");
            return;
        }

        Map<String, String> map = new HashMap<>();
        map.put("id", MyApplication.getInstance().getUid());
        map.put("firstContactName", mEtName);
        map.put("firstContactType", mChonosePerson);
        map.put("firstContactCellPhone", mEtPhone);
        map.put("qq", mEtqq);
        map.put("weChat", mEtWeixin);
        //第二个
        map.put("secondContactType", mTchoosePersonTwo);
        map.put("secondContactName", mEtNameTwo);
        map.put("secondContactCellPhone", mEtPhoneTwo);
        map.put("qq2", mEtqqTwo);
        map.put("weChat2", mEtWeixinTwo);
        ApiRequest.request(MyApplication.getInstance().apiService.updateRelative(map), new Subscriber<Result>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showMessage("网络不好");
            }

            @Override
            public void onNext(Result result) {
                ToastUtils.showMessage(result.getMsg());
                if (result.isSuccess()) {
                    finish();
                }

            }
        });
//        MyApplication.getInstance().apiService.addBank(map).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<Result>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        ToastUtils.showMessage("网络不好");
//                    }
//
//                    @Override
//                    public void onNext(Result result) {
//                        ToastUtils.showMessage(result.getMsg());
//                        if (result.isSuccess()) {
//                            finish();
//                        }
//
//                    }
//                });
    }

    OptionsPickerView OnePeopleOptions;
    OptionsPickerView TwoPeopleOptions;

    private ArrayList<String> onePeopleList = new ArrayList<>();
    private ArrayList<String> twoPeopleList = new ArrayList<>();

    @Override
    protected void initConfig() {
        super.initConfig();
        for (String s : Constants.KINSFOLK) {
            onePeopleList.add(s);
        }
        for (String s : Constants.FRIEND) {
            twoPeopleList.add(s);
        }

        OnePeopleOptions = new OptionsPickerView(mActivity);
        TwoPeopleOptions = new OptionsPickerView(mActivity);

        OnePeopleOptions.setCancelable(true);
        TwoPeopleOptions.setCancelable(true);

        topbar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {
            }
        });

        OnePeopleOptions.setPicker(onePeopleList);
//        OnePeopleOptions.setSelectOptions(0);
        OnePeopleOptions.setCyclic(false);
        OnePeopleOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                Log.d("EmergencyContactActivit", onePeopleList.get(options1));
                tvChoosePerson.setText(onePeopleList.get(options1));
            }
        });

        TwoPeopleOptions.setPicker(twoPeopleList);
//        TwoPeopleOptions.setSelectOptions(0);
        TwoPeopleOptions.setCyclic(false);

        TwoPeopleOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                Log.d("EmergencyContactActivit", twoPeopleList.get(options1));
                tvChoosePersonTwo.setText(twoPeopleList.get(options1));
            }
        });


        llRelation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnePeopleOptions.show();
            }
        });
        llRelationTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TwoPeopleOptions.show();
            }
        });
    }


    @Override
    protected boolean switchToolbar() {
        return false;
    }

    @Override
    public int initLayout() {
        return R.layout.activity_emergency_contact;
    }


    @Override
    protected void loadData() {
        UserInfoRequest.requestUserInfo(new CallBack() {
            @Override
            public void onNext(BaseBean baseBean) {
                UserInfoBean.UsersBean usersBean = ((UserInfoBean) baseBean).getUsers().get(0);
                tvChoosePerson.setText(usersBean.getFirstContactType());
                etName.setText(usersBean.getFirstContactName());
                etPhone.setText(usersBean.getFirstContactCellPhone());
                etqq.setText(usersBean.getQq());
                etWeixin.setText(usersBean.getWeChat());

                tvChoosePersonTwo.setText(usersBean.getSecondContactType());
                etNameTwo.setText(usersBean.getSecondContactName());//secondContactCellPhone
                etPhoneTwo.setText(usersBean.getSecondContactCellPhone());
                etqqTwo.setText(usersBean.getQq2());
                etWeixinTwo.setText(usersBean.getWeChat2());
            }

            @Override
            public void onError() {
                ToastUtils.showMessage(getResources().getString(R.string.str_http_network_error));
            }
        });
    }


}
