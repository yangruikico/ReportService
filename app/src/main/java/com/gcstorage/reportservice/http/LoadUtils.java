package com.gcstorage.reportservice.http;


import com.yrbase.response.ObjectLoader;
import com.yrbase.response.RetrofitServiceManager;

/**
 * Created by EA on 2018/4/18
 */

public class LoadUtils extends ObjectLoader {

    private static LoadUtils mLoadUtils = null;

    public static RetrofitService mRetrofitService = RetrofitServiceManager.getInstance().create(RetrofitService.class);
    
    private LoadUtils() {

    }

    public static LoadUtils getInstance() {


        if (mLoadUtils == null) {
            synchronized (LoadUtils.class) {
                if (mLoadUtils == null) {
                    mLoadUtils = new LoadUtils();
                }
            }
        }
        return mLoadUtils;
    }
}
