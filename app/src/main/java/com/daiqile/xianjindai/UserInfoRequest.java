package com.daiqile.xianjindai;

import com.daiqile.xianjindai.Fragment.bean.UserInfoBean;
import com.daiqile.xianjindai.utils.ApiRequest;
import com.daiqile.xianjindai.utils.CallBack;

import rx.Subscriber;
import suangrenduobao.daiqile.com.mvlib.utils.SPUtils;


/**
 * Created by zkw on 2017/7/27.
 */

public class UserInfoRequest {


    public static void requestUserInfo(final CallBack callBack) {
        ApiRequest.request(MyApplication.getInstance().apiService.
                requestUserMyinfo(MyApplication.getInstance().getUid(),
                        SPUtils.get(MyApplication.getInstance(), Constants.PHONE, "").toString(),
                        SPUtils.get(MyApplication.getInstance(), Constants.LOGINPASSWORD, "").toString()), new Subscriber<UserInfoBean>() {

            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                callBack.onError();
            }

            @Override
            public void onNext(UserInfoBean userInfoBean) {
                callBack.onNext(userInfoBean);
            }
        });


    }
}
