package com.example.rynfar.eslogistics;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends Fragment {


    public MeFragment() {
        // Required empty public constructor
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
        RecyclerView listView = (RecyclerView) view.findViewById(R.id.me_list);
        List<Map<String, Object>> list = new ArrayList<>();
        initData(list);
        listView.setAdapter(null);
        return view;
    }
    private void initData(List<Map<String, Object>> list) {
        for (int i = 0; i < 100; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("head", R.mipmap.ic_favorite_white_24dp);
            map.put("title", "标题" + i);
            map.put("summary", "内容摘要" + i);
            list.add(map);
        }
    }

}
