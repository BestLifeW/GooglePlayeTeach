package com.lovec.googleplayeteach.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.lovec.googleplayeteach.R;
import com.lovec.googleplayeteach.ui.fragment.BaseFragment;
import com.lovec.googleplayeteach.ui.fragment.FragmentFactory;
import com.lovec.googleplayeteach.ui.view.PagerTab;
import com.lovec.googleplayeteach.utils.UIUtils;

/**
 * 当项目和appcompat关联在一起时，一定要设置主题
 * Created by lovec on 2016/8/27.
 */
public class MainActivity extends BaseActivity {

    private PagerTab mPagerTab;
    private ViewPager mViewpager;
    private MyAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mPagerTab = (PagerTab) findViewById(R.id.pager_tab);
        mViewpager = (ViewPager) findViewById(R.id.viewpager);
        mAdapter = new MyAdapter(getSupportFragmentManager());
        mViewpager.setAdapter(mAdapter);
        mPagerTab.setViewPager(mViewpager);


        mPagerTab.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                BaseFragment fragment = FragmentFactory.createFragment(position);
                fragment.loadData();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    class MyAdapter extends FragmentPagerAdapter {

        private String[] mTabNames;

        public MyAdapter(FragmentManager fm) {
            super(fm);
            mTabNames = UIUtils.getStringArray(R.array.tab_names);// 加载页面标题数组
        }

        // 返回页签标题
        @Override
        public CharSequence getPageTitle(int position) {
            return mTabNames[position];
        }

        // 返回当前页面位置的fragment对象
        @Override
        public Fragment getItem(int position) {
            BaseFragment fragment = FragmentFactory.createFragment(position);
            return fragment;
        }

        // fragment数量
        @Override
        public int getCount() {
            return mTabNames.length;
        }
    }
}
