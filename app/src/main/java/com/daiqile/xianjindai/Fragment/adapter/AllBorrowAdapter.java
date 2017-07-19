package com.daiqile.xianjindai.Fragment.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.daiqile.xianjindai.Fragment.bean.AllBorrowBean;
import com.daiqile.xianjindai.R;
import com.daiqile.xianjindai.utils.RxString;
import com.daiqile.xianjindai.utils.TimeUtils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import butterknife.BindView;

/**
 * Created by zkw on 2017/7/19.
 */

public class AllBorrowAdapter extends RecyclerArrayAdapter<AllBorrowBean.ListBean> {
    public AllBorrowAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new AllBorrowViewHolder(parent);
    }

    class AllBorrowViewHolder extends BaseViewHolder<AllBorrowBean.ListBean> {

        TextView tvBankName;
        TextView tvBankMoney;
        TextView tvBorrowTime;
        TextView tvRepaymentTime;
        Button btnProtocol;
        TextView tvMoneyStatus;
        TextView tvMoneyTime;

        public AllBorrowViewHolder(ViewGroup parent) {
            super(parent, R.layout.fragment_all_borrow_item);

            tvBankName = $(R.id.tv_bank_name);
            tvBankMoney = $(R.id.tv_bank_money);
            tvBorrowTime = $(R.id.tv_borrow_time);
            tvRepaymentTime = $(R.id.tv_repayment_time);
            btnProtocol = $(R.id.btn_protocol);
            tvMoneyStatus = $(R.id.tv_money_status);
            tvMoneyTime = $(R.id.tv_money_time);
        }

        @Override
        public void setData(AllBorrowBean.ListBean data) {
            super.setData(data);

            tvBankName.setText(data.getBankName() + " " + RxString.repBank(data.getBankNo()));
            tvBankMoney.setText("¥1000");

            tvBorrowTime.setText(TimeUtils.timedate(data.getApplyTime()+""));
            tvRepaymentTime.setText(TimeUtils.timedate(data.getRepayTime()+""));
            String status = null;
            switch (data.getStatus()) {
                case 0:
                    status = "申请";
                    break;
                case 1:
                    status = "未放款";
                    break;
                case 2:
                    status = "未还款";
                    break;
                case 3:
                    status = "已还款";
                    break;
                case -1:
                    status = "审核未通过";
                    break;
                case 4:
                    status = "手机端状态(待后台审核中)";
                    break;
            }
            tvMoneyStatus.setText(status);
            tvMoneyTime.setText(status);
        }
    }
}
