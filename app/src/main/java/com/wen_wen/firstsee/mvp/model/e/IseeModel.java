package com.wen_wen.firstsee.mvp.model.e;

import android.content.Context;

import com.wen_wen.firstsee.mvp.presenter.callback.OnSeeListener;

/**
 * Created by WeLot on 2017/12/14.
 */

public interface IseeModel {

    void loadSee(Context context, String type, String page, OnSeeListener  onSeeListener );
}
