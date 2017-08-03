package com.daiqile.xianjindai;

import suangrenduobao.daiqile.com.mvlib.utils.http.BaseBean;

/**
 * Created by zkw on 2017/7/10.
 */

public class Result implements BaseBean {

    /**
     * success : true
     * msg : 保存成功
     */

    private boolean success;
    private String msg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Result{" +
                "success=" + success +
                ", msg='" + msg + '\'' +
                '}';
    }
}
