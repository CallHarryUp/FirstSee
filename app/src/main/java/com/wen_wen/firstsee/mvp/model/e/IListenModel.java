package com.wen_wen.firstsee.mvp.model.e;

import android.content.Context;

import com.wen_wen.firstsee.mvp.presenter.callback.OnListenListener;

/**
 * Created by WeLot on 2017/12/15.
 */

public interface IListenModel {

    void loadListen(Context context, boolean isFirst, String type, String page, OnListenListener listenListener);

    void loadListen(Context context, boolean isFirst, String page, OnListenListener listenListener);
}
