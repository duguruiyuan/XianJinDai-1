package com.daiqile.xianjindai.activity;

import android.content.Intent;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daiqile.xianjindai.Constants;
import com.daiqile.xianjindai.MyApplication;
import com.daiqile.xianjindai.R;

import com.daiqile.xianjindai.activity.adapter.BankListAdapter;
import com.daiqile.xianjindai.activity.bean.BankInfoList;

import com.daiqile.xianjindai.view.TopBar;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.jude.easyrecyclerview.EasyRecyclerView;

import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import suangrenduobao.daiqile.com.mvlib.mv.BaseActivity;


import suangrenduobao.daiqile.com.mvlib.utils.ToastUtils;

public class BankCardActivity extends BaseActivity {

    @BindView(R.id.topbar)
    TopBar topbar;
//    @BindView(R.id.iv_bind)
//    ImageView ivBind;

    @BindView(R.id.rl_data)
    EasyRecyclerView easyRecyclerView;

    BankListAdapter adapter;


    @Subscribe(tags = {@Tag(Constants.REFRESH_BANK)})
    public void refresh(String s) {
        if (adapter.getAllData().size() > 0) {
            adapter.clear();
        }
        loadData();
    }

    @Override
    protected void initConfig() {
        super.initConfig();
        topbar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {
            }
        });

        final LinearLayoutManager manager = new LinearLayoutManager(mContext);
        easyRecyclerView.setLayoutManager(manager);
        easyRecyclerView.setRefreshingColorResources(R.color.colorAccent);
        adapter = new BankListAdapter(mContext);
        easyRecyclerView.setAdapter(adapter);

//        DividerDecoration itemDecoration = new DividerDecoration(Color.GRAY, DensityUtils.dp2px(mContext, 1f), 0, 0);
//        itemDecoration.setDrawLastItem(true);
//        easyRecyclerView.addItemDecoration(itemDecoration);

//        adapter.setNoMore(R.layout.view_nodata);
        adapter.setError(R.layout.view_error);

        easyRecyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (adapter.getAllData().size() > 0) {
                    adapter.clear();
                }
                loadData();
            }
        });

        adapter.addFooter(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                return LayoutInflater.from(mActivity).inflate(R.layout.footview_bank_card, parent, false);
            }

            @Override
            public void onBindView(View headerView) {
                headerView.findViewById(R.id.iv_bind).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(mActivity, BindCardActivity.class));
                    }
                });
            }
        });

//        adapter.setMore(R.layout.view_load_more, new RecyclerArrayAdapter.OnLoadMoreListener() {
//            @Override
//            public void onLoadMore() {
////                loadData();
//            }
//        });
    }


    @Override
    protected int initLayout() {
        return R.layout.activity_bank_card;
    }

    @Override
    protected void loadData() {
        Map<String, String> map = new HashMap<>();
        map.put("userId", MyApplication.getInstance().getUid());
        map.put(Constants.PHONE, MyApplication.getInstance().getPhone());
        map.put(Constants.LOGINPASSWORD, MyApplication.getInstance().getLoginPassword());

        MyApplication.getInstance().apiService.getBankLists(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<BankInfoList>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showMessage("网络不好");
                if (easyRecyclerView.getSwipeToRefresh().isRefreshing()) {
                    easyRecyclerView.getSwipeToRefresh().setRefreshing(false);
                }
            }

            @Override
            public void onNext(BankInfoList bankInfoList) {
                // TODO: 2017/7/10 银行卡列表
                Log.d("BankCardActivity", "银行卡列表");
                List<BankInfoList.BanksBean> banks = bankInfoList.getBanks();
                if (banks.size() > 0) {
                    adapter.addAll(banks);
                    adapter.notifyDataSetChanged();
//                    adapter.stopMore();
                }
            }
        });
    }

    @Override
    protected boolean switchToolbar() {
        return false;
    }

}
