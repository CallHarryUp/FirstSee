package com.wen_wen.firstsee.mvp.ui.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wen_wen.firstsee.R;
import com.wen_wen.firstsee.mvp.model.e.bean.SeeEntity;
import com.wen_wen.firstsee.mvp.presenter.impl.SeePresenter;
import com.wen_wen.firstsee.mvp.ui.adapter.SeeAdapter;
import com.wen_wen.firstsee.mvp.ui.view.IseeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class SeeChildFragment extends Fragment implements IseeView {
    @BindView(R.id.see_child_swipe)
    SwipeRefreshLayout seeSwipe;
    @BindView(R.id.see_child_recycler)
    RecyclerView seeRecycler;
    private List<SeeEntity> seeEntityList;
    private String page;
    private String type;
    private Unbinder unbind;
    private SeePresenter seePresenter;
    private SeeAdapter adapter;
    private boolean isRefresh = false;

    public SeeChildFragment() {

    }

    public static SeeChildFragment getInstance(String type) {
        SeeChildFragment fragment = new SeeChildFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        seeEntityList = new ArrayList<>();
        //获取类型
        if (getArguments() != null) {
            type = getArguments().getString("type");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_see_child, container, false);
        unbind = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        //实现iSeeView 重写其中的两个方法
        seePresenter = new SeePresenter(this);
        //请求网络
        requestSee();
    }

    private void requestSee() {
        seePresenter.loadSee(getContext(), type, page);
    }

    private void initView() {
        seeRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        seeRecycler.setItemAnimator(new DefaultItemAnimator());
        adapter = new SeeAdapter(getContext());
        seeRecycler.setAdapter(adapter);
        seeSwipe.setColorSchemeColors(Color.parseColor("#707070"));
        //下拉刷新
        seeSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = null;
                isRefresh = true;
                //重新尽心数据访问
                requestSee();
            }
        });
        //上拉加载更多
        seeRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int lastVisibleItemPosition;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition == adapter.getItemCount() - 1) {
                    requestSee();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) seeRecycler.getLayoutManager();
                lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                adapter.getItemCount();
            }
        });
        //点击事件
        adapter.setOnItemClickListenr(new SeeAdapter.onItemClickListenr() {
            @Override
            public void onItemClick(int positon, View view) {
                Log.d("111","跳转到一见详情");
               // Snackbar.make(this,"跳转到一见详情",Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbind.unbind();
    }

    @Override
    public void onSuccess(List<SeeEntity> seeEntityList) {
        if (page == null) {
            page = "1";
        } else {
            int I_page = Integer.parseInt(page);
            I_page = I_page + 1;
            page = String.valueOf(I_page);
        }
        if (isRefresh == true) {
            seeEntityList.clear();
            isRefresh = false;

        }
        //添加数据
        adapter.addAll(seeEntityList);
        //停止刷新
        seeSwipe.setRefreshing(false);
    }

    @Override
    public void OnError(Throwable throwable) {

    }
}
