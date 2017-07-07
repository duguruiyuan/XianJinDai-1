package com.daiqile.xianjindai.Fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daiqile.xianjindai.R;
import com.daiqile.xianjindai.base.BaseFragment;
import com.daiqile.xianjindai.view.TopBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

//借款记录
public class BorrowingRecordFragment extends BaseFragment {
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
        titles.add("全部");
        titles.add("进行中");
        titles.add("已结清");

        fragments.add(new AllBorrowFragment());
        fragments.add(new UnderwayFragment());
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
        // TODO: inflate a fragment view
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
