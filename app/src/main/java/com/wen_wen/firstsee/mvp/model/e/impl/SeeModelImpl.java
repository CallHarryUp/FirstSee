package com.wen_wen.firstsee.mvp.model.e.impl;

import android.content.Context;

import com.wen_wen.firstsee.http.Api;
import com.wen_wen.firstsee.http.Service;
import com.wen_wen.firstsee.http.ServiceFactory;
import com.wen_wen.firstsee.mvp.model.e.IseeModel;
import com.wen_wen.firstsee.mvp.model.e.bean.SeeEntity;
import com.wen_wen.firstsee.mvp.presenter.callback.OnSeeListener;
import com.wen_wen.firstsee.utils.DocParseUtil;
import com.wen_wen.firstsee.utils.StringUtil;

import java.io.InputStream;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by WeLot on 2017/12/14.
 */
//真正做网路请求实现数据获取
public class SeeModelImpl implements IseeModel {
    private Service service;
    private OnSeeListener listener;
    private Context context;

    @Override
    public void loadSee(Context context, String type, String page, OnSeeListener onSeeListener) {
        this.context = context;
        this.listener = onSeeListener;


        this.service = ServiceFactory.getInstance().createService(Service.class, Api.BASE_URL_YIJIAN);

        loadSeeInfo(type, page);
    }

    private void loadSeeInfo(String type, String page) {
        Call<ResponseBody> call = service.loadSee(type, page);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                InputStream inputStream = response.body().byteStream();
                String string = StringUtil.inToString(inputStream);
                //解析
                List<SeeEntity> seeEntityList = DocParseUtil.parseSee(string);

                listener.onSuccess(seeEntityList);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                listener.onError(t);
            }
        });


    }
}
