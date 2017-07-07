package com.daiqile.xianjindai.model;

import java.util.List;

/**
 * Created by G150T on 2017/6/30.
 */

public class Bank {

    private List<BankXFsBean> bankXFs;

    public List<BankXFsBean> getBankXFs() {
        return bankXFs;
    }

    public void setBankXFs(List<BankXFsBean> bankXFs) {
        this.bankXFs = bankXFs;
    }

    public static class BankXFsBean {
        /**
         * id : 1
         * page :
         * ip :
         * bankCode : ICBC
         * bankName : 中国工商银行
         * isUse : 1
         * bOrder : 1
         */

        private int id;
        private String page;
        private String ip;
        private String bankCode;
        private String bankName;
        private int isUse;
        private int bOrder;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getBankCode() {
            return bankCode;
        }

        public void setBankCode(String bankCode) {
            this.bankCode = bankCode;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public int getIsUse() {
            return isUse;
        }

        public void setIsUse(int isUse) {
            this.isUse = isUse;
        }

        public int getBOrder() {
            return bOrder;
        }

        public void setBOrder(int bOrder) {
            this.bOrder = bOrder;
        }
    }
}
