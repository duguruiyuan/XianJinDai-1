package com.daiqile.xianjindai;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.bqs.crawler.cloud.sdk.MnoAction;
import com.bqs.crawler.cloud.sdk.OnMnoAuthListener;
import com.bqs.crawler.cloud.sdk.OnMnoSendSmsListener;
import com.daiqile.xianjindai.activity.ThirdPartCertificationActivity;
import com.daiqile.xianjindai.view.ClearEditText;
import com.daiqile.xianjindai.view.TopBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import butterknife.BindView;
import butterknife.OnClick;
import suangrenduobao.daiqile.com.mvlib.mv.BaseActivity;
import suangrenduobao.daiqile.com.mvlib.utils.ToastUtils;


public class SendSmsCodeActivity extends BaseActivity {

    @BindView(R.id.topbar)
    TopBar topBar;

    @BindView(R.id.et_servert_code)
    ClearEditText etServertCode;

    @Override
    protected boolean switchToolbar() {
        return false;
    }

    @Override
    protected void initConfig() {
        super.initConfig();
        topBar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {

            }
        });
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_send_sms_code;
    }

    @Override
    protected void loadData() {
    }

    @OnClick({R.id.btn_send_sms_code, R.id.btn_query})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_send_sms_code:
                MnoAction.sendAuthSmsCode(new OnMnoSendSmsListener() {
                    @Override
                    public void onSendSmsSucces() {
                        ToastUtils.showMessage("发送成功");
                    }

                    @Override
                    public void onSendSmsFailure(String s, String s1) {
                        ToastUtils.showMessage("发送失败" + s1);
                    }
                });
                break;
            case R.id.btn_query:
                String mCode = etServertCode.getText().toString().trim();
                MnoAction.verifyAuthSmsCode(mCode, new OnMnoAuthListener() {
                    @Override
                    public void onAuthSuccess() {
                        startActivity(new Intent(mActivity, ThirdPartCertificationActivity.class));
                        finish();
                    }

                    @Override
                    public void onAuthFailure(String s, String s1) {
                        Log.d("BaiqishiActivity", "onAuthFailure" + s + s1);
                    }
                });
                break;
        }
    }

//    private void getAddress() throws JSONException, UnsupportedEncodingException {
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.accumulate("certNo", CERTNO);
//        jsonObject.accumulate("mobile", MOBILE);
//        jsonObject.accumulate("name", NAME);
//        jsonObject.accumulate("verifyKey", VERIFYKEY);
//        jsonObject.accumulate("partnerId", PARTNERID);
//
//        Log.d("BaiqishiActivity", sendPost("https://credit.baiqishi.com/clweb/api/mno/getoriginal",
//                URLEncoder.encode(jsonObject.toString(), "UTF-8")));
//    }

//    public static String sendPost(String url, String param) {
//        PrintWriter out = null;
//        BufferedReader in = null;
//        String result = "";
//        try {
//            URL realUrl = new URL(url);
//            // 打开和URL之间的连接
//            URLConnection conn = realUrl.openConnection();
//            // 设置通用的请求属性
//            conn.setRequestProperty("accept", "*/*");
//            conn.setRequestProperty("connection", "Keep-Alive");
//            conn.setRequestProperty("user-agent",
//                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//            // 发送POST请求必须设置如下两行
//            conn.setDoOutput(true);
//            conn.setDoInput(true);
//            // 获取URLConnection对象对应的输出流
//            out = new PrintWriter(conn.getOutputStream());
//            // 发送请求参数
//            out.print(param);
//            // flush输出流的缓冲
//            out.flush();
//            // 定义BufferedReader输入流来读取URL的响应
//            in = new BufferedReader(
//                    new InputStreamReader(conn.getInputStream()));
//            String line;
//            while ((line = in.readLine()) != null) {
//                result += line;
//            }
//        } catch (Exception e) {
//            System.out.println("发送 POST 请求出现异常！" + e);
//            e.printStackTrace();
//        }
//        //使用finally块来关闭输出流、输入流
//        finally {
//            try {
//                if (out != null) {
//                    out.close();
//                }
//                if (in != null) {
//                    in.close();
//                }
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
//        return result;
//    }
}
