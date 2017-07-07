package com.daiqile.xianjindai.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


//import com.ab.util.AbSharedUtil;
import com.daiqile.xianjindai.Constants;
import com.daiqile.xianjindai.GenericCache;
import com.daiqile.xianjindai.UserPrefs;
import com.daiqile.xianjindai.utils.NetworkUtil;
import com.daiqile.xianjindai.utils.ToastUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;

/**
 * Created by orgwcl on 2016/9/2.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private ProgressDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(bindActivity());
        init();
        if (!isNetworkAvailable()) {
            ToastUtil.showToast(getApplicationContext(), "当前无可用网络！");
        }

    }

    public abstract void init();
    public abstract int getLayoutId();
    public abstract Activity bindActivity();

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void showProgress() {
        dialog = ProgressDialog.show(bindActivity(), null, "请稍候......");
        dialog.setCancelable(true);
    }

    public void dismissProgress() {
        dialog.dismiss();
    }

    public boolean isNetworkAvailable() {
        return NetworkUtil.getNetworkState(getApplicationContext());
    }
   /*public void clearTemporaryData(){
        GenericCache.getInstance().getUserCache().remove(Constants.userId);
        Constants.isLogin = false;
        Constants.userId = "";
        Constants.phone = "";
        Constants.loginName = "";
        Constants.token = "";

        AbSharedUtil.putString(this,"USERNAME","");
        AbSharedUtil.putString(this,"USERPSW","");
        AbSharedUtil.putString(this,"token","");
        AbSharedUtil.putString(this,"tokentime","0");
        AbSharedUtil.putString(this,"logintoken","");
        AbSharedUtil.putString(this,"logintokentime","0");
    }*/

  public Map<String,String> getHttpHeads(){
        Map<String, String> headers = new HashMap<>();
        headers.put("Charset", "utf-8");
        headers.put("Connection", "keep-alive");
        headers.put("Content-Type", "application/json");//application/json;charset=UTF-8  multipart/form-data
        headers.put("Content-Length", 8192 + "");
        headers.put("Accept-Charset", "ISO-8859-1");
        headers.put("X-Requested-With", "XMLHttpRequest");
        headers.put("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6,zh-TW;q=0.4;UTF-8");
        headers.put("Accept-Encoding", "gzip,compress");
        headers.put("Accept", "text/*, image/*,application/json");
        return headers;
    }
}
