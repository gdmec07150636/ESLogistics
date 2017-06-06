package com.example.rynfar.eslogistics;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.rynfar.eslogistics.tools.Tools;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HomeFragment extends Fragment implements View.OnClickListener {
    private Button homeBtnPoint, homeBtnOrder, scan_code;
    EditText searchText;
    private static int REQUEST_CODE = 1;


    public HomeFragment() {
        ZXingLibrary.initDisplayOpinion(getActivity());
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        searchText = (EditText) v.findViewById(R.id.search_order);
        searchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    Tools.ShowShortToast(getContext(), searchText.getText().toString());
                }
                return true;
            }
        });
        //searchText.setLines();
        ListView listView = (ListView) v.findViewById(R.id.home_list);
        List<Map<String, Object>> list = new ArrayList<>();
        initData(list);
        String[] from = {"head", "title", "summary"};
        int[] to = new int[]{R.id.message_head, R.id.message_title, R.id.message_summary};
        SimpleAdapter adapter = new SimpleAdapter(getContext(), list, R.layout.message_item, from, to);
        listView.setAdapter(adapter);
        homeBtnOrder = (Button) v.findViewById(R.id.home_btn_order);
        homeBtnOrder.setOnClickListener(this);
        homeBtnPoint = (Button) v.findViewById(R.id.home_btn_point);
        homeBtnPoint.setOnClickListener(this);
        scan_code = (Button) v.findViewById(R.id.scan_code);
        scan_code.setOnClickListener(this);
        return v;
    }

    private void initData(List<Map<String, Object>> list) {
        for (int i = 0; i < 10; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("head", R.drawable.ic_home_white_24dp);
            map.put("title", "标题" + i);
            map.put("summary", "内容摘要" + i);
            list.add(map);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.scan_code:
                Intent intent0 = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent0, REQUEST_CODE);
                break;
            case R.id.home_btn_order:
                Main main = (Main) getActivity();
                main.viewPager.setCurrentItem(1);
                break;
            case R.id.home_btn_point:
                Intent intent = new Intent(v.getContext(), MapActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Tools.ShowShortToast(getContext(),result);
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Tools.ShowShortToast(getContext(), "解析二维码失败");
                }
            }
        }
    }
}
