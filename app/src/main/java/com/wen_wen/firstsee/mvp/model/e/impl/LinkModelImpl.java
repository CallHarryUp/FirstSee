package com.wen_wen.firstsee.mvp.model.e.impl;

import android.content.Context;

import com.wen_wen.firstsee.http.Api;
import com.wen_wen.firstsee.http.Service;
import com.wen_wen.firstsee.http.ServiceFactory;
import com.wen_wen.firstsee.mvp.model.e.IlinkModel;
import com.wen_wen.firstsee.mvp.model.e.bean.LinkEntity;
import com.wen_wen.firstsee.mvp.presenter.callback.OnLinkListener;
import com.wen_wen.firstsee.utils.DocParseUtil;
import com.wen_wen.firstsee.utils.StringUtil;

import java.io.InputStream;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by WeLot on 2017/12/15.
 */
//真正的进行网络请求  如果请求成功就将数据交给OnLinkListener
public class LinkModelImpl implements IlinkModel {
    private Service service;
    private OnLinkListener linkListener;
    private Context context;

    @Override
    public void loadLink(Context context, String type, String page, OnLinkListener linkListener) {
        this.context = context;
        this.linkListener = linkListener;

        this.service = ServiceFactory.getInstance().createService(Service.class, Api.BASE_URL_BURU);

        loadLinkInfo(type, page);
    }

    private void loadLinkInfo(String type, String page) {

        service.loadLink(type, page).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                InputStream inputStream = response.body().byteStream();
                String string = StringUtil.inToString(inputStream);

                List<LinkEntity> linkEntityList = DocParseUtil.parseLink(string);

                linkListener.onSuccess(linkEntityList);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                linkListener.onError(t);
            }
        });
    }
}
