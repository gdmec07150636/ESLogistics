package com.example.rynfar.eslogistics.tools;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.rynfar.eslogistics.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rynfar on 2017/5/29.
 */

public class LocationNamesHelper {
    private String DB_PATH = null;
    private String DB_NAME = "weather.db";
    private boolean isWritten = false;
    private static LocationNamesHelper locationNames = null;

    public static LocationNamesHelper getInstance(Context context) {
        if (locationNames == null) {
            locationNames = new LocationNamesHelper(context);
        }
        return locationNames;
    }

    private LocationNamesHelper(Context context){
        DB_PATH = "/data/data/"+context.getPackageName()+"/databases/";
        writeSql(context);
    }

    public void writeSql(Context context) {
        // 检查 SQLite 数据库文件是否存在
        if (!(new File(DB_PATH + DB_NAME)).exists()) {
            // 如 SQLite 数据库文件不存在，再检查一下 database 目录是否存在
            File f = new File(DB_PATH);
            // 如 database 目录不存在，新建该目录
            if (!f.exists()) {
                f.mkdir();
            }
            try {
                // 得到 assets 目录下我们实现准备好的 SQLite 数据库作为输入流
                InputStream is = context.getResources().openRawResource(R.raw.weather);
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
                isWritten = true;
            } catch (Exception e) {
                e.printStackTrace();
                isWritten = false;
            }
        }else{
            isWritten = true;
        }
    }

    public List<String> getProvinceData() {
        if (!isWritten) return null;
        List<String> province_list;
        SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(DB_PATH + DB_NAME, null);
        Cursor cursor = database.rawQuery("select distinct province_name from weathers", null);
        province_list = new ArrayList<>();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                //Log.d("province_name",cursor.getString(cursor.getColumnIndex("province_name")));
                province_list.add(cursor.getString(cursor.getColumnIndex("province_name")));
            }
        }
        //Log.d("province",province_list.toString());
        return province_list;
    }

    public List<String> getCityData(String province) {
        if (!isWritten) return null;
        List<String> city_list;
        SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(DB_PATH + DB_NAME, null);
        Cursor cursor = database.rawQuery("select distinct city_name from weathers where province_name='" + province + "'", null);
        city_list = new ArrayList<>();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                //Log.d("province_name",cursor.getString(cursor.getColumnIndex("province_name")));
                city_list.add(cursor.getString(cursor.getColumnIndex("city_name")));
            }
        }
        return city_list;
    }

    public List<String> getAreaData(String city) {
        List<String> area_list;
        SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(DB_PATH + DB_NAME, null);
        Cursor cursor = database.rawQuery("select distinct area_name from weathers where city_name='" + city + "'", null);
        area_list = new ArrayList<>();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                //Log.d("province_name",cursor.getString(cursor.getColumnIndex("province_name")));
                area_list.add(cursor.getString(cursor.getColumnIndex("area_name")));
            }
        }
        return area_list;
    }

}
