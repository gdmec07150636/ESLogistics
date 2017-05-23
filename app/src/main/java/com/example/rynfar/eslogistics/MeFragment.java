package com.example.rynfar.eslogistics;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import net.qiujuer.genius.graphics.Blur;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends Fragment {

    RecyclerView mRecyclerView;
    Context context;
    Bitmap bitmap;
    ImageView headbg;

    public MeFragment() {

    }

    public MeFragment(Context context) {
        this.context = context;
        new LoadBitmap().start();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.me);
        //toolbar.setLogo(R.mipmap.home_bg);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_me);
        collapsingToolbarLayout.setExpandedTitleMarginStart(-1000);
        headbg = (ImageView) view.findViewById(R.id.head_bg);
        headbg.setImageBitmap(bitmap);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.me_recycler_list);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        List<String> titles = new ArrayList<>();
        /*for (int i = 0; i < 4; i++) {
            titles.add("设置项" + i);
        }*/
        titles.add("全部订单");
        titles.add("我的地址");
        titles.add("实名认证");
        titles.add("反馈意见");
        titles.add("关于我们");
        mRecyclerView.setAdapter(new MeRecyclerViewAdapter(getContext(), titles));
        headbg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });
        return view;
    }

    public void Martix(Bitmap bitmap) {
        Log.d(TAG, "Martix: " + bitmap.getByteCount());
        Matrix matrix = new Matrix();
        matrix.setScale(0.1f, 0.1f);
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                bitmap.getHeight(), matrix, true);
        if (bitmap.getByteCount() / 1024 > 10) {
            Martix(bitmap);
        }

        Log.d(TAG, "Martix: " + bitmap.getByteCount());
    }

    private class LoadBitmap extends Thread {
        @Override
        public void run() {
            super.run();
            BitmapFactory.Options options2 = new BitmapFactory.Options();
            options2.inPreferredConfig = Bitmap.Config.RGB_565;
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.home_bg,options2);
            Martix(bitmap);
            Blur.onStackBlur(bitmap, 90);
        }
    }
}
