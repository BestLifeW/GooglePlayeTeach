package com.lovec.googleplayeteach.ui.fragment;

import android.view.View;

import com.lovec.googleplayeteach.domain.CategoryInfo;
import com.lovec.googleplayeteach.http.protocol.CategoryProtocol;
import com.lovec.googleplayeteach.ui.adapter.MyBaseAdapter;
import com.lovec.googleplayeteach.ui.holder.BaseHolder;
import com.lovec.googleplayeteach.ui.holder.CategoryHolder;
import com.lovec.googleplayeteach.ui.holder.TitleHolder;
import com.lovec.googleplayeteach.ui.view.LoadingPage;
import com.lovec.googleplayeteach.ui.view.MyListView;
import com.lovec.googleplayeteach.utils.UIUtils;

import java.util.ArrayList;

/**
 * Created by lovec on 2016/8/27.
 */
public class CategoryFragment extends BaseFragment {

    private ArrayList<CategoryInfo> data;

    @Override
    public View onCreateSuccessView() {

        MyListView view = new MyListView(UIUtils.getContext());
        view.setAdapter(new CategoryAdapter(data));
        return view;
    }

    @Override
    public LoadingPage.ResultState onLoad() {
        CategoryProtocol protocol = new CategoryProtocol();
        data = protocol.getData(0);
        return check(data);
    }

    class CategoryAdapter extends MyBaseAdapter<CategoryInfo> {

        public CategoryAdapter(ArrayList<CategoryInfo> data) {
            super(data);
        }

        @Override
        public BaseHolder<CategoryInfo> getHolder(int position) {
            CategoryInfo info = data.get(position);
            if (info.isTitle) {
                return new TitleHolder();
            } else {

                return new CategoryHolder();
            }
        }

        @Override
        public int getInnerType(int position) {
            //判断是否是标题类型
            CategoryInfo info = data.get(position);
            if (info.isTitle) {
                //返回标题类型
                return super.getInnerType(position) + 1;
            } else {
                return super.getInnerType(position);
            }
        }

        @Override
        public ArrayList<CategoryInfo> onLoadMore() {
            return null;
        }

        @Override
        public boolean hasMore() {
            return false; //
        }

        @Override
        public int getViewTypeCount() {
            return super.getViewTypeCount() + 1; //原来基础上加1
        }
    }
}
