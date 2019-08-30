package com.gcstorage.reportservice.mvp;


import com.gcstorage.reportservice.http.LoadUtils;
import com.gcstorage.reportservice.http.MySubscriber;
import com.gcstorage.reportservice.mode.MouthTotal;
import com.gcstorage.reportservice.mode.TaskBean;
import com.gcstorage.reportservice.util.LogUtil;
import com.yrbase.mvp.BasePresenter;
import com.yrbase.response.HashMapUtil;
import com.yrbase.utils.ViewUtil;

import java.util.List;

/**
 * Created by EA on 2018/4/18
 */

public class MainPresenter {


    public interface View {

        void onDateSuccess(List<TaskBean.DataBean> mDataBean);

        void onMouthSuccess(List<MouthTotal.DataBean> mDataBean);


    }

    public static class Presenter extends BasePresenter<View> {


        public void getTbServices(long createtime,String type) {

            LogUtil.yangRui().e("传递天:"+ViewUtil.ms2DateAll(createtime));

            HashMapUtil hashMapUtil = new HashMapUtil();

            hashMapUtil.putParams("createtime", createtime);
            hashMapUtil.putParams("type", type);

            MySubscriber<TaskBean> subscriber = new MySubscriber<TaskBean>() {

                @Override
                public void onSuccess(TaskBean data) {
                    super.onSuccess(data);

                    List<TaskBean.DataBean> mDataBean = data.getData();
                    if (!ViewUtil.isListEmpty(mDataBean)) {
                        mView.onDateSuccess(mDataBean);

                    } else {
                        onError("没有数据");
                    }


                }

                @Override
                public void onError(String str) {
                    super.onError(str);
                }
            };


            LoadUtils.getInstance().observe(LoadUtils.mRetrofitService.getTbServices(hashMapUtil)).subscribe(subscriber);

        }


        public void getTimeCount(long time) {


            LogUtil.yangRui().e("传递月份:"+ViewUtil.ms2DateAll(time));



            HashMapUtil hashMapUtil = new HashMapUtil();
            hashMapUtil.putParams("time", time);

            MySubscriber<MouthTotal> subscriber = new MySubscriber<MouthTotal>() {

                @Override
                public void onSuccess(MouthTotal data) {
                    super.onSuccess(data);

                    List<MouthTotal.DataBean> mDataBean = data.getData();
                    if (!ViewUtil.isListEmpty(mDataBean)) {
                        mView.onMouthSuccess(mDataBean);

                    } else {
                        onError("没有数据");
                    }


                }

                @Override
                public void onError(String str) {
                    super.onError(str);
                }
            };


            LoadUtils.getInstance().observe(LoadUtils.mRetrofitService.getTimeCount(hashMapUtil)).subscribe(subscriber);

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
