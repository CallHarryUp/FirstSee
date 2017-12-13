package com.wen_wen.firstsee.http;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by WeLot on 2017/12/13.
 */

public class ServiceFactory {

    private static final long DEFAUT_TIME_OUT = 10;

    public ServiceFactory() {
    }

    private static class SingletonHolder {

        private static final ServiceFactory INSTANCE = new ServiceFactory();
    }

    public static ServiceFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public <T> T createService(Class<T> serviceClass, String baseUrl) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(getOkhttpClient())
                .build();

        return retrofit.create(serviceClass);
    }

    private OkHttpClient getOkhttpClient() {
        //定制okhttp
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new RetrofitIntercept());

        //设置超时时间
        builder.connectTimeout(DEFAUT_TIME_OUT, TimeUnit.SECONDS);
        builder.writeTimeout(DEFAUT_TIME_OUT, TimeUnit.SECONDS);
        builder.readTimeout(DEFAUT_TIME_OUT, TimeUnit.SECONDS);
        return builder.build();
    }
}
