package com.wen_wen.firstsee.mvp.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.wen_wen.firstsee.mvp.ui.fragment.ListenChildFragment;

import java.util.List;

/**
 * Created by WeLot on 2017/12/12.
 */

public class TabAdapter extends FragmentPagerAdapter {
    private List<String> tabList;

    public TabAdapter(FragmentManager fm, List<String> tabList) {
        super(fm);
        this.tabList = tabList;
    }

    @Override
    public Fragment getItem(int position) {
        return ListenChildFragment.getnstance(tabList.get(position));
    }

    @Override
    public int getCount() {
        return tabList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabList.get(position);
    }
}
