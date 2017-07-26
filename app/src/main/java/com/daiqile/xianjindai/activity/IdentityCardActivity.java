package com.daiqile.xianjindai.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import com.bumptech.glide.Glide;
import com.daiqile.xianjindai.R;
import com.daiqile.xianjindai.base.BaseActivity;
import com.daiqile.xianjindai.utils.ImageToString;
import com.daiqile.xianjindai.view.TopBar;
import com.wevey.selector.dialog.DialogOnItemClickListener;
import com.wevey.selector.dialog.NormalSelectionDialog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import suangrenduobao.daiqile.com.mvlib.utils.ToastUtils;


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

    private Activity mActivity;


    @Override
    public void init() {
        mActivity = IdentityCardActivity.this;
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


    @OnClick({R.id.topbar, R.id.ll_front_card, R.id.ll_contrary_card, R.id.ll_face_photo, R.id.btn_card})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_front_card://正面
//                File parents = Environment.getExternalStorageDirectory();
//                File file = new File(parents, "positive");
//                ImageUtils.getInstance().getImageByUrl("", ivPositive, file.getAbsolutePath());
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
                break;
        }
    }

    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 0xfff;

    private void getImage(int type) {
        switch (type) {
            case 0:
                intent = new Intent();
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0x11);
                break;

            case 1:
                intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 0x12);
                break;
        }
    }

    ImageView imageView;

    String fileName;

    private void showCameraType(int i) {

        switch (i) {
            case 0:
                imageView = ivPositive;
                fileName = "positive";
                break;
            case 1:
                imageView = ivReverse;
                fileName = "reverse";
                break;
            case 2:
                imageView = ivPerson;
                fileName = "person";
                break;
        }
        if (headDialog == null) {
            final ArrayList<String> s = new ArrayList<>();
            headDialog = new NormalSelectionDialog.Builder(this)
                    .setTitleTextSize(14)
                    .setTitleTextColor(R.color.colorPrimary)
                    .setItemHeight(40)
                    .setItemWidth(0.9f)
                    .setItemTextColor(R.color.colorPrimaryDark)
                    .setItemTextSize(14)
                    .setOnItemListener(new DialogOnItemClickListener() {
                        @Override
                        public void onItemClick(Button button, int position) {
                            headDialog.dismiss();
                            if (ContextCompat.checkSelfPermission(IdentityCardActivity.this,
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                    != PackageManager.PERMISSION_GRANTED) {
                                ActivityCompat.requestPermissions(IdentityCardActivity.this,
                                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                        MY_PERMISSIONS_REQUEST_CALL_PHONE);
                                index = position;
                            } else {
                                getImage(position);
                            }
                        }
                    })
                    .setCanceledOnTouchOutside(true)
                    .build();
            s.add("照相机");
            s.add("图片");
            headDialog.setDataList(s);
        }
        headDialog.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getImage(index);
            } else {
                ToastUtils.showMessage("请前往设置界面打开");
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case 0x11:
                    Bundle bundle = data.getExtras();
                    if (bundle != null) {
                        Bitmap bitmap = bundle.getParcelable("data");
                        Uri uri = saveBitmap(bitmap);
                        starImageZoom(uri);
                    }
                    break;
                case 0x12:
                    Uri fileuUri = converUri(data.getData());
                    starImageZoom(fileuUri);
                    break;

                case 0x14:
                    if (null == data) {
                        return;
                    }
                    bundle = data.getExtras();
                    if (null == bundle) {
                        return;
                    }
                    final Bitmap bitmap = bundle.getParcelable("data");

//                    BitmapDrawable d=new BitmapDrawable(bitmap);
//                    imageView.setImageBitmap(bitmap);

                    final String bitmapSize = ImageToString.sendPhoto(bitmap);
                    Log.d("IdentityCardActivity", bitmapSize);
//                    presenter.requestImage(bitmapSize);

                    File tmDir = new File(Environment.getExternalStorageDirectory()
                            + "/xianjindai");
                    if (!tmDir.exists()) {
                        tmDir.mkdir();
                    }
                    File img = new File(tmDir.getAbsolutePath() + String.format("%s.png", fileName));
                    Log.d("UserInfoActivity", img.getPath());
                    //本地文件
                    //加载图片
                    Glide.with(this).load(img).into(imageView);
//                    bitmap.recycle();
                    break;
            }
        }
    }

    /**
     * 把 Bitmap 保存在SD卡路径后，返回file 类型的 uri
     *
     * @param bm
     * @return
     */
    private Uri saveBitmap(Bitmap bm) {
        File tmDir = new File(Environment.getExternalStorageDirectory()
                + "/xianjindai");
        if (!tmDir.exists()) {
            tmDir.mkdir();
        }
        File img = new File(tmDir.getAbsolutePath() + String.format("%s.png", fileName));
        if (img.exists()) {
            img.delete();
            img = new File(tmDir.getAbsolutePath() + String.format("%s.png", fileName));
        }
        try {
            FileOutputStream fos = new FileOutputStream(img);
            bm.compress(Bitmap.CompressFormat.PNG, 85, fos);
            fos.flush();
            fos.close();
            return Uri.fromFile(img);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 通过 file 类型的 uri 去启动系统图片裁剪器
     *
     * @param uri
     */
    private void starImageZoom(Uri uri) {
        // 打开裁剪器
        intent = new Intent();
//        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setAction("com.android.camera.action.CROP");
        // 设置 裁剪的数据uri 和类型 image
        intent.setDataAndType(uri, "image/*");
        // 是可裁剪的
        intent.putExtra("crop", true);
        // 设置裁剪宽高的比例 1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 设置最终裁剪出来的图片的宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        // 设置 最终裁剪完是通过intent 传回来的
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 0x14);
    }

    /**
     * 把 content 类型的uri 转换为 file 类型的 uri （其实，就是通过content类型的uri
     * 解释为bitmap，然后保存在sd卡中，通过保存路径来获得file类型额uri）
     *
     * @param uri
     * @return
     */
    private Uri converUri(Uri uri) {
        InputStream is = null;
        try {
            is = getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            is.close();
            return saveBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Intent intent;
    private int index = 0;

    private NormalSelectionDialog headDialog;
}