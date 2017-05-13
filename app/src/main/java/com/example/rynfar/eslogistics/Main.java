package com.example.rynfar.eslogistics;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import java.util.ArrayList;

import layout.MessageFragment;

public class Main extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {
    BottomNavigationBar bar;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        viewPager = (ViewPager) findViewById(R.id.viewpager_main);

        bar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bar
                .setMode(BottomNavigationBar.MODE_FIXED)
                //.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE)
                .setActiveColor(R.color.navigation_active)
                .setInActiveColor(R.color.navigation_inactive)
                .addItem(new BottomNavigationItem(R.mipmap.ic_home_white_24dp, R.string.navigation_home))
                .addItem(new BottomNavigationItem(R.mipmap.ic_book_white_24dp, R.string.navigation_order))
                .addItem(new BottomNavigationItem(R.mipmap.ic_find_replace_white_24dp,R.string.navigation_info))
                .addItem(new BottomNavigationItem(R.mipmap.ic_favorite_white_24dp, R.string.navigation_mime))
                .initialise();
        bar.setTabSelectedListener(this);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bar.selectTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ArrayList<Fragment>fragments = new ArrayList<>();
        fragments.add(new BlankFragment("主页"));
        fragments.add(new BlankFragment("下单"));
        fragments.add(new MessageFragment());
        fragments.add(new BlankFragment("我的"));
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(adapter);
    }


    @Override
    public void onTabSelected(int position) {
        viewPager.setCurrentItem(position);
        if(position==2){
            Main.this.setTitle("消息");
            Main.this.setTheme(R.style.AppTheme);
        }
        Log.d("555", "onTabSelected: "+position);
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
