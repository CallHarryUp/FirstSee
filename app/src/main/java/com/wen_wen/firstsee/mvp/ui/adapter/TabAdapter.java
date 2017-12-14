package com.wen_wen.firstsee.mvp.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.wen_wen.firstsee.mvp.ui.fragment.LinkChildFragment;
import com.wen_wen.firstsee.mvp.ui.fragment.SeeChildFragment;

import java.util.List;

import static com.wen_wen.firstsee.mvp.ui.fragment.ListenChildFragment.getnstance;

/**
 * Created by WeLot on 2017/12/12.
 */

public class TabAdapter extends FragmentPagerAdapter {
    private List<String> tabList;
    private String bottomTab;

    public TabAdapter(FragmentManager fm, List<String> tabList, String bottomTab) {
        super(fm);
        this.tabList = tabList;
        this.bottomTab = bottomTab;
    }

    @Override
    public Fragment getItem(int position) {
        if (bottomTab.equals("listen")) {
            return getnstance(tabList.get(position));
        } else if (bottomTab.equals("link")) {
            return LinkChildFragment.getInstance(tabList.get(position));
        } else {
            return SeeChildFragment.getInstance(tabList.get(position));
        }


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
