package com.daiqile.xianjindai.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

public class VerticalSwipeRefreshLayout extends android.support.v4.widget.SwipeRefreshLayout {

    private int scaleTouchSlop;
    private float preX;

    public VerticalSwipeRefreshLayout(Context context) {
        super(context);
        scaleTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public VerticalSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                preX = ev.getX();
                break;

            case MotionEvent.ACTION_MOVE:
                float moveX = ev.getX();
                float instanceX = Math.abs(moveX - preX);
                // 容差值大概是24，再加上60
                if (instanceX > scaleTouchSlop + 60) {
                    return false;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

}