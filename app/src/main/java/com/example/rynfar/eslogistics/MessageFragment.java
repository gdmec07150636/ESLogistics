package com.example.rynfar.eslogistics;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends Fragment {
    List<Map<String, Object>> list;

    public MessageFragment() {
        // Required empty public constructor
        init();
    }

    private void init() {
        list = new ArrayList<>();
        initData(list);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_message, container, false);
        Toolbar toolbar = (Toolbar) v.findViewById(R.id.message_toolbar);
        toolbar.setTitle(R.string.message_title);
        ListView listView = (ListView) v.findViewById(R.id.message_list);
        //List<Map<String, Object>> list = new ArrayList<>();
        //initData(list);
        String[] from = {"head", "title", "summary"};
        int[] to = new int[]{R.id.message_head, R.id.message_title, R.id.message_summary};
        SimpleAdapter adapter = new SimpleAdapter(getContext(), list, R.layout.message_item, from, to);
        listView.setAdapter(adapter);
        return v;
    }

    private void initData(List<Map<String, Object>> list) {
        for (int i = 0; i < 100; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("head", R.mipmap.home_bg);
            map.put("title", "标题" + i);
            map.put("summary", "内容摘要" + i);
            list.add(map);
        }
    }

}
