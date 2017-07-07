package com.daiqile.xianjindai.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.daiqile.xianjindai.MyApplication;
import com.daiqile.xianjindai.R;
import com.daiqile.xianjindai.base.BaseActivity;
import com.daiqile.xianjindai.model.Province;
import com.daiqile.xianjindai.model.ProvinceCityArea;
import com.daiqile.xianjindai.utils.SoftInputUtil;
import com.daiqile.xianjindai.view.TopBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 个人信息页面
 */
public class PersonalDetailsActivity extends BaseActivity {

    @BindView(R.id.topbar)//topbar
            TopBar topbar;
    @BindView(R.id.et_phone)//手机号码
            EditText etPhone;
    @BindView(R.id.et_province)//省
            TextView etProvince;
    @BindView(R.id.et_city)//市
            TextView etCity;
    @BindView(R.id.et_country)//区
            TextView etCountry;
    @BindView(R.id.et_address_detail)//详细地址
            EditText etAddressDetail;
    @BindView(R.id.et_work_address)//工作单位
            EditText etWorkAddress;
    @BindView(R.id.btn_bind_card)//提交按钮
            Button btnBindCard;
    @BindView(R.id.et_work_address_phone)//工作单位号码
            EditText etWorkAddressPhone;
    private Activity mActivity;
    private MyApplication application;

    OptionsPickerView provinceOptions;//选择省
    OptionsPickerView cityOptions;//选择市
    OptionsPickerView countryOptions;//选择县

    private ArrayList<ProvinceCityArea.ProvinceListBean> provinceList = new ArrayList<>();//省份列表集合
    private ArrayList<String> provinceName = new ArrayList<>();//省份名称集合
    private ArrayList<String> provinceId = new ArrayList<>();//省份id集合

    private ArrayList<ProvinceCityArea.CityListBean> cityList = new ArrayList<>();//城市列表集合
    private ArrayList<String> cityName = new ArrayList<>();//城市名称集合
    private ArrayList<String> cityNIDs = new ArrayList<>();//城市id集合

    private ArrayList<ProvinceCityArea.AreaListBean> countryList = new ArrayList<>();//区域列表集合
    private ArrayList<String> countryName = new ArrayList<>();//区域名称集合
    private ArrayList<String> countryNIDs = new ArrayList<>();//区域id集合

    private String provinceNID = "";//选中省份id
    private String cityNID = "";//选中城市id
    private String countryNID = "";//选中区域id

    @Override
    public void init() {
        mActivity = PersonalDetailsActivity.this;
        application = (MyApplication) getApplication();
        provinceOptions = new OptionsPickerView(mActivity);
        cityOptions = new OptionsPickerView(mActivity);
        countryOptions = new OptionsPickerView(mActivity);

        etCity.setVisibility(View.INVISIBLE);
        etCountry.setVisibility(View.INVISIBLE);
        getProvince();
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

    /**
     * 选择省份
     */
    private void getProvince() {
        application.apiService.getProvinceList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ProvinceCityArea>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ProvinceCityArea province) {
                        provinceName.clear();
                        provinceId.clear();
                        provinceList.clear();
                        provinceList.addAll(province.getProvinceList());
                        for (int i = 0; i < provinceList.size(); i++) {
                            provinceName.add(provinceList.get(i).getName());
                            provinceId.add(provinceList.get(i).getId() + "");
                        }
                        provinceOptions.setPicker(provinceName);
                        provinceOptions.setTitle("选择省");
                        provinceOptions.setCyclic(false);
                        provinceOptions.setSelectOptions(0);
                        provinceOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
                            @Override
                            public void onOptionsSelect(int options1, int option2, int options3) {
                                etCity.setVisibility(View.VISIBLE);
                                etProvince.setText(provinceName.get(options1));
                                provinceNID = provinceId.get(options1);
                                getCity(provinceNID);
                            }
                        });
                    }
                });
    }

    /**
     * 选择城市
     *
     * @param id
     */
    private void getCity(final String id) {
        application.apiService.getCityList(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ProvinceCityArea>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ProvinceCityArea city) {
                        cityName.clear();
                        cityNIDs.clear();
                        cityList.clear();
                        cityList.addAll(city.getCityList());
                        for (int i = 0; i < cityList.size(); i++) {
                            cityName.add(cityList.get(i).getName());
                            cityNIDs.add(cityList.get(i).getId() + "");
                        }
                        cityOptions.setPicker(cityName);
                        cityOptions.setTitle("选择市");
                        cityOptions.setCyclic(false);
                        cityOptions.setSelectOptions(0);
                        etCity.setText(cityName.get(0));
                        cityNID = cityNIDs.get(0);
                        cityOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
                            @Override
                            public void onOptionsSelect(int options1, int option2, int options3) {
                                etCountry.setVisibility(View.VISIBLE);
                                etCity.setText(cityName.get(options1));
                                cityNID = cityNIDs.get(options1);
                                getCounty(cityNID);
                            }
                        });
                    }


                });
    }

    /**
     * 选择区
     *
     * @param id
     */
    private void getCounty(String id) {

        application.apiService.getCountryList(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ProvinceCityArea>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ProvinceCityArea country) {
                        countryName.clear();
                        countryNIDs.clear();
                        countryList.clear();
                        countryList.addAll(country.getAreaList());
                        for (int i = 0; i < countryList.size(); i++) {
                            countryName.add(countryList.get(i).getName());
                            countryNIDs.add(countryList.get(i).getId() + "");
                        }
                        etCountry.setText(countryName.get(0));
                        countryNID = countryNIDs.get(0);
                        countryOptions.setPicker(countryName);
                        countryOptions.setTitle("选择区");
                        countryOptions.setCyclic(false);
                        countryOptions.setSelectOptions(0);
                        countryOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
                            @Override
                            public void onOptionsSelect(int options1, int option2, int options3) {
                                etCountry.setText(countryName.get(options1));
                                countryNID = countryNIDs.get(options1);
                            }
                        });
                    }
                });
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_personal_details;
    }

    @Override
    public Activity bindActivity() {
        return this;
    }


    @OnClick({R.id.et_province, R.id.et_city, R.id.et_country, R.id.btn_bind_card})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_province:
                if (SoftInputUtil.isOpen(mActivity)) {
                    SoftInputUtil.closeSoftInput(mActivity, etProvince);
                }
                provinceOptions.show();
                break;
            case R.id.et_city:
                if (SoftInputUtil.isOpen(mActivity)) {
                    SoftInputUtil.closeSoftInput(mActivity, etCity);
                }
                cityOptions.show();
                break;
            case R.id.et_country:
                if (SoftInputUtil.isOpen(mActivity)) {
                    SoftInputUtil.closeSoftInput(mActivity, etCountry);
                }
                countryOptions.show();
                break;
            case R.id.btn_bind_card:
                break;
        }
    }
}
