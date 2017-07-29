package com.daiqile.xianjindai.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.FutureTarget;
import com.daiqile.xianjindai.Constants;
import com.daiqile.xianjindai.MyApplication;
import com.daiqile.xianjindai.R;
import com.daiqile.xianjindai.Result;
import com.daiqile.xianjindai.utils.ApiRequest;
import com.daiqile.xianjindai.utils.SelectorImageUtils;
import com.daiqile.xianjindai.view.ClearEditText;
import com.daiqile.xianjindai.view.TopBar;
import com.yanzhenjie.album.Album;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Subscriber;
import suangrenduobao.daiqile.com.mvlib.mv.BaseActivity;
import suangrenduobao.daiqile.com.mvlib.utils.ToastUtils;

import static android.R.id.list;
import static com.daiqile.xianjindai.R.id.imageView;

public class OtherOptionalActivity extends BaseActivity {
    @BindView(R.id.topbar)
    TopBar topbar;

    @BindView(R.id.iv_positive)
    ImageView ivPositive;

    @BindView(R.id.iv_reverse)
    ImageView ivReverse;

    @BindView(R.id.iv_person)
    ImageView ivPerson;

    @BindView(R.id.et_security_account)
    ClearEditText etSecurityAccount;

    @BindView(R.id.et_schooling)
    ClearEditText etSchooling;

    private ArrayList<String> imageList = new ArrayList<>();

    @Override
    protected boolean switchToolbar() {
        return false;
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_other_optional;
    }

    @Override
    protected void loadData() {
        topbar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {

            }
        });

        Glide.with(MyApplication.getInstance().getApplicationContext())
                .load(Constants.BASE_URL + "1")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(false)
                .error(R.drawable.my_photo).into(ivPositive);

        filePath.add(postion, Constants.BASE_URL + "1");
        Glide.with(MyApplication.getInstance().getApplicationContext())
                .load(Constants.BASE_URL + "2")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(false)
                .error(R.drawable.my_photo).into(ivReverse);

        filePath.add(postion, Constants.BASE_URL + "2");
        Glide.with(MyApplication.getInstance().getApplicationContext())
                .load(Constants.BASE_URL + "3")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(false)
                .error(R.drawable.my_photo).into(ivPerson);

        filePath.add(postion, Constants.BASE_URL + "3");
    }

    public static List<MultipartBody.Part> filesToMultipartBodyParts(List<File> files) {
        List<MultipartBody.Part> parts = new ArrayList<>(files.size());
        for (File file : files) {
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("图片上传", file.getName(), requestBody);
            parts.add(part);
        }
        return parts;
    }


    @OnClick({R.id.iv_positive, R.id.iv_reverse, R.id.iv_person, R.id.btn_card})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_positive:
                selectorImage(0);
                break;

            case R.id.iv_reverse:
                selectorImage(1);
                break;

            case R.id.iv_person:
                selectorImage(2);
                break;

            case R.id.btn_card:
                final String strAccout = etSecurityAccount.getText().toString();
                final String strScholling = etSchooling.getText().toString();

                final List<File> files = new ArrayList<>();

                if (TextUtils.isEmpty(strAccout) && TextUtils.isEmpty(strScholling) && files.size() > 0) {
                    ToastUtils.showMessage("你还没呀填写内容东西呢");
                } else {
                    new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            if (filePath.size() > 0) {
                                for (String s : filePath) {
                                    FutureTarget<File> fileFutureTarget = Glide.with(MyApplication.getInstance().getApplicationContext())
                                            .load(String.format("%s", s)).downloadOnly(100, 100);
                                    try {
                                        files.add(new File(fileFutureTarget.get().getAbsolutePath()));
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    } catch (ExecutionException e) {
                                        e.printStackTrace();
                                    }
                                }
                                ApiRequest.request(MyApplication.getInstance().apiService.optionalForUserInfo(MyApplication.getInstance().getUid(),
                                        strAccout, strScholling), new Subscriber<Result>() {
                                    @Override
                                    public void onCompleted() {

                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }

                                    @Override
                                    public void onNext(Result result) {
                                        if (result.isSuccess()) {
                                            finish();
                                        }
                                        ToastUtils.showMessage(result.getMsg());
                                    }
                                });
                            }
                        }
                    }.start();
                }


                break;
        }

    }

    ImageView imageView;
    int postion;


    private void selectorImage(int i) {
        switch (i) {
            case 0:
                imageView = ivPositive;
                postion = 0;
                break;
            case 1:
                postion = 1;
                imageView = ivReverse;
                break;
            case 2:
                postion = 2;
                imageView = ivPerson;
                break;
        }
        SelectorImageUtils.selectByofOne(mActivity, imageList);
    }

    List<String> filePath = new ArrayList<>();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 999) {
            if (resultCode == RESULT_OK) {
                File file = new File(Album.parseResult(data).get(0));
                Glide.with(MyApplication.getInstance().getApplicationContext())
                        .load(file)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .skipMemoryCache(false)
                        .error(R.drawable.my_photo).into(imageView);

                filePath.add(postion, file.getAbsolutePath());
            } else if (resultCode == RESULT_CANCELED) {

            }
        }
    }

}
