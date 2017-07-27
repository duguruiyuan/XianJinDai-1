package com.daiqile.xianjindai.utils;

import suangrenduobao.daiqile.com.mvlib.utils.http.BaseBean;

/**
 * Created by zkw on 2017/7/27.
 */

public interface CallBack {
    void onNext(BaseBean baseBean);

    void onError();
}
