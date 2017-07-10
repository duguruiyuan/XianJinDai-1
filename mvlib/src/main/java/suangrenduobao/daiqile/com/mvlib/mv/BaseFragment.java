package suangrenduobao.daiqile.com.mvlib.mv;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hwangjr.rxbus.RxBus;
import com.trello.rxlifecycle.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends RxFragment {

    protected Context mContext;

    protected View mRootView;

    private boolean isVisible;
    private boolean isPrepared;
    private boolean isFirstLoad = true;

    protected Activity mActivity;

    Unbinder bind;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mActivity = getActivity();

        if (null == mRootView) {
            mRootView = inflater.inflate(getContentViewLayoutID(), container, false);
             bind = ButterKnife.bind(this, mRootView);

            initConfig();

            initView();
        }

        isFirstLoad = true;
        isPrepared = true;
        isVisible = true;
        lazyLoad();

        return mRootView;
    }

    protected void initConfig() {
    }

    protected void initView() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        RxBus.get().register(this);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isPrepared = true;
        lazyLoad();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    protected void onVisible() {
        lazyLoad();
    }

    protected void onInvisible() {
    }

    protected void lazyLoad() {
        if (!isPrepared || !isVisible || !isFirstLoad) {
            return;
        }
        isFirstLoad = false;
        loadData();
    }

    protected abstract void loadData();

    protected abstract
    @NonNull
    @LayoutRes
    int getContentViewLayoutID();

    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
//        ButterKnife.unbind(this);
        RxBus.get().unregister(this);
    }

}
