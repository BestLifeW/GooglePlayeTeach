package com.lovec.googleplayeteach.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.lovec.googleplayeteach.domain.AppInfo;
import com.lovec.googleplayeteach.http.protocol.HomeProtocol;
import com.lovec.googleplayeteach.ui.activity.HomeDetiailActivity;
import com.lovec.googleplayeteach.ui.adapter.MyBaseAdapter;
import com.lovec.googleplayeteach.ui.holder.BaseHolder;
import com.lovec.googleplayeteach.ui.holder.HomeHeaderHolder;
import com.lovec.googleplayeteach.ui.holder.HomeHolder;
import com.lovec.googleplayeteach.ui.view.LoadingPage.ResultState;
import com.lovec.googleplayeteach.ui.view.MyListView;
import com.lovec.googleplayeteach.utils.UIUtils;

import java.util.ArrayList;

/**
 * Created by lovec on 2016/8/27.
 */
public class HomeFragment extends BaseFragment {


    // private ArrayList<String> data;
    private ArrayList<AppInfo> data;
    private ArrayList<String> mPictureList;

    // 如果加载数据成功, 就回调此方法, 在主线程运行
    @Override
    public View onCreateSuccessView() {
        MyListView view = new MyListView(UIUtils.getContext());

        HomeHeaderHolder HeaderHolder = new HomeHeaderHolder();
        view.addHeaderView(HeaderHolder.getRootView());//先添加头布局 在设置adapter
        view.setAdapter(new HomeAdapter(data));
        if (mPictureList != null) {
            HeaderHolder.setData(mPictureList);
        }

        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                AppInfo appInfo = data.get(position - 1);//-1去掉头布局
                if (appInfo != null) {
                    Intent intent = new Intent(UIUtils.getContext(), HomeDetiailActivity.class);
                    intent.putExtra("packageName",appInfo.packageName);
                    startActivity(intent);
                }
            }
        });

        return view;
    }

    // 运行在子线程,可以直接执行耗时网络操作
    @Override
    public ResultState onLoad() {
        // 请求网络, HttpClient, HttpUrlConnection, XUtils
        // data = new ArrayList<String>();
        // for (int i = 0; i < 20; i++) {
        // data.add("测试数据:" + i);
        // }
        HomeProtocol protocol = new HomeProtocol();
        data = protocol.getData(0);// 加载第一页数据
        mPictureList = protocol.getPicturelist();
        return check(data);// 校验数据并返回
    }

    class HomeAdapter extends MyBaseAdapter<AppInfo> {

        public HomeAdapter(ArrayList<AppInfo> data) {
            super(data);
        }

        @Override
        public BaseHolder<AppInfo> getHolder(int position) {
            return new HomeHolder();
        }

        // 此方法在子线程调用
        @Override
        public ArrayList<AppInfo> onLoadMore() {
            HomeProtocol protocol = new HomeProtocol();
            ArrayList<AppInfo> moreData = protocol.getData(getListSize());
            return moreData;
        }


    }

    static class ViewHolder {
        public TextView tvContent;
    }

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
