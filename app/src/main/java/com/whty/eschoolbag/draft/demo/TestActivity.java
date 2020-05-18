package com.whty.eschoolbag.draft.demo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.whty.eschoolbag.draft.Draft;

public class TestActivity extends AppCompatActivity {

    private Draft draft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        draft = new Draft();
        draft.attach(this, false);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                TestFragment fragment = new TestFragment();
                Bundle bundle = new Bundle();
                bundle.putString("index", "" + i);
                fragment.setArguments(bundle);

                return fragment;
            }

            @Override
            public int getCount() {
                return 5;
            }
        });


        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                draft.setCurrentFrgIndex(position);
            }
        });

//        draft.setViewPager(viewPager);


        //我是分支 develop_ll 的修改


        // 1. 我是主线的修改 11：53


        //我是分支develop_ll 的二次修改

    }


}
