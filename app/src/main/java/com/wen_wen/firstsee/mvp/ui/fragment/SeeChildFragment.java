package com.wen_wen.firstsee.mvp.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wen_wen.firstsee.R;
import com.wen_wen.firstsee.mvp.model.e.bean.SeeEntity;
import com.wen_wen.firstsee.mvp.ui.adapter.SeeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class SeeChildFragment extends Fragment {
    @BindView(R.id.see_child_swipe)
    SwipeRefreshLayout seeSwipe;
    @BindView(R.id.see_child_recycler)
    RecyclerView seeRecycler;

    private List<SeeEntity> seeEntityList;

    private String page;
    private String type;
    private Unbinder unbind;

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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_see_child, container, false);
        unbind = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        seeRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        seeRecycler.setItemAnimator(new DefaultItemAnimator());
        SeeAdapter adapter = new SeeAdapter(getContext());
        seeRecycler.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbind.unbind();
    }
}
