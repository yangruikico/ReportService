package com.gcstorage.reportservice.util;

import android.app.Application;

import com.yrbase.response.RetrofitServiceManager;

import java.util.HashMap;
import java.util.Map;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


        /*
        * 公共参数
        *
        * 与主机地址
        *
        * */

        Map<String, String> commonParams = new HashMap<>();
        commonParams.put("userid", "6BC567AA653F6675E0531004273BE6C0");
        RetrofitServiceManager.commonParams = commonParams;

        RetrofitServiceManager.ENTERPORT = "http://47.107.134.212:15211/";







    }



}
