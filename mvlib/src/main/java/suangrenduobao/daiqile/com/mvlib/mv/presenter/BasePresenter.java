package suangrenduobao.daiqile.com.mvlib.mv.presenter;

import java.lang.ref.WeakReference;

import suangrenduobao.daiqile.com.mvlib.mv.view.IView;


public class BasePresenter<V extends IView> implements IPresenter<V> {

    private WeakReference<V> viewRef;

    public BasePresenter() {
    }

    @Override
    public void attachView(IView view) {
        viewRef = new WeakReference<>((V) view);
    }

    @Override
    public void detachView(boolean retainInstance) {
        if (null != viewRef) {
            viewRef.clear();
            viewRef = null;
        }
    }

    @Override
    public void detachView() {
        if (null != viewRef) {
            viewRef.clear();
            viewRef = null;
        }
    }


    public V getView() {
        return viewRef == null ? null : viewRef.get();
    }

    public boolean isViewAttached() {
        return viewRef != null && viewRef.get() != null;
    }
}
