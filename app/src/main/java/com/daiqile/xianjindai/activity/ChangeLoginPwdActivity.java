package com.daiqile.xianjindai.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.daiqile.xianjindai.Constants;
import com.daiqile.xianjindai.MyApplication;
import com.daiqile.xianjindai.R;
import com.daiqile.xianjindai.UserPrefs;
import com.daiqile.xianjindai.base.BaseActivity;
import com.daiqile.xianjindai.utils.SPUtils;
import com.daiqile.xianjindai.utils.ToastUtil;
import com.daiqile.xianjindai.view.TopBar;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 修改登录密码页面
 */
public class ChangeLoginPwdActivity extends BaseActivity {


    @BindView(R.id.topbar)//topbar
    TopBar topbar;
    @BindView(R.id.et_old_password)//老的密码
    EditText etOldPassword;
    @BindView(R.id.et_new_password)//新的密码
    EditText etNewPassword;
    @BindView(R.id.et_new_password_again)//新密码再次输入
    EditText etNewPasswordAgain;
    @BindView(R.id.btn_password)//按钮
    Button btnPassword;

    private Activity mActivity;
    private MyApplication application;
    @Override
    public void init() {
        mActivity = ChangeLoginPwdActivity.this;
        application = (MyApplication) getApplication();
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
    private void changeLoginPwd(){
        String oldPwd = etOldPassword.getText().toString().trim();
        String newPwd = etNewPassword.getText().toString().trim();
        String newPwdAgain = etNewPasswordAgain.getText().toString().trim();
        if (oldPwd.isEmpty()){
            ToastUtil.showToast(mActivity,"请输入旧密码");
            return;
        }
        if (newPwd.isEmpty()){
            ToastUtil.showToast(mActivity,"请输入新密码");
            return;
        }
        if (newPwdAgain.isEmpty()){
            ToastUtil.showToast(mActivity,"请再次输入新密码");
            return;
        }
        if(oldPwd.length()<6&&oldPwd.length()>16){
            ToastUtil.showToast(mActivity,"请输入正确的旧密码");
            return;
        }
        if(newPwd.length()<6&&newPwd.length()>16){
            ToastUtil.showToast(mActivity,"请输入正确的旧密码");
            return;
        }
        if(newPwdAgain.length()<6&&newPwdAgain.length()>16){
            ToastUtil.showToast(mActivity,"请输入正确的旧密码");
            return;
        }
        OkHttpUtils
                .post()//
                .url(Constants.BASE_URL + "xjd/front/user/updateLoginPassword")//
                .addParams("phone", UserPrefs.getInstance().getPhone())
                .addParams("oldPwd",oldPwd)
                .addParams("newPwd1",newPwd)
                .addParams("newPwd2",newPwdAgain)
                .addParams("token", UserPrefs.getInstance().getToken())
                .tag(Tag)
                .build()//
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("ll_yh", "登录失败404"+e.toString());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("ll_yh", "注册结果： " + response);
                       // Log.e("sss", "onResponse: "+SPUtils.get(application,"token",null ).toString());
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String isSuccess = jsonObject.getString("success");
                            String msg = jsonObject.getString("msg");
                            Log.e("sss", "onResponse: "+isSuccess );
                            if (isSuccess.equals("true")){
                                ToastUtil.showToast(mActivity,msg);
                                finish();
                            }else{
                                ToastUtil.showToast(mActivity,msg);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @OnClick(R.id.btn_password)
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_password:
                changeLoginPwd();
                break;
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_change_login_pwd;
    }

    @Override
    public Activity bindActivity() {
        return this;
    }

    private static final String Tag = "ChangeLoginPwdActivity";
    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkHttpUtils.getInstance().cancelTag(Tag);
    }
}
