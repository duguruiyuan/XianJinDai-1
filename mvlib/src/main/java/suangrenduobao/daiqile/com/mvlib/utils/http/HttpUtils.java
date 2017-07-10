package suangrenduobao.daiqile.com.mvlib.utils.http;

import android.support.annotation.NonNull;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpParams;
import com.kymjs.rxvolley.rx.Result;
import com.orhanobut.logger.Logger;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import suangrenduobao.daiqile.com.mvlib.utils.CreateUtil;
import suangrenduobao.daiqile.com.mvlib.utils.GsonUtil;


/**
 * Created by zkw on 2017/3/17.
 */

public class HttpUtils {

    private static volatile CompositeSubscription compositeSubscription = null;
    private static volatile Observable<Result> result = null;
    private static volatile Func1<Result, BaseBean> func;

    public static void addSubscription(@NonNull String url, String tag, HttpParams params, @NonNull final Subscriber subscriber) {
        result = new RxVolley.Builder()
                .url(url)
                .setTag(tag)
                .httpMethod(RxVolley.Method.GET)
                .params(params)
//                .cacheTime(1)
                .shouldCache(true)
                .contentType(RxVolley.ContentType.JSON)
                .shouldCache(false)
                .getResult();

        if (compositeSubscription == null) {
            synchronized (HttpUtils.class) {
                if (compositeSubscription == null) {
                    compositeSubscription = new CompositeSubscription();

//                    func = new Func1<Result, BaseBean>() {
//                        @Override
//                        public BaseBean call(Result result) {
//                            String s = new String(result.data);
//                            return (BaseBean) GsonUtil.GsonToBean(s, CreateUtil.getT(subscriber, 0).getClass());
//                        }
//                    };
                }
            }
        }
        func = new Func1<Result, BaseBean>() {
            @Override
            public BaseBean call(Result result) {
                String s = new String(result.data);
                return (BaseBean) GsonUtil.GsonToBean(s, CreateUtil.getT(subscriber, 0).getClass());
            }
        };
        compositeSubscription.add(result.map(func)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber)
        );
//        compositeSubscription = new CompositeSubscription();
//        compositeSubscription.add(result
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber)
//        );


    }


}
