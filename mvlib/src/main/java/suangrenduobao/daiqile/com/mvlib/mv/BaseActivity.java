package suangrenduobao.daiqile.com.mvlib.mv;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import android.support.annotation.ColorInt;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bugtags.library.Bugtags;
import com.hwangjr.rxbus.RxBus;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.weavey.loading.lib.LoadingLayout;

import java.lang.reflect.Field;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import suangrenduobao.daiqile.com.mvlib.R;
import suangrenduobao.daiqile.com.mvlib.utils.AppManager;
import suangrenduobao.daiqile.com.mvlib.utils.IBus;
import suangrenduobao.daiqile.com.mvlib.utils.IButterKnife;
import suangrenduobao.daiqile.com.mvlib.utils.IDialog;
import suangrenduobao.daiqile.com.mvlib.utils.IToolbar;
import suangrenduobao.daiqile.com.mvlib.utils.KeyBoardUtils;
import suangrenduobao.daiqile.com.mvlib.utils.NetworkUtils;
import suangrenduobao.daiqile.com.mvlib.utils.ToastUtils;


public abstract class BaseActivity extends RxAppCompatActivity implements IDialog, IBus, IButterKnife, IToolbar {

    private ProgressDialog progressDialog;

    private TextView mTitle;
    private Toolbar mToolbar;
    private FrameLayout mFrameContent;
    protected LoadingLayout loadingLayout;
    private OnClickListener onClickListenerTopLeft, onClickListenerTopRight;

    private long firstTime;

    private String menuStr;
    private int menuResId;

    protected Context mContext;
    protected Activity mActivity;

    public Toolbar getmToolbar() {
        return mToolbar;
    }

    @Override
    public
    @ColorInt
    int initToolbarColor() {
        return Color.parseColor("#FF023467");
//        return Color.parseColor("#FFFFFF");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_base);

        // 修改状态栏颜色，4.4+生效
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            setTranslucentStatus();
//        }
//        SystemBarTintManager tintManager = new SystemBarTintManager(this);
//        tintManager.setStatusBarTintEnabled(true);
////        tintManager.setStatusBarTintResource(R.color.btg_global_transparent);//通知栏所需颜色
//        tintManager.setStatusBarTintColor(initToolbarColor());
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
//        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }
//        getWindow().getDecorView().setSystemUiVisibility(
//                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                        | SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//
//        );

        mContext = this;
        mActivity=this;
        ToastUtils.initToastUtilsContext(mContext.getApplicationContext());

        mTitle = (TextView) findViewById(R.id.tv_title_base);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_base);
        mFrameContent = (FrameLayout) findViewById(R.id.fl_content_base);
        loadingLayout = (LoadingLayout) findViewById(R.id.loadingLayout_base);

        mToolbar.setBackgroundColor(initToolbarColor());

        mFrameContent.addView(LayoutInflater.from(mContext).inflate(initLayout(), null));

        register();
        bind();
        initConfig();

        //是否显示Toolbar
        if (switchToolbar()) {
            mToolbar.setTitle("");
            initToolbar();
            setSupportActionBar(mToolbar);
        } else {
            mToolbar.setVisibility(View.GONE);
        }

        //是否进行网络加载
        if (switchNetWork()) {
            loadingLayout.setStatus(LoadingLayout.Loading);
            if (!NetworkUtils.isNetworkAvailable(mContext)) {
                loadingLayout.setStatus(LoadingLayout.No_Network);
                loadingLayout.setOnReloadListener(new LoadingLayout.OnReloadListener() {
                    @Override
                    public void onReload(View v) {
                        loadData();
                    }
                });
            }
        } else {
            loadingLayout.setStatus(LoadingLayout.Success);
        }
        loadData();
    }


//    @TargetApi(19)
//    protected void setTranslucentStatus() {
//        Window window = getWindow();
//        // Translucent status bar
//        window.setFlags(
//                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
//                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        // Translucent navigation bar
////        window.setFlags(
////                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
////                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//    }

    @Override
    protected void onResume() {
        super.onResume();
        Bugtags.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Bugtags.onPause(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Bugtags.onDispatchTouchEvent(this, event);
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (switchApp()) {
            return super.onKeyUp(keyCode, event);
        }

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > 800) {
                ToastUtils.showMessage("再按一次退出程序...");
                firstTime = secondTime;
                return true;
            } else {
                AppManager.getAppManager().finishAllActivity();
                System.exit(0);
            }
        }
        return super.onKeyUp(keyCode, event);
    }

    protected boolean switchApp() {
        return true;
    }

    protected void initToolbar() {
    }

    protected void initConfig() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unbind();
        unregister();

//        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        try {
//            InputMethodManager.class.getDeclaredMethod("windowDismissed", IBinder.class).invoke(imm,
//                    getWindow().getDecorView().getWindowToken());
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//        if (mContext == null) {
//            return;
//        }
        KeyBoardUtils.hideSoftKeyboard(this);

//        try {
//            // 对 mCurRootView mServedView mNextServedView 进行置空...
//            InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
//            if (imm == null) {
//                return;
//            }// author:sodino mail:sodino@qq.com
//
//            Object obj_get = null;
//            Field f_mCurRootView = imm.getClass().getDeclaredField("mCurRootView");
//            Field f_mServedView = imm.getClass().getDeclaredField("mServedView");
//            Field f_mNextServedView = imm.getClass().getDeclaredField("mNextServedView");
//
//            if (f_mCurRootView.isAccessible() == false) {
//                f_mCurRootView.setAccessible(true);
//            }
//            obj_get = f_mCurRootView.get(imm);
//            if (obj_get != null) { // 不为null则置为空
//                f_mCurRootView.set(imm, null);
//            }
//
//            if (f_mServedView.isAccessible() == false) {
//                f_mServedView.setAccessible(true);
//            }
//            obj_get = f_mServedView.get(imm);
//            if (obj_get != null) { // 不为null则置为空
//                f_mServedView.set(imm, null);
//            }
//
//            if (f_mNextServedView.isAccessible() == false) {
//                f_mNextServedView.setAccessible(true);
//            }
//            obj_get = f_mNextServedView.get(imm);
//            if (obj_get != null) { // 不为null则置为空
//                f_mNextServedView.set(imm, null);
//            }
//        } catch (Throwable t) {
//            t.printStackTrace();
//        }

        AppManager.getAppManager().finishActivity(this);
        mContext = null;
    }

    protected abstract
    @LayoutRes
    int initLayout();

    //加载数据
    protected abstract void loadData();

    //是否要判断有网络，默认是判断的
    protected boolean switchNetWork() {
        return false;
    }

    //是否需要Toolbar，默认是显示的
    protected boolean switchToolbar() {
        return true;
    }


    /**
     * Dialog
     */
    @Override
    public void showDialog() {
        if (null == progressDialog) {
            progressDialog = new ProgressDialog(mContext);
            progressDialog.setMessage("正在加载中...");
            progressDialog.setCancelable(false);
        }
        if (progressDialog != null && !progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    @Override
    public void dismissDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    /**
     * RxBus
     */
    @Override
    public void register() {
        RxBus.get().register(this);
    }

    @Override
    public void unregister() {
        RxBus.get().unregister(this);
    }

    /**
     * ButterKnife
     */
    @Override
    public void bind() {
        bind = ButterKnife.bind(this);
    }

    Unbinder bind;

    @Override
    public void unbind() {
//        ButterKnife.unbind(this);
        bind.unbind();
    }


    /**
     * Toolbar
     */
    public interface OnClickListener {
        void onClick();
    }

    @Override
    public void showLeftButton() {
        setTopLeftButton(R.drawable.ic_return_white_24dp, new OnClickListener() {
            @Override
            public void onClick() {
                finish();
            }
        });
    }

    @Override
    public void setTopLeftButton(OnClickListener onClickListener) {
        setTopLeftButton(R.drawable.ic_return_white_24dp, onClickListener);
    }

    @Override
    public void setTopLeftButton() {
        setTopLeftButton(R.drawable.ic_return_white_24dp, null);
    }

    @Override
    public void setTopLeftButton(int iconResId, OnClickListener onClickListener) {
        mToolbar.setNavigationIcon(iconResId);
        this.onClickListenerTopLeft = onClickListener;
    }

    @Override
    public void setTopRightButton(String menuStr, OnClickListener onClickListener) {
        this.onClickListenerTopRight = onClickListener;
        this.menuStr = menuStr;
    }

    @Override
    public void setTopRightButton(String menuStr, int menuResId, OnClickListener onClickListener) {
        this.menuResId = menuResId;
        this.menuStr = menuStr;
        this.onClickListenerTopRight = onClickListener;
    }

    @Override
    public void setTopRightButton(int menuResId, OnClickListener onClickListener) {
        this.menuResId = menuResId;
        this.onClickListenerTopRight = onClickListener;
    }

    @Override
    public void setTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            mTitle.setText(title);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (menuResId != 0 || !TextUtils.isEmpty(menuStr)) {
            getMenuInflater().inflate(R.menu.menu_activity_base_top_bar, menu);
        }
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (menuResId != 0) {
            menu.findItem(R.id.menu_1).setIcon(menuResId);
        }
        if (!TextUtils.isEmpty(menuStr)) {
            menu.findItem(R.id.menu_1).setTitle(menuStr);
        }
        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onClickListenerTopLeft.onClick();
        } else if (item.getItemId() == R.id.menu_1) {
            onClickListenerTopRight.onClick();
        }
        return true;
    }


}
