package com.daiqile.xianjindai.activity.bean;

import java.util.List;

import suangrenduobao.daiqile.com.mvlib.utils.http.BaseBean;

/**
 * Created by zkw on 2017/7/27.
 */

public class BankCertificateBean implements BaseBean {

    private List<ImageBean> list;

    public List<ImageBean> getList() {
        return list;
    }

    public void setList(List<ImageBean> list) {
        this.list = list;
    }

    public static class ImageBean implements BaseBean {
        private int postion;
        private String url;

        private int res;

        private boolean flag = false;

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

        public int getRes() {
            return res;
        }

        public void setRes(int res) {
            this.res = res;
        }

        public ImageBean() {
        }

        public ImageBean(int postion, String url, int res) {
            this.postion = postion;
            this.url = url;
            this.res = res;
        }

        public int getPostion() {
            return postion;
        }

        public void setPostion(int postion) {
            this.postion = postion;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

}
