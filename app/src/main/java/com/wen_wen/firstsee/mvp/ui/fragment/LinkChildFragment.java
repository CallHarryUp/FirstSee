package com.wen_wen.firstsee.mvp.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wen_wen.firstsee.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LinkChildFragment extends BaseFragment {


    public LinkChildFragment() {
        // Required empty public constructor
    }
    public  static   LinkChildFragment  getInstance(String type){
        LinkChildFragment  childFragment  =  new LinkChildFragment();
        Bundle  bundle  =  new Bundle();
        bundle.putString("type",type);
        childFragment.setArguments(bundle);
        return   childFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_link_child, container, false);
    }

}
