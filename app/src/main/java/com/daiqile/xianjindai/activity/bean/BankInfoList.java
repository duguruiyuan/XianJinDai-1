package com.daiqile.xianjindai.activity.bean;

import java.util.List;

import suangrenduobao.daiqile.com.mvlib.utils.http.BaseBean;

/**
 * Created by zkw on 2017/7/10.
 */

public class BankInfoList implements BaseBean {
    private List<BanksBean> banks;

    public List<BanksBean> getBanks() {
        return banks;
    }

    public void setBanks(List<BanksBean> banks) {
        this.banks = banks;
    }

    public static class BanksBean {
        /**
         * id : 71
         * page :
         * ip :
         * userId : 119
         * bankNo : 3333333333333333
         * bankName : 中国银行
         * phone : 15949629529
         * status : 0
         * remark :
         * createdTime : 1499737004000
         * updatedTime : 1499737004000
         * province : 北京
         * city : 北京市
         * county : 东城区
         * province_id :
         * city_id :
         * county_id :
         * bankBkno :
         * bankLogo :
         * bankBgcolor :
         */

        private int id;
        private String page;
        private String ip;
        private int userId;
        private String bankNo;
        private String bankName;
        private String phone;
        private int status;
        private String remark;
        private long createdTime;
        private long updatedTime;
        private String province;
        private String city;
        private String county;
        private String province_id;
        private String city_id;
        private String county_id;
        private String bankBkno;
        private String bankLogo;
        private String bankBgcolor;

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

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getBankNo() {
            return bankNo;
        }

        public void setBankNo(String bankNo) {
            this.bankNo = bankNo;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public long getCreatedTime() {
            return createdTime;
        }

        public void setCreatedTime(long createdTime) {
            this.createdTime = createdTime;
        }

        public long getUpdatedTime() {
            return updatedTime;
        }

        public void setUpdatedTime(long updatedTime) {
            this.updatedTime = updatedTime;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCounty() {
            return county;
        }

        public void setCounty(String county) {
            this.county = county;
        }

        public String getProvince_id() {
            return province_id;
        }

        public void setProvince_id(String province_id) {
            this.province_id = province_id;
        }

        public String getCity_id() {
            return city_id;
        }

        public void setCity_id(String city_id) {
            this.city_id = city_id;
        }

        public String getCounty_id() {
            return county_id;
        }

        public void setCounty_id(String county_id) {
            this.county_id = county_id;
        }

        public String getBankBkno() {
            return bankBkno;
        }

        public void setBankBkno(String bankBkno) {
            this.bankBkno = bankBkno;
        }

        public String getBankLogo() {
            return bankLogo;
        }

        public void setBankLogo(String bankLogo) {
            this.bankLogo = bankLogo;
        }

        public String getBankBgcolor() {
            return bankBgcolor;
        }

        public void setBankBgcolor(String bankBgcolor) {
            this.bankBgcolor = bankBgcolor;
        }
    }


//    /**
//     * error_code : 0
//     * data : {"uid":"1","username":"12154545","name":"吴系挂","groupid":2,"reg_time":"1436864169","last_login_time":"0"}
//     */
//
//    private int error_code;
//    private DataBean data;
//
//    public int getError_code() {
//        return error_code;
//    }
//
//    public void setError_code(int error_code) {
//        this.error_code = error_code;
//    }
//
//    public DataBean getData() {
//        return data;
//    }
//
//    public void setData(DataBean data) {
//        this.data = data;
//    }
//
//    public static class DataBean {
//        /**
//         * uid : 1
//         * username : 12154545
//         * name : 吴系挂
//         * groupid : 2
//         * reg_time : 1436864169
//         * last_login_time : 0
//         */
//
//        private String uid;
//        private String username;
//        private String name;
//        private int groupid;
//        private String reg_time;
//        private String last_login_time;
//
//        public String getUid() {
//            return uid;
//        }
//
//        public void setUid(String uid) {
//            this.uid = uid;
//        }
//
//        public String getUsername() {
//            return username;
//        }
//
//        public void setUsername(String username) {
//            this.username = username;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public int getGroupid() {
//            return groupid;
//        }
//
//        public void setGroupid(int groupid) {
//            this.groupid = groupid;
//        }
//
//        public String getReg_time() {
//            return reg_time;
//        }
//
//        public void setReg_time(String reg_time) {
//            this.reg_time = reg_time;
//        }
//
//        public String getLast_login_time() {
//            return last_login_time;
//        }
//
//        public void setLast_login_time(String last_login_time) {
//            this.last_login_time = last_login_time;
//        }
//    }
}
