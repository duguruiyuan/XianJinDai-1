package suangrenduobao.daiqile.com.mvlib.mv;


import suangrenduobao.daiqile.com.mvlib.mv.view.IView;

public interface IMvpBase<V extends IView> {

    V getMvpView();
}
