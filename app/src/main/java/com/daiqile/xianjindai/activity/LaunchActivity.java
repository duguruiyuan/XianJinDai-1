package com.daiqile.xianjindai.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.daiqile.xianjindai.Constants;
import com.daiqile.xianjindai.MainActivity;
import com.daiqile.xianjindai.MyApplication;
import com.daiqile.xianjindai.R;
import com.daiqile.xianjindai.base.BaseActivity;
import com.daiqile.xianjindai.model.ProvinceCityArea;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import suangrenduobao.daiqile.com.mvlib.utils.FileUtils;

/**
 * 启动图页面
 */
public class LaunchActivity extends BaseActivity {

    @BindView(R.id.img_launch)
    ImageView imgLaunch;
//    private Activity mActivity;
//    private MyApplication application ;

    @Override
    public void init() {
//        mActivity = LaunchActivity.this;
//        application = (MyApplication) getApplication();
        animate(LaunchActivity.this);

        new Thread() {
            @Override
            public void run() {
                super.run();
                final File provnceFile = new File(Environment.getExternalStorageDirectory(), Constants.PROVINCELIST);
                final File cityFile = new File(Environment.getExternalStorageDirectory(), Constants.CITYLIST);
                final File arealFile = new File(Environment.getExternalStorageDirectory(), Constants.AREALIST);

                if (!provnceFile.exists() || !cityFile.exists() || !arealFile.exists()) {
                    MyApplication.getInstance().apiService.getProvinceList()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Subscriber<ProvinceCityArea>() {
                                @Override
                                public void onCompleted() {

                                }

                                @Override
                                public void onError(Throwable e) {
                                    Log.d("AddressFrameLayout", "e:" + e);
                                }

                                @Override
                                public void onNext(ProvinceCityArea province) {
                                    FileUtils.writeObj(cityFile, province.getCityList());
                                    FileUtils.writeObj(arealFile, province.getAreaList());
                                    FileUtils.writeObj(provnceFile, province.getProvinceList());
                                }
                            });
                }
            }
        }.start();

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
