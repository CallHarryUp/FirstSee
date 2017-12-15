package com.wen_wen.firstsee.mvp.ui.view;

import com.wen_wen.firstsee.mvp.model.e.bean.LinkEntity;

import java.util.List;

/**
 * Created by WeLot on 2017/12/15.
 */

public interface ILinkView {

    void onSuccess(List<LinkEntity> linkEntityList);

    void onError(Throwable throwable);
}
