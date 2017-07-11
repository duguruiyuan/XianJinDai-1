package com.daiqile.xianjindai.activity.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daiqile.xianjindai.R;
import com.daiqile.xianjindai.activity.bean.BankInfoList;
import com.daiqile.xianjindai.utils.ImageLoader;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import butterknife.BindView;
import suangrenduobao.daiqile.com.mvlib.utils.RxTextUtils;

public class BankListAdapter extends RecyclerArrayAdapter<BankInfoList.BanksBean> {

    public BankListAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new BankListViewHolder(parent);
    }

    class BankListViewHolder extends BaseViewHolder<BankInfoList.BanksBean> {

        ImageView ivBankIcon;
        TextView tvBankName;
        TextView tvBankNumber;

        TextView tvProvince;
        TextView tvCity;
        TextView tvArea;

//            ClickableSpan clickableSpan = new ClickableSpan() {
//                @Override
//                public void onClick(View widget) {
//                }
//
//                @Override
//                public void updateDrawState(TextPaint ds) {
//                    ds.setColor(getContext().getResources().getColor(R.color.red));
//                    ds.setUnderlineText(false);
//                }
//            };

        public BankListViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_bank_card);

            ivBankIcon = $(R.id.iv_bank_icon);
            tvBankName = $(R.id.tv_bank_name);
            tvBankNumber = $(R.id.tv_bank_number);
            tvProvince = $(R.id.tv_province);
            tvCity = $(R.id.tv_city);
            tvArea = $(R.id.tv_area);
        }

        @Override
        public void setData(BankInfoList.BanksBean data) {
            super.setData(data);

            tvBankName.setText(data.getBankName());
            tvBankNumber.setText(data.getBankNo());

            tvProvince.setText(data.getProvince());
            tvCity.setText(data.getCity());
            tvArea.setText(data.getCounty());
//            ImageLoader.getInstance().load(data.getThumb(), ivLogo);
//            ImageLoader.getInstance().load(data.getThumb(), ivLogo, "");
//            tvBankName.setText(data.get());
////            tvEarnings.setText(String.format("增值收益:\n%d",data.getFee()));
////            tvNumber.setText(String.format("项目期限\n%d天", data.getSuccess_time()));
//            tvMoney.setText(String.format("¥%d", data.getCurrent_price()));
//
//
//            RxTextUtils.getBuilder("增值收益:").append(String.format("%d%s", data.getInterest(), "%")).setClickSpan(clickableSpan).into(tvEarnings);
//            RxTextUtils.getBuilder("项目期限:").append(String.format("%d%s", data.getTime_limit(), "个月")).setClickSpan(clickableSpan).into(tvNumber);
        }
    }

}