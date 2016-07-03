package com.su.lapponampai_w.mhm_app_beta1;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class AdherenceActivity extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    MyViewPagerAdaptor myViewPagerAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adherence);
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        myViewPagerAdaptor = new MyViewPagerAdaptor(getSupportFragmentManager());
        myViewPagerAdaptor.addFragments(new DailyFragment(),"รายวัน");
        myViewPagerAdaptor.addFragments(new WeeklyFragment(),"รายเดือน");

        viewPager.setAdapter(myViewPagerAdaptor);
        tabLayout.setupWithViewPager(viewPager);

    }
}
