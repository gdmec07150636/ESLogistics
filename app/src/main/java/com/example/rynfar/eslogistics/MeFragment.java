package com.example.rynfar.eslogistics;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rynfar.eslogistics.tools.Tools;

import java.util.ArrayList;
import java.util.List;

import cn.carbs.android.avatarimageview.library.AvatarImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends Fragment {

    RecyclerView mRecyclerView;
    Context context;
    Bitmap bitmap;
    AvatarImageView head_icon;

    public MeFragment() {

    }

    public MeFragment(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.me);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_me);
        AppBarLayout appBarLayout = (AppBarLayout) view.findViewById(R.id.appbar);
        collapsingToolbarLayout.setExpandedTitleMarginStart(-1000);
        if (bitmap == null) {
            bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.home_bg);
            Tools.MartixBitmap(bitmap,0.01f,0.01f);
            bitmap = Tools.blur(context,bitmap);
        }
        appBarLayout.setBackground(new BitmapDrawable(bitmap));
        mRecyclerView = (RecyclerView) view.findViewById(R.id.me_recycler_list);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        List<String> titles = new ArrayList<>();
        List<Integer> icons = new ArrayList<>();
        icons.add(R.drawable.ic_home_black_24dp);
        titles.add("全部订单");
        icons.add(R.drawable.ic_keyboard_arrow_left_black_24dp);
        titles.add("我的地址");
        icons.add(R.drawable.ic_dashboard_black_24dp);
        titles.add("实名认证");
        icons.add(R.drawable.ic_notifications_black_24dp);
        titles.add("反馈意见");
        icons.add(R.drawable.ic_home_black_24dp);
        titles.add("关于我们");
        mRecyclerView.setAdapter(new MeRecyclerViewAdapter(getContext(), titles, icons));
        head_icon = (AvatarImageView) view.findViewById(R.id.head_icon);
        head_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });
        return view;
    }
}
