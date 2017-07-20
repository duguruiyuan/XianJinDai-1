package com.daiqile.xianjindai.Fragment.bean;

import suangrenduobao.daiqile.com.mvlib.utils.http.BaseBean;

/**
 * Created by zkw on 2017/7/20.
 */

public class LoanInfoBean implements BaseBean {

    @Override
    public String toString() {
        return "LoanInfoBean{" +
                "name='" + name + '\'' +
                ", max_money=" + max_money +
                ", min_money=" + min_money +
                ", time_limit='" + time_limit + '\'' +
                ", min_time='" + min_time + '\'' +
                ", isDay=" + isDay +
                ", loan_expenses='" + loan_expenses + '\'' +
                ", type=" + type +
                '}';
    }

    /**
     * name : 现金贷
     * max_money : 5000
     * min_money : 1000
     * time_limit : 7
     * min_time : 0天
     * isDay : 0
     * loan_expenses : 20%
     * type : 1
     */


    private String name;
    private int max_money;
    private int min_money;
    private String time_limit;
    private String min_time;
    private int isDay;
    private String loan_expenses;
    private int type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMax_money() {
        return max_money;
    }

    public void setMax_money(int max_money) {
        this.max_money = max_money;
    }

    public int getMin_money() {
        return min_money;
    }

    public void setMin_money(int min_money) {
        this.min_money = min_money;
    }

    public String getTime_limit() {
        return time_limit;
    }

    public void setTime_limit(String time_limit) {
        this.time_limit = time_limit;
    }

    public String getMin_time() {
        return min_time;
    }

    public void setMin_time(String min_time) {
        this.min_time = min_time;
    }

    public int getIsDay() {
        return isDay;
    }

    public void setIsDay(int isDay) {
        this.isDay = isDay;
    }

    public String getLoan_expenses() {
        return loan_expenses;
    }

    public void setLoan_expenses(String loan_expenses) {
        this.loan_expenses = loan_expenses;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
