package com.gcstorage.reportservice.http;

import com.gcstorage.reportservice.mode.MouthTotal;
import com.gcstorage.reportservice.mode.TaskBean;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import rx.Observable;

public interface RetrofitService {

    @POST("Services/services/addTbServices")
    Observable<BaseBean<Object>> addTbServices(@QueryMap Map<String, Object> map);

    @POST("Services/services/getTbServices")
    Observable<BaseBean<TaskBean>> getTbServices(@QueryMap Map<String, Object> map);


    @POST("Services/services/getTimeCount")
    Observable<BaseBean<MouthTotal>> getTimeCount(@QueryMap Map<String, Object> map);


    @Multipart
    @POST("invitation/upload")
    Observable<BaseBean<Object>> upLoad(@Part List<MultipartBody.Part> list);

//lowable<BaseResponse<UploadApplyInfo>> uploadImages(@Part List<MultipartBody.Part> list);


    /*l
@QueryMap
@FieldMap


     @Expose
    private String picUrl;


     //上新
    @FormUrlEncoded
    @POST(URL.addGoods)  有json  new Gson().toJson(List)
    Observable<BaseBean<GoodsId>> addGoods(@FieldMap Map<String, String> map);

    l*/


}
