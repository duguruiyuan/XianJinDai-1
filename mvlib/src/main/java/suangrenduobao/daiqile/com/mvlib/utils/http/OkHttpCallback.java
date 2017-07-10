package suangrenduobao.daiqile.com.mvlib.utils.http;

import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;
import suangrenduobao.daiqile.com.mvlib.utils.CreateUtil;

/**
 * Created by zkw on 2017/6/2.
 * Okhttp
 */


public abstract class OkHttpCallback<T> extends Callback<T> {

    @Override
    public T parseNetworkResponse(Response response, int id) throws Exception {
        String string = response.body().string();
        Gson gson = new Gson();
        return (T) gson.fromJson(string, CreateUtil.getT(this, 0).getClass());
    }


}
