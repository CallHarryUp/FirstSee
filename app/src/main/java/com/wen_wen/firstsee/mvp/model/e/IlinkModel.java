package com.wen_wen.firstsee.mvp.model.e;

import android.content.Context;

import com.wen_wen.firstsee.mvp.presenter.callback.OnLinkListener;

/**
 * Created by WeLot on 2017/12/15.
 */
 //相对于presenter中只是多了一个监听
public interface IlinkModel {

    void  loadLink(Context  context , String type , String page , OnLinkListener  linkListener);
}
