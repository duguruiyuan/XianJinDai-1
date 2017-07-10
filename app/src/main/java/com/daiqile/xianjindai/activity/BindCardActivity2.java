package com.daiqile.xianjindai.activity;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.daiqile.xianjindai.Constants;
import com.daiqile.xianjindai.MyApplication;
import com.daiqile.xianjindai.R;
import com.daiqile.xianjindai.base.BaseActivity;
import com.daiqile.xianjindai.model.Bank;
import com.daiqile.xianjindai.model.ProvinceCityArea;
import com.daiqile.xianjindai.utils.SPUtils;
import com.daiqile.xianjindai.utils.SoftInputUtil;
import com.daiqile.xianjindai.utils.ToastUtil;
import com.daiqile.xianjindai.view.TopBar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


//绑定银行卡页面
public class BindCardActivity2 extends BaseActivity {
    @BindView(R.id.topbar)
    TopBar topBar;
    @BindView(R.id.card_number)//银行卡号
            EditText cardNumber;
    @BindView(R.id.tv_choose_bank)//选择银行卡
            TextView tvChooseBank;
    @BindView(R.id.et_province)//选择省
            TextView etProvince;
    @BindView(R.id.et_city)//选择市
            TextView etCity;
    @BindView(R.id.et_country)//选择区
            TextView etCountry;
    @BindView(R.id.et_phone)//手机号
            EditText etPhone;

    private Activity mActivity;
    private MyApplication application;

    OptionsPickerView bankOptions;
    OptionsPickerView provinceOptions;
    OptionsPickerView cityOptions;
    OptionsPickerView countryOptions;

    private ArrayList<Integer> bankIdList = new ArrayList<>();//银行id集合
    private ArrayList<String> bankNameList = new ArrayList<>();//银行英文名称集合
    private ArrayList<String> bankValueList = new ArrayList<>();//银行中文名称集合
    private ArrayList<Bank.BankXFsBean> bankCardList = new ArrayList<>();//银行卡列表集合

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
    private String bankId = "";//选中银行卡id
    private String bankName = "";//选中银行卡英文名称

    @Override
    public void init() {
        mActivity = BindCardActivity2.this;
        application = (MyApplication) getApplication();
        provinceOptions = new OptionsPickerView(mActivity);
        cityOptions = new OptionsPickerView(mActivity);
        countryOptions = new OptionsPickerView(mActivity);
        bankOptions = new OptionsPickerView(mActivity);


        testCitys();


        etCity.setVisibility(View.INVISIBLE);
        etCountry.setVisibility(View.INVISIBLE);

        getProvince();

        getBankList();

        topBar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {

            }
        });
    }


    private static final String TAG = "BindCardActivity";

    private void testCitys() {
        application.apiService.getProvinceList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ProvinceCityArea>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.showToast(BindCardActivity2.this, e.toString());
                    }

                    @Override
                    public void onNext(ProvinceCityArea province) {
                        List<ProvinceCityArea.ProvinceListBean> provinceList = province.getProvinceList(); //省
                        List<ProvinceCityArea.AreaListBean> areaList = province.getAreaList();  //区
                        List<ProvinceCityArea.CityListBean> cityList = province.getCityList();  //市
                        Gson gson = new Gson();
                        SPUtils.put(application, Constants.PROVINCELIST, gson.toJson(provinceList));
                        SPUtils.put(application, Constants.AREALIST, gson.toJson(areaList));
                        SPUtils.put(application, Constants.CITYLIST, gson.toJson(cityList));
                    }
                });


    }

    /**
     * 获取银行列表
     */
    private void getBankList() {
        application.apiService.getBankList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Bank>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Bank bank) {

                        //Log.e("getBankListSuccess" , bankCard.getTypes().toString()) ;

                        bankCardList.clear();
                        bankNameList.clear();
                        bankIdList.clear();
                        bankCardList.addAll(bank.getBankXFs());
                        for (int i = 0; i < bankCardList.size(); i++) {
                            bankIdList.add(bankCardList.get(i).getId());
                            bankNameList.add(bankCardList.get(i).getBankCode());
                            bankValueList.add(bankCardList.get(i).getBankName());
                        }
                        bankOptions = new OptionsPickerView(mActivity);
                        bankOptions.setPicker(bankValueList);
                        bankOptions.setTitle("选择银行");
                        bankOptions.setCyclic(false);
                        bankOptions.setSelectOptions(0);
                        bankOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
                            @Override
                            public void onOptionsSelect(int options1, int option2, int options3) {
                                bankId = bankIdList.get(options1) + "";
                                bankName = bankNameList.get(options1);
                                tvChooseBank.setText(bankValueList.get(options1));
                            }
                        });
                    }
                });
    }

    /**
     * 选择省
     */
    ArrayList<ProvinceCityArea.ProvinceListBean> provincelistbean;
    ArrayList<ProvinceCityArea.AreaListBean> areaListBeen;
    ArrayList<ProvinceCityArea.CityListBean> cityListBeen;

    private void getProvince() {
        if (null == provincelistbean) {
            String provinceList = SPUtils.get(application, Constants.PROVINCELIST, "").toString();
            if (null != provinceList) {
                Gson gson = new Gson();
                provincelistbean = gson.fromJson(provinceList, new TypeToken<List<ProvinceCityArea.ProvinceListBean>>() {
                }.getType());
//            for (int i = 0; i < provincelistbean.size(); i++) {
//                provinceName.add(provincelistbean.get(i).getName());
//                provinceId.add(provincelistbean.get(i).getId() + "");
//            }
                for (ProvinceCityArea.ProvinceListBean provinceListBean : provincelistbean) {
                    provinceName.add(provinceListBean.getName());
                    provinceId.add(provinceListBean.getId() + "");
                }
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

//        application.apiService.getProvinceList()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<ProvinceCityArea>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(ProvinceCityArea province) {
////                        provinceName.clear();
////                        provinceId.clear();
////                        provinceList.clear();
////                        provinceList.addAll(province.getProvinceList());
////                        for (int i = 0; i < provinceList.size(); i++) {
////                            provinceName.add(provinceList.get(i).getName());
////                            provinceId.add(provinceList.get(i).getId() + "");
////                        }
//                        String provinceList = SPUtils.get(application, "provinceList", null).toString();
//                        if (null != provinceList) {
//                            Gson gson = new Gson();
//                            provinceName = gson.fromJson(provinceList, new TypeToken<List<ProvinceCityArea.ProvinceListBean>>() {
//                            }.getType());
//                        }
//
//                        provinceOptions.setPicker(provinceName);
//                        provinceOptions.setTitle("选择省");
//                        provinceOptions.setCyclic(false);
//                        provinceOptions.setSelectOptions(0);
//                        provinceOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
//                            @Override
//                            public void onOptionsSelect(int options1, int option2, int options3) {
//                                etCity.setVisibility(View.VISIBLE);
//                                etProvince.setText(provinceName.get(options1));
//                                provinceNID = provinceId.get(options1);
//                                getCity(provinceNID);
//                            }
//                        });
//                    }
//                });
    }

    /*
      * 选择城市
      * @param id
      */

    private void getCity(String id) {
        if (null == cityListBeen) {
            String cityList = SPUtils.get(application, Constants.CITYLIST, "").toString();
            if (null != cityList) {
                Gson gson = new Gson();
                cityListBeen = gson.fromJson(cityList, new TypeToken<List<ProvinceCityArea.CityListBean>>() {
                }.getType());
//            for (int i = 0; i < cityList.size(); i++) {
//                cityName.add(cityList.get(i).getName());
//                cityNIDs.add(cityList.get(i).getId() + "");
//            }
            }
        }
        if (null != cityListBeen) {
            cityName = new ArrayList<>();
            cityNIDs = new ArrayList<>();
            for (ProvinceCityArea.CityListBean cityListBean : cityListBeen) {
                if (id.equals(cityListBean.getProvinceId() + "")) {
                    cityName.add(cityListBean.getName());
                    cityNIDs.add(cityListBean.getId() + "");
                }
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


//        application.apiService.getProvinceList()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<ProvinceCityArea>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(ProvinceCityArea province) {
//                        cityName.clear();
//                        cityNIDs.clear();
//                        cityList.clear();
//                        cityList.addAll(province.getCityList());
//                        for (int i = 0; i < cityList.size(); i++) {
//                            cityName.add(cityList.get(i).getName());
//                            cityNIDs.add(cityList.get(i).getId() + "");
//
//                        }
//                        cityOptions.setPicker(cityName);
//                        cityOptions.setTitle("选择市");
//                        cityOptions.setCyclic(false);
//                        cityOptions.setSelectOptions(0);
//                        etCity.setText(cityName.get(0));
//                        cityNID = cityNIDs.get(0);
//                        cityOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
//                            @Override
//                            public void onOptionsSelect(int options1, int option2, int options3) {
//                                etCountry.setVisibility(View.VISIBLE);
//                                etCity.setText(cityName.get(options1));
//                                cityNID = cityNIDs.get(options1);
//                                getCounty(cityNID);
//                            }
//                        });
//                    }
//                });
    }

    /*
     * 选择区
     * @param id
     */
    private void getCounty(String id) {
        if (null == areaListBeen) {
            String areaList = SPUtils.get(application, Constants.AREALIST, "").toString();
            if (null != areaList) {
                Gson gson = new Gson();
                areaListBeen = gson.fromJson(areaList, new TypeToken<List<ProvinceCityArea.AreaListBean>>() {
                }.getType());
            }
        }
        if (null != areaListBeen) {
            countryName = new ArrayList<>();
            countryNIDs = new ArrayList<>();
            for (ProvinceCityArea.AreaListBean areaListBean : areaListBeen) {
                if (id.equals(areaListBean.getCityId())) {
                    countryName.add(areaListBean.getName());
                    countryNIDs.add(areaListBean.getId() + "");
                }
            }
            if (countryList.size() > 0) {
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

        }


//        application.apiService.getProvinceList()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<ProvinceCityArea>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(ProvinceCityArea province) {
//                        countryName.clear();
//                        countryNIDs.clear();
//                        countryList.clear();
//                        countryList.addAll(province.getAreaList());
//                        for (int i = 0; i < countryList.size(); i++) {
//                            countryName.add(countryList.get(i).getName());
//                            countryNIDs.add(countryList.get(i).getId() + "");
//                        }
//                        etCountry.setText(countryName.get(0));
//                        countryNID = countryNIDs.get(0);
//                        countryOptions.setPicker(countryName);
//                        countryOptions.setTitle("选择区");
//                        countryOptions.setCyclic(false);
//                        countryOptions.setSelectOptions(0);
//                        countryOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
//                            @Override
//                            public void onOptionsSelect(int options1, int option2, int options3) {
//                                etCountry.setText(countryName.get(options1));
//                                countryNID = countryNIDs.get(options1);
//                            }
//                        });
//                    }
//                });
    }

    @OnClick({R.id.et_city, R.id.et_country, R.id.et_province, R.id.tv_choose_bank})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_choose_bank:
                if (SoftInputUtil.isOpen(mActivity)) {
                    SoftInputUtil.closeSoftInput(mActivity, tvChooseBank);
                }
                bankOptions.show();
                break;
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
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_bind_card;
    }

    @Override
    public Activity bindActivity() {
        return this;
    }



}
