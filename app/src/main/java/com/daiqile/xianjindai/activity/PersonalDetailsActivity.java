package com.daiqile.xianjindai.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.daiqile.xianjindai.Fragment.bean.UserInfoBean;
import com.daiqile.xianjindai.MyApplication;
import com.daiqile.xianjindai.R;
import com.daiqile.xianjindai.Result;
import com.daiqile.xianjindai.UserInfoRequest;
import com.daiqile.xianjindai.utils.ApiRequest;
import com.daiqile.xianjindai.utils.CallBack;
import com.daiqile.xianjindai.utils.SoftInputUtil;
import com.daiqile.xianjindai.view.AddressFrameLayout;
import com.daiqile.xianjindai.view.TopBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;
import suangrenduobao.daiqile.com.mvlib.mv.BaseActivity;
import suangrenduobao.daiqile.com.mvlib.utils.RegexValidateUtil;
import suangrenduobao.daiqile.com.mvlib.utils.ToastUtils;
import suangrenduobao.daiqile.com.mvlib.utils.http.BaseBean;


/**
 * 个人信息页面
 */
public class PersonalDetailsActivity extends BaseActivity {

    @BindView(R.id.topbar)//topbar
            TopBar topbar;
    @BindView(R.id.et_phone)//手机号码
            EditText etPhone;
    @BindView(R.id.et_address_detail)//详细地址
            EditText etAddressDetail;
    @BindView(R.id.et_work_address)//工作单位
            EditText etWorkAddress;
    @BindView(R.id.et_work_address_phone)//工作单位号码
            EditText etWorkAddressPhone;
    @BindView(R.id.af_address)
    AddressFrameLayout afAddress;
    @BindView(R.id.et_work_name)
    EditText etWorkName;
    @BindView(R.id.et_housing_situation)
    EditText etHousingSituation;
    @BindView(R.id.et_marriage_status)
    TextView etMarriageStatus;


    @BindView(R.id.tv_ningbo_status)
    TextView tvNingboStatus;
    @BindView(R.id.et_qq)
    EditText etQq;
    @BindView(R.id.et_weixin)
    EditText etWeixin;

    @Override
    public int initLayout() {
        return R.layout.activity_personal_details;
    }


    @OnClick({R.id.btn_bind_card, R.id.et_marriage_status, R.id.tv_other_optional, R.id.tv_ningbo_status})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_bind_card:
                String strPhone = etPhone.getText().toString().trim();
                if (TextUtils.isEmpty(strPhone)) {
                    ToastUtils.showMessage("请输入手机号码");
                    return;
                }
                if (!RegexValidateUtil.checkCellphone(strPhone)) {
                    ToastUtils.showMessage("请输入正确的手机号码");
                    return;
                }
                if ("-1".equals(afAddress.getCityId())) {
                    ToastUtils.showMessage("台湾，香港，澳门这些地方不能申请");
                    return;
                }
                if (TextUtils.isEmpty(strPhone)) {
                    ToastUtils.showMessage("请输入手机号码");
                    return;
                }

                if ("-1".equals(afAddress.getProvinceId())) {
                    ToastUtils.showMessage("请选择省市区");
                    return;
                }

                String strAddressDetail = etAddressDetail.getText().toString().trim();
                if (TextUtils.isEmpty(strAddressDetail)) {
                    ToastUtils.showMessage("请输入详细地址");
                    return;
                }


                String strWorkName = etWorkName.getText().toString().trim();
                if (TextUtils.isEmpty(strWorkName)) {
                    ToastUtils.showMessage("请输入工作单位");
                    return;
                }
                String strWorkAddressPhone = etWorkAddressPhone.getText().toString().trim();
                if (TextUtils.isEmpty(strWorkAddressPhone)) {
                    ToastUtils.showMessage("请输入公司电话");
                    return;
                }
                String strWorkAddress = etWorkAddress.getText().toString().trim();
                if (TextUtils.isEmpty(strWorkAddress)) {
                    ToastUtils.showMessage("请输入公司地址");
                    return;
                }
                String strNingboStatus = tvNingboStatus.getText().toString().trim();
                if (TextUtils.isEmpty(strWorkAddress)) {
                    ToastUtils.showMessage("请输入公司是否在宁波");
                    return;
                }
                String strHousingSituation = etHousingSituation.getText().toString().trim();
                if (TextUtils.isEmpty(strHousingSituation)) {
                    ToastUtils.showMessage("请输入住房情况");
                    return;
                }
                String strMarriageStatus = etMarriageStatus.getText().toString().trim();
                if (TextUtils.isEmpty(strMarriageStatus)) {
                    ToastUtils.showMessage("请选择婚姻情况");
                    return;
                }
                String strQq = etQq.getText().toString().trim();
                if (TextUtils.isEmpty(strQq)) {
                    ToastUtils.showMessage("请填写QQ号");
                    return;
                }
                String strWeixin = etWeixin.getText().toString().trim();
                if (TextUtils.isEmpty(strWeixin)) {
                    ToastUtils.showMessage("请填写微信号");
                    return;
                }

                Log.d("PersonalDetailsActivity", strNingboStatus);


                Map<String, String> map = new HashMap<>();
                map.put("userId", MyApplication.getInstance().getUid());
                map.put("other_phone", strPhone);
                map.put("province_id", afAddress.getProvinceId());
                map.put("city_id", afAddress.getCityId());
                map.put("county_id", afAddress.getCountryId());
                map.put("area", strAddressDetail);
                map.put("company_phone", strWorkAddressPhone);
                map.put("company", strWorkName);
                map.put("house_situation", strHousingSituation);
                map.put("company_address", strWorkAddress);
                map.put("marriage", strMarriageStatus);
                map.put("qq", strQq);
                map.put("weChat", strWeixin);
                map.put("company_ningbo", "宁波".equals(strNingboStatus) ? "1" : "2");
                ApiRequest.request(MyApplication.getInstance().apiService.userInfo(map), new Subscriber<Result>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showMessage(getString(R.string.str_http_network_error));
                    }

                    @Override
                    public void onNext(Result result) {
                        if (result.isSuccess()) {
                            finish();
                        }
                        ToastUtils.showMessage(result.getMsg());
                    }
                });


                break;

            case R.id.et_marriage_status:
                if (SoftInputUtil.isOpen(mContext)) {
                    SoftInputUtil.closeSoftInput(mContext, etMarriageStatus);
                }
                marriageOptions.show();
                break;

            case R.id.tv_ningbo_status:
                if (SoftInputUtil.isOpen(mContext)) {
                    SoftInputUtil.closeSoftInput(mContext, tvNingboStatus);
                }
                ningBoOptions.show();
                break;

            case R.id.tv_other_optional:
                //其他信息 跳转
                startActivity(new Intent(mActivity, OtherOptionalActivity.class));
                break;
        }
    }

    @Override
    protected boolean switchToolbar() {
        return false;
    }

    @Override
    protected void loadData() {
        UserInfoRequest.requestUserInfo(new CallBack() {
            @Override
            public void onNext(BaseBean baseBean) {
                UserInfoBean.UsersBean userInfoBean = ((UserInfoBean) baseBean).getUsers().get(0);
                etPhone.setText(userInfoBean.getOtherPhone());

                etAddressDetail.setText(userInfoBean.getArea());

                etWorkName.setText(userInfoBean.getCompany());
                etWorkAddressPhone.setText(userInfoBean.getCompanyPhone());

                etWorkAddress.setText(userInfoBean.getCompanyAddress());
                etHousingSituation.setText(userInfoBean.getHouseSituation());
                etMarriageStatus.setText(userInfoBean.getMarriage());

                afAddress.setAddress(userInfoBean.getProvince(), userInfoBean.getCity(),
                        userInfoBean.getCounty());
            }

            @Override
            public void onError() {

            }
        });

        topbar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {

            }
        });
        if (null == marriageList) {
            marriageList = new ArrayList<>();
            marriageList.add("已婚已育");
            marriageList.add("已婚未育");
            marriageList.add("未婚");
            marriageList.add("离异");
            marriageOptions = new OptionsPickerView(mContext);
            marriageOptions.setCancelable(true);
            marriageOptions.setPicker(marriageList);
            marriageOptions.setCyclic(false);
            marriageOptions.setSelectOptions(0);
            marriageOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int option2, int options3) {
                    etMarriageStatus.setText(marriageList.get(options1));
                }
            });
        }
        if (null == ningBoOptions) {
            ningBoList = new ArrayList<>();
            ningBoList.add("宁波");
            ningBoList.add("非宁波");
            ningBoOptions = new OptionsPickerView(mContext);
            ningBoOptions.setCancelable(true);
            ningBoOptions.setPicker(ningBoList);
            ningBoOptions.setCyclic(false);
            ningBoOptions.setSelectOptions(0);
            ningBoOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int option2, int options3) {
                    tvNingboStatus.setText(ningBoList.get(options1));
                }
            });
        }
    }

    ArrayList<String> marriageList, ningBoList;
    OptionsPickerView marriageOptions, ningBoOptions;

}
