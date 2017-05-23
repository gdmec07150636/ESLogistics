package com.example.rynfar.eslogistics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderAllActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ListView listView;
    List<Map<String, Object>> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_all);
        toolbar = (Toolbar) findViewById(R.id.order_all_toolbar);
        listView = (ListView) findViewById(R.id.order_all_list);
        String[] from = {"head", "title", "summary"};
        int to [] = new int[]{R.id.order_head,R.id.order_title,R.id.order_summary};
        list = new ArrayList<>();
        initData(list);
        toolbar.setTitle(R.string.my_order);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(OrderAllActivity.this, "666", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });
        SimpleAdapter adapter = new SimpleAdapter(this,list,R.layout.order_item,from,to);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView itemId = (TextView) view.findViewById(R.id.order_item_id);
                //Log.d("666",itemId.getText().toString());
                //Toast.makeText(OrderAllActivity.this, itemId.getText().toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view.getContext(),OrderInfoActivity.class);
                startActivity(intent);
            }
        });
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
}
