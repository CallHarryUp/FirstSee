package com.wen_wen.firstsee.mvp.presenter.callback;

import com.wen_wen.firstsee.mvp.model.e.bean.SeeEntity;

import java.util.List;

/**
 * Created by WeLot on 2017/12/14.
 */
//一见的接口回调
public interface OnSeeListener {
    void onSuccess(List<SeeEntity> seeEntityList);

    void onError(Throwable throwable);
}
