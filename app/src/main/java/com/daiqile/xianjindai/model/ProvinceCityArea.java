package com.daiqile.xianjindai.model;

import java.util.List;

import suangrenduobao.daiqile.com.mvlib.utils.http.BaseBean;

/**
 * Created by Administrator on 2017/1/17.
 */

public class ProvinceCityArea implements BaseBean {
    private List<ProvinceListBean> provinceList;
    private List<AreaListBean> areaList;
    private List<CityListBean> cityList;

    public List<ProvinceListBean> getProvinceList() {
        return provinceList;
    }

    public void setProvinceList(List<ProvinceListBean> provinceList) {
        this.provinceList = provinceList;
    }

    public List<AreaListBean> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<AreaListBean> areaList) {
        this.areaList = areaList;
    }

    public List<CityListBean> getCityList() {
        return cityList;
    }

    public void setCityList(List<CityListBean> cityList) {
        this.cityList = cityList;
    }

    public static class ProvinceListBean implements BaseBean {
        /**
         * id : 1
         * page :
         * ip :
         * name : 北京
         * order : 0
         * initial : B
         */

        private int id;
        private String page;
        private String ip;
        private String name;
        private int order;
        private String initial;

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public String getInitial() {
            return initial;
        }

        public void setInitial(String initial) {
            this.initial = initial;
        }
    }

    public static class AreaListBean implements BaseBean {
        /**
         * id : 3
         * name : 东城区
         * cityId : 2
         * order : 2
         * initial : D
         */

        private int id;
        private String name;
        private int cityId;
        private int order;
        private String initial;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getCityId() {
            return cityId;
        }

        public void setCityId(int cityId) {
            this.cityId = cityId;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public String getInitial() {
            return initial;
        }

        public void setInitial(String initial) {
            this.initial = initial;
        }
    }

    public static class CityListBean implements BaseBean {
        /**
         * id : 2
         * page :
         * ip :
         * name : 北京市
         * provinceId : 1
         * order : 1
         * initial : B
         * isOpen : 0
         */

        private int id;
        private String page;
        private String ip;
        private String name;
        private int provinceId;
        private int order;
        private String initial;
        private int isOpen;

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getProvinceId() {
            return provinceId;
        }

        public void setProvinceId(int provinceId) {
            this.provinceId = provinceId;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public String getInitial() {
            return initial;
        }

        public void setInitial(String initial) {
            this.initial = initial;
        }

        public int getIsOpen() {
            return isOpen;
        }

        public void setIsOpen(int isOpen) {
            this.isOpen = isOpen;
        }
    }

//    private List<ProvinceListBean> provinceList;
//    private List<AreaListBean> areaList;
//    private List<CityListBean> cityList;
//
//    @Override
//    public String toString() {
//        return "ProvinceCityArea{" +
//                "provinceList=" + provinceList +
//                ", areaList=" + areaList +
//                ", cityList=" + cityList +
//                '}';
//    }
//
//    public List<ProvinceListBean> getProvinceList() {
//        return provinceList;
//    }
//
//    public void setProvinceList(List<ProvinceListBean> provinceList) {
//        this.provinceList = provinceList;
//    }
//
//    public List<AreaListBean> getAreaList() {
//        return areaList;
//    }
//
//    public void setAreaList(List<AreaListBean> areaList) {
//        this.areaList = areaList;
//    }
//
//    public List<CityListBean> getCityList() {
//        return cityList;
//    }
//
//    public void setCityList(List<CityListBean> cityList) {
//        this.cityList = cityList;
//    }
//
//    public static class ProvinceListBean implements BaseBean {
//        @Override
//        public String toString() {
//            return "ProvinceListBean{" +
//                    "id=" + id +
//                    ", page='" + page + '\'' +
//                    ", ip='" + ip + '\'' +
//                    ", name='" + name + '\'' +
//                    ", order=" + order +
//                    ", initial='" + initial + '\'' +
//                    '}';
//        }
//
//        /**
//         * id : 1
//         * page :
//         * ip :
//         * name : 北京
//         * order : 0
//         * initial : B
//         */
//
//        private int id;
//        private String page;
//        private String ip;
//        private String name;
//        private int order;
//        private String initial;
//
//        public int getId() {
//            return id;
//        }
//
//        public void setId(int id) {
//            this.id = id;
//        }
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
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public int getOrder() {
//            return order;
//        }
//
//        public void setOrder(int order) {
//            this.order = order;
//        }
//
//        public String getInitial() {
//            return initial;
//        }
//
//        public void setInitial(String initial) {
//            this.initial = initial;
//        }
//    }
//
//    public static class AreaListBean implements BaseBean{
//        @Override
//        public String toString() {
//            return "AreaListBean{" +
//                    "id=" + id +
//                    ", name='" + name + '\'' +
//                    ", cityId=" + cityId +
//                    ", order=" + order +
//                    ", initial='" + initial + '\'' +
//                    '}';
//        }
//
//        /**
//         * id : 3
//         * name : 东城区
//         * cityId : 2
//         * order : 2
//         * initial : D
//         */
//
//        private int id;
//        private String name;
//        private int cityId;
//        private int order;
//        private String initial;
//
//        public int getId() {
//            return id;
//        }
//
//        public void setId(int id) {
//            this.id = id;
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
//        public int getCityId() {
//            return cityId;
//        }
//
//        public void setCityId(int cityId) {
//            this.cityId = cityId;
//        }
//
//        public int getOrder() {
//            return order;
//        }
//
//        public void setOrder(int order) {
//            this.order = order;
//        }
//
//        public String getInitial() {
//            return initial;
//        }
//
//        public void setInitial(String initial) {
//            this.initial = initial;
//        }
//    }
//
//    public static class CityListBean implements BaseBean {
//        @Override
//        public String toString() {
//            return "CityListBean{" +
//                    "id=" + id +
//                    ", page='" + page + '\'' +
//                    ", ip='" + ip + '\'' +
//                    ", name='" + name + '\'' +
//                    ", provinceId=" + provinceId +
//                    ", order=" + order +
//                    ", initial='" + initial + '\'' +
//                    ", isOpen=" + isOpen +
//                    '}';
//        }
//
//        /**
//         * id : 2
//         * page :
//         * ip :
//         * name : 北京市
//         * provinceId : 1
//         * order : 1
//         * initial : B
//         * isOpen : 0
//         */
//
//        private int id;
//        private String page;
//        private String ip;
//        private String name;
//        private int provinceId;
//        private int order;
//        private String initial;
//        private int isOpen;
//
//        public int getId() {
//            return id;
//        }
//
//        public void setId(int id) {
//            this.id = id;
//        }
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
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public int getProvinceId() {
//            return provinceId;
//        }
//
//        public void setProvinceId(int provinceId) {
//            this.provinceId = provinceId;
//        }
//
//        public int getOrder() {
//            return order;
//        }
//
//        public void setOrder(int order) {
//            this.order = order;
//        }
//
//        public String getInitial() {
//            return initial;
//        }
//
//        public void setInitial(String initial) {
//            this.initial = initial;
//        }
//
//        public int getIsOpen() {
//            return isOpen;
//        }
//
//        public void setIsOpen(int isOpen) {
//            this.isOpen = isOpen;
//        }
//    }

}
