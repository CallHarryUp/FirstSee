package com.wen_wen.firstsee.mvp.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wen_wen.firstsee.R;
import com.wen_wen.firstsee.mvp.model.e.bean.ListenEntity;
import com.wen_wen.firstsee.mvp.model.e.bean.ListenListDetail;
import com.wen_wen.firstsee.mvp.presenter.impl.ListenPresenter;
import com.wen_wen.firstsee.mvp.ui.adapter.ListenAdapter;
import com.wen_wen.firstsee.mvp.ui.view.IListenView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListenChildFragment extends BaseFragment implements IListenView {
    @BindView(R.id.listen_recycler)
    RecyclerView listenRecycler;
    @BindView(R.id.listen_swipe)
    SwipeRefreshLayout listenSwipe;

    private Unbinder unbind;
    private ListenAdapter adapter;
    private ListenPresenter listenPresenter;
    private String page;
    private String type;
    private boolean isRefresh = false;
    private boolean isFirst = true;

    public ListenChildFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public static ListenChildFragment getnstance(String type) {
        ListenChildFragment fragment = new ListenChildFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        fragment.setArguments(bundle);
        return fragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listen_child, container, false);
        unbind = ButterKnife.bind(this, view);
        type = getArguments().getString("type");
        listenPresenter = new ListenPresenter(this);
        requestListen();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listenRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        listenRecycler.setItemAnimator(new DefaultItemAnimator());
        adapter = new ListenAdapter(getContext());
        listenRecycler.setAdapter(adapter);
        listenSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                requestListen();
                isRefresh = true;

            }
        });

        adapter.setOnItemClickListener(new ListenAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                Toast.makeText(getContext(), "点击Item", Toast.LENGTH_SHORT).show();
            }
        });
        listenRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int lastVisibleItemPosition;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition == adapter.getItemCount() - 1) {
                    requestListen();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();

            }
        });
    }

    private void requestListen() {
        if (TextUtils.isEmpty(type)) {
            listenPresenter.loadListen(getContext(), isFirst, page);
        } else {
            listenPresenter.loadListen(getContext(), isFirst, type, page);
        }

    }

    @Override
    public void onSuccess(ListenListDetail listenListDetail) {
        if (page == null) {
            page = "1";
        } else {
            int I_page = Integer.parseInt(page);
            I_page = I_page + 1;
            page = String.valueOf(I_page);
        }
        if (isRefresh == true) {
            page = null;
            listenListDetail.listenEntityList.clear();
            listenListDetail.page = null;
            isRefresh = false;
        }
        List<ListenEntity> listenEntityList = listenListDetail.listenEntityList;
        adapter.addAll(listenEntityList);
        listenSwipe.setRefreshing(false);
    }

    @Override
    public void onError(Throwable throwable) {
        listenSwipe.setRefreshing(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbind.unbind();
    }
}
