package com.daiqile.xianjindai.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daiqile.xianjindai.BorrowingRecordCallback;
import com.daiqile.xianjindai.Constants;
import com.daiqile.xianjindai.Fragment.bean.AllBorrowBean;
import com.daiqile.xianjindai.R;
import com.daiqile.xianjindai.activity.RepaymentActivity;
import com.daiqile.xianjindai.base.BaseFragment;
import com.daiqile.xianjindai.view.TopBar;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
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
                intent.putExtra(Constants.BEAN,bean);
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
