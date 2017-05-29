package com.example.rynfar.eslogistics;


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

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends Fragment {
    EditText shipper_name;
    EditText shipper_phone;
    EditText shipper_tel;
    EditText shipper_location_detail;
    EditText receiver_name;
    EditText receiver_phone;
    EditText receiver_tel;
    EditText receiver_detail;
    EditText good_name;
    EditText good_weight;
    EditText good_volume;
    EditText good_count;
    EditText good_package;
    EditText ship_mark;
    Spinner shipper_province;
    Spinner shipper_city;
    Spinner shipper_area;
    Spinner receiver_province;
    Spinner receiver_city;
    Spinner receiver_area;
    Spinner ship_mode;
    Spinner pay_mode;
    CheckBox receive_article;
    Button submit_order;
    private List<String> province_list;
    private List<String> city_list;
    private List<String> area_list;
    private ArrayAdapter province_adapter;
    private ArrayAdapter city_adapter;
    private ArrayAdapter area_adapter;

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
        View v = inflater.inflate(R.layout.fragment_order, container, false);
        Toolbar toolbar = (Toolbar) v.findViewById(R.id.order_toolbar);
        toolbar.setTitle(R.string.order);
        final LocationNamesHelper locationNames = LocationNamesHelper.getInstance(getContext());
        init(v);
        province_list = locationNames.getProvinceData();
        province_adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, province_list);
        receiver_province.setAdapter(province_adapter);
        receiver_province.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tag = parent.getSelectedItem().toString();
                city_list = locationNames.getCityData(tag);
                city_adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, city_list);
                receiver_city.setAdapter(city_adapter);
                receiver_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String tag = parent.getSelectedItem().toString();
                        area_list = locationNames.getAreaData(tag);
                        Log.d("onItemSelected: ", city_list.toString());
                        area_adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, area_list);
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
        });


        shipper_province.setAdapter(province_adapter);
        shipper_province.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                city_list = locationNames.getCityData(parent.getSelectedItem().toString());
                city_adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, city_list);
                shipper_city.setAdapter(city_adapter);
                shipper_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        area_list = locationNames.getAreaData(parent.getSelectedItem().toString());
                        area_adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, area_list);
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
        });

        return v;
    }

    void init(View v) {
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
    }
}
