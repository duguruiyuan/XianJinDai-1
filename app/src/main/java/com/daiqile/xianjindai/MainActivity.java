package com.daiqile.xianjindai;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.bqs.crawler.cloud.sdk.ChsiAction;
import com.bqs.crawler.cloud.sdk.MnoAction;
import com.bqs.crawler.cloud.sdk.OnChsiLoginListener;
import com.bqs.crawler.cloud.sdk.OnMnoLoginListener;
import com.bqs.crawler.cloud.sdk.OnSDKInitListener;
import com.bqs.crawler.cloud.sdk.SDKInitialize;
import com.bqs.risk.df.android.BqsDF;
import com.bqs.risk.df.android.BqsParams;
import com.bqs.risk.df.android.OnBqsDFContactsListener;
import com.bqs.risk.df.android.OnBqsDFListener;
import com.daiqile.xianjindai.Fragment.AccountFragment;
import com.daiqile.xianjindai.Fragment.BorrowingRecordFragment;
import com.daiqile.xianjindai.Fragment.IndexFragment;

import com.daiqile.xianjindai.activity.LoginActivity;
import com.daiqile.xianjindai.utils.ToastUtil;

import com.daiqile.xianjindai.view.ViewPagerFix;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import haixianwang.daiqile.com.baiqishi.PermissionUtils;
import okhttp3.Call;
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
//
//        OkHttpUtils.post().
//                url("http://credit.baiqishi.com/clweb/api/mno/getoriginal").
//                addParams("verifyKey", "0714a66e025d4f999dccab65ff09078c").
//                addParams("partnerId", "daiqile").
//                addParams("name", "郭远逵").
//                addParams("certNo", "362428199301052713").
//                addParams("mobile", "15949629529")
//                .build().execute(new StringCallback() {
//            @Override
//            public void onError(Call call, Exception e, int id) {
//                String s = call.toString();
//                Log.d("MainActivity", s);
//            }
//
//            @Override
//            public void onResponse(String response, int id) {
//                Log.d("MainActivity", response);
//            }
//        });

//        BqsParams params = new BqsParams();
//        params.setPartnerId("daiqile");
//        BqsDF.initialize(this, params);
//        BqsDF.addOnBqsDFContactsListener(new OnBqsDFContactsListener() {
//            @Override
//            public void a(boolean b) {
//                Log.d("ExampleInstrumentedTest", "b:" + b);
//            }
//
//            @Override
//            public void onSuccess(String s) {
//                Log.d("ExampleInstrumentedTest", s);
//            }
//
//            @Override
//            public void onFailure(String s, String s1) {
//                Log.d("ExampleInstrumentedTest", s + s1);
//            }
//        });
//        BqsDF.setOnBqsDFListener(new OnBqsDFListener() {
//            @Override
//            public void onSuccess(String s) {
//                Log.d("ExampleInstrumentedTest1","onSuccess:"+ s);
//            }
//
//            @Override
//            public void onFailure(String s, String s1) {
//                Log.d("ExampleInstrumentedTest1", "onFailure:"+s + s1);
//            }
//        });

//        PermissionUtils.initialize("guo", "362428199301052713", "15949629529", new OnSDKInitListener() {
//            @Override
//            public void onSuccess() {
//                Log.d("MainActivity", "成功");
//            }
//
//            @Override
//            public void onFailure(String s, String s1) {
//
//            }
//        });
//        PermissionUtils.service("148880", new OnMnoLoginListener() {
//            @Override
//            public void onInputAuthSmsCode() {
//                Log.d("MainActivity", "onInputAuthSmsCode");
//            }
//
//            @Override
//            public void onInputLoginSmsCode() {
//                Log.d("MainActivity", "onInputLoginSmsCode");
//            }
//
//            @Override
//            public void onLoginSuccess() {
//                Log.d("MainActivity", "onLoginSuccess");
//            }
//
//            @Override
//            public void onLoginFailure(String s, String s1) {
//                Log.d("MainActivity", "onLoginFailure" + s + s1);
//            }
//        });
//        ChsiAction.login("15949629529", "362428199301052713", new OnChsiLoginListener() {
//            @Override
//            public void onInputCaptcha(byte[] bytes, String s) {
//                Log.d("MainActivity", "onInputCaptcha" + s);
//            }
//
//            @Override
//            public void onLoginSuccess() {
//                Log.d("MainActivity", "成功");
//            }
//
//            @Override
//            public void onLoginFailure(String s, String s1) {
//                Log.d("MainActivity", "onLoginFailure:" + s + s1);
//            }
//        });
//        MnoAction.login("15949629529", "148880", new OnMnoLoginListener() {
//            @Override
//            public void onInputAuthSmsCode() {
//
//            }
//
//            @Override
//            public void onInputLoginSmsCode() {
//
//            }
//
//            @Override
//            public void onLoginSuccess() {
//
//            }
//
//            @Override
//            public void onLoginFailure(String s, String s1) {
//                Log.d("MainActivity", "onLoginFailure:" + s + s1);
//            }
//        });


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

        mBottomNavigationBar.setTabSelectedListener(
                new BottomNavigationBar.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(int position) {
                        mPager.setCurrentItem(position);
                    }

                    @Override
                    public void onTabUnselected(int position) {
                        Log.d("MainActivity", "onTabUnselected:" + position);
//                        mBottomNavigationBar.setFirstSelectedPosition(0).setActiveColor(R.color.def_reached_color1);//.selectTab(0);

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
