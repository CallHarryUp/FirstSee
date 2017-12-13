package com.wen_wen.firstsee.mvp.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wen_wen.firstsee.R;
import com.wen_wen.firstsee.mvp.ui.adapter.TabAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListenFragment extends BaseFragment {
    @BindView(R.id.listen_tab)
    TabLayout listenTab;
    @BindView(R.id.listen_pager)
    ViewPager listenPager;
    private Unbinder unbind;
    private List<String> tabList;

    public ListenFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        tabList = new ArrayList<>();
        tabList.add("美图美句");
        tabList.add("纸上心情");
        tabList.add("电影对白");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listen, container, false);
        unbind = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        listenTab.setTabMode(TabLayout.MODE_FIXED);
        TabAdapter adapter = new TabAdapter(getFragmentManager(), tabList);
        listenPager.setAdapter(adapter);
        listenTab.setupWithViewPager(listenPager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbind.unbind();
    }
}
