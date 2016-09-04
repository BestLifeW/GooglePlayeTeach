package com.lovec.googleplayeteach.ui.holder;

import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.lidroid.xutils.BitmapUtils;
import com.lovec.googleplayeteach.R;
import com.lovec.googleplayeteach.http.HttpHelper;
import com.lovec.googleplayeteach.utils.UIUtils;

import java.util.ArrayList;


public class HomeHeaderHolder extends BaseHolder<ArrayList<String>> {

    private ViewPager mViewPager;
    private ArrayList<String> data;
    private LinearLayout llContainer;
    private int mPreviousPos;

    @Override
    public View initView() {
        RelativeLayout rlRoot = new RelativeLayout(UIUtils.getContext());
        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, UIUtils.dip2px(150));
        rlRoot.setLayoutParams(layoutParams);

        mViewPager = new ViewPager(UIUtils.getContext());
        RelativeLayout.LayoutParams vpParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        rlRoot.addView(mViewPager, vpParams);


        //初始化 指示器
        llContainer = new LinearLayout(UIUtils.getContext());
        llContainer.setOrientation(LinearLayout.HORIZONTAL);

        RelativeLayout.LayoutParams llParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int padding = UIUtils.dip2px(10);
        llContainer.setPadding(padding, padding, padding, padding);
        llParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);//底部对其
        llParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);//右对齐
        rlRoot.addView(llContainer, llParams);
        return rlRoot;
    }

    @Override
    public void refreshView(final ArrayList<String> data) {
        this.data = data;
        mViewPager.setAdapter(new HomeHeaderAdapter());
        mViewPager.setCurrentItem(data.size() * 10000);

        //初始化指示器
        for (int i = 0; i < data.size(); i++) {
            ImageView point = new ImageView(UIUtils.getContext());

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);

            if (i == 0) {// 第一个默认选中
                point.setImageResource(R.drawable.indicator_selected);
            } else {
                point.setImageResource(R.drawable.indicator_normal);

                params.leftMargin = UIUtils.dip2px(4);// 左边距
            }

            point.setLayoutParams(params);

            llContainer.addView(point);
        }

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                position = position % data.size();

                ImageView point = (ImageView) llContainer.getChildAt(position);
                point.setImageResource(R.drawable.indicator_selected);

                ImageView proPoint = (ImageView) llContainer.getChildAt(mPreviousPos);
                proPoint.setImageResource(R.drawable.indicator_normal);

                mPreviousPos = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        Handler handler = UIUtils.getHandler();
        HomeHeaderTask task = new HomeHeaderTask();
        task.start();
    }

    class HomeHeaderTask implements Runnable {
        public void start() {
            // 移除之前发送的所有消息, 避免消息重复
            UIUtils.getHandler().removeCallbacksAndMessages(null);
            UIUtils.getHandler().postDelayed(this, 3000);
        }

        @Override
        public void run() {
            int currentItem = mViewPager.getCurrentItem();
            currentItem++;
            mViewPager.setCurrentItem(currentItem);

            // 继续发延时3秒消息, 实现内循环
            UIUtils.getHandler().postDelayed(this, 3000);
        }

    }

    class HomeHeaderAdapter extends PagerAdapter {

        private final BitmapUtils bitmapUtils;

        public HomeHeaderAdapter() {
            bitmapUtils = new BitmapUtils(UIUtils.getContext());
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            position = position % data.size();

            String url = data.get(position);
            ImageView view = new ImageView(UIUtils.getContext());
            view.setScaleType(ImageView.ScaleType.FIT_XY);
            bitmapUtils.display(view, HttpHelper.URL + "image?name=" + url);

            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
