package com.daiqile.xianjindai.utils.utils;

import com.daiqile.xianjindai.Fragment.bean.AllBorrowBean;
import com.daiqile.xianjindai.MyApplication;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import suangrenduobao.daiqile.com.mvlib.utils.http.BaseBean;

/**
 * Created by zkw on 2017/7/28.
 */

public class PayUtils {

    public static PayOrder CONSTRUCTGESTUREPAYORDER(BaseBean baseBean) {
        AllBorrowBean.ListBean borrowBean = (AllBorrowBean.ListBean) baseBean;
        SimpleDateFormat dataFormat = new SimpleDateFormat(
                "yyyyMMddHHmmss");
        Date date = new Date();
        String timeString = dataFormat.format(date);
        PayOrder order = new PayOrder();
        order.setBusi_partner("101001");
        order.setNo_order(timeString);
        order.setDt_order(timeString);
        order.setName_goods("还借款");
        order.setNotify_url(Constants.NOTIFY_URL);//Constants.NOTIFY_URL
        order.setSign_type(PayOrder.SIGN_TYPE_RSA);
        order.setUser_id(MyApplication.getInstance().getUid());
        order.setId_no(borrowBean.getIdcard());
        order.setAcct_name(borrowBean.getRealName());
        order.setMoney_order(borrowBean.getLoanAmount() + "");//测试0.01
        order.setCard_no(borrowBean.getBankNo());
        order.setRisk_item(constructRiskItem(baseBean));
        order.setFlag_modify("1");
        order.setOid_partner(EnvConstants.PARTNER);
        String content = BaseHelper.sortParam(order);
        String sign = Rsa.sign(content, EnvConstants.RSA_PRIVATE);
        order.setSign(sign);
        return order;
    }

    private static String constructRiskItem(BaseBean baseBean) {
//        @"frms_ware_category" : @"2010",
//        @"user_info_mercht_userno" : userid,
//        @"user_info_bind_phone" : phone,
//        @"user_info_identify_state" : @"1",
//        @"user_info_identify_type" : @"4",
//        @"user_info_full_name" : self.realName,
//        @"user_info_id_no" : self.id_no,
//        @"user_info_dt_register":createdTime

        AllBorrowBean.ListBean borrowBean = (AllBorrowBean.ListBean) baseBean;
        JSONObject mRiskItem = new JSONObject();
        try {
//            mRiskItem.put("user_info_bind_phone", "13958069593");
//            mRiskItem.put("user_info_dt_register", "201407251110120");
//            mRiskItem.put("frms_ware_category", "4.0");
//            mRiskItem.put("request_imei", "1122111221");
            mRiskItem.put("user_info_bind_phone", borrowBean.getPhone());
            mRiskItem.put("user_info_identify_state", "1");
            mRiskItem.put("user_info_identify_type", "4");
            mRiskItem.put("user_info_full_name", borrowBean.getRealName());
            mRiskItem.put("user_info_id_no", borrowBean.getIdcard());
            mRiskItem.put("user_info_mercht_userno", MyApplication.getInstance().getUid());//用户Id
            mRiskItem.put("user_info_dt_register", "");//用户注册的时间createdTime 1500451285000
            mRiskItem.put("frms_ware_category", "2010");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mRiskItem.toString();
    }
}
