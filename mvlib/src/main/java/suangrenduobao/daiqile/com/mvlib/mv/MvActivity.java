package suangrenduobao.daiqile.com.mvlib.mv;

import suangrenduobao.daiqile.com.mvlib.mv.presenter.IPresenter;
import suangrenduobao.daiqile.com.mvlib.mv.view.IView;
import suangrenduobao.daiqile.com.mvlib.utils.CreateUtil;


public abstract class MvActivity<V extends IView, P extends IPresenter<V>>
        extends BaseActivity implements IView, IMvpBase<V> {

    protected P presenter;

    @Override
    protected void initConfig() {

        presenter = CreateUtil.getT(this, 1);

        presenter.attachView(getMvpView());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public V getMvpView() {
        return (V) this;
    }

}
//package suangrenduobao.daiqile.com.mvlib.mv;
//
//import suangrenduobao.daiqile.com.mvlib.mv.presenter.IPresenter;
//import suangrenduobao.daiqile.com.mvlib.mv.view.IView;
//import suangrenduobao.daiqile.com.mvlib.utils.CreateUtil;
//
//
//public abstract class MvActivity<V extends IView, P extends IPresenter<V>>
//        extends BaseActivity implements IView, IMvpBase<V> {
//
//    protected P presenter;
//
//    @Override
//    protected void initConfig() {
//
//        presenter = CreateUtil.getT(this, 1);
//
//        presenter.attachView(getMvpView());
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        presenter.detachView();
//    }
//
//    @Override
//    public V getMvpView() {
//        return (V) this;
//    }
//
//}
