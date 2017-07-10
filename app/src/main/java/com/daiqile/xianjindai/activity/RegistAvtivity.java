package com.daiqile.xianjindai.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daiqile.xianjindai.Constants;
import com.daiqile.xianjindai.MyApplication;
import com.daiqile.xianjindai.R;
import com.daiqile.xianjindai.base.BaseActivity;
import com.daiqile.xianjindai.model.Regist;
import com.daiqile.xianjindai.utils.PhoneFormatCheckUtils;
import com.daiqile.xianjindai.utils.ToastUtil;
import com.daiqile.xianjindai.view.TimeButton;
import com.daiqile.xianjindai.view.TopBar;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 注册页面
 */
public class RegistAvtivity extends suangrenduobao.daiqile.com.mvlib.mv.BaseActivity {

    @BindView(R.id.topbar)
    TopBar topbar;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tb_get_validate_number)
    TimeButton tbGetValidateNumber;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_password_again)
    EditText etPasswordAgain;
    @BindView(R.id.btn_regist)
    Button btnRegist;
    @BindView(R.id.tv_agreement)
    TextView tvAgreement;
    @BindView(R.id.activity_regist_avtivity)
    LinearLayout activityRegistAvtivity;
    private Activity mActivity;
    private MyApplication application;
    private static String Tag = "RegistAvtivity";

    @Override
    protected boolean switchToolbar() {
        return false;
    }

    @Override
    protected void initConfig() {
        super.initConfig();
        mActivity = RegistAvtivity.this;

        topbar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {

            }
        });
        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tbGetValidateNumber.setMobile(2, s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

//    @Override
//    public void init() {
//        mActivity = RegistAvtivity.this;
//
//        topbar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
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
//        etPhone.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                tbGetValidateNumber.setMobile(2 , s.toString());
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//       /* etPassword.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                tbGetValidateNumber.setNewpassword(s.toString());
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });*/
//    }

    /**
     * 注册方法
     */
    private void regist() {
        String phone = etPhone.getText().toString().trim();
        String code = etCode.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String passwordAgain = etPasswordAgain.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            ToastUtil.showToast(mActivity, "请输入手机号");
            return;
        }
        if (TextUtils.isEmpty(code)) {
            ToastUtil.showToast(mActivity, "请输入验证码");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            ToastUtil.showToast(mActivity, "请输入密码");
            return;
        }
        if (TextUtils.isEmpty(passwordAgain)) {
            ToastUtil.showToast(mActivity, "请再次输入密码");
            return;
        }
        if (!password.equals(passwordAgain)) {
            ToastUtil.showToast(mActivity, "两次输入的密码不一致");
            return;
        }

        /*Map<String,String> map = new HashMap<>();
        map.put("phone",phone);
        map.put("clientCode",code);
        map.put("loginPassword",password);
        showProgress();
        application.apiService.regist(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        dismissProgress();
                        Log.e("findPwd" , e.toString()) ;
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            JSONObject jsonObject = new JSONObject(responseBody.string());
                            boolean isSuccess = jsonObject.getBoolean("success");
                            String msg = jsonObject.getString("msg");
                            if (isSuccess==true){
                                ToastUtil.showToast(mActivity,"注册成功");
                                finish();
                            }else{
                                ToastUtil.showToast(mActivity,msg);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });*/
        OkHttpUtils
                .post()//
                .url(Constants.BASE_URL + "xjd/front/user/regist")//
                .addParams("phone", phone)
                .addParams("clientCode", code)
                .addParams("loginPassword", password)
                .tag(Tag)
                .build()//
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("ll_yh", "登录失败404" + e.toString());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("ll_yh", "注册结果： " + response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String isSuccess = jsonObject.getString("success");
                            Log.e("sss", "onResponse: " + isSuccess);
                            if (isSuccess.equals("true")) {
                                ToastUtil.showToast(mActivity, "注册成功");
                                finish();
                            } else {
                                ToastUtil.showToast(mActivity, "注册失败");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    /**
     * 获取验证码
     *
     * @return
     */
    private void getCode() {
        String phone = etPhone.getText().toString().trim();
        if (phone.isEmpty()) {
            ToastUtil.showToast(mActivity, "请输入手机号");
            return;
        }
        if (phone.length() < 11 || phone.length() > 11) {
            ToastUtil.showToast(mActivity, "请输入正确的手机号码");
            return;
        }
        OkHttpUtils
                .post()//
                .url(Constants.BASE_URL + "xjd/front/user/sendCode")//
                .addParams("phone", phone)
                .tag(Tag)
                .build()//
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("ll_yh", "登录失败404");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("ll_yh", "登陆结果： " + response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean isSuccess = jsonObject.getBoolean("success");
                            String code = jsonObject.getString("mobileCode");
                            String msg = jsonObject.getString("msg");
                            Log.e("sss", "onResponse: " + code);
                            if (isSuccess == true) {
                                ToastUtil.showToast(mActivity, "验证码发送成功");
                            } else {
                                ToastUtil.showToast(mActivity, msg);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkHttpUtils.getInstance().cancelTag(Tag);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_regist;
    }

    @Override
    protected void loadData() {

    }


    /**
     * 点击事件
     *
     * @return
     */

    @OnClick({R.id.tb_get_validate_number, R.id.btn_regist, R.id.tv_agreement})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tb_get_validate_number: //获取验证码
                getCode();
                break;
            case R.id.btn_regist://注册按钮
                regist();
                break;
            case R.id.tv_agreement://注册协议
                break;
        }
    }
}
