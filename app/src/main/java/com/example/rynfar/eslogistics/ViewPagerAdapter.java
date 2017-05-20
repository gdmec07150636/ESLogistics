package com.example.rynfar.eslogistics;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by rynfar on 2017/5/13.
 */

class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Fragment>fragments;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public ViewPagerAdapter(FragmentManager fm,ArrayList<Fragment>fragments) {
        super(fm);
        this.fragments = fragments;
    }

    public void addFragment(Fragment fragment){
        fragments.add(fragment);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
