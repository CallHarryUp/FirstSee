package com.wen_wen.firstsee.mvp.ui.view;

import com.wen_wen.firstsee.mvp.model.e.bean.SeeEntity;

import java.util.List;

/**
 * Created by WeLot on 2017/12/14.
 */

public interface IseeView {

    void onSuccess(List<SeeEntity> seeEntityList);

    void OnError(Throwable throwable);
}
