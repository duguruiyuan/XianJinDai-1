package com.daiqile.xianjindai.activity.adapter;

import android.content.Context;

import android.text.TextUtils;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.daiqile.xianjindai.Constants;
import com.daiqile.xianjindai.MyApplication;
import com.daiqile.xianjindai.R;
import com.daiqile.xianjindai.activity.bean.BankCertificateBean;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.io.File;

/**
 * Created by zkw on 2017/7/27.
 */

public class BankCertificateAdapter extends RecyclerArrayAdapter<BankCertificateBean.ImageBean> {

    public BankCertificateAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new BankCertificateViewHolder(parent);
    }

    class BankCertificateViewHolder extends BaseViewHolder<BankCertificateBean.ImageBean> {

        ImageView ivHead;
        TextView tvMark;

        public BankCertificateViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_bankcertificate);

            ivHead = $(R.id.iv_head);
            tvMark = $(R.id.tv_mark);
        }

        @Override
        public void setData(BankCertificateBean.ImageBean data) {
            super.setData(data);
            if (TextUtils.isEmpty(data.getUrl())) {
                Glide.with(MyApplication.getInstance().getApplicationContext()).load(data.getRes())
                        .into(ivHead);
            } else if (data.isFlag()) {
                File file = new File(data.getUrl());
                if (file.exists()) {
                    Glide.with(MyApplication.getInstance().getApplicationContext()).load(file)
                            .into(ivHead);
                }
            } else {
                try {
                    Glide.with(MyApplication.getInstance().getApplicationContext())
                            .load(String.format("%s%s", Constants.BASE_URL, data.getUrl()))
                            .error(data.getRes()).into(ivHead);
                } catch (Exception e) {
                    e.printStackTrace();
//                    File file = new File(data.getUrl());
//                    Glide.with(MyApplication.getInstance().getApplicationContext()).load(file)
//                            .into(ivHead);
                }
            }
            tvMark.setText(String.format("银行流水%d", (data.getPostion() + 1)));
        }
    }
}
