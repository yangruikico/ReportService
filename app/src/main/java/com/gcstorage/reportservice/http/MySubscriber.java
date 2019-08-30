package com.gcstorage.reportservice.http;


import com.gcstorage.reportservice.util.LogUtil;
import com.yrbase.utils.NetworkUtil;
import com.yrbase.utils.ViewUtil;

import rx.Subscriber;

/**
 * Created by kico 2018/5/7on weiniu.
 */

public class MySubscriber<T> extends rx.Subscriber<BaseBean<T>> {
    @Override
    public void onCompleted() {

    }

    @Override
    public void onStart() {
        super.onStart();
        if (!NetworkUtil.isNetworkAvailable()) {
            ViewUtil.Toast("请检查网络");
            onCompleted();
        }
    }

    @Override
    public void onError(Throwable e) {
        LogUtil.yangRui().e(e.toString());
        onError(e.getMessage());
    }

    public static final String successStatus = "20000";
    public static final int loginOutStatus = 409;

    @Override
    public void onNext(BaseBean<T> mBaseBean) {
        LogUtil.yangRui().e(mBaseBean);


       /* if (mBaseBean.status == successStatus) {
            if (mBaseBean.success) {
                onSuccess(mBaseBean.data);
            } else {
                //ViewUtil.Toast(mBaseBean.errMsg);
                onError(mBaseBean.errMsg);
            }
        } else if (mBaseBean.status == loginOutStatus) {

            LogUtil.xuTianXiong().e("LoginOut");
            //  ViewUtil.startActivity(LoginOutActivity.class);

        } else {
            onError(mBaseBean.errMsg);
        }*/


        if (successStatus.equals(mBaseBean.resultcode)) {
            onSuccess(mBaseBean.getResponse());
        }else {
            onError(mBaseBean.getResultmessage());
        }
        }

    public void onSuccess(T data) {

    }

    public void onError(String str) {

    }

}
