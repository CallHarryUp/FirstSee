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
public class SeeFragment extends Fragment {
    @BindView(R.id.see_tab)
    TabLayout seeTab;
    @BindView(R.id.see_pager)
    ViewPager seePager;
    private List<String> tabList;
    private Unbinder unbind;

    public SeeFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        tabList = new ArrayList<>();
        tabList.add("精选剧集");
        tabList.add("最新剧集");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_see, container, false);
        unbind = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TabAdapter adapter = new TabAdapter(getFragmentManager(), tabList);
        seePager.setAdapter(adapter);
        seeTab.setupWithViewPager(seePager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbind.unbind();
    }
}
