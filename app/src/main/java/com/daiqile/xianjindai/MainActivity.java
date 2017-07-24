package com.daiqile.xianjindai;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.bqs.risk.df.android.OnBqsDFListener;
import com.daiqile.xianjindai.Fragment.AccountFragment;
import com.daiqile.xianjindai.Fragment.BorrowingRecordFragment;
import com.daiqile.xianjindai.Fragment.IndexFragment;

import com.daiqile.xianjindai.activity.LoginActivity;
import com.daiqile.xianjindai.utils.ToastUtil;

import com.daiqile.xianjindai.view.ViewPagerFix;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import haixianwang.daiqile.com.baiqishi.PermissionUtils;
import suangrenduobao.daiqile.com.mvlib.mv.BaseActivity;

public class MainActivity extends BaseActivity implements OnBqsDFListener {


    /* @BindView(R.id.topbar)
     TopBar topbar;*/
    @BindView(R.id.pager)
    ViewPagerFix mPager;
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar mBottomNavigationBar;

    //    private Activity mActivity;
    private long clickTime = 0;

    private List<Fragment> fragments = new ArrayList<>();
    // private MyApplication application;
    private MainPagerAdapter adapter;

    private int p = 0;

    @Override
    protected void initConfig() {
        super.initConfig();

        mPager.setScrollable(false);
        mPager.setOffscreenPageLimit(2);

        fragments.add(new IndexFragment());
        fragments.add(new BorrowingRecordFragment());
        fragments.add(new AccountFragment());

        adapter = new MainPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(adapter);

       /* topbar.setRightButton(true);
        p = 0;*/
        //设置底部导航栏
        mBottomNavigationBar
                .setMode(BottomNavigationBar.MODE_FIXED).
                setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .setActiveColor(R.color.def_reached_color1)
                .setInActiveColor(R.color.black);
        mBottomNavigationBar.setBarBackgroundColor(R.color.material_white);
        mBottomNavigationBar.addItem(new
                BottomNavigationItem(R.mipmap.icon_nav_home, getString(R.string.home_page)
        )).addItem(new BottomNavigationItem(R.mipmap.icon_nav_borrow, getString(R.string.borrow)
        )).addItem(new BottomNavigationItem(R.mipmap.icon_nav_user, getString(R.string.account))).
                setFirstSelectedPosition(0).initialise();

        mBottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
                                                        @Override
                                                        public void onTabSelected(int position) {
                                                            if (MyApplication.getInstance().isLogin()) {
                                                                mPager.setCurrentItem(position);
                                                            } else {
                                                                Log.d("MainActivity", "a");
                                                                startActivity(new Intent(mActivity, LoginActivity.class));
//                                                                mBottomNavigationBar.selectTab(0);
                                                                mBottomNavigationBar.setFirstSelectedPosition(0).initialise();
                                                            }
                                                        }

                                                        @Override
                                                        public void onTabUnselected(int position) {
                                                        }

                                                        @Override
                                                        public void onTabReselected(int position) {
                                                        }
                                                    }

        );

    }

    @Override
    protected boolean switchToolbar() {
        return false;
    }

//    @Override
//    public void init() {
//
//
//
//    }

//    @Override
//    protected int initLayout() {
//        return 0;
//    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onSuccess(String s) {
        Log.d("MainActivity", s + "成");
    }

    @Override
    public void onFailure(String s, String s1) {
        Log.d("MainActivity", s + " " + s1);
    }

    private class MainPagerAdapter extends FragmentPagerAdapter {

        public MainPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

    }

    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - clickTime > 2000) {
            ToastUtil.showToast(mActivity, R.string.click_again_exit);
            clickTime = currentTime;
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public int initLayout() {
        return R.layout.activity_main;
    }

//    @Override
//    public Activity bindActivity() {
//        return this;
//    }


}
