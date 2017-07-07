package com.daiqile.xianjindai.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daiqile.xianjindai.R;
import com.daiqile.xianjindai.base.BaseFragment;

//借款记录————全部
public class AllBorrowFragment extends BaseFragment {

    @Override
    public void init() {

    }

    @Override
    public int getFragmentId() {
        return R.layout.fragment_all_borrow;
    }

    @Override
    public Object bindFragment() {
        return this;
    }
}
