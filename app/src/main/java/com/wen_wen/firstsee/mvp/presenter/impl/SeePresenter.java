package com.wen_wen.firstsee.mvp.presenter.impl;

import android.content.Context;

import com.wen_wen.firstsee.mvp.model.e.IseeModel;
import com.wen_wen.firstsee.mvp.model.e.bean.SeeEntity;
import com.wen_wen.firstsee.mvp.model.e.impl.SeeModelImpl;
import com.wen_wen.firstsee.mvp.presenter.ISeePresenter;
import com.wen_wen.firstsee.mvp.presenter.callback.OnSeeListener;
import com.wen_wen.firstsee.mvp.ui.view.IseeView;

import java.util.List;

/**
 * Created by WeLot on 2017/12/14.
 */

public class SeePresenter implements ISeePresenter, OnSeeListener {
    private IseeView iseeView;
    private IseeModel iseeModel;

    public SeePresenter(IseeView iseeView) {
        this.iseeView = iseeView;
        this.iseeModel = new SeeModelImpl();
    }

    @Override
    public void loadSee(Context context, String type, String page) {
        iseeModel.loadSee(context, type, page, this);
    }

    @Override
    public void onSuccess(List<SeeEntity> seeEntityList) {
        iseeView.onSuccess(seeEntityList);
    }

    @Override
    public void onError(Throwable throwable) {
        iseeView.OnError(throwable);
    }
}
