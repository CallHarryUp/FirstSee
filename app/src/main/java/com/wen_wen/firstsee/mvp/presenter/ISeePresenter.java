package com.wen_wen.firstsee.mvp.presenter;

import android.content.Context;

/**
 * Created by WeLot on 2017/12/14.
 */
//一见  用户的操作反馈到数据层面
public interface ISeePresenter {

    void loadSee(Context context, String type, String page);
}
