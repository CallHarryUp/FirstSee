package com.wen_wen.firstsee.mvp.ui.view;

import com.wen_wen.firstsee.mvp.model.e.bean.ListenListDetail;

/**
 * Created by WeLot on 2017/12/15.
 */

public interface IListenView {
    void onSuccess(ListenListDetail listenListDetail);

    void onError(Throwable throwable);
}
