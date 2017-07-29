package com.daiqile.xianjindai.view;

import android.content.Context;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.daiqile.xianjindai.Constants;
import com.daiqile.xianjindai.MyApplication;
import com.daiqile.xianjindai.R;
import com.daiqile.xianjindai.model.ProvinceCityArea;

import java.io.File;
import java.util.ArrayList;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import suangrenduobao.daiqile.com.mvlib.utils.FileUtils;

/**
 * Created by zkw on 2017/7/12.
 */

public class AddressFrameLayout extends FrameLayout implements View.OnClickListener {

    private ArrayList<ProvinceCityArea.ProvinceListBean> provincelistbean;
    private ArrayList<ProvinceCityArea.AreaListBean> areaListBeen;
    private ArrayList<ProvinceCityArea.CityListBean> cityListBeen;

    private OptionsPickerView provinceOptions;

    private ArrayList<String> provinceNames;//省份名称集合
    private ArrayList<String> provinceIds;//省份id集合

    private ArrayList<String> cityNames;//城市名称集合
    private ArrayList<String> cityIds;//城市id集合

    private ArrayList<String> countryNames;//区域名称集合
    private ArrayList<String> countryIds;//区域id集合

    private String provinceId, cityId, countryId;

    private TextView provinceText, cityText, countryText;

    public AddressFrameLayout(Context context) {
        this(context, null);
    }

    public AddressFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }


    public AddressFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        View view = LayoutInflater.from(context).inflate(R.layout.address_fragmelayout, null);
        addView(view);

        provinceText = (TextView) view.findViewById(R.id.tv_select_province);
        provinceText.setOnClickListener(this);

        cityText = (TextView) view.findViewById(R.id.tv_select_city);
        cityText.setOnClickListener(this);

        countryText = (TextView) view.findViewById(R.id.tv_select_country);
        countryText.setOnClickListener(this);

        cityText.setVisibility(INVISIBLE);
        countryText.setVisibility(INVISIBLE);

        final File provnceFile = new File(Environment.getExternalStorageDirectory(), Constants.PROVINCELIST);
        final File cityFile = new File(Environment.getExternalStorageDirectory(), Constants.CITYLIST);
        final File arealFile = new File(Environment.getExternalStorageDirectory(), Constants.AREALIST);

        if (!provnceFile.exists() || !cityFile.exists() || !arealFile.exists()) {
            MyApplication.getInstance().apiService.getProvinceList()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<ProvinceCityArea>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.d("AddressFrameLayout", "e:" + e);
                        }

                        @Override
                        public void onNext(ProvinceCityArea province) {
                            FileUtils.writeObj(cityFile, province.getCityList());
                            FileUtils.writeObj(arealFile, province.getAreaList());
                            FileUtils.writeObj(provnceFile, province.getProvinceList());

                            provincelistbean = (ArrayList<ProvinceCityArea.ProvinceListBean>) province.getProvinceList();
                            areaListBeen = (ArrayList<ProvinceCityArea.AreaListBean>) province.getAreaList();
                            cityListBeen = (ArrayList<ProvinceCityArea.CityListBean>) province.getCityList();

                            //省
                            provinceNames = new ArrayList<>();
                            provinceIds = new ArrayList<>();
                            for (ProvinceCityArea.ProvinceListBean provinceListBean : provincelistbean) {
                                provinceNames.add(provinceListBean.getName());
                                provinceIds.add(provinceListBean.getId() + "");
                            }
                            provinceId = provinceIds.get(0);

                            //市
                            cityNames = new ArrayList<>();
                            cityIds = new ArrayList<>();

                            //区
                            countryNames = new ArrayList<>();
                            countryIds = new ArrayList<>();

                            getCity();
                            cityId = cityIds.get(0);

                            getCountry();
                            countryId = countryIds.get(0);
                        }
                    });
        } else {
            try {
                if (null == provincelistbean) {
                    provincelistbean = (ArrayList<ProvinceCityArea.ProvinceListBean>) FileUtils.readObj(provnceFile);
                }
                if (null == areaListBeen) {
                    areaListBeen = (ArrayList<ProvinceCityArea.AreaListBean>) FileUtils.readObj(arealFile);
                }
                if (null == cityListBeen) {
                    cityListBeen = (ArrayList<ProvinceCityArea.CityListBean>) FileUtils.readObj(cityFile);
                }
                //省
                provinceNames = new ArrayList<>();
                provinceIds = new ArrayList<>();
                for (ProvinceCityArea.ProvinceListBean provinceListBean : provincelistbean) {
                    provinceNames.add(provinceListBean.getName());
                    provinceIds.add(provinceListBean.getId() + "");
                }
                provinceId = provinceIds.get(0);

                //市
                cityNames = new ArrayList<>();
                cityIds = new ArrayList<>();

                //区
                countryNames = new ArrayList<>();
                countryIds = new ArrayList<>();

                getCity();
                cityId = cityIds.get(0);

                getCountry();
                countryId = countryIds.get(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        provinceOptions = new OptionsPickerView(context);
        provinceOptions.setCancelable(true);
    }

    private void showOptionsPickerView(final int index, final ArrayList<String> provinceNames) {
        if (provinceNames.size() > 0) {
            provinceOptions.setPicker(provinceNames);
            provinceOptions.setCyclic(false);
            provinceOptions.setSelectOptions(0);
            provinceOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int option2, int options3) {
                    switch (index) {
                        case 0:
                            cityText.setVisibility(VISIBLE);
                            countryText.setVisibility(VISIBLE);

                            provinceId = provinceIds.get(options1);
                            getCity();//市
                            if (cityIds.size() > 0) {
                                cityId = cityIds.get(0);
                                getCountry();//区
                            } else {
                                cityId = "-1";
                            }
                            provinceText.setText(provinceNames.get(options1));
                            cityText.setText(cityNames.size() > 0 ? cityNames.get(0) : "");
                            countryText.setText(countryNames.size() > 0 ? countryNames.get(0) : "");
                            countryId = countryIds.size() > 0 ? countryIds.get(0) : "-1";
                            break;
                        case 1:
                            if (cityIds.size() > 0) {
                                cityId = cityIds.get(options1);
                                getCountry();//区
                            }
                            cityText.setText(cityNames.size() > 0 ? cityNames.get(options1) : "");
                            countryText.setText(countryNames.size() > 0 ? countryNames.get(0) : "");
                            countryId = countryIds.size() > 0 ? countryIds.get(0) : "-1";
                            break;

                        case 2:
                            countryText.setText(countryNames.size() > 0 ? countryNames.get(options1) : "");
                            countryId = countryIds.size() > 0 ? countryIds.get(options1) : "-1";
                            break;
                    }
                }
            });
            Log.d("AddressFrameLayout", provinceId + "-" + cityId + "-" + countryId);
            provinceOptions.show();
        }
    }

    public String getCityId() {//市 -1  不能选择
        return cityId;
    }

    public String getProvinceId() {//省
        return provinceId;
    }

    public String getCountryId() {//区  -1  不能选择
        return countryId;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_select_province:
                showOptionsPickerView(0, provinceNames);
                break;
            case R.id.tv_select_city:
                showOptionsPickerView(1, cityNames);
                break;
            case R.id.tv_select_country:
                showOptionsPickerView(2, countryNames);
                break;
        }
    }

    private void getCity() {
        cityNames.clear();
        cityIds.clear();

        countryNames.clear();
        countryIds.clear();

        for (ProvinceCityArea.CityListBean cityListBean : cityListBeen) {
            if (provinceId.equals(cityListBean.getProvinceId() + "")) {
                cityNames.add(cityListBean.getName());
                cityIds.add(cityListBean.getId() + "");
            }
        }
    }

    private void getCountry() {
        countryNames.clear();
        countryIds.clear();
        for (ProvinceCityArea.AreaListBean areaListBean : areaListBeen) {
            if (cityId.equals(areaListBean.getCityId() + "")) {
                countryNames.add(areaListBean.getName());
                countryIds.add(areaListBean.getId() + "");
            }
        }
    }

    public void setAddress(String province, String city, String area) {
        cityText.setVisibility(VISIBLE);
        countryText.setVisibility(VISIBLE);


        provinceText.setText(province);
        cityText.setText(city);
        countryText.setText(area);
        //省
        for (ProvinceCityArea.ProvinceListBean provinceListBean : provincelistbean) {
            if (province.equals(provinceListBean.getName())) {
                provinceId = provinceListBean.getId() + "";
            }
        }

        //市
        for (ProvinceCityArea.CityListBean cityListBean : cityListBeen) {
            if (city.equals(cityListBean.getName())) {
                cityId = cityListBean.getId() + "";
            }
        }
        cityNames.clear();
        cityIds.clear();

        countryNames.clear();
        countryIds.clear();
        for (ProvinceCityArea.CityListBean cityListBean : cityListBeen) {
            if (provinceId.equals(cityListBean.getProvinceId() + "")) {
                cityNames.add(cityListBean.getName());
                cityIds.add(cityListBean.getId() + "");
            }
        }

        //区
        for (ProvinceCityArea.AreaListBean areaListBean : areaListBeen) {
            if (area.equals(areaListBean.getName() + "")) {
                countryId = areaListBean.getId() + "";
            }
        }
        countryNames.clear();
        countryIds.clear();
        for (ProvinceCityArea.AreaListBean areaListBean : areaListBeen) {
            if (cityId.equals(areaListBean.getCityId() + "")) {
                countryNames.add(areaListBean.getName());
                countryIds.add(areaListBean.getId() + "");
            }
        }

    }


}