package com.example.rynfar.eslogistics;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

public class Main extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        BottomNavigationBar bar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bar
                .setMode(BottomNavigationBar.MODE_FIXED)
                .setActiveColor(R.color.navigation_active)
                .setInActiveColor(R.color.navigation_inactive)
        ;
        bar
                .addItem(new BottomNavigationItem(R.mipmap.ic_home_white_24dp, R.string.navigation_home))
                .addItem(new BottomNavigationItem(R.mipmap.ic_book_white_24dp, R.string.navigation_order))
                .addItem(new BottomNavigationItem(R.mipmap.ic_find_replace_white_24dp,R.string.navigation_info))
                .addItem(new BottomNavigationItem(R.mipmap.ic_favorite_white_24dp, R.string.navigation_mime))
                .initialise();
        bar.setTabSelectedListener(this);
    }

    @Override
    public void onTabSelected(int position) {
        switch (position) {
            case 0:
                Log.d("0", "onTabSelected: ");
                break;
            case 1:
                Log.d("1", "onTabSelected: ");
                break;
            case 2:
                Log.d("2", "onTabSelected: ");
                break;
            case 3:
                Log.d("3", "onTabSelected: ");
                break;
        }
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
