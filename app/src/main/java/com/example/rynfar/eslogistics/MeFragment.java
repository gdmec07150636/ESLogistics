package com.example.rynfar.eslogistics;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends Fragment {

    RecyclerView mRecyclerView;
    Context context;
    Bitmap bgBitmap;

    public MeFragment() {
        // Required empty public constructor
    }

    public MeFragment(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bgBitmap = BitmapFactory.decodeResource(getActivity().getResources(), R.mipmap.home_bg);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        View headBG = view.findViewById(R.id.head_bg);
        headBG.setBackground(new BitmapDrawable(bgBitmap));
        mRecyclerView = (RecyclerView) view.findViewById(R.id.me_recycler_list);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        List<String> titles = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            titles.add("设置项"+i);
        }
        mRecyclerView.setAdapter(new MeRecyclerViewAdapter(getContext(), titles));
        return view;
    }

}
