package com.example.rynfar.eslogistics;

import android.util.Log;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;

/**
 * Created by rynfar on 2017/5/12.
 */

public class NavigationBarListener implements BottomNavigationBar.OnTabSelectedListener {
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
