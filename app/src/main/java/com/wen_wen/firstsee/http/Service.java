package com.wen_wen.firstsee.http;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by WeLot on 2017/12/13.
 */

public interface Service {
    @GET("{type}")
    Call<ResponseBody> loadLink(@Path("type") String type, @Query("page") String page);

    @GET("{type}")
    Call<ResponseBody> loadSee(@Path("type") String type, @Query("page") String page);

    @GET
    Call<ResponseBody> loadListenFirst(@Url String url);

    @GET("{type}")
    Call<ResponseBody> loadListen(@Path("type") String type, @Query("page") String page);

    // 句子详情
    @GET
    Call<ResponseBody> loadJuziDetail(@Url String url);

}
