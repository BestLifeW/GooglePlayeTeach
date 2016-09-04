package com.lovec.googleplayeteach.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.lovec.googleplayeteach.ui.holder.BaseHolder;
import com.lovec.googleplayeteach.ui.holder.MoreHolder;
import com.lovec.googleplayeteach.utils.UIUtils;

import java.util.ArrayList;

/**
 * adapter
 * Created by lovec on 2016/8/27.
 */
public abstract class MyBaseAdapter<T> extends BaseAdapter {

    private static final int TYPE_NORMAL = 1;
    private static final int TYPE_MORE = 0;


    private ArrayList<T> data;

    public MyBaseAdapter(ArrayList<T> data) {
        this.data = data;
    }

    @Override
    public int getViewTypeCount() {
        return 2;//返回两种 一个是普通+加载更多
    }

    @Override
    public int getItemViewType(int position) {

        if (position == getCount() - 1) {//最后一个类型
            return TYPE_MORE;
        } else {
            return getInnerType(position);
        }
    }

    public int getInnerType(int position) {
        return TYPE_NORMAL;
    }

    @Override
    public int getCount() {
        return data.size() + 1;

    }

    @Override
    public T getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseHolder holder;
        if (convertView == null) {
            // 加载布局文件，初始化控件 打一个标记 全做了
            if (getItemViewType(position) == TYPE_MORE) {
                holder = new MoreHolder(hasMore());
            } else {
                holder = getHolder(position);
            }
        } else {
            holder = (BaseHolder) convertView.getTag();
        }
        //4 根据数据来刷新界面
        if (getItemViewType(position) != TYPE_MORE) {
            holder.setData(getItem(position));
        } else {
            MoreHolder moreHolder = (MoreHolder) holder;
            //加载更多布局
            //只有在有更多才加载
            if (moreHolder.getData() == moreHolder.STATE_MORE_MORE) {
                loadMore(moreHolder);
            }
        }

        return holder.getRootView();
    }

    //子类可以重写此方法
    public boolean hasMore() {
        return true;//默认有更多数据
    }

    //返回当前界面的holder对象 必须子类实现
    public abstract BaseHolder<T> getHolder(int position);


    private boolean isLoadMore = false; //标记是否正在加载中

    //加载更多数据
    public void loadMore(final MoreHolder holder) {
        if (!isLoadMore) {
            isLoadMore = true;
            new Thread(new Runnable() {

                @Override
                public void run() {
                    final ArrayList<T> moreData = onLoadMore();

                    UIUtils.runOnUIThread(new Runnable() {
                        @Override
                        public void run() {
                            if (moreData != null) {
                                //
                                if (moreData.size() < 20) {
                                    holder.setData(MoreHolder.STATE_MORE_NONE);
                                    Toast.makeText(UIUtils.getContext(), "没有更多数据了", Toast.LENGTH_LONG).show();
                                } else {
                                    holder.setData(MoreHolder.STATE_MORE_MORE);
                                }
                                //将更多的数据添加到集合中
                                data.addAll(moreData);
                                MyBaseAdapter.this.notifyDataSetChanged();
                            } else {
                                //加载更多失败
                                holder.setData(MoreHolder.STATE_MORE_ERROR);
                            }
                            isLoadMore = false;
                        }
                    });
                }
            }).start();
        }

    }

    public abstract ArrayList<T> onLoadMore();

    //获取当前集合大小
    public int getListSize() {
        return data.size();
    }
}
