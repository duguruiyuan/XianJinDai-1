//package com.daiqile.xianjindai.Fragment;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.util.Log;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.RelativeLayout;
//
//import com.bigkoo.convenientbanner.ConvenientBanner;
//import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
//import com.bigkoo.convenientbanner.holder.Holder;
//import com.bumptech.glide.Glide;
//import com.daiqile.xianjindai.Constants;
//import com.daiqile.xianjindai.MyApplication;
//import com.daiqile.xianjindai.R;
//import com.daiqile.xianjindai.activity.BorrowActivity;
//import com.daiqile.xianjindai.activity.LoginActivity;
//import com.daiqile.xianjindai.base.BaseFragment;
//import com.daiqile.xianjindai.model.Banner;
//import com.daiqile.xianjindai.view.TopBar;
//
//import java.util.ArrayList;
//
//import butterknife.BindView;
//import butterknife.OnClick;
//import butterknife.Unbinder;
//import rx.Subscriber;
//import rx.android.schedulers.AndroidSchedulers;
//import rx.schedulers.Schedulers;
//
////首页
//public class IndexFragment2 extends BaseFragment {
//    @BindView(R.id.topbar)//topbar
//            TopBar topBar;
//    @BindView(R.id.convenient_banner)//轮播图
//            ConvenientBanner mConvenientBanner;
//    @BindView(R.id.rl_person)//个人现金
//            RelativeLayout rlPerson;
//    @BindView(R.id.rl_home_liren)
//    RelativeLayout rlHomeLiren;
//    //    @BindView(R.id.rl_legal_person)//法人贷
////            RelativeLayout rlLegalPerson;
////    @BindView(R.id.rl_house)//房产贷
////            RelativeLayout rlHouse;
//    Unbinder unbinder;
//
//    private Activity mActivity;
//    private ArrayList<String> localImages = new ArrayList<>();//轮播图资源集合
//    private MyApplication application;
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        init();
//    }
//
//    @Override
//    public void init() {
//
//        if (MyApplication.getInstance().isLogin()) {
//            topBar.setRightButton(false);
////            topBar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
////                @Override
////                public void leftClick() {
////
////                }
////
////                @Override
////                public void rightClick() {
////                    startActivity(new Intent(mActivity, LoginActivity.class));
////                }
////            });
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
//
//    /**
//     * 点击事件
//     */
//    @OnClick({R.id.rl_person, R.id.rl_home_liren/*, R.id.rl_legal_person, R.id.rl_house*/})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.rl_person:
//                startActivity(new Intent(mActivity, BorrowActivity.class));
//                break;
//            case R.id.rl_home_liren:
//                startActivity(new Intent(mActivity, BorrowActivity.class));
//                break;
////            case R.id.rl_legal_person:
////                startActivity(new Intent(mActivity, BorrowActivity.class));
////                break;
////            case R.id.rl_house:
////                startActivity(new Intent(mActivity, BorrowActivity.class));
////                break;
//
//        }
//    }
//
//    /**
//     * 设置轮播图
//     *
//     * @return
//     */
//    private void setBanner() {
//        //localImages = initImageData();
//        mConvenientBanner.setPages(new CBViewHolderCreator<LocalImageHolderView>() {
//            @Override
//            public LocalImageHolderView createHolder() {
//                return new LocalImageHolderView();
//            }
//        }, localImages)
//                .setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused})
//                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
//        mConvenientBanner.startTurning(5000);
//    }
//
//    private void banner() {
//        application.apiService.getBanner()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<Banner>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(Banner banner) {
//                        localImages.clear();
//                        for (int i = 0; i < banner.getPpts().getList().size(); i++) {
//                            localImages.add(Constants.BASE_URL + "xjd/" + banner.getPpts().getList().get(i).getUrl());
//                            Log.e("sss", "onNext: " + Constants.BASE_URL + "xjd/" + banner.getPpts().getList().get(i).getUrl());
//                        }
//                        setBanner();
//                    }
//                });
//
//    }
//
//    /**
//     * 轮播图资源获取
//     *
//     * @param
//     * @return
//     */
////    private ArrayList<String> initImageData(List<String> mList) {
////        ArrayList<Banner.PptsBean.ListBean> list = new ArrayList<>();
////        for (int i = 0; i < mList.size(); i++) {
////            list.add(mList.get(i));
////        }
////        return list;
////    }
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        if (null!=unbinder) {
//            unbinder.unbind();
//        }
//    }
//
//    private class LocalImageHolderView implements Holder<String> {
//
//        private ImageView imageView;
//
//        @Override
//        public View createView(Context context) {
//            imageView = new ImageView(context);
//            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//            return imageView;
//        }
//
//        @Override
//        public void UpdateUI(Context context, int position, String data) {
//            Glide.with(mActivity).load(data).into(imageView);
//        }
//
//
//    }
//
//    @Override
//    public int getFragmentId() {
//        return R.layout.fragment_index;
//    }
//
//    @Override
//    public Object bindFragment() {
//        return this;
//    }
//}
