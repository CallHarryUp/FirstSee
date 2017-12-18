package com.wen_wen.firstsee.mvp.presenter;

import android.content.Context;

/**
 * Created by WeLot on 2017/12/15.
 */

public interface IListenerPresenter {
    void loadListen(Context context, boolean isFirst, String type, String page);

    void loadListen(Context context, boolean isFirst, String page);
}
