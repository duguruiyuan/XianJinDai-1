package com.daiqile.xianjindai.model;

import suangrenduobao.daiqile.com.mvlib.utils.http.BaseBean;

/**
 * Created by G150T on 2017/6/28.
 */

public class User implements BaseBean{


    /**
     * msg : 登陆成功
     * success : true
     * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHQiOjE0OTg3MzEzNTcyOTMsInVpZCI6MTExLCJpYXQiOjE0OTg3MDYxNTcyOTN9.9ZDtkmikSEKwMvEyRTdh1sAeGu3H9Uxct3zOLUJoTkk
     * uid : 111
     */

    private String msg;
    private boolean success;
    private String token;
    private String uid;

//    private String phone;
//    private String loginPassword;

//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public String getLoginPassword() {
//        return loginPassword;
//    }
//
//    public void setLoginPassword(String loginPassword) {
//        this.loginPassword = loginPassword;
//    }

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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
