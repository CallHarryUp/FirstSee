package com.wen_wen.firstsee.mvp.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

import com.wen_wen.firstsee.R;
import com.wen_wen.firstsee.mvp.ui.callback.OnScrollYListener;
import com.wen_wen.firstsee.mvp.ui.fragment.LinkFragment;
import com.wen_wen.firstsee.mvp.ui.fragment.ListenFragment;
import com.wen_wen.firstsee.mvp.ui.fragment.SeeFragment;
import com.wen_wen.firstsee.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements OnScrollYListener {
    @BindView(R.id.main_drawer)
    DrawerLayout mainDrawer;
    @BindView(R.id.main_card)
    CardView mainCard;
    @BindView(R.id.main_link_icon)
    ImageView link_icon;
    @BindView(R.id.main_link_state)
    TextView link_state;
    @BindView(R.id.main_listen_icon)
    ImageView listen_icon;
    @BindView(R.id.main_listen_state)
    TextView listen_state;
    @BindView(R.id.main_see_icon)
    ImageView see_icon;
    @BindView(R.id.mian_see_state)
    TextView see_state;
    private List<Fragment> fragmentList;
    private FragmentManager supportFragmentManager;
    private ListenFragment listenFragment;
    private LinkFragment linkFragment;
    private SeeFragment seeFragment;
    private boolean ispressListen = true;
    private boolean isPressLink = false;
    private boolean isPressSee = false;
    private int latestY;
    private int upTotalY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtil.addStatusView(this);
        ButterKnife.bind(this);
        initView();
    }

    @OnClick(R.id.main_meun)
    public void openDraw() {
        mainDrawer.openDrawer(Gravity.LEFT, true);
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
        if (ispressListen!=true) {
            Press(listen_icon,listen_state,R.mipmap.listen_press, Color.parseColor("#333333"));
            ispressListen   =  true;
        }

        if (isPressLink==true||isPressSee==true) {
            normal(link_icon,link_state,R.mipmap.link,Color.parseColor("#707070"));
            isPressLink  = false;
        }
        if (isPressSee==true) {
            normal(see_icon,see_state,R.mipmap.see,Color.parseColor("#707070"));
            isPressSee  =  false;
        }

    }
    @OnClick(R.id.main_link)
    public void showLinkFgt() {
        hideAllFgt();
        supportFragmentManager.beginTransaction().show(linkFragment).commit();
        Press(link_icon,link_state,R.mipmap.link_press, Color.parseColor("#333333"));
        isPressLink  =  true;
        if (ispressListen==true) {
            normal(listen_icon,listen_state,R.mipmap.listen,Color.parseColor("#707070"));
            ispressListen  = false;
        }
        if (isPressSee==true) {
            normal(see_icon,see_state,R.mipmap.see,Color.parseColor("#707070"));
            isPressSee  =  false;
        }
    }

    @OnClick(R.id.main_see)
    public void showSeeFgt() {
        hideAllFgt();
        supportFragmentManager.beginTransaction().show(seeFragment).commit();
        Press(see_icon,see_state,R.mipmap.see_press_2, Color.parseColor("#333333"));
        isPressSee  =  true;
        if (isPressLink==true) {
            normal(link_icon,link_state,R.mipmap.link,Color.parseColor("#707070"));
            isPressLink  = false;
        }
        if (ispressListen==true) {
            normal(listen_icon,listen_state,R.mipmap.listen,Color.parseColor("#707070"));
            ispressListen  =  false;
        }
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
    private void normal(ImageView icon, TextView state, int ivResId, int color) {
        icon.setImageResource(ivResId);
        state.setTextColor(color);
    }

    private void Press(ImageView icon, TextView state, int ivResId, int color) {
        icon.setImageResource(ivResId);
        state.setTextColor(color);
    }

    @Override
    public void onScrollY(int totalY) {// 如果lateetY 大于totalY 说明是下滑
        //另一种实现思路 我们只判断recyclerView是上滑 还是下滑  如果是上滑执行上滑的动画 如果是下滑执行下滑的动画


    }
}
