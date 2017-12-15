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
public class LinkFragment extends BaseFragment {
    private List<String> tabList;
    private Unbinder unbind;
    @BindView(R.id.link_tab)
    TabLayout linkTab;
    @BindView(R.id.link_pager)
    ViewPager linkPager;

    public LinkFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        tabList = new ArrayList<>();
        tabList.add("电影台词");
        tabList.add("小说摘抄");
        tabList.add("散文美句");
        tabList.add("动漫语录");
        tabList.add("古文名句");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_link, container, false);
        unbind = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TabAdapter adapter = new TabAdapter(getFragmentManager(), tabList,"link");
        linkPager.setAdapter(adapter);
        linkTab.setupWithViewPager(linkPager);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbind.unbind();
    }
}
