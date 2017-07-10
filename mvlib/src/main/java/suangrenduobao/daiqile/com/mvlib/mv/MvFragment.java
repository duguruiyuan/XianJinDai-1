package suangrenduobao.daiqile.com.mvlib.mv;

import android.support.annotation.Nullable;

import suangrenduobao.daiqile.com.mvlib.mv.presenter.IPresenter;
import suangrenduobao.daiqile.com.mvlib.mv.view.IView;
import suangrenduobao.daiqile.com.mvlib.utils.CreateUtil;


/**
 * Created by zkw on 2017/6/2.
 */

public abstract class MvFragment<V extends IView, P extends IPresenter<V>> extends BaseFragment
        implements IView, IMvpBase<V> {

    protected P presenter;

    @Override
    protected void initConfig() {
        presenter = CreateUtil.getT(this, 1);
        presenter.attachView(getMvpView());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView(getRetainInstance());
    }


    @Nullable
    @Override
    public V getMvpView() {
        return (V) this;
    }
}
