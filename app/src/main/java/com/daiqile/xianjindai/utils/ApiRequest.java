package com.daiqile.xianjindai.utils;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by zkw on 2017/7/20.
 */

public class ApiRequest {

    public static void request(Observable observable, Subscriber subscriber) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
