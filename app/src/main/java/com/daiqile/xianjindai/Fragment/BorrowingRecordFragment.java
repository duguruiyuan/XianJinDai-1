package com.daiqile.xianjindai.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daiqile.xianjindai.BorrowingRecordCallback;
import com.daiqile.xianjindai.Fragment.bean.AllBorrowBean;
import com.daiqile.xianjindai.MyApplication;
import com.daiqile.xianjindai.R;
import com.daiqile.xianjindai.Result;
import com.daiqile.xianjindai.activity.RepaymentActivity;
import com.daiqile.xianjindai.base.BaseFragment;
import com.daiqile.xianjindai.utils.ApiRequest;
import com.daiqile.xianjindai.utils.utils.BaseHelper;
import com.daiqile.xianjindai.utils.utils.Constants;
import com.daiqile.xianjindai.utils.utils.EnvConstants;
import com.daiqile.xianjindai.utils.utils.MobileSecurePayer;
import com.daiqile.xianjindai.utils.utils.PayOrder;
import com.daiqile.xianjindai.utils.utils.ResultChecker;
import com.daiqile.xianjindai.utils.utils.Rsa;
import com.daiqile.xianjindai.view.TopBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscriber;
import suangrenduobao.daiqile.com.mvlib.utils.ToastUtils;
import suangrenduobao.daiqile.com.mvlib.utils.http.BaseBean;


//借款记录
public class BorrowingRecordFragment extends BaseFragment implements BorrowingRecordCallback {
    @BindView(R.id.topbar)
    TopBar topBar;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    Unbinder unbinder;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private ArrayList<String> titles = new ArrayList<>();

    @Override
    public void init() {
        titles.addAll(Arrays.asList("全部", "进行中", "已结清"));

        fragments.add(new AllBorrowFragment(this));
        fragments.add(new UnderwayFragment(this));
        fragments.add(new ClosedAccountFragment());
        viewPager.setOffscreenPageLimit(fragments.size());
        viewPager.setAdapter(new BorrowingRecordFragmentAdapter(getFragmentManager()));
        tabs.setupWithViewPager(viewPager);
        topBar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {

            }

            @Override
            public void rightClick() {

            }
        });
    }

    @Override
    public void onCallBack(BaseBean baseBean) {
        AllBorrowBean.ListBean bean = (AllBorrowBean.ListBean) baseBean;
        switch (bean.getStatus()) {
            case 0:
                //"申请";
                ToastUtils.showMessage("申请中", Gravity.CENTER);
                break;
            case 1:
                //"未放款";
                ToastUtils.showMessage("未放款", Gravity.CENTER);
                break;
            case 2:
                //未还款
                Intent intent = new Intent(getActivity(), RepaymentActivity.class);
                intent.putExtra(com.daiqile.xianjindai.Constants.BEAN, bean);
                startActivity(intent);
                break;
            case 3:
                ToastUtils.showMessage("已还款", Gravity.CENTER);
                break;
            case -1:
                ToastUtils.showMessage("审核未通过", Gravity.CENTER);
                break;
            case 4:
                ToastUtils.showMessage("手机端状态(待后台审核中)", Gravity.CENTER);
                break;
        }

//        if ("-2793600000".equals(bean.getLoanTime() + "")) {
//            ToastUtils.showMessage("未打款", Gravity.CENTER);  //未打款
//        } else {
//            switch (bean.getStatus()) {
//                case 0:
//                    //"申请";
//                    ToastUtils.showMessage("申请中", Gravity.CENTER);
//                    break;
//                case 1:
//                    //"未放款";
//                    ToastUtils.showMessage("未放款", Gravity.CENTER);
//                    break;
//                case 2:
//                    //未还款
//                    Intent intent = new Intent(getActivity(), RepaymentActivity.class);
//                    startActivity(intent);
//                    break;
//                case 3:
//                    ToastUtils.showMessage("已还款", Gravity.CENTER);
//                    break;
//                case -1:
//                    ToastUtils.showMessage("审核未通过", Gravity.CENTER);
//                    break;
//                case 4:
//                    ToastUtils.showMessage("手机端状态(待后台审核中)", Gravity.CENTER);
//                    break;
//            }
//        }
    }

//    private PayOrder constructGesturePayOrder() {
//        SimpleDateFormat dataFormat = new SimpleDateFormat(
//                "yyyyMMddHHmmss");
//        Date date = new Date();
//        String timeString = dataFormat.format(date);
//        PayOrder order = new PayOrder();
//        order.setBusi_partner("101001");
//        order.setNo_order(timeString);
//        order.setDt_order(timeString);
//        order.setName_goods("还借款");
//        order.setNotify_url(Constants.NOTIFY_URL);//Constants.NOTIFY_URL
//        order.setSign_type(PayOrder.SIGN_TYPE_RSA);
//        order.setUser_id(MyApplication.getInstance().getUid());
//        order.setId_no("330284198904210512");
//        order.setAcct_name("孙克斌");
//        order.setMoney_order("0.01");
//        order.setCard_no("6222021001116245702");
//        order.setRisk_item(constructRiskItem());
//        order.setFlag_modify("1");
//        order.setOid_partner(EnvConstants.PARTNER);
//        String content = BaseHelper.sortParam(order);
//        String sign = Rsa.sign(content, EnvConstants.RSA_PRIVATE);
//        order.setSign(sign);
//        return order;
//    }

//    private String constructRiskItem() {
////        @"frms_ware_category" : @"2010",
////        @"user_info_mercht_userno" : userid,
////        @"user_info_bind_phone" : phone,
////        @"user_info_identify_state" : @"1",
////        @"user_info_identify_type" : @"4",
////        @"user_info_full_name" : self.realName,
////        @"user_info_id_no" : self.id_no,
////        @"user_info_dt_register":createdTime
//
//        JSONObject mRiskItem = new JSONObject();
//        try {
////            mRiskItem.put("user_info_bind_phone", "13958069593");
////            mRiskItem.put("user_info_dt_register", "201407251110120");
////            mRiskItem.put("frms_ware_category", "4.0");
////            mRiskItem.put("request_imei", "1122111221");
//            mRiskItem.put("user_info_bind_phone", "13958069593");
//            mRiskItem.put("user_info_identify_state", "1");
//            mRiskItem.put("user_info_identify_type", "4");
//            mRiskItem.put("user_info_full_name", "孙克斌");
//            mRiskItem.put("user_info_id_no", "330284198904210512");
//            mRiskItem.put("user_info_mercht_userno", MyApplication.getInstance().getUid());//用户Id
//            mRiskItem.put("user_info_dt_register", "1500451285000");//用户注册的时间createdTime
//            mRiskItem.put("frms_ware_category", "2010");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return mRiskItem.toString();
//    }

//    private String constructPlan() {
////        DatePicker dp = (DatePicker) findViewById(R.id.datePicker1);
//        int year = 2017;
//        int month = 7;
//        int day = 28;
//
//        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
//        for (int i = 0; i < 1; i++) {
//            Map<String, String> map = new HashMap<String, String>();
//            String date = String.format("%d-%02d-%02d", year, month + 1, day++);
//            map.put("date", date);
//            map.put("amount", "0.01");
//            list.add(map);
//        }
//        JSONArray array = new JSONArray(list);
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.putOpt("repaymentPlan", array);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        return jsonObject.toString();
//    }

    private class BorrowingRecordFragmentAdapter extends FragmentPagerAdapter {

        public BorrowingRecordFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }


    @Override
    public int getFragmentId() {
        return R.layout.fragment_borrowing_record;
    }

    @Override
    public Object bindFragment() {
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
