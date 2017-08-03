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
import com.daiqile.xianjindai.utils.AddressListUtils;
import com.daiqile.xianjindai.utils.ToastUtil;

import com.daiqile.xianjindai.view.ViewPagerFix;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import haixianwang.daiqile.com.baiqishi.PermissionUtils;
import okhttp3.Call;
import suangrenduobao.daiqile.com.mvlib.mv.BaseActivity;
import suangrenduobao.daiqile.com.mvlib.utils.GsonUtil;

import static com.yintong.secure.e.m.j.s;

public class MainActivity extends BaseActivity {


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

//    private int p = 0;

//    public static String sendPost(String url, String param) {
//        PrintWriter out = null;
//        BufferedReader in = null;
//        String result = "";
//        try {
//            URL realUrl = new URL(url);
//            // 打开和URL之间的连接
//            URLConnection conn = realUrl.openConnection();
//            // 设置通用的请求属性
//            conn.setRequestProperty("accept", "*/*");
//            conn.setRequestProperty("connection", "Keep-Alive");
//            conn.setRequestProperty("user-agent",
//                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//            // 发送POST请求必须设置如下两行
//            conn.setDoOutput(true);
//            conn.setDoInput(true);
//            // 获取URLConnection对象对应的输出流
//            out = new PrintWriter(conn.getOutputStream());
//            // 发送请求参数
//            out.print(param);
//            // flush输出流的缓冲
//            out.flush();
//            // 定义BufferedReader输入流来读取URL的响应
//            in = new BufferedReader(
//                    new InputStreamReader(conn.getInputStream()));
//            String line;
//            while ((line = in.readLine()) != null) {
//                result += line;
//            }
//        } catch (Exception e) {
//            System.out.println("发送 POST 请求出现异常！" + e);
//            e.printStackTrace();
//        }
//        //使用finally块来关闭输出流、输入流
//        finally {
//            try {
//                if (out != null) {
//                    out.close();
//                }
//                if (in != null) {
//                    in.close();
//                }
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
//        return result;
//    }

    @Override
    protected void initConfig() {
        super.initConfig();

        AddressListUtils.readAddress(MyApplication.getInstance());


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
//                        Log.d("MainActivity", "onTabUnselected:" + position);
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
//
//    @Override
//    public void onSuccess(String s) {
//        Log.d("MainActivity", s + "成");
//    }
//
//    @Override
//    public void onFailure(String s, String s1) {
//        Log.d("MainActivity", s + " " + s1);
//    }

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
