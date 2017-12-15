package com.wen_wen.firstsee.mvp.ui.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wen_wen.firstsee.R;
import com.wen_wen.firstsee.mvp.model.e.bean.LinkEntity;
import com.wen_wen.firstsee.mvp.presenter.impl.LinkPresenter;
import com.wen_wen.firstsee.mvp.ui.adapter.LinkAdapter;
import com.wen_wen.firstsee.mvp.ui.view.ILinkView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class LinkChildFragment extends BaseFragment implements ILinkView {
    @BindView(R.id.link_swipe)
    SwipeRefreshLayout linkSwipe;
    @BindView(R.id.link_recycler)
    RecyclerView linkRecycler;
    private Unbinder unbind;

    private String type;
    private String page;
    private LinkAdapter adapter;


     private LinkPresenter  linkPresenter;
    private boolean isRefresh  =false;

    public LinkChildFragment() {
    }

    public static LinkChildFragment getInstance(String type) {
        LinkChildFragment childFragment = new LinkChildFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        childFragment.setArguments(bundle);
        return childFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getString("type");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_link_child, container, false);
        unbind = ButterKnife.bind(this, view);
        linkPresenter  =  new LinkPresenter(this);
        requestLink();
        return view;
    }

    private void requestLink() {
        linkPresenter.load(getContext(),type,page);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        linkRecycler.setItemAnimator(new DefaultItemAnimator());
        linkRecycler.setLayoutManager(new GridLayoutManager(getContext(),2));
        adapter = new LinkAdapter(getContext());
        linkRecycler.setAdapter(adapter);
        linkSwipe.setColorSchemeColors(Color.parseColor("#333333"));
        adapter.setOnItemClickListener(new LinkAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                Toast.makeText(getContext(),"点击Item",Toast.LENGTH_SHORT).show();
            }
        });

        linkSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page  =  null;
               isRefresh  =  true;
                requestLink();
            }
        });

        linkRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int lastVisibleItemPosition;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState== RecyclerView.SCROLL_STATE_IDLE&&lastVisibleItemPosition==adapter.getItemCount()-1) {
                    requestLink();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
               // layoutManager.getItemCount();
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbind.unbind();
    }

    @Override
    public void onSuccess(List<LinkEntity> linkEntityList) {
        if (page==null) {
            page="1";
        }else {
            int I_page  =  Integer.parseInt(page);
            I_page  = I_page+1;
            page  = String.valueOf(I_page);
        }
        if (isRefresh ==true) {
            linkEntityList.clear();
            isRefresh  = false;
        }
        adapter.addAll(linkEntityList);
        linkSwipe.setRefreshing(false);
    }

    @Override
    public void onError(Throwable throwable) {
         linkSwipe.setRefreshing(false);
    }
}
