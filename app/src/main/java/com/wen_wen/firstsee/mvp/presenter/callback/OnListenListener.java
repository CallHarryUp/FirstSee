package com.wen_wen.firstsee.mvp.presenter.callback;

import com.wen_wen.firstsee.mvp.model.e.bean.ListenListDetail;

/**
 * Created by WeLot on 2017/12/15.
 */

public interface OnListenListener {

    void onSuccess(ListenListDetail listenListDetailList);

    void onError(Throwable throwable);
}
