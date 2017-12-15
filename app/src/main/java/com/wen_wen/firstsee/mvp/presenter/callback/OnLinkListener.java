package com.wen_wen.firstsee.mvp.presenter.callback;

import com.wen_wen.firstsee.mvp.model.e.bean.LinkEntity;

import java.util.List;

/**
 * Created by WeLot on 2017/12/15.
 */
//请求成功或者失败的回调
public interface OnLinkListener {
    void   onSuccess(List<LinkEntity>  linkEntityList);

    void  onError(Throwable  throwable);
}
