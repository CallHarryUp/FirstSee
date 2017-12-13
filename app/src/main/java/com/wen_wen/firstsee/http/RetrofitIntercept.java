package com.wen_wen.firstsee.http;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by WeLot on 2017/12/13.
 */

public class RetrofitIntercept implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        String message = request.method() + "url:" + request.url();
        Log.d("111", "Message:" + message);
        Response response = chain.proceed(request);

        Log.d("111", "respone code :" + response.code()  + "respone Message"+response.message());

        return response;
    }
}
