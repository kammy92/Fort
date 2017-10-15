package com.karman.fort.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.karman.fort.R;
import com.karman.fort.fragment.ActivityListFragment;
import com.karman.fort.utils.Utils;

import java.util.ArrayList;
import java.util.List;


public class ActivitiesActivity extends AppCompatActivity {
    Toolbar toolbar;
    CoordinatorLayout clMain;
    RelativeLayout rlBack;
    TabLayout tabLayout;
    TextView tvTitle;
    AppBarLayout appBar;
    private ViewPager viewPager;
    
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_activities);
        initView ();
        initData ();
        initListener ();
    }
    
    private void initView () {
        rlBack = (RelativeLayout) findViewById (R.id.rlBack);
        clMain = (CoordinatorLayout) findViewById (R.id.clMain);
        toolbar = (Toolbar) findViewById (R.id.toolbar);
        tabLayout = (TabLayout) findViewById (R.id.tabs);
        viewPager = (ViewPager) findViewById (R.id.viewpager);
        tvTitle = (TextView) findViewById (R.id.tvTitle);
        appBar = (AppBarLayout) findViewById (R.id.appBar);
    }
    
    private void initData () {
        tabLayout.setupWithViewPager (viewPager);
        tabLayout.setTabGravity (TabLayout.GRAVITY_FILL);
        setupViewPager (viewPager);
        Utils.setTypefaceToAllViews (this, rlBack);
    }
    
    private void initListener () {
        rlBack.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                finish ();
                overridePendingTransition (R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
    }
    
    private void setupViewPager (ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter (getSupportFragmentManager ());
        adapter.addFragment (new ActivityListFragment (), "Ambarsari Zaika", 1);
        adapter.addFragment (new ActivityListFragment (), "Attraction", 2);
        adapter.addFragment (new ActivityListFragment (), "Haat Bazar", 3);
        adapter.addFragment (new ActivityListFragment (), "Musuems",4);
        adapter.addFragment (new ActivityListFragment (), "Shows",5);
        viewPager.setAdapter (adapter);
    }
    
    @Override
    public void onBackPressed () {
        finish ();
        overridePendingTransition (R.anim.slide_in_left, R.anim.slide_out_right);
    }
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<> ();
        private final List<String> mFragmentTitleList = new ArrayList<> ();
        private final List<Integer> mFragmentIDList = new ArrayList<> ();
    
        public ViewPagerAdapter (FragmentManager manager) {
            super (manager);
        }
        
        @Override
        public Fragment getItem (int position) {
            return ActivityListFragment.newInstance (mFragmentIDList.get (position));
        }
        
        @Override
        public int getCount () {
            return mFragmentList.size ();
        }
        
        public void addFragment (Fragment fragment, String title, int activity_id) {
            mFragmentList.add (fragment);
            mFragmentTitleList.add (title);
            mFragmentIDList.add (activity_id);
        }
        
        @Override
        public CharSequence getPageTitle (int position) {
            return mFragmentTitleList.get (position);
        }
    }
}