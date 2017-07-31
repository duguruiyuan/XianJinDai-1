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
//        http://121.199.54.237:9591/xjd/front/user/loanAgreementPage?loanId=120
        if (getIntent().hasExtra(Constants.USERID)) {
            String url = Constants.BASE_URL + "xjd/front/user/loanAgreementPage?loanId=" + getIntent().getStringExtra(Constants.USERID);
            webView.loadUrl(url);
        } else {
            webView.loadUrl("file:///android_asset/index.html");
        }
    }
}
