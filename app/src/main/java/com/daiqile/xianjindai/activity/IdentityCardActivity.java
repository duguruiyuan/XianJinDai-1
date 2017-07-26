package com.daiqile.xianjindai.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.daiqile.xianjindai.Constants;
import com.daiqile.xianjindai.MyApplication;
import com.daiqile.xianjindai.R;
import com.daiqile.xianjindai.Result;
import com.daiqile.xianjindai.base.BaseActivity;
import com.daiqile.xianjindai.utils.ImageToString;
import com.daiqile.xianjindai.utils.SelectorImageUtils;
import com.daiqile.xianjindai.view.TopBar;
import com.wevey.selector.dialog.DialogOnItemClickListener;
import com.wevey.selector.dialog.NormalSelectionDialog;
import com.yanzhenjie.album.Album;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import suangrenduobao.daiqile.com.mvlib.utils.FileUtils;
import suangrenduobao.daiqile.com.mvlib.utils.GsonUtil;
import suangrenduobao.daiqile.com.mvlib.utils.ToastUtils;

import static com.daiqile.xianjindai.R.id.imageView;


/**
 * 身份认证页面
 */
public class IdentityCardActivity extends BaseActivity {


    @BindView(R.id.topbar)//topbar
            TopBar topbar;
    @BindView(R.id.ll_front_card)//身份证正面
            LinearLayout llFrontCard;
    @BindView(R.id.ll_contrary_card)//身份证反面
            LinearLayout llContraryCard;
    @BindView(R.id.ll_face_photo)//拍摄人脸
            LinearLayout llFacePhoto;
    @BindView(R.id.btn_card)//提交按钮
            Button btnCard;
    @BindView(R.id.activity_identity_card)
    LinearLayout activityIdentityCard;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    @BindView(R.id.iv_positive)
    ImageView ivPositive;
    @BindView(R.id.iv_reverse)
    ImageView ivReverse;
    @BindView(R.id.iv_person)
    ImageView ivPerson;

//    private Activity mActivity;


    @Override
    public void init() {
//        mActivity = IdentityCardActivity.this;
        topbar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {

            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_identity_card;
    }

    @Override
    public Activity bindActivity() {
        return this;
    }

    File filePositive, fileReverse, filePerson;

    @OnClick({R.id.topbar, R.id.ll_front_card, R.id.ll_contrary_card, R.id.ll_face_photo, R.id.btn_card})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_front_card://正面
                showCameraType(0);
                break;
            case R.id.ll_contrary_card://反面
                showCameraType(1);
                break;
            case R.id.ll_face_photo://拍摄人脸
                showCameraType(2);
                break;
            case R.id.btn_card://提交按钮
                // TODO: 2017/7/26 提交
                Log.d("IdentityCardActivity", "ivPositive.getTag()+ivReverse.getTag()+ivPerson.getTag():" + (ivPositive.getTag().toString()
                        + ivReverse.getTag().toString() + ivPerson.getTag().toString()));

                Log.d("IdentityCardActivity", "filePathList.size():" + filePathList.size());
                if (filePathList.size() >= 3) {
                    filePositive = new File(filePathList.get(0));
                    fileReverse = new File(filePathList.get(1));
                    filePerson = new File(filePathList.get(2));
                    OkHttpUtils.post().url(String.format("%s%s", Constants.BASE_URL, "/xjd/front/user/realApproveForApp"))
                            .addParams("userId", MyApplication.getInstance().getUid())
                            .addParams("id_name", "姓名")
                            .addParams("id_no", "id_no")
                            .addParams("age", "888")
                            .addParams("flag_sex", "男")
                            .addParams("date_birthday", "1999-10-24")
                            .addParams("addr_card", "详细地址")
                            .addParams("startCard", "10年")
                            .addFile("files", "positive", filePositive)
                            .addFile("files", "reverse", fileReverse)
                            .addFile("files", "person", filePerson)
                            .build().execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            ToastUtils.showMessage(getString(R.string.str_http_network_error));
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            Log.d("IdentityCardActivity", response);
                            if (GsonUtil.GsonToBean(response, Result.class).isSuccess()) {
                                ToastUtils.showMessage("上传成功");
                                finish();
                            } else {
                                ToastUtils.showMessage("上传失败");
                            }
                        }
                    });
                } else {
                    ToastUtils.showMessage("请选择图片");
                }
                break;
        }
    }

    ImageView imageView;
    ArrayList<String> mImageList = new ArrayList<>();
    ArrayList<String> filePathList = new ArrayList<>();

    private void showCameraType(int i) {
        switch (i) {
            case 0:
                imageView = ivPositive;
                break;
            case 1:
                imageView = ivReverse;
                break;
            case 2:
                imageView = ivPerson;
                break;
        }
        mImageList.clear();
        SelectorImageUtils.selectByofOne(this, mImageList);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 999) {
            if (resultCode == RESULT_OK) {
                mImageList = Album.parseResult(data);
                filePathList.add(mImageList.get(0));
                File img = new File(mImageList.get(0));
                Glide.with(MyApplication.getInstance().getApplicationContext()).load(img).into(imageView);
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "取消", Toast.LENGTH_SHORT).show();
            }
        }
    }
}