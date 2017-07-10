package suangrenduobao.daiqile.com.mvlib.mv.presenter;


import suangrenduobao.daiqile.com.mvlib.mv.view.IView;


public interface IPresenter<V extends IView> {

    void attachView(IView view);

    void detachView(boolean retainInstance);

    void detachView();
}
