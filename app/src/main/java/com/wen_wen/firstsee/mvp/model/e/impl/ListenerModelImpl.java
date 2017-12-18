package com.wen_wen.firstsee.mvp.model.e.impl;

import android.content.Context;
import android.text.TextUtils;

import com.wen_wen.firstsee.http.Api;
import com.wen_wen.firstsee.http.Service;
import com.wen_wen.firstsee.http.ServiceFactory;
import com.wen_wen.firstsee.mvp.model.e.IListenModel;
import com.wen_wen.firstsee.mvp.model.e.bean.ListenListDetail;
import com.wen_wen.firstsee.mvp.presenter.callback.OnListenListener;
import com.wen_wen.firstsee.utils.DocParseUtil;
import com.wen_wen.firstsee.utils.StringUtil;

import java.io.InputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by WeLot on 2017/12/15.
 */

public class ListenerModelImpl implements IListenModel {
    private Service service;
    private Context context;
    private OnListenListener listenListener;

    @Override
    public void loadListen(Context context, boolean isFirst, String type, String page, OnListenListener listenListener) {

        this.context = context;
        this.listenListener = listenListener;

        this.service = ServiceFactory.getInstance().createService(Service.class, Api.BASE_URL_BAIWEN);

        loadListenInfo(isFirst, type, page);
    }

    @Override
    public void loadListen(Context context, boolean isFirst, String page, OnListenListener listenListener) {
        this.context = context;
        this.listenListener = listenListener;
        this.service = ServiceFactory.getInstance().createService(Service.class, Api.BASE_URL_BAIWEN);
      loadListenInfo(isFirst, null, page);
    }

    private void loadListenInfo(final boolean isFirst, String type, String page) {
        Call<ResponseBody> call = null;
        if (TextUtils.isEmpty(type)) {

            String url = Api.BASE_URL_BAIWEN;
            if (!TextUtils.isEmpty(page)) {
                url = "url" + "?page" + page;
            }
            call = service.loadListenFirst(url);
        } else {

            call = service.loadListen(type, page);
        }

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                InputStream inputStream = response.body().byteStream();
                String string = StringUtil.inToString(inputStream);

                ListenListDetail listenListDetail = DocParseUtil.parseMeiju(isFirst, string);

                listenListener.onSuccess(listenListDetail);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }
}
