package com.example.rynfar.eslogistics;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends Fragment {
    List<Map<String, Object>> list;
    Toolbar toolbar;
    ListView listView;

    public MessageFragment() {
        // Required empty public constructor
        init();
    }

    private void init() {
        Log.d("aaaaaaaa", "init: " + (list == null));
        if (list == null)
            list = new ArrayList<>();
        initData(list);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_message, container, false);
        toolbar = (Toolbar) v.findViewById(R.id.message_toolbar);
        toolbar.setTitle(R.string.message_title);
        listView = (ListView) v.findViewById(R.id.message_list);
        //List<Map<String, Object>> list = new ArrayList<>();
        //initViews(list);
        String[] from = {"head", "title", "summary"};
        int[] to = new int[]{R.id.message_head, R.id.message_title, R.id.message_summary};
        SimpleAdapter adapter = new SimpleAdapter(getContext(), list, R.layout.message_item, from, to);
        listView.setAdapter(adapter);
        return v;
    }

    private void initData(List<Map<String, Object>> list) {
        for (int i = 0; i < 10; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("head", R.mipmap.home_bg);
            map.put("title", "标题" + i);
            map.put("summary", "内容摘要" + i);
            list.add(map);
        }
    }

    private int getStatusBarHeight() {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, sbar = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            sbar = getContext().getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return sbar;
    }
}
