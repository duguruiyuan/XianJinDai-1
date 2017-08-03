package com.daiqile.xianjindai;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.text.TextUtils;
import android.util.Log;

import com.bqs.risk.df.android.BqsDF;
import com.bqs.risk.df.android.BqsParams;
import com.bqs.risk.df.android.OnBqsDFContactsListener;
import com.bqs.risk.df.android.OnBqsDFListener;
import com.daiqile.xianjindai.utils.ApiRequest;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import rx.Subscriber;
import suangrenduobao.daiqile.com.mvlib.utils.GsonUtil;
import suangrenduobao.daiqile.com.mvlib.utils.ToastUtils;
import suangrenduobao.daiqile.com.mvlib.utils.http.BaseBean;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();

        Log.d("ExampleInstrumentedTest", MyApplication.getInstance().getUid());

        OkHttpUtils.post().url("http://121.199.54.237:9591/xjd/front/user/addressBook")
                .addParams("id", MyApplication.getInstance().getUid() + "")
//                .addParams("token", MyApplication.getInstance().getToken())

                .addParams("data", GsonUtil.GsonString(getPhoneContacts()).toString()).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.d("ExampleInstrumentedTest", e.toString() + " ");
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d("ExampleInstrumentedTest", response+"成功l");
            }
        });
    }

    private static final String[] PHONES_PROJECTION = new String[]{
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER,
            ContactsContract.CommonDataKinds.Photo.PHOTO_ID, ContactsContract.CommonDataKinds.Phone.CONTACT_ID};

    /**
     * 联系人显示名称
     **/
    private static final int PHONES_DISPLAY_NAME_INDEX = 0;

    /**
     * 电话号码
     **/
    private static final int PHONES_NUMBER_INDEX = 1;


    /**
     * 得到手机通讯录联系人信息
     **/
    private List<User> getPhoneContacts() {

        Context mContext = InstrumentationRegistry.getTargetContext();
        ContentResolver resolver = mContext.getContentResolver();
        // 获取手机联系人
        Cursor phoneCursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, PHONES_PROJECTION, null, null, null);


        if (phoneCursor != null) {
            list = new ArrayList<>();
            while (phoneCursor.moveToNext()) {
                //得到手机号码  
                String phoneNumber = phoneCursor.getString(PHONES_NUMBER_INDEX);
                //当手机号码为空的或者为空字段 跳过当前循环  
                if (TextUtils.isEmpty(phoneNumber))
                    continue;
                //得到联系人名称  
                String contactName = phoneCursor.getString(PHONES_DISPLAY_NAME_INDEX);
                //得到联系人ID  
//                Long contactid = phoneCursor.getLong(PHONES_CONTACT_ID_INDEX);
                //得到联系人头像ID  
//                Long photoid = phoneCursor.getLong(PHONES_PHOTO_ID_INDEX);
//                for (int i = 0; i < 1000; i++) {
                list.add(new User(contactName, phoneNumber));
//                }
            }
            Log.d("ExampleInstrumentedTest", "list.size():" + list.size());
            phoneCursor.close();
        }
        return list;
    }

    List<User> list;

    class User implements BaseBean {
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
