package haixianwang.daiqile.com.baiqishi;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;

import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;


import com.bqs.crawler.cloud.sdk.BqsParams;
import com.bqs.crawler.cloud.sdk.MnoAction;
import com.bqs.crawler.cloud.sdk.OnMnoLoginListener;
import com.bqs.crawler.cloud.sdk.OnSDKInitListener;
import com.bqs.crawler.cloud.sdk.SDKInitialize;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zkw on 2017/7/13.
 */

public final class PermissionUtils {

    //授权 2.0
    public static final void initialize(String name, String cardId, String phone, OnSDKInitListener onSDKInitListener) {
        Log.d("手机授权", name + " " + cardId + " " + phone);
        BqsParams params = new BqsParams();
        params.setPartnerId("daiqile");
        params.setCertNo(cardId);
        params.setName(name);
        params.setMobile(phone);
        SDKInitialize.getInstance().initialize(params, onSDKInitListener);
    }

    //验证服务
    public static final void service(String serviceCode, OnMnoLoginListener onMnoLoginListener) {
        MnoAction.login(serviceCode, onMnoLoginListener);
    }


//    public static final int CODE_PERMISSIONS = 123;
//
//    public interface PermissionGrant {
//        /**
//         * 处理结果
//         *
//         * @param requestCode        请求码
//         * @param permissions        本次授权权限列表
//         * @param grantResults       本次授权结果,0:授权成功 -1:拒绝授权
//         * @param requestPermissions 请求所有权限
//         */
//        void onPermissionGranted(final int requestCode, @NonNull String[] permissions,
//                                 @NonNull int[] grantResults, String[] requestPermissions);
//    }
//
//    private static void showMessageOKCancel(final Activity context, String message, DialogInterface.OnClickListener okListener) {
//        new AlertDialog.Builder(context)
//                .setMessage(message)
//                .setPositiveButton("是", okListener)
//                .setNegativeButton("否", null)
//                .create()
//                .show();
//    }
//
//    /**
//     * 跳转到系统设置界面去开启权限
//     *
//     * @param activity
//     * @param message
//     */
//    private static void openSettingActivity(final Activity activity, String message) {
//        showMessageOKCancel(activity, message, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Intent intent = new Intent();
//                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
//                intent.setData(uri);
//                activity.startActivity(intent);
//            }
//        });
//    }
//
//    /////////////////////////////////执行授权/////////////////////////////////////
//
//    /**
//     * 一次申请多个权限
//     *
//     * @param activity
//     * @param grant
//     */
//    public static void requestMultiPermissions(final Activity activity, String[] requestPermissions, PermissionGrant grant) {
//        //获取没有授权的权限
//        final List<String> permissionsList = getNoGrantedPermission(activity, requestPermissions, false);
//        //获取上次被拒权限列表
//        final List<String> shouldRationalePermissionsList = getNoGrantedPermission(activity, requestPermissions, true);
//
//        if (permissionsList == null || shouldRationalePermissionsList == null) {
//            return;
//        }
//
//        if (permissionsList.size() > 0) {//去授权
//            ActivityCompat.requestPermissions(activity, permissionsList.toArray(new String[permissionsList.size()]), CODE_PERMISSIONS);
//        } else if (shouldRationalePermissionsList.size() > 0) {
//            showMessageOKCancel(activity, "应用缺少权限，是否重新去授权？",
//                    new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            ActivityCompat.requestPermissions(activity, shouldRationalePermissionsList.toArray(new String[shouldRationalePermissionsList.size()]),
//                                    CODE_PERMISSIONS);
//                        }
//                    });
//        } else {
//            int[] grantResults = new int[requestPermissions.length];
//            grant.onPermissionGranted(CODE_PERMISSIONS, requestPermissions, grantResults, requestPermissions);
//        }
//    }
//
//    private static ArrayList<String> getNoGrantedPermission(Activity activity, String[] requestPermissions, boolean isShouldRationale) {
//        ArrayList<String> permissions = new ArrayList<>();
//        for (int i = 0; i < requestPermissions.length; i++) {
//            String requestPermission = requestPermissions[i];
//
//            int checkSelfPermission = -1;
//            try {
//                checkSelfPermission = ActivityCompat.checkSelfPermission(activity, requestPermission);
//            } catch (RuntimeException e) {
//                return null;
//            }
//
//            if (checkSelfPermission != PackageManager.PERMISSION_GRANTED) {
//                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, requestPermission)) {
//                    if (isShouldRationale) {
//                        permissions.add(requestPermission);
//                    }
//
//                } else {
//                    if (!isShouldRationale) {
//                        permissions.add(requestPermission);
//                    }
//                }
//            }
//        }
//
//        return permissions;
//    }
//
//    /////////////////////////////////处理授权结果/////////////////////////////////////
//
//    /**
//     * 处理授权
//     *
//     * @param requestCode  Need consistent with requestPermission
//     * @param permissions
//     * @param grantResults
//     */
//    public static void requestPermissionsResult(final int requestCode, @NonNull String[] permissions,
//                                                @NonNull int[] grantResults, String[] requestPermissions, PermissionUtils.PermissionGrant permissionGrant) {
//        if (requestCode == CODE_PERMISSIONS) {
//            permissionGrant.onPermissionGranted(requestCode, permissions, grantResults, requestPermissions);
//            return;
//        }
//    }
}