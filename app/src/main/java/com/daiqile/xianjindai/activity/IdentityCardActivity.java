package com.daiqile.xianjindai.activity;

import android.app.Activity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.authreal.api.AuthBuilder;
import com.authreal.api.OnResultListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.daiqile.xianjindai.Constants;
import com.daiqile.xianjindai.MyApplication;
import com.daiqile.xianjindai.R;
import com.daiqile.xianjindai.Result;
import com.daiqile.xianjindai.base.BaseActivity;
import com.daiqile.xianjindai.utils.TimeUtils;
import com.daiqile.xianjindai.view.TopBar;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import suangrenduobao.daiqile.com.mvlib.utils.GsonUtil;
import suangrenduobao.daiqile.com.mvlib.utils.ToastUtils;
import suangrenduobao.daiqile.com.mvlib.utils.http.BaseBean;

/**
 * 身份认证页面
 */
public class IdentityCardActivity extends BaseActivity implements OnResultListener {


    @BindView(R.id.topbar)//topbar
            TopBar topbar;
    @BindView(R.id.ll_front_card)//身份证正面
            LinearLayout llFrontCard;
    @BindView(R.id.ll_contrary_card)//身份证反面
            LinearLayout llContraryCard;
    @BindView(R.id.ll_face_photo)//拍摄人脸
            LinearLayout llFacePhoto;
    @BindView(R.id.btn_card)//提交按钮
            Button btnCard;
    @BindView(R.id.activity_identity_card)
    LinearLayout activityIdentityCard;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    @BindView(R.id.iv_positive)
    ImageView ivPositive;
    @BindView(R.id.iv_reverse)
    ImageView ivReverse;
    @BindView(R.id.iv_person)
    ImageView ivPerson;

    private UserInfo userInfo;
    private List<File> files;

    @Override
    public void init() {
        topbar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
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
    public int getLayoutId() {
        return R.layout.activity_identity_card;
    }

    @Override
    protected void onStop() {
        super.onStop();
        OkHttpUtils.getInstance().cancelTag("onViewClicked");
    }

    @Override
    public Activity bindActivity() {
        return this;
    }

    AuthBuilder authBuilder;

    @OnClick({R.id.topbar, R.id.ll_front_card, R.id.ll_contrary_card, R.id.ll_face_photo, R.id.btn_card})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_front_card://正面
            case R.id.ll_contrary_card://反面
            case R.id.ll_face_photo://拍摄人脸
                if (null == authBuilder) {
                    authBuilder = new AuthBuilder(Constants.OUTORDERID, Constants.AUTHKEY,
                            Constants.URLNOTIFY, this);
                }
                authBuilder.faceAuth(this);
                break;
            case R.id.btn_card://提交按钮
                if (files.size() >= 3) {
                    OkHttpUtils.post().url(String.format("%s%s", Constants.BASE_URL, "/xjd/front/user/realApproveForApp"))
                            .tag("onViewClicked")
                            .addParams("userId", MyApplication.getInstance().getUid())
                            .addParams("id_name", userInfo.getId_name())
                            .addParams("id_no", userInfo.getId_no())//身份证号
                            .addParams("age", String.format("%d", TimeUtils.getAge(userInfo.getDate_birthday()))) //现在时间-userInfo.getDate_birthday()
                            .addParams("flag_sex", userInfo.getFlag_sex())
                            .addParams("date_birthday", userInfo.getDate_birthday())
                            .addParams("addr_card", userInfo.getAddr_card())
                            .addParams("startCard", userInfo.getStart_card())
                            .addFile("files", "positive", files.get(0))
                            .addFile("files", "reverse", files.get(1))
                            .addFile("files", "person", files.get(2))
                            .build().execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            ToastUtils.showMessage(getString(R.string.str_http_network_error));
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            Log.d("IdentityCardActivity", response);
                            if (GsonUtil.GsonToBean(response, Result.class).isSuccess()) {
                                ToastUtils.showMessage("上传成功");
                                finish();
                            } else {
                                ToastUtils.showMessage("上传失败");
                            }
                        }
                    });
                } else {
                    ToastUtils.showMessage("请先完成人脸识别");
                }
                break;
        }
    }


    @Override
    public void onResult(String s) {
        Log.d("IdentityCardActivity", s);
        userInfo = GsonUtil.GsonToBean(s, UserInfo.class);

        Glide.with(MyApplication.getInstance().getApplicationContext()).load(userInfo.getUrl_photoget())
                .error(R.drawable.my_photo).into(ivPositive);

        Glide.with(MyApplication.getInstance().getApplicationContext()).load(userInfo.getUrl_backcard())
                .error(R.drawable.my_photo).into(ivReverse);

        Glide.with(MyApplication.getInstance().getApplicationContext()).load(userInfo.getUrl_photoliving())
                .error(R.drawable.my_photo).into(ivPerson);
        if (null == files) {
            files = new ArrayList<>();
        }
        files.clear();
        try {//1
            FutureTarget<File> fileFutureTarget = Glide.with(MyApplication.getInstance().getApplicationContext())
                    .load(String.format("%s", userInfo.getUrl_photoget())).downloadOnly(100, 100);
            files.add(new File(fileFutureTarget.get().getAbsolutePath()));
            //2
            fileFutureTarget = Glide.with(MyApplication.getInstance().getApplicationContext())
                    .load(String.format("%s", userInfo.getUrl_backcard())).downloadOnly(100, 100);
            files.add(new File(fileFutureTarget.get().getAbsolutePath()));
            //3
            fileFutureTarget = Glide.with(MyApplication.getInstance().getApplicationContext())
                    .load(String.format("%s", userInfo.getUrl_photoliving())).downloadOnly(100, 100);
            files.add(new File(fileFutureTarget.get().getAbsolutePath()));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    class UserInfo implements BaseBean {

        /**
         * url_photoget : https://idsafe-auth.udcredit.com/front/4.0/api/file_download/platform/web?name=i_20170606155551894754573.jpg&param=ENCRYPT_8664FD3F90B180ED636DA4FB54112AF56CE26B10F7A44C47FA1126F6629BDB
         * flag_sex : 男
         * risk_tag : {"living_attack":"0"}
         * ret_msg : 交易成功
         * id_name : 周伟利
         * be_idcard : 0.3896
         * serialVersionUID : 1
         * id_no : 330382199401068312
         * date_birthday : 1994.01.06
         * url_frontcard : https://idsafe-auth.udcredit.com/front/4.0/api/file_download/platform/web?name=i_20170606155551734210608.jpg&param=ENCRYPT_8664FD3F90B180ED636DA4FB54112AF56CE26B10F7A44C47FA1126F6629BDB
         * addr_card : 浙江省乐清市仙溪镇蔡家岭村
         * branch_issued : 乐清市公安局
         * state_id : 汉
         * url_backcard : https://idsafe-auth.udcredit.com/front/4.0/api/file_download/platform/web?name=i_20170606155642833795176.jpg&param=ENCRYPT_8664FD3F90B180ED636DA4FB54112AF56CE26B10F7A44C47FA1126F6629BDB
         * ret_code : 000000
         * start_card : 2010.02.18-2020.02.18
         * result_auth : F
         * url_photoliving : https://idsafe-auth.udcredit.com/front/4.0/api/file_download/platform/web?name=i_20170606155659277377557.jpg&param=ENCRYPT_8664FD3F90B180ED636DA4FB54112AF56CE26B10F7A44C47FA1126F6629BDB
         */

        private String url_photoget;
        private String flag_sex;
        private RiskTagBean risk_tag;
        private String ret_msg;
        private String id_name;
        private String be_idcard;
        private int serialVersionUID;
        private String id_no;
        private String date_birthday;
        private String url_frontcard;
        private String addr_card;
        private String branch_issued;
        private String state_id;
        private String url_backcard;
        private String ret_code;
        private String start_card;
        private String result_auth;
        private String url_photoliving;

        public String getUrl_photoget() {
            return url_photoget;
        }

        public void setUrl_photoget(String url_photoget) {
            this.url_photoget = url_photoget;
        }

        public String getFlag_sex() {
            return flag_sex;
        }

        public void setFlag_sex(String flag_sex) {
            this.flag_sex = flag_sex;
        }

        public RiskTagBean getRisk_tag() {
            return risk_tag;
        }

        public void setRisk_tag(RiskTagBean risk_tag) {
            this.risk_tag = risk_tag;
        }

        public String getRet_msg() {
            return ret_msg;
        }

        public void setRet_msg(String ret_msg) {
            this.ret_msg = ret_msg;
        }

        public String getId_name() {
            return id_name;
        }

        public void setId_name(String id_name) {
            this.id_name = id_name;
        }

        public String getBe_idcard() {
            return be_idcard;
        }

        public void setBe_idcard(String be_idcard) {
            this.be_idcard = be_idcard;
        }

        public int getSerialVersionUID() {
            return serialVersionUID;
        }

        public void setSerialVersionUID(int serialVersionUID) {
            this.serialVersionUID = serialVersionUID;
        }

        public String getId_no() {
            return id_no;
        }

        public void setId_no(String id_no) {
            this.id_no = id_no;
        }

        public String getDate_birthday() {
            return date_birthday;
        }

        public void setDate_birthday(String date_birthday) {
            this.date_birthday = date_birthday;
        }

        public String getUrl_frontcard() {
            return url_frontcard;
        }

        public void setUrl_frontcard(String url_frontcard) {
            this.url_frontcard = url_frontcard;
        }

        public String getAddr_card() {
            return addr_card;
        }

        public void setAddr_card(String addr_card) {
            this.addr_card = addr_card;
        }

        public String getBranch_issued() {
            return branch_issued;
        }

        public void setBranch_issued(String branch_issued) {
            this.branch_issued = branch_issued;
        }

        public String getState_id() {
            return state_id;
        }

        public void setState_id(String state_id) {
            this.state_id = state_id;
        }

        public String getUrl_backcard() {
            return url_backcard;
        }

        public void setUrl_backcard(String url_backcard) {
            this.url_backcard = url_backcard;
        }

        public String getRet_code() {
            return ret_code;
        }

        public void setRet_code(String ret_code) {
            this.ret_code = ret_code;
        }

        public String getStart_card() {
            return start_card;
        }

        public void setStart_card(String start_card) {
            this.start_card = start_card;
        }

        public String getResult_auth() {
            return result_auth;
        }

        public void setResult_auth(String result_auth) {
            this.result_auth = result_auth;
        }

        public String getUrl_photoliving() {
            return url_photoliving;
        }

        public void setUrl_photoliving(String url_photoliving) {
            this.url_photoliving = url_photoliving;
        }

        public class RiskTagBean {
            /**
             * living_attack : 0
             */

            private String living_attack;

            public String getLiving_attack() {
                return living_attack;
            }

            public void setLiving_attack(String living_attack) {
                this.living_attack = living_attack;
            }
        }
    }
}