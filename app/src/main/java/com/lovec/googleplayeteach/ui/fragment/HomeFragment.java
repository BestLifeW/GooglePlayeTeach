package com.lovec.googleplayeteach.ui.fragment;

import android.view.View;
import android.widget.TextView;

import com.lovec.googleplayeteach.domain.AppInfo;
import com.lovec.googleplayeteach.http.protocol.HomeProtocol;
import com.lovec.googleplayeteach.ui.adapter.MyBaseAdapter;
import com.lovec.googleplayeteach.ui.holder.BaseHolder;
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

    // 如果加载数据成功, 就回调此方法, 在主线程运行
    @Override
    public View onCreateSuccessView() {
        MyListView  view = new MyListView (UIUtils.getContext());
        view.setAdapter(new HomeAdapter(data));
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

        return check(data);// 校验数据并返回
    }

    class HomeAdapter extends MyBaseAdapter<AppInfo> {

        public HomeAdapter(ArrayList<AppInfo> data) {
            super(data);
        }

        @Override
        public BaseHolder<AppInfo> getHolder() {
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

}
