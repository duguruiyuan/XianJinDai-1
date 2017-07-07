package com.daiqile.xianjindai.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.daiqile.xianjindai.R;
import com.daiqile.xianjindai.base.BaseActivity;
import com.daiqile.xianjindai.view.TopBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    @BindView(R.id.activity_emergency_contact)
    LinearLayout activityEmergencyContact;
    private Activity mActivity;

    OptionsPickerView OnePeopleOptions;
    OptionsPickerView TwoPeopleOptions;


    private ArrayList<String> OnePeopleList = new ArrayList<>();
    private ArrayList<String> TwoPeopleList = new ArrayList<>();
    @Override
    public void init() {
        mActivity = EmergencyContactActivity.this;
        OnePeopleOptions = new OptionsPickerView(mActivity);
        TwoPeopleOptions = new OptionsPickerView(mActivity);
        OnePeople();
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

    private void OnePeople(){

    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_emergency_contact;
    }

    @Override
    public Activity bindActivity() {
        return this;
    }


}
