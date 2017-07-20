package com.daiqile.xianjindai;

import android.graphics.Color;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.daiqile.xianjindai.view.TopBar;

import butterknife.BindView;
import suangrenduobao.daiqile.com.mvlib.mv.BaseActivity;


public class DealActivity extends BaseActivity {

    @BindView(R.id.topbar)
    TopBar topbar;
    @BindView(R.id.webview)
    WebView webView;


    @Override
    protected boolean switchToolbar() {
        return false;
    }

    @Override
    protected void initConfig() {
        super.initConfig();
        topbar.setRightButton(true);
        topbar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {
            }
        });

        WebSettings webSettings = webView.getSettings();
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webView.setBackgroundColor(Color.TRANSPARENT);
    }


    @Override
    protected int initLayout() {
        return R.layout.activity_deal;
    }

    @Override
    protected void loadData() {
        webView.loadUrl("file:///android_asset/index.html");
    }
}
