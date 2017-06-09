package com.example.rynfar.eslogistics;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.rynfar.eslogistics.tools.LocationNamesHelper;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends Fragment {
    EditText shipper_name, shipper_phone, shipper_tel, shipper_location_detail, receiver_name, receiver_phone, receiver_tel, receiver_detail, good_name, good_weight, good_volume, good_count, good_package, ship_mark;
    Spinner shipper_province,shipper_city,shipper_area,receiver_province,receiver_city,receiver_area,ship_mode,pay_mode;
    CheckBox receive_article;
    Button submit_order;
    private List<String> province_list;
    private List<String> city_list;
    private List<String> area_list;
    private ArrayAdapter province_adapter;
    private ArrayAdapter city_adapter;
    private ArrayAdapter area_adapter;
    private LocationNamesHelper locationNames;
    Map<String,String> order;
    Context context;

    public OrderFragment() {
        // Required empty public constructor

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = getContext();
        locationNames = LocationNamesHelper.getInstance(context);
        View v = inflater.inflate(R.layout.fragment_order, container, false);
        Toolbar toolbar = (Toolbar) v.findViewById(R.id.order_toolbar);
        toolbar.setTitle(R.string.order);
        initView(v);
        order = new HashMap<>();
        return v;
    }

    private void getOrderList() {
        //Tools.ShowShortToast(context,shipper_name.getText().toString());
        order.put("shipper_name",shipper_name.getText().toString());
        order.put("shipper_phone",shipper_phone.getText().toString());
        order.put("shipper_tel",shipper_tel.getText().toString());
        order.put("shipper_location_detail",shipper_location_detail.getText().toString());
        order.put("receiver_name",receiver_name.getText().toString());
        order.put("receiver_phone",receiver_phone.getText().toString());
        order.put("receiver_tel",receiver_tel.getText().toString());
        order.put("receiver_detail",receiver_detail.getText().toString());
        order.put("good_name",good_name.getText().toString());
        order.put("good_weight",good_weight.getText().toString());
        order.put("good_volume",good_volume.getText().toString());
        order.put("good_count",good_count.getText().toString());
        order.put("good_package",good_package.getText().toString());
        order.put("ship_mark",ship_mark.getText().toString());
        /*order.add(((TextView)shipper_province.getSelectedView()).getText().toString());
        order.add(((TextView)shipper_city.getSelectedView()).getText().toString());
        order.add(((TextView)shipper_area.getSelectedView()).getText().toString());
        order.add(((TextView)receiver_province.getSelectedView()).getText().toString());
        order.add(((TextView)receiver_city.getSelectedView()).getText().toString());
        order.add(((TextView)receiver_area.getSelectedView()).getText().toString());*/
        JSONObject jo = new JSONObject(order);
        Log.d(TAG, "getOrderList: "+jo.toString());
    }

    void initView(View v) {
        shipper_name = (EditText) v.findViewById(R.id.shipper_name);
        shipper_phone = (EditText) v.findViewById(R.id.shipper_phone);
        shipper_tel = (EditText) v.findViewById(R.id.shipper_tel);
        shipper_location_detail = (EditText) v.findViewById(R.id.shipper_location_detail);
        receiver_name = (EditText) v.findViewById(R.id.receiver_name);
        receiver_phone = (EditText) v.findViewById(R.id.receiver_phone);
        receiver_tel = (EditText) v.findViewById(R.id.receiver_tel);
        receiver_detail = (EditText) v.findViewById(R.id.receiver_detail);
        good_name = (EditText) v.findViewById(R.id.good_name);
        good_weight = (EditText) v.findViewById(R.id.good_weight);
        good_volume = (EditText) v.findViewById(R.id.good_volume);
        good_count = (EditText) v.findViewById(R.id.good_count);
        good_package = (EditText) v.findViewById(R.id.good_package);
        ship_mark = (EditText) v.findViewById(R.id.ship_mark);
        shipper_province = (Spinner) v.findViewById(R.id.shipper_province);
        shipper_city = (Spinner) v.findViewById(R.id.shipper_city);
        shipper_area = (Spinner) v.findViewById(R.id.shipper_area);
        receiver_province = (Spinner) v.findViewById(R.id.receiver_province);
        receiver_city = (Spinner) v.findViewById(R.id.receiver_city);
        receiver_area = (Spinner) v.findViewById(R.id.receiver_area);
        ship_mode = (Spinner) v.findViewById(R.id.ship_mode);
        pay_mode = (Spinner) v.findViewById(R.id.pay_mode);
        receive_article = (CheckBox) v.findViewById(R.id.receive_article);
        submit_order = (Button) v.findViewById(R.id.submit_order);
        province_list = locationNames.getProvinceData();
        province_adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, province_list);
        receiver_province.setAdapter(province_adapter);
        receiver_province.setOnItemSelectedListener(new ProvinceListener());
        shipper_province.setAdapter(province_adapter);
        shipper_province.setOnItemSelectedListener(new CityListener());
        submit_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOrderList();
            }
        });
    }

    class ProvinceListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String tag = parent.getSelectedItem().toString();
            city_list = locationNames.getCityData(tag);
            city_adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, city_list);
            receiver_city.setAdapter(city_adapter);
            receiver_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String tag = parent.getSelectedItem().toString();
                    area_list = locationNames.getAreaData(tag);
                    Log.d("onItemSelected: ", city_list.toString());
                    area_adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, area_list);
                    receiver_area.setAdapter(area_adapter);
                    Log.d("onItemSelected: ", area_list.toString());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    class CityListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            city_list = locationNames.getCityData(parent.getSelectedItem().toString());
            city_adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, city_list);
            shipper_city.setAdapter(city_adapter);
            shipper_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    area_list = locationNames.getAreaData(parent.getSelectedItem().toString());
                    area_adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, area_list);
                    shipper_area.setAdapter(area_adapter);
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    }
}
