package com.daiqile.xianjindai.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;

import com.daiqile.xianjindai.Constants;
import com.daiqile.xianjindai.MyApplication;
import com.daiqile.xianjindai.Result;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import suangrenduobao.daiqile.com.mvlib.utils.GsonUtil;
import suangrenduobao.daiqile.com.mvlib.utils.http.ApiCallback;
import suangrenduobao.daiqile.com.mvlib.utils.http.BaseBean;

import static android.R.id.list;
import static com.yintong.secure.e.m.j.C;
import static com.yintong.secure.e.m.j.m;

/**
 * Created by zkw on 2017/8/2.
 */

public class AddressListUtils {
    private static final String[] PHONES_PROJECTION = new String[]{
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER,
            ContactsContract.CommonDataKinds.Photo.PHOTO_ID, ContactsContract.CommonDataKinds.Phone.CONTACT_ID};

    private static final int PHONES_DISPLAY_NAME_INDEX = 0;

    private static final int PHONES_NUMBER_INDEX = 1;

    public static void readAddress(Context context) {
        Map<String, String> map = new HashMap<>();
        map.put("userId", MyApplication.getInstance().getUid() + "");
        map.put("data", GsonUtil.GsonString(getPhoneContacts(context)));
        ApiRequest.request(MyApplication.getInstance().apiService.addressBook(map), new ApiCallback<Result>() {
            @Override
            public void onSuccess(Result result) {
                Log.d("AddressListUtils", "result:" + result);
            }

            @Override
            public void onFailure(String msg) {
                Log.d("AddressListUtils", msg);
            }

            @Override
            public void onFinish() {

            }
        });
    }

    /**
     * 得到手机通讯录联系人信息
     **/
    private static List<User> getPhoneContacts(Context mContext) {

        ContentResolver resolver = mContext.getContentResolver();
        // 获取手机联系人
        Cursor phoneCursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, PHONES_PROJECTION, null, null, null);
        List<User> list = new ArrayList<>();
        if (phoneCursor != null) {
            while (phoneCursor.moveToNext()) {
                String phoneNumber = phoneCursor.getString(PHONES_NUMBER_INDEX);
                if (TextUtils.isEmpty(phoneNumber))
                    continue;
                list.add(new User(phoneCursor.getString(PHONES_DISPLAY_NAME_INDEX), phoneNumber));
            }
            phoneCursor.close();
        }
        return list;
    }


    static class User implements BaseBean {
        String userName;
        String userPhone;

        public User() {
        }

        public User(String userName, String userPhone) {
            this.userName = userName;
            this.userPhone = userPhone;
        }
    }
}
