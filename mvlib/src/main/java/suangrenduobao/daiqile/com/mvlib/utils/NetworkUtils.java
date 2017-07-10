package suangrenduobao.daiqile.com.mvlib.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;

/**
 * 网络管理工具
 *
 * @author wd
 */
public final class NetworkUtils {

    /**
     * 网络是否可用
     *
     * @param context
     * @return false（没有网络）
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager manager = NetworkUtils.getManager(context);
        if (null == manager) {
            return false;
        } else {
            NetworkInfo[] info = manager.getAllNetworkInfo();

            State state;
            if (null != info) {
                for (int i = 0; i < info.length; i++) {
                    state = info[i].getState();
                    if (State.CONNECTED == state) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 是否是Wifi
     *
     * @param context
     * @return true（是Wifi）
     */
    public static boolean isWifi(Context context) {
        ConnectivityManager manager = NetworkUtils.getManager(context);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return info != null
                && info.getType() == ConnectivityManager.TYPE_WIFI;

    }


    /**
     * 判断当前网络是否3G网络
     *
     * @param context
     * @return true（是3G）
     */
    public static boolean is3G(Context context) {
        ConnectivityManager connectivityManager = NetworkUtils.getManager(context);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetInfo != null
                && activeNetInfo.getType() == ConnectivityManager.TYPE_MOBILE;
    }


    private static ConnectivityManager getManager(Context context) {
        ConnectivityManager manager;
        manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return manager;
    }


}
