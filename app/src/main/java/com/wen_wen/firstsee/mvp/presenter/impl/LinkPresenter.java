package com.wen_wen.firstsee.mvp.presenter.impl;

import android.content.Context;

import com.wen_wen.firstsee.mvp.model.e.IlinkModel;
import com.wen_wen.firstsee.mvp.model.e.bean.LinkEntity;
import com.wen_wen.firstsee.mvp.model.e.impl.LinkModelImpl;
import com.wen_wen.firstsee.mvp.presenter.ILinkPresenter;
import com.wen_wen.firstsee.mvp.presenter.callback.OnLinkListener;
import com.wen_wen.firstsee.mvp.ui.view.ILinkView;

import java.util.List;

/**
 * Created by WeLot on 2017/12/15.
 */

public class LinkPresenter implements ILinkPresenter, OnLinkListener {
    private IlinkModel ilinkModel;
    private ILinkView iLinkView;

    public LinkPresenter(ILinkView iLinkView) {
        this.iLinkView = iLinkView;
        ilinkModel = new LinkModelImpl();
    }

    @Override
    public void load(Context context, String type, String page) {

        ilinkModel.loadLink(context, type, page,this);
    }

    @Override
    public void onSuccess(List<LinkEntity> linkEntityList) {

        iLinkView.onSuccess(linkEntityList);
    }

    @Override
    public void onError(Throwable throwable) {
        iLinkView.onError(throwable);
    }
}
