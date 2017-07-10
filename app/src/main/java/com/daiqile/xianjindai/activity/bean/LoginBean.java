package com.daiqile.xianjindai.activity.bean;

import suangrenduobao.daiqile.com.mvlib.utils.http.BaseBean;

/**
 * Created by zkw on 2017/7/10.
 */

public class LoginBean implements BaseBean {


    /**
     * msg : 登陆成功
     * success : true
     * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHQiOjE0OTk2ODI4MDkxMDEsInVpZCI6MTE5LCJpYXQiOjE0OTk2NTc2MDkxMDF9.otTE1Pr4NKNfV68vi5zMA5gaRLGdGRAm9NFDcJP0nt0
     * uid : 119
     */

    private String msg;
    private boolean success;
    private String token;
    private int uid;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
