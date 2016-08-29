package com.lovec.googleplayeteach.ui.fragment;

import android.view.View;

import com.lovec.googleplayeteach.domain.SubjectInfo;
import com.lovec.googleplayeteach.http.protocol.SubjectProtocol;
import com.lovec.googleplayeteach.ui.adapter.MyBaseAdapter;
import com.lovec.googleplayeteach.ui.holder.BaseHolder;
import com.lovec.googleplayeteach.ui.holder.SubjectHolder;
import com.lovec.googleplayeteach.ui.view.LoadingPage;
import com.lovec.googleplayeteach.ui.view.MyListView;
import com.lovec.googleplayeteach.utils.UIUtils;

import java.util.ArrayList;

/**
 * Created by lovec on 2016/8/27.
 */
public class SubjectFragment extends BaseFragment {

    private ArrayList<SubjectInfo> data;

    @Override
    public View onCreateSuccessView() {

        MyListView view = new MyListView(UIUtils.getContext());
        view.setAdapter(new SubjectAdapter(data));
        return view;
    }

    @Override
    public LoadingPage.ResultState onLoad() {
        SubjectProtocol protocol = new SubjectProtocol();
        data = protocol.getData(0);
        return check(data);
    }

    class SubjectAdapter extends MyBaseAdapter<SubjectInfo> {

        public SubjectAdapter(ArrayList<SubjectInfo> data) {
            super(data);
        }

        @Override
        public BaseHolder<SubjectInfo> getHolder() {
            return new SubjectHolder();
        }

        @Override
        public ArrayList<SubjectInfo> onLoadMore() {
            SubjectProtocol protocol = new SubjectProtocol();
            ArrayList<SubjectInfo> moreData = protocol.getData(getListSize());
            return moreData;
        }
    }
}
