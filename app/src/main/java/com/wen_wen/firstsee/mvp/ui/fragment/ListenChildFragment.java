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
public class ListenChildFragment extends Fragment {


    public ListenChildFragment() {
        // Required empty public constructor
    }

    public static ListenChildFragment getnstance(String type) {
        ListenChildFragment fragment = new ListenChildFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        fragment.setArguments(bundle);
        return fragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_listen_child, container, false);
    }

}
