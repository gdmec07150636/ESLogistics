package com.example.rynfar.eslogistics;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;



/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends Fragment {
    EditText shipper_name;
    EditText shipper_phone;
    EditText shipper_tel;
    EditText shipper_location_detail;
    EditText point_detail;
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
    Spinner point_province;
    Spinner point_city;
    Spinner point_area;
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
    String DB_PATH = "/data/data/com.example.rynfar.eslogistics/databases/";
    String DB_NAME = "weather.db";
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

        init(v);
        getProvinceData();
        province_adapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,province_list);
        receiver_province.setAdapter(province_adapter);
        receiver_province.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tag = province_list.get(position);
                //Log.d("tag",province_list.toString());
                getCityData(tag);
                city_adapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,city_list);
                receiver_city.setAdapter(city_adapter);
                receiver_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String tag = city_list.get(position);
                        getAreaData(tag);
                        Log.d("onItemSelected: ",city_list.toString());
                        area_adapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,area_list);
                        receiver_area.setAdapter(area_adapter);
                        Log.d( "onItemSelected: ",area_list.toString());
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

        point_province.setAdapter(province_adapter);
        point_province.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tag = province_list.get(position);
                getCityData(tag);
                city_adapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,city_list);
                point_city.setAdapter(city_adapter);
                point_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String tag = city_list.get(position);
                        getAreaData(tag);
                        area_adapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,area_list);
                        point_area.setAdapter(area_adapter);
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
                String tag = province_list.get(position);
                getCityData(tag);
                city_adapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,city_list);
                shipper_city.setAdapter(city_adapter);
                shipper_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String tag = city_list.get(position);
                        getAreaData(tag);
                        area_adapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,area_list);
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
    public void writeSql(){
        // 检查 SQLite 数据库文件是否存在
        if ((new File(DB_PATH + DB_NAME)).exists() == false) {
            // 如 SQLite 数据库文件不存在，再检查一下 database 目录是否存在
            File f = new File(DB_PATH);
            // 如 database 目录不存在，新建该目录
            if (!f.exists()) {
                f.mkdir();
            }
            try {
                // 得到 assets 目录下我们实现准备好的 SQLite 数据库作为输入流
                InputStream is = getResources().openRawResource(R.raw.weather);
                // 输出流
                OutputStream os = new FileOutputStream(DB_PATH + DB_NAME);

                // 文件写入
                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }

                // 关闭文件流
                os.flush();
                os.close();
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void getProvinceData(){
        writeSql();
        SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(DB_PATH + DB_NAME, null);
        Cursor cursor = database.rawQuery("select distinct province_name from weathers", null);
        province_list = new ArrayList<>();
        if(cursor.getCount()>0){
            while (cursor.moveToNext()){
                //Log.d("province_name",cursor.getString(cursor.getColumnIndex("province_name")));
                province_list.add(cursor.getString(cursor.getColumnIndex("province_name")));
            }
        }
        //Log.d("province",province_list.toString());
    }

    public void getCityData(String province){
        SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(DB_PATH + DB_NAME, null);
        Cursor cursor = database.rawQuery("select distinct city_name from weathers where province_name='"+province+"'", null);
        city_list = new ArrayList<>();
        if(cursor.getCount()>0){
            while (cursor.moveToNext()){
                //Log.d("province_name",cursor.getString(cursor.getColumnIndex("province_name")));
                city_list.add(cursor.getString(cursor.getColumnIndex("city_name")));
            }
        }
    }
    public void getAreaData(String city){
        SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(DB_PATH + DB_NAME, null);
        Cursor cursor = database.rawQuery("select distinct area_name from weathers where city_name='"+city+"'", null);
        area_list = new ArrayList<>();
        if(cursor.getCount()>0){
            while (cursor.moveToNext()){
                //Log.d("province_name",cursor.getString(cursor.getColumnIndex("province_name")));
                area_list.add(cursor.getString(cursor.getColumnIndex("area_name")));
            }
        }
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
        point_province = (Spinner) v.findViewById(R.id.point_province);
        point_city = (Spinner) v.findViewById(R.id.point_city);
        point_area = (Spinner) v.findViewById(R.id.point_area);
        receiver_province = (Spinner) v.findViewById(R.id.receiver_province);
        receiver_city = (Spinner) v.findViewById(R.id.receiver_city);
        receiver_area = (Spinner) v.findViewById(R.id.receiver_area);
        ship_mode = (Spinner) v.findViewById(R.id.ship_mode);
        pay_mode = (Spinner) v.findViewById(R.id.pay_mode);
        receive_article = (CheckBox) v.findViewById(R.id.receive_article);
        submit_order = (Button) v.findViewById(R.id.submit_order);

    }
}
