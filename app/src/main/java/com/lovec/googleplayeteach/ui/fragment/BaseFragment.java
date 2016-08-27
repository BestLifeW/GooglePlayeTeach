package com.lovec.googleplayeteach.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lovec.googleplayeteach.ui.view.LoadingPage;
import com.lovec.googleplayeteach.ui.view.LoadingPage.ResultState;
import com.lovec.googleplayeteach.utils.UIUtils;

import java.util.ArrayList;

/**
 * Created by lovec on 2016/8/27.
 */
public abstract class BaseFragment extends Fragment {

    private LoadingPage mLoadingPage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mLoadingPage = new LoadingPage(UIUtils.getContext()) {
            @Override
            public View onCreateSuccessView() {
                View view = BaseFragment.this.onCreateSuccessView();
                return view;
            }

            @Override
            protected ResultState onLoad() {
                return BaseFragment.this.onLoad();
            }
        };
        return mLoadingPage;
    }

    public abstract View onCreateSuccessView();

    public abstract LoadingPage.ResultState onLoad();

    //加载数据
    public void loadData() {
        if (mLoadingPage != null) {
            mLoadingPage.loadData();
        }
    }

    // 对网络返回数据的合法性进行校验
    public ResultState check(Object obj) {
        if (obj != null) {
            if (obj instanceof ArrayList) {// 判断是否是集合
                ArrayList list = (ArrayList) obj;

                if (list.isEmpty()) {
                    return ResultState.STATE_EMPTY;
                } else {
                    return ResultState.STATE_SUCCESS;
                }
            }
        }
        return ResultState.STATE_ERROR;
    }
}

