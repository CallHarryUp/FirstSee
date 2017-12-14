package com.wen_wen.firstsee.mvp.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.wen_wen.firstsee.mvp.ui.fragment.LinkChildFragment;
import com.wen_wen.firstsee.mvp.ui.fragment.SeeChildFragment;

import java.util.ArrayList;
import java.util.List;

import static com.wen_wen.firstsee.mvp.ui.fragment.ListenChildFragment.getnstance;

/**
 * Created by WeLot on 2017/12/12.
 */

public class TabAdapter extends FragmentPagerAdapter {
    private List<String> tabList;
    private String bottomTab;
    private  List<String> seeTypeList;
    public TabAdapter(FragmentManager fm, List<String> tabList, String bottomTab) {
        super(fm);
        this.tabList = tabList;
        this.bottomTab = bottomTab;
        seeTypeList  =  new ArrayList<>();
        seeTypeList.add("albums");
        seeTypeList.add("newalbums");
    }

    @Override
    public Fragment getItem(int position) {
        if (bottomTab.equals("listen")) {
            Log.d("111","执行 listen");
            return getnstance(tabList.get(position));
        } else if (bottomTab.equals("link")) {
            Log.d("111","执行  link");
            return LinkChildFragment.getInstance(tabList.get(position));
        } else {
            Log.d("111","执行 see");
            return SeeChildFragment.getInstance(seeTypeList.get(position));
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
