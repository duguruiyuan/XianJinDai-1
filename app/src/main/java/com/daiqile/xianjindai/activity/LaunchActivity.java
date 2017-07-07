package com.daiqile.xianjindai.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.daiqile.xianjindai.MainActivity;
import com.daiqile.xianjindai.MyApplication;
import com.daiqile.xianjindai.R;
import com.daiqile.xianjindai.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 启动图页面
 */
public class LaunchActivity extends BaseActivity {

    @BindView(R.id.img_launch)
    ImageView imgLaunch;
    private Activity mActivity;
    private MyApplication application ;

    @Override
    public void init() {
        mActivity = LaunchActivity.this;
        application = (MyApplication) getApplication();
        animate(LaunchActivity.this);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_launch;
    }

    @Override
    public Activity bindActivity() {
        return this;
    }

    private void animate(final Context context) {
        ScaleAnimation animation = new ScaleAnimation(1.0f, 1.1f, 1.0f, 1.1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(3000);
        animation.setFillAfter(true);
        animation.start();
        imgLaunch.setAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(context, MainActivity.class));
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
