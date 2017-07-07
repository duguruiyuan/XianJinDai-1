package com.daiqile.xianjindai;

import android.content.Context;
import android.content.SharedPreferences;

import com.daiqile.xianjindai.model.MyInfo;
import com.daiqile.xianjindai.model.User;

import static android.R.attr.password;

/**
 * Created by G150T on 2017/7/3.
 */

public class UserPrefs {
    private final SharedPreferences preferences;

    private static UserPrefs userPrefs;

    public static void init(Context context) {
        userPrefs = new UserPrefs(context);
    }

    private static final String PREFS_NAME = "user_info";
    private static final String USER_ID = "userId";
    private static final String MOBILEPHONE = "mobilePhone";
    private static final String NICK_NAME = "nickName";
    private static final String ICON = "headPicture";
    private static final String TOKEN = "token";
    private static final String SEX = "sex";//0为男
    private static final String Age = "age";
    private static final String ADDRESS_RECEIVE = "addressReceive";
    private static final String ADDRESS_PHONE = "addressPhone";
    private static final String ADDRESS = "address";
    private static final String ADDRESS_ID = "addressId";
    private static final String IS_REAL = "isReal";
    private static final String ID = "id";//用户信息注册登录用id
    private static final String UID = "id";
    private static final String USER_NAME = "username";
    private static final String NAME = "name";
    private static final String PHONE = "phone";
    private static final String PASSWORD = "password";

    private UserPrefs(Context context) {
        preferences = context.getApplicationContext().
                getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static UserPrefs getInstance() {
        return userPrefs;
    }

    public String getPassword() {
        return preferences.getString(PASSWORD, null);
    }

    public void setPassword(String password) {
        preferences.edit().putString(PASSWORD, password).commit();
    }

    public String getPhone() {
        return preferences.getString(PHONE, null);
    }

    public void setPhone(String phone) {
        preferences.edit().putString(PHONE, phone).commit();
    }

    public String getName() {
        return preferences.getString(NAME, null);
    }

    public void setName(String name) {
        preferences.edit().putString(NAME, name).commit();
    }

    public String getUserName() {
        return preferences.getString(USER_NAME, null);
    }

    public void setUserName(String userName) {
        preferences.edit().putString(USER_NAME, userName).commit();
    }

    public String getUid() {
        return preferences.getString(UID, null);
    }

    public void setUid(String uid) {
        preferences.edit().putString(UID, uid).commit();
    }

    public void setUserId(int userId) {
        preferences.edit().putInt(USER_ID, userId).commit();
    }

    public int getUserId() {
        return preferences.getInt(USER_ID, 0);
    }

    public void setMobilePhone(String mobilePhone) {
        preferences.edit().putString(MOBILEPHONE, mobilePhone).commit();
    }

    public String getMobilePhone() {
        return preferences.getString(MOBILEPHONE, null);
    }

    public void setNickName(String nickName) {
        preferences.edit().putString(NICK_NAME, nickName).commit();
    }

    public String getNickName() {
        return preferences.getString(NICK_NAME, null);
    }

    public void setIcon(String iconUrl) {
        preferences.edit().putString(ICON, iconUrl).apply();
    }

    public String getIcon() {
        return preferences.getString(ICON, null);
    }

    public void setToken(String token) {
        preferences.edit().putString(TOKEN, token).commit();
    }

    public String getToken() {
        return preferences.getString(TOKEN, null);
    }

    public void setSex(int sex) {
        preferences.edit().putInt(SEX, sex).commit();
    }

    public int getSex() {
        return preferences.getInt(SEX, 0);
    }

    public void setAddressReceive(String addressReceive) {
        preferences.edit().putString(ADDRESS_RECEIVE, addressReceive).commit();
    }

    public String getAddressReceive() {
        return preferences.getString(ADDRESS_RECEIVE, null);
    }

    public void setAddressPhone(String addressPhone) {
        preferences.edit().putString(ADDRESS_PHONE, addressPhone).commit();
    }

    public String getAddressPhone() {
        return preferences.getString(ADDRESS_PHONE, null);
    }

    public void setAddress(String address) {
        preferences.edit().putString(ADDRESS, address).commit();
    }

    public String getAddress() {
        return preferences.getString(ADDRESS, null);
    }

    public void setAddressId(int addressId) {
        preferences.edit().putInt(ADDRESS_ID, addressId).commit();
    }

    public int getAddressId() {
        return preferences.getInt(ADDRESS_ID, 0);
    }

    public void setIsReal(int isReal) {
        preferences.edit().putInt(IS_REAL, isReal).commit();
    }

    public int getIsReal() {
        return preferences.getInt(IS_REAL, 0);
    }

    public void setAge(int age) {
        preferences.edit().putInt(Age, age).commit();
    }

    public int getAge() {
        return preferences.getInt(Age, 0);
    }

    public void setId(String id) {
        preferences.edit().putString(ID, id).commit();
    }

    public String getId() {
        return preferences.getString(ID, null);
    }


    public void clearUser() {
        preferences.edit().clear().commit();
    }

}


