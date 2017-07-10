package suangrenduobao.daiqile.com.mvlib.utils;

import android.support.annotation.ColorInt;

import suangrenduobao.daiqile.com.mvlib.mv.BaseActivity;

/**
 * Created by zkw on 2017/6/5.
 */

public interface IToolbar {

    @ColorInt
    int initToolbarColor();

    void showLeftButton();

    void setTopLeftButton();

    void setTopRightButton(String menuStr, int menuResId, BaseActivity.OnClickListener onClickListener);

    void setTopRightButton(int menuResId, BaseActivity.OnClickListener onClickListener);

    void setTopLeftButton(int iconResId, BaseActivity.OnClickListener onClickListener);


    void setTopLeftButton(BaseActivity.OnClickListener onClickListener);

    void setTopRightButton(String menuStr, BaseActivity.OnClickListener onClickListener);

    void setTitle(String title);
}
