package com.daiqile.xianjindai;

import android.util.LruCache;

/**
 * Created by G150T on 2017/6/29.
 */
//没用
public class GenericCache {
    private static GenericCache genericCache;

    private LruCache<String,String> textCache;

    /*private LruCache<String,User> userCache;

    private LruCache<String,Invest> investLruCache;

    private LruCache<String,Category> categoryLruCache;//分类名的缓存 key是分类id

    private LruCache<String,Recommend> recommendLruCache;//分类商品的缓存 key是分类id+商品id

    private LruCache<String,Banner> bannerLruCache;//首页轮播图的缓存

    private LruCache<String,BankInfo> bankInfoLruCache;//绑定银行卡的缓存

    private LruCache<String,Person> personLruCache;*/

    private GenericCache(){

    }

    public static GenericCache getInstance(){
        if(genericCache == null){
            genericCache = new GenericCache();
        }
        return genericCache;
    }

//    public LruCache<String,List<Commodity>> getCommodityLruCache(){
//        if(commodityLruCache == null){
//            commodityLruCache = new LruCache<String,List<Commodity>>(1024*1024);
//        }
//        return commodityLruCache;
//    }

    public LruCache<String,String> getTextCache(){
        if(textCache == null){
            textCache = new LruCache<String,String>(1024*1024);
        }
        return textCache;
    }

    /*public LruCache<String,User> getUserCache(){
        if(userCache == null){
            userCache = new LruCache<>(1024 * 1024);
        }
        return userCache;
    }

    public LruCache<String,Invest> getInvestLruCache(){
        if(investLruCache == null){
            investLruCache = new LruCache<>(1024 * 1024);
        }
        return investLruCache;
    }

    public LruCache<String,Category> getCategoryLruCache(){
        if(categoryLruCache == null){
            categoryLruCache = new LruCache<>(1024 * 1024);
        }
        return categoryLruCache;
    }

    public LruCache<String,Recommend> getRecommendLruCache(){
        if(recommendLruCache == null){
            recommendLruCache = new LruCache<>(1024 * 1024);
        }
        return recommendLruCache;
    }

    public LruCache<String,BankInfo> getBankInfoLruCache(){
        if(bankInfoLruCache == null){
            bankInfoLruCache = new LruCache<>(1024 * 1024);
        }
        return bankInfoLruCache;
    }

    public LruCache<String,Person> getPersonLruCache(){
        if(personLruCache == null){
            personLruCache = new LruCache<>(1024 * 1024);
        }
        return personLruCache;
    }*/

    public void clearLruch(LruCache<String,Object> cache){
        if(cache != null && cache.size() > 0){
            cache.evictAll();
        }
    }
}
