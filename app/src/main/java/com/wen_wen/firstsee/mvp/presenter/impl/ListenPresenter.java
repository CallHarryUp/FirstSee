package com.wen_wen.firstsee.mvp.presenter.impl;

import android.content.Context;

import com.wen_wen.firstsee.mvp.model.e.bean.ListenListDetail;
import com.wen_wen.firstsee.mvp.presenter.IListenerPresenter;
import com.wen_wen.firstsee.mvp.presenter.callback.OnListenListener;

/**
 * Created by WeLot on 2017/12/15.
 */

public class ListenPresenter implements OnListenListener,IListenerPresenter {


    @Override
    public void loadListen(Context context, String type, String page) {

    }

    @Override
    public void onSuccess(ListenListDetail listenListDetailList) {

    }

    @Override
    public void onError(Throwable throwable) {

    }
}
