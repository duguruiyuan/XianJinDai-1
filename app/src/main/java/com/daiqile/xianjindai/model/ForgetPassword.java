package com.daiqile.xianjindai.model;

/**
 * Created by G150T on 2017/6/29.
 */

public class ForgetPassword {

    /**
     * msg : 密码修改成功
     * success : true
     */

    private String msg;
    private boolean success;

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
}
