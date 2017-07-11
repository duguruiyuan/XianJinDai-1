package com.daiqile.xianjindai.activity;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.daiqile.xianjindai.Constants;
import com.daiqile.xianjindai.MyApplication;
import com.daiqile.xianjindai.R;
import com.daiqile.xianjindai.Result;
import com.daiqile.xianjindai.model.Bank;
import com.daiqile.xianjindai.model.ProvinceCityArea;

import com.daiqile.xianjindai.utils.SoftInputUtil;
import com.daiqile.xianjindai.utils.ToastUtil;
import com.daiqile.xianjindai.view.TopBar;
import com.hwangjr.rxbus.RxBus;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import suangrenduobao.daiqile.com.mvlib.mv.BaseActivity;
import suangrenduobao.daiqile.com.mvlib.utils.FileUtils;

import suangrenduobao.daiqile.com.mvlib.utils.RegexValidateUtil;
import suangrenduobao.daiqile.com.mvlib.utils.ToastUtils;


//绑定银行卡页面
public class BindCardActivity extends BaseActivity {
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


    OptionsPickerView bankOptions;
    OptionsPickerView provinceOptions;
    OptionsPickerView cityOptions;
    OptionsPickerView countryOptions;

    private ArrayList<Integer> bankIdList = new ArrayList<>();//银行id集合
    private ArrayList<String> bankNameList = new ArrayList<>();//银行英文名称集合
    private ArrayList<String> bankValueList = new ArrayList<>();//银行中文名称集合
    private ArrayList<Bank.BankXFsBean> bankCardList;//银行卡列表集合

    //    private ArrayList<ProvinceCityArea.ProvinceListBean> provinceList = new ArrayList<>();//省份列表集合
    private ArrayList<String> provinceName = new ArrayList<>();//省份名称集合
    private ArrayList<String> provinceId = new ArrayList<>();//省份id集合

    //    private ArrayList<ProvinceCityArea.CityListBean> cityList = new ArrayList<>();//城市列表集合
    private ArrayList<String> cityName = new ArrayList<>();//城市名称集合
    private ArrayList<String> cityNIDs = new ArrayList<>();//城市id集合

    //    private ArrayList<ProvinceCityArea.AreaListBean> countryList = new ArrayList<>();//区域列表集合
    private ArrayList<String> countryName = new ArrayList<>();//区域名称集合
    private ArrayList<String> countryNIDs = new ArrayList<>();//区域id集合

    private String provinceNID = "";//选中省份id
    private String cityNID = "";//选中城市id
    private String countryNID = "";//选中区域id
    private String bankId = "";//选中银行卡id
    private String bankName = "";//选中银行卡英文名称

    @Override
    protected void initConfig() {
        super.initConfig();
        provinceOptions = new OptionsPickerView(mContext);
        cityOptions = new OptionsPickerView(mContext);
        countryOptions = new OptionsPickerView(mContext);
        bankOptions = new OptionsPickerView(mContext);

        testCitys();
        getBankList();

        etCity.setVisibility(View.INVISIBLE);
        etCountry.setVisibility(View.INVISIBLE);

        topBar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {

            }
        });
        bankOptions.setCancelable(true);
        provinceOptions.setCancelable(true);
        cityOptions.setCancelable(true);
        countryOptions.setCancelable(true);
    }
//    @Override
//    public void init() {
//        mActivity = BindCardActivity.this;
//        application = (MyApplication) getApplication();
//        provinceOptions = new OptionsPickerView(mActivity);
//        cityOptions = new OptionsPickerView(mActivity);
//        countryOptions = new OptionsPickerView(mActivity);
//        bankOptions = new OptionsPickerView(mActivity);
//
//
//        testCitys();
//
//
//        etCity.setVisibility(View.INVISIBLE);
//        etCountry.setVisibility(View.INVISIBLE);
//
//        getProvince();
//
//        getBankList();
//
//        topBar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
//            @Override
//            public void leftClick() {
//                finish();
//            }
//
//            @Override
//            public void rightClick() {
//
//            }
//        });
//    }


    private static final String TAG = "BindCardActivity";

    private void testCitys() {
        File cityFile = new File(Environment.getExternalStorageDirectory(), Constants.CITYLIST);
        if (!cityFile.exists()) {
            MyApplication.getInstance().apiService.getProvinceList()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<ProvinceCityArea>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            ToastUtil.showToast(BindCardActivity.this, e.toString());
                        }

                        @Override
                        public void onNext(ProvinceCityArea province) {
                            File cityFile = new File(Environment.getExternalStorageDirectory(), Constants.CITYLIST);
                            File arealFile = new File(Environment.getExternalStorageDirectory(), Constants.AREALIST);
                            File provnceFile = new File(Environment.getExternalStorageDirectory(), Constants.PROVINCELIST);

                            if (!cityFile.exists()) {
                                FileUtils.writeObj(cityFile, province.getCityList());
                            }
                            if (!arealFile.exists()) {
                                FileUtils.writeObj(arealFile, province.getAreaList());
                            }
                            if (!provnceFile.exists()) {
                                FileUtils.writeObj(provnceFile, province.getProvinceList());
                            }
                            getProvince();
                        }
                    });
        } else {
            getProvince();
        }

    }

    /**
     * 获取银行列表
     */
    private void getBankList() {
        File bankFile = new File(Environment.getExternalStorageDirectory(), Constants.BANKNAME);
        if (null == bankCardList) {
            if (!bankFile.exists()) {
                MyApplication.getInstance().apiService.getBankList()
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
                                File bankFile = new File(Environment.getExternalStorageDirectory(), Constants.BANKNAME);
                                if (!bankFile.exists()) {
                                    FileUtils.writeObj(bankFile, bank.getBankXFs());
                                }
                                if (null == bankCardList) {
                                    bankNameList.clear();
                                    bankIdList.clear();
                                    bankCardList = (ArrayList<Bank.BankXFsBean>) FileUtils.readObj(bankFile);
                                    for (int i = 0; i < bankCardList.size(); i++) {
                                        bankIdList.add(bankCardList.get(i).getId());
                                        bankNameList.add(bankCardList.get(i).getBankCode());
                                        bankValueList.add(bankCardList.get(i).getBankName());
                                    }
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

                            }
                        });
            } else {
                if (null == bankCardList) {
                    bankNameList.clear();
                    bankIdList.clear();
                    bankCardList = (ArrayList<Bank.BankXFsBean>) FileUtils.readObj(bankFile);
                    for (int i = 0; i < bankCardList.size(); i++) {
                        bankIdList.add(bankCardList.get(i).getId());
                        bankNameList.add(bankCardList.get(i).getBankCode());
                        bankValueList.add(bankCardList.get(i).getBankName());
                    }
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
            }
        }


    }

    ArrayList<ProvinceCityArea.ProvinceListBean> provincelistbean;
    ArrayList<ProvinceCityArea.AreaListBean> areaListBeen;
    ArrayList<ProvinceCityArea.CityListBean> cityListBeen;

    private void getProvince() {
        if (null == provincelistbean) {
            File provnceFile = new File(Environment.getExternalStorageDirectory(), Constants.PROVINCELIST);
            provincelistbean = (ArrayList<ProvinceCityArea.ProvinceListBean>) FileUtils.readObj(provnceFile);
        }
        if (provincelistbean.size() > 0) {
            for (ProvinceCityArea.ProvinceListBean provinceListBean : provincelistbean) {
                provinceName.add(provinceListBean.getName());
                provinceId.add(provinceListBean.getId() + "");
            }
            provinceOptions.setPicker(provinceName);
            provinceOptions.setTitle("选择省");
            provinceOptions.setCyclic(false);
            provinceOptions.setSelectOptions(0);

            provinceOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int option2, int options3) {
                    etCity.setVisibility(View.VISIBLE);
//                    etCountry.setVisibility(View.INVISIBLE);
                    etCountry.setText("");
                    etCity.setText("");
                    etProvince.setText(provinceName.get(options1));
                    provinceNID = provinceId.get(options1);
                    getCity(provinceNID);
                }
            });
        }
    }

    /*
      * 选择城市
      * @param id
      */

    private void getCity(String id) {
        if (null == cityListBeen) {
            File cityFile = new File(Environment.getExternalStorageDirectory(), Constants.CITYLIST);
            cityListBeen = (ArrayList<ProvinceCityArea.CityListBean>) FileUtils.readObj(cityFile);
        }
        if (cityListBeen.size() > 0) {
            cityName = new ArrayList<>();
            cityNIDs = new ArrayList<>();
            for (ProvinceCityArea.CityListBean cityListBean : cityListBeen) {
                if (id.equals(cityListBean.getProvinceId() + "")) {
                    cityName.add(cityListBean.getName());
                    cityNIDs.add(cityListBean.getId() + "");
                }
            }
            if (cityName.size() > 0) {
                Log.d(TAG, "a");
                cityOptions.setPicker(cityName);
                cityOptions.setTitle("选择市");
                cityOptions.setCyclic(false);
                cityOptions.setSelectOptions(0);
                etCity.setText(cityName.get(0));
                cityNID = cityNIDs.get(0);
                countryNID = cityNID;
//                countryNID = cityNIDs.get(0);
                getCounty(cityNID);
                cityOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3) {
                        etCountry.setVisibility(View.VISIBLE);
                        etCity.setText(cityName.get(options1));
                        cityNID = cityNIDs.get(options1);
                        getCounty(cityNID);
                    }
                });
            } else {
                etCountry.setText("");
//                etCountry.setVisibility(View.INVISIBLE);
//                etCity.setVisibility(View.INVISIBLE);
//                etCity.setText(cityName.get(options1));
            }
        }
    }

    /*
     * 选择区
     * @param id
     */
    private void getCounty(String id) {
        if (null == areaListBeen) {
            File arealFile = new File(Environment.getExternalStorageDirectory(), Constants.AREALIST);
            areaListBeen = (ArrayList<ProvinceCityArea.AreaListBean>) FileUtils.readObj(arealFile);
        }
        Log.d(TAG, id);
        if (areaListBeen.size() > 0) {
            countryName = new ArrayList<>();
            countryNIDs = new ArrayList<>();
            for (ProvinceCityArea.AreaListBean areaListBean : areaListBeen) {
                if (id.equals(areaListBean.getCityId() + "")) {
                    Log.d(TAG, "a");
                    countryName.add(areaListBean.getName());
                    countryNIDs.add(areaListBean.getId() + "");
                }
            }
//            if (countryList.size() > 0) {
            if (countryName.size() > 0) {
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
    }

    @OnClick({R.id.et_city, R.id.et_country, R.id.et_province, R.id.tv_choose_bank})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_choose_bank:
                if (SoftInputUtil.isOpen(mContext)) {
                    SoftInputUtil.closeSoftInput(mContext, tvChooseBank);
                }
                bankOptions.show();
                break;
            case R.id.et_province:
                if (SoftInputUtil.isOpen(mContext)) {
                    SoftInputUtil.closeSoftInput(mContext, etProvince);
                }
                provinceOptions.show();
                break;
            case R.id.et_city:
                if (SoftInputUtil.isOpen(mContext)) {
                    SoftInputUtil.closeSoftInput(mContext, etCity);
                }
                cityOptions.show();
                break;
            case R.id.et_country:
                if (SoftInputUtil.isOpen(mContext)) {
                    SoftInputUtil.closeSoftInput(mContext, etCountry);
                }
                countryOptions.show();
                break;
        }
    }

    @OnClick(R.id.btn_bind_card)
    public void click(View view) {
        String mCardNumber = cardNumber.getText().toString().trim();
        String mPhone = etPhone.getText().toString().trim();
        if (TextUtils.isEmpty(mCardNumber)) {
            ToastUtils.showMessage("请输入银行卡号");
            return;
        }
        if ("".equals(bankId)) {
            ToastUtils.showMessage("请银行类型");
            return;
        }
        if ("".equals(provinceNID)) {
            ToastUtils.showMessage("请选择银行开户地");
            return;
        }
        if (TextUtils.isEmpty(mPhone)) {
            ToastUtils.showMessage("请输入手机号码");
            return;
        }
        if (!RegexValidateUtil.checkCellphone(mPhone)) {
            ToastUtils.showMessage("请输入正确的手机号码");
            return;
        }
        Log.d(TAG, provinceNID + cityNID + countryNID);
        Log.d(TAG, bankId + bankName);

        Map<String, String> map = new HashMap<>();
        map.put("userId", MyApplication.getInstance().getUid());
        map.put("bankNo", mCardNumber.replace(" ", ""));
        map.put("bankName", bankName);
        map.put("phone", mPhone);
        map.put("province_id", provinceNID);
        map.put("city_id", cityNID);
        map.put("county_id", countryNID);

        MyApplication.getInstance().apiService.addBank(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result>() {
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
                            RxBus.get().post(Constants.REFRESH_BANK, "");
                            finish();
                        }
                    }
                });
    }


    @Override
    protected boolean switchToolbar() {
        return false;
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_bind_card;
    }

    @Override
    protected void loadData() {

    }
}
