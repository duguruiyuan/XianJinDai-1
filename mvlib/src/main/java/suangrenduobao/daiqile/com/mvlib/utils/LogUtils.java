package suangrenduobao.daiqile.com.mvlib.utils;

import com.orhanobut.logger.Logger;

/**
 * Log统一管理类
 *
 * @author Mr Guo
 */
public final class LogUtils {

    // 是否需要打印bug，可以在application的onCreate函数里面初始化
    public static boolean ISDEBUG = true;
    public static String TAG = "zls";

    public static void init(String tag) {
        init(tag,ISDEBUG);
    }

    public static void init(String tag, boolean isDebug) {
        TAG = tag;
        ISDEBUG = isDebug;
        Logger.init(TAG);
    }

    private LogUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static void i(String msg) {
        LogUtils.i(TAG, msg);
    }

    public static void d(String msg) {
        LogUtils.d(TAG, msg);
    }

    public static void e(String msg) {
        LogUtils.e(TAG, msg);
    }

    public static void v(String msg) {
        LogUtils.v(TAG, msg);
    }

    public static void w(String msg) {
        LogUtils.w(TAG, msg);
    }

    public static void json(String msg) {
        Logger.json(TAG, msg);
    }



    // 下面是传入自定义tag的函数
    public static void i(String tag, String msg) {
        if (ISDEBUG)
            Logger.i(tag, msg);
    }

    public static void d(String tag, String msg) {
        if (ISDEBUG)
            Logger.d(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (ISDEBUG)
            Logger.e(tag, msg);
    }

    public static void v(String tag, String msg) {
        if (ISDEBUG)
            Logger.w(tag, msg);
    }

    public static void w(String tag, String msg) {
        if (ISDEBUG)
            Logger.w(tag, msg);
    }

    public static void json(String tag, String msg) {
        if (ISDEBUG)
            Logger.json(tag, msg);
    }


}
