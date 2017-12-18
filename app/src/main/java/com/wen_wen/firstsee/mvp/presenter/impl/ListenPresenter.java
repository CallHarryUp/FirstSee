package com.wen_wen.firstsee.mvp.presenter.impl;

import android.content.Context;

import com.wen_wen.firstsee.mvp.model.e.IListenModel;
import com.wen_wen.firstsee.mvp.model.e.bean.ListenListDetail;
import com.wen_wen.firstsee.mvp.model.e.impl.ListenerModelImpl;
import com.wen_wen.firstsee.mvp.presenter.IListenerPresenter;
import com.wen_wen.firstsee.mvp.presenter.callback.OnListenListener;
import com.wen_wen.firstsee.mvp.ui.view.IListenView;

/**
 * Created by WeLot on 2017/12/15.
 */

public class ListenPresenter implements OnListenListener, IListenerPresenter {
    private IListenView iListenView;
    private IListenModel iListenModel;

    public ListenPresenter(IListenView iListenView) {
        this.iListenView = iListenView;
        iListenModel = new ListenerModelImpl();
    }


    @Override
    public void onSuccess(ListenListDetail listenListDetailList) {
        iListenView.onSuccess(listenListDetailList);
    }

    @Override
    public void onError(Throwable throwable) {
        iListenView.onError(throwable);
    }


    @Override
    public void loadListen(Context context, boolean isFirst, String type, String page) {
        iListenModel.loadListen(context, isFirst, type, page, this);
    }

    @Override
    public void loadListen(Context context, boolean isFirst, String page) {
        iListenModel.loadListen(context,isFirst,page,this);
    }
}
