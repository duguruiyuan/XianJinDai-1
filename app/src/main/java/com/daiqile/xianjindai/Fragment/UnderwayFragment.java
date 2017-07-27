package com.daiqile.xianjindai.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.daiqile.xianjindai.BorrowingRecordCallback;
import com.daiqile.xianjindai.Fragment.adapter.AllBorrowAdapter;
import com.daiqile.xianjindai.Fragment.bean.AllBorrowBean;
import com.daiqile.xianjindai.MyApplication;
import com.daiqile.xianjindai.R;

import com.daiqile.xianjindai.activity.RepaymentActivity;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import suangrenduobao.daiqile.com.mvlib.mv.BaseFragment;
import suangrenduobao.daiqile.com.mvlib.utils.DensityUtils;
import suangrenduobao.daiqile.com.mvlib.utils.ToastUtils;

/**
 * 借款记录--进行中
 */
@SuppressLint("ValidFragment")
public class UnderwayFragment extends BaseFragment {

    @BindView(R.id.easy_recycler_view)
    EasyRecyclerView easyRecyclerView;

    private AllBorrowAdapter adapter;

    private boolean sIsScrolling;

    private int page = 1;

    BorrowingRecordCallback callback;

    public UnderwayFragment(BorrowingRecordCallback callback) {
        this.callback = callback;
    }

    @NonNull
    @Override
    public int getContentViewLayoutID() {
        return R.layout.fragment_all_borrow;
    }


    @Override
    protected void loadData() {
        MyApplication.getInstance().apiService.requestAllBorrow(MyApplication.getInstance().getUid())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<AllBorrowBean>() {

            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showMessage(getResources().getString(R.string.str_http_network_error));
                closeRefreshing();
            }

            @Override
            public void onNext(AllBorrowBean allBorrowBean) {
                List<AllBorrowBean.ListBean> list = allBorrowBean.getList();
                if (null != list && list.size() > 0) {
                    //判断是否是进行中
                    Collections.reverse(list);
                    for (AllBorrowBean.ListBean listBean : list) {
                        if (0 == listBean.getStatus() || 1 == listBean.getStatus() || 2 == listBean.getStatus()) {
                            adapter.add(listBean);
                        }
                    }
                    adapter.notifyDataSetChanged();
                    adapter.stopMore();
                }else{
                    adapter.stopMore();
                }
                closeRefreshing();
            }
        });
//        HttpUtils.addSubscription("", "", null, new ApiCallback<AllBorrowBean>() {
//            @Override
//            public void onSuccess(AllBorrowBean allBorrowBean) {
//                Log.d("AllBorrowFragment", "allBorrowBean:" + allBorrowBean);
//
//                List<AllBorrowBean.ListBean> list = allBorrowBean.getList();
//                if (null != list && list.size() > 0) {
//                    adapter.addAll(allBorrowBean.getList());
//                    adapter.notifyDataSetChanged();
//                } else {
//                    adapter.stopMore();
//                }
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                ToastUtils.showMessage(getResources().getString(R.string.str_http_network_error));
//            }
//
//            @Override
//            public void onFinish() {
//                closeRefreshing();
//            }
//        });
    }


    private void closeRefreshing() {
        if (null != easyRecyclerView && easyRecyclerView.getSwipeToRefresh().isRefreshing()) {
            easyRecyclerView.getSwipeToRefresh().setRefreshing(false);
        }
    }

    @Override
    protected void initView() {
        super.initView();

        final LinearLayoutManager manager = new LinearLayoutManager(mContext);
        easyRecyclerView.setLayoutManager(manager);
        easyRecyclerView.setRefreshingColorResources(R.color.colorAccent);
        easyRecyclerView.setLayoutManager(manager);
        adapter = new AllBorrowAdapter(mActivity);

        easyRecyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                if (null != adapter && adapter.getAllData().size() > 0) {
                    adapter.clear();
                }
                loadData();
            }
        });
        adapter.setNoMore(R.layout.view_nodata);
        adapter.setError(R.layout.view_error);
        adapter.setMore(R.layout.view_load_more, new RecyclerArrayAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                page += 1;
                loadData();
            }
        });

        DividerDecoration itemDecoration = new DividerDecoration(Color.GRAY, DensityUtils.dp2px(mActivity, 1f), 0, 0);
        itemDecoration.setDrawLastItem(true);
        easyRecyclerView.addItemDecoration(itemDecoration);

        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                callback.onCallBack(adapter.getItem(position));
            }
        });
        easyRecyclerView.setAdapter(adapter);
        easyRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING || newState == RecyclerView.SCROLL_STATE_SETTLING) {
                    sIsScrolling = true;
                    Glide.with(mActivity.getApplicationContext()).pauseRequests();
                } else if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (sIsScrolling) {
                        Glide.with(mActivity.getApplicationContext()).resumeRequests();
                    }
                    sIsScrolling = false;
                }
            }
        });


    }
}
