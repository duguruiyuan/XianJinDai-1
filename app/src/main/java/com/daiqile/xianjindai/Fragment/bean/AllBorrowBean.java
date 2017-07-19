package com.daiqile.xianjindai.Fragment.bean;

import java.util.List;

import suangrenduobao.daiqile.com.mvlib.utils.http.BaseBean;

/**
 * Created by zkw on 2017/7/19.
 */

public class AllBorrowBean implements BaseBean{


    @Override
    public String toString() {
        return "AllBorrowBean{" +
                "loan=" + loan +
                ", page=" + page +
                ", list=" + list +
                '}';
    }

    /**
     * loan : {"page":{"pageSize":30,"currentPage":1,"prePage":0,"nextPage":0,"totalPage":1,"totalCount":6},"userId":111,"flag":"0"}
     * list : [{"id":114,"userId":111,"loanNumber":"xjd1500282931661596709111","realName":"周伟利","loanAmount":5000,"poundage":400,"term":365,"status":3,"bankId":82,"repayTime":1500366413000,"isOverdue":0,"overdueDays":0,"loanTime":-2793600000,"loanInterest":0.0025,"applyTime":1500282931000,"overdueAmount":0,"derateAmount":0,"derateDemurrage":0,"derateCapital":0,"resultStatus":1,"loanType":1,"riskStatus":1,"idcard":"330382199401068312","phone":"15726818334","bankNo":"6217001590000010051","bankName":"中国建设银行"},{"id":115,"userId":111,"loanNumber":"xjd1500367078857573881111","realName":"周伟利","loanAmount":5000,"poundage":1000,"term":7,"status":3,"bankId":82,"repayTime":1500367726000,"isOverdue":0,"overdueDays":0,"loanTime":-2793600000,"loanInterest":0.1142,"applyTime":1500367078000,"overdueAmount":0,"derateAmount":0,"derateDemurrage":0,"derateCapital":0,"resultStatus":1,"loanType":1,"riskStatus":1,"idcard":"330382199401068312","phone":"15726818334","bankNo":"6217001590000010051","bankName":"中国建设银行"},{"id":116,"userId":111,"loanNumber":"xjd1500450245769735611111","realName":"周伟利","loanAmount":1000,"poundage":200,"term":7,"status":2,"bankId":82,"isOverdue":0,"overdueDays":0,"loanTime":-2793600000,"loanInterest":0.1142,"applyTime":1500450246000,"overdueAmount":0,"derateAmount":0,"derateDemurrage":0,"derateCapital":0,"resultStatus":1,"loanType":1,"riskStatus":1,"idcard":"330382199401068312","phone":"15726818334","bankNo":"6217001590000010051","bankName":"中国建设银行"},{"id":117,"userId":111,"loanNumber":"xjd1500450292582794921111","realName":"周伟利","loanAmount":1000,"poundage":200,"term":7,"status":2,"bankId":82,"isOverdue":0,"overdueDays":0,"loanTime":-2793600000,"loanInterest":0.1142,"applyTime":1500450292000,"overdueAmount":0,"derateAmount":0,"derateDemurrage":0,"derateCapital":0,"resultStatus":1,"loanType":1,"riskStatus":1,"idcard":"330382199401068312","phone":"15726818334","bankNo":"6217001590000010051","bankName":"中国建设银行"},{"id":118,"userId":111,"loanNumber":"xjd1500450301613162689111","realName":"周伟利","loanAmount":1000,"poundage":200,"term":7,"status":2,"bankId":82,"isOverdue":0,"overdueDays":0,"loanTime":-2793600000,"loanInterest":0.1142,"applyTime":1500450301000,"overdueAmount":0,"derateAmount":0,"derateDemurrage":0,"derateCapital":0,"resultStatus":1,"loanType":1,"riskStatus":1,"idcard":"330382199401068312","phone":"15726818334","bankNo":"6217001590000010051","bankName":"中国建设银行"},{"id":119,"userId":111,"loanNumber":"xjd1500450386925400433111","realName":"周伟利","loanAmount":1000,"poundage":200,"term":7,"status":2,"bankId":82,"isOverdue":0,"overdueDays":0,"loanTime":-2793600000,"loanInterest":0.1142,"applyTime":1500450387000,"overdueAmount":0,"derateAmount":0,"derateDemurrage":0,"derateCapital":0,"resultStatus":1,"loanType":1,"riskStatus":3,"idcard":"330382199401068312","phone":"15726818334","bankNo":"6217001590000010051","bankName":"中国建设银行"}]
     * page : {"pageSize":30,"currentPage":1,"prePage":0,"nextPage":0,"totalPage":1,"totalCount":6}
     */

    private LoanBean loan;
    private PageBeanX page;
    private List<ListBean> list;

    public LoanBean getLoan() {
        return loan;
    }

    public void setLoan(LoanBean loan) {
        this.loan = loan;
    }

    public PageBeanX getPage() {
        return page;
    }

    public void setPage(PageBeanX page) {
        this.page = page;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class LoanBean {
        /**
         * page : {"pageSize":30,"currentPage":1,"prePage":0,"nextPage":0,"totalPage":1,"totalCount":6}
         * userId : 111
         * flag : 0
         */

        private PageBean page;
        private int userId;
        private String flag;

        public PageBean getPage() {
            return page;
        }

        public void setPage(PageBean page) {
            this.page = page;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public static class PageBean {
            /**
             * pageSize : 30
             * currentPage : 1
             * prePage : 0
             * nextPage : 0
             * totalPage : 1
             * totalCount : 6
             */

            private int pageSize;
            private int currentPage;
            private int prePage;
            private int nextPage;
            private int totalPage;
            private int totalCount;

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public int getCurrentPage() {
                return currentPage;
            }

            public void setCurrentPage(int currentPage) {
                this.currentPage = currentPage;
            }

            public int getPrePage() {
                return prePage;
            }

            public void setPrePage(int prePage) {
                this.prePage = prePage;
            }

            public int getNextPage() {
                return nextPage;
            }

            public void setNextPage(int nextPage) {
                this.nextPage = nextPage;
            }

            public int getTotalPage() {
                return totalPage;
            }

            public void setTotalPage(int totalPage) {
                this.totalPage = totalPage;
            }

            public int getTotalCount() {
                return totalCount;
            }

            public void setTotalCount(int totalCount) {
                this.totalCount = totalCount;
            }
        }
    }

    public static class PageBeanX {
        /**
         * pageSize : 30
         * currentPage : 1
         * prePage : 0
         * nextPage : 0
         * totalPage : 1
         * totalCount : 6
         */

        private int pageSize;
        private int currentPage;
        private int prePage;
        private int nextPage;
        private int totalPage;
        private int totalCount;

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getPrePage() {
            return prePage;
        }

        public void setPrePage(int prePage) {
            this.prePage = prePage;
        }

        public int getNextPage() {
            return nextPage;
        }

        public void setNextPage(int nextPage) {
            this.nextPage = nextPage;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }
    }

    public static class ListBean {
        /**
         * id : 114
         * userId : 111
         * loanNumber : xjd1500282931661596709111
         * realName : 周伟利
         * loanAmount : 5000
         * poundage : 400
         * term : 365
         * status : 3
         * bankId : 82
         * repayTime : 1500366413000
         * isOverdue : 0
         * overdueDays : 0
         * loanTime : -2793600000
         * loanInterest : 0.0025
         * applyTime : 1500282931000
         * overdueAmount : 0
         * derateAmount : 0
         * derateDemurrage : 0
         * derateCapital : 0
         * resultStatus : 1
         * loanType : 1
         * riskStatus : 1
         * idcard : 330382199401068312
         * phone : 15726818334
         * bankNo : 6217001590000010051
         * bankName : 中国建设银行
         */

        private int id;
        private int userId;
        private String loanNumber;
        private String realName;
        private int loanAmount;
        private int poundage;
        private int term;
        private int status;
        private int bankId;
        private long repayTime;
        private int isOverdue;
        private int overdueDays;
        private long loanTime;
        private double loanInterest;
        private long applyTime;
        private int overdueAmount;
        private int derateAmount;
        private int derateDemurrage;
        private int derateCapital;
        private int resultStatus;
        private int loanType;
        private int riskStatus;
        private String idcard;
        private String phone;
        private String bankNo;
        private String bankName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getLoanNumber() {
            return loanNumber;
        }

        public void setLoanNumber(String loanNumber) {
            this.loanNumber = loanNumber;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public int getLoanAmount() {
            return loanAmount;
        }

        public void setLoanAmount(int loanAmount) {
            this.loanAmount = loanAmount;
        }

        public int getPoundage() {
            return poundage;
        }

        public void setPoundage(int poundage) {
            this.poundage = poundage;
        }

        public int getTerm() {
            return term;
        }

        public void setTerm(int term) {
            this.term = term;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getBankId() {
            return bankId;
        }

        public void setBankId(int bankId) {
            this.bankId = bankId;
        }

        public long getRepayTime() {
            return repayTime;
        }

        public void setRepayTime(long repayTime) {
            this.repayTime = repayTime;
        }

        public int getIsOverdue() {
            return isOverdue;
        }

        public void setIsOverdue(int isOverdue) {
            this.isOverdue = isOverdue;
        }

        public int getOverdueDays() {
            return overdueDays;
        }

        public void setOverdueDays(int overdueDays) {
            this.overdueDays = overdueDays;
        }

        public long getLoanTime() {
            return loanTime;
        }

        public void setLoanTime(long loanTime) {
            this.loanTime = loanTime;
        }

        public double getLoanInterest() {
            return loanInterest;
        }

        public void setLoanInterest(double loanInterest) {
            this.loanInterest = loanInterest;
        }

        public long getApplyTime() {
            return applyTime;
        }

        public void setApplyTime(long applyTime) {
            this.applyTime = applyTime;
        }

        public int getOverdueAmount() {
            return overdueAmount;
        }

        public void setOverdueAmount(int overdueAmount) {
            this.overdueAmount = overdueAmount;
        }

        public int getDerateAmount() {
            return derateAmount;
        }

        public void setDerateAmount(int derateAmount) {
            this.derateAmount = derateAmount;
        }

        public int getDerateDemurrage() {
            return derateDemurrage;
        }

        public void setDerateDemurrage(int derateDemurrage) {
            this.derateDemurrage = derateDemurrage;
        }

        public int getDerateCapital() {
            return derateCapital;
        }

        public void setDerateCapital(int derateCapital) {
            this.derateCapital = derateCapital;
        }

        public int getResultStatus() {
            return resultStatus;
        }

        public void setResultStatus(int resultStatus) {
            this.resultStatus = resultStatus;
        }

        public int getLoanType() {
            return loanType;
        }

        public void setLoanType(int loanType) {
            this.loanType = loanType;
        }

        public int getRiskStatus() {
            return riskStatus;
        }

        public void setRiskStatus(int riskStatus) {
            this.riskStatus = riskStatus;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
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
    }
}
