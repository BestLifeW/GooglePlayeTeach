package com.lovec.googleplayeteach.ui.fragment;

import android.view.View;

import com.lovec.googleplayeteach.domain.AppInfo;
import com.lovec.googleplayeteach.http.protocol.AppProtocol;
import com.lovec.googleplayeteach.ui.adapter.MyBaseAdapter;
import com.lovec.googleplayeteach.ui.holder.AppHolder;
import com.lovec.googleplayeteach.ui.holder.BaseHolder;
import com.lovec.googleplayeteach.ui.view.LoadingPage;
import com.lovec.googleplayeteach.ui.view.MyListView;
import com.lovec.googleplayeteach.utils.UIUtils;

import java.util.ArrayList;

/**
 * Created by lovec on 2016/8/27.
 */
public class AppFragment extends BaseFragment {

    private ArrayList<AppInfo> data;

    @Override
    public View onCreateSuccessView() {
        MyListView view = new MyListView(UIUtils.getContext());
        view.setAdapter(new AppAdapter(data));
        return view;
    }

    @Override
    public LoadingPage.ResultState onLoad() {
        AppProtocol appProtocol = new AppProtocol();
        data = appProtocol.getData(0);
        return check(data);
    }

    class AppAdapter extends MyBaseAdapter<AppInfo> {

        public AppAdapter(ArrayList<AppInfo> data) {
            super(data);
        }

        @Override
        public BaseHolder<AppInfo> getHolder() {
            return new AppHolder();
        }

        @Override
        public ArrayList<AppInfo> onLoadMore() {
            AppProtocol appProtocol = new AppProtocol();
            ArrayList<AppInfo> moreData = appProtocol.getData(getListSize());
            return moreData;
        }
    }
}
