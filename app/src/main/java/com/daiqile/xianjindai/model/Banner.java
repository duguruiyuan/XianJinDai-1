package com.daiqile.xianjindai.model;

import java.util.List;

import suangrenduobao.daiqile.com.mvlib.utils.http.BaseBean;

/**
 * Created by G150T on 2017/7/3.
 */

public class Banner implements BaseBean{

    /**
     * ppts : {"list":[{"id":198,"page":"","ip":"","url":"upload/ppt/img/20170703/20170703101759234727.jpg","isDisplay":"1","disOrder":1,"category":1,"addTime":"","pptUrl":"1","explanation":"ss"},{"id":199,"page":"","ip":"","url":"upload/ppt/img/20170703/20170703101819718283.jpg","isDisplay":"1","disOrder":1,"category":1,"addTime":"","pptUrl":"2","explanation":"4"}],"ppt":"","page":"","categoryMap":"","disPlayList":"","categoryList":"","isDisplayMap":""}
     */

    private PptsBean ppts;

    public PptsBean getPpts() {
        return ppts;
    }

    public void setPpts(PptsBean ppts) {
        this.ppts = ppts;
    }

    public static class PptsBean {
        /**
         * list : [{"id":198,"page":"","ip":"","url":"upload/ppt/img/20170703/20170703101759234727.jpg","isDisplay":"1","disOrder":1,"category":1,"addTime":"","pptUrl":"1","explanation":"ss"},{"id":199,"page":"","ip":"","url":"upload/ppt/img/20170703/20170703101819718283.jpg","isDisplay":"1","disOrder":1,"category":1,"addTime":"","pptUrl":"2","explanation":"4"}]
         * ppt :
         * page :
         * categoryMap :
         * disPlayList :
         * categoryList :
         * isDisplayMap :
         */

        private String ppt;
        private String page;
        private String categoryMap;
        private String disPlayList;
        private String categoryList;
        private String isDisplayMap;
        private List<ListBean> list;

        public String getPpt() {
            return ppt;
        }

        public void setPpt(String ppt) {
            this.ppt = ppt;
        }

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public String getCategoryMap() {
            return categoryMap;
        }

        public void setCategoryMap(String categoryMap) {
            this.categoryMap = categoryMap;
        }

        public String getDisPlayList() {
            return disPlayList;
        }

        public void setDisPlayList(String disPlayList) {
            this.disPlayList = disPlayList;
        }

        public String getCategoryList() {
            return categoryList;
        }

        public void setCategoryList(String categoryList) {
            this.categoryList = categoryList;
        }

        public String getIsDisplayMap() {
            return isDisplayMap;
        }

        public void setIsDisplayMap(String isDisplayMap) {
            this.isDisplayMap = isDisplayMap;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 198
             * page :
             * ip :
             * url : upload/ppt/img/20170703/20170703101759234727.jpg
             * isDisplay : 1
             * disOrder : 1
             * category : 1
             * addTime :
             * pptUrl : 1
             * explanation : ss
             */

            private int id;
            private String page;
            private String ip;
            private String url;
            private String isDisplay;
            private int disOrder;
            private int category;
            private String addTime;
            private String pptUrl;
            private String explanation;

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

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getIsDisplay() {
                return isDisplay;
            }

            public void setIsDisplay(String isDisplay) {
                this.isDisplay = isDisplay;
            }

            public int getDisOrder() {
                return disOrder;
            }

            public void setDisOrder(int disOrder) {
                this.disOrder = disOrder;
            }

            public int getCategory() {
                return category;
            }

            public void setCategory(int category) {
                this.category = category;
            }

            public String getAddTime() {
                return addTime;
            }

            public void setAddTime(String addTime) {
                this.addTime = addTime;
            }

            public String getPptUrl() {
                return pptUrl;
            }

            public void setPptUrl(String pptUrl) {
                this.pptUrl = pptUrl;
            }

            public String getExplanation() {
                return explanation;
            }

            public void setExplanation(String explanation) {
                this.explanation = explanation;
            }
        }
    }
}
