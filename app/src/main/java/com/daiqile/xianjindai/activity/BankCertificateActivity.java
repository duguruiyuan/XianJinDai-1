package com.daiqile.xianjindai.activity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.daiqile.xianjindai.Constants;
import com.daiqile.xianjindai.Fragment.bean.UserInfoBean;
import com.daiqile.xianjindai.MyApplication;
import com.daiqile.xianjindai.R;
import com.daiqile.xianjindai.Result;
import com.daiqile.xianjindai.UserInfoRequest;
import com.daiqile.xianjindai.activity.adapter.BankCertificateAdapter;
import com.daiqile.xianjindai.activity.bean.BankCertificateBean;
import com.daiqile.xianjindai.utils.ApiRequest;
import com.daiqile.xianjindai.utils.CallBack;
import com.daiqile.xianjindai.utils.SelectorImageUtils;
import com.daiqile.xianjindai.view.TopBar;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.yanzhenjie.album.Album;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Subscriber;
import suangrenduobao.daiqile.com.mvlib.mv.BaseActivity;
import suangrenduobao.daiqile.com.mvlib.utils.ToastUtils;
import suangrenduobao.daiqile.com.mvlib.utils.http.BaseBean;


public class BankCertificateActivity extends BaseActivity {

    @BindView(R.id.topbar)
    TopBar topbar;
    Button btnSubmit;

    @BindView(R.id.easy_recycler_view)
    EasyRecyclerView easyRecyclerView;

    private BankCertificateAdapter adapter;

    @Override
    protected boolean switchToolbar() {
        return false;
    }

    int mPostion = -1;

    ArrayList<String> imageList = new ArrayList<>();
    ArrayList<BankCertificateBean.ImageBean> list = new ArrayList<>();

    @Override
    protected int initLayout() {
        return R.layout.activity_bank_certificate;
    }

    UserInfoBean mUserInfoBean;

    @Override
    protected void loadData() {
        topbar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {
            }
        });

//        final StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,
//                StaggeredGridLayoutManager.VERTICAL);
        final GridLayoutManager manager = new GridLayoutManager(this, 2);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return adapter.getCount() == position ? manager.getSpanCount()
                        : 1;
            }
        });
        easyRecyclerView.setLayoutManager(manager);
        easyRecyclerView.setRefreshingColorResources(R.color.colorAccent);
        adapter = new BankCertificateAdapter(mActivity);

        adapter.addAll(list);
        adapter.notifyDataSetChanged();
        easyRecyclerView.setAdapter(adapter);

        UserInfoRequest.requestUserInfo(new CallBack() {
            @Override
            public void onNext(BaseBean baseBean) {
                UserInfoBean userInfoBean = (UserInfoBean) baseBean;
                mUserInfoBean = userInfoBean;
                list.add(new BankCertificateBean.ImageBean(0, mUserInfoBean.getUsers().get(0).getPicOne(), R.drawable.my_photo));
                list.add(new BankCertificateBean.ImageBean(1, mUserInfoBean.getUsers().get(0).getPicTwo(), R.drawable.my_photo));
                list.add(new BankCertificateBean.ImageBean(2, mUserInfoBean.getUsers().get(0).getPicThree(), R.drawable.my_photo));
                list.add(new BankCertificateBean.ImageBean(3, mUserInfoBean.getUsers().get(0).getPicFour(), R.drawable.my_photo));
                list.add(new BankCertificateBean.ImageBean(4, mUserInfoBean.getUsers().get(0).getPicFive(), R.drawable.my_photo));
                list.add(new BankCertificateBean.ImageBean(5, mUserInfoBean.getUsers().get(0).getPicSix(), R.drawable.my_photo));
                list.add(new BankCertificateBean.ImageBean(6, mUserInfoBean.getUsers().get(0).getPicSeven(), R.drawable.my_photo));
                list.add(new BankCertificateBean.ImageBean(7, mUserInfoBean.getUsers().get(0).getPicEight(), R.drawable.my_photo));
                adapter.addAll(list);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError() {
                ToastUtils.showMessage(getString(R.string.str_http_network_error));
            }
        });

        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                mPostion = position;
                imageList.clear();
                SelectorImageUtils.selectByofOne(mActivity, imageList);
            }
        });
        adapter.addFooter(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                return LayoutInflater.from(BankCertificateActivity.this).inflate(R.layout.bankcertificate_result, null);
            }

            @Override
            public void onBindView(View headerView) {
                if (null == btnSubmit) {
                    btnSubmit = (Button) headerView.findViewById(R.id.btn_submit);
                    btnSubmit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            loanUploadImg();
                        }
                    });
                }
            }
        });
    }

    public static List<MultipartBody.Part> filesToMultipartBodyParts(List<File> files) {
        List<MultipartBody.Part> parts = new ArrayList<>(files.size());
        for (File file : files) {
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("files", file.getName(), requestBody);
            parts.add(part);
        }
        return parts;
    }

    private void loanUploadImg() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    List<File> fileList = new ArrayList<>();

                    for (String s : adapter.getImageList()) {
                        FutureTarget<File> fileFutureTarget = Glide.with(MyApplication.getInstance().getApplicationContext())
                                .load(String.format("%s", s)).downloadOnly(100, 100);
                        fileList.add(new File(fileFutureTarget.get().getAbsolutePath()));
                    }

//                    fileFutureTarget = Glide.with(MyApplication.getInstance().getApplicationContext())
//                            .load(String.format("%s%s", Constants.BASE_URL, list.get(1).getUrl())).downloadOnly(100, 100);
//                    fileList.add(new File(fileFutureTarget.get().getAbsolutePath()));
//
//                    fileFutureTarget = Glide.with(MyApplication.getInstance().getApplicationContext())
//                            .load(String.format("%s%s", Constants.BASE_URL, list.get(2).getUrl())).downloadOnly(100, 100);
//                    fileList.add(new File(fileFutureTarget.get().getAbsolutePath()));
//
//                    fileFutureTarget = Glide.with(MyApplication.getInstance().getApplicationContext())
//                            .load(String.format("%s%s", Constants.BASE_URL, list.get(3).getUrl())).downloadOnly(100, 100);
//                    fileList.add(new File(fileFutureTarget.get().getAbsolutePath()));
//
//                    fileFutureTarget = Glide.with(MyApplication.getInstance().getApplicationContext())
//                            .load(String.format("%s%s", Constants.BASE_URL, list.get(4).getUrl())).downloadOnly(100, 100);
//                    fileList.add(new File(fileFutureTarget.get().getAbsolutePath()));
//
//                    fileFutureTarget = Glide.with(MyApplication.getInstance().getApplicationContext())
//                            .load(String.format("%s%s", Constants.BASE_URL, list.get(5).getUrl())).downloadOnly(100, 100);
//                    fileList.add(new File(fileFutureTarget.get().getAbsolutePath()));
//
//                    fileFutureTarget = Glide.with(MyApplication.getInstance().getApplicationContext())
//                            .load(String.format("%s%s", Constants.BASE_URL, list.get(6).getUrl())).downloadOnly(100, 100);
//                    fileList.add(new File(fileFutureTarget.get().getAbsolutePath()));
//
//                    fileFutureTarget = Glide.with(MyApplication.getInstance().getApplicationContext())
//                            .load(String.format("%s%s", Constants.BASE_URL, list.get(7).getUrl())).downloadOnly(100, 100);
//                    fileList.add(new File(fileFutureTarget.get().getAbsolutePath()));
                    if (fileList.size() > 0) {
                        ApiRequest.request(MyApplication.getInstance().apiService.loanUploadImg(MyApplication.getInstance().getUid(),
                                filesToMultipartBodyParts(fileList)), new Subscriber<Result>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                ToastUtils.showMessage(getString(R.string.str_http_network_error));
                            }

                            @Override
                            public void onNext(Result result) {
                                if (result.isSuccess()) {
                                    ToastUtils.showMessage("上传成功");
                                } else {
                                    ToastUtils.showMessage(result.getMsg());
                                }
                            }
                        });
                    } else {
                        ToastUtils.showMessage("最小上传一张图片");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 999) {
            if (resultCode == RESULT_OK) {
                list.get(mPostion).setUrl(Album.parseResult(data).get(0));
                list.get(mPostion).setFlag(true);
                adapter.notifyDataSetChanged();
            } else if (resultCode == RESULT_CANCELED) {
            }
        }
    }
}
