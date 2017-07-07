package com.daiqile.xianjindai.model;

/**
 * Created by G150T on 2017/6/28.
 */

public class User {


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
