package com.daiqile.xianjindai.Fragment;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;

import com.daiqile.xianjindai.Constants;
import com.daiqile.xianjindai.MyApplication;
import com.daiqile.xianjindai.R;
import com.daiqile.xianjindai.activity.BorrowActivity;
import com.daiqile.xianjindai.activity.LoginActivity;

import com.daiqile.xianjindai.model.Banner;
import com.daiqile.xianjindai.utils.ImageLoader;
import com.daiqile.xianjindai.view.TopBar;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import suangrenduobao.daiqile.com.mvlib.mv.BaseFragment;

//首页
public class IndexFragment extends BaseFragment {
    @BindView(R.id.topbar)//topbar
            TopBar topBar;
    @BindView(R.id.convenient_banner)//轮播图
            ConvenientBanner mConvenientBanner;
    @BindView(R.id.rl_person)//个人现金
            RelativeLayout rlPerson;
    @BindView(R.id.rl_home_liren)
    RelativeLayout rlHomeLiren;

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    private List<Banner.PptsBean.ListBean> imageList;


    @Override
    public void onStart() {
        super.onStart();
        initTopBar();
        initView();
    }

    private void initTopBar() {
        topBar.setRightButton(!MyApplication.getInstance().isLogin());
    }

    @Override
    protected void initView() {
        super.initView();

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (null != swipeRefresh && swipeRefresh.isRefreshing()) {
                    swipeRefresh.setRefreshing(false);
                }
                setBanner();
            }
        });

        topBar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {
            }

            @Override
            public void rightClick() {
                startActivity(new Intent(mActivity, LoginActivity.class));
            }
        });

        mConvenientBanner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (imageList.size() > 0) {
                    // TODO: 2017/7/10 图片的点击跳转
                    Log.d("IndexFragment", imageList.get(position).getId() + "");
                }

            }
        });
    }

//    @Override
//    public void init() {
//
//        if (MyApplication.getInstance().isLogin()) {
//            topBar.setRightButton(false);
//        }else {
//            topBar.setRightButton(true);
//            topBar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
//                @Override
//                public void leftClick() {
//
//                }
//
//                @Override
//                public void rightClick() {
//                    startActivity(new Intent(mActivity, LoginActivity.class));
//                }
//            });
//        }
//
//        mActivity = getActivity();
//        //setBanner();
////        topBar.setRightButton(true);
////        topBar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
////            @Override
////            public void leftClick() {
////
////            }
////
////            @Override
////            public void rightClick() {
////                startActivity(new Intent(mActivity, LoginActivity.class));
////            }
////        });
//
//        application = (MyApplication) mActivity.getApplication();
//        banner();
//    }

    /**
     * 点击事件 1 个人现金 2:法人贷 3:房产贷 4:丽人贷
     */
    @OnClick({R.id.rl_person, R.id.rl_home_liren/*, R.id.rl_legal_person, R.id.rl_house*/})
    public void onClick(View view) {
        Intent intent = new Intent();
        intent.setClass(mActivity, BorrowActivity.class);
        switch (view.getId()) {
            case R.id.rl_person:
                intent.putExtra(Constants.LOANTYPE, "1");
//                intent = new Intent(mActivity, BorrowActivity.class);
//                intent.setClass(mActivity,BorrowActivity.class);
                break;
            case R.id.rl_home_liren:
                intent.putExtra(Constants.LOANTYPE, "4");
//                startActivity(new Intent(mActivity, BorrowActivity.class));
                break;
//            case R.id.rl_legal_person:
//                startActivity(new Intent(mActivity, BorrowActivity.class));
//                break;
//            case R.id.rl_house:
//                startActivity(new Intent(mActivity, BorrowActivity.class));
//                break;

        }
        startActivity(intent);
    }

    /**
     * 设置轮播图
     *
     * @return
     */
    private void setBanner() {
        if (null != imageList && imageList.size() > 0) {
            mConvenientBanner.setPages(new CBViewHolderCreator<LocalImageHolderView>() {
                @Override
                public LocalImageHolderView createHolder() {
                    return new LocalImageHolderView();
                }
            }, imageList)
                    .setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused})
                    .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);

            mConvenientBanner.startTurning(5000);
        }
    }

    private void banner() {
        MyApplication.getInstance().apiService.getBanner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Banner>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Banner banner) {
                        imageList = banner.getPpts().getList();
                        setBanner();
                    }
                });

    }


    @Override
    protected void loadData() {
        banner();
    }

    @NonNull
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_index;
    }

    private class LocalImageHolderView implements Holder<Banner.PptsBean.ListBean> {

        private ImageView imageView;

        @Override
        public View createView(Context context) {
            if (null == imageView) {
                Log.d("LocalImageHolderView", "imageview");

                imageView = new ImageView(context);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            }
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, Banner.PptsBean.ListBean data) {
//            Glide.with(mActivity).load(Constants.BASE_URL + "xjd/" + data.getUrl()).into(imageView);
            ImageLoader.getInstance().load(data.getUrl(), imageView, "");
        }
    }

}
