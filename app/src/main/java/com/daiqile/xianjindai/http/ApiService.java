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

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
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
    @POST("xjd//front/user/bankList")
    @FormUrlEncoded
    Observable<BankInfoList> getBankLists(@FieldMap Map<String, String> map);


    //紧急联系人
    @POST("xjd/front/user/updateRelative")
    @FormUrlEncoded
    Observable<Result> updateRelative(@FieldMap Map<String, String> map);


    //借款记录 全部
    @POST("xjd/front/loan/findUserLoan")//userId
    @FormUrlEncoded
    Observable<AllBorrowBean> requestAllBorrow(@Field("userId") String userId);//id


    //个人信息列表
    @POST("xjd/front/user/myInfo")
    @FormUrlEncoded
    Observable<UserInfoBean> requestUserMyinfo(@Field("userId") String userId,
                                               @Field("phone") String phone,
                                               @Field("loginPassword") String loginPassword);

    //人脸识别
    @POST("xjd/front/user/realApproveForApp")
    @FormUrlEncoded
    Observable<Result> realApproveForApp(@Field("userId") String userId,
                                         @Field("id_name") String id_name,
                                         @Field("id_no") String id_no,
                                         @Field("age") String age,
                                         @Field("date_birthday") String date_birthday,
                                         @Field("addr_card") String addr_card,
                                         @Field("startCard") String startCard);

    //图片上传
    @Multipart
    @POST("xjd/front/loan/loanUploadImg")
    Observable<Result> loanUploadImg(@Query("id") String userId,
                                     @Part() List<MultipartBody.Part> parts);




}
