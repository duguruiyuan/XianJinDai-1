package com.daiqile.xianjindai.http;

import com.daiqile.xianjindai.Constants;
import com.daiqile.xianjindai.Fragment.bean.AllBorrowBean;
import com.daiqile.xianjindai.Fragment.bean.UserInfoBean;
import com.daiqile.xianjindai.MyApplication;
import com.daiqile.xianjindai.Result;
import com.daiqile.xianjindai.activity.bean.BankInfoList;
import com.daiqile.xianjindai.model.Bank;
import com.daiqile.xianjindai.model.Banner;
import com.daiqile.xianjindai.model.City;
import com.daiqile.xianjindai.model.Country;
import com.daiqile.xianjindai.model.ForgetPassword;
import com.daiqile.xianjindai.model.Province;
import com.daiqile.xianjindai.model.ProvinceCityArea;
import com.daiqile.xianjindai.model.User;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by G150T on 2017/6/21.
 */

public interface ApiService {

    //获取省市区
    @POST("xjd/front/user/provinceCityArea")
    Observable<ProvinceCityArea> getProvinceList();

    //获取市列表
    @FormUrlEncoded
    @POST("xjd/front/user/provinceCityArea")
    Observable<ProvinceCityArea> getCityList(@Field("id") String provinceId);

    //获取区列表
    @FormUrlEncoded
    @POST("xjd/front/user/provinceCityArea")
    Observable<ProvinceCityArea> getCountryList(@Field("id") String cityId);

    //获取轮播图
    @POST("xjd/front/app/pptList")
    Observable<Banner> getBanner();

    //登录
    @POST("xjd/front/user/login")
    Observable<User> login(@FieldMap Map<String, String> map);

    //注册
    @POST("xjd/front/user/regist")
    Observable<ResponseBody> regist(@FieldMap Map<String, String> map);

    //找回密码
    @POST("xjd/front/user/forgetLoginCode")
    Observable<ForgetPassword> forgetpassword(@FieldMap Map<String, String> map);

    //所有银行卡
    @POST("xjd/front/user/bankInfo")
    Observable<Bank> getBankList();

    //添加银行卡
    @POST("xjd/front/user/collectionMoneyInfo")
    @FormUrlEncoded
    Observable<Result> addBank(@FieldMap Map<String, String> map);

    //银行卡列表
    @POST("xjd/front/user/bankList")
    @FormUrlEncoded
    Observable<BankInfoList> getBankLists(@FieldMap Map<String, String> map);


    //紧急联系人
    @POST("xjd/front/user/updateRelative")
    @FormUrlEncoded
    Observable<Result> updateRelative(@FieldMap Map<String, String> map);


    //借款记录 全部
    @POST("xjd/front/loan/findUserLoan")
    @FormUrlEncoded
    Observable<AllBorrowBean> requestAllBorrow(@Field("id") String userId);


    //个人信息列表
    @POST("xjd/front/user/myInfo")
    @FormUrlEncoded
    Observable<UserInfoBean> requestUserMyinfo(@Field("userId") String userId,
                                               @Field("phone") String phone,
                                               @Field("loginPassword") String loginPassword);


}
