package com.lovec.googleplayeteach.ui.holder;

import android.view.View;

/**
 * Created by lovec on 2016/8/27.
 */
public abstract class BaseHolder<T> {

    private final View mRootView;
    private T data;

    public BaseHolder() {
        mRootView = initView();
        //打标记
        mRootView.setTag(this);
    }

    public abstract View initView();

    public View getRootView() {
        return mRootView;
    }

    //设置数据
    public void setData(T data) {
        this.data = data;
        refreshView(data);
    }

    public T getData() {
        return data;
    }

    //根据数据刷新界面
    public abstract void refreshView(T data);
}
