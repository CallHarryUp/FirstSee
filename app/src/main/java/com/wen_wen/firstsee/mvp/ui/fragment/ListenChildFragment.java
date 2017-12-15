package com.wen_wen.firstsee.mvp.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wen_wen.firstsee.R;
import com.wen_wen.firstsee.mvp.ui.adapter.ListenAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListenChildFragment extends BaseFragment {
    @BindView(R.id.listen_recycler)
    RecyclerView listenRecycler;
    @BindView(R.id.listen_swipe)
    SwipeRefreshLayout listenSwipe;

    private Unbinder unbind;
    private ListenAdapter adapter;

    public ListenChildFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        View view = inflater.inflate(R.layout.fragment_listen_child, container, false);
        unbind = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listenRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        listenRecycler.setItemAnimator(new DefaultItemAnimator());

        adapter = new ListenAdapter(getContext());
        listenRecycler.setAdapter(adapter);
        listenSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });

        adapter.setOnItemClickListener(new ListenAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {

            }
        });

        listenSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
    }
}
