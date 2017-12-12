package com.wen_wen.firstsee.mvp.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import com.wen_wen.firstsee.R;
import com.wen_wen.firstsee.mvp.ui.fragment.LinkFragment;
import com.wen_wen.firstsee.mvp.ui.fragment.ListenFragment;
import com.wen_wen.firstsee.mvp.ui.fragment.SeeFragment;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @BindView(R.id.main_drawer)
    DrawerLayout mainDrawer;
    private List<Fragment> fragmentList;
    private FragmentManager supportFragmentManager;
    private ListenFragment listenFragment;
    private LinkFragment linkFragment;
    private SeeFragment seeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }
    @OnClick(R.id.main_meun)
    public void openDraw() {
        mainDrawer.openDrawer(Gravity.START, true);
    }
    private void initView() {
        initBottomBar();
    }
    private void initBottomBar() {
        fragmentList = new ArrayList<>();
        listenFragment = new ListenFragment();
        linkFragment = new LinkFragment();
        seeFragment = new SeeFragment();
        fragmentList.add(listenFragment);
        fragmentList.add(linkFragment);
        fragmentList.add(seeFragment);
        supportFragmentManager = getSupportFragmentManager();
        supportFragmentManager.beginTransaction().add(R.id.main_fgt_continer, listenFragment).commit();
        supportFragmentManager.beginTransaction().add(R.id.main_fgt_continer, linkFragment).hide(linkFragment).commit();
        supportFragmentManager.beginTransaction().add(R.id.main_fgt_continer, seeFragment).hide(seeFragment).commit();
    }

    @OnClick(R.id.main_listen)
    public void showListenFgt() {
        hideAllFgt();
        supportFragmentManager.beginTransaction().show(listenFragment).commit();
    }

    @OnClick(R.id.main_link)
    public void showLinkFgt() {
        hideAllFgt();
        supportFragmentManager.beginTransaction().show(linkFragment).commit();
    }

    @OnClick(R.id.main_see)
    public void showSeeFgt() {
        hideAllFgt();
        supportFragmentManager.beginTransaction().show(seeFragment).commit();
    }

    //隐藏所有fragment
    private void hideAllFgt() {
        hideFgt(linkFragment);
        hideFgt(listenFragment);
        hideFgt(seeFragment);
    }

    private void hideFgt(Fragment fragment) {
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        if (fragment != null) {
            transaction.hide(fragment).commit();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
