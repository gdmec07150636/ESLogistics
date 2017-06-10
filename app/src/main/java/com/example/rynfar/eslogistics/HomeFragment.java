package com.example.rynfar.eslogistics;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rynfar.eslogistics.tools.Tools;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class HomeFragment extends Fragment implements View.OnClickListener {
    private Button homeBtnPoint, homeBtnOrder, scan_code;
    EditText searchText;
    List<String> time;
    List<String> msg;
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

        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.home_list);
        initData();
        TraceAdapter adapter = new TraceAdapter(time, msg);
        recyclerView.setAdapter(adapter);
        homeBtnOrder = (Button) v.findViewById(R.id.home_btn_order);
        homeBtnOrder.setOnClickListener(this);
        homeBtnPoint = (Button) v.findViewById(R.id.home_btn_point);
        homeBtnPoint.setOnClickListener(this);
        scan_code = (Button) v.findViewById(R.id.scan_code);
        scan_code.setOnClickListener(this);
        return v;
    }

    private void initData() {
        sendRequestWithHttp();
    }

    /**
     * 获取最近订单
     */
    private void sendRequestWithHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    RequestBody requestBody = new FormBody.Builder().build();
                    Request request = new Request.Builder().url(Const.BASE_URL + Const.HOME_ORDER).post(requestBody).build();
                    Response response = client.newCall(request).execute();
                    String r = response.body().string();
                    parseJson(r);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 解析数据
     *
     * @param r
     */
    private void parseJson(String r) {
        time = new ArrayList<>();
        msg = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(r);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jo = jsonArray.getJSONObject(i);
                time.add(jo.getString("newtime"));
                msg.add(jo.getString("elocale"));
                Log.d("parseJson", "parseJson: " + time.toString() + "\n" + msg.toString());
            }

        } catch (JSONException e) {
            e.printStackTrace();
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
                    Tools.ShowShortToast(getContext(), result);
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Tools.ShowShortToast(getContext(), "解析二维码失败");
                }
            }
        }
    }

    class TraceAdapter extends RecyclerView.Adapter<TraceAdapter.ViewHolder> {
        List<String> times, messages;

        TraceAdapter(List<String> times, List<String> messages) {
            this.times = times;
            this.messages = messages;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View view = inflater.inflate(R.layout.recent_item, parent);
            Log.d("onCreateViewHolder", "onCreateViewHolder: ");
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.icon.setImageResource(position == 0 ? R.mipmap.ic_local_shipping_first : R.mipmap.ic_local_shipping_line);
            holder.time.setText(times.get(position));
            holder.message.setText(messages.get(position));
            Log.d("onBindViewHolder", "onBindViewHolder: " + times.get(position));
        }


        @Override
        public int getItemCount() {
            return times.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private ImageView icon;
            private TextView time, message;

            public ViewHolder(View itemView) {
                super(itemView);
                icon = (ImageView) itemView.findViewById(R.id.recent_head);
                time = (TextView) itemView.findViewById(R.id.recent_title);
                message = (TextView) itemView.findViewById(R.id.recent_summary);
            }
        }
    }

}
