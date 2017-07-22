package com.daiqile.xianjindai.Fragment.bean;

import java.util.List;

import suangrenduobao.daiqile.com.mvlib.utils.http.BaseBean;

/**
 * Created by zkw on 2017/7/20.
 */

public class UserInfoBean implements BaseBean {


    /**
     * success : true
     * mssg : 成功
     * users : [{"id":111,"page":"","ip":"","userName":"","loginPassword":"e10adc3949ba59abbe56e057f20f883e","payPassword":"e10adc3949ba59abbe56e057f20f883e","idcard":"330382199401068312","email":"","realName":"周伟利","phone":"15726818334","otherPhone":"15726818334","companyAddress":"123456","companyPhone":"123456789","company":"so","monthIncome":"","job":"","hasBank":1,"hasIdcardInfo":1,"hasWorkInfo":1,"hasContactInfo":1,"hasAddressBook":0,"hasSwitch":0,"hasPhoto":1,"socialSecurity":"4444","netLoanInfo":"","carInfo":"","sesameCreditImg":"/xjd/upload/userinfo/20170711/20170711141130282864.jpg","flowerAmountImg":"/xjd/upload/userinfo/20170711/20170711141130157288.jpg","borrowQuotaImg":"/xjd/upload/userinfo/20170711/20170711141130219785.jpg","parentDetailAddress":"","otherContactName2":"","otherContactType2":"","otherContactCellPhone2":"","age":"12","qq2":"12345","weChat2":"6789","education":"77777","houseSituation":"258966","hasSocial":1,"level":0,"score":0,"createdTime":1498633108000,"updatedTime":1498633108000,"status":0,"remark":"","freezeDate":"","province":"浙江省","province_id":"","parentProvince":"","parentprovince_id":"","city":"温州","city_id":"","parentCity":"","parentcity_id":"","county":"平阳县","county_id":"","parentCounty":"","parentcounty_id":"","area":"ttttt","liveTime":"","trades":"","marriage":"未婚","firstContactName":"1","firstContactType":"父母","firstContactCellPhone":"2","secondContactName":"5","secondContactType":"同事","secondContactCellPhone":"4","otherContactType":"","otherContactCellPhone":"","otherContactName":"","weChat":"456","qq":"123","channel":"1","workState":"","data":"","workTime":"","alipayAccount":"","positionInformation":"","deleteFlag":0,"picOne":"/xjd/upload/loanImg/20170719/20170719154028628776.jpg","picTwo":"/xjd/upload/loanImg/20170719/20170719154028800670.jpg","picThree":"/xjd/upload/loanImg/20170719/20170719154028957341.jpg","picFour":"/xjd/upload/loanImg/20170719/20170719154029144375.jpg","picFive":"/xjd/upload/loanImg/20170719/20170719154029332612.jpg","picSix":"/xjd/upload/loanImg/20170719/20170719154029566894.jpg","picSeven":"/xjd/upload/loanImg/20170719/20170719154029707240.jpg","picEight":"/xjd/upload/loanImg/20170719/20170719154029878615.jpg","userVo":""}]
     */

    private boolean success;
    private String mssg;
    private List<UsersBean> users;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMssg() {
        return mssg;
    }

    public void setMssg(String mssg) {
        this.mssg = mssg;
    }

    public List<UsersBean> getUsers() {
        return users;
    }

    public void setUsers(List<UsersBean> users) {
        this.users = users;
    }

    public static class UsersBean {
        /**
         * id : 111
         * page :
         * ip :
         * userName :
         * loginPassword : e10adc3949ba59abbe56e057f20f883e
         * payPassword : e10adc3949ba59abbe56e057f20f883e
         * idcard : 330382199401068312
         * email :
         * realName : 周伟利
         * phone : 15726818334
         * otherPhone : 15726818334
         * companyAddress : 123456
         * companyPhone : 123456789
         * company : so
         * monthIncome :
         * job :
         * hasBank : 1
         * hasIdcardInfo : 1
         * hasWorkInfo : 1
         * hasContactInfo : 1
         * hasAddressBook : 0
         * hasSwitch : 0
         * hasPhoto : 1
         * socialSecurity : 4444
         * netLoanInfo :
         * carInfo :
         * sesameCreditImg : /xjd/upload/userinfo/20170711/20170711141130282864.jpg
         * flowerAmountImg : /xjd/upload/userinfo/20170711/20170711141130157288.jpg
         * borrowQuotaImg : /xjd/upload/userinfo/20170711/20170711141130219785.jpg
         * parentDetailAddress :
         * otherContactName2 :
         * otherContactType2 :
         * otherContactCellPhone2 :
         * age : 12
         * qq2 : 12345
         * weChat2 : 6789
         * education : 77777
         * houseSituation : 258966
         * hasSocial : 1
         * level : 0
         * score : 0
         * createdTime : 1498633108000
         * updatedTime : 1498633108000
         * status : 0
         * remark :
         * freezeDate :
         * province : 浙江省
         * province_id :
         * parentProvince :
         * parentprovince_id :
         * city : 温州
         * city_id :
         * parentCity :
         * parentcity_id :
         * county : 平阳县
         * county_id :
         * parentCounty :
         * parentcounty_id :
         * area : ttttt
         * liveTime :
         * trades :
         * marriage : 未婚
         * firstContactName : 1
         * firstContactType : 父母
         * firstContactCellPhone : 2
         * secondContactName : 5
         * secondContactType : 同事
         * secondContactCellPhone : 4
         * otherContactType :
         * otherContactCellPhone :
         * otherContactName :
         * weChat : 456
         * qq : 123
         * channel : 1
         * workState :
         * data :
         * workTime :
         * alipayAccount :
         * positionInformation :
         * deleteFlag : 0
         * picOne : /xjd/upload/loanImg/20170719/20170719154028628776.jpg
         * picTwo : /xjd/upload/loanImg/20170719/20170719154028800670.jpg
         * picThree : /xjd/upload/loanImg/20170719/20170719154028957341.jpg
         * picFour : /xjd/upload/loanImg/20170719/20170719154029144375.jpg
         * picFive : /xjd/upload/loanImg/20170719/20170719154029332612.jpg
         * picSix : /xjd/upload/loanImg/20170719/20170719154029566894.jpg
         * picSeven : /xjd/upload/loanImg/20170719/20170719154029707240.jpg
         * picEight : /xjd/upload/loanImg/20170719/20170719154029878615.jpg
         * userVo :
         */

        private int id;
//        private String page;
//        private String ip;
//        private String userName;
//        private String loginPassword;
//        private String payPassword;
//        private String idcard;
//        private String email;
//        private String realName;
        private String phone;
//        private String otherPhone;
//        private String companyAddress;
//        private String companyPhone;
//        private String company;
//        private String monthIncome;
//        private String job;
        private int hasBank;
        private int hasIdcardInfo;
//        private int hasWorkInfo;
//        private int hasContactInfo;
//        private int hasAddressBook;
//        private int hasSwitch;
        private String hasPhoto;
//        private String socialSecurity;
//        private String netLoanInfo;
//        private String carInfo;
//        private String sesameCreditImg;
//        private String flowerAmountImg;
//        private String borrowQuotaImg;
//        private String parentDetailAddress;
//        private String otherContactName2;
//        private String otherContactType2;
//        private String otherContactCellPhone2;
//        private String age;
//        private String qq2;
//        private String weChat2;
//        private String education;
//        private String houseSituation;
//        private int hasSocial;
//        private int level;
//        private int score;
//        private long createdTime;
//        private long updatedTime;
        private int status;
//        private String remark;
//        private String freezeDate;
//        private String province;
//        private String province_id;
//        private String parentProvince;
//        private String parentprovince_id;
//        private String city;
//        private String city_id;
//        private String parentCity;
//        private String parentcity_id;
//        private String county;
//        private String county_id;
//        private String parentCounty;
//        private String parentcounty_id;
//        private String area;
//        private String liveTime;
//        private String trades;
//        private String marriage;
//        private String firstContactName;
//        private String firstContactType;
//        private String firstContactCellPhone;
//        private String secondContactName;
//        private String secondContactType;
//        private String secondContactCellPhone;
//        private String otherContactType;
//        private String otherContactCellPhone;
//        private String otherContactName;
//        private String weChat;
//        private String qq;
//        private String channel;
//        private String workState;
        private String data;
//        private String workTime;
//        private String alipayAccount;
//        private String positionInformation;
//        private int deleteFlag;
//        private String picOne;
//        private String picTwo;
//        private String picThree;
//        private String picFour;
//        private String picFive;
//        private String picSix;
//        private String picSeven;
//        private String picEight;
//        private String userVo;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
//
//        public String getPage() {
//            return page;
//        }
//
//        public void setPage(String page) {
//            this.page = page;
//        }
//
//        public String getIp() {
//            return ip;
//        }
//
//        public void setIp(String ip) {
//            this.ip = ip;
//        }

//        public String getUserName() {
//            return userName;
//        }
//
//        public void setUserName(String userName) {
//            this.userName = userName;
//        }
//
//        public String getLoginPassword() {
//            return loginPassword;
//        }
//
//        public void setLoginPassword(String loginPassword) {
//            this.loginPassword = loginPassword;
//        }
//
//        public String getPayPassword() {
//            return payPassword;
//        }
//
//        public void setPayPassword(String payPassword) {
//            this.payPassword = payPassword;
//        }

//        public String getIdcard() {
//            return idcard;
//        }
//
//        public void setIdcard(String idcard) {
//            this.idcard = idcard;
//        }
//
//        public String getEmail() {
//            return email;
//        }
//
//        public void setEmail(String email) {
//            this.email = email;
//        }
//
//        public String getRealName() {
//            return realName;
//        }
//
//        public void setRealName(String realName) {
//            this.realName = realName;
//        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
//
//        public String getOtherPhone() {
//            return otherPhone;
//        }
//
//        public void setOtherPhone(String otherPhone) {
//            this.otherPhone = otherPhone;
//        }
//
//        public String getCompanyAddress() {
//            return companyAddress;
//        }
//
//        public void setCompanyAddress(String companyAddress) {
//            this.companyAddress = companyAddress;
//        }

//        public String getCompanyPhone() {
//            return companyPhone;
//        }
//
//        public void setCompanyPhone(String companyPhone) {
//            this.companyPhone = companyPhone;
//        }
//
//        public String getCompany() {
//            return company;
//        }
//
//        public void setCompany(String company) {
//            this.company = company;
//        }
//
//        public String getMonthIncome() {
//            return monthIncome;
//        }
//
//        public void setMonthIncome(String monthIncome) {
//            this.monthIncome = monthIncome;
//        }
//
//        public String getJob() {
//            return job;
//        }
//
//        public void setJob(String job) {
//            this.job = job;
//        }

        public int getHasBank() {
            return hasBank;
        }

        public void setHasBank(int hasBank) {
            this.hasBank = hasBank;
        }

        public int getHasIdcardInfo() {
            return hasIdcardInfo;
        }

        public void setHasIdcardInfo(int hasIdcardInfo) {
            this.hasIdcardInfo = hasIdcardInfo;
        }

//        public int getHasWorkInfo() {
//            return hasWorkInfo;
//        }
//
//        public void setHasWorkInfo(int hasWorkInfo) {
//            this.hasWorkInfo = hasWorkInfo;
//        }
//
//        public int getHasContactInfo() {
//            return hasContactInfo;
//        }
//
//        public void setHasContactInfo(int hasContactInfo) {
//            this.hasContactInfo = hasContactInfo;
//        }
//
//        public int getHasAddressBook() {
//            return hasAddressBook;
//        }
//
//        public void setHasAddressBook(int hasAddressBook) {
//            this.hasAddressBook = hasAddressBook;
//        }
//
//        public int getHasSwitch() {
//            return hasSwitch;
//        }
//
//        public void setHasSwitch(int hasSwitch) {
//            this.hasSwitch = hasSwitch;
//        }

        public String getHasPhoto() {
            return hasPhoto;
        }

        public void setHasPhoto(String hasPhoto) {
            this.hasPhoto = hasPhoto;
        }

//        public String getSocialSecurity() {
//            return socialSecurity;
//        }
//
//        public void setSocialSecurity(String socialSecurity) {
//            this.socialSecurity = socialSecurity;
//        }

//        public String getNetLoanInfo() {
//            return netLoanInfo;
//        }
//
//        public void setNetLoanInfo(String netLoanInfo) {
//            this.netLoanInfo = netLoanInfo;
//        }
//
//        public String getCarInfo() {
//            return carInfo;
//        }
//
//        public void setCarInfo(String carInfo) {
//            this.carInfo = carInfo;
//        }

//        public String getSesameCreditImg() {
//            return sesameCreditImg;
//        }
//
//        public void setSesameCreditImg(String sesameCreditImg) {
//            this.sesameCreditImg = sesameCreditImg;
//        }
//        public String getFlowerAmountImg() {
//            return flowerAmountImg;
//        }
//
//        public void setFlowerAmountImg(String flowerAmountImg) {
//            this.flowerAmountImg = flowerAmountImg;
//        }
//
//        public String getBorrowQuotaImg() {
//            return borrowQuotaImg;
//        }
//
//        public void setBorrowQuotaImg(String borrowQuotaImg) {
//            this.borrowQuotaImg = borrowQuotaImg;
//        }

//        public String getParentDetailAddress() {
//            return parentDetailAddress;
//        }
//
//        public void setParentDetailAddress(String parentDetailAddress) {
//            this.parentDetailAddress = parentDetailAddress;
//        }
//
//        public String getOtherContactName2() {
//            return otherContactName2;
//        }
//
//        public void setOtherContactName2(String otherContactName2) {
//            this.otherContactName2 = otherContactName2;
//        }
//
//        public String getOtherContactType2() {
//            return otherContactType2;
//        }
//
//        public void setOtherContactType2(String otherContactType2) {
//            this.otherContactType2 = otherContactType2;
//        }

//        public String getOtherContactCellPhone2() {
//            return otherContactCellPhone2;
//        }
//
//        public void setOtherContactCellPhone2(String otherContactCellPhone2) {
//            this.otherContactCellPhone2 = otherContactCellPhone2;
//        }

//        public String getAge() {
//            return age;
//        }
//
//        public void setAge(String age) {
//            this.age = age;
//        }
//
//        public String getQq2() {
//            return qq2;
//        }
//
//        public void setQq2(String qq2) {
//            this.qq2 = qq2;
//        }
//
//        public String getWeChat2() {
//            return weChat2;
//        }
//
//        public void setWeChat2(String weChat2) {
//            this.weChat2 = weChat2;
//        }
//
//        public String getEducation() {
//            return education;
//        }
//
//        public void setEducation(String education) {
//            this.education = education;
//        }

//        public String getHouseSituation() {
//            return houseSituation;
//        }
//
//        public void setHouseSituation(String houseSituation) {
//            this.houseSituation = houseSituation;
//        }

//        public int getHasSocial() {
//            return hasSocial;
//        }
//
//        public void setHasSocial(int hasSocial) {
//            this.hasSocial = hasSocial;
//        }
//
//        public int getLevel() {
//            return level;
//        }
//
//        public void setLevel(int level) {
//            this.level = level;
//        }

//        public int getScore() {
//            return score;
//        }
//
//        public void setScore(int score) {
//            this.score = score;
//        }
//
//        public long getCreatedTime() {
//            return createdTime;
//        }
//
//        public void setCreatedTime(long createdTime) {
//            this.createdTime = createdTime;
//        }
//
//        public long getUpdatedTime() {
//            return updatedTime;
//        }
//
//        public void setUpdatedTime(long updatedTime) {
//            this.updatedTime = updatedTime;
//        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

//        public String getRemark() {
//            return remark;
//        }
//
//        public void setRemark(String remark) {
//            this.remark = remark;
//        }
//
//        public String getFreezeDate() {
//            return freezeDate;
//        }
//
//        public void setFreezeDate(String freezeDate) {
//            this.freezeDate = freezeDate;
//        }
//
//        public String getProvince() {
//            return province;
//        }
//
//        public void setProvince(String province) {
//            this.province = province;
//        }
//
//        public String getProvince_id() {
//            return province_id;
//        }
//
//        public void setProvince_id(String province_id) {
//            this.province_id = province_id;
//        }
//
//        public String getParentProvince() {
//            return parentProvince;
//        }
//
//        public void setParentProvince(String parentProvince) {
//            this.parentProvince = parentProvince;
//        }
//
//        public String getParentprovince_id() {
//            return parentprovince_id;
//        }
//
//        public void setParentprovince_id(String parentprovince_id) {
//            this.parentprovince_id = parentprovince_id;
//        }

//        public String getCity() {
//            return city;
//        }
//
//        public void setCity(String city) {
//            this.city = city;
//        }
//
//        public String getCity_id() {
//            return city_id;
//        }
//
//        public void setCity_id(String city_id) {
//            this.city_id = city_id;
//        }
//
//        public String getParentCity() {
//            return parentCity;
//        }
//
//        public void setParentCity(String parentCity) {
//            this.parentCity = parentCity;
//        }
//
//        public String getParentcity_id() {
//            return parentcity_id;
//        }
//
//        public void setParentcity_id(String parentcity_id) {
//            this.parentcity_id = parentcity_id;
//        }
//
//        public String getCounty() {
//            return county;
//        }
//
//        public void setCounty(String county) {
//            this.county = county;
//        }

//        public String getCounty_id() {
//            return county_id;
//        }
//
//        public void setCounty_id(String county_id) {
//            this.county_id = county_id;
//        }
//
//        public String getParentCounty() {
//            return parentCounty;
//        }
//
//        public void setParentCounty(String parentCounty) {
//            this.parentCounty = parentCounty;
//        }
//
//        public String getParentcounty_id() {
//            return parentcounty_id;
//        }
//
//        public void setParentcounty_id(String parentcounty_id) {
//            this.parentcounty_id = parentcounty_id;
//        }
//
//        public String getArea() {
//            return area;
//        }
//
//        public void setArea(String area) {
//            this.area = area;
//        }
//
//        public String getLiveTime() {
//            return liveTime;
//        }
//
//        public void setLiveTime(String liveTime) {
//            this.liveTime = liveTime;
//        }

//        public String getTrades() {
//            return trades;
//        }
//
//        public void setTrades(String trades) {
//            this.trades = trades;
//        }
//
//        public String getMarriage() {
//            return marriage;
//        }
//
//        public void setMarriage(String marriage) {
//            this.marriage = marriage;
//        }
//
//        public String getFirstContactName() {
//            return firstContactName;
//        }
//
//        public void setFirstContactName(String firstContactName) {
//            this.firstContactName = firstContactName;
//        }

//        public String getFirstContactType() {
//            return firstContactType;
//        }
//
//        public void setFirstContactType(String firstContactType) {
//            this.firstContactType = firstContactType;
//        }
//
//        public String getFirstContactCellPhone() {
//            return firstContactCellPhone;
//        }
//
//        public void setFirstContactCellPhone(String firstContactCellPhone) {
//            this.firstContactCellPhone = firstContactCellPhone;
//        }
//
//        public String getSecondContactName() {
//            return secondContactName;
//        }
//
//        public void setSecondContactName(String secondContactName) {
//            this.secondContactName = secondContactName;
//        }
//
//        public String getSecondContactType() {
//            return secondContactType;
//        }
//
//        public void setSecondContactType(String secondContactType) {
//            this.secondContactType = secondContactType;
//        }

//        public String getSecondContactCellPhone() {
//            return secondContactCellPhone;
//        }
//
//        public void setSecondContactCellPhone(String secondContactCellPhone) {
//            this.secondContactCellPhone = secondContactCellPhone;
//        }
//
//        public String getOtherContactType() {
//            return otherContactType;
//        }
//
//        public void setOtherContactType(String otherContactType) {
//            this.otherContactType = otherContactType;
//        }
//
//        public String getOtherContactCellPhone() {
//            return otherContactCellPhone;
//        }
//
//        public void setOtherContactCellPhone(String otherContactCellPhone) {
//            this.otherContactCellPhone = otherContactCellPhone;
//        }
//
//        public String getOtherContactName() {
//            return otherContactName;
//        }
//
//        public void setOtherContactName(String otherContactName) {
//            this.otherContactName = otherContactName;
//        }

//        public String getWeChat() {
//            return weChat;
//        }
//
//        public void setWeChat(String weChat) {
//            this.weChat = weChat;
//        }
//
//        public String getQq() {
//            return qq;
//        }
//
//        public void setQq(String qq) {
//            this.qq = qq;
//        }
//
//        public String getChannel() {
//            return channel;
//        }
//
//        public void setChannel(String channel) {
//            this.channel = channel;
//        }
//
//        public String getWorkState() {
//            return workState;
//        }
//
//        public void setWorkState(String workState) {
//            this.workState = workState;
//        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

//        public String getWorkTime() {
//            return workTime;
//        }
//
//        public void setWorkTime(String workTime) {
//            this.workTime = workTime;
//        }
//
//        public String getAlipayAccount() {
//            return alipayAccount;
//        }
//
//        public void setAlipayAccount(String alipayAccount) {
//            this.alipayAccount = alipayAccount;
//        }
//
//        public String getPositionInformation() {
//            return positionInformation;
//        }
//
//        public void setPositionInformation(String positionInformation) {
//            this.positionInformation = positionInformation;
//        }
//
//        public int getDeleteFlag() {
//            return deleteFlag;
//        }
//
//        public void setDeleteFlag(int deleteFlag) {
//            this.deleteFlag = deleteFlag;
//        }
//
//        public String getPicOne() {
//            return picOne;
//        }
//
//        public void setPicOne(String picOne) {
//            this.picOne = picOne;
//        }
//
//        public String getPicTwo() {
//            return picTwo;
//        }
//
//        public void setPicTwo(String picTwo) {
//            this.picTwo = picTwo;
//        }
//
//        public String getPicThree() {
//            return picThree;
//        }
//
//        public void setPicThree(String picThree) {
//            this.picThree = picThree;
//        }
//
//        public String getPicFour() {
//            return picFour;
//        }
//
//        public void setPicFour(String picFour) {
//            this.picFour = picFour;
//        }
//
//        public String getPicFive() {
//            return picFive;
//        }
//
//        public void setPicFive(String picFive) {
//            this.picFive = picFive;
//        }
//
//        public String getPicSix() {
//            return picSix;
//        }
//
//        public void setPicSix(String picSix) {
//            this.picSix = picSix;
//        }
//
//        public String getPicSeven() {
//            return picSeven;
//        }
//
//        public void setPicSeven(String picSeven) {
//            this.picSeven = picSeven;
//        }
//
//        public String getPicEight() {
//            return picEight;
//        }
//
//        public void setPicEight(String picEight) {
//            this.picEight = picEight;
//        }
//
//        public String getUserVo() {
//            return userVo;
//        }
//
//        public void setUserVo(String userVo) {
//            this.userVo = userVo;
//        }
    }
}
