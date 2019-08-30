package com.gcstorage.reportservice.mvp;


import com.gcstorage.reportservice.http.LoadUtils;
import com.gcstorage.reportservice.http.MySubscriber;
import com.yrbase.mvp.BasePresenter;
import com.yrbase.response.HashMapUtil;
import com.yrbase.utils.ViewUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Subscriber;
import rx.observers.TestSubscriber;

/**
 * Created by EA on 2018/4/18
 */

public class AddPresenter {


    public interface View {
        void onSaveSuccess();
    }

    public static class Presenter extends BasePresenter<View> {


        public void addTbServices(String mjcountString,String fjcountString,String carcountString,String stringtvStartTime,String stringtvEndTime) {

            long l = System.currentTimeMillis();
            HashMapUtil hashMapUtil = new HashMapUtil();

            hashMapUtil.putParams("mjcount",mjcountString);
            hashMapUtil.putParams("fjcount",fjcountString);
            hashMapUtil.putParams("carcount",carcountString);
            long value = ViewUtil.stringTOLong(stringtvStartTime);
            hashMapUtil.putParams("duty_start_time", value);
            long value1 = ViewUtil.stringTOLong(stringtvEndTime);
            hashMapUtil.putParams("duty_end_time", value1);

            MySubscriber<Object> subscriber = new MySubscriber<Object>() {

                @Override
                public void onSuccess(Object data) {
                    super.onSuccess(data);
                    mView.onSaveSuccess();
                }

                @Override
                public void onError(String str) {
                    super.onError(str);
                }
            };


           LoadUtils.getInstance().observe(LoadUtils.mRetrofitService.addTbServices(hashMapUtil)).subscribe(subscriber);

        }









    }


   /*

//下载图片
    @GET
    Observable<ResponseBody> downloadPicFromNet(@retrofit2.http.Url String fileUrl);


   @POST(URL.getServerPhone)
    Observable<BaseBean<ServerConfigBean>> getServerPhone();


    @POST(URL.getServerPhone)
    Observable<BaseBean<Object>> test();



        Observable.just("")
        .map(new Func1<String, File>() {
        @Override
        public File call(String s) {
            return new File(s);
        }
    }).flatMap(new Func1<File, Observable<BaseBean<ServerConfigBean>>>() {
        @Override
        public Observable<BaseBean<ServerConfigBean>> call(File o) {
            return LoadUtils.getInstance()
                    .observe(LoadUtils.mRetrofitService.getServerPhone());
        }
    })
            .map(new Func1<BaseBean<ServerConfigBean>, String>() {
        @Override
        public String call(BaseBean<ServerConfigBean> serverConfigBeanBaseBean) {


            return "serverConfigBeanBaseBean.geturl";
        }
    })
            .flatMap(new Func1<String, Observable<BaseBean<Object>>>() {
        @Override
        public Observable<BaseBean<Object>> call(String serverConfigBeanBaseBean) {



            return LoadUtils.getInstance()
                    .observe(LoadUtils.mRetrofitService.test());

        }
    })
            .subscribe(new Subscriber<BaseBean<Object>>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(BaseBean<Object> objectBaseBean) {

        }
    });


    */
}
