package suangrenduobao.daiqile.com.mvlib.utils.http;

import com.kymjs.rxvolley.http.VolleyError;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;


public abstract class ApiCallback<M> extends Subscriber<M> {

    public abstract void onSuccess(M m);

    public abstract void onFailure(String msg);

    public abstract void onFinish();

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            int code = httpException.code();
            onFailure(resultMsg(code));
        } else if (e instanceof VolleyError) {
            VolleyError httpException = (VolleyError) e;
            int code = httpException.networkResponse.statusCode;
            onFailure(resultMsg(code));
        } else {
            onFailure(e.getMessage());
        }
        onFinish();
    }

    private String resultMsg(int code) {
        String msg = null;
        switch (code) {
            case 504:
                msg = "网络不给力";
                break;

            case 502:
            case 404:
                msg = "服务器异常，请稍候再试";
                break;
            default:
                msg = "网络不好，请稍后再试!";
                break;
        }
        return msg;
    }

    @Override
    public void onNext(M m) {
        onSuccess(m);
    }

    @Override
    public void onCompleted() {
        onFinish();
    }


}
